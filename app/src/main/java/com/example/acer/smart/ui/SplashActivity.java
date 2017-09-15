package com.example.acer.smart.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.acer.smart.R;
import com.example.acer.smart.utils.ShareUtils;
import com.example.acer.smart.utils.StaticClass;
import com.example.acer.smart.utils.UtilTools;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.ui
*  文件名：SplashActivity
*  创建者：朱勇杰
*  创建时间：2017/9/13
*  描述：  闪屏界面
*/
public class SplashActivity extends AppCompatActivity {

    /**
     * 1.延时2秒
     * 2.判断程序是否第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     */
    private TextView tv_splash;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否第一次运行
                    if (isFirst()) {

                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {

                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {


        tv_splash = (TextView) findViewById(R.id.tv_splash);
        //设置字体
        UtilTools.setFont(this, tv_splash);
        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
    }

    //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST, true);
        if (isFirst) {
            //是第一次运行
            ShareUtils.putBoolean(this, StaticClass.SHARE_IS_FIRST, false);
            return true;
        } else {

            return false;
        }

    }

    //禁止返回键
    @Override
    public void onBackPressed() {

    }
}
