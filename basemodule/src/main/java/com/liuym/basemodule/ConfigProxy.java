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

    /**
     * 进行初始化
     *
     * @param context
     * @param builder
     */
    private static void initAll(Context context, BaseConfig.Builder builder) {
        Context applicationContext = context.getApplicationContext();
        //通过meta-data标签获取相应的配置类
        List<ConfigModule> manifestModules = new ManifestParser(applicationContext).parse();
        //执行更改具体配置的操作
        for (ConfigModule module : manifestModules) {
            module.applyOptions(builder);
        }
        mConfigProxy = new ConfigProxy();
    }
}
