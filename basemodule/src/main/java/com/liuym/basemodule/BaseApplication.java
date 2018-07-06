package com.liuym.basemodule;

import android.app.Application;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ConfigProxy.get(this);
    }
}
