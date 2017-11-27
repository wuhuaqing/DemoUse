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

public class Line extends View {

    public Line(Context context) {
        super(context);
        init(context);
    }

    public Line(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Line(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    Paint paint;
    public void init(Context context){
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawLine(200,200,300,300,paint);
    }
}
