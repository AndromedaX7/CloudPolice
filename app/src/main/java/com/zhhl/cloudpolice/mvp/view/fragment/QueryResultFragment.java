package com.zhhl.cloudpolice.mvp.view.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.common.fragment.BaseFragment;
import com.zhhl.cloudpolice.common.tcp.data.TableHYRY;
import com.zhhl.cloudpolice.common.tcp.data.TableKSXX;
import com.zhhl.cloudpolice.common.tcp.data.TableSZQY;
import com.zhhl.cloudpolice.common.tcp.data.TableWZRZ;
import com.zhhl.cloudpolice.mvp.QueryResultAdapter;
import com.zhhl.cloudpolice.mvp.model.entity.QueryResultData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QueryResultFragment extends BaseFragment implements QueryResultAdapter.CallOutLine {

    private QueryResultAdapter.CallOutLine call;

    public void setCall(QueryResultAdapter.CallOutLine call) {
        this.call = call;
    }

    @BindView(R.id.meBack)
    ImageView meBack;
    @BindView(R.id.mTitle)
    TextView mTitle;
    @BindView(R.id.mQueryContent)
    ListView mQueryContent;

    private View nothing;

    private QueryResultAdapter mAdapter = new QueryResultAdapter(new ArrayList<>());

    public QueryResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_query_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter.setCallOutLine(this);
        mQueryContent.setAdapter(mAdapter);
        if (mAdapter.getCount() == 0) {
            if (nothing == null)
                nothing = View.inflate(getContext(), R.layout.item_header_nothing, null);
            mQueryContent.addHeaderView(nothing);
        } else {
            mQueryContent.removeHeaderView(nothing);
        }

    }


    @Override
    public void put(Bundle bundle) {
        super.put(bundle);

        try {
            ArrayList<Parcelable> data = bundle.getParcelableArrayList("data");
            int type = bundle.getInt("intType");
            if (bundle.getString("err") != null || data == null || data.size() == 0) {
                if (bundle.getString("err") != null) {
//                Toast.makeText(getContext(), bundle.getString("err"), Toast.LENGTH_SHORT).show();
                    mAdapter.setData(new ArrayList<>());
                }
                if (mQueryContent != null) {
                    if (nothing == null) {
                        nothing = View.inflate(getContext(), R.layout.item_header_nothing, null);
                    }
                    if (mQueryContent.getHeaderViewsCount() == 0)
                        mQueryContent.addHeaderView(nothing);

                }
                return;
            } else {
                if (mQueryContent != null && nothing != null && mQueryContent.getHeaderViewsCount() > 0) {
                    mQueryContent.removeHeaderView(nothing);
                }
            }


            ArrayList<QueryResultData> source = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                source.add(wrapper(type, data.get(i)));
            }

            mAdapter.setData(source);
            if (mQueryContent != null)
                mQueryContent.setAdapter(mAdapter);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private QueryResultData wrapper(int type, Parcelable data) {
        QueryResultData data1;
        switch (type) {
            case 1:
                TableHYRY.ObjBean t = (TableHYRY.ObjBean) data;

                data1 = new QueryResultData(t);
                break;
            case 2:
                TableKSXX.ObjBean t2 = (TableKSXX.ObjBean) data;
                data1 = new QueryResultData(t2);
                break;
            case 3:
                TableSZQY.ObjBean t3 = (TableSZQY.ObjBean) data;
                data1 = new QueryResultData(t3);
                break;
            case 4:
                TableWZRZ.ObjBean t4 = (TableWZRZ.ObjBean) data;
                data1 = new QueryResultData(t4);
                break;
            default:
                data1 = new QueryResultData();
        }
        return data1;
    }

    @OnClick(R.id.meBack)
    void meBack() {
        closeCallback.onClose();
    }

    @Override
    public void call(int tag, QueryResultAdapter.Flag flag) {
        Bundle bundle = new Bundle();
        QueryResultData item = mAdapter.getItem(tag);
        bundle.putParcelable("data", item.getByType());
        bundle.putInt("flag", item.getType());
        bundle.putString("cmd", "add");


//        if (flag.getFlag() == 0) {
//            mAdapter.getData().remove(tag);
//            mAdapter.notifyDataSetChanged();
//        }
        if (call != null) call.call(0, flag);
        openCallback.open(EarlyFragment.class, bundle, false);
    }
}
