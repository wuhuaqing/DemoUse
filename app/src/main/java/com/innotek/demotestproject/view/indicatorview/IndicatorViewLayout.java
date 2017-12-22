package com.innotek.demotestproject.view.indicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.piechart.DensityUtil;

/**
 * Created by whq on 2017/12/21.
 */

public class IndicatorViewLayout extends LinearLayout {

    private Context context;
    //状态textview字体大小
    private int text_statussize = 15;
    private int text_tagsize = 15;
    private int indicatoritembg;
    private int indicatorviewtagbg;
    private int indicatorviewstatusbg;
    private int text_tagcolor;
    private int text_statuscolor;
    //默认布局高度
    private int defaultlaoutHight = 140;



    public IndicatorViewLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttrs(context, attrs);

    }

    public void initAttrs(Context context, AttributeSet attrs) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        text_statussize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, text_statussize, displayMetrics);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Indicatorview);
        this.indicatoritembg = typedArray.getResourceId(R.styleable.Indicatorview_itembg, R.mipmap.ic_stephor);
        this.indicatorviewtagbg = typedArray.getResourceId(R.styleable.Indicatorview_itemtagbg, R.drawable.shape_text_circlebg);
        this.indicatorviewstatusbg = typedArray.getResourceId(R.styleable.Indicatorview_itemstatusbg, R.drawable.shape_text_circlebg);

        this.text_tagcolor = typedArray.getResourceId(R.styleable.Indicatorview_itemtagcolor, R.color.white);
        this.text_statuscolor = typedArray.getResourceId(R.styleable.Indicatorview_itemstatuscolor, R.color.white);

        this.text_tagsize = typedArray.getResourceId(R.styleable.Indicatorview_itemtagsize, text_tagsize);
        this.text_statussize = typedArray.getResourceId(R.styleable.Indicatorview_itemtagsize, text_statussize);

        typedArray.recycle();

    }

    private int indicatorViewWidth;
    private int indicatorViewHeight;
    private int itemWidth;//item宽度
    private int itemHight;//item高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        this.indicatorViewWidth = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        this.indicatorViewHeight = MeasureSpec.getSize(heightMeasureSpec);

        int desiredWidth = indicatorViewWidth + getPaddingLeft() + getPaddingRight();
        int desiredHeight = getMeasuredHeight() + getPaddingTop() + getPaddingBottom();
        if (desiredHeight <= 0) {
            desiredHeight = defaultlaoutHight;
        }

        //测量宽度
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                indicatorViewWidth =indicatorViewWidth;
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
                indicatorViewHeight =indicatorViewHeight;
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


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //整体宽度
        int indicarviewWidth = this.indicatorViewWidth - this.getPaddingLeft() - this.getPaddingRight();
        if (strtags != null && strtags.length > 0) {
            //获取没想item的宽度
            itemWidth = indicarviewWidth / strtags.length;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth,
                    indicatorViewHeight);// LinearLayout.LayoutParams.WRAP_CONTENT
            for (int i = 0; i < strtags.length; i++) {
                addView(strtags[i], statuss[i], params);
            }
        }
    }

    private String[] strtags, statuss;

    public void initStepViewData(String[] strtags, String[] statuss) {
        this.strtags = strtags;
        this.statuss = statuss;

    }



    private void addView(String tag, String status, LinearLayout.LayoutParams params) {
        View v = createItem(tag, status);
        //  v.setId(strId);
        this.addView(v, params);
    }

    private View createItem(String tag, String status) {
        ViewGroup childView = (ViewGroup) View.inflate(context, R.layout.layout_indicatorview, null);

        IndicatorItemView iv_step = (IndicatorItemView) childView.findViewById(R.id.iv_step);
        TextView tagView = (TextView) childView.findViewById(R.id.tv_number);
        TextView tv_status = (TextView) childView.findViewById(R.id.tv_status);
        int chilecount = childView.getChildCount();
        if (indicatorViewHeight > 0 && chilecount > 0) {
            itemHight = indicatorViewHeight / chilecount;
        } else {
            itemHight = tagView.getMeasuredHeight();
        }

        ViewGroup.LayoutParams layoutParams = iv_step.getLayoutParams();
      /*  int measureHeight = iv_step.getMeasuredHeight();
        if(itemHight >measureHeight){
            layoutParams.height = itemHight;
        }*/
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = itemWidth;
        iv_step.setLayoutParams(layoutParams);
        //指示条长度
        iv_step.setLineslength(itemWidth);
        refitText(tagView, tag, itemHight);
        refitText(tv_status, status, itemHight);

        return childView;
    }


    private float mMinTextSize = 8;    //最小的字体大小

    private void refitText(TextView tv, String textString, int height) {
        float mMaxTextSize = tv.getTextSize();
        TextPaint mTextPaint = new TextPaint();
        mTextPaint.set(tv.getPaint());
        if (height > 0) {
            int availableHeight = height - this.getPaddingTop() - this.getPaddingBottom();   //减去边距为字体的实际高度
            float trySize = mMaxTextSize;
            mTextPaint.setTextSize(trySize);
            while (mTextPaint.descent() - mTextPaint.ascent() > availableHeight) {   //测量的字体高度过大，不断地缩放
                trySize -= 1;  //字体不断地减小来适应
                if (trySize <= mMinTextSize) {
                    trySize = mMinTextSize;  //最小为这个
                    break;
                }
                mTextPaint.setTextSize(trySize);
            }
            tv.setTextSize(DensityUtil.px2dip(getContext(), trySize));
            tv.setText(textString);
        }
    }

}
