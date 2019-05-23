package com.zhhl.cloudpolice.mvp;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zhhl.cloudpolice.DateUtil;
import com.zhhl.cloudpolice.DialogUtils;
import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.common.adapter.BaseAdapter;
import com.zhhl.cloudpolice.common.tcp.HttpTools;
import com.zhhl.cloudpolice.common.tcp.data.TableHYRY;
import com.zhhl.cloudpolice.common.tcp.data.TableKSXX;
import com.zhhl.cloudpolice.common.tcp.data.TableSZQY;
import com.zhhl.cloudpolice.common.tcp.data.TableWZRZ;
import com.zhhl.cloudpolice.common.tcp.inf.QueryItem;
import com.zhhl.cloudpolice.mvp.model.entity.CompareData;
import com.zhhl.cloudpolice.mvp.model.entity.DefaultResult;
import com.zhhl.cloudpolice.mvp.model.entity.ItemDetails;
import com.zhhl.cloudpolice.mvp.model.entity.QueryResultData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miao on 2018/11/6.
 */
public class QueryResultAdapter extends BaseAdapter<QueryResultData, QueryResultAdapter.QueryResultViewHolder> {

    public void setCallOutLine(CallOutLine callOutLine) {
        this.callOutLine = callOutLine;
    }

    private CallOutLine callOutLine;

    private HashMap<String, ItemDetails.ObjBean> maps = new HashMap<>();
    private ProgressDialog loading;


    public static enum Flag {
        A(0), B(1), C(2), D(3);

        Flag(int flag) {
            this.flag = flag;
        }

        private int flag;

        public int getFlag() {
            return flag;
        }
    }

    public interface CallOutLine {
        void call(int tag, Flag flag);
    }

    public QueryResultAdapter(ArrayList<QueryResultData> data) {
        super(data);
    }

    @Override
    protected QueryResultViewHolder create(View view, int itemViewType) {
        return new QueryResultViewHolder(view);
    }

    @Override
    protected void bindView(QueryResultViewHolder vh, int position, QueryResultData item) {
        int type = item.getType();
        boolean upLoad = true;
        switch (type) {
            case 1:
                TableHYRY.ObjBean hy = item.getHy();
                if (hy.getFlag() == null) {
                    upLoad = true;
                } else
                    upLoad = !hy.getFlag().equals("2");
                vh.invoke.setVisibility(upLoad ? View.VISIBLE : View.INVISIBLE);
                vh.queryName.setText(hy.getUser_name());
                vh.queryUserId.setText(hy.getId_number());
                break;
            case 2:
                TableKSXX.ObjBean ks = item.getKs();
                if (ks.getFlag() == null) {
                    upLoad = true;
                } else
                    upLoad = !ks.getFlag().equals("2");
                vh.invoke.setVisibility(upLoad ? View.VISIBLE : View.INVISIBLE);
                vh.queryName.setText(ks.getBy_unlocking_person());
                vh.queryUserId.setText(ks.getBy_unlocking_person_num());
                break;
            case 3:
                TableSZQY.ObjBean sz = item.getSz();
                if (sz.getFlag() == null) {
                    upLoad = true;
                } else
                    upLoad = !sz.getFlag().equals("2");
                vh.invoke.setVisibility(upLoad ? View.VISIBLE : View.INVISIBLE);
                vh.queryName.setText(sz.getName());
                vh.queryUserId.setText(sz.getIdentity_num());
                break;
            case 4:
                TableWZRZ.ObjBean wz = item.getWz();
                if (wz.getFlag() == null) {
                    upLoad = true;
                } else
                    upLoad = !wz.getFlag().equals("2");
                vh.invoke.setVisibility(upLoad ? View.VISIBLE : View.INVISIBLE);
                vh.queryName.setText(wz.getName());
                vh.queryUserId.setText(wz.getIdentity_num());
                break;
        }


//        vh.invoke.setVisibility(upLoad ? View.VISIBLE : View.INVISIBLE);
        vh.upLoadFlag.setVisibility(View.GONE);

        vh.queryTime.setText(DateUtil.format("[yyyy-MM-dd]", item.getTime()));


        if (upLoad) {
            vh.invoke.setText("对比");
            vh.invoke.setTextColor(context.getResources().getColor(android.R.color.white));
            vh.invoke.setBackgroundColor(context.getResources().getColor(R.color.colorOrange));

        } else {
            vh.invoke.setText("已对比");
            vh.invoke.setBackgroundColor(context.getResources().getColor(R.color.colorBg));
            vh.invoke.setTextColor(context.getResources().getColor(R.color.colorTextColorLight));
        }
        vh.invoke.setTag(position);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_query_result;
    }


