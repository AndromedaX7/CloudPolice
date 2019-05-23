package com.zhhl.cloudpolice;

import android.graphics.Color;
import android.graphics.Matrix;
import android.support.annotation.ColorInt;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class MPChartUtils {
    /**
     * 不显示无数据的提示
     *
     * @param mChart
     */
    public static void NotShowNoDataText(Chart mChart) {
        mChart.clear();
        mChart.notifyDataSetChanged();
        mChart.setNoDataText("你还没有记录数据");
        mChart.setNoDataTextColor(Color.WHITE);
        mChart.invalidate();
    }

    /**
     * 配置Chart 基础设置
     *
     * @param mChart       图表
     * @param mLabels      x 轴标签
     * @param yMax         y 轴最大值
     * @param yMin         y 轴最小值
     * @param isShowLegend 是否显示图例
     */
    public static void configChart(CombinedChart mChart, List<String> mLabels, float yMax, float yMin, boolean isShowLegend) {

        mChart.setDrawGridBackground(false);
        mChart.setDrawBorders(false);
        mChart.setScaleEnabled(false);
        mChart.setDragEnabled(true);
        mChart.setNoDataText("");
        // 不显示描述数据
        mChart.getDescription().setEnabled(false);
        mChart.getAxisRight().setEnabled(false);

        Legend legend = mChart.getLegend();
        // 是否显示图例
        if (isShowLegend) {
            legend.setEnabled(true);
            legend.setTextColor(Color.WHITE);
            legend.setForm(Legend.LegendForm.CIRCLE);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            legend.setYEntrySpace(20f);
            //图例的大小
            legend.setFormSize(7f);
            // 图例描述文字大小
            legend.setTextSize(10);
            legend.setXEntrySpace(20f);

        } else {
            legend.setEnabled(false);
        }


        XAxis xAxis = mChart.getXAxis();

        // 是否显示x轴线
        xAxis.setDrawAxisLine(true);
        // 设置x轴线的颜色
        xAxis.setAxisLineColor(Color.parseColor("#4cffffff"));
        // 是否绘制x方向网格线
        xAxis.setDrawGridLines(false);
        //x方向网格线的颜色
        xAxis.setGridColor(Color.parseColor("#30FFFFFF"));

        // 设置x轴数据的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置x轴文字的大小
        xAxis.setTextSize(12);

        // 设置x轴数据偏移量
        xAxis.setYOffset(5);
        final List<String> labels = mLabels;
        // 显示x轴标签
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                if (index < 0 || index >= labels.size()) {
                    return "";
                }
                return labels.get(index);
                // return labels.get(Math.min(Math.max((int) value, 0), labels.size() - 1));
            }

        };
        // 引用标签
        xAxis.setValueFormatter(formatter);
        // 设置x轴文字颜色
        xAxis.setTextColor(mChart.getResources().getColor(R.color.colorPrimaryDark));
        // 设置x轴每最小刻度 interval
        xAxis.setGranularity(1f);

        YAxis yAxis = mChart.getAxisLeft();
        //设置x轴的最大值
        yAxis.setAxisMaximum(yMax);
        // 设置y轴的最大值
        yAxis.setAxisMinimum(yMin);
        // 不显示y轴
        yAxis.setDrawAxisLine(false);
        // 设置y轴数据的位置
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        // 不从y轴发出横向直线
        yAxis.setDrawGridLines(false);
        // 是否显示y轴坐标线
        yAxis.setDrawZeroLine(true);
        // 设置y轴的文字颜色
        yAxis.setTextColor(mChart.getResources().getColor(R.color.colorPrimaryDark));
        // 设置y轴文字的大小
        yAxis.setTextSize(12);
        // 设置y轴数据偏移量
        //yAxis.setXOffset(30);
        // yAxis.setYOffset(-3);
        yAxis.setXOffset(15);
        // yAxis.setGranularity(yGranularity);
        yAxis.setLabelCount(5, false);
        //yAxis.setGranularity(5);//interval

        Matrix matrix = new Matrix();
        // 根据数据量来确定 x轴缩放大倍
        if (mLabels.size() <= 10) {
            matrix.postScale(1.0f, 1.0f);
        } else if (mLabels.size() <= 15) {
            matrix.postScale(1.5f, 1.0f);
        } else if (mLabels.size() <= 20) {
            matrix.postScale(2.0f, 1.0f);
        } else {
            matrix.postScale(3.0f, 1.0f);
        }

        // 在图表动画显示之前进行缩放
        mChart.getViewPortHandler().refresh(matrix, mChart, false);
        // x轴执行动画
        mChart.animateX(500);

    }

    /**
     * 初始化数据
     *
     * @param chart
     * @param lineDatas
     */
    public static void initData(CombinedChart chart, LineData... lineDatas) {
        CombinedData combinedData = new CombinedData();
        for (LineData lineData : lineDatas) {
            combinedData.setData(lineData);
        }
        chart.setData(combinedData);
        chart.invalidate();
    }

    /**
     * 获取LineDataSet
     *
     * @param entries
     * @param label
     * @param textColor
     * @param lineColor
     * @return
     */
    public static LineDataSet getLineData(List<Entry> entries, String label, @ColorInt int textColor, @ColorInt int lineColor, boolean isFill) {
        LineDataSet dataSet = new LineDataSet(entries, label);
        // 设置曲线的颜色
        dataSet.setColor(lineColor);
        //数值文字颜色
        dataSet.setValueTextColor(textColor);
        // 模式为贝塞尔曲线
        dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        // 是否绘制数据值
        dataSet.setDrawValues(false);
        // 是否绘制圆点
        dataSet.setDrawCircles(true);
        dataSet.setDrawCircleHole(false);
        // 这里有一个坑，当我们想隐藏掉高亮线的时候，MarkerView 跟着不见了
        // 因此只有将它设置成透明色
        dataSet.setHighlightEnabled(true);// 隐藏点击时候的高亮线
        //设置高亮线为透明色
        dataSet.setHighLightColor(Color.TRANSPARENT);

        if (isFill) {
            //是否设置填充曲线到x轴之间的区域
            dataSet.setDrawFilled(true);
            // 填充颜色
            dataSet.setFillColor(lineColor);
        }
        //设置圆点的颜色
        dataSet.setCircleColor(lineColor);
        // 设置圆点半径
        dataSet.setCircleRadius(3.5f);
        // 设置线的宽度
        dataSet.setLineWidth(1f);
        return dataSet;
    }

    /**
     * 获取barDataSet
     *
     * @param entries
     * @param label
     * @param textColor
     * @param lineColor
     * @return
     */
    public static BarDataSet getBarDataSet(List<BarEntry> entries, String label, @ColorInt int textColor, @ColorInt int lineColor) {
        BarDataSet dataSet = new BarDataSet(entries, label);
        dataSet.setBarBorderWidth(5);
        dataSet.setBarShadowColor(lineColor);
        dataSet.setValueTextColor(textColor);
        dataSet.setDrawValues(true);
        return dataSet;
    }

    /**
     * 配置柱状图基础设置
     *
     * @param barChart
     * @param xLabels
     */
    public static void configBarChart(BarChart barChart, final List<String> xLabels) {
        barChart.getDescription().setEnabled(false);//设置描述
        barChart.setPinchZoom(true);//设置按比例放缩柱状图
        barChart.setScaleEnabled(false);
        barChart.setDragEnabled(true);
        barChart.setNoDataText("暂无统计数据"); // 没有数据时的提示文案
        barChart.setNoDataTextColor(barChart.getContext().getResources().getColor(R.color.colorBlueTextBar));
        //x坐标轴设置
        // IAxisValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);//设置自定义的x轴值格式化器
        XAxis xAxis = barChart.getXAxis();//获取x轴
        xAxis.setLabelRotationAngle(45);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        xAxis.setDrawGridLines(true);//不绘制格网线
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        // 显示x轴标签
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (xLabels.size() == 0) return "";
                return xLabels.get(Math.min(Math.max((int) value, 0), xLabels.size() - 1));
            }

        };
        xAxis.setValueFormatter(formatter);
        xAxis.setTextSize(10);//设置标签字体大小
        xAxis.setTextColor(barChart.getResources().getColor(R.color.colorPrimaryDark));
        xAxis.setAxisLineColor(Color.parseColor("#4cffffff"));
        xAxis.setLabelCount(xLabels.size());//设置标签显示的个数

        //y轴设置
        YAxis leftAxis = barChart.getAxisLeft();//获取左侧y轴
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//设置y轴标签显示在外侧
        leftAxis.setAxisMinimum(0f);//设置Y轴最小值
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawLabels(true);//禁止绘制y轴标签
        leftAxis.setDrawAxisLine(true);//禁止绘制y轴
        leftAxis.setAxisLineColor(Color.parseColor("#4cffffff"));
        leftAxis.setTextColor(barChart.getResources().getColor(R.color.colorPrimaryDark));
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                return ((int) (value * 100)) + "%";
                return String.valueOf(((int) value));
            }
        });
        barChart.getAxisLeft().setEnabled(true);
        barChart.getAxisRight().setEnabled(false);//禁用右侧y轴
        barChart.getLegend().setEnabled(false);
        //图例设置
       /* Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//图例水平居中
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例在图表上方
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//图例的方向为水平
        legend.setDrawInside(false);//绘制在chart的外侧
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);//图例中的文字方向

        legend.setForm(Legend.LegendForm.SQUARE);//图例窗体的形状
        legend.setFormSize(0f);//图例窗体的大小
        legend.setTextSize(16f);//图例文字的大小*/
        //legend.setYOffset(-2f);


        Matrix matrix = new Matrix();
        // 根据数据量来确定 x轴缩放大倍
        if (xLabels.size() <= 10) {
            matrix.postScale(1.0f, 1.0f);
        } else if (xLabels.size() <= 15) {
            matrix.postScale(1.5f, 1.0f);
        } else if (xLabels.size() <= 20) {
            matrix.postScale(2.0f, 1.0f);
        } else {
            matrix.postScale(3.0f, 1.0f);
        }
        barChart.getViewPortHandler().refresh(matrix, barChart, false);
        barChart.setExtraBottomOffset(10);//距视图窗口底部的偏移，类似与paddingbottom
        barChart.setExtraTopOffset(30);//距视图窗口顶部的偏移，类似与paddingtop
        barChart.setFitBars(true);//使两侧的柱图完全显示
        barChart.animateX(1500);//数据显示动画，从左往右依次显示
    }

    /**
     * 初始化柱状图图表数据
     *
     * @param chart
     * @param entries
     * @param title
     * @param barColor
     */
    public static void initBarChart(BarChart chart, List<BarEntry> entries, String title, @ColorInt int barColor) {
        BarDataSet set1 = new BarDataSet(entries, title);
        set1.setValueTextColor(Color.WHITE);
        set1.setColor(barColor);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        // 设置bar的宽度，但是点很多少的时候好像没作用，会拉得很宽
        data.setBarWidth(0.1f);
        // 设置value值 颜色
        data.setValueTextColor(chart.getContext().getResources().getColor(R.color.colorBlueTextBar));
        //设置y轴显示的标签
        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return ((value)) + "";
            }
        });
        chart.setData(data);
        chart.invalidate();
    }

    public static void initBarChart(BarChart chart, List<IBarDataSet> entries) {

        BarData data = new BarData(entries);
        data.setValueTextSize(10f);
        // 设置bar的宽度，但是点很多少的时候好像没作用，会拉得很宽
        data.setBarWidth(0.3f);
        // 设置value值 颜色
//        data.setValueTextColor(chart.getContext().getResources().getColor(R.color.colorBlueTextBar));
        //设置y轴显示的标签
        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return ((value)) + "";
            }
        });
        chart.setData(data);
        chart.invalidate();
    }

    public static void initPieChart(PieChart chart, PieDataSet entries) {

//        BarData data = new BarData(entries);

        PieData data = new PieData(entries);

//        data.setValueTextSize(10f);
        // 设置bar的宽度，但是点很多少的时候好像没作用，会拉得很宽
//        data.setBarWidth(0.3f);
        // 设置value值 颜色
//        data.setValueTextColor(chart.getContext().getResources().getColor(R.color.colorBlueTextBar));
        //设置y轴显示的标签
//        data.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return ((value)) + "";
//            }
//        });
        chart.setData(data);
        chart.invalidate();
    }


    public static ArrayList<IBarDataSet> marginData(ArrayList<IBarDataSet> dataSets, List<BarEntry> entries, String title, int color) {
        BarDataSet set = new BarDataSet(entries, title);
        set.setValueTextColor(color);
        set.setColor(color);
        dataSets.add(set);
        return dataSets;
    }

    public static ArrayList<IPieDataSet> marginPieData(ArrayList<IPieDataSet> dataSets, List<PieEntry> entries, String title, int color) {
        PieDataSet set = new PieDataSet(entries, title);
        set.setValueTextColor(color);
        set.setColor(color);
        dataSets.add(set);
        return dataSets;
    }


    public static void configPieChart(PieChart pieChart, final List<String> xLabels) {
        pieChart.getDescription().setEnabled(false);//设置描述
        pieChart.setUsePercentValues(true);
        pieChart.setBackgroundColor(Color.WHITE);
        pieChart.setExtraOffsets(5, 10, 60, 10);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setRotationAngle(0);                   //设置pieChart图表起始角度
        pieChart.setRotationEnabled(true);              //设置pieChart图表是否可以手动旋转
        pieChart.setHighlightPerTapEnabled(true);       //设置piecahrt图表点击Item高亮是否可用
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
//        barChart.setPinchZoom(true);//设置按比例放缩柱状图
//        barChart.setScaleEnabled(false);
//        barChart.setDragEnabled(true);
        pieChart.setNoDataText("暂无统计数据"); // 没有数据时的提示文案
        pieChart.setNoDataTextColor(pieChart.getContext().getResources().getColor(R.color.colorBlueTextBar));
        //x坐标轴设置
        // IAxisValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);//设置自定义的x轴值格式化器


        Matrix matrix = new Matrix();
        // 根据数据量来确定 x轴缩放大倍
        if (xLabels.size() <= 10) {
            matrix.postScale(1.0f, 1.0f);
        } else if (xLabels.size() <= 15) {
            matrix.postScale(1.5f, 1.0f);
        } else if (xLabels.size() <= 20) {
            matrix.postScale(2.0f, 1.0f);
        } else {
            matrix.postScale(3.0f, 1.0f);
        }
        pieChart.getViewPortHandler().refresh(matrix, pieChart, false);
        pieChart.setExtraBottomOffset(10);//距视图窗口底部的偏移，类似与paddingbottom
        pieChart.setExtraTopOffset(30);//距视图窗口顶部的偏移，类似与paddingtop
//        barChart.setFitBars(true);//使两侧的柱图完全显示
        pieChart.animateX(1500);//数据显示动画，从左往右依次显示
    }


    public static void configPie(PieChart mChart, OnChartValueSelectedListener onChartValueSelectedListener) {
        // 设置 pieChart 图表基本属性

        mChart.setNoDataText("暂无统计数据"); // 没有数据时的提示文案
        mChart.setNoDataTextColor(mChart.getContext().getResources().getColor(R.color.colorBlueTextBar));
        mChart.setUsePercentValues(false);            //使用百分比显示
        mChart.getDescription().setEnabled(false);    //设置pieChart图表的描述
        mChart.setBackgroundColor(Color.WHITE);      //设置pieChart图表背景色
        mChart.setExtraOffsets(5, 10, 60, 10);        //设置pieChart图表上下左右的偏移，类似于外边距
        mChart.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]
        mChart.setRotationAngle(0);                   //设置pieChart图表起始角度
        mChart.setRotationEnabled(true);              //设置pieChart图表是否可以手动旋转
        mChart.setHighlightPerTapEnabled(true);       //设置piecahrt图表点击Item高亮是否可用
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);// 设置pieChart图表展示动画效果

