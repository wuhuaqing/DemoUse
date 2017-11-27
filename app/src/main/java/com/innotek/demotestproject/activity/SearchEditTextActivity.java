package com.innotek.demotestproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.view.seacheditview.ChooseEditText;
import com.innotek.demotestproject.view.seacheditview.OnChooseEditTextListener;

public class SearchEditTextActivity extends Activity implements View.OnClickListener{

    ChooseEditText chooseEditText;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seacheditview);
        chooseEditText = (ChooseEditText) findViewById(R.id.chooseedittext);
        tvResult = (TextView) findViewById(R.id.textView);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.search).setOnClickListener(this);

        chooseEditText.setOnChooseEditTextListener(new OnChooseEditTextListener() {
            @Override
            public void onTextChangeed(String text) {
                tvResult.setText(text);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_2:
                chooseEditText.addItem("NIKE");
                break;
            case R.id.btn_3:
                chooseEditText.addItem("ADIDAS");
                break;
            case R.id.btn_4:
                chooseEditText.addItem("MIZUNO");
                break;
            case R.id.btn_5:
                chooseEditText.addItem("安踏");
                break;
            case R.id.search:
                //获取输入值
                String text = chooseEditText.getText();
                if (text == null)
                    Toast.makeText(SearchEditTextActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SearchEditTextActivity.this, text, Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
