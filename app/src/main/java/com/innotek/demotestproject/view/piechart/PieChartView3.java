package com.innotek.demotestproject.view.piechart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.innotek.demotestproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼状统计图，带有标注线，都可以自行设定其多种参数选项
 * <p/>
 * Created By: Seal.Wu
 */
public class PieChartView3 extends View {

    //文字笔
    private TextPaint mTextPaint;
    //数字笔
    private TextPaint mNumberPaint;
    private float mTextWidth;
    private float mTextHeight;

    /**
     * 饼图半径
     */
    private float pieChartCircleRadius = 100;

    private float textBottom;
    /**
     * 记录文字大小
     */
    private float mTextSize = 14;

    /**
     * 饼图所占矩形区域（不包括文字）
     */
    private RectF pieChartCircleRectF = new RectF();

    /**
     * 饼状图信息列表
     */
    private List<PieceDataHolder> pieceDataHolders = new ArrayList<>();


    /**
     * 标记线长度
     */
    private float markerLineLength = 30f;

    Context context;

    public PieChartView3(Context context) {
        super(context);
        init(null, 0);
        this.context = context;
    }

    public PieChartView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
        this.context = context;
    }

    public PieChartView3(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
        this.context = context;
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PieChartView, defStyle, 0);

        pieChartCircleRadius = a.getDimension(
                R.styleable.PieChartView_circleRadius,
                pieChartCircleRadius);

        mTextSize = a.getDimension(R.styleable.PieChartView_textSize, mTextSize) / getResources().getDisplayMetrics().density;

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mNumberPaint = new TextPaint();
        mNumberPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mNumberPaint.setTextAlign(Paint.Align.LEFT);


        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();

        BarAnimation anim = new BarAnimation();
        anim.setDuration(1500);
        this.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LineAnimation lineAnimation = new LineAnimation();
                lineAnimation.setDuration(2000);
                startAnimation(lineAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    private void invalidateTextPaintAndMeasurements() {

        mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, getContext().getResources().getDisplayMetrics()));

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.descent - fontMetrics.ascent;
        textBottom = fontMetrics.bottom;

        mNumberPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, getContext().getResources().getDisplayMetrics()));

        Paint.FontMetrics fontMetricsNum = mNumberPaint.getFontMetrics();
        mTextHeight = fontMetricsNum.descent - fontMetricsNum.ascent;
        textBottom = fontMetricsNum.bottom;
    }

    /**
     * 设置饼状图的半径
     *
     * @param pieChartCircleRadius 饼状图的半径（px）
     */
    public void setPieChartCircleRadius(int pieChartCircleRadius) {

        this.pieChartCircleRadius = pieChartCircleRadius;

        invalidate();
    }

    /**
     * 设置标记线的长度
     *
     * @param markerLineLength 标记线的长度（px）
     */
    public void setMarkerLineLength(int markerLineLength) {
        this.markerLineLength = markerLineLength;
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.(sp)
     */
    public float getTextSize() {
        return mTextSize;
    }

    /**
     * Sets the view's text dimension attribute value. In the PieChartView view, this dimension
     * is the font size.
     *
     * @param textSize The text dimension attribute value to use.(sp)
     */
    public void setTextSize(float textSize) {
        mTextSize = textSize;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * 设置饼状图要显示的数据
     *
     * @param data 列表数据
     */
    public void setData(List<PieceDataHolder> data) {

        if (data != null) {
            pieceDataHolders.clear();
            pieceDataHolders.addAll(data);
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height =  MeasureSpec.getSize(heightMeasureSpec);
        int widthMode =  MeasureSpec.getMode(widthMeasureSpec);
        int heightMode =  MeasureSpec.getMode(heightMeasureSpec);
        //非精准模式，指定宽高
        if(widthMode ==  MeasureSpec.AT_MOST||heightMode == MeasureSpec.AT_MOST){
                width = DensityUtil.dip2px(context,300);
                height =  DensityUtil.dip2px(context,300);
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPieChartCircleRectF();
        drawAllSectors(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void drawAllSectors(Canvas canvas) {
        float sum = 0f;
        for (PieceDataHolder pieceDataHolder : pieceDataHolders) {
            sum += pieceDataHolder.value;
        }
        float sum2 = 0f;
        for (PieceDataHolder pieceDataHolder : pieceDataHolders) {
            float startAngel = sum2 / sum * 360;
            sum2 += pieceDataHolder.value;
            //终点角度
            float sweepAngel = pieceDataHolder.value / sum * 360;
            Log.e("whq_sweepAngel", sweepAngel + "");
            mSweepAnglePer = mRotateAnimProgress * sweepAngel;
            Log.e("whq_SWEEP", mSweepAnglePer + "");
            drawSector(canvas, pieceDataHolder.color, startAngel, mSweepAnglePer);
            // drawSector(canvas, pieceDataHolder.color, startAngel, sweepAngel);
            drawMarkerLineAndText(canvas, pieceDataHolder.color, startAngel + sweepAngel / 2, pieceDataHolder.marker, pieceDataHolder.number);
        }
        //whq  画空心圆
        drawCenterCircle(canvas);

    }

    /***
     * 确定椭圆的绘制区域坐标
     */
    private void initPieChartCircleRectF() {
        pieChartCircleRectF.left = getWidth() / 2 - pieChartCircleRadius;
        pieChartCircleRectF.top = getHeight() / 2 - pieChartCircleRadius;
        pieChartCircleRectF.right = pieChartCircleRectF.left + pieChartCircleRadius * 2;
        pieChartCircleRectF.bottom = pieChartCircleRectF.top + pieChartCircleRadius * 2;
    }


    private float mSweepAnglePer;
    private float mRotateAnimProgress;
    private float mLineAnimProgress;

    public class BarAnimation extends Animation {

        public BarAnimation() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            // Log.e("whq",interpolatedTime+"");
            mRotateAnimProgress = interpolatedTime;
           /* if (interpolatedTime < 1.0f) {
                mSweepAnglePer =  interpolatedTime *sweepAngel;//interpolatedTime *
            } else {
                mSweepAnglePer = sweepAngel;

            }*/


            postInvalidate();
        }
    }


    public class LineAnimation extends Animation {

        public LineAnimation() {

        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mLineAnimProgress = interpolatedTime;
            postInvalidate();
        }
    }

    /**
     * 绘制扇形
     *
     * @param canvas     画布
     * @param color      要绘制扇形的颜色
     * @param startAngle 起始角度
     * @param sweepAngle 结束角度
     */
    protected void drawSector(Canvas canvas, int color, float startAngle, float sweepAngle) {

        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawArc(pieChartCircleRectF, startAngle, sweepAngle, true, paint);

    }

    ///whq 画空心圆

    public void drawCenterCircle(Canvas canvas) {

        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        canvas.drawCircle(pieChartCircleRectF.centerX(), pieChartCircleRectF.centerY(), pieChartCircleRadius / 2, paint);
    }


    protected void drawMarkerLineAndText(Canvas canvas, int color, float rotateAngel, String text, String number) {
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(4);
        Path path = new Path();
        path.close();
        path.moveTo(getWidth() / 2, getHeight() / 2);
//        final float x = (float) (getWidth() / 2 + (markerLineLength + pieChartCircleRadius) * Math.cos(Math.toRadians(rotateAngel)));
//        final float y = (float) (getHeight() / 2 + (markerLineLength + pieChartCircleRadius) * Math.sin(Math.toRadians(rotateAngel)));

        float lineLength = mLineAnimProgress * markerLineLength;
        final float x = (float) (getWidth() / 2 + (lineLength + pieChartCircleRadius) * Math.cos(Math.toRadians(rotateAngel)));
        final float y = (float) (getHeight() / 2 + (lineLength + pieChartCircleRadius) * Math.sin(Math.toRadians(rotateAngel)));

        path.lineTo(x, y);
        float landLineX;
        if (270f > rotateAngel && rotateAngel > 90f) {
            //折线长度
            landLineX = x - (float) DensityUtil.dip2px(context, paint.measureText(text));
        } else {
            //折线长度
            landLineX = x + (float) DensityUtil.dip2px(context, paint.measureText(text)) + 20;
        }
        path.lineTo(landLineX, y);
        canvas.drawPath(path, paint);

        //文字的显示位置
        mTextPaint.setColor(color);
        if (270f > rotateAngel && rotateAngel > 90f) {
            float textWidth = mTextPaint.measureText(text);
            canvas.drawText(text, landLineX - textWidth, y + mTextHeight / 2 - textBottom, mTextPaint);
            // canvas.drawText(number,landLineX, y-20, mTextPaint);

        } else {
            canvas.drawText(text, landLineX, y + mTextHeight / 2 - textBottom, mTextPaint);
            // canvas.drawText(number, x+20, y-20  , mTextPaint);
        }


        //数字的显示位置
        mNumberPaint.setColor(color);
        if (270f > rotateAngel && rotateAngel > 90f) {
            float textWidth = mNumberPaint.measureText(text);

            canvas.drawText(text, landLineX - textWidth, y + mTextHeight / 2 - textBottom, mTextPaint);
            // canvas.drawText(text,landLineX, y+40, mNumberPaint);

        } else {
            canvas.drawText(text, landLineX, y + mTextHeight / 2 - textBottom, mTextPaint);
            // canvas.drawText(text, x+20, y+40  , mNumberPaint);
        }

    }
    /**
     * 绘制标注线和标记文字
     *
     * @param canvas      画布
     * @param color       标记的颜色
     * @param rotateAngel 标记线和水平相差旋转的角度
     */
    /*protected void drawMarkerLineAndText(Canvas canvas, int color, float rotateAngel, String text, String number) {
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(4);
        Path path = new Path();
        path.close();
        path.moveTo(getWidth() / 2, getHeight() / 2);
        final float x = (float) (getWidth() / 2 + (markerLineLength + pieChartCircleRadius) * Math.cos(Math.toRadians(rotateAngel)));
        final float y = (float) (getHeight() / 2 + (markerLineLength + pieChartCircleRadius) * Math.sin(Math.toRadians(rotateAngel)));
        path.lineTo(x, y);
        float landLineX;
        if (270f > rotateAngel && rotateAngel > 90f) {
            //折线长度
            landLineX = x - (float) DensityUtil.dip2px(context,paint.measureText(text));
        } else {
            //折线长度
            landLineX = x +(float) DensityUtil.dip2px(context,paint.measureText(text))+20;
        }
        path.lineTo(landLineX, y);
        canvas.drawPath(path, paint);

        //文字的显示位置
        mTextPaint.setColor(color);
        if (270f > rotateAngel && rotateAngel > 90f) {
            float textWidth = mTextPaint.measureText(text);
            canvas.drawText(text, landLineX - textWidth, y + mTextHeight / 2 - textBottom, mTextPaint);
            // canvas.drawText(number,landLineX, y-20, mTextPaint);

        } else {
             canvas.drawText(text, landLineX, y + mTextHeight / 2 - textBottom, mTextPaint);
            // canvas.drawText(number, x+20, y-20  , mTextPaint);
        }


        //数字的显示位置
        mNumberPaint.setColor(color);
        if (270f > rotateAngel && rotateAngel > 90f) {
            float textWidth = mNumberPaint.measureText(text);

             canvas.drawText(text, landLineX - textWidth, y + mTextHeight / 2 - textBottom, mTextPaint);
            // canvas.drawText(text,landLineX, y+40, mNumberPaint);

        } else {
              canvas.drawText(text, landLineX, y + mTextHeight / 2 - textBottom, mTextPaint);
            // canvas.drawText(text, x+20, y+40  , mNumberPaint);
        }

    }*/

    /**
     * 饼状图每块的信息持有者
     */
    public static final class PieceDataHolder {

        /**
         * 每块扇形的值的大小
         */
        private float value;

        /**
         * 扇形的颜色
         */
        private int color;

        /**
         * 每块的类目
         */
        private String marker;
        /**
         * 每块的数值
         */
        private String number;

        public PieceDataHolder(float value, int color, String marker, String number) {
            this.value = value;
            this.color = color;
            this.marker = marker;
            this.number = number;
        }
    }


}
