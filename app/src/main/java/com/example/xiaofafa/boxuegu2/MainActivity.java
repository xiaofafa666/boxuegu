package com.example.xiaofafa.boxuegu2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofafa.boxuegu2.view.ExercisesView;
import com.example.xiaofafa.boxuegu2.view.MyInfoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    private FrameLayout mBodyLayout;
    private LinearLayout mBottomLayout;
    private View mCourseBtn;
    private ImageView iv_course;
    private TextView tv_course;
    private View mExercissBtn;
    private ImageView iv_exerciss;
    private TextView tv_exerciss;
    private View mMyInfoBtn;
    private ImageView iv_myinfo;
    private TextView tv_myinfo;
    private MyInfoView mMyInfoView;
    private ExercisesView mExercisesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     init();
     initBottomBar();
     setListener();
     setInitStatus();
    }

    private void setInitStatus() {
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);
    }

    private void setListener() {
        for(int i =0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }

    }

    private void initBottomBar() {


        mBottomLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);
        mCourseBtn = findViewById(R.id.bottom_bar_course_btn);
        iv_course = (ImageView) findViewById(R.id.bottom_bar_image_course);
        tv_course = (TextView) findViewById(R.id.bottom_bar_text_course);
        mExercissBtn = findViewById(R.id.bottom_bar_exerciss_btn);
        iv_exerciss = (ImageView) findViewById(R.id.bottom_bar_image_exerciss);
        tv_exerciss = (TextView) findViewById(R.id.bottom_bar_text_exerciss);
        mMyInfoBtn = findViewById(R.id.bottom_bar_myinfo_btn);
        iv_myinfo = (ImageView) findViewById(R.id.bottom_bar_image_myinfo);
        tv_myinfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);
    }

    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.GONE);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        initViewBodyLayout();
    }

    private void initViewBodyLayout() {
        mBodyLayout = (FrameLayout) findViewById(R.id.main_body);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_bar_course_btn:
            clearBottomImageState();
            selectDisplayView(0);
            break;
            case R.id.bottom_bar_exerciss_btn:
            clearBottomImageState();
            selectDisplayView(1);
            break;
            case R.id.bottom_bar_myinfo_btn:
            clearBottomImageState();
            selectDisplayView(2);
            break;
            default:
                break;
        }
    }

    private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }

    private void setSelectedStatus(int index) {
        switch (index){
            case 0:
                mCourseBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon_selected);
                tv_course.setTextColor(Color.parseColor("#0097f7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷课程");
                break;
            case 1:
                mExercissBtn.setSelected(true);
                iv_exerciss.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exerciss.setTextColor(Color.parseColor("#0097f7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷习题");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myinfo.setImageResource(R.drawable.main_my_icon_selected);
                tv_myinfo.setTextColor(Color.parseColor("#0097f7"));
                rl_title_bar.setVisibility(View.GONE);
                break;
        }
    }

    private void createView(int index) {
        switch (index){
            case 0:
                Toast.makeText(this,"课程页面待完成",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (mExercisesView == null) {
                    mExercisesView = new ExercisesView(this);
                    mBodyLayout.addView(mExercisesView.getView());
                }else{
                    mExercisesView.getView();
                }
                mExercisesView.showView();
                Toast.makeText(this,"习题页面待完成",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                if (mMyInfoView == null){
                    mMyInfoView = new MyInfoView(this);
                    mBodyLayout.addView(mMyInfoView.getView());
                }else{
                    mMyInfoView.getView();
                }
                mMyInfoView.showView();
                Toast.makeText(this,"我的页面待完成",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void removeAllView() {
        for(int i=0;i<mBodyLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }

    private void clearBottomImageState() {
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exerciss.setTextColor(Color.parseColor("#666666"));
        tv_myinfo.setTextColor(Color.parseColor("#666666"));

        iv_course.setImageResource(R.drawable.main_course_icon);
        iv_exerciss.setImageResource(R.drawable.main_exercises_icon);
        iv_myinfo.setImageResource(R.drawable.main_my_icon);

        for (int i =0;i< mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }

    protected long exitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - exitTime) > 2000){
                Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                exitTime =System.currentTimeMillis();
            }else{
                MainActivity.this.finish();
                if (readLoginStatus()){
                    clearLoginStatus();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void clearLoginStatus() {
        SharedPreferences sp =getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();
    }

    private boolean readLoginStatus() {
        SharedPreferences sp =getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin =sp.getBoolean("isLogin",false);
        return isLogin;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data !=null){
           boolean isLogin = data.getBooleanExtra("isLogin",false);
            if(isLogin){
                clearBottomImageState();
                selectDisplayView(2);
            }
            if (mMyInfoView != null){
                mMyInfoView.setLoginParams(isLogin);
            }
        }
    }
}
