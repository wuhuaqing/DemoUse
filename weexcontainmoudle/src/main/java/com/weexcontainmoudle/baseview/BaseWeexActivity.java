package com.weexcontainmoudle.baseview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.weexcontainmoudle.R;

import java.util.HashMap;
import java.util.Map;

import util.LogUtil;
import util.ToastUtil;

public class BaseWeexActivity extends Activity implements IWXRenderListener{

    private WXSDKInstance mWXSDKInstance;

    private String urlJs = "http://192.168.1.241:8081/dist/TestWeex.weex.js";
    //private String urlJs = "http://192.168.1.241:8081/web/index.html?page=/dist/web/index.js";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_weex);

        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, urlJs);
        //   mWXSDKInstance.render("WXSample", WXFileUtils.loadFileContent("TestWeex.weex.js", this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
        mWXSDKInstance.renderByUrl(BaseWeexActivity.this.getClass().getSimpleName(), urlJs, options, null,  -1,-1, WXRenderStrategy.APPEND_ONCE);
 
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
       // ToastUtil.show("onRenderSuccess:"+"width: "+width+" height: "+height);
        LogUtil.e("onRenderSuccess:"+"width: "+width+" height: "+height);
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
        LogUtil.e("onRefreshSuccess:"+"width: "+width+" height: "+height);
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        LogUtil.e("onException:"+"errCode: "+errCode+" msg: "+msg);
     //   ToastUtil.show("onException:"+"errCode: "+errCode+" msg: "+msg);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityResume();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityDestroy();
        }
    }
}
