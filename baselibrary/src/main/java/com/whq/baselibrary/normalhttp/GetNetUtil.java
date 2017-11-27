package com.whq.baselibrary.normalhttp;

import com.whq.baselibrary.normalhttp.okhttpLib.OkHttpUtil;
import com.whq.baselibrary.normalhttp.retrofitLib.RetrofitUtil;

/**
 * Created by admin on 2017/11/20.
 */

public class GetNetUtil {

    public OkHttpUtil getHttpByOkHttp(){
        return OkHttpUtil.getInstance();
    }
    public RetrofitUtil getHttpByRetrofit(){
           return RetrofitUtil.getInstance();
    }
}
