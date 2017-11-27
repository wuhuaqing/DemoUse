package com.whq.baselibrary.normalhttp;

/**
 * Created by admin on 2017/11/21.
 */

public interface HttpResultInterface<T> {

    int getRequestCode();

    void setRequestCode(int requestCode);

    int getStatusCode();

    void setStatusCode(int statusCode);

    String getResponseString();

    void setResponseString(String responseString);

    T getData(Class<T> clazz);

    void setData(T data);
}
