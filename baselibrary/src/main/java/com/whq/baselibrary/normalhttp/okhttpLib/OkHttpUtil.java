package com.whq.baselibrary.normalhttp.okhttpLib;


import android.util.Log;


import com.whq.baselibrary.normalhttp.ResponseConstant;
import com.whq.baselibrary.normalhttp.ResponseHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.LogUtil;

/**
  * OKHttp Utils
  * OkHttp 3.4.1
  * OkIo 1.10.0
 */

public class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";

    private static volatile OkHttpUtil mInstance;
    private OkHttpClient mOkHttpClient;
    //private Handler mDelivery;
    //private Gson mGson;

    private DownloadDelegate mDownloadDelegate = new DownloadDelegate();
    private GetDelegate mGetDelegate = new GetDelegate();
    private UploadDelegate mUploadDelegate = new UploadDelegate();
    private PostDelegate mPostDelegate = new PostDelegate();

    private OkHttpUtil() {
        //mOkHttpClient = new OkHttpClient();
        //cookie enabled
        //mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        //mDelivery = new Handler(Looper.getMainLooper());
        //mGson = new Gson();
        OkHttpClient.Builder clientBuilder=new OkHttpClient.Builder();
        clientBuilder.connectTimeout(3*10*1000, TimeUnit.MILLISECONDS);
        clientBuilder.readTimeout(3*10*1000, TimeUnit.MILLISECONDS);
         String cerFile="";//AppCfg.getInstance().getSslCerFile();
        if (cerFile != null && cerFile.length() > 0) {
            SSLContext sslContext = loadCertificates(cerFile);
            if (sslContext != null) {//添加ssl 证书
                clientBuilder.sslSocketFactory(sslContext.getSocketFactory());
            }
        }
        mOkHttpClient=clientBuilder.build();

    }

    /**
     * 加载本地 ssl 证书
     * @return
     */
    public SSLContext loadCertificates(String cerFile) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            InputStream certificate= null;//BaseApp.instance().getAssets().open(cerFile);
            String certificateAlias = "cer1";
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
            try {
                if (certificate != null)
                    certificate.close();
            } catch (IOException e) {
                LogUtil.e("OkHttpUtil", e.getMessage(), e);
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null,trustManagerFactory.getTrustManagers(),new SecureRandom());
            //mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
            return sslContext;
        } catch (Exception e) {
            LogUtil.e("OkHttpUtil",e.getMessage(),e);
        }
        return null;
    }

    public static OkHttpUtil getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtil.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtil();
                }
            }
        }
        return mInstance;
    }

    public GetDelegate getGetDelegate() {
        return mGetDelegate;
    }

    public PostDelegate getPostDelegate() {
        return mPostDelegate;
    }

    private DownloadDelegate _getDownloadDelegate() {
        return mDownloadDelegate;
    }

    private UploadDelegate _getUploadDelegate() {
        return mUploadDelegate;
    }

    public static DownloadDelegate getDownloadDelegate() {
        return getInstance()._getDownloadDelegate();
    }

    public static UploadDelegate getUploadDelegate() {
        return getInstance()._getUploadDelegate();
    }

    /**
     * ============Get方便的访问方式============
     */
    public static void getAsyn(String url, ResponseHandler callback) {
        getInstance().getGetDelegate().getAsyn(url, callback, null);
    }

    public static void getAsyn(String url, ResponseHandler callback, Object tag) {
        getInstance().getGetDelegate().getAsyn(url, callback, tag);
    }

    /**
     * ============POST方便的访问方式===============
     */
    public static void postAsyn(String url, Param[] params, final ResponseHandler callback) {
        getInstance().getPostDelegate().postAsyn(url, params, callback, null);
    }

    public static void postAsyn(String url, Map<String, String> params, final ResponseHandler callback) {
        getInstance().getPostDelegate().postAsyn(url, params, callback, null);
    }

    public static void postAsyn(String url, String bodyStr, final ResponseHandler callback) {
        getInstance().getPostDelegate().postAsyn(url, bodyStr, callback, null);
    }

    public static void postAsyn(String url, Param[] params, final ResponseHandler callback, Object tag) {
        getInstance().getPostDelegate().postAsyn(url, params, callback, tag);
    }

    public static void postAsyn(String url, Map<String, String> params, final ResponseHandler callback, Object tag) {
        getInstance().getPostDelegate().postAsyn(url, params, callback, tag);
    }

    public static void postAsyn(String url, String bodyStr, final ResponseHandler callback, Object tag) {
        getInstance().getPostDelegate().postAsyn(url, bodyStr, callback, tag);
    }

    //--------------2017.1.13 图片上传操作-----------
    //单张图片上传操作
    public static void postAsynHasProgress(String url, String filekey, String filepath, Map<String, String> params,   final ResponseHandler callback, Object tag) {

       getInstance()._getUploadDelegate().postAsynHasProgress(url,filekey, new File(filepath), params ,  callback, tag);
    }

    public static void postAysnHasProgressWithMulti(String url, String[] fileKeys, File[] files, Map<String, String> params,   final ResponseHandler callback, Object tag){
        getInstance()._getUploadDelegate().postAysnHasProgressWithMulti(url,fileKeys, files, params , callback, tag);
    }

