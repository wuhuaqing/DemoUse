package com.innotek.demotestproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2017/11/8.
 */

public class PointMoveView extends View {

    private Paint mPaint;
    private int cx = 50;      //圆点默认X坐标
    private int cy = 50;      //圆点默认Y坐标
    private int radius = 50;
    private int screenW = 20;
    private int screenH = 20;

    public PointMoveView(Context context) {
        super(context);
        init(context);
    }

    public PointMoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PointMoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

     public void init(Context context){
         DisplayMetrics display = context.getResources().getDisplayMetrics();
         screenW = display.widthPixels;
         screenH = display.heightPixels;
         mPaint = new Paint();
         mPaint.setAntiAlias(true);
         mPaint.setStyle(Paint.Style.FILL);
         mPaint.setColor(Color.BLACK);


     }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.drawCircle();
        revise();
        //绘制小圆作为小球
        canvas.drawCircle(cx, cy, radius, mPaint);
    }


    //修正圆点坐标
    private void revise(){
        if(cx <= radius){
            cx = radius;
        }else if(cx >= (screenW-radius)){
            cx = screenW-radius;
        }
        if(cy <= radius){
            cy = radius;
        }else if(cy >= (screenH-radius)){
            cy = screenH-radius;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                // 按下
                cx = (int) event.getX();
                cy = (int) event.getY();
                // 通知重绘
                postInvalidate();   //该方法会调用onDraw方法，重新绘图
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动
              /*  cx = (int) event.getX();
                cy = (int) event.getY();
                // 通知重绘
                postInvalidate();*/
                CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
                int left = layoutParams.leftMargin + x   ;
                int top = layoutParams.topMargin + y  ;


                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                setLayoutParams(layoutParams);
                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                // 抬起
                cx = (int) event.getX();
                cy = (int) event.getY();
                // 通知重绘
                postInvalidate();
                break;
        }

            /*
             * 备注1：此处一定要将return super.onTouchEvent(event)修改为return true，原因是：
             * 1）父类的onTouchEvent(event)方法可能没有做任何处理，但是返回了false。
             * 2)一旦返回false，在该方法中再也不会收到MotionEvent.ACTION_MOVE及MotionEvent.ACTION_UP事件。
             */
        //return super.onTouchEvent(event);
        return true;
    }

}
