package com.example.xiaofafa.boxuegu2.activity;

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
import com.example.xiaofafa.boxuegu2.utils.AnalysisUtils;

public class FindPswActivity extends AppCompatActivity {

    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_user_name;
    private EditText et_user_name;
    private EditText et_validate_name;
    private TextView tv_reset_psw;
    private Button btn_validate;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        from = getIntent().getStringExtra("from");
        init();
    }

    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);

        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        et_user_name = (EditText)  findViewById(R.id.et_user_name);
         et_validate_name =(EditText) findViewById(R.id.et_validate_name);

        tv_reset_psw = (TextView)  findViewById(R.id.tv_reset_psw);
        btn_validate = (Button) findViewById(R.id.btn_validate);



        if("security".equals(from)){
            tv_main_title.setText("设置密保");
        }else{
            tv_main_title.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
        }

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindPswActivity.this.finish();
            }
        });

        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validateName= et_validate_name.getText().toString().trim();
//                Toast.makeText(FindPswActivity.this,"输入值为"+validateName,Toast.LENGTH_SHORT).show();
                if("security".equals(from)){
                    if(TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActivity.this,"请输入需要验证的姓名",Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Toast.makeText(FindPswActivity.this,"密保设置成功",Toast.LENGTH_SHORT).show();
                        saveSecurity(validateName);
                        FindPswActivity.this.finish();
                    }
                }else {
                      //找回密码
                    String userName = et_user_name.getText().toString().trim();
                    String sp_security = readSecurity(userName);
                    if (TextUtils.isEmpty(userName)){
                        Toast.makeText(FindPswActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                        return;

                    }else if(!isExistUserName(userName)){
                        Toast.makeText(FindPswActivity.this,"当前用户名不存在",Toast.LENGTH_SHORT).show();
                        return;
                    }else if (TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActivity.this,"请输入此用户名密保",Toast.LENGTH_SHORT).show();

                        return;
                    }else if (!validateName.equals(sp_security)){

                        Toast.makeText(FindPswActivity.this,"密保输入有误",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        tv_reset_psw.setVisibility(View.VISIBLE);

                        tv_reset_psw.setText("初始密码：123456");
                        Toast.makeText(FindPswActivity.this,"找回密码成功，初始密码为：123456",Toast.LENGTH_SHORT).show();

                        savePsw(userName);
                    }
                }

            }
        });
    }

    private void savePsw(String userName) {
        String md5Psw = com.example.xiaofafa.boxuegu2.udil.Md5Utils.md5("123456");
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();
    }

    private boolean isExistUserName(String userName) {
        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)){
            hasUserName = true;
        }
        return  hasUserName;

    }

    private String readSecurity(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String security = sp.getString(userName+"_security","");
        return security;
    }

    private void saveSecurity(String validateName){
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this)+"_security",validateName);
        editor.commit();
    }
}
