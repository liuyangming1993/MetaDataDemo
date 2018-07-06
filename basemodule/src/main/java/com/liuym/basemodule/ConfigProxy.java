package com.liuym.basemodule;

import android.content.Context;

import java.util.List;

public class ConfigProxy {
    private static ConfigProxy mConfigProxy;

    public static ConfigProxy get(Context context) {
        if (mConfigProxy == null) {
            init(context);
        }
        return mConfigProxy;
    }

    private static void init(Context context) {
        initAll(context, new BaseConfig.Builder());
    }

    private static void initAll(Context context, BaseConfig.Builder builder) {
        Context applicationContext = context.getApplicationContext();
        List<ConfigModule> manifestModules = new ManifestParser(applicationContext).parse();
        for (ConfigModule module : manifestModules) {
            module.applyOptions(builder);
        }
        mConfigProxy = new ConfigProxy();
    }
}
