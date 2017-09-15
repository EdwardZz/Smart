package com.example.acer.smart.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.smart.MainActivity;
import com.example.acer.smart.R;
import com.example.acer.smart.entity.MyUser;
import com.example.acer.smart.utils.ShareUtils;
import com.example.acer.smart.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.ui
*  文件名：LoginActivity
*  创建者：朱勇杰
*  创建时间：2017/9/14
*  描述：  登陆
*/
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    //注册按钮
    private Button btn_registered;

    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;
    private CheckBox keep_password;
    private TextView tv_forget;

    private CustomDialog customdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {

        btn_registered = (Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        tv_forget= (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);

        //customdialog=new CustomDialog(this,100,100,R.layout.diaolog_loding,R.style.Theme_dialog, Gravity.CENTER);

        //屏幕外点击无效
        //customdialog.setCancelable(false);


        //设置选中状态
        boolean isCheck = ShareUtils.getBoolean(this, "keeppassword", false);
        keep_password.setChecked(isCheck);
        if (isCheck) {

            et_name.setText(ShareUtils.getString(this, "name", ""));
            et_password.setText(ShareUtils.getString(this, "password", ""));

        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.btnLogin:

                //获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    //登陆
                    //customdialog.show();
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);

                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            //判断结果
                            //customdialog.dismiss();
                            if (e == null) {

                                //判断邮箱是否进行验证
                                if (user.getEmailVerified()) {
                                    //跳转

                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "请前往注册邮箱进行验证后登陆！", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "登陆失败，请重新输入" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框为空！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_forget:

                startActivity(new Intent(this,ForgetPasswordActivity.class));

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存记住密码框的状态
        ShareUtils.putBoolean(this, "keeppassword", keep_password.isChecked());
        //如果被选中
        if (keep_password.isChecked()) {
            //保存用户名和密码
            ShareUtils.putString(this, "name", et_name.getText().toString().trim());
            ShareUtils.putString(this, "password", et_password.getText().toString().trim());
        } else {

            //否则清空密码
            ShareUtils.deleShare(this, "name");
            ShareUtils.deleShare(this, "password");

        }
    }
}
