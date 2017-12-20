package com.innotek.demotestproject.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by admin on 2017/12/14.
 * 水平指示条
 *
 */

public class IndicatorView  extends LinearLayout{


    private Context context;
    private Paint paint;

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }


    public void init(Context context, AttributeSet attrs){
        this.context =  context;
        this.setOrientation(LinearLayout.HORIZONTAL);
        //开启绘图缓存，提高绘图效率
        this.setDrawingCacheEnabled(true);
        initPaint();
    }

    public void initPaint(){
        paint = new Paint();
        // 设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        this.paint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        this.paint.setDither(true);
    }
}
