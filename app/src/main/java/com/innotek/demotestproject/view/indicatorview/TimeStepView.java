package com.innotek.demotestproject.view.indicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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
 * Created by admin on 2018/1/22.
 */

public class TimeStepView extends View {

    private Context context;
    private int defaultRadius = 4;
    private float indicatorRadius;
    private int indicatorColor;
    private int lineColor;
    private int indicatortextcolor;
    private Paint circlePaint;
    private Paint linePaint;
    private Paint abovetextPaint;
    private Paint buttotextPaint;
    private int defaultLinesize = 5;
    private int textPadding = 10;
    private Drawable textBactDrawable;
    private Bitmap baBitmap;
    private List<IndicatorBean> indicatorBeanList = new ArrayList<>();

    public TimeStepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TimeStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimestepView);
        indicatorRadius = typedArray.getDimension(R.styleable.TimestepView_indiradius, defaultRadius);
        indicatorColor = typedArray.getColor(R.styleable.TimestepView_indicolor, context.getResources().getColor(R.color.color_3A819B));
        lineColor = typedArray.getColor(R.styleable.TimestepView_indilinecolor, context.getResources().getColor(R.color.color_D3D3D3));

        baBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_paymessage);
        changeSize();
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

        //上方文字Paint
        abovetextPaint = new Paint();
        abovetextPaint.setAntiAlias(true);
        //下部文字Paint
        buttotextPaint = new Paint();
        buttotextPaint.setAntiAlias(true);

    }


    /***
     * 设置指示器数据
     * @param indicatorBeanList
     */
    public void setIndicatorData(List<IndicatorBean> indicatorBeanList) {
        this.indicatorBeanList = indicatorBeanList;
        postInvalidate();
    }

    //view宽、高
    private int indicatorWidth;
    private int indicatorHeight;
    private boolean hasAboveText = true;//是否有上部文字
    private boolean hasButtomText = true;//是否有下部文字
    private float lineLenth = 100;

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
            indicatorWidth = widthMeasure;
        } else if (widthMode == MeasureSpec.AT_MOST) {
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
                // indicatorHeight = indicatorHeight +(int)indicatorRadius*2+ textPadding ;// ((int) indicatorRadius * 2);
                int bitmapHei = baBitmap.getHeight();
                indicatorHeight = indicatorHeight + bitmapHei + textPadding;// ((int) indicatorRadius * 2);
            }
            if (hasButtomText) {

                //圆的高度+ 下文字的高度
                indicatorHeight = indicatorHeight + (int) indicatorRadius * 2 + textPadding;// ((int) indicatorRadius * 2);
            }

        }
        setMeasuredDimension(indicatorWidth, indicatorHeight);
        //计算线的长度  屏幕宽度  -  左右padding  - 圆的直径长度
        lineLenth = (screenwidth - getPaddingLeft() - getPaddingRight()) / (indicatorBeanList.size() - 1) - 2 * indicatorRadius * (indicatorBeanList.size() - 1);//

    }

    public void changeSize() {
        indicatorRadius = DensityUtil.dip2px(context, indicatorRadius);
        textPadding = DensityUtil.dip2px(context, textPadding);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStepView(canvas);
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

    public void drawStepView(Canvas canvas) {
        //getX() +
        left =  getPaddingLeft();
        top =   getPaddingTop();//getY() +

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
                centerY = centerY + raduis * 4 + textPadding * 2;
                startY = startY + raduis * 4 + textPadding * 2;
                endY = startY;
            }

            for (int i = 0; i < indicatorBeanList.size(); i++) {
                IndicatorBean indicatorBean = indicatorBeanList.get(i);

                //多个圆的横坐标公式
                centerX = left + raduis + (lenth + raduis + raduis) * i;
                drawCircleIndicator(canvas, centerX, centerY, indicatorBean.circleIndicatorColor);
                //多条线的横坐标公式
                if (i < indicatorBeanList.size() - 1) {
                    startX = left + (raduis * 2) * (i + 1) + lenth * i;
                    endX = startX + lenth;
                    drawIndicatorLine(canvas, startX, startY, endX, endY, indicatorBean.lineColor);
                }

                //画指示器上部文字
                if (indicatorBean.isShowAboveText && !TextUtils.isEmpty(indicatorBean.indicatorAboveText)) {
                    drawOutSideAboveText(canvas, centerX, centerY, indicatorBean.indicatorAboveText, indicatorBean.indicatroAboveTextColor);
                }


                if (indicatorBean.isShowButtomText && !TextUtils.isEmpty(indicatorBean.indicatorButtomText)) {
                    //画指示器下部文字
                    int textButtomY = centerY + textPadding * 2;//raduis * 2
                    drawOutSideButtomText(canvas, i, centerX, textButtomY, indicatorBean.indicatorButtomText, indicatorBean.indicatroButtomTextColor);
                }
            }


        }
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
        int textColorDef = context.getResources().getColor(R.color.white);
        abovetextPaint.setColor(textColorDef);
        //设置默认字体大小
        int textSize = DensityUtil.dip2px(context, 13);
        abovetextPaint.setTextSize(textSize);
        abovetextPaint.setTextAlign(Paint.Align.CENTER);
        //该方法即为设置基线上那个点究竟是left,center,还是right  设置为center
        int bitmapX = (int) (centerX - textPadding * 2);
        int bitmapY = (int) (raduis * 1.5);
        int textY = (int) (bitmapY + textPadding * 2.2);

        Rect rectMin = new Rect();
        Paint testPain = new Paint();
        testPain.setAntiAlias(true);
        testPain.setTextSize(textSize);
        testPain.setColor(textColorDef);
        String commontext = "-￥44";
        testPain.getTextBounds(commontext, 0, commontext.length(), rectMin);
        Rect rect = new Rect();
        abovetextPaint.getTextBounds(text, 0, text.length(), rect);
        int minWidth = rectMin.width();
        int textwidth = rect.width();//文字宽
        //设置最小文字宽度
        if (textwidth < minWidth) {
            textwidth = minWidth;
        }
        textwidth = textwidth + 30;
        NinePatch ninePatch = new NinePatch(baBitmap, baBitmap.getNinePatchChunk(), null);
        RectF rectF = new RectF(bitmapX, bitmapY, bitmapX + textwidth, bitmapY + baBitmap.getHeight());
        ninePatch.draw(canvas, rectF);
        canvas.drawText(text, centerX, textY, abovetextPaint);
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
        linePaint.setStrokeWidth(defaultLinesize);
        canvas.drawLine(startX, startY, endX, endY, linePaint);
    }

    /***
     * 底部文字
     * @param canvas
     * @param centerX
     * @param centerY
     * @param text
     * @param textColor
     */
    private void drawOutSideButtomText(Canvas canvas, int i, int centerX, int centerY, String text, int textColor) {
        //设置默认字体大小
        int textSize = DensityUtil.dip2px(context, 15);
        buttotextPaint.setColor(textColor);
        buttotextPaint.setTextSize(textSize);
        //该方法即为设置基线上那个点究竟是left,center,还是right  设置为center
        buttotextPaint.setTextAlign(Paint.Align.CENTER);
        int textLen = (int) buttotextPaint.measureText(text);
        //第一个和最后一个，位置要往中间靠
        if (i == 0) {
            buttotextPaint.setTextAlign(Paint.Align.LEFT);
            centerX = centerX - textLen / 4;
        } else if (i == indicatorBeanList.size() - 1) {
            buttotextPaint.setTextAlign(Paint.Align.RIGHT);
            centerX = centerX + textLen / 4;
        }
        canvas.drawText(text, centerX, centerY, buttotextPaint);
    }

    /**
     * 图片上画文字
     *
     * @param bitmap
     * @param text   文字内容
     * @param textX  文字X坐标
     * @param textY  文字Y坐标
     * @return Bitmap
     */
    private void drawTextAtBitmap(Bitmap bitmap, String text, float textX, float textY) {
        int x = bitmap.getWidth();
        int y = bitmap.getHeight();

        // 创建一个和原图同样大小的位图
        Bitmap newbit = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(newbit);

        Paint paint = new Paint();

        // 在原始位置0，0插入原图
        canvas.drawBitmap(bitmap, 0, 0, paint);
        paint.setColor(getResources().getColor(R.color.white));
        paint.setTextSize(17);

        // 在原图指定位置写上字
        canvas.drawText(text, textX, textY, paint);

        canvas.save(Canvas.ALL_SAVE_FLAG);


    }
}