//--------------2017.1.13 图片上传操作-----------

    //=============便利的访问方式结束===============
    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


    private Param[] validateParam(Param[] params) {
        if (params == null)
            return new Param[0];
        else return params;
    }

    private Param[] map2Params(Map<String, String> params) {
        if (params == null) return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }

    private void deliveryResult(final ResponseHandler resHandler, Request request) {
        okhttp3.Call call=mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Request request1 = call.request();
                resHandler.sendFailureMessage(ResponseConstant.RESULT_CODE_ERROR_UNKNOWN, request1.headers(), new Throwable("responseCode is " + ResponseConstant.RESULT_CODE_ERROR_UNKNOWN));
                if(call.isCanceled()){
                    call.cancel();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();
                if (response.isSuccessful()) {
                    resHandler.sendSuccessMessage(response.code(), response.headers(), body);
                } else {
                    resHandler.sendSuccessMessage(response.code(), response.headers(), body);
                }
                if (call.isCanceled()) {
                    call.cancel();
                }
            }
        });
    }

    private String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }

    public static void cancelTag(Object tag) {
        getInstance()._cancelTag(tag);
    }

    private void _cancelTag(Object tag) {
        //TODO　OkHttpClient.cancel(tag)
        //mOkHttpClient.cancel(tag);
        //mOkHttpClient.dispatcher().cancelAll();
    }

    public static OkHttpClient getClinet() {
        return getInstance().client();
    }

    public OkHttpClient client() {
        return mOkHttpClient;
    }


    public static class Param {
        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }

    //====================PostDelegate=======================
    public class PostDelegate {
        private final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream;charset=utf-8");
        private final MediaType MEDIA_TYPE_STRING = MediaType.parse("text/plain;charset=utf-8");

        public Response post(String url, Param[] params) throws IOException {
            return post(url, params, null);
        }

        /**
         * 同步的Post请求
         */
        public Response post(String url, Param[] params, Object tag) throws IOException {
            Request request = buildPostFormRequest(url, params, tag);
            Response response = mOkHttpClient.newCall(request).execute();
            return response;
        }

        public String postAsString(String url, Param[] params) throws IOException {
            return postAsString(url, params, null);
        }

        /**
         * 同步的Post请求
         */
        public String postAsString(String url, Param[] params, Object tag) throws IOException {
            Response response = post(url, params, tag);
            return response.body().string();
        }

        public void postAsyn(String url, Map<String, String> params, final ResponseHandler callback) {
            postAsyn(url, params, callback, null);
        }

        public void postAsyn(String url, Map<String, String> params, final ResponseHandler callback, Object tag) {
            Param[] paramsArr = map2Params(params);
            postAsyn(url, paramsArr, callback, tag);
        }

        public void postAsyn(String url, Param[] params, final ResponseHandler callback) {
            postAsyn(url, params, callback, null);
        }

        /**
         * 异步的post请求
         */
        public void postAsyn(String url, Param[] params, final ResponseHandler callback, Object tag) {
            Request request = buildPostFormRequest(url, params, tag);
            deliveryResult(callback, request);
        }

        /**
         * 同步的Post请求:直接将bodyStr以写入请求体
         */
        public Response post(String url, String bodyStr) throws IOException {
            return post(url, bodyStr, null);
        }

        public Response post(String url, String bodyStr, Object tag) throws IOException {

            RequestBody body = RequestBody.create(MEDIA_TYPE_STRING, bodyStr);
            Request request = buildPostRequest(url, body, tag);
            Response response = mOkHttpClient.newCall(request).execute();
            return response;
        }

        /**
         * 同步的Post请求:直接将bodyFile以写入请求体
         */
        public Response post(String url, File bodyFile) throws IOException {
            return post(url, bodyFile, null);
        }

        public Response post(String url, File bodyFile, Object tag) throws IOException {
            RequestBody body = RequestBody.create(MEDIA_TYPE_STREAM, bodyFile);
            Request request = buildPostRequest(url, body, tag);
            Response response = mOkHttpClient.newCall(request).execute();
            return response;
        }

        /**
         * 同步的Post请求
         */
        public Response post(String url, byte[] bodyBytes) throws IOException {
            return post(url, bodyBytes, null);
        }

        public Response post(String url, byte[] bodyBytes, Object tag) throws IOException {
            RequestBody body = RequestBody.create(MEDIA_TYPE_STREAM, bodyBytes);
            Request request = buildPostRequest(url, body, tag);
            Response response = mOkHttpClient.newCall(request).execute();
            return response;
        }

        /**
         * 直接将bodyStr以写入请求体
         */
        public void postAsyn(String url, String bodyStr, final ResponseHandler callback) {
            postAsyn(url, bodyStr, callback, null);
        }

        public void postAsyn(String url, String bodyStr, final ResponseHandler callback, Object tag) {
            postAsynWithMediaType(url, bodyStr, MediaType.parse("application/json;charset=utf-8"), callback, tag);
        }

        /**
         * 直接将bodyBytes以写入请求体
         */
        public void postAsyn(String url, byte[] bodyBytes, final ResponseHandler callback) {
            postAsyn(url, bodyBytes, callback, null);
        }

        public void postAsyn(String url, byte[] bodyBytes, final ResponseHandler callback, Object tag) {
            postAsynWithMediaType(url, bodyBytes, MediaType.parse("application/octet-stream;charset=utf-8"), callback, tag);
        }

        /**
         * 直接将bodyFile以写入请求体
         */
        public void postAsyn(String url, File bodyFile, final ResponseHandler callback) {
            postAsyn(url, bodyFile, callback, null);
        }

        public void postAsyn(String url, File bodyFile, final ResponseHandler callback, Object tag) {
            postAsynWithMediaType(url, bodyFile, MediaType.parse("application/octet-stream;charset=utf-8"), callback, tag);
        }

        /**
         * 直接将bodyStr以写入请求体
         */
        public void postAsynWithMediaType(String url, String bodyStr, MediaType type, final ResponseHandler callback, Object tag) {
            RequestBody body = RequestBody.create(type, bodyStr);
            Request request = buildPostRequest(url, body, tag);
            deliveryResult(callback, request);
        }

        /**
         * 直接将bodyBytes以写入请求体
         */
        public void postAsynWithMediaType(String url, byte[] bodyBytes, MediaType type, final ResponseHandler callback, Object tag) {
            RequestBody body = RequestBody.create(type, bodyBytes);
            Request request = buildPostRequest(url, body, tag);
            deliveryResult(callback, request);
        }

        /**
         * 直接将bodyFile以写入请求体
         */
        public void postAsynWithMediaType(String url, File bodyFile, MediaType type, final ResponseHandler callback, Object tag) {
            RequestBody body = RequestBody.create(type, bodyFile);
            Request request = buildPostRequest(url, body, tag);
            deliveryResult(callback, request);
        }

    }

    private Request buildPostFormRequest(String url, Param[] params, Object tag) {
        if (params == null) {
            params = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        Request.Builder reqBuilder = new Request.Builder();
        reqBuilder.post(requestBody)
                .url(url);
        if (tag != null) {
            reqBuilder.tag(tag);
        }
        addHeader(reqBuilder);
        Request request = reqBuilder.build();
        return request;
    }

    /**
     * post构造Request的方法
     *
     * @param url
     * @param body
     * @return
     */
    private Request buildPostRequest(String url, RequestBody body, Object tag) {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(body);
        if (tag != null) {
            builder.tag(tag);
        }
        //
        addHeader(builder);
        Request request = builder.build();
        return request;
    }

    private Request buildGetRequest(String url, Object tag) {
        Request.Builder builder = new Request.Builder()
                .url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        addHeader(builder);
        return builder.build();
    }

    //====================GetDelegate=======================
    public class GetDelegate {

        /**
         * 通用的方法
         */
        public Response get(Request request) throws IOException {
            Call call = mOkHttpClient.newCall(request);
            Response execute = call.execute();
            return execute;
        }

        /**
         * 同步的Get请求
         */
        public Response get(String url) throws IOException {
            return get(url, null);
        }

        public Response get(String url, Object tag) throws IOException {
            final Request request = buildGetRequest(url, tag);
            return get(request);
        }


        /**
         * 同步的Get请求
         */
        public String getAsString(String url) throws IOException {
            return getAsString(url, null);
        }

        public String getAsString(String url, Object tag) throws IOException {
            Response execute = get(url, tag);
            return execute.body().string();
        }

        /**
         * 通用的方法
         */
        public void getAsyn(Request request, ResponseHandler callback) {
            deliveryResult(callback, request);
        }

        /**
         * 异步的get请求
         */
        public void getAsyn(String url, final ResponseHandler callback) {
            getAsyn(url, callback, null);
        }

        public void getAsyn(String url, final ResponseHandler callback, Object tag) {
            final Request request = buildGetRequest(url, tag);
            getAsyn(request, callback);
        }
    }


    //====================UploadDelegate=======================

    /**
     * 上传相关的模块
     */
    public class UploadDelegate {
        /**
         * 同步基于post的文件上传:上传单个文件
         */
        public Response post(String url, String fileKey, File file, Object tag) throws IOException {
            return post(url, new String[]{fileKey}, new File[]{file}, null, tag);
        }

        /**
         * 同步基于post的文件上传:上传多个文件以及携带key-value对：主方法
         */
        public Response post(String url, String[] fileKeys, File[] files, Param[] params, Object tag) throws IOException {
            Request request = buildMultipartFormRequest(url, files, fileKeys, params, tag);
            return mOkHttpClient.newCall(request).execute();
        }

        /**
         * 同步单文件上传
         */
        public Response post(String url, String fileKey, File file, Param[] params, Object tag) throws IOException {
            return post(url, new String[]{fileKey}, new File[]{file}, params, tag);
        }

        /**
         * 异步基于post的文件上传:主方法
         */
        public void postAsyn(String url, String[] fileKeys, File[] files, Param[] params, ResponseHandler callback, Object tag) {
            Request request = buildMultipartFormRequest(url, files, fileKeys, params, tag);
            deliveryResult(callback, request);
        }

        /**
         * 异步基于post的文件上传:单文件不带参数上传
         */
        public void postAsyn(String url, String fileKey, File file, ResponseHandler callback, Object tag) throws IOException {
            postAsyn(url, new String[]{fileKey}, new File[]{file}, null, callback, tag);
        }

        /**
         * 异步基于post的文件上传，单文件且携带其他form参数上传
         */
        public void postAsyn(String url, String fileKey, File file, Param[] params, ResponseHandler callback, Object tag) {
            postAsyn(url, new String[]{fileKey}, new File[]{file}, params, callback, tag);
        }

        private Request buildMultipartFormRequest(String url, File[] files,
                                                  String[] fileKeys, Param[] params, Object tag) {
            params = validateParam(params);
            //MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MediaType.parse("multipart/form-data"));
            for (Param param : params) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""),
                        RequestBody.create(null, param.value));
            }
            if (files != null) {
                RequestBody fileBody = null;
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    String fileName = file.getName();
                    fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                    //根据文件名设置contentType
                    builder.addPart(Headers.of("Content-Disposition",
                            "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + fileName + "\""),
                            fileBody);
                }
            }

            RequestBody requestBody = builder.build();
            Request.Builder builder1=new Request.Builder().url(url)
                                .post(requestBody)
                                .tag(tag);
            addHeader(builder1);
            return builder1.build();
        }
    //-------------------------2017.1.13添加--------------------------


        /**
         * 异步基于post的文件上传，单文件且携带其他form参数上传
         */
        public void postAsynHasProgress(String url, String fileKey, File file, Map<String, String> params,   ResponseHandler callback, Object tag) {
            Param[] paramsArr = map2Params(params);
            postAsynHasProgress(url, new String[]{fileKey}, new File[]{file}, paramsArr, callback,  tag);
        }
        /**
         * 异步基于post的文件上传，多文件且携带其他form参数上传
         */
        public void postAysnHasProgressWithMulti(String url, String[] fileKeys, File[] files, Map<String, String> params,  ResponseHandler callback, Object tag) {
            Param[] paramsArr = map2Params(params);
            postAsynHasProgress(url,fileKeys, files, paramsArr,  callback,  tag);
        }


        /**
         * 异步基于post的文件上传:主方法
         * 带进度
         */
        public void postAsynHasProgress(String url, String[] fileKeys, File[] files, Param[] params,  ResponseHandler callback, Object tag) {
            Request request = buildMultipartFormRequestHasProgress(url, files, fileKeys, params,tag);
            deliveryResult(callback, request);
        }


        /**
         * 带进度条的表单构建方法
         * @return
         */
        private Request buildMultipartFormRequestHasProgress(String url, File[] files,
                                                             String[] fileKeys, Param[] params , Object tag){
           /* params = validateParam(params);
            //MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MediaType.parse("multipart/form-data"));
            for (Param param : params) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""),
                        RequestBody.create(null, param.value));
            }
            if (files != null) {
                RequestBody fileBody = null;
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    String fileName = file.getName();
                    fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                    //根据文件名设置contentType
                    builder.addPart(Headers.of("Content-Disposition",
                            "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + fileName + "\""),
                            fileBody);
                }
            }

            RequestBody requestBody = builder.build();
            Request.Builder builder1=new Request.Builder().url(url)
                    .post(ProgressHelper.addProgressRequestListener(requestBody,uiProgressRequestListener))
                    .tag(tag);
            addHeader(builder1);
            return builder1.build();*/


            params = validateParam(params);
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (Param param : params) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""),
                        RequestBody.create(null, param.value));
            }

            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    String fileName = file.getName();
                    RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                    builder.addFormDataPart(fileKeys[i], fileName, fileBody);
                }
            }

            RequestBody requestBody = builder.build();
            Request.Builder builder1=new Request.Builder().url(url)
                    .post(requestBody)//ProgressHelper.addProgressRequestListener(requestBody,uiProgressRequestListener)   ProgressRequestBody 105报异常
                    .tag(tag);
            addHeader(builder1);
            return builder1.build();
        }

        //-------------------------2017.1.13添加--------------------------
    }

    /**
     * 下载相关的模块
     */
    public class DownloadDelegate {

        /**
         * 异步下载文件
         * @param url
         * @param destFileDir 本地文件存储的文件夹
         * @param resphandler
         */
        public void downloadAsyn(final String url, final String destFileDir, final ResponseHandler resphandler, Object tag) {
            Request.Builder reqBuilder=new Request.Builder()
                                .url(url)
                                .tag(tag);
            addHeader(reqBuilder);
            final Request request =reqBuilder.build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Request request1 = call.request();
                    resphandler.sendFailureMessage(ResponseConstant.RESULT_CODE_ERROR_UNKNOWN, request1.headers(), new Throwable("responseCode is " + ResponseConstant.RESULT_CODE_ERROR_UNKNOWN));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        is = response.body().byteStream();
                        File dir = new File(destFileDir);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        File file = new File(dir, getFileName(url));
                        fos = new FileOutputStream(file);
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }
                        fos.flush();
                        //如果下载文件成功，第一个参数为文件的绝对路径
                        //sendSuccessResultCallback(file.getAbsolutePath(), callback);
                        resphandler.sendSuccessMessage(response.code(), response.headers(), file.getAbsolutePath());
                    } catch (IOException e) {
                        resphandler.sendFailureMessage(ResponseConstant.RESULT_CODE_ERROR_UNKNOWN, response.headers(), e);
                    } finally {
                        try {
                            if (is != null) is.close();
                        } catch (IOException e) {
                        }
                        try {
                            if (fos != null) fos.close();
                        } catch (IOException e) {
                        }
                    }
                }

            });
        }


        public void downloadAsyn(final String url, final String destFileDir, final ResponseHandler callback) {
            downloadAsyn(url, destFileDir, callback, null);
        }
    }


    public static String encodedParams(Map<String,String> textParams) {
        StringBuilder encodedParams = new StringBuilder();
        for (Map.Entry<String, String> entry : textParams.entrySet()) {
            if (encodedParams.length() > 0) {
                encodedParams.append("&");
            }
            String key = entry.getKey();
            try {
                key = URLEncoder.encode(entry.getKey(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Log.w(TAG, e.getMessage(), e);
            }
            encodedParams.append(key);
            encodedParams.append('=');
            String v = entry.getValue() == null ? "" : entry.getValue();
            try {
                v = URLEncoder.encode(v, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Log.w(TAG, e.getMessage(), e);
            }
            encodedParams.append(v);
        }
        return encodedParams.toString();
    }


    private HashMap<String,String> headerMap=new HashMap<String,String>();

    void addHeader(Request.Builder requestBuidler) {
        if (headerMap != null && headerMap.size() > 0) {
            Iterator iter = headerMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) iter.next();
                String key = entry.getKey();
                String val = entry.getValue();
                requestBuidler.addHeader(key, val);
            }
        }
    }

    public void addHeader(HashMap<String, String> headerMap) {
        if (headerMap != null && headerMap.size() > 0) {
            this.headerMap.clear();
            this.headerMap.putAll(headerMap);
        }
    }

}
