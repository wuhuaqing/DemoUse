package com.innotek.demotestproject;

import android.content.Context;

import com.squareup.leakcanary.RefWatcher;
import com.weexcontainmoudle.WeexApplication;

import base.BaseApplication;

/**
 * Created by admin on 2017/1/5.
 * 自定义Application后需要在清单文件中申明 android:name=".DemoProApplication"
 */

public class DemoProApplication extends WeexApplication {//BaseApplication
   /*  public static RefWatcher getRefWatcher(Context context) {
       DemoProApplication application = (DemoProApplication) context.getApplicationContext();
        return application.refWatcher;
    }
    private RefWatcher refWatcher;
    @Override
    public void init() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }*/
}
