package com.zhhl.cloudpolice.mvp.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.zhhl.cloudpolice.DateUtil;
import com.zhhl.cloudpolice.MPChartUtils;
import com.zhhl.cloudpolice.R;
import com.zhhl.cloudpolice.app.App;
import com.zhhl.cloudpolice.common.fragment.BaseMvpFragment;
import com.zhhl.cloudpolice.di.component.AppComponent;
import com.zhhl.cloudpolice.di.component.DaggerStatisticsComponent;
import com.zhhl.cloudpolice.di.module.StatisticsModule;
import com.zhhl.cloudpolice.mvp.Contract.StatisticsContract;
import com.zhhl.cloudpolice.mvp.StatisticsAdapter;
import com.zhhl.cloudpolice.mvp.model.entity.Statistics;
import com.zhhl.cloudpolice.mvp.presenter.StatisticsPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by miao on 2018/9/19.
 */

public class StatisticsFragment extends BaseMvpFragment<StatisticsPresenter> implements StatisticsContract.View {

    //    @BindView(R.id.mSelector)
    @BindView(R.id.mStatisticsView)
    BarChart mStatisticsView;
    //    LinearLayout mSelector;
    @BindView(R.id.yestoday)
    TextView yestoday;
    @BindView(R.id.aWeek)
    TextView aWeek;
    @BindView(R.id.aMonth)
    TextView aMonth;
    @BindView(R.id.mCount)
    TextView mCount;
    @BindView(R.id.mPie)
    PieChart mPie;
    @BindView(R.id.flag_0)
    TextView flag0;
    @BindView(R.id.flag_1)
    TextView flag1;
    private static Gson gson = new Gson();


