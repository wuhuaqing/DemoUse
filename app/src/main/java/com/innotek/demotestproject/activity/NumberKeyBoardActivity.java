package com.innotek.demotestproject.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.innotek.demotestproject.R;
import com.whq.numberkeyboardview.PopEnterPassword;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class NumberKeyBoardActivity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_key_board);
        et = (EditText) findViewById(R.id.et);
        hideSoftInputMethod(this, et);
        et.addTextChangedListener(txWatcher);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumberKeyBoard();
            }
        });

    }
    private TextWatcher txWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            et.setSelection(s.length());
        }
    };


    /**
     * 展示数字键盘
     */
    private PopEnterPassword popEnterPassword;
    private String paymentMoney = "";//edittext 展示paymentMoney
    private String paymentMoneyTemp = "";//临时paymentMoney

    public void showNumberKeyBoard() {
        if (isFinishing()) {
            return;
        }
        if (popEnterPassword != null && popEnterPassword.isShowing()) {
            return;
        }
        popEnterPassword = new PopEnterPassword(NumberKeyBoardActivity.this);
        // 显示窗口
        popEnterPassword.showAtLocation(NumberKeyBoardActivity.this.findViewById(R.id.activity_number_key_board),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
        popEnterPassword.setPwdHasCompletedListener(new PopEnterPassword.PwdHasCompletedListener() {
            @Override
            public void pwdHasCompleted(String number) {
                if (paymentMoney.length() >=10) {
                    return;
                }
                //当前数字字符串包含 "."
                if (paymentMoneyTemp.contains(".")) {
                    //非小数点时加入字符串
                    if (!number.equals(".")) {
                        paymentMoneyTemp = paymentMoneyTemp + number;
                    }
                } else {
                    paymentMoneyTemp = paymentMoneyTemp + number;
                }
                et.setText(paymentMoney);
                /*boolean isMatchMoneyFormat = StringUtils.isMatches(paymentMoneyTemp, "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
                 //匹配则展示在输入框中
                if (isMatchMoneyFormat) {
                    paymentMoney = paymentMoneyTemp;
                    et.setText(paymentMoney);
                } else {
                    //不匹配则临时paymentMoney 还原成展示的paymentMoney
                    paymentMoneyTemp = paymentMoney;
                }*/
            }

            @Override
            public void pwdDelete() {
                //支付金额向前删除以为
                if (paymentMoney.length() > 0) {
                    paymentMoney = paymentMoney.substring(0, paymentMoney.length() - 1);
                    paymentMoneyTemp = paymentMoney;
                    et.setText(paymentMoney);
                }
            }
        });
    }


    public void hideSoftInputMethod(Activity activity, EditText ed){
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if(currentVersion >= 16){
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        }
        else if(currentVersion >= 14){
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }

        if(methodName == null){
            ed.setInputType(InputType.TYPE_NULL);
        }
        else{
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(ed, false);
            } catch (NoSuchMethodException e) {
                ed.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
