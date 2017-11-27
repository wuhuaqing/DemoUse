package com.innotek.demotestproject.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.innotek.demotestproject.R;

/**
 * Created by admin on 2017/6/12.
 */

public class FloatButtonMenu extends RelativeLayout  implements View.OnClickListener{

    private ImageView iv_floatmenubtn;
    private LinearLayout ll_btn;
    private ImageView iv_btn1;
    private ImageView iv_btn2;
    private ImageView iv_btn3;
    private Context context;

    public FloatButtonMenu(Context context) {
        super(context);
        init(context);
    }

    public FloatButtonMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FloatButtonMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        this.context = context;
        View floatMenu = LayoutInflater.from(context).inflate(R.layout.layout_floatmenu, this, true);
        iv_floatmenubtn = (ImageView) floatMenu.findViewById(R.id.iv_floatmenubtn);
        ll_btn = (LinearLayout) floatMenu.findViewById(R.id.ll_btn);
        iv_btn1 = (ImageView) floatMenu.findViewById(R.id.iv_btn1);
        iv_btn2 = (ImageView)  floatMenu.findViewById(R.id.iv_btn2);
        iv_btn3 = (ImageView) floatMenu.findViewById(R.id.iv_btn3);
        iv_floatmenubtn.setOnClickListener(this);
        iv_btn1.setOnClickListener(this);
        iv_btn2.setOnClickListener(this);
        iv_btn3.setOnClickListener(this);
    }

    private boolean isOpen =  false;


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_floatmenubtn:
                //开启动画
                onMove();
                break;
            case R.id.iv_btn1:
                Toast.makeText(context,"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_btn2:
                Toast.makeText(context,"2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_btn3:
                Toast.makeText(context,"3",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /***
     * 测量控件宽高
     * @param iv
     * @return
     */
    public int getWHwithViewMea(View iv) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        iv.measure(width, height);
        int hei = iv.getMeasuredHeight();
        int wid  = iv.getMeasuredWidth();
        //Log.e(getClass().getName(), "wid的值：" + wid + " hei的值：" + hei);
        return wid;

    }
    @SuppressLint("NewApi")
    public void onMove() {//View view
      int  width = getWHwithViewMea(ll_btn);//获取控件的宽度
        int btn_with =  getWHwithViewMea(iv_floatmenubtn);
        int riwi =  iv_floatmenubtn.getRight();
        if (isOpen) {
           // translateAnimation(-(width) );
            isOpen = false;
           // int point = -width+btn_with+(riwi-btn_with)
            int point =  btn_with-width ;
            ObjectAnimator animator = ObjectAnimator.ofFloat(ll_btn, View.TRANSLATION_X,point);
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(ll_btn,View.SCALE_X,0);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(ll_btn, View.SCALE_Y, 0);
            ObjectAnimator rotationAn = ObjectAnimator.ofFloat(iv_floatmenubtn, View.ROTATION , 0f, 225f);
            rotationAn.setDuration(500);
            AnimatorSet animator2 = new AnimatorSet() ;
            animator2.playTogether(animator,animatorX,animatorY,rotationAn);
            // animator2.setDuration(1000);
            animator2.start();
            animator2.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                 //   iv_floatmenubtn.setImageResource(R.mipmap.ic_open);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        } else {
            //translateAnimation(width);
            isOpen = true;
            ObjectAnimator animator = ObjectAnimator.ofFloat(ll_btn, View.TRANSLATION_X, 0);
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(ll_btn,View.SCALE_X,1);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(ll_btn, View.SCALE_Y, 1);
            ObjectAnimator rotationAn = ObjectAnimator.ofFloat(iv_floatmenubtn, View.ROTATION, 225f,0f);//"rotation"
            rotationAn.setDuration(500);
            AnimatorSet animator2 = new AnimatorSet() ;
            animator2.playTogether(animator,animatorX,animatorY,rotationAn);
             //animator2.setDuration(1000);
            animator2.start();
            animator2.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                 //   iv_floatmenubtn.setImageResource(R.mipmap.ic_ffloatbtn_cancel);
                    ll_btn.setVisibility(VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }

    }

}