// 设置 pieChart 图表Item文本属性
        mChart.setDrawEntryLabels(true);              //设置pieChart是否只显示饼图上百分比不显示文字（true：下面属性才有效果）
        mChart.setEntryLabelColor(Color.WHITE);       //设置pieChart图表文本字体颜色
//        mChart.setEntryLabelTypeface(mTfRegular);     //设置pieChart图表文本字体样式
        mChart.setEntryLabelTextSize(10f);            //设置pieChart图表文本字体大小

// 设置 pieChart 内部圆环属性
        mChart.setDrawHoleEnabled(true);              //是否显示PieChart内部圆环(true:下面属性才有意义)
        mChart.setHoleRadius(28f);                    //设置PieChart内部圆的半径(这里设置28.0f)
        mChart.setTransparentCircleRadius(31f);       //设置PieChart内部透明圆的半径(这里设置31.0f)
        mChart.setTransparentCircleColor(Color.BLACK);//设置PieChart内部透明圆与内部圆间距(31f-28f)填充颜色
        mChart.setTransparentCircleAlpha(50);         //设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
        mChart.setHoleColor(Color.WHITE);             //设置PieChart内部圆的颜色
        mChart.setDrawCenterText(true);               //是否绘制PieChart内部中心文本（true：下面属性才有意义）
