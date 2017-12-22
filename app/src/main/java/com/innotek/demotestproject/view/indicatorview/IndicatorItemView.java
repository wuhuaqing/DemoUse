package com.innotek.demotestproject.view.indicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.innotek.demotestproject.R;

/**
 * Created by admin on 2017/12/22.
 */

public class IndicatorItemView extends View {
    private Context context;
    private int defaultRadius = 10;
    private int defaultLinesize = 5;
    private int defaultLinelength = 20;
    private boolean defaultLineStatus = true;

    private Drawable indicatorDrawable;
    private float indicatorRadius;
    private int indicatorColor;
    private float lineSize;
    private int lineColor;
    private boolean linestatus;
    private float lineLenth;
    private Paint circlePaint;
    private Paint linePaint;

    public IndicatorItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attr) {
        initAttrs(context, attr);
        initPaint();
    }

    /***
     * 初始化操作
     * @param context
     * @param attrs
     */
    public void initAttrs(Context context, @Nullable AttributeSet attrs) {
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorItemview);
        int indicatorDrawableId = typedArray.getResourceId(R.styleable.IndicatorItemview_indicatorimg, R.drawable.shape_text_circlebg);
        indicatorDrawable = context.getResources().getDrawable(indicatorDrawableId);
        indicatorRadius = typedArray.getDimension(R.styleable.IndicatorItemview_indicatorradius, defaultRadius);
        indicatorColor = typedArray.getColor(R.styleable.IndicatorItemview_indicatorcolor, context.getResources().getColor(R.color.color_3A819B));
        lineSize = typedArray.getDimension(R.styleable.IndicatorItemview_linesize, defaultLinesize);
        lineColor = typedArray.getColor(R.styleable.IndicatorItemview_linecolor, context.getResources().getColor(R.color.color_3A819B));
        linestatus = typedArray.getBoolean(R.styleable.IndicatorItemview_linestatus, defaultLineStatus);
        lineLenth = typedArray.getDimension(R.styleable.IndicatorItemview_lineslength, defaultLinelength);
        typedArray.recycle();
    }

    public void initPaint() {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
    }

    //view宽、高
    private int indicatorWidth;
    private int indicatorHeight;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量得到的高度
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            indicatorWidth = widthMeasure;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //控件宽度
            indicatorWidth = ((int) indicatorRadius * 2) + getPaddingLeft() + getPaddingRight() + (int) lineLenth;
            // indicatorWidth = Math.min(desiredWidth, widthMeasure);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            indicatorHeight = ((int) indicatorRadius * 2) + getPaddingTop() + getPaddingBottom();
          //  indicatorHeight = heightMeasure;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            indicatorHeight = ((int) indicatorRadius * 2) + getPaddingTop() + getPaddingBottom();
            //indicatorHeight = Math.min(desiredHeight, heightMeasure);
        }
        setMeasuredDimension(indicatorWidth, indicatorHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawIndicator(canvas);
    }


    public void drawIndicator(Canvas canvas) {

        int left = getLeft() + getPaddingLeft();//getX() +
        int top = getTop() + getPaddingTop();//getY() +

        circlePaint.setColor(indicatorColor);
        circlePaint.setStrokeWidth(1);
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(left + indicatorRadius, top + indicatorRadius, indicatorRadius, circlePaint);
        // canvas.drawCircle(27, 300, indicatorRadius, circlePaint);

        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineSize);
        float startX = left + 2 * indicatorRadius;
        float startY = top + indicatorRadius;
        canvas.drawLine(startX, startY, startX + lineLenth, startY, linePaint);
    }

    public void setLineslength(int lineLenth){
        this.lineLenth = lineLenth-getPaddingLeft()+indicatorRadius*2;
        //this.lineLenth = lineLenth;
        postInvalidate();
    }
}
