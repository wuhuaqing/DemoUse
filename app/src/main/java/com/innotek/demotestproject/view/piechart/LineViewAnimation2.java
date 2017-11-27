package com.innotek.demotestproject.view.piechart;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by whq on 2016/11/22.
 * 划线
 */
public class LineViewAnimation2 extends View {

    private boolean isTurn;//到转折点的标识
    private Point point3;

    public LineViewAnimation2(Context context) {
        super(context);
        init();
    }

    public LineViewAnimation2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineViewAnimation2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private Point points = null;
    public void init() {
        final  Point point1 = new Point(0, 0);
        final  Point point2 = new Point(300, 300);
        point3 = new Point(400, 300);
        //Point point4 = new Point(400, 400);
        points = new Point(0,0);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(),  point1, point2, point3);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
              Point point = (Point) animation.getAnimatedValue();
              points.x = point.getX();
              points.y = point.getY();
                //到第二个点
              if(point.equals(point2)){
                  isTurn = true;
                }


              postInvalidate();
            }
        });

        //动画监听器
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.setDuration(3000);
        anim.start();
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

        final float x =   (initX + points.getX());
        final float y =   (initY + points.getY());

        path.lineTo(x, y);

        if(isTurn){
            float turnY = point3.getY();
            float turnX = point3.getX();
            path.lineTo(turnX,turnY);
        }
//
//         float landLineX = x + 40;
//
//         path.rLineTo(landLineX,initY);

        canvas.drawPath(path, paint);
    }

    public class PointEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;
            float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
            float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
            Point point = new Point(x, y);
            return point;
        }
    }

    public class Point {

        private float x;

        private float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if(null==o){
                return false;
            }
            return ((Point) o).x==this.x && ((Point) o).y==this.y ;
        }
    }
}