    class QueryResultViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.queryName)
        TextView queryName;
        @BindView(R.id.queryTime)
        TextView queryTime;
        @BindView(R.id.upLoadFlag)
        TextView upLoadFlag;
        @BindView(R.id.queryUserId)
        TextView queryUserId;
        @BindView(R.id.invoke)
        TextView invoke;


        @OnClick(R.id.invoke)
        void onInvoked(View view) {
            int tag = (int) view.getTag();
            loading = loading == null ? DialogUtils.createProgressDialog(context) : loading;
            loading.show();
            QueryResultData item = getItem(tag);


            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception {

                    try {
                        e.onNext(connect(item.getIdentity()));
                        e.onComplete();
                    } catch (Exception e2) {
                        e.onError(e2);
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe((value) -> {

                        /*TODO : TEST > remove> */
//                        queryName.setText(value);
                        XStream xStream = new XStream(new DomDriver());
                        xStream.ignoreUnknownElements();
                        XStream.setupDefaultSecurity(xStream);
                        xStream.allowTypes(new Class[]{CompareData.RBMPMessage.class});
                        xStream.processAnnotations(CompareData.RBMPMessage.class);
                        String startFlag = ":return>";
                        String endFlag = "</ns:return";
                        int i = value.indexOf(startFlag);
                        int ed = value.indexOf(endFlag);
                        System.out.println(i + startFlag.length());
                        value = value.substring(i + startFlag.length(), ed);
                        System.out.println(value);
                        CompareData.RBMPMessage r = (CompareData.RBMPMessage) xStream.fromXML(value);
                        CompareData.Row row = r.getMethod().getItems().getItem().getValue().getRow().get(0);//todo row id ==?
                        loading.dismiss();
                        if (row.getData().get(0).equals("000")) {
                            if (Integer.parseInt(row.getData().get(1)) > 0) {//TODO::数据长度
                                invoke.setText("已推送");


                                ArrayList<CompareData.Row> rows = r.getMethod().getItems().getItem().getValue().getRow();
                                int flag = 0;
                                if (rows.size() > 2) {
                                    String s = rows.get(2).getData().get(2).trim();

                                    flag = Integer.parseInt(s, 2) >> (s.length() - 7);
                                }

//                                getItem(tag).setFlag(flag);
                                callOutLine.call(tag, Flag.B);
                            } else {
                                invoke.setText("已对比");
//                                getItem(tag).setFlag(0);
                            }

                            Log.e( "onInvoked: ", getId(item));
                            HttpTools.build(QueryItem.class)
                                    .updateItem(getByType(item.getType()), getId(item))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(this::itemUpdate, this::err, this::onComplete)
                                    .isDisposed();
                            invoke.setBackgroundColor(context.getResources().getColor(R.color.colorBg));
                            invoke.setTextColor(context.getResources().getColor(R.color.colorTextColorLight));
                        }
//                        Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
                    }, this::err, this::onComplete).isDisposed();

        }

        private void itemUpdate(DefaultResult defaultResult) {
            Log.e("update", "itemUpdate: "+defaultResult.isSuccess()+defaultResult.getMsg());
        }

        private String getId(QueryResultData item) {

            int type = item.getType();
            switch (type){
                case 1:
                    return item.getHy().getId();
                case 2:
                    return item.getKs().getId();
                case 3:
                    return item.getSz().getId();
                case 4:
                    return item.getWz().getId();
            }
            return null;
        }

        private void onComplete() {

        }

        private void err(Throwable throwable) {
            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }

        public QueryResultViewHolder(View view) {
            super(view);
        }
    }

    private String getByType(int type) {

        switch (type) {
            case 1:
                return "hyry";
            case 2:
                return "ksxx";
            case 3:
                return "szqy";
            case 4:
                return "wzrz";
        }
        return "";
    }


    private String connect(String sfid) throws IOException {
        /*todo ::url change*/
        String url = "http://192.168.20.228:7097/dsp/services/GabRequestServiceServer?wsdl";
        String current = "<?xml version=\"1.0\" ?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><QueryToXml xmlns=\"http://server.webservice.dsp.dm.com\"><SenderID>C00-00000013</SenderID><ServiceID>S10-00000077</ServiceID><EndUser>刘雷</EndUser><EndUser>220211198206280034</EndUser><EndUser>吉林省吉林市公安局科技通信处信息化科</EndUser><EndUser>220211198206280034</EndUser><DataObjectCode>dataset_A829041C363DF97C8452CCB850E17540</DataObjectCode><Condition>"/*XM like '" + "王%"*/+"GMSFHM = '" + sfid +"'</Condition><RequiredItems>XM</RequiredItems><RequiredItems>GMSFHM</RequiredItems><RequiredItems>ZDRYLBBJ</RequiredItems><InfoCodeMode></InfoCodeMode><PageIndex>1</PageIndex></QueryToXml></S:Body></S:Envelope>";
//        String current3 = "<?xml version=\"1.0\" ?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><QueryToXml xmlns=\"http://server.webservice.dsp.dm.com\"><SenderID>C00-00000013</SenderID><ServiceID>S10-00000091</ServiceID><EndUser>刘雷</EndUser><EndUser>220211198206280034</EndUser><EndUser>吉林省吉林市公安局科技通信处信息化科</EndUser><EndUser>220211198206280034</EndUser><DataObjectCode>dataset_F784A292B2B38042270D7A66D91004F9</DataObjectCode><Condition>CZR_GMSFHM like '2%'</Condition><RequiredItems>CZR_XM</RequiredItems><RequiredItems>JDC_HPHM</RequiredItems><InfoCodeMode></InfoCodeMode><PageIndex>1</PageIndex></QueryToXml></S:Body></S:Envelope>";
        URL url1 = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
        urlConnection.addRequestProperty("Content-Type", "text/xml");
        urlConnection.addRequestProperty("SOAPAction", "");
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(current.getBytes());
        outputStream.flush();
        outputStream.close();
        int responseCode = urlConnection.getResponseCode();
        if (responseCode == 200) {
            InputStream inputStream = urlConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read = 0;
            while ((read = inputStream.read()) != -1) {
                byteArrayOutputStream.write(read);
            }
            return byteArrayOutputStream.toString().replace("&lt;", "<").replace("&gt;", ">");
        } else {
            return responseCode + "";
        }
    }
}
