package com.zhhl.cloudpolice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhhl.cloudpolice.R;

/**
 * Created by miao on 2018/9/20.
 */
public class VerticalProgressbar extends View {
    public VerticalProgressbar(Context context) {
        this(context, null);
    }

    public VerticalProgressbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalProgressbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public VerticalProgressbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.VerticalProgressbar);
        progressColor = attr.getColor(R.styleable.VerticalProgressbar_color, getResources().getColor(R.color.colorBlueTextBar));
        progress = attr.getInteger(R.styleable.VerticalProgressbar_progress, 0);
        title = attr.getString(R.styleable.VerticalProgressbar_title);
        if (title == null) title = " ";
        max = attr.getInteger(R.styleable.VerticalProgressbar_max, 200);
        attr.recycle();
        paintBackground = initPaint();
        paintProgress = initPaint();
        paintText = initPaint();
        sMax = String.valueOf(progress);
        paintText.setTextSize(sp2px(16));

        Log.e("VerticalProgressbar: ", sMax);
//        resetWidth();

    }

    Rect r = new Rect();
    Rect mr = new Rect();


    private Paint initPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        return paint;
    }


    private void resetWidth() {
        sMax = String.valueOf(progress);
        paintText.getTextBounds(title, 0, title.length(), r);
        paintText.getTextBounds(sMax, 0, sMax.length(), mr);
    }

//    void demo(int start, int end, String text) {
//        int val = (start | end | (end - start) | (text.length() - end));
//
//        Log.e(  "demo: ",String .valueOf(val) );
//    }

    private int progressColor;
    private int progress;
    private String title;
    private int max;
    private String sMax;

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        resetWidth();
        invalidate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        resetWidth();
        invalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    Paint paintText;
    Paint paintProgress;
    Paint paintBackground;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widMode = MeasureSpec.getMode(widthMeasureSpec);
        int widSize = MeasureSpec.getSize(widthMeasureSpec);

        int heiMode = MeasureSpec.getMode(heightMeasureSpec);
        int heiSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widMode == MeasureSpec.AT_MOST && heiMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(WRAP_WIDTH, WRAP_HEIGHT);
        } else if (widMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(WRAP_WIDTH, heiSize);
        } else if (heiMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widSize, WRAP_HEIGHT);
        }
    }

    private float dp2px(int dp) {
        return getContext().getResources().getDisplayMetrics().density * dp + 0.5f;
    }

    private float sp2px(int sp) {
        return getContext().getResources().getDisplayMetrics().scaledDensity * sp + 0.5f;
    }

    private int WRAP_WIDTH = (int) dp2px(100);
    private int WRAP_HEIGHT = (int) dp2px(200);


    Rect maxRect;
    Rect progressRect;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#FFFFFF"));

        int height = getHeight();
        int width = getWidth();


        int rh = r.bottom - r.top;

        int maxH = (int) (height - rh - dp2px(20));
        int progressHeight = (int) ((progress * 1.0 / max) * maxH);

        paintBackground.setColor(Color.parseColor("#CFD8E8"));

        paintProgress.setColor(progressColor);
        int textWidth = mr.right - mr.left;
        int verBarWidth = (int) dp2px(20);

        int block = (int) ((width - (verBarWidth + textWidth + dp2px(8))) / 2);


        canvas.drawRect(block, (int) dp2px(4), verBarWidth + block, maxH, paintBackground);
        canvas.drawRect(block, maxH - progressHeight, verBarWidth + block, maxH, paintProgress);
        canvas.drawText(sMax, block + verBarWidth + dp2px(4), maxH - progressHeight, paintText);
//        canvas.drawText(title, dp2px(18), height - (dp2px(4)), paintText);

        int blockWidth = (int) dp2px(10);
        int titleWidth = r.right - r.left;
        int block2 = (int) ((width - (blockWidth + titleWidth + dp2px(10))) / 2);
        canvas.drawRect(dp2px(20), height - dp2px(14), dp2px(30), height - dp2px(4), paintProgress);

        canvas.drawText(title, dp2px(40), height - (dp2px(4)), paintText);

    }
}
