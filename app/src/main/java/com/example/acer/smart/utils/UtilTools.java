package com.example.acer.smart.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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
    //保存图片到ShareUtil下
    public static void putImageToShare(Context context,ImageView imgView){



        BitmapDrawable drawable= (BitmapDrawable) imgView.getDrawable();
        Bitmap bitmap=drawable.getBitmap();
        //将Bitmap压缩成字节数组输出流
        ByteArrayOutputStream byStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,80,byStream);
        //转化为字符串
        byte[] byteArray=byStream.toByteArray();
        String imgString=new String(Base64.encodeToString(byteArray,Base64.DEFAULT));
        //将Sring保存
        ShareUtils.putString(context,"image_title",imgString);
    }
    //读取图片
    public  static void getImageToShare(Context context,ImageView imageView){
        //拿到String
        String imgString=ShareUtils.getString(context,"image_title","");
        if(!imgString.equals("")){
            //转化
            byte[] byteArray= Base64.decode(imgString,Base64.DEFAULT);
            ByteArrayInputStream byStream=new ByteArrayInputStream(byteArray);
            //生成Bitmap
            Bitmap bitmap= BitmapFactory.decodeStream(byStream);
            imageView.setImageBitmap(bitmap);


        }
    }


}

