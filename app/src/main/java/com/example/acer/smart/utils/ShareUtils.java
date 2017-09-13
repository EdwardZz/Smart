package com.example.acer.smart.utils;

import android.content.Context;
import android.content.SharedPreferences;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.utils
*  文件名：ShareUtils
*  创建者：朱勇杰
*  创建时间：2017/9/12
*  描述：  SharePreference工具类的封装
*/
public class ShareUtils {

    //标记字
    public static final String NAME = "config";

    //键  值
    public static void putString(Context mContext, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();

    }

    //键  默认值
    public static String getString(Context mContext, String key, String defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);

        return sp.getString(key, defValue);
    }

    //键  值
    public static void putInt(Context mContext, String key, int value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();

    }

    //键  默认值
    public static int getInt(Context mContext, String key, int defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);

        return sp.getInt(key, defValue);
    }

    //键  值
    public static void putBoolean(Context mContext, String key, Boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();

    }

    //键  默认值
    public static Boolean getBoolean(Context mContext, String key, Boolean defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);

        return sp.getBoolean(key, defValue);
    }

    //删除单个元素
    public static void deleShare(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //删除全部
    public static void deleAll(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
