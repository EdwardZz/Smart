package com.example.acer.smart.utils;

import android.util.Log;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.utils
*  文件名：L
*  创建者：朱勇杰
*  创建时间：2017/9/12
*  描述：  封装的工具类Log
*/
public class L {
    //开关
    public static final boolean DEBUG = true;
    //TAG
    public static final String TAG = "Smart";

    //这里我们使用其中的四个等级DIWE
    public static void d(String text) {
        if (DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void i(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text);
        }
    }

    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text);
        }
    }
}
