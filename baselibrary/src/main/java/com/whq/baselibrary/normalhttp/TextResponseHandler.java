package com.whq.baselibrary.normalhttp;

import java.io.UnsupportedEncodingException;

public abstract class TextResponseHandler extends ResponseHandler {

    public static final String UTF8_BOM = "\uFEFF";

    //public abstract void onSuccess(int statusCode,  headers, String responseString);

    //public void onSuccess(int statusCode, Map<String, String> headers, byte[] responseString) {
    //    onSuccess(statusCode,headers,getString(responseString));
    //}

    public static String getString(byte[] stringBytes) {
        try {
            String toReturn = (stringBytes == null) ? null : new String(stringBytes, "UTF-8");
            if (toReturn != null && toReturn.startsWith(UTF8_BOM)) {
                return toReturn.substring(1);
            }
            return toReturn;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}
