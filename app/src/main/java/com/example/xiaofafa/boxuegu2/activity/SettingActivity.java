package com.example.xiaofafa.boxuegu2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofafa.boxuegu2.FindPswActivity;
import com.example.xiaofafa.boxuegu2.R;

public class SettingActivity extends AppCompatActivity {

    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    private RelativeLayout rl_modify_psw;
    private RelativeLayout rl_security_setting;
    private RelativeLayout rl_exit_login;
    public static SettingActivity instance=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        instance = this;
        init();
    }

    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_modify_psw = (RelativeLayout) findViewById(R.id.rl_modify_psw);
        rl_security_setting = (RelativeLayout) findViewById(R.id.rl_security_setting);
        rl_exit_login = (RelativeLayout) findViewById(R.id.rl_exit_login);

        tv_main_title.setText("设置");
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });
        rl_modify_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "跳转到修改密码页面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingActivity.this, ModifyPswActivity.class);
                startActivity(intent);
            }
        });
        rl_security_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, FindPswActivity.class);
                intent.putExtra("from","security");
                startActivity(intent);
                Toast.makeText(SettingActivity.this, "跳转到密保页面", Toast.LENGTH_SHORT).show();
            }
        });
        rl_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "退出登录成功", Toast.LENGTH_SHORT).show();
                clearLoginStatus();
                Intent data =new Intent();
                data.putExtra("isLogin",false);
                setResult(RESULT_OK,data);
                SettingActivity.this.finish();
            }


        });
    }
    private void clearLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();
    }
}
