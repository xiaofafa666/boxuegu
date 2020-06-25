package com.example.xiaofafa.boxuegu2.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofafa.boxuegu2.R;
import com.example.xiaofafa.boxuegu2.bean.UserBean;
import com.example.xiaofafa.boxuegu2.utils.AnalysisUtils;
import com.example.xiaofafa.boxuegu2.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private String spUserName;
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    private RelativeLayout rl_nickName;
    private RelativeLayout rl_sex;
    private RelativeLayout rl_signatrue;
    private TextView tv_nickName;
    private TextView tv_userName;
    private TextView tv_sex;
    private TextView tv_signature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        spUserName = AnalysisUtils.readLoginUserName(this);
         init();
         initData();
         setListener();
    }

    private void setListener() {
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signatrue.setOnClickListener(this);
    }

    private void initData() {
        UserBean bean = null;
        bean  = DBUtils.getInstance(this).getUserInfo(spUserName);
        if (bean == null){
            bean = new UserBean();
            bean.userName = spUserName;
            bean.nickName = "移动2班07180901";
            bean.sex = "男";
            bean.signature = "移动07180901签名";
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }

    private void setValue(UserBean bean) {
        tv_nickName.setText(bean.nickName);
        tv_userName.setText(bean.userName);
        tv_sex.setText(bean.sex);
        tv_signature.setText(bean.signature);

    }

    private void init() {
        tv_back = (TextView)findViewById(R.id.tv_back);
        tv_main_title = (TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));

        rl_nickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_signatrue = (RelativeLayout) findViewById(R.id.rl_signatrue);

        tv_nickName = (TextView) findViewById(R.id.tv_nickName);
        //此处不同
        tv_userName = (TextView) findViewById(R.id.tv_userName);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_signature = (TextView) findViewById(R.id.tv_signature);

        //初始化数据


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickName:
                Toast.makeText(this,"跳转昵称页面，待完成",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_sex:
                String sex = tv_sex.getText().toString();
                setDialog(sex);
                break;
            case R.id.rl_signatrue:
                Toast.makeText(this,"跳转签名页面，待完成",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
//创建选择性别对话框
    private void setDialog(String sex) {
        int sexFlag = 0;
        if ("男".equals(sex)){
            sexFlag = 0;
        }else if ("女".equals(sex)){
            sexFlag = 1;
        }
        final String items[] = {"男","女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this,items[which],Toast.LENGTH_SHORT).show();
                setSex(items[which]);
            }
        });
        builder.create().show();
    }

    private void setSex(String sex) {
        tv_sex.setText(sex);
        DBUtils.getInstance(this).updateUserInfo("sex",sex,spUserName);

    }
}
