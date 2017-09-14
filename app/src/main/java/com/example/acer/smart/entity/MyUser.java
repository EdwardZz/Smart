package com.example.acer.smart.entity;

import cn.bmob.v3.BmobUser;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.entity
*  文件名：MyUser
*  创建者：朱勇杰
*  创建时间：2017/9/14
*  描述：  用户属性
*/
public class MyUser extends BmobUser {

    private String age;
    private boolean sex;
    private String desc;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
