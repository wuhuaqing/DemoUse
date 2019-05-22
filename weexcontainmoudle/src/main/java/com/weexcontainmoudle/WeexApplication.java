package com.weexcontainmoudle;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

import base.BaseApplication;

public class WeexApplication extends BaseApplication {

    @Override
    public void init() {
        InitConfig initConfig = new InitConfig.Builder().setImgAdapter(new WeexImageAdapter()).build();
        WXSDKEngine.initialize(this,initConfig);
    }
}
