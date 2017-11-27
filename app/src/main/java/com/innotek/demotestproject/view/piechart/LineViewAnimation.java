package com.innotek.demotestproject.view.piechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by whq on 2016/11/22.
 * 划线
 */
public class LineViewAnimation extends View {


    private Path path;
    private Paint paint;
    private LineAnimation lineAnimation;
    private boolean isStop;

    public LineViewAnimation(Context context) {
        super(context);
        init();
    }

    public LineViewAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineViewAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        lineAnimation = new LineAnimation();
        lineAnimation.setDuration(1000);
        startAnimation(lineAnimation);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawLine(canvas);

    }

    public void drawLine(Canvas canvas){
        Path path = new Path();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        //设置空心
        paint.setStyle(Paint.Style.STROKE);

        float initX= getWidth()/4;
        float initY= getHeight()/4;

        path.moveTo(initX,initY);

        float inX = mLineAnimProgress*100;
        float inY = mLineAnimProgress*100;

        final float x =   (initX + inX);
        final float y =   (initY + inY);

        path.lineTo(x, initY);

        if(isStop){

            float landLineX = x + 40;
            float landLineY= y + 40;
           // path.rLineTo(landLineX,initY);
            path.lineTo(landLineX,landLineY);
        }



        canvas.drawPath(path, paint);
    }

    private float  mLineAnimProgress  ;
    public class LineAnimation extends Animation {

        public LineAnimation() {

        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mLineAnimProgress = interpolatedTime;
            if(interpolatedTime>=1.0){
                isStop = true;
            }
            postInvalidate();
        }
    }
}
