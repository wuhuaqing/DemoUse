package com.innotek.demotestproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.innotek.demotestproject.R;

/**
 * Created by admin on 2017/10/31.
 */

public class TextDemoView extends View {

    private int textsize;
    private int textcolor;
    private String mText = "";

    /**
     * 画笔,文本绘制范围
     */
    private Rect mBound;
    private Paint mPaint;


    public TextDemoView(Context context) {
        super(context);
        init(context,null);
    }

    public TextDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public TextDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs){
        TypedArray typedArray =  context.obtainStyledAttributes(attrs, R.styleable.textview_attr);
        textsize = typedArray.getDimensionPixelSize(R.styleable.textview_attr_textsize,20);
        textcolor = typedArray.getColor(R.styleable.textview_attr_textColor, Color.BLACK);
        mText = typedArray.getString(R.styleable.textview_attr_text);
        typedArray.recycle();

             /*
         * 初始化画笔
         */
        mBound = new Rect();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(textsize);
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = onMeasureR(0, widthMeasureSpec);
        int height = onMeasureR(1, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    /**
     * 计算控件宽高
     * @param oldMeasure
     * @author Ruffian
     */
    public int onMeasureR(int attr, int oldMeasure) {

        int newSize = 0;
        int mode = MeasureSpec.getMode(oldMeasure);
        int oldSize = MeasureSpec.getSize(oldMeasure);

        switch (mode) {
            case MeasureSpec.EXACTLY:
                newSize = oldSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:

                float value;

                if (attr == 0) {

                    //  value = mBound.width();
                   value = mPaint.measureText(mText);
                    // 控件的宽度  + getPaddingLeft() +  getPaddingRight()
                    newSize = (int) (getPaddingLeft() + value + getPaddingRight());

                } else if (attr == 1) {

                   // value = mBound.height();
                      Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
                      value = Math.abs((fontMetrics.descent - fontMetrics.ascent));
                    // 控件的高度  + getPaddingTop() +  getPaddingBottom()
                    newSize = (int) (getPaddingTop() + value + getPaddingBottom());
                }
                break;
        }

        return newSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(textcolor);

        /*
         * 控件宽度/2 - 文字宽度/2
         */
        float startX = getWidth() / 2 - mBound.width() / 2;

        /*
         * 控件高度/2 + 文字高度/2,绘制文字从文字左下角开始,因此"+"
         */
        //float startY = getHeight() / 2 + mBound.height() / 2;
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
       // float startY = getHeight() / 2 +(fontMetrics.bottom-fontMetrics.ascent)/2 ;
        float startY = getHeight() / 2 - fontMetrics.descent + (fontMetrics.bottom - fontMetrics.top) / 2;


        // 绘制文字
        canvas.drawText(mText, startX, startY, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        // 中线,做对比
       // canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
        canvas.drawLine(0,startY, getWidth(), startY, mPaint);
    }
}
