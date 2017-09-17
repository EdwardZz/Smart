package com.example.acer.smart.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.smart.R;
import com.example.acer.smart.entity.MyUser;
import com.example.acer.smart.ui.LoginActivity;
import com.example.acer.smart.utils.L;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.fragment
*  文件名：UserFragment
*  创建者：朱勇杰
*  创建时间：2017/9/11
*  描述：  个人中心
*/
public class UserFragment extends Fragment implements View.OnClickListener {

    private Button btn_exit_user;
    private TextView edit_user;
    private EditText edit_username;
    private EditText edit_usersex;
    private EditText edit_userage;
    private EditText edit_userdesc;
    private Button btn_ok;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        btn_exit_user = view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);
        edit_user = view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);
        edit_username = view.findViewById(R.id.edit_username);
        edit_usersex = view.findViewById(R.id.edit_usersex);
        edit_userage = view.findViewById(R.id.edit_userage);
        edit_userdesc = view.findViewById(R.id.edit_userdesc);
        btn_ok = view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);

        //默认是不可点击的/不可修改

        setEnabled(false);

        //设置具体的值

        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        edit_username.setText(userInfo.getUsername());
        edit_usersex.setText(userInfo.isSex() ? "男" : "女");
        edit_userage.setText(userInfo.getAge());
        edit_userdesc.setText(userInfo.getDesc());


    }

    private void setEnabled(boolean is) {

        edit_username.setEnabled(is);
        edit_usersex.setEnabled(is);
        edit_userage.setEnabled(is);
        edit_userdesc.setEnabled(is);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //退出登录
            case R.id.btn_exit_user:


                //清除缓存用户对象
                MyUser.logOut();
                // 现在的currentUser是null了
                BmobUser currentUser = MyUser.getCurrentUser();
                //跳转页面
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            //编辑资料
            case R.id.edit_user:
                setEnabled(true);
                btn_ok.setVisibility(View.VISIBLE);
                break;
            //修改资料
            case R.id.btn_ok:

                //拿到输入框的值
                String username =edit_username.getText().toString().trim();
                String usersex=edit_usersex.getText().toString().trim();
                String userage=edit_userage.getText().toString().trim();
                String userdesc=edit_userdesc.getText().toString().trim();
                //判断是否为空
                if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(userage)&&!TextUtils.isEmpty(usersex))
                {
                    //更新属性
                    MyUser user= new MyUser();
                    user.setUsername(username);
                    user.setAge(userage);
                    L.i("性别:  "+usersex);
                    if(usersex.equals("男")){

                        user.setSex(true);
                    }
                    else {
                        user.setSex(false);
                    }
                    if(!userdesc.isEmpty()){
                        user.setDesc(userdesc);
                    }
                    else {
                        user.setDesc("这个人很懒，什么都没有留下......");
                    }
                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    user.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null)
                            {
                                //修改成功

                                setEnabled(false);
                                btn_ok.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getActivity(), "修改失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }else {
                    Toast.makeText(getActivity(), "输入框不能为空！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
