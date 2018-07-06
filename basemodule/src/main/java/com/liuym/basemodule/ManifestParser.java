package com.liuym.basemodule;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析Manifest并获取相应的ConfigModule
 */
public class ManifestParser {
    private static final String TAG = "ManifestParser";
    private String CONFIG_MODULE_VALUE = "ConfigModule";
    private Context mContext;

    public ManifestParser(Context context) {
        mContext = context;
    }

    public List<ConfigModule> parse() {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Loading Glide modules");
        }
        List<ConfigModule> modules = new ArrayList<>();
        try {
            ApplicationInfo appInfo = mContext.getPackageManager()
                    .getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData == null) {
                if (Log.isLoggable(TAG, Log.DEBUG)) {
                    Log.d(TAG, "Got null app info metadata");
                }
                return modules;
            }
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                Log.v(TAG, "Got app info metadata: " + appInfo.metaData);
            }
            for (String key : appInfo.metaData.keySet()) {
                if (CONFIG_MODULE_VALUE.equals(appInfo.metaData.get(key))) {
                    modules.add(parseModule(key));
                    if (Log.isLoggable(TAG, Log.DEBUG)) {
                        Log.d(TAG, "Loaded Glide module: " + key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Finished loading Glide modules");
        }
        return modules;
    }

    private static ConfigModule parseModule(String className) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e);
        }

        Object module = null;
        try {
            module = clazz.getDeclaredConstructor().newInstance();
            // These can't be combined until API minimum is 19.
        } catch (InstantiationException e) {
            throwInstantiateGlideModuleException(clazz, e);
        } catch (IllegalAccessException e) {
            throwInstantiateGlideModuleException(clazz, e);
        } catch (NoSuchMethodException e) {
            throwInstantiateGlideModuleException(clazz, e);
        } catch (InvocationTargetException e) {
            throwInstantiateGlideModuleException(clazz, e);
        }

        if (!(module instanceof ConfigModule)) {
            throw new RuntimeException("Expected instanceof ConfigModule, but found: " + module);
        }
        return (ConfigModule) module;
    }

    private static void throwInstantiateGlideModuleException(Class<?> clazz, Exception e) {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + clazz, e);
    }
}
