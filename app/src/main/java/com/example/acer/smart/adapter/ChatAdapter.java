package com.example.acer.smart.adapter;
/*
 * 项目名：    Smart
 * 包名：      com.example.acer.smart.adapter
 * 文件名：    smart
 * 创建者：    朱勇杰
 * 创建时间：   2017/10/13
 * 描述：      对话框适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acer.smart.R;
import com.example.acer.smart.entity.ChatListData;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    //左边的type
    public static final int VALUE_LEFT_TEXT = 1;
    //右边的type
    public static final int VALUE_RIGHT_TEXT = 2;

    private Context mContext;
    private LayoutInflater inflater;
    private ChatListData data;
    private List<ChatListData> mList;


    public ChatAdapter(Context mContext, List<ChatListData> mList) {


        this.mContext = mContext;
        this.mList = mList;
        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {

        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderLeft viewHolderLeft = null;
        ViewHolderRight viewHolderRight = null;
        //获取当前要显示的type 根据这个type来区分数据的加载
        int type = getItemViewType(i);
        if (view == null) {
            switch (type) {
                case VALUE_LEFT_TEXT:
                    viewHolderLeft = new ViewHolderLeft();
                    view = inflater.inflate(R.layout.left_item, null);
                    viewHolderLeft.tv_left_text = view.findViewById(R.id.tv_left_text);
                    view.setTag(viewHolderLeft);
                    break;
                case VALUE_RIGHT_TEXT:
                    viewHolderRight = new ViewHolderRight();
                    view = inflater.inflate(R.layout.right_item, null);
                    viewHolderRight.tv_right_text = view.findViewById(R.id.tv_right_text);
                    view.setTag(viewHolderRight);
                    break;

            }
        } else {
            switch (type) {
                case VALUE_LEFT_TEXT:
                    viewHolderLeft = (ViewHolderLeft) view.getTag();
                    break;
                case VALUE_RIGHT_TEXT:
                    viewHolderRight = (ViewHolderRight) view.getTag();
                    break;
            }
        }
        //赋值
        ChatListData data = mList.get(i);
        switch (type) {
            case VALUE_LEFT_TEXT:
                viewHolderLeft.tv_left_text.setText(data.getText());
                break;
            case VALUE_RIGHT_TEXT:
                viewHolderRight.tv_right_text.setText(data.getText());
                break;
        }

        return view;
    }

    //根据数据源的posion来返回要显示的item
    @Override
    public int getItemViewType(int position) {

        ChatListData data = mList.get(position);
        int type = data.getType();
        return type;
    }

    //返回所有的Layout数量
    @Override
    public int getViewTypeCount() {
        return 3;//mList.size+1
    }

    //左边的文本
    class ViewHolderLeft {
        private TextView tv_left_text;
    }

    //右边的文本
    class ViewHolderRight {
        private TextView tv_right_text;
    }
}
