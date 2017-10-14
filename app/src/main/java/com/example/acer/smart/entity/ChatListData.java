package com.example.acer.smart.entity;
/*
 * 项目名：    Smart
 * 包名：      com.example.acer.smart.entity
 * 文件名：    ChatListData
 * 创建者：    朱勇杰
 * 创建时间：   2017/10/14
 * 描述：      对话框的实体类
 */

public class ChatListData {

    //区分左右
    private int type;
    //文本
    private String text;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
