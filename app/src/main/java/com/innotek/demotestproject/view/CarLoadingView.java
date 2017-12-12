package com.innotek.demotestproject.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.innotek.demotestproject.R;

/**
 * Created by admin on 2017/12/5.
 */

public class CarLoadingView extends RelativeLayout {

    public Context context;
    private ImageView iv_car;
    private ImageView iv_road;
    private View layout_carloading;

    public CarLoadingView(Context context) {
        super(context);
        init(context);
    }

    public CarLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CarLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
        layout_carloading = LayoutInflater.from(context).inflate(R.layout.layout_carloading, this,true);
        iv_car = (ImageView) layout_carloading.findViewById(R.id.iv_car);
        iv_road = (ImageView) layout_carloading.findViewById(R.id.iv_road);
    }

    public int getWHwithViewMea(View iv) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        iv.measure(width, height);
        int wid = iv.getMeasuredWidth();
        //Log.e(getClass().getName(), "wid的值：" + wid + " hei的值：" + hei);
        return wid;

    }

    public void startLoad() {
        int layoutWidth = getWHwithViewMea(layout_carloading);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(iv_car, View.TRANSLATION_X, 0f, 600f);
             animator.setRepeatCount(ValueAnimator.INFINITE);
             animator.setRepeatMode(ValueAnimator.RESTART);
            ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(iv_car,View.SCALE_X,1f,0.8f,0.5f);
            ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(iv_car,View.SCALE_Y,1f,0.8f,0.5f);
            animatorScaleX.setRepeatCount(ValueAnimator.INFINITE);
            animatorScaleY.setRepeatCount(ValueAnimator.INFINITE);


            ObjectAnimator animatorRoad = ObjectAnimator.ofFloat(iv_road, View.TRANSLATION_X, 0f,-50f);
            animatorRoad.setRepeatCount(ValueAnimator.INFINITE);
            animatorRoad.setRepeatMode(ValueAnimator.RESTART);
            animatorRoad.setDuration(300);

            AnimatorSet animatorSet = new AnimatorSet();
             animatorSet.playTogether(animator,animatorScaleX,animatorScaleY,animatorRoad);
           // animatorSet.playTogether(animator );
            animatorSet.addListener(new Animator.AnimatorListener() {
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
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.setDuration(5000);

            animatorSet.start();
        }
    }
}
