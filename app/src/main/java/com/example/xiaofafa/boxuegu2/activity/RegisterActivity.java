package com.example.xiaofafa.boxuegu2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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

public class RegisterActivity extends AppCompatActivity {

    private  TextView mTv_main_title;
    private EditText mEt_user_name;
    private EditText mEt_psw;
    private EditText mEt_psw_again;
    private Button mBtn_register;
    private RelativeLayout mRl_title_bar;
    private TextView mTv_back;
    private String mUserName;
    private String mpsw;
    private String mPsw_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }


    private void init() {
        mTv_main_title = (TextView)findViewById(R.id.tv_main_title);
        mTv_main_title.setText("注册");
        mRl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        mRl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        mTv_back = (TextView) findViewById(R.id.tv_back);

        mEt_user_name = (EditText)findViewById(R.id.et_user_name);
        mEt_psw = (EditText)findViewById(R.id.et_psw);
        mEt_psw_again = (EditText)findViewById(R.id.et_psw_again);
        mBtn_register = (Button) findViewById(R.id.btn_register);

        mTv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        mBtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(mUserName)){
                    Toast.makeText(RegisterActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(mpsw)){
                    Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(mPsw_again)){
                    Toast.makeText(RegisterActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                }else if(!mpsw.equals(mPsw_again)){
                    Toast.makeText(RegisterActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                  }else if (isExistUserName(mUserName)){
                    Toast.makeText(RegisterActivity.this,"此用户名已存在",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    saceRegisterInfo(mUserName,mpsw);
                    Intent data = new Intent();
                    data.putExtra("userName",mUserName);
                    setResult(RESULT_OK,data);
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    private void saceRegisterInfo(String userName, String psw) {
        String md5Psw =Md5Utils.md5(psw);
        SharedPreferences sp =getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();
    }


    public boolean isExistUserName(String userName ) {
       boolean has_userName = false;
        SharedPreferences sp =getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw =sp.getString(userName,"");
        if(!TextUtils.isEmpty(spPsw)){
            has_userName = true;
        }
        return has_userName;
    }

    private void getEditString() {
        mUserName = mEt_user_name.getText().toString().trim();
        mpsw = mEt_psw.getText().toString().trim();
        mPsw_again = mEt_psw_again.getText().toString().trim();
    }
}
