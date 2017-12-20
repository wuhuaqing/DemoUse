package com.innotek.demotestproject.activity_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.innotek.demotestproject.R;
import com.innotek.demotestproject.activity.BaseActivity;
import com.innotek.demotestproject.activity.MainActivity;

public class ViewMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main);
        findViewById(R.id.btn_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewMainActivity.this, TextPaintActivity.class));

            }
        });
        findViewById(R.id.btn_horizonlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewMainActivity.this, StepHorezonActivity.class));

            }
        });
    }
}
