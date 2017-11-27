package com.innotek.demotestproject.activity_view.basictrain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2017/9/11.
 */

public class Circle extends View {
    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Circle(Context context) {
        super(context);
        init(context);
    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    Paint paint;
    public void init(Context context){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300,300,200,paint);
    }
}
