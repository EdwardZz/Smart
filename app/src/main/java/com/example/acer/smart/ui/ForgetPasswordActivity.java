package com.example.acer.smart.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.smart.R;
import com.example.acer.smart.entity.MyUser;
import com.example.acer.smart.utils.L;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.ui
*  文件名：ForgetPasswordActivity
*  创建者：朱勇杰
*  创建时间：2017/9/15
*  描述：  忘记密码界面
*/
public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_email;
    private Button  btn_forget_password;

    private EditText et_now;
    private EditText et_new;
    private EditText et_new_password;

    private Button btn_update_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        initView();
    }

    private void initView() {

        et_email= (EditText) findViewById(R.id.et_email);
        btn_forget_password= (Button) findViewById(R.id.btn_forget_password);
        btn_forget_password.setOnClickListener(this);

        et_now= (EditText) findViewById(R.id.et_now);
        et_new= (EditText) findViewById(R.id.et_new);
        et_new_password= (EditText) findViewById(R.id.et_new_password);

        btn_update_password= (Button) findViewById(R.id.btn_update_password);
        btn_update_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_forget_password:

                //获取邮箱
                final String email =et_email.getText().toString().trim();
                //判断是否为空
                if(!TextUtils.isEmpty(email))
                {
                    //发送邮件
                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                            //判断结果
                            if(e==null)
                            {
                                Toast.makeText(ForgetPasswordActivity.this, "邮件已经发送至:"+email, Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                Toast.makeText(ForgetPasswordActivity.this, "发送失败"+e.toString(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
                else {
                    Toast.makeText(this, "输入框不允许为空", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_update_password:
                //获取输入框内的值
                final String now=et_now.getText().toString().trim();
                final String newpass=et_new.getText().toString().trim();
                String newpassword=et_new_password.getText().toString().trim();

                //判断输入框内是否为空

                if(!TextUtils.isEmpty(now)&&!TextUtils.isEmpty(newpass)&&!TextUtils.isEmpty(newpassword))
                {
                    //判断密码是否一致
                    if(newpass.equals(newpassword))
                    {
                       //重置密码
                        MyUser.updateCurrentUserPassword(now, newpass, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {


                                L.i("旧密码"+now);

                                L.i("新密码"+newpass);

                                if(e==null)
                                {
                                    Toast.makeText(ForgetPasswordActivity.this, "重置密码成功！", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    Toast.makeText(ForgetPasswordActivity.this, "重置密码失败"+e.toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }else {
                        Toast.makeText(this, "两次输入的密码不一致！", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(this, "输入框不允许为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
