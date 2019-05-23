package com.zhhl.cloudpolice.mvp;

import android.view.View;
import android.widget.TextView;

import com.zhhl.cloudpolice.common.adapter.BaseAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by miao on 2018/11/9.
 */
public class SpinnerAdapter extends BaseAdapter<SpinnerAdapter.Data, SpinnerAdapter.ViewHolder> {

    public SpinnerAdapter(ArrayList<Data> data) {
        super(data);
    }

    @Override
    protected ViewHolder create(View view, int itemViewType) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(ViewHolder viewHolder, int position, Data item) {
        viewHolder.text.setText(item.name);
    }

    @Override
    protected int getDefaultLayout() {
        return android.R.layout.simple_list_item_1;
    }

    public static class ViewHolder extends BaseAdapter.ViewHolder {

        @BindView(android.R.id.text1)
        TextView text;

        public ViewHolder(View view) {
            super(view);
        }
    }


    public static class Data {
        String name;
        String value;

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Data() {

        }

        public Data(String name, String value) {

            this.name = name;
            this.value = value;
        }
    }
}
