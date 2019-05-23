package com.zhhl.cloudpolice.mvp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.mvp.model.entity.Statistics;
import com.zhhl.cloudpolice.view.VerticalProgressbar;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by miao on 2018/9/20.
 */
public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder> {

    int color[] = new int[]{
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_purple,
            android.R.color.holo_green_light,
            android.R.color.holo_green_dark,
            android.R.color.holo_blue_bright,
            android.R.color.holo_blue_dark,
            android.R.color.holo_blue_light,
            android.R.color.holo_orange_dark,
            android.R.color.background_dark
    };

    private int max;

    public StatisticsAdapter(ArrayList<Statistics> statistics) {
        this.statistics = new ArrayList<>();
        this.statistics.addAll(statistics);
        ArrayList<Statistics> s2 = new ArrayList<>(statistics);
        Collections.sort(s2);
        max = (int) (Integer.parseInt(s2.get(s2.size() - 1).getCount())*1.0 / 2 * 3);


    }

    private ArrayList<Statistics> statistics;

    public void setStatistics(ArrayList<Statistics> statistics) {

        ArrayList<Statistics> s2 = new ArrayList<>(statistics);
        Collections.sort(s2);
        max = (int) (Integer.parseInt(s2.get(s2.size() - 1).getCount()) *1.0/ 2 * 3);
        this.statistics.clear();
        this.statistics.addAll(statistics);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StatisticsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_statistics, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsViewHolder statisticsViewHolder, int i) {
        Statistics s = statistics.get(i);
        statisticsViewHolder.mItem.setProgressColor(statisticsViewHolder.mItem.getContext().getResources().getColor(color[i % color.length]));
        statisticsViewHolder.mItem.setProgress(Integer.parseInt(s.getCount()));
        statisticsViewHolder.mItem.setTitle(s.getName());
        statisticsViewHolder.mItem.setMax(max);
        Log.e("onBind :max: ", "" + max);
        Log.e("onBind :count: ", "" + s.getCount());
    }

    @Override
    public int getItemCount() {
        return statistics.size();
    }

    public class StatisticsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mItem)
        VerticalProgressbar mItem;

        public StatisticsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
