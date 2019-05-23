package com.zhhl.cloudpolice.mvp.view.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.common.fragment.BaseFragment;
import com.zhhl.cloudpolice.common.tcp.data.TableHYRY;
import com.zhhl.cloudpolice.common.tcp.data.TableKSXX;
import com.zhhl.cloudpolice.common.tcp.data.TableSZQY;
import com.zhhl.cloudpolice.common.tcp.data.TableWZRZ;
import com.zhhl.cloudpolice.mvp.EarlyWarningAdapter;
import com.zhhl.cloudpolice.mvp.model.entity.EarlyWarningData;
import com.zhhl.cloudpolice.mvp.model.entity.QueryResultData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarlyFragment extends BaseFragment {

    private ViewHolder vh;
    private EarlyWarningAdapter mAdapter = new EarlyWarningAdapter(new ArrayList<>());
    private View nothing;

    public EarlyFragment() {
        // Required empty public constructor
    }

    public static EarlyFragment newInstance(String param1, String param2) {
        return new EarlyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        nothing = View.inflate(getContext(), R.layout.item_header_nothing, null);
        View inflate = inflater.inflate(R.layout.fragment_early, container, false);
        vh = new ViewHolder(inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mAdapter.getCount() == 0) {
            vh.mEarlyList.addHeaderView(nothing);
        } else {
            vh.mEarlyList.removeHeaderView(nothing);
        }
        vh.mEarlyList.setAdapter(mAdapter);
    }

    static class ViewHolder {
        @BindView(R.id.mTitle)
        TextView mTitle;
        @BindView(R.id.mEarlyList)
        ListView mEarlyList;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    @Override
    public void put(Bundle bundle) {
        super.put(bundle);
        switch (bundle.getString("cmd")) {
            case "add":

                if (mAdapter.getCount() == 0) {
                    if (vh != null && vh.mEarlyList != null) {
                        vh.mEarlyList.removeHeaderView(nothing);
                    }
                }
                int flag = bundle.getInt("flag", 0);

                Parcelable q = bundle.getParcelable("data");
                QueryResultData queryResultData;
                switch (flag) {
                    case 1:
                        queryResultData = new QueryResultData((TableHYRY.ObjBean) q);
                        break;
                    case 2:
                        queryResultData = new QueryResultData((TableKSXX.ObjBean) q);
                        break;
                    case 3:
                        queryResultData = new QueryResultData((TableSZQY.ObjBean) q);
                        break;
                    case 4:
                        queryResultData = new QueryResultData((TableWZRZ.ObjBean) q);
                        break;

                    default:
                        queryResultData = new QueryResultData();
                }
                EarlyWarningData earlyWarningData = new EarlyWarningData();
                earlyWarningData.setMessage(queryResultData.getName() + "-" + queryResultData.getIdentity() + "-已对比");
                earlyWarningData.setTime(queryResultData.getTime());
                mAdapter.addData(earlyWarningData);
                break;
        }


    }
}
