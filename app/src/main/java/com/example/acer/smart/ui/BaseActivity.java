package com.example.acer.smart.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.ui
*  文件名：BaseActivity
*  创建者：朱勇杰
*  创建时间：2017/9/11
*  描述：  BaseActivity
*/
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //菜单栏操作


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

