package com.innotek.demotestproject.view.indicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.piechart.DensityUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by whq on 2017/12/22.
 * 水平指示器
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
    private Paint textPaint;
    private String indicatrorText;
    private int indicatortextcolor;
    private boolean hasAboveText;//是否有上部文字
    private boolean hasButtomText;//是否有下部文字
    private Paint currentIndexpaint;
    private Paint abovetextPaint;
    private Paint buttotextPaint;
    private Paint dashLinePaint;


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
        indicatrorText = typedArray.getString(R.styleable.IndicatorItemview_indicatortext);
        indicatortextcolor = typedArray.getColor(R.styleable.IndicatorItemview_indicatortextcolor, context.getResources().getColor(R.color.white));
        hasAboveText = typedArray.getBoolean(R.styleable.IndicatorItemview_indicatorhasabovetext, true);
        hasButtomText = typedArray.getBoolean(R.styleable.IndicatorItemview_indicatorhasbuttomtext, true);
        typedArray.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        //指示器paint
        circlePaint = new Paint();
        circlePaint.setColor(indicatorColor);
        circlePaint.setAntiAlias(true);
        //指示器线Paint
        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setAntiAlias(true);
        //指示器文字Paint
        textPaint = new Paint();
        textPaint.setColor(indicatortextcolor);
        textPaint.setAntiAlias(true);
        //虚线Paint
        dashLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //上方文字Paint
        abovetextPaint = new Paint();
        abovetextPaint.setAntiAlias(true);
        //下部文字Paint
        buttotextPaint = new Paint();
        buttotextPaint.setAntiAlias(true);
        //小车图标Paint
        currentIndexpaint = new Paint();
        // 设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        currentIndexpaint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        currentIndexpaint.setDither(true);
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
            indicatorWidth = getPaddingLeft() + getPaddingRight() + (((int) indicatorRadius * 2) + (int) lineLenth) * indicatorBeanList.size();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            indicatorHeight = heightMeasure;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            indicatorHeight = ((int) indicatorRadius * 2) + getPaddingTop() + getPaddingBottom();
            if (hasAboveText) {
                //圆的高度+ 上文字的高度
                indicatorHeight = indicatorHeight + ((int) indicatorRadius * 2);
            }
            if (hasButtomText) {
                //圆的高度+ 上文字的高度
                indicatorHeight = indicatorHeight + ((int) indicatorRadius * 2);
            }

        }
        setMeasuredDimension(indicatorWidth, indicatorHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawIndicator(canvas);
    }

    private int left;//初始左边距
    private int top; //初始上边距
    //圆的半径
    private int raduis = 0;
    //线的长度
    private int lenth = 0;
    //圆的中心点坐标
    private int centerX = 0;
    private int centerY = 0;
    //线的起始点坐标
    private int startX = 0;
    private int startY = 0;
    //线的终点坐标
    private int endX = 0;
    private int endY = 0;

    private void drawIndicator(Canvas canvas) {

        //getX() +
        left = getLeft() + getPaddingLeft();
        top = getTop() + getPaddingTop();//getY() +

        if (indicatorBeanList != null && indicatorBeanList.size() > 0) {
            //圆的半径
            raduis = (int) indicatorRadius;
            //线的长度
            lenth = (int) lineLenth;
            //圆的中心点坐标
            centerX = 0;
            centerY = top + raduis;
            //线的起始点坐标
            startX = 0;
            startY = top + raduis;
            //线的终点坐标
            endX = 0;
            endY = top + raduis;
            if (hasAboveText) {
                centerY = centerY + raduis * 2;
                startY = startY + raduis * 2;
                endY = startY;
            }

            for (int i = 0; i < indicatorBeanList.size(); i++) {
                IndicatorBean indicatorBean = indicatorBeanList.get(i);

                //多个圆的横坐标公式
                centerX = left + raduis + (lenth + raduis + raduis) * i;
                drawCircleIndicator(canvas, centerX, centerY, indicatorBean.circleIndicatorColor);
                //画文字
                drawText(canvas, centerX, centerY, indicatorBean.indicatorText, indicatorBean.indicatorTexttColor);
                //多条线的横坐标公式
                startX = left + (raduis * 2) * (i + 1) + lenth * i;
                endX = startX + lenth;
                if (indicatorBean.isDashLine)
                    //如果是最后一根虚线，画一半
                    if (i == indicatorBeanList.size() - 1) {
                        drawIndicatorDashLine(canvas, startX, startY, endX, endY, indicatorBean.lineColor, true);
                    } else {
                        drawIndicatorDashLine(canvas, startX, startY, endX, endY, indicatorBean.lineColor, false);
                    }

                else {
                    drawIndicatorLine(canvas, startX, startY, endX, endY, indicatorBean.lineColor);
                }

                //画指示器上部文字
                if (indicatorBean.isShowAboveText&& !TextUtils.isEmpty(indicatorBean.indicatorAboveText)) {
                    int textY = (int) (raduis * 1.5);
                    textY = centerY - textY;
                    drawOutSideAboveText(canvas, centerX, textY, indicatorBean.indicatorAboveText, indicatorBean.indicatroAboveTextColor);
                }
                if (indicatorBean.isShowButtomText && !TextUtils.isEmpty(indicatorBean.indicatorButtomText)) {
                    //画指示器下部文字
                    int textButtomY = centerY + raduis * 2;
                    drawOutSideButtomText(canvas, centerX, textButtomY, indicatorBean.indicatorButtomText, indicatorBean.indicatroButtomTextColor);
                }
            }

        }

    }

    /***
     * 圆指示器
     * @param canvas
     * @param centerX
     * @param centerY
     * @param indicatorColor
     */
    private void drawCircleIndicator(Canvas canvas, int centerX, int centerY, int indicatorColor) {
        circlePaint.setColor(indicatorColor);
        circlePaint.setStrokeWidth(1);
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, indicatorRadius, circlePaint);
        //  canvas.drawCircle(left+ indicatorRadius  , top + indicatorRadius, indicatorRadius, circlePaint);
    }

    /***
     * 线
     * @param canvas
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param lineColor
     */
    private void drawIndicatorLine(Canvas canvas, int startX, int startY, int endX, int endY, int lineColor) {
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineSize);
        canvas.drawLine(startX, startY, endX, endY, linePaint);
    }

    /**
     * 画虚线
     *
     * @param canvas
     * @param starX
     * @param startY
     * @param endX
     * @param endY
     * @param lineColor
     */
    private void drawIndicatorDashLine(Canvas canvas, int starX, int startY, int endX, int endY, int lineColor, boolean drawHalf) {

        dashLinePaint.setColor(lineColor);
        // 需要加上这句，否则画不出东西
        dashLinePaint.setStyle(Paint.Style.STROKE);
        dashLinePaint.setStrokeWidth(5);
        dashLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 10}, 0));

        Path mPath = new Path();

        mPath.reset();
        mPath.moveTo(starX, startY);
        //最后一根线，是否只画一半
        if (drawHalf) {
            endX = endX - lenth / 2;
        }
        mPath.lineTo(endX, endY);
        canvas.drawPath(mPath, dashLinePaint);
    }

    /**
     * 指示器内文字
     *
     * @param canvas
     * @param centerX
     * @param centerY
     * @param text
     * @param textColor
     */
    private void drawText(Canvas canvas, int centerX, int centerY, String text, int textColor) {

        //设置默认字体大小
        int textSize = DensityUtil.dip2px(context, 15);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        //该方法即为设置基线上那个点究竟是left,center,还是right  设置为center
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontMetricstop = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float fontMetricsbottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (centerY - fontMetricstop / 2 - fontMetricsbottom / 2);//基线中间点的y轴计算公式

        //获取text设置默认字体后的文字长度
        float measureTextLen = textPaint.measureText(text);
        //文字宽度大于圆的宽度
        if (measureTextLen + 10 > indicatorRadius * 2) {

            //缩减字体大小
            while (textPaint.measureText(text) + 10 > indicatorRadius * 2) {
                textSize--;
                textPaint.setTextSize(textSize);
            }

        }

        canvas.drawText(text, centerX, baseLineY, textPaint);

    }

    /***
     * 底部文字
     * @param canvas
     * @param centerX
     * @param centerY
     * @param text
     * @param textColor
     */
    private void drawOutSideButtomText(Canvas canvas, int centerX, int centerY, String text, int textColor) {
        //设置默认字体大小
        int textSize = DensityUtil.dip2px(context, 15);
        buttotextPaint.setColor(textColor);
        buttotextPaint.setTextSize(textSize);
        //该方法即为设置基线上那个点究竟是left,center,还是right  设置为center
        buttotextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, centerX, centerY, buttotextPaint);

    }

    /**
     * 上方文字
     *
     * @param canvas
     * @param centerX
     * @param centerY
     * @param text
     * @param textColor
     */
    private void drawOutSideAboveText(Canvas canvas, int centerX, int centerY, String text, int textColor) {

        abovetextPaint.setColor(textColor);
        //设置默认字体大小
        int textSize = DensityUtil.dip2px(context, 15);
        abovetextPaint.setTextSize(textSize);
        //该方法即为设置基线上那个点究竟是left,center,还是right  设置为center
        abovetextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, centerX, centerY, abovetextPaint);

    }


    //以覆盖的方式画小车
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawCurrentIndexIcon(canvas);
    }

    /***
     * 画小车
     * @param canvas
     */
    private void drawCurrentIndexIcon(Canvas canvas) {
        Drawable carDarwable = context.getResources().getDrawable(R.mipmap.ic_maincar);
        Bitmap carBitmap = drawableToBitmap(carDarwable);
        int carBitmapLeft = 0;
        int carBitmapTop = 0;
        int carHeight = carBitmap.getHeight();
        int carWidth = carBitmap.getWidth();
        carBitmapTop = centerY - carHeight;// centerY - carHeight *
        carBitmapLeft = centerX - raduis - carWidth - 20;
        canvas.drawBitmap(carBitmap, carBitmapLeft, carBitmapTop, currentIndexpaint);
    }

    /***
     * 将drawable 转化成 bitmap
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /***
     * 设置指示线长度
     * @param lineLenth
     */
    public void setLineslength(int lineLenth) {
        this.lineLenth = lineLenth ;
        //this.lineLenth = lineLenth;
        postInvalidate();
    }

    List<IndicatorBean> indicatorBeanList = new ArrayList<>();

    /***
     * 设置指示器数据
     * @param indicatorBeanList
     */
    public void setIndicatorData(List<IndicatorBean> indicatorBeanList) {
        this.indicatorBeanList = indicatorBeanList;
        postInvalidate();
    }

}
