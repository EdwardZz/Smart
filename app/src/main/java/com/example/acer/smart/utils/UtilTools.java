package com.example.acer.smart.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.utils
*  文件名：UtilTools
*  创建者：朱勇杰
*  创建时间：2017/9/11
*  描述：  工具统一类
*/
public class UtilTools {


    //设置字体
    public static void setFont(Context mContext, TextView mTextView) {

        Typeface frontType = Typeface.createFromAsset(mContext.getAssets(), "Fonts/font1.ttf");
        mTextView.setTypeface(frontType);
    }

}

