package com.innotek.demotestproject.view.indicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.piechart.DensityUtil;

import java.util.ArrayList;
import java.util.List;

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
    private String indicatrorText;

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
            indicatorWidth =  getPaddingLeft() + getPaddingRight() +(((int) indicatorRadius * 2) + (int) lineLenth)*indicatorNumbers.size();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
             indicatorHeight = heightMeasure;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            indicatorHeight = ((int) indicatorRadius * 2) + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(indicatorWidth, indicatorHeight);
    }


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        drawIndicator(canvas);
    }


    public void drawIndicator(Canvas canvas) {

        int left = getLeft() + getPaddingLeft();//getX() +
        int top = getTop() + getPaddingTop();//getY() +
        //画文字
        //  drawText(canvas,left,top,indicatrorText);
        //画指示实线
        //drawIndicatorLine(canvas,left,top);
        // 画虚线
        //drawIndicatorDashLine(canvas,left,top);
        if(indicatorNumbers!=null&&indicatorNumbers.size()>0){
            //圆的半径
            int raduis = (int)indicatorRadius;
            //线的长度
            int lenth = (int)lineLenth;
            //圆的中心点坐标
            int centerX = 0;
            int centerY = top+raduis;
            //线的起始点坐标
            int startX= 0;
            int startY = top+raduis;
            //线的终点坐标
            int endX = 0;
            int endY = top+raduis;
            for(int i = 0;i<indicatorNumbers.size();i++){
                //多个圆的横坐标公式
                centerX =  left+raduis+(lenth+raduis+raduis)*i;
                drawCircleIndicator(canvas,centerX,centerY);
                //画文字
                drawText(canvas,centerX,centerY,strList.get(i));
                //多条线的横坐标公式
                startX =  left+(raduis*2)*(i+1)+lenth*i;
                endX = startX+lenth;
                if(lineStausList.get(i))
                drawIndicatorLine(canvas,startX,startY,endX,endY);
                else{
                drawIndicatorDashLine(canvas,startX,startY,endX,endY);
                }
            }

        }


    }

    public void drawCircleIndicator(Canvas canvas, int centerX , int centerY){
        circlePaint.setColor(indicatorColor);
        circlePaint.setStrokeWidth(1);
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX  , centerY , indicatorRadius, circlePaint);
      //  canvas.drawCircle(left+ indicatorRadius  , top + indicatorRadius, indicatorRadius, circlePaint);
    }


    public void drawIndicatorLine(Canvas canvas, int startX , int startY, int endX , int endY){
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineSize);

        canvas.drawLine(startX, startY, endX, endY, linePaint);
    }

    public void drawIndicatorDashLine( Canvas canvas ,int starX , int startY, int endX , int endY){
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(lineColor);
        // 需要加上这句，否则画不出东西
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setPathEffect(new DashPathEffect(new float[] {10, 15}, 0));

        Path  mPath = new Path();

        mPath.reset();
        mPath.moveTo(starX, startY);
        mPath.lineTo(endX, endY);
        canvas.drawPath(mPath, mPaint);
    }

    public void drawText(Canvas canvas, int centerX , int centerY,String text){

        //设置默认字体大小
         int textSize = DensityUtil.dip2px(context,15);
         Paint textPaint = new Paint();
         textPaint.setColor(Color.WHITE);
         textPaint.setAntiAlias(true);
         textPaint.setTextSize(textSize);
         //该方法即为设置基线上那个点究竟是left,center,还是right  设置为center
         textPaint.setTextAlign(Paint.Align.CENTER);

         Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
         float fontMetricstop = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
         float fontMetricsbottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
         int baseLineY = (int) (centerY- fontMetricstop/2 - fontMetricsbottom/2);//基线中间点的y轴计算公式

       //获取text设置默认字体后的文字长度
        float measureTextLen = textPaint.measureText(text);
        //文字宽度大于圆的宽度
        if(measureTextLen+10>indicatorRadius*2){

            //缩减字体大小
            while ( textPaint.measureText(text)+10>indicatorRadius*2){
                      textSize--;
                      textPaint.setTextSize(textSize);
            }

        }

         canvas.drawText(text,centerX,baseLineY,textPaint);

    }



    public void setLineslength(int lineLenth){
        this.lineLenth = lineLenth-getPaddingLeft()+indicatorRadius*2;
        //this.lineLenth = lineLenth;
        postInvalidate();
    }

    public List<Integer> indicatorNumbers = new ArrayList<>();
    public List<Boolean> lineStausList = new ArrayList<>();
    public List<String> strList = new ArrayList<>();
    public void setIndicatorData(ArrayList indicatorNumbers,ArrayList lineStausList,ArrayList strList){
         this.indicatorNumbers =indicatorNumbers;
         this.lineStausList =lineStausList;
         this.strList =strList;
         postInvalidate();
    }
}
