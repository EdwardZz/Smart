package com.example.acer.smart.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.acer.smart.R;
import com.example.acer.smart.entity.MyUser;
import com.example.acer.smart.utils.L;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.ui
*  文件名：RegisteredActivity
*  创建者：朱勇杰
*  创建时间：2017/9/14
*  描述：  注册界面
*/
public class RegisteredActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_user;
    private EditText et_age;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_desc;
    private RadioGroup mRadioGroup;
    private EditText et_email;
    private Button btnRegister;
    //判断性别的变量
    private boolean isGender = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        et_user = (EditText) findViewById(R.id.et_user);
        et_age = (EditText) findViewById(R.id.et_age);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        et_desc = (EditText) findViewById(R.id.et_dec);
        et_email = (EditText) findViewById(R.id.et_email);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        mRadioGroup= (RadioGroup) findViewById(R.id.mRadioGroup);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnRegister:

                //获取输入框中的值
                String name = et_user.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                //判断是否为空
                //判断两次输入是否一致
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) &&
                        !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(email))
                    if (pass.equals(password)) {
                        //判断性别


                        L.i("性别2" + isGender);
                        mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                                if (i == R.id.rb_boy) {
                                    isGender = true;
                                } else if (i == R.id.rb_girl) {
                                    isGender = false;

                                }
                                L.i("性别1" + isGender);
                            }
                        });


                        //判断简介是否为空
                        if (TextUtils.isEmpty(desc)) {
                            desc = "这个人很懒，什么都没留下......";
                        }
                        //注册
                        L.i("测试001");
                        MyUser user = new MyUser();
                        user.setUsername(name);
                        user.setDesc(desc);
                        user.setAge(age);
                        user.setPassword(pass);
                        user.setEmail(email);
                        user.setSex(isGender);
                        L.i("性别" + isGender);
                        L.i("测试002");
                        user.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if (e == null) {

                                    Toast.makeText(RegisteredActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {

                                    Toast.makeText(RegisteredActivity.this, "注册失败" + e.toString(), Toast.LENGTH_SHORT).show();

                                }
                            }

                        });
                    } else {

                        Toast.makeText(this, "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                else {
                    Toast.makeText(this, "输入框为空", Toast.LENGTH_SHORT).show();
                }


                break;

        }
    }
}
