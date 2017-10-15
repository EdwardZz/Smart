package com.example.acer.smart.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.acer.smart.R;
import com.example.acer.smart.adapter.ChatAdapter;
import com.example.acer.smart.entity.ChatListData;

import java.util.ArrayList;
import java.util.List;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.fragment
*  文件名：ButlerFragment
*  创建者：朱勇杰
*  创建时间：2017/9/11
*  描述：  管家服务
*/
public class ButlerFragment extends Fragment implements View.OnClickListener{

    private ListView mListView;
    private Button btn_left,btn_right;
    private ChatAdapter adapter;
    private List<ChatListData> mList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_butler,null);
        findView(view);
        return view;
    }
    //初始化View
    private void findView(View view) {

        mListView=view.findViewById(R.id.mChatListView);
        btn_left=view.findViewById(R.id.btn_left);
        btn_left.setOnClickListener(this);
        btn_right=view.findViewById(R.id.btn_right);
        btn_right.setOnClickListener(this);
        //设置适配器
        adapter=new ChatAdapter(getActivity(),mList);
        mListView.setAdapter(adapter);

        addLeftItem("你好，我是小管家");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_left:
                addLeftItem("左边");
                break;
            case R.id.btn_right:
                addRightItem("右边");
                break;
        }
    }
    //添加左边文本
    private void addLeftItem(String text)
    {
        ChatListData data =new ChatListData();
        data.setType(ChatAdapter.VALUE_LEFT_TEXT);
        data.setText(text);
        mList.add(data);
        //通知adapter刷新
        adapter.notifyDataSetChanged();
        //滚动到底部
        mListView.setSelection(mListView.getBottom());
    }
    //添加右边文本
    private void addRightItem(String text){
        ChatListData data =new ChatListData();
        data.setType(ChatAdapter.VALUE_RIGHT_TEXT);
        data.setText(text);
        mList.add(data);
        //通知adapter刷新
        adapter.notifyDataSetChanged();
        //滚动到底部
        mListView.setSelection(mListView.getBottom());
    }
}
