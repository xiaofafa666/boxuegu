package com.example.xiaofafa.boxuegu2.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofafa.boxuegu2.R;
import com.example.xiaofafa.boxuegu2.udil.Md5Utils;
import com.example.xiaofafa.boxuegu2.utils.AnalysisUtils;

public class ModifyPswActivity extends AppCompatActivity {

    private TextView tv_main_title;
    private TextView tv_back;
    private EditText et_original_psw;
    private EditText et_new_psw;
    private EditText et_psw_again;
    private Button btn_save;
    private String originalPsw;
    private String newPsw;
    private String newPswAgain;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        userName = AnalysisUtils.readLoginUserName(this);
        init();
    }

    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("修改新密码");
        tv_back = (TextView) findViewById(R.id.tv_back);
        et_original_psw = (EditText) findViewById(R.id.et_original_psw);
        et_new_psw = (EditText) findViewById(R.id.et_new_psw);
        et_psw_again = (EditText) findViewById(R.id.et_psw_again);

        btn_save = (Button) findViewById(R.id.btn_save);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyPswActivity.this.finish();

            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(originalPsw)){
                    Toast.makeText(ModifyPswActivity.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!Md5Utils.md5(originalPsw).equals(readPsw())){
                    Toast.makeText(ModifyPswActivity.this, "输入密码与原始密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else if (Md5Utils.md5(newPsw).equals(readPsw())){
                    Toast.makeText(ModifyPswActivity.this, "输入新密码与原密码一样", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(newPsw)){
                    Toast.makeText(ModifyPswActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(newPswAgain)){
                    Toast.makeText(ModifyPswActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!newPsw.equals(newPswAgain)){
                    Toast.makeText(ModifyPswActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(ModifyPswActivity.this, "设置密码成功", Toast.LENGTH_SHORT).show();
                    modifyPsw(newPsw);
                    Intent intent = new Intent(ModifyPswActivity.this, LoginActivity.class);
                    startActivity(intent);
                    ModifyPswActivity.this.finish();
                    SettingActivity.instance.finish();
                }
            }
        });
    }

    private void modifyPsw(String newPsw) {
        String md5Psw = Md5Utils.md5(newPsw);
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();
    }

    private String readPsw() {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        return  spPsw;
    }

    private void getEditString() {
        originalPsw = et_original_psw.getText().toString().trim();
        newPsw = et_new_psw.getText().toString().trim();
        newPswAgain = et_psw_again.getText().toString().trim();
    }
}
