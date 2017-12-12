package com.innotek.demotestproject.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.innotek.demotestproject.DemoProApplication;
import com.innotek.demotestproject.R;
import com.squareup.leakcanary.RefWatcher;

/***
 * 基础类
 */
public abstract  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initToolBar(Toolbar toolbar,String title){
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_3A819B));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存监控
        RefWatcher refWatcher = DemoProApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
