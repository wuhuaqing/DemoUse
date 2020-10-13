package com.innotek.demotestproject.view.indicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
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
    private TextPaint buttotextPaint;
    private Paint dashLinePaint;
    /**
     * 指示器数据集合
     */
    private List<IndicatorBean> indicatorBeanList = new ArrayList<>();
    private int staticlayoutHeght;
    private int abovetextlenth;

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
        buttotextPaint = new TextPaint();
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
        //屏幕宽度
        int screenwidth = DensityUtil.getDisplayMetrics(context).widthPixels;

        if (widthMode == MeasureSpec.EXACTLY) {
            indicatorWidth = widthMeasure - getPaddingLeft() - getPaddingRight();
        } else if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            //控件宽度
            // indicatorWidth = getPaddingLeft() + getPaddingRight() + (((int) indicatorRadius * 2) + (int) lineLenth) * indicatorBeanList.size();
            indicatorWidth = screenwidth - getPaddingLeft() - getPaddingRight();
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

        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            indicatorHeight = ((int) indicatorRadius * 2) + getPaddingTop() + getPaddingBottom();
            if (hasAboveText) {
                //圆的高度+ 上文字的高度
                indicatorHeight = indicatorHeight + ((int) indicatorRadius * 2);
            }
            if (hasButtomText) {
                //圆的高度+ 上文字的高度
                indicatorHeight = indicatorHeight + ((int) indicatorRadius * 2);
                if (staticlayoutHeght > 0) {
                    indicatorHeight += staticlayoutHeght;
                }
            }

        }
        setMeasuredDimension(indicatorWidth, indicatorHeight);
        //计算线的长度  屏幕宽度  -  左右padding  - 圆的直径长度

        left = getPaddingLeft();
        right = getPaddingRight();
        top = getPaddingTop();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (indicatorBeanList.size() > 3) {//indicatorWidth -left - right
            lineLenth = (indicatorWidth) / (indicatorBeanList.size() - 1) - 3 * indicatorRadius;//(indicatorWidth) / (indicatorBeanList.size() - 1) - 4 * indicatorRadius

        } else if (indicatorBeanList.size() == 3) {
            lineLenth = (indicatorWidth) / (indicatorBeanList.size() - 1) - 3 * indicatorRadius - DensityUtil.dip2px(context, 14);
        } else {
            lineLenth = (indicatorWidth - left - right) / 2;
            lineLenth += (lineLenth) / 2 - DensityUtil.dip2px(context, 14);
        }
        drawIndicator(canvas);
    }

    private int left;//初始左边距
    private int right;//右边距
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
    private int carCenterX = 0;

    private void drawIndicator(Canvas canvas) {


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
                if (indicatorBean.carIndex == i) {//小车X坐标
                    carCenterX = centerX;
                }

                drawCircleIndicator(canvas, centerX, centerY, indicatorBean.circleIndicatorColor);
                //画文字
                drawText(canvas, centerX, centerY, indicatorBean.indicatorText, indicatorBean.indicatorTexttColor);
                //多条线的横坐标公式
                startX = left + (raduis * 2) * (i + 1) + lenth * i;
                endX = startX + lenth;
                //是否划线
                if (indicatorBean.hasLine) {
                    if (indicatorBean.isDashLine)
                    //如果是最后一根虚线，画一半
                    {
                        if (i == indicatorBeanList.size() - 1) {
                            drawIndicatorDashLine(canvas, startX, startY, endX, endY, indicatorBean.lineColor, true);
                        } else {
                            drawIndicatorDashLine(canvas, startX, startY, endX, endY, indicatorBean.lineColor, false);
                        }
                    } else {
                        drawIndicatorLine(canvas, startX, startY, endX, endY, indicatorBean.lineColor);
                    }
                }

                //画指示器上部文字
                if (indicatorBean.isShowAboveText && !TextUtils.isEmpty(indicatorBean.indicatorAboveText)) {
                    int textY = (int) (raduis * 1.5);
                    textY = centerY - textY;
                    drawOutSideAboveText(canvas, i, centerX, textY, abovetextlenth, indicatorBean.indicatorAboveText, indicatorBean.indicatroAboveTextColor);
                    // drawOutSideAboveText(canvas, i, centerX, textY , indicatorBean.indicatorAboveText, indicatorBean.indicatroAboveTextColor);
                }
                if (indicatorBean.isShowButtomText && !TextUtils.isEmpty(indicatorBean.indicatorButtomText)) {
                    //画指示器下部文字
                    drawOutSideButtomText(canvas, i, centerX, centerY, indicatorBean);
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
        if (lineColor == 0) {
            lineColor = getResources().getColor(R.color.gary);
        }
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineSize);
        canvas.drawLine(startX, startY, endX, endY, linePaint);
    }

    /**
     * 画虚线
     */
    private void drawIndicatorDashLine(Canvas canvas, int starX, int startY, int endX, int endY, int lineColor, boolean drawHalf) {
        if (lineColor == 0) {
            lineColor = getResources().getColor(R.color.gary);
        }
        dashLinePaint.setColor(lineColor);
        // 需要加上这句，否则画不出东西
        dashLinePaint.setStyle(Paint.Style.STROKE);
        dashLinePaint.setStrokeWidth(5);
        dashLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));

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
     */
    private void drawText(Canvas canvas, int centerX, int centerY, String text, int textColor) {

        //设置默认字体大小
        int textSize = DensityUtil.dip2px(context, 15);
        if (textColor == 0) {
            textColor = indicatortextcolor;
        }
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        //该方法即为设置基线上那个点究竟是left,center,还是right  设置为center
        textPaint.setTextAlign(Paint.Align.CENTER);

        //两种竖直方向偏移量的取值方式
        //方式一：
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        //   float baseLineY = centerY - (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.top - 5;
        float baseLineY = centerY - (fontMetrics.descent + fontMetrics.ascent) / 2;
        //方式二：
//        Rect rect = new Rect();
//        textPaint.getTextBounds(text,0,text.length(),rect);
//        float baseLineY = centerY - (rect.top +rect.bottom)/2;


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
     * @param indicatorBean
     */
    private void drawOutSideButtomText(Canvas canvas, int index, int centerX, int centerY, IndicatorBean indicatorBean) {

        CharSequence text = indicatorBean.indicatorButtomText;
        int textColor = indicatorBean.indicatroButtomTextColor;
        buttotextPaint.setColor(textColor);
        //该方法即为设置基线上那个点究竟是left,center,还是right  设置为center
        buttotextPaint.setTextAlign(Paint.Align.CENTER);
        //int textLen = (int) buttotextPaint.measureText(text,0,text.length());
        //第一个和最后一个，位置要往中间靠
        if (index == 0) {
            buttotextPaint.setTextAlign(Paint.Align.LEFT);
            centerX = centerX - raduis;
        } else if (index == indicatorBeanList.size() - 1) {
            buttotextPaint.setTextAlign(Paint.Align.RIGHT);
            //右边文字起始坐标
            centerX = centerX + raduis + right / 2;
        }
        //需要换行显示
        if (indicatorBean.buttomTextIsNeedNewLine) {
            centerY = (int) (centerY + raduis * 1.5);
            //设置默认字体大小
            buttotextPaint.setTextSize(DensityUtil.dip2px(context, 13));
            canvas.save();
            canvas.translate(centerX, centerY);
            // lenth 换行的宽度是线的长度
            StaticLayout staticLayout = new StaticLayout(text, buttotextPaint, lenth, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
            staticLayout.draw(canvas);
            staticlayoutHeght = staticLayout.getHeight();
            canvas.restore();
            requestLayout();
        } else {//不换行显示
            buttotextPaint.setTextSize(DensityUtil.dip2px(context, 13));
            centerY = (int) (centerY + raduis * 2.5);
            canvas.drawText(text.toString(), centerX, centerY, buttotextPaint);
        }

    }

    /**
     * 上方文字, int textLenth
     */
    private void drawOutSideAboveText(Canvas canvas, int index, int centerX, int centerY, int textLenth, String text, int textColor) {
        abovetextPaint.setColor(textColor);
        //设置默认字体大小
        int textSize = DensityUtil.dip2px(context, 12);
        abovetextPaint.setTextSize(textSize);

        //该方法即为设置基线上那个点究竟是left,center,还是right  设置为center
        abovetextPaint.setTextAlign(Paint.Align.CENTER);

        //第一个和最后一个，位置要往中间靠
        if (index == 0) {
            abovetextPaint.setTextAlign(Paint.Align.LEFT);
            centerX = centerX - raduis;
        } else if (index == indicatorBeanList.size() - 1) {
            abovetextPaint.setTextAlign(Paint.Align.RIGHT);
            //右边文字起始坐标
            centerX = centerX + raduis + right / 2;
        }

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
        Drawable carDarwable = context.getResources().getDrawable(R.drawable.ic_maincar);
        Bitmap carBitmap = drawableToBitmap(carDarwable);
        int carBitmapLeft = 0;
        int carBitmapTop = 0;
        int carHeight = carBitmap.getHeight();
        int carWidth = carBitmap.getWidth();
        carBitmapTop = centerY - carHeight;// centerY - carHeight *

        carBitmapLeft = carCenterX - raduis - carWidth - 20;


        canvas.drawBitmap(carBitmap, carBitmapLeft, carBitmapTop, currentIndexpaint);
    }

    /***
     * 将drawable 转化成 bitmap
     * Bitmap 和 Drawable 的互相转换
     * 事实上，由于 Bitmap 和 Drawable 是两个不同的概念，因此确切地说它们并不是互相「转换」，⽽是从其中⼀个获得另一个的对象:
     * Bitmap -> Drawable:创建一个 BitmapDrawable。
     * Drawable -> Bitmap:如果是 BitmapDrawable，使⽤用 BitmapDrawable.getBitmap() 直接 获取;如果不，创建⼀个 Bitmap 和⼀个 Canvas，使⽤Drawable 通过 Canvas 把内容绘制 到 Bitmap 中。
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
        this.lineLenth = lineLenth;
        invalidateView();
    }


    /***
     * 设置指示器数据
     * @param indicatorBeanList
     */
    public void setIndicatorData(List<IndicatorBean> indicatorBeanList) {
        this.indicatorBeanList = indicatorBeanList;
        invalidateView();

    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }


}
