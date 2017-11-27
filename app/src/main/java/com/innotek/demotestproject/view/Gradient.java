package com.innotek.demotestproject.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;  
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/2/9.
 */
public class Gradient extends RelativeLayout {

    private List<ImageView> imageViews;
    private List<Animation> outAnim;//淡出动画
    private List<Animation> inAnim;//淡入动画
    private Context mContext;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int couot;
    private int currentIndex;//当前的页面
    private LinearLayout linearLayout;
    private onClickListner listner;
    private long time = 3000;//动画间隔时间
    private Runnable runnable;


    public Gradient(Context context) {
        this(context, null);
    }

    public Gradient(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    /**
     * 画点
     */
    public void cratePoint() {
        if (null != imageViews && imageViews.size() > 0) {
            int size = imageViews.size();

            linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.CENTER);
            // 添加图片
            for (int i = 0; i < size; i++) {
                // 设置圆点
               View viewPoint = new View(mContext);
              /*
                 //需要显示圆点时，打开注释
                 viewPoint.setBackgroundResource(R.drawable.point_background);
                int weight = dip2px(mContext, 5);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(weight, weight);
                lp.leftMargin = weight;
                viewPoint.setLayoutParams(lp);
                viewPoint.setEnabled(false);*/
                linearLayout.addView(viewPoint);
            }
            View childAt = linearLayout.getChildAt(0);
            if (null != childAt) {
                childAt.setEnabled(true);
            }
            //添加到图片的下边
            RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-1, -2);
            rlp.bottomMargin = dip2px(mContext, 5);
            rlp.addRule(ALIGN_PARENT_BOTTOM);
            this.addView(linearLayout, rlp);

        }


    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 设置图片
     *
     * @param imageViews
     */
    public void setImageViews(List<ImageView> imageViews) {
        this.imageViews = imageViews;
        for (int i = 0; i < imageViews.size(); i++) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            addView(imageViews.get(i), layoutParams);
        }
        setonClick();
        cratePoint();//隐藏指示点
        createAnim();
        start();

    }

    /**
     * 开启动画
     */
    private void start() {
        final int size = imageViews.size();

        runnable = new Runnable() {
            @Override
            public void run() {
                final int i = couot % size;
                //解决点击事件的冲突
                for (int j = 0; j < size; j++) {


                    if (j == i) {
                        imageViews.get(i).setClickable(true);


                    } else {
                        imageViews.get(i).setClickable(false);
                    }
                }
                if (couot < size) {
                    if (i == size - 1) {

                        ImageView imageView = imageViews.get(0);
                        imageView.startAnimation(outAnim.get(0));

                        ImageView imageView2 = imageViews.get(size - 1);
                        imageView2.startAnimation(inAnim.get(size - 1));


                    } else {
                        //当前的淡出,下一张淡入
                        ImageView imageView = imageViews.get(size - 1 - i);
                        imageView.startAnimation(outAnim.get(size - 1 - i));


                    }
                } else {
                    if (i == size - 1) {
                        //当显示到最后一张的时候,要跳到第一张
                        ImageView imageView = imageViews.get(0);
                        imageView.startAnimation(outAnim.get(0));

                        ImageView imageView2 = imageViews.get(size - 1);
                        imageView2.startAnimation(inAnim.get(size - 1));


                    } else {
                        //当前的淡出,下一张淡入
                        ImageView imageView = imageViews.get(size - 1 - i);
                        imageView.startAnimation(outAnim.get(size - 1 - i));

                        ImageView imageView2 = imageViews.get(size - 2 - i);
                        imageView2.startAnimation(inAnim.get(size - 2 - i));

                    }
                }

                currentIndex = i;
                linearLayout.getChildAt(currentIndex % size).setEnabled(false);
                currentIndex++;
                linearLayout.getChildAt(currentIndex % size).setEnabled(true);
                couot++;
                handler.postDelayed(this, time);
            }
        };

        handler.post(runnable);
    }

    public void stop() {
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        if (imageViews != null) {
            imageViews.clear();
        }
    }

    /**
     * 创建动画
     */
    private void createAnim() {
        outAnim = new ArrayList<>();
        inAnim = new ArrayList<>();
        for (int i = 0; i < imageViews.size(); i++) {
            Animation zoomOutAwayAnim = createZoomOutAwayAnim();
            zoomOutAwayAnim.setFillAfter(true);
            outAnim.add(zoomOutAwayAnim);

            Animation zoomOutNearAnim = createZoomOutNearAnim();
            zoomOutNearAnim.setFillAfter(true);
            inAnim.add(zoomOutNearAnim);

        }
    }

    /**
     * 设置点击事件
     */
    public void setonClick() {
        for (int i = 0; i < imageViews.size(); i++) {
            imageViews.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        listner.setonClick((currentIndex) % imageViews.size());
                    }

                }
            });
        }
    }

    public interface onClickListner {

        void setonClick(int position);
    }

    /**
     * 设置动画播放和handler延迟时间
     *
     * @param time
     */
    public void setTime(long time) {
        this.time = time;
    }

    public void setListner(onClickListner listner) {
        this.listner = listner;
    }

    /**
     * 创建一个淡出缩小的动画
     */
    public Animation createZoomOutAwayAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // 创建一个淡出的动画
        anim = new AlphaAnimation(1f, 0f);
        anim.setDuration(time);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        // 创建一个缩小的动画
        /*anim = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);*/
        return ret;
    }

    /**
     * 创建一个淡入缩小的动画
     */
    public Animation createZoomOutNearAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // 创建一个淡入的动画
        anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(time);
        anim.setInterpolator(new LinearInterpolator());
        ret.addAnimation(anim);
        // 创建一个缩小的动画
        /*anim = new ScaleAnimation(3, 1, 3, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);*/
        return ret;
    }
}