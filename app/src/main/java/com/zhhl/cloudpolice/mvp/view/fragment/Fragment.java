//package com.zhhl.wechatforenterprise.mvp.view.fragment;
//
//import android.graphics.Color;
//import android.util.Log;
//import android.util.SparseArray;
//import android.widget.TextView;
//
//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.charts.PieChart;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.zhhl.wechatforenterprise.DateUtil;
//import com.zhhl.wechatforenterprise.MPChartUtils;
//import com.zhhl.wechatforenterprise.R;
//import com.zhhl.wechatforenterprise.common.fragment.BaseMvpFragment;
//import com.zhhl.wechatforenterprise.di.component.AppComponent;
//import com.zhhl.wechatforenterprise.di.component.DaggerStatisticsComponent;
//import com.zhhl.wechatforenterprise.di.module.StatisticsModule;
//import com.zhhl.wechatforenterprise.mvp.Contract.StatisticsContract;
//import com.zhhl.wechatforenterprise.mvp.StatisticsAdapter;
//import com.zhhl.wechatforenterprise.mvp.model.entity.Statistics;
//import com.zhhl.wechatforenterprise.mvp.presenter.StatisticsPresenter;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import butterknife.BindView;
//
///**
// * Created by miao on 2018/9/19.
// */
//
//public class StatisticsFragment extends BaseMvpFragment<StatisticsPresenter> implements StatisticsContract.View {
//
//    //    @BindView(R.id.mSelector)
//    @BindView(R.id.mStatisticsView)
//    BarChart mStatisticsView;
//    //    LinearLayout mSelector;
//    @BindView(R.id.yestoday)
//    TextView yestoday;
//    @BindView(R.id.aWeek)
//    TextView aWeek;
//    @BindView(R.id.aMonth)
//    TextView aMonth;
//    @BindView(R.id.mCount)
//    TextView mCount;
//    @BindView(R.id.mPie)
//    PieChart mPie;
//
//
//    private StatisticsAdapter adapter;
//
//    private String startDate = "";
//    private String endDate = "";
//
//    public static StatisticsFragment newInstance() {
//        return new StatisticsFragment();
//    }
//
//    @Override
//    protected int getLayoutRes() {
//        return R.layout.fragment_statistics_2;
//    }
//
//    @Override
//    protected void setupFragmentComponent(AppComponent appComponent) {
//        DaggerStatisticsComponent
//                .builder()
//                .appComponent(appComponent)
//                .statisticsModule(new StatisticsModule(this))//请将MainActivity_mainModule()第一个首字母改为小写
//                .build()
//                .inject(this);
//    }
//
//
//    private String sToday;
//    private String sYestoday;
//
//    private String sWeek;
//    private String sMonth;
//
//    @Override
//    protected void onCreated() {
//        super.onCreated();
//        name.add("行业人员");
//        name.add("从业人员");
//        name.add("车辆");
//        name.add("开锁人员");
//        name.add("散装汽油人员");
//        name.add("无证入住人员");
////        mStatisticsView.setData(new BarData());
////        mStatisticsView.setNoDataTextColor(getContext().getResources().getColor(R.color.colorBlueTextBar));
////        mStatisticsView.setNoDataText("暂无统计数据");
////        mStatisticsView.notifyDataSetChanged();
////        mStatisticsView.invalidate();
//        sToday = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis());
//        sYestoday = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis() - 24 * 3600 * 1000);
//        sWeek = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis() - 24 * 60 * 60 * 1000 * 7);
//        GregorianCalendar g = new GregorianCalendar();
//        int month = g.get(Calendar.MONTH);
//        if (month == 0) {
//            g.set(Calendar.MONTH, 11);
//        } else {
//            g.set(Calendar.MONTH, month - 1);
//        }
//        int actualMaximum = g.getActualMaximum(Calendar.DATE);
//        g.set(Calendar.MONTH, month);
//
//        sMonth = DateUtil.format("yyyy-MM-dd", (System.currentTimeMillis() - (24L * 3600 * 1000 * actualMaximum)));
//
//
//        Log.e(TAG, "onCreated: " + sYestoday + "::" + sToday);
//        Log.e(TAG, "onCreated: " + sWeek + "::" + sToday);
//        Log.e(TAG, "onCreated: " + sMonth + "::" + sToday);
//        mPresenter.getType(StatisticsFragment.all, sYestoday, sToday);
//
//    }
//
//
//    @Override
//    public void data(ArrayList<Statistics> statistics) {
//        if (statistics != null) {
//            ArrayList<String> names = new ArrayList<>();
//            ArrayList<Integer> count = new ArrayList<>();
//
//
//            for (int i = 0; i < statistics.size(); i++) {
//                names.add(statistics.get(i).getName());
//                count.add(Integer.parseInt(statistics.get(i).getCount()));
//            }
//
//
//            ArrayList<BarEntry> entries = new ArrayList<>();
//
//
//            for (int i = 0; i < count.size(); i++) {
//                int co = count.get(i);
//                entries.add(new BarEntry(i, co));
//            }
//
//            MPChartUtils.configBarChart(mStatisticsView, names);
//            if (statistics.size() > 0) {
//                BarDataSet barDataSet = MPChartUtils.getBarDataSet(entries, "", Color.parseColor("#FF0000"), Color.parseColor("#00FF00"));
//                MPChartUtils.initBarChart(mStatisticsView, barDataSet.getValues(), "数量", getContext().getResources().getColor(R.color.colorBlueTextBar));
//                Log.w(TAG, "data: " + statistics.size());
//            } else {
////                mStatisticsView.setNoDataText("暂无统计数据");
////                mStatisticsView.setData(new BarData());
//////                mStatisticsView.notifyDataSetChanged();
////                mStatisticsView.invalidate();
//                mStatisticsView.clear();
//            }
////            mStatisticsView.postDelayed(() -> {
//        }
//    }
//
//    @Override
//    public void data(ArrayList<Statistics> statistics, int idx) {
//        sMap.put(idx, statistics);
//
//
//    }
//
//    public static final int all = -1;
//    public static final int hangye = 0;
//    public static final int congye = 1;
//    public static final int cheliang = 2;
//    public static final int kaisuo = 3;
//    public static final int sanzhuangqiyou = 4;
//    public static final int wuzhengjuzhu = 5;
//
//    SparseArray<ArrayList<Statistics>> sMap = new SparseArray<>();
//
//    ArrayList<String> name = new ArrayList<>();
//
////    class ViewHolder {
////
////        private int currentId;
////
////        @BindView(R.id.rb0)
////        RadioButton rb0;
////        @BindView(R.id.rb1)
////        RadioButton rb1;
////        @BindView(R.id.rb2)
////        RadioButton rb2;
////        @BindView(R.id.rb3)
////        RadioButton rb3;
////        @BindView(R.id.rb4)
////        RadioButton rb4;
////        @BindView(R.id.rb5)
////        RadioButton rb5;
////        @BindView(R.id.mDialogList)
////        RadioGroup mDialogList;
////
////        @BindView(R.id.mTimeStarter)
////        TextView mTimeStarter;
////        @BindView(R.id.mTimeEnder)
////        TextView mTimeEnder;
////
////
////        int selected_id;
////        ArrayList<RadioButton> rbs = new ArrayList<>();
////
////        ViewHolder(View view) {
////            ButterKnife.bind(this, view);
////
////            rbs.add(rb0);
////            rbs.add(rb1);
////            rbs.add(rb2);
////            rbs.add(rb3);
////            rbs.add(rb4);
////            rbs.add(rb5);
////
////
////            for (int i = 0; i < rbs.size(); i++) {
////                RadioButton rb = rbs.get(i);
////                rb.setTextColor(getContext().getResources().getColor(rb.isChecked() ? android.R.color.white : R.color.colorBlueTextBar));
////            }
////            mDialogList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
////                @Override
////                public void onCheckedChanged(RadioGroup group, int checkedId) {
////                    for (int i = 0; i < rbs.size(); i++) {
////                        RadioButton rb = rbs.get(i);
////                        rb.setTextColor(getContext().getResources().getColor(rb.getUserId() == checkedId ? android.R.color.white : R.color.colorBlueTextBar));
////                    }
////
////
////                    switch (checkedId) {
////                        case R.id.rb0:
////                            selected_id = 0;
////                            break;
////                        case R.id.rb1:
////                            selected_id = 1;
////                            break;
////                        case R.id.rb2:
////                            selected_id = 2;
////                            break;
////                        case R.id.rb3:
////                            selected_id = 3;
////                            break;
////                        case R.id.rb4:
////                            selected_id = 4;
////                            break;
////                        case R.id.rb5:
////                            selected_id = 5;
////                            break;
////                    }
////
////                }
////            });
////        }
////
////        @OnClick({R.id.mTimeStarter, R.id.mTimeEnder})
////        public void onViewClicked(View view) {
////            currentId = view.getUserId();
////            String format = DateFormat.format("yyyy-MM-dd", System.currentTimeMillis()).toString();
////
////            String[] date = format.split("-");
////            DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), (view1, year, month, dayOfMonth) -> {
////
////                switch (currentId) {
////                    case R.id.mTimeStarter:
////                        startDate = year + "-" + (month + 1) + "-" + dayOfMonth;
////                        mTimeStarter.setText(startDate);
////                        break;
////                    case R.id.mTimeEnder:
////                        endDate = year + "-" + (month + 1) + "-" + dayOfMonth;
////                        mTimeEnder.setText(endDate);
////                        break;
////                }
////
////                currentId = -1;
////
////            }, Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]));
////            datePickerDialog.show();
////        }
////    }
//}