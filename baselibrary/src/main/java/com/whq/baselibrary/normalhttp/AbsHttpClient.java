package com.whq.baselibrary.normalhttp;

import android.content.Context;

import com.whq.baselibrary.normalhttp.okhttpLib.HttpResult;
import com.whq.baselibrary.normalhttp.okhttpLib.OkHttpUtil;
import com.whq.baselibrary.normalhttp.retrofitLib.HttpResultRetrofit;
import com.whq.baselibrary.normalhttp.retrofitLib.ResponseHandlerRetrofit;
import com.whq.baselibrary.normalhttp.retrofitLib.RetrofitUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Headers;
//import retrofit.Call;
//import retrofit.Response;
import util.ToastUtil;

/**
 * Created by whq on 2017/7/21.
 * 请求类
 */

public abstract class AbsHttpClient<T> {
    private Context context;
    private GetNetUtil getNetUtil;

    public AbsHttpClient(Context context) {
        this.context = context;
        getNetUtil = new GetNetUtil();
    }

    static final AtomicInteger sNextGeneratedId = new AtomicInteger(100);

    /**
     * 生成接口唯一码，用于匹配
     */
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range
            // under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF)
                newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    /***
     * GET请求
     *
     * @param url
     */
    public <T> int getAsyncHttp(String url, final Class<T> clazz) {
        final int requestCode = AbsHttpClient.generateViewId();

        OkHttpUtil.getAsyn(url, new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, String responseString) {
                try {
                    HttpResult httpResult = new HttpResult();
                    httpResult.responseString = responseString;
                    httpResult.statusCode = statusCode;
                    httpResult.data = httpResult.getData(clazz);
                    httpResult.requestCode = requestCode;
                    EventBus.getDefault().post(httpResult);
                } catch (Exception e) {
                    EventBus.getDefault().post(null);
                }


            }

            @Override
            public void onFailure(int statusCode, Headers headers, String throwable) {
                try {
                    HttpResult httpResult = new HttpResult();
                    httpResult.responseString = throwable;
                    httpResult.statusCode = statusCode;
                    EventBus.getDefault().post(httpResult);
                    httpResult.requestCode = requestCode;
                } catch (Exception e) {
                    EventBus.getDefault().post(null);
                }
            }
        });


        return requestCode;

    }

    /**
     * post以key_value方式传值
     *
     * @param url
     * @param params
     * @param clazz
     * @return
     */
    public int postAysncHttp(String url, Map<String, String> params, final Class<T> clazz) {
        final int requestCode = AbsHttpClient.generateViewId();
        OkHttpUtil.postAsyn(url, params, new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, String responseString) {
                try {
                    HttpResult httpResult = new HttpResult();
                    httpResult.responseString = responseString;
                    httpResult.statusCode = statusCode;
                    httpResult.data = httpResult.getData(clazz);
                    httpResult.requestCode = requestCode;
                    EventBus.getDefault().post(httpResult);
                } catch (Exception e) {
                    EventBus.getDefault().post(null);
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String throwable) {
                try {
                    HttpResult httpResult = new HttpResult();
                    httpResult.responseString = throwable;
                    httpResult.statusCode = statusCode;
                    EventBus.getDefault().post(httpResult);
                    httpResult.requestCode = requestCode;
                } catch (Exception e) {
                    EventBus.getDefault().post(null);
                }
            }
        });
        return requestCode;
    }

    /**
     * post请求直接以字符串形式传递（不做key_value的键值对方式，直接将值放入请求体）
     *
     * @param url
     * @param requesBodyStr
     * @param clazz
     * @return
     */
    public int postAysncHttp(String url, String requesBodyStr, final Class<T> clazz) {

        final int requestCode = AbsHttpClient.generateViewId();
        OkHttpUtil.postAsyn(url, requesBodyStr, new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, String responseString) {
                try {
                    HttpResult httpResult = new HttpResult();
                    httpResult.responseString = responseString;
                    httpResult.statusCode = statusCode;
                    httpResult.data = httpResult.getData(clazz);
                    httpResult.requestCode = requestCode;
                    EventBus.getDefault().post(httpResult);
                } catch (Exception e) {
                    EventBus.getDefault().post(null);
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String throwable) {
                try {
                    HttpResult httpResult = new HttpResult();
                    httpResult.responseString = throwable;
                    httpResult.statusCode = statusCode;
                    EventBus.getDefault().post(httpResult);
                    httpResult.requestCode = requestCode;
                } catch (Exception e) {
                    EventBus.getDefault().post(null);
                }
            }
        });
        return requestCode;
    }


    /**
     * 图片上传操作
     *
     * @param url
     * @param fileKeys
     * @param filepaths
     * @param params
     * @param clazz
     * @return
     */
    public int uploadFileToServer(String url, String[] fileKeys, File[] filepaths, Map<String, String> params, final Class<T> clazz) {
        final int requestCode = AbsHttpClient.generateViewId();
        OkHttpUtil.postAysnHasProgressWithMulti(url, fileKeys, filepaths, params, new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, String responseString) {
                try {
                    HttpResult httpResult = new HttpResult();
                    httpResult.responseString = responseString;
                    httpResult.statusCode = statusCode;
                    httpResult.data = httpResult.getData(clazz);
                    httpResult.requestCode = requestCode;
                    EventBus.getDefault().post(httpResult);
                } catch (Exception e) {
                    EventBus.getDefault().post(null);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String throwable) {
                try {
                    HttpResult httpResult = new HttpResult();
                    httpResult.responseString = throwable;
                    httpResult.statusCode = statusCode;
                    EventBus.getDefault().post(httpResult);
                    httpResult.requestCode = requestCode;
                } catch (Exception e) {
                    EventBus.getDefault().post(null);
                }
            }
        }, null);
        return requestCode;
    }



//------------------------------------------------------------------retrofit--------------------------------------------------------

    /*public void getSync(String url,Call<T> call) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        retrofitUtil.getRetrofit(url);
        retrofitUtil.getSync(call, new ResponseHandlerRetrofit() {
            @Override
            public <T> void onSuccess(int statusCode, com.squareup.okhttp.Headers headers, Response<T> response) {
                try {
                    HttpResultRetrofit<T> httpResult = new HttpResultRetrofit<T>();
                    T githubUser = response.body();
                    httpResult.setData(githubUser);
                    httpResult.setResponseString(response.message());
                    httpResult.setStatusCode(response.code());
                    ToastUtil.show(githubUser.toString());
                    EventBus.getDefault().post(httpResult);
                } catch (Exception e) {
                    EventBus.getDefault().post(null);
                }
            }

            @Override
            public <T> void onFailure(String throwable) {
                EventBus.getDefault().post(throwable);
            }
        });
    }
*/
 }
