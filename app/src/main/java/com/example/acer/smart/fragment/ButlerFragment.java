package com.example.acer.smart.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.acer.smart.R;
import com.example.acer.smart.adapter.ChatAdapter;
import com.example.acer.smart.entity.ChatListData;
import com.example.acer.smart.utils.L;
import com.example.acer.smart.utils.StaticClass;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONException;
import org.json.JSONObject;

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
    private Button btn_send;
    private EditText et_text;
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

        btn_send=view.findViewById(R.id.btn_send);
        et_text=view.findViewById(R.id.et_text);
        btn_send.setOnClickListener(this);
        mListView=view.findViewById(R.id.mChatListView);

        adapter=new ChatAdapter(getActivity(),mList);
        mListView.setAdapter(adapter);

        addLeftItem("你好，我是小管家");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_send:
                /*逻辑
                 * 1.获得接受数据
                 * 2.判断输入是否为空
                 * 3.判断输入是否过长
                 * 4.清空当前输入框
                 * 5.添加输入的内容到right item
                 * 6.发送请求给机器人
                 * 7.拿到返回数据并将其体现在left
                 */
                String text=et_text.getText().toString().trim();
                if(!TextUtils.isEmpty(text)){
                    if(text.length()>30){
                        Toast.makeText(getActivity(), "输入长度超出限制", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //清空输入框
                        et_text.setText("");
                        //设置right item
                        addRightItem(text);
                        //向网络发送请求
                        String url="http://op.juhe.cn/robot/index?info="+text+"&key="+ StaticClass.CHAT_LIST_KEY;
                        RxVolley.get(url, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                //Toast.makeText(getActivity(), "json: "+t, Toast.LENGTH_SHORT).show();
                                L.i("json "+t);
                                paringJson(t);

                            }


                        });

                    }
                }
                else{
                    Toast.makeText(getActivity(), "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //解析json
    private void paringJson(String t) {

        try{
            JSONObject jsonObject=new JSONObject(t);
            JSONObject jsonResult=jsonObject.getJSONObject("result");
            //获取返回值
            String text=jsonResult.getString("text");
            //设置right item
            addLeftItem(text);
        }
        catch (JSONException e) {
            e.printStackTrace();
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
