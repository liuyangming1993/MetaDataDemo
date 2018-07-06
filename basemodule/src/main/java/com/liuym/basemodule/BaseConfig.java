package com.liuym.basemodule;

import android.util.Log;

public class BaseConfig {
    private static String mBaseUrl = "";

    /**
     * 提供配置的Builder给外界使用
     */
    public static class Builder {
        public void setBaseUrl(String baseUrl) {
            mBaseUrl = baseUrl;
            Log.i("liuym", "setBaseUrl: " + mBaseUrl);
        }
    }
}
