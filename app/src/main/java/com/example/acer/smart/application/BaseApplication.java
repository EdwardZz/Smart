package com.example.acer.smart.application;

import android.app.Application;

import com.example.acer.smart.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

/**
 * Created by acer on 2017/9/11.
 */

public class BaseApplication extends Application {


    //创建
    @Override
    public void onCreate() {

        super.onCreate();
        //Bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, false);
        //Bmob默认初始化
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);
    }
}
