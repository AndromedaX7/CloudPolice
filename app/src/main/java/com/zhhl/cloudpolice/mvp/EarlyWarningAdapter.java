package com.zhhl.cloudpolice.mvp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhhl.cloudpolice.DateUtil;
import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.common.adapter.BaseAdapter;
import com.zhhl.cloudpolice.mvp.model.entity.EarlyWarningData;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by miao on 2018/11/6.
 */
public class EarlyWarningAdapter extends BaseAdapter<EarlyWarningData, EarlyWarningAdapter.EarlyWarningViewHolder> {


    public EarlyWarningAdapter(ArrayList<EarlyWarningData> data) {
        super(data);
    }

    @Override
    protected EarlyWarningViewHolder create(View view, int itemViewType) {
        return new EarlyWarningViewHolder(view);
    }

    @Override
    protected void bindView(EarlyWarningViewHolder earlyWarningViewHolder, int position, EarlyWarningData item) {
        earlyWarningViewHolder.mContent.setText(item.getMessage());
        earlyWarningViewHolder.mIsNew.setVisibility(View.VISIBLE);
        earlyWarningViewHolder.mNewFlag.setVisibility(View.VISIBLE);
        earlyWarningViewHolder.mTime.setText(DateUtil.format("MM-dd HH:mm", item.getTime()));
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_early_list;
    }

    public void addData(EarlyWarningData earlyWarningData) {
        data.add(earlyWarningData);
        notifyDataSetChanged();
    }

    static class EarlyWarningViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.mTime)
        TextView mTime;
        @BindView(R.id.mIsNew)
        TextView mIsNew;
        @BindView(R.id.mContent)
        TextView mContent;
        @BindView(R.id.mNewFlag)
        ImageView mNewFlag;

        public EarlyWarningViewHolder(View view) {
            super(view);
        }
    }
}
