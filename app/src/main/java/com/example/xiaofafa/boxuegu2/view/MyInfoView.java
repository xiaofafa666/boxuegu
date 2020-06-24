package com.example.xiaofafa.boxuegu2.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofafa.boxuegu2.R;
import com.example.xiaofafa.boxuegu2.activity.LoginActivity;
import com.example.xiaofafa.boxuegu2.activity.SettingActivity;
import com.example.xiaofafa.boxuegu2.utils.AnalysisUtils;

public class MyInfoView {
    private  final Activity mContext;
    private  final LayoutInflater mInflater;
    private View mCurrentView;
    private LinearLayout ll_head;
    private ImageView ll_head_icon;
    private RelativeLayout rl_course_history;
    private RelativeLayout rl_setting;
    private TextView tv_user_name;

    public MyInfoView(Activity context){
        mContext = context;
        mInflater =LayoutInflater.from(mContext);
    }
    private void createView(){
        initView();
    }

    private void initView() {
        mCurrentView =mInflater.inflate(R.layout.main_view_myinfo,null);
        ll_head = (LinearLayout) mCurrentView.findViewById(R.id.ll_head);
        ll_head_icon = (ImageView) mCurrentView.findViewById(R.id.iv_head_icon);
        rl_course_history = (RelativeLayout) mCurrentView.findViewById(R.id.rl_course_history);
        rl_setting = (RelativeLayout) mCurrentView.findViewById(R.id.rl_setting);
        tv_user_name = (TextView) mCurrentView.findViewById(R.id.tv_user_name);
        mCurrentView.setVisibility(View.VISIBLE);

        //修复账号未退出时账号名不显示问题
        setLoginParams(readLoginStatus());


        ll_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()){
                    Toast.makeText(mContext,"跳转到个人页面",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent =new Intent(mContext,LoginActivity.class);
                    mContext.startActivityForResult(intent,1);
                }
            }

        });
        rl_course_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()){
                    Toast.makeText(mContext,"跳转到播放记录页面",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,"请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()){
                    Intent intent = new Intent (mContext,SettingActivity.class);
                    mContext.startActivityForResult(intent,1);
//                    Toast.makeText(mContext,"跳转到设置页面",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,"请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setLoginParams(boolean isLogin){
        if(isLogin){
            tv_user_name.setText(AnalysisUtils.readLoginUserName(mContext));
        }else{
            tv_user_name.setText("点击登录");
        }
    }

    public View getView() {
      if (mCurrentView == null){
          createView();
      }
      return mCurrentView;
    }

    public void showView(){
        if (mCurrentView == null){
            createView();
        }
    mCurrentView.setVisibility(View.VISIBLE);
    }


    private boolean readLoginStatus() {
        SharedPreferences sp = mContext.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin=sp.getBoolean("isLogin",false);
        return isLogin;
    }
}
