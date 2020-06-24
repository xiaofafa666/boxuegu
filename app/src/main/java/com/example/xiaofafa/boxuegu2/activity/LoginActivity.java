package com.example.xiaofafa.boxuegu2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofafa.boxuegu2.R;
import com.example.xiaofafa.boxuegu2.udil.Md5Utils;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_back;
    private EditText et_psw  ;
    private Button   btn_login  ;
    private TextView tv_register;
    private TextView tv_find_psw;
    private TextView tv_main_title;
    private EditText et_user_name;
    private String userName;
    private String psw;
    private String spPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        RelativeLayout rl_title_bar  =  (RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
         tv_back  =  (TextView)findViewById(R.id.tv_back);

         tv_main_title  =  (TextView)findViewById(R.id.tv_main_title);
         tv_main_title.setText("登录");
         et_user_name = (EditText)findViewById(R.id.et_user_name);
         et_psw  =  (EditText)findViewById(R.id.et_psw);
         btn_login  =(Button)  findViewById(R.id.btn_login);
         tv_register  = (TextView)findViewById(R.id.tv_register);
         tv_find_psw  =   (TextView)findViewById(R.id.tv_find_psw);

         tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
         btn_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                  userName = et_user_name.getText().toString().trim();
                  psw = et_psw.getText().toString().trim();
                  String md5Psw = Md5Utils.md5(psw);
                  spPsw =readPsw(userName);
                  if(TextUtils.isEmpty(userName)){
                      Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();;
                      return;
                  }else if (TextUtils.isEmpty(psw)){
                      Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();;
                      return;
                  }else if (TextUtils.isEmpty(spPsw)){
                      Toast.makeText(LoginActivity.this,"用户名不存在",Toast.LENGTH_SHORT).show();;
                      return;
                  }else if(!md5Psw.equals(spPsw) && !TextUtils.isEmpty(spPsw)){
                      Toast.makeText(LoginActivity.this,"账号或密码有误",Toast.LENGTH_SHORT).show();;
                      return;
                  }else if (md5Psw.equals(spPsw)){
                      Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();;
                      saveLoginStatus(true,userName);
                      Intent data =new Intent();
                      data.putExtra("isLogin",true);
                      setResult(RESULT_OK,data);
                      LoginActivity.this.finish();
                      return;
                  }
             }
         });

         tv_register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                 startActivityForResult(intent,1);
             }
         });
         tv_find_psw.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(LoginActivity.this, FindPswActivity.class);
                 startActivity(intent);
                 Toast.makeText(LoginActivity.this,"跳转到找回密码界面",Toast.LENGTH_SHORT).show();;
                 return;
             }
         });
    }



    private void saveLoginStatus(boolean status, String userName) {
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin",status);
        editor.putString("loginUserName",userName);
        editor.commit();
    }

    private String readPsw(String userName) {
        SharedPreferences sp =getSharedPreferences("loginInfo",MODE_PRIVATE);
        return  sp.getString(userName,"");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            data.getStringArrayExtra("userName");
            if (TextUtils.isEmpty(userName)){
                et_user_name.setText(userName);
                et_user_name.setSelection(userName.length());
            }
        }
    }
}
