package com.liuym.basemodule;

import android.util.Log;

public class BaseConfig {
    private static String mBaseUrl = "";

    public static class Builder {
        public void setBaseUrl(String baseUrl) {
            mBaseUrl = baseUrl;
            Log.i("liuym", "setBaseUrl: " + mBaseUrl);
        }
    }
}
