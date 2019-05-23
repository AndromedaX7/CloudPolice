package com.zhhl.cloudpolice.mvp.view.fragment;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhhl.cloudpolice.DateUtil;
import com.zhhl.cloudpolice.DialogUtils;
import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.common.fragment.BaseFragment;
import com.zhhl.cloudpolice.common.tcp.HttpTools;
import com.zhhl.cloudpolice.common.tcp.data.TableHYRY;
import com.zhhl.cloudpolice.common.tcp.data.TableKSXX;
import com.zhhl.cloudpolice.common.tcp.data.TableSZQY;
import com.zhhl.cloudpolice.common.tcp.data.TableWZRZ;
import com.zhhl.cloudpolice.common.tcp.inf.QueryAll;
import com.zhhl.cloudpolice.mvp.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QueryFragment extends BaseFragment {

    private QueryAll queryAll;
    private String value;
    @BindView(R.id.input)
    Spinner input;
    @BindView(R.id.startTime)
    TextView startTime;
    @BindView(R.id.endTime)
    TextView endTime;
    @BindView(R.id.mQuery)
    TextView mQuery;

    public QueryFragment() {
        // Required empty public constructor
    }

    private SpinnerAdapter mAdapter = new SpinnerAdapter(new ArrayList<>());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<SpinnerAdapter.Data> data = new ArrayList<>();
        data.add(new SpinnerAdapter.Data("请选择查询种类", ""));
        data.add(new SpinnerAdapter.Data("行业人员", "hyry"));
        data.add(new SpinnerAdapter.Data("开锁信息", "ksxx"));
        data.add(new SpinnerAdapter.Data("散装汽油", "szqy"));
        data.add(new SpinnerAdapter.Data("无证入住", "wzrz"));
        mAdapter.setData(data);
        input.setAdapter(mAdapter);
        input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                value = mAdapter.getItem(position).getValue();
                Log.e(TAG, "onItemSelected: " + value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @OnClick({R.id.startTime, R.id.endTime, R.id.mQuery})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.startTime:
                createOrShow(1);
                break;
            case R.id.endTime:
                createOrShow(2);
                break;
            case R.id.mQuery:
                queryAll = HttpTools.build(QueryAll.class);
                if (progressDialog == null)
                    progressDialog = DialogUtils.createProgressDialog(getContext());
                if (!value.equals(""))
                    progressDialog.show();
                switch (value) {
                    case "hyry":
                        Observable<TableHYRY> hyry = queryAll.queryHYRY("hyry");
                        hyry.observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::data, this::err, this::onComplete);

                        break;
                    case "ksxx":
                        Observable<TableKSXX> ksxx =
 queryAll.queryKSXX("ksxx");
//                                Observable.create(new ObservableOnSubscribe<TableKSXX>() {
//                                    @Override
//                                    public void subscribe(ObservableEmitter<TableKSXX> e) throws Exception {
//                                        e.onNext(new Gson().fromJson("{\n" +
//                                                "    \"success\": true,\n" +
//                                                "    \"msg\": \"查询成功\",\n" +
//                                                "    \"obj\": [\n" +
//                                                "        {\n" +
//                                                "            \"id\": \"13FC37A3664C49AAA10671CAC90E9A76\",\n" +
//                                                "            \"address\": \"吉林省长春市朝阳区南湖街道前进大街力旺广场\",\n" +
//                                                "            \"detail_address\": \"B座1410室\",\n" +
//                                                "            \"unlocking_person\": \"冯金龙\",\n" +
//                                                "            \"unlocking_person_num\": null,\n" +
//                                                "            \"by_unlocking_person\": \"冯金龙\",\n" +
//                                                "            \"by_unlocking_person_num\": \"220183199606051012\",\n" +
//                                                "            \"create_date\": \"2018-08-02 11:00\",\n" +
//                                                "            \"userid\": \"17600194545\",\n" +
//                                                "            \"dept_name\": \"系统维护\",\n" +
//                                                "            \"longitude\": \"125.29013\",\n" +
//                                                "            \"latitude\": \"43.84066\",\n" +
//                                                "\"flag\": \"2\" " +
//                                                "        },\n" +
//                                                "        {\n" +
//                                                "            \"id\": \"21800237DE3B499C9F75B393860F6E63\",\n" +
//                                                "            \"address\": \"吉林省长春市朝阳区南湖街道\",\n" +
//                                                "            \"detail_address\": \"力旺广场14\",\n" +
//                                                "            \"unlocking_person\": \"段云龙\",\n" +
//                                                "            \"unlocking_person_num\": null,\n" +
//                                                "            \"by_unlocking_person\": \"段云龙\",\n" +
//                                                "            \"by_unlocking_person_num\": \"429006199301054295\",\n" +
//                                                "            \"create_date\": \"2018-09-07 15:11\",\n" +
//                                                "            \"userid\": \"18043134285\",\n" +
//                                                "            \"dept_name\": \"系统维护\",\n" +
//                                                "            \"longitude\": \"125.29013\",\n" +
//                                                "            \"latitude\": \"43.84066\",\n" +
//                                                "\"flag\": null\n" +
//                                                "        }\n" +
//                                                "    ],\n" +
//                                                "    \"attributes\": null\n" +
//                                                "}", TableKSXX.class));
//                                        e.onComplete();
//                                    }
//                                });
                        ksxx.observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::data2, this::err, this::onComplete);
                        break;
                    case "szqy":
                        Observable<TableSZQY> szqy = queryAll.querySZQY("szqy");
                        szqy.observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::data3, this::err, this::onComplete);
                        break;
                    case "wzrz":
                        Observable<TableWZRZ> wzrz = queryAll.queryWZRZ("wzrz");
                        wzrz.observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::data4, this::err, this::onComplete);
                        break;
                    default:
                        Toast.makeText(getContext(), "请选择有效的选项", Toast.LENGTH_SHORT).show();

                }


                break;
        }
    }

    private void data4(TableWZRZ tableWZRZ) {
        Bundle bundle = new Bundle();
        if (!tableWZRZ.isSuccess()) {
            bundle.putString("err", tableWZRZ.getMsg());
        } else {
            ArrayList<TableWZRZ.ObjBean> obj = (ArrayList<TableWZRZ.ObjBean>) tableWZRZ.getObj();
            bundle.putInt("intType", 4);
            bundle.putParcelableArrayList("data", obj);
        }
        openCallback.open(QueryResultFragment.class, bundle, false);
    }

    private void data3(TableSZQY tableSZQY) {

        Bundle bundle = new Bundle();
        if (!tableSZQY.isSuccess()) {
            bundle.putString("err", tableSZQY.getMsg());
        } else {
            ArrayList<TableSZQY.ObjBean> obj = (ArrayList<TableSZQY.ObjBean>) tableSZQY.getObj();
            bundle.putInt("intType", 3);
            bundle.putParcelableArrayList("data", obj);
        }
        openCallback.open(QueryResultFragment.class, bundle, false);
    }

    private void data2(TableKSXX tableKSXX) {
        Bundle bundle = new Bundle();
        if (!tableKSXX.isSuccess()) {
            bundle.putString("err", tableKSXX.getMsg());
        } else {
            ArrayList<TableKSXX.ObjBean> obj = (ArrayList<TableKSXX.ObjBean>) tableKSXX.getObj();
            bundle.putInt("intType", 2);
            bundle.putParcelableArrayList("data", obj);
        }
        openCallback.open(QueryResultFragment.class, bundle, false);

    }


    public void createOrShow(int id) {
        GregorianCalendar g = new GregorianCalendar();

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int fid = id;
                g.set(Calendar.YEAR, year);
                g.set(Calendar.MONTH, month);
                g.set(Calendar.DATE, dayOfMonth);
                long timeInMillis = g.getTimeInMillis();
                String text = DateUtil.format("yyyy-MM-dd", timeInMillis);
                if (fid == 1) {
                    startTime.setText(text);
                } else
                    endTime.setText(text);
            }
        }, g.get(Calendar.YEAR), g.get(Calendar.MONTH), g.get(Calendar.DATE));
        datePickerDialog.show();
    }


    private DatePickerDialog datePickerDialog;

    private void err(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        if (progressDialog != null) progressDialog.dismiss();
    }

    private void data(TableHYRY tableHYRY) {
        Bundle bundle = new Bundle();
//        Toast.makeText(getContext(), new Gson().toJson(tableHYRY), Toast.LENGTH_SHORT).show();
        if (!tableHYRY.isSuccess()) {
            bundle.putString("err", tableHYRY.getMsg());
        } else {
            ArrayList<TableHYRY.ObjBean> obj = (ArrayList<TableHYRY.ObjBean>) tableHYRY.getObj();
            bundle.putInt("intType", 1);
            bundle.putParcelableArrayList("data", obj);
        }
        openCallback.open(QueryResultFragment.class, bundle, false);
    }


    private void onComplete() {
        Log.e(TAG, "onComplete: ");
        if (progressDialog != null) progressDialog.dismiss();
    }

    private String TAG = getClass().getSimpleName();
    ProgressDialog progressDialog;
}
