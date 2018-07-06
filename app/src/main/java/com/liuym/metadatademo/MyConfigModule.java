package com.liuym.metadatademo;

import com.liuym.basemodule.BaseConfig;
import com.liuym.basemodule.ConfigModule;

/**
 * ConfigModule的实现类，具体的配置实现都在这里
 */
public class MyConfigModule implements ConfigModule {
    @Override
    public void applyOptions(BaseConfig.Builder builder) {
        builder.setBaseUrl("hehehehehehehe");
    }
}
