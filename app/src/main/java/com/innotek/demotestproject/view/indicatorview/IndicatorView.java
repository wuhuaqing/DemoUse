package com.innotek.demotestproject.view.indicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.innotek.demotestproject.R;

/**
 * Created by admin on 2017/12/14.
 * 水平指示条
 */

public class IndicatorView extends LinearLayout {

    private Context context;
    private Paint paint;
    //状态textview字体大小
    private int text_statussize = 15;
    private int text_tagsize = 15;
    private int indicatoritembg;
    private int indicatorviewtagbg;
    private int indicatorviewstatusbg;
    private int text_tagcolor;
    private int text_statuscolor;

    private int tagstringId = R.array.indicator_strings;
    private String[] tagstrings;
    private Drawable indicatorbg;
    private Drawable indicatortagbg;
    private DisplayMetrics displayMetrics;
    private int paddingTopInXML;
    private Bitmap indicatortagBitmap;
    private int tagPaddingButtom = 30;

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public void init(Context context, AttributeSet attrs) {
        this.context = context;
        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setVisibility(VISIBLE);
        //开启绘图缓存，提高绘图效率
        this.setDrawingCacheEnabled(true);
        this.paddingTopInXML = this.getPaddingTop();
        initPaint();
        initAttrs(attrs);
        addViewToParent();

       /* this.setPadding(this.getPaddingLeft() + this.indicatortagBitmap.getWidth() /4,
                this.getPaddingTop() + this.indicatortagBitmap.getHeight(),
                this.getPaddingRight() + this.indicatortagBitmap.getWidth() / 2,
                this.getPaddingBottom()+tagPaddingButtom);*/
    }

    public void initPaint() {
        paint = new Paint();
        // 设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        this.paint.setAntiAlias(true);
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        this.paint.setDither(true);
    }

    public void initAttrs(AttributeSet attrs) {
        displayMetrics = getResources().getDisplayMetrics();
        text_statussize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, text_statussize, displayMetrics);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Indicatorview);
        this.indicatoritembg = typedArray.getResourceId(R.styleable.Indicatorview_itembg, R.mipmap.ic_stephor);
        this.indicatorviewtagbg = typedArray.getResourceId(R.styleable.Indicatorview_itemtagbg, R.drawable.shape_text_circlebg);
        this.indicatorviewstatusbg = typedArray.getResourceId(R.styleable.Indicatorview_itemstatusbg, R.drawable.shape_text_circlebg);

        this.text_tagcolor = typedArray.getResourceId(R.styleable.Indicatorview_itemtagcolor, R.color.white);
        this.text_statuscolor = typedArray.getResourceId(R.styleable.Indicatorview_itemstatuscolor, R.color.white);

        this.text_tagsize = typedArray.getResourceId(R.styleable.Indicatorview_itemtagsize, text_tagsize);
        this.text_statussize = typedArray.getResourceId(R.styleable.Indicatorview_itemtagsize, text_statussize);

        indicatorbg = context.getResources().getDrawable(indicatoritembg);
        indicatortagbg = context.getResources().getDrawable(indicatorviewtagbg);
        indicatortagBitmap = drawableToBitmap(indicatortagbg);
        typedArray.recycle();

    }

    private int indicatorViewWidth;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        this.indicatorViewWidth = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int indicatorViewHeight = MeasureSpec.getSize(heightMeasureSpec);

        int desiredWidth = indicatorViewWidth + getPaddingLeft() + getPaddingRight();
        int desiredHeight = this.getChildAt(0).getMeasuredHeight() + getPaddingTop() + getPaddingBottom();

        //测量宽度
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                indicatorViewWidth = Math.min(desiredWidth, indicatorViewWidth);
                break;
            case MeasureSpec.UNSPECIFIED:
                indicatorViewWidth = desiredWidth;
                break;
        }

        //测量高度
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                indicatorViewHeight = Math.min(desiredHeight, indicatorViewHeight);
                break;
            case MeasureSpec.UNSPECIFIED:
                indicatorViewHeight = desiredHeight;
                break;
        }
        setMeasuredDimension(indicatorViewWidth, indicatorViewHeight);
    }

    public void addViewToParent() {
        tagstrings = context.getResources().getStringArray(tagstringId);

        Bitmap indicatorBitmap = drawableToBitmap(indicatorbg);

        if (tagstrings == null || tagstrings.length <= 0) {
            return;
        }
        for (int i = 0; i < tagstrings.length; i++) {
            addIndicarView(indicatorBitmap);
        }
    }

    /***
     * 指示条
     * @param indicatorBitmap
     */
    public void addIndicarView(Bitmap indicatorBitmap) {
        ImageView imageView = new ImageView(context);
        //imageView.setImageBitmap(indicatorBitmap);
        imageView.setBackgroundDrawable(indicatorbg);
        imageView.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0F));
        this.addView(imageView);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawTagView(canvas);
    }

    /***
     * 画tag
     * @param canvas
     */
    public void drawTagView(Canvas canvas) {

        if (tagstrings != null && tagstrings.length > 0) {
            //整体宽度
            int indicarviewWidth = this.indicatorViewWidth - this.getPaddingLeft() - this.getPaddingRight();
            //item宽度
            int itemWidth = indicarviewWidth / tagstrings.length;
            int left = this.getPaddingLeft();
            //左边距
            int paddingleft = left;
            for (int i = 0; i <tagstrings.length; i++) {
                if(i==0){
                    paddingleft = paddingleft +  itemWidth/2 ;
                }else{
                    paddingleft = paddingleft +  itemWidth ;
                }

                //画tag图标
               canvas.drawBitmap(indicatortagBitmap, paddingleft,   0, paint);


                //Rect rect = new Rect(left+paddingleft,this.paddingTopInXML,0,20);
                // Rect rectf = new Rect(left+paddingleft,this.paddingTopInXML,0,20);
                //indicatortagBitmap = drawTextToBitmap(tagstrings[i], indicatortagBitmap);
                //canvas.drawBitmap(indicatortagBitmap, rect, rectf, paint);
            }
        }

    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private Bitmap drawTextToBitmap(String string, Bitmap bitmap) {

        TextPaint textPain = new TextPaint();
        textPain.setColor(Color.WHITE);
        textPain.setTextSize(15);
        textPain.setAntiAlias(true);

        int width = bitmap.getWidth();
        int hight = bitmap.getHeight();

        Bitmap newBitmap = Bitmap.createBitmap(width,hight, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(newBitmap);
        // 建立画笔
        Paint photoPaint = new Paint();
        // 获取更清晰的图像采样，防抖动
        photoPaint.setDither(true);
        // 过滤一下，抗剧齿
        photoPaint.setFilterBitmap(true);
        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());// 创建一个指定的新矩形的坐标
        Rect dst = new Rect(0, 0, width, hight);// 创建一个指定的新矩形的坐标
        canvas.drawBitmap(bitmap, src,dst, photoPaint);

        StaticLayout sl = new StaticLayout(string, textPain, bitmap.getWidth() - 8, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        canvas.translate(20, 20);
        sl.draw(canvas);

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        return bitmap;
    }


}