//        mChart.setCenterTextTypeface(mTfLight);       //设置PieChart内部圆文字的字体样式
        mChart.setCenterText("");                 //设置PieChart内部圆文字的内容
        mChart.setCenterTextSize(10f);                //设置PieChart内部圆文字的大小
        mChart.setCenterTextColor(Color.RED);         //设置PieChart内部圆文字的颜色

// pieChart添加数据
//        setData();

// 获取pieCahrt图列
        Legend l = mChart.getLegend();
        l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义）
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setForm(Legend.LegendForm.DEFAULT); //设置图例的形状
        l.setFormSize(10);                      //设置图例的大小
        l.setFormToTextSpace(10f);              //设置每个图例实体中标签和形状之间的间距
        l.setDrawInside(false);
        l.setWordWrapEnabled(true);              //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
        l.setXEntrySpace(10f);                  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(8f);                  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(0f);                      //设置比例块Y轴偏移量
        l.setTextSize(10f);                      //设置图例标签文本的大小
        l.setTextColor(Color.parseColor("#ff9933"));//设置图例标签文本的颜色

//pieChart 选择监听
        mChart.setOnChartValueSelectedListener(onChartValueSelectedListener);

//设置MARKERVIEW
//        CustomMarkerView mv = new CustomMarkerView(this, new PercentFormatter());
//        mv.setChartView(mChart);
//        mChart.setMarker(mv);


    }

    public static void setData2(PieChart mChart, PieDataSet s) {
        mChart.setBackgroundColor(Color.WHITE);
        //最终数据 PieData
        PieData pieData = new PieData(s);
        pieData.setDrawValues(true);            //设置是否显示数据实体(百分比，true:以下属性才有意义)
        pieData.setValueTextColor(Color.WHITE);  //设置所有DataSet内数据实体（百分比）的文本颜色
        pieData.setValueTextSize(12f);          //设置所有DataSet内数据实体（百分比）的文本字体大小
//        pieData.setValueTypeface(mTfLight);     //设置所有DataSet内数据实体（百分比）的文本字体样式
        pieData.setValueFormatter(new PercentFormatter());//设置所有DataSet内数据实体（百分比）的文本字体格式
        mChart.setData(pieData);
        mChart.highlightValues(null);
        mChart.invalidate();                    //将图表重绘以显示设置的属性和数据
    }

    public static void setData(PieChart mChart ) {
        ArrayList<PieEntry> pieEntryList = new ArrayList<PieEntry>();
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#f17548"));
        colors.add(Color.parseColor("#FF9933"));
        //饼图实体 PieEntry
        PieEntry CashBalance = new PieEntry(70, "");
        PieEntry ConsumptionBalance = new PieEntry(30, "");
        pieEntryList.add(CashBalance);
        pieEntryList.add(ConsumptionBalance);
        //饼状图数据集 PieDataSet
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "");
        pieDataSet.setSliceSpace(3f);           //设置饼状Item之间的间隙
        pieDataSet.setSelectionShift(10f);      //设置饼状Item被选中时变化的距离
        pieDataSet.setColors(colors);           //为DataSet中的数据匹配上颜色集(饼图Item颜色)
        //最终数据 PieData
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);            //设置是否显示数据实体(百分比，true:以下属性才有意义)
        pieData.setValueTextColor(Color.WHITE);  //设置所有DataSet内数据实体（百分比）的文本颜色
        pieData.setValueTextSize(12f);          //设置所有DataSet内数据实体（百分比）的文本字体大小
//        pieData.setValueTypeface(mTfLight);     //设置所有DataSet内数据实体（百分比）的文本字体样式
        pieData.setValueFormatter(new PercentFormatter());//设置所有DataSet内数据实体（百分比）的文本字体格式
        mChart.setData(pieData);
        mChart.highlightValues(null);
        mChart.invalidate();                    //将图表重绘以显示设置的属性和数据
    }
}

//        MPChartUtils.configChart(mWeightChart, xLabels, maxWeight, minWeight, true);
//         2,获取数据Data，这里有2条曲线
//        LineDataSet tartgetDataSet = MPChartUtils.getLineData(targetEntries, "目标体重", Color.WHITE, Color.WHITE, false);
//        LineDataSet lineDataSet = MPChartUtils.getLineData(weightEntries, "当前体重", Color.WHITE, getResources().getColor(R.color.chart_dot_color), false);
//          3,初始化数据并绘制
//       MPChartUtils.initData(mWeightChart, new LineData(lineDataSet, tartgetDataSet));
//