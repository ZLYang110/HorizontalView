package com.zlyandroid.horizontalview.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;


/**
 * 缓存
 * author: zhangliyang
 * date: 2018/2/7
 */

public class PreUtils {


    private final SharedPreferences sp;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }
    private PreUtils(@NonNull String name) {
        sp = getSharedPreferences(name);
    }
    private PreUtils() {
        sp = getDefaultSharedPreferences();
    }

    public static PreUtils newInstance(String name) {
        return new PreUtils(name);
    }

    public static PreUtils getInstance() {
        return new PreUtils();
    }


    private static SharedPreferences getSharedPreferences(@NonNull String name) {
        return mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getDefaultSharedPreferences() {
        return  PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    private SharedPreferences.Editor getEditor() {
        return sp.edit();
    }

    /**
     * 存
     *
     * @param keyword
     * @param value
     */
    public <T> PreUtils save(String keyword, T value) {
        SharedPreferences.Editor editor = getEditor();
        if (value == null) {
            editor.remove(keyword).apply();
        } else if (value instanceof String) {
            editor.putString(keyword, (String) value).apply();
        } else if (value instanceof Integer) {
            editor.putInt(keyword, (Integer) value).apply();
        } else if (value instanceof Boolean) {
            editor.putBoolean(keyword, (Boolean) value).apply();
        } else if (value instanceof Long) {
            editor.putLong(keyword, (Long) value).apply();
        } else if (value instanceof Float) {
            editor.putFloat(keyword, (Float) value).apply();
        }
        return this;
    }

    /**
     * 取
     *
     * @param keyword
     * @param defValue
     * @return
     */
    public <T> T get(String keyword, T defValue) {
        T value;
        if (defValue instanceof String) {
            String s = sp.getString(keyword, (String) defValue);
            value = (T) s;
        } else if (defValue instanceof Integer) {
            Integer i = sp.getInt(keyword, (Integer) defValue);
            value = (T) i;
        } else if (defValue instanceof Long) {
            Long l = sp.getLong(keyword, (Long) defValue);
            value = (T) l;
        } else if (defValue instanceof Float) {
            Float f = sp.getFloat(keyword, (Float) defValue);
            value = (T) f;
        } else if (defValue instanceof Boolean) {
            Boolean b = sp.getBoolean(keyword, (Boolean) defValue);
            value = (T) b;
        } else {
            value = defValue;
        }
        return value;
    }


    public PreUtils clear() {
        getEditor().clear().apply();
        return this;
    }

    public PreUtils remove(String keyword){
        getEditor().remove(keyword).apply();
        return this;
    }



}
