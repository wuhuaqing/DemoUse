package com.whq.baselibrary.normalhttp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ResponseHandler {
    protected static final int MSG_SUCCESS = 0;
    protected static final int MSG_FAILURE = 1;
    protected static final int MSG_START = 2;
    protected static final int MSG_FINISH = 3;
    protected static final int MSG_PROGRESS = 4;
    private Handler handler;
    private Looper looper = null;

    public ResponseHandler() {
        this(null);
    }

    public ResponseHandler(Looper looper) {
        this.looper = (looper == null ? Looper.getMainLooper() : looper);
        handler = new ResponderHandler(this, this.looper);
    }

    public void onStart() {
        // Do nothing by default
    }

    public void onProgress(long bytesReceived, long totalBytes) {
        // Do nothing by default
    }

    public void onFinish() {
        // Do nothing by default
    }

    public void onSuccess(HttpResponse response){
        onSuccess(response.getResponseCode(),response.getHeaders(),response.getResponse());
    }

    public void onFailure(HttpResponse response){
        onFailure(response.getResponseCode(),response.getHeaders(),response.getResponse());
    }

    public abstract void onSuccess(int statusCode, okhttp3.Headers headers, String responseString);


    public abstract void onFailure(int statusCode, okhttp3.Headers headers, String throwable);


    public void processResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        Map<String, String> responseHeaders = getHttpResponseHeader(connection);
        // Response
        long contentLength = connection.getContentLength();
        // 'Successful' response codes will be in interval [200,300)
        if (responseCode >= 200 && responseCode < 300) {
            byte[] responseContent = readFrom(connection.getInputStream(), contentLength);
            sendSuccessMessage(responseCode, responseHeaders, responseContent);
        } else {
            sendFailureMessage(responseCode, responseHeaders, new Throwable("responseCode is " +responseCode));
        }
    }

    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_SUCCESS:
                onSuccess((HttpResponse) msg.obj);
                break;
            case MSG_FAILURE:
                onFailure((HttpResponse)msg.obj);
                break;
            case MSG_START:
                onStart();
                break;
            case MSG_FINISH:
                onFinish();
                break;
            case MSG_PROGRESS:
                Object[] obj = (Object[]) msg.obj;
                if (obj != null && obj.length >= 2) {
                    onProgress((Long) obj[0], (Long) obj[1]);
                }
                break;
        }
    }

    byte[] readFrom(InputStream inputStream, long length) throws IOException {
        if (inputStream == null) {
            return new byte[0];
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.flush();
        os.close();
        return os.toByteArray();
    }

    protected final void sendProgressMessage(long bytesWritten, long bytesTotal) {
        handler.sendMessage(obtainMessage(MSG_PROGRESS, new Object[]{bytesWritten, bytesTotal}));
    }

    @Deprecated
    public final void sendSuccessMessage(int responseCode, Map<String, String> header, byte[] responseBytes) {
        HttpResponse response = new HttpResponse(responseCode, responseBytes, header);
        handler.sendMessage(obtainMessage(MSG_SUCCESS, response));
    }

    public final void sendSuccessMessage(int responseCode, okhttp3.Headers headers, String responseBody) {
        HttpResponse response = new HttpResponse(responseCode, responseBody, headers);
        handler.sendMessage(obtainMessage(MSG_SUCCESS, response));
    }

    protected final void sendFailureMessage(int responseCode, Throwable throwable) {
        HttpResponse response = new HttpResponse();
        response.setResponseCode(responseCode);
        response.setResponse(throwable.getMessage());
        handler.sendMessage(obtainMessage(MSG_FAILURE, response));
    }

    public final void sendFailureMessage(int responseCode, okhttp3.Headers headers, Throwable throwable) {
           HttpResponse response = new HttpResponse(responseCode, throwable.getMessage(), headers);
           handler.sendMessage(obtainMessage(MSG_FAILURE, response));
       }


    @Deprecated
    public final void sendFailureMessage(int responseCode, Map<String, String> header, Throwable throwable) {
        HttpResponse response = new HttpResponse(responseCode, throwable.getMessage(), header);
        handler.sendMessage(obtainMessage(MSG_FAILURE, response));
    }

    protected final void sendStartMessage() {
        handler.sendMessage(obtainMessage(MSG_START, null));
    }

    protected final void sendFinishMessage() {
        handler.sendMessage(obtainMessage(MSG_FINISH, null));
    }

    protected Message obtainMessage(int responseMessageId, HttpResponse responseMessageData) {
        return Message.obtain(handler, responseMessageId, responseMessageData);
    }

    protected Message obtainMessage(int responseMessageId, Object objects) {
        return Message.obtain(handler, responseMessageId, objects);
    }

    /**
     * Avoid leaks by using a non-anonymous handler class.
     */
    private static class ResponderHandler extends Handler {
        private final ResponseHandler mResponder;

        ResponderHandler(ResponseHandler mResponder, Looper looper) {
            super(looper);
            this.mResponder = mResponder;
        }

        @Override
        public void handleMessage(Message msg) {
            mResponder.handleMessage(msg);
        }
    }

    private Map<String, String> getHttpResponseHeader(HttpURLConnection http) {
        Map<String, String> header = new LinkedHashMap<String, String>();
        for (int i = 0; ; i++) {
            String mine = http.getHeaderField(i);
            if (mine == null)
                break;
            header.put(http.getHeaderFieldKey(i), mine);
        }
        return header;
    }

    static String getEncode(InputStream inputStream) {
        String code = "gb2312";
        try {
            byte[] head = new byte[3];
            inputStream.read(head);
            if (head[0] == -17 && head[1] == -69 && head[2] == -65)
                code = "UTF-8";
            if (head[0] == -1 && head[1] == -2)
                code = "UTF-16";
            if (head[0] == -2 && head[1] == -1)
                code = "Unicode";

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }
}
