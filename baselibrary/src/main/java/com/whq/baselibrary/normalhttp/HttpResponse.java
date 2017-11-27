package com.whq.baselibrary.normalhttp;

import java.util.Map;

import okhttp3.Headers;

/**
 * Created by THINK on 2016/9/29.
 * 响应消息体
 */
public class HttpResponse {

    public HttpResponse(){
    }

    @Deprecated
    public HttpResponse(int responseCode, String response, Map<String, String> mapHeader) {
        this.response = response;
        this.responseCode = responseCode;
        this.header = mapHeader;
    }

    public HttpResponse(int responseCode, String response, okhttp3.Headers mapHeader) {
        this.response = response;
        this.responseCode = responseCode;
        this.headers = mapHeader;
    }

    @Deprecated
    public HttpResponse(int responseCode, byte[] responseBtye, Map<String, String> mapHeader) {
        this.responseBtye = responseBtye;
        this.responseCode = responseCode;
        this.header = mapHeader;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public byte[] getResponseBtye() {
        return responseBtye;
    }

    public void setResponseBtye(byte[] responseBtye) {
        this.responseBtye = responseBtye;
    }

    public long getContentLeght() {
        return contentLeght;
    }

    public void setContentLeght(long contentLeght) {
        this.contentLeght = contentLeght;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    private int responseCode;
    private String response;
    @Deprecated
    private Map<String, String> header;
    @Deprecated
    private byte[] responseBtye;

    private okhttp3.Headers headers;

    private  long contentLeght;


}
