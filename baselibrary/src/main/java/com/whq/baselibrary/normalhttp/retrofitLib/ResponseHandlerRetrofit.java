package com.whq.baselibrary.normalhttp.retrofitLib;


import com.whq.baselibrary.normalhttp.ResponseHandler;


/**
 * Created by admin on 2017/11/21.
 */

public abstract class ResponseHandlerRetrofit extends ResponseHandler {


   // public abstract <T> void onSuccess(int statusCode, Headers headers, Response<T> response) ;


    public abstract <T> void onFailure(String throwable);

    @Override
    public void onSuccess(int statusCode, okhttp3.Headers headers, String responseString) {

    }

    @Override
    public void onFailure(int statusCode, okhttp3.Headers headers, String throwable) {

    }
}
