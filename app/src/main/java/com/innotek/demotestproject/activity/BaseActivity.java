package com.innotek.demotestproject.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.innotek.demotestproject.DemoProApplication;
import com.squareup.leakcanary.RefWatcher;

/***
 * 基础类
 */
public abstract  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存监控
        RefWatcher refWatcher = DemoProApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
