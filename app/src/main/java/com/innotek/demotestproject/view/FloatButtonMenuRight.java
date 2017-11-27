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
 * 浮动开关菜单
 */

public class FloatButtonMenuRight extends RelativeLayout  implements View.OnClickListener{

    private ImageView iv_floatmenubtn;
    private LinearLayout ll_btn;
    private ImageView iv_btn1;
    private ImageView iv_btn2;
    private ImageView iv_btn3;
    private Context context;

    public FloatButtonMenuRight(Context context) {
        super(context);
        init(context);
    }

    public FloatButtonMenuRight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FloatButtonMenuRight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        this.context = context;
        View floatMenu = LayoutInflater.from(context).inflate(R.layout.layout_floatmenu_right, this, true);
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
    /***
     * 测量控件宽高
     * @param iv
     * @return
     */
    public int[] getWHwithViewMea(View iv) {
        int width = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int height = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        iv.measure(width, height);
        int hei = iv.getMeasuredHeight();
        int wid  = iv.getMeasuredWidth();
        int[]  wh = new int[2];
        wh[0] = wid;
        wh[1] = hei;
        //Log.e(getClass().getName(), "wid的值：" + wid + " hei的值：" + hei);
        return wh;

    }
    @SuppressLint("NewApi")
    public void onMove() {//View view
        int  width = getWHwithViewMea(ll_btn)[0];//获取控件的宽度
        int btn_with =  getWHwithViewMea(iv_floatmenubtn)[0];
        int riwi =  iv_floatmenubtn.getRight();
        if (isOpen) {
            // translateAnimation(-(width) );
            isOpen = false;
            // int point = -width+btn_with+(riwi-btn_with)
            int point =  btn_with+btn_with/2;
            ObjectAnimator animator = ObjectAnimator.ofFloat(ll_btn, View.TRANSLATION_X,point);//point
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
                    iv_btn1.setClickable(false);
                    iv_btn2.setClickable(false);
                    iv_btn3.setClickable(false);
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
                    iv_btn1.setClickable(true);
                    iv_btn2.setClickable(true);
                    iv_btn3.setClickable(true);
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

    //回调接口
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener =  onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClicked(int position);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_floatmenubtn:
                //开启动画
                onMove();
                break;
            case R.id.iv_btn1:
                Toast.makeText(context,"1",Toast.LENGTH_SHORT).show();
                itemClick(0);
                break;
            case R.id.iv_btn2:
                Toast.makeText(context,"2",Toast.LENGTH_SHORT).show();
                itemClick(1);
                break;
            case R.id.iv_btn3:
                Toast.makeText(context,"3",Toast.LENGTH_SHORT).show();
                itemClick(2);
                break;
        }
    }

    /**
     * 子项点击方法
     * @param position
     */
      public  void itemClick(int position){
          //回调控件使用者
          if(onItemClickListener!=null){
              onItemClickListener.onItemClicked(position);
          }
      }
}
