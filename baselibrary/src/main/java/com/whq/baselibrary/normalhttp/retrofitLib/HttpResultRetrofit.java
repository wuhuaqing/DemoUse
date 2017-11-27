package com.whq.baselibrary.normalhttp.retrofitLib;

import android.text.TextUtils;

import com.whq.baselibrary.normalhttp.HttpResultInterface;

import util.GsonUtil;
import util.LogUtil;

/**
 * Created by admin on 2017/11/21.
 */

public class HttpResultRetrofit<T> implements HttpResultInterface<T> {

    public int requestCode;
    public int statusCode;
    public String responseString;
    public T data;


    @Override
    public int getRequestCode() {
        return requestCode;
    }

    @Override
    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getResponseString() {
        return responseString;
    }

    @Override
    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    @Override
    public Object getData(Class clazz) {
        try {
            if (!TextUtils.isEmpty(responseString)) {
                return GsonUtil.fromJson(responseString, clazz);
            }
        } catch (Exception e) {
            LogUtil.d("data parse wrong");
            return null;
        }

        return null;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }


}
