package com.innotek.demotestproject.view.piechart;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
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
public class LineViewAnimation4 extends View {
    Object[] pointsArray = new Object[]{
            new Point(0,0),
            new Point(300,0),
            new Point(300,300),
            new Point(0,300),
            new Point(0,0),
    };
    private Point lastPoint = null;
    public LineViewAnimation4(Context context) {
        super(context);
        init();
    }

    public LineViewAnimation4(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineViewAnimation4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private Point points = null;
    private Bitmap bitmap = null;
    public void init() {
        points = new Point(0,0);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), pointsArray);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
              Point point = (Point) animation.getAnimatedValue();
              points.x = point.getX();
              points.y = point.getY();
              postInvalidate();
            }
        });
        anim.setDuration(2000);
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
        if(lastPoint == null){
            lastPoint = new Point(initX,initY);
        }
        path.moveTo(lastPoint.getX(),lastPoint.getY());

        final float x =   (initX + points.getX());
        final float y =   (initY + points.getY());

        path.lineTo(x, y);
        if(bitmap == null){
            bitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas1 = new Canvas(bitmap);
        canvas1.drawPath(path, paint);
        lastPoint.x = points.getX()+initX;
        lastPoint.y = points.getY()+initY;
        canvas.drawBitmap(bitmap,0,0,null);
    }

    public class PointEvaluator implements TypeEvaluator<Point> {
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            float x = startValue.getX() + fraction * (endValue.getX() - startValue.getX());
            float y = startValue.getY() + fraction * (endValue.getY() - startValue.getY());
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

    }
}
