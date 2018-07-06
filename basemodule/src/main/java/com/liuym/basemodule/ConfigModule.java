package com.liuym.basemodule;

/**
 * 配置组件接口
 */
public interface ConfigModule {
    /**
     * 通过Builder来进行具体配置
     *
     * @param builder
     */
    void applyOptions(BaseConfig.Builder builder);
}
