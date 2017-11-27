package com.whq.baselibrary.normalhttp.retrofitLib;


import util.ToastUtil;

/**
 * Created by admin on 2017/11/17.
 */

public class RetrofitUtil {

    private static RetrofitUtil retrofitUtil;

    private RetrofitUtil() {
    }

    public static RetrofitUtil getInstance() {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }




 /*   public BaseInterfaceService getRetrofit(String BaseUrl) {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        BaseInterfaceService service = retrofit.create(BaseInterfaceService.class);
        return service;
    }*/
/*

    public <T>  void getSync(Call<T> call,final ResponseHandlerRetrofit resHandler){
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Response<T> response, Retrofit retrofit) {
                if (response != null) {
                        resHandler.onSuccess(response.code(),response.headers(),response);

                }


            }

            @Override
            public void onFailure(Throwable t) {
                resHandler.onFailure(t.toString());
            }
        });
    }

    public <T> void getServiceResponse(Call<T> call, T t) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Response<T> response, Retrofit retrofit) {
                if (response != null) {
                    HttpResultRetrofit<T> httpResult = new HttpResultRetrofit<T>();
                    T githubUser = response.body();
                    httpResult.setData(githubUser);
                    httpResult.setResponseString(response.message());
                    httpResult.setStatusCode(response.code());
                    ToastUtil.show(githubUser.toString());
                }


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
*/


}
