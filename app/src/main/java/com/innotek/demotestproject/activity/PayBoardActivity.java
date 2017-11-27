package com.innotek.demotestproject.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.innotek.demotestproject.R;

/**
 * Created by admin on 2017/5/9.
 */

public class PayBoardActivity extends  BaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_payboard);
        findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
