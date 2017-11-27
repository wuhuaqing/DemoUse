package com.whq.numberkeyboardview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;


/**
 * 输入支付密码
 *
 */
public class PopEnterPassword extends PopupWindow {

   // private PasswordView pwdView;
  //  private PasswordView_Dialog pwdView;
    private VirtualKeyboardView pwdView;

    private View mMenuView;

    private Activity mContext;

    public PopEnterPassword(final Activity context) {

        super(context);

        this.mContext = context;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mMenuView = inflater.inflate(R.layout.dialog_enter_password, null);

        //pwdView = (PasswordView) mMenuView.findViewById(R.id.pwd_view);
        pwdView = (VirtualKeyboardView) mMenuView.findViewById(R.id.pwd_view);

        //添加密码输入完成的响应
        pwdView.setOnFinishInput(new OnPasswordInputFinish() {
            @Override
            public void inputFinish(String password) {
               // dismiss();
                if(pwdHasCompletedListener!=null){
                    pwdHasCompletedListener.pwdHasCompleted(password);
                }
            }

            @Override
            public void inputDelete() {
                if(pwdHasCompletedListener!=null){
                    pwdHasCompletedListener.pwdDelete();
                }
            }
        });

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.pop_add_ainm);
        // 实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);


      /*  mMenuView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK)&&( isShowing())){
                      dismiss();
                    return true;
                }
                return false;
            }
        });*/

        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(isShowing()){
                     dismiss();
                }
                return false;
            }
        });
    }

    public interface  PwdHasCompletedListener{
         //输入完成
          void pwdHasCompleted(String password);
          void pwdDelete();
    }

    public PwdHasCompletedListener pwdHasCompletedListener;

    public void setPwdHasCompletedListener(PwdHasCompletedListener pwdHasCompletedListener){
        this.pwdHasCompletedListener =pwdHasCompletedListener;
    }
}
