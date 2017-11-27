package com.innotek.demotestproject.utils.http;

import android.content.Context;

import com.whq.baselibrary.normalhttp.AbsHttpClient;

/**
 * Created by admin on 2017/11/20.
 */

public class NetWorkClient extends AbsHttpClient  {

    public NetWorkClient(Context context) {
        super(context);
    }

    @Override
    public int getAsyncHttp(String url, final Class  clazz){
       return super.getAsyncHttp(url,clazz);
    }
}