    private StatisticsAdapter adapter;


    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_statistics_2;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerStatisticsComponent
                .builder()
                .appComponent(appComponent)
                .statisticsModule(new StatisticsModule(this))//请将MainActivity_mainModule()第一个首字母改为小写
                .build()
                .inject(this);
    }


    private String sToday;
    private String sYestoday;

    private String sWeek;
    private String sMonth;

    private ArrayList<TextView> sDays = new ArrayList<>();

    @Override
    protected void onCreated() {
        super.onCreated();
        name.add("行业人员");
        name.add("从业人员");
        name.add("静态车辆");
        name.add("开锁人员");
        name.add("散装汽油人员");
        name.add("无证入住人员");

        sDays.add(yestoday);
        sDays.add(aWeek);
        sDays.add(aMonth);

        for (int i2 = 0; i2 < name.size(); i2++) {
            sMap.put(i2, 0);
        }

        getDate();
        MPChartUtils.configBarChart(mStatisticsView, name);
//        MPChartUtils.configPieChart(mPie, name);
        MPChartUtils.configPie(mPie, new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
//        mPresenter.getType(StatisticsFragment.all, sToday, sToday);

    }


    void getDate() {
        sToday = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis());
        sYestoday = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis() - 24 * 3600 * 1000);
        sWeek = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis() - 24 * 60 * 60 * 1000 * 7);
        GregorianCalendar g = new GregorianCalendar();
        int month = g.get(Calendar.MONTH);
        if (month == 0) {
            g.set(Calendar.MONTH, 11);
        } else {
            g.set(Calendar.MONTH, month - 1);
        }
        int actualMaximum = g.getActualMaximum(Calendar.DATE);
        g.set(Calendar.MONTH, month);

        sMonth = DateUtil.format("yyyy-MM-dd", (System.currentTimeMillis() - (24L * 3600 * 1000 * actualMaximum)));
    }


    @Override
    public void put(Bundle bundle) {
        super.put(bundle);

        String cmd = bundle.getString("cmd");
        switch (cmd) {
            case "recall":
                onCreated();
                mPresenter.getType(StatisticsFragment.all, sToday, sToday);
                break;
        }
    }

    @Override
    public void data(ArrayList<Statistics> statistics, int flag, String start, String end, boolean o) {
        if (o) {
            if (statistics != null) {
                ArrayList<String> names = new ArrayList<>();
                ArrayList<Integer> count = new ArrayList<>();


                for (int i = 0; i < statistics.size(); i++) {
                    names.add(statistics.get(i).getName());
                    count.add(Integer.parseInt(statistics.get(i).getCount()));
                }


                ArrayList<BarEntry> entries = new ArrayList<>();


                for (int i = 0; i < count.size(); i++) {
                    int co = count.get(i);
                    entries.add(new BarEntry(i, co));
                }

                MPChartUtils.configBarChart(mStatisticsView, names);
                if (statistics.size() > 0) {
//                BarDataSet barDataSet = MPChartUtils.getBarDataSet(entries, "", Color.parseColor("#FF0000"), Color.parseColor("#00FF00"));
                    MPChartUtils.initBarChart(mStatisticsView, /*barDataSet.getValues()*/entries, "数量", getContext().getResources().getColor(R.color.colorBlueTextBar));
                    Log.w(TAG, "data: " + statistics.size());
                } else {
//                mStatisticsView.setNoDataText("暂无统计数据");
//                mStatisticsView.setData(new BarData());
////                mStatisticsView.notifyDataSetChanged();
//                mStatisticsView.invalidate();
                    mStatisticsView.clear();
                }
//            mStatisticsView.postDelayed(() -> {
            }
        } else {
            data(statistics, flag, start, end);
        }


        String addr = "";
        switch (flag) {
            case 0:
                addr = "/qywxtj/count/hydwtj";
                break;
            case 1:
                addr = "/qywxtj/count/cyrytj";
                break;
            case 2:
                addr = "/qywxtj/count/jtcltj";
                break;
            case 3:
                addr = "/qywxtj/count/ksfwtj";
                break;
            case 4:
                addr = "/qywxtj/count/szqytj";
                break;
            case 5:
                addr = "/qywxtj/count/wzrztj";
                break;
            default:

        }
        App.app().getLogReport().log("{\n" +
                "  \"create_datefrom\": \"" + start + "\",\n" +
                "  \"create_dateto\": \"" + end + "\"\n" +
                "}", addr, gson.toJson(statistics));
    }

    @Override
    public void data(ArrayList<Statistics> statistics, int idx, String startDate, String endDate) {

        StringBuilder builder = new StringBuilder();
        builder.append(DateUtil.format("MM月dd日", DateUtil.parseDate("yyyy-MM-dd", startDate)))
                .append("-")
                .append(DateUtil.format("MM月dd日", DateUtil.parseDate("yyyy-MM-dd", endDate)))
                .append("人员信息统计");
        flag0.setText(builder.toString());
        flag1.setText(builder.toString());
        int count = 0;
        for (Statistics s : statistics) {
            count += Integer.parseInt(s.getCount());
        }
        sMap.put(idx, count);
        update(idx);
    }

    private ArrayList<IBarDataSet> barDataSets = new ArrayList<>();
    private PieDataSet pieDataSets;

    private void update(int idx) {
        Integer integer = sMap.get(idx);
        String name = this.name.get(idx);
        ArrayList<BarEntry> sets = new ArrayList<>();
        sets.add(new BarEntry(idx, integer));
        barDataSets = MPChartUtils.marginData(barDataSets, sets, name, Color.parseColor(colors[idx]));
        MPChartUtils.initBarChart(mStatisticsView, barDataSets);
        int ys = 0;
        for (int i = 0; i < sMap.size(); i++) {
            ys += sMap.get(i);
        }


        Log.e(TAG, "update: " + ys);
        if (ys == 0) {
            mStatisticsView.clear();
        } else {
            ArrayList<PieEntry> ps = new ArrayList<>();
            for (int i = 0; i < sMap.size(); i++) {
                PieEntry p = new PieEntry(sMap.get(i) * 100 / ys, this.name.get(i));
                ps.add(p);
            }
            pieDataSets = new PieDataSet(ps, "");

            ArrayList<Integer> cs = new ArrayList<>();
            for (int i = 0; i < colors2.length; i++) {
                cs.add(Color.parseColor(colors2[i]));
            }
            pieDataSets.setColors(cs);
            mPie.clear();
            MPChartUtils.setData2(mPie, pieDataSets);
//            MPChartUtils.initPieChart(mPie,pieDataSets);
        }
        mCount.setText(String.valueOf(ys));

    }

    private String[] colors = {
            "#3b7ee3",
            "#fb6057",
            "#f8b020", "#3b7ee3",
            "#fb6057",
            "#f8b020", "#3b7ee3",
            "#fb6057",
            "#f8b020",

    };
    private String[] colors2 = {
            "#fb6057", "#fd8a2f", "#f8b020", "#3b7ee3", "#41609b", "#96b3e4", "#e3edff"


    };


    public static final int all = -1;
    public static final int hangye = 0;
    public static final int congye = 1;
    public static final int cheliang = 2;
    public static final int kaisuo = 3;
    public static final int sanzhuangqiyou = 4;
    public static final int wuzhengjuzhu = 5;

    SparseArray<Integer> sMap = new SparseArray<>();

    ArrayList<String> name = new ArrayList<>();


    @OnClick({R.id.yestoday, R.id.aWeek, R.id.aMonth})
    public void onViewClicked(View view) {

        String lastDay = "";
        switch (view.getId()) {
            case R.id.yestoday:
                lastDay = sYestoday;

                break;
            case R.id.aWeek:
                lastDay = sWeek;
                break;
            case R.id.aMonth:
                lastDay = sMonth;
                break;
        }
        for (TextView v : sDays) {
            if (v.getId() == view.getId()) {
                v.setBackground(getResources().getDrawable(R.drawable.btn_click_shape));
            } else {
                v.setBackgroundColor(Color.parseColor("#196EE9"));
            }
        }

        dataClean();
        mPresenter.getType(all, lastDay, sToday);
    }

    private void dataClean() {
        for (int i2 = 0; i2 < name.size(); i2++) {
            sMap.put(i2, 0);
        }
        mPie.clear();
        mStatisticsView.clear();
        mCount.setText("0");
    }
}