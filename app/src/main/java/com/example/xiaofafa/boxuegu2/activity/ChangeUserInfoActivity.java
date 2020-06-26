package com.example.xiaofafa.boxuegu2.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofafa.boxuegu2.R;

public class ChangeUserInfoActivity extends AppCompatActivity {

    private String content;
    private String title;
    private TextView tv_main_title;
    private TextView tv_back;
    private TextView tv_save;
    private RelativeLayout rl_title_bar;
    private EditText et_content;
    private ImageView iv_delect;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        init();

    }

    private void init() {
        content = getIntent().getStringExtra("content");
        title = getIntent().getStringExtra("title");
        flag = getIntent().getIntExtra("flag", 0);

         tv_main_title = (TextView) findViewById(R.id.tv_main_title);
         tv_main_title.setText(title);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back = (TextView) findViewById(R.id.tv_back);
         tv_save = (TextView) findViewById(R.id.tv_save);
         tv_save.setVisibility(View.VISIBLE);
        et_content = (EditText) findViewById(R.id.et_content);
        iv_delect = (ImageView) findViewById(R.id.iv_delect);

        if(!TextUtils.isEmpty(content)){
            et_content.setText(content);
            et_content.setSelection(content.length());
        }
        contentListener();
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ChangeUserInfoActivity.this.finish();
            }
        });

        iv_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_content.setText("");
            }
        });

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ChangeUserInfoActivity.this,"点击了签名保存,当前flag值为"+flag, Toast.LENGTH_SHORT).show();
                Intent data = new Intent();
                String etContent = et_content.getText().toString().trim();
                switch (flag){
                    case 1:
                        if (!TextUtils.isEmpty(etContent)) {

                            data.putExtra("nickName",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"昵称保存成功", Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        } else {
                            Toast.makeText(ChangeUserInfoActivity.this,"昵称不能为空"+flag, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case  2:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("signature",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"签名保存成功", Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        } else {
                            Toast.makeText(ChangeUserInfoActivity.this,"签名不能为空"+flag, Toast.LENGTH_SHORT).show();
                        }
//                        break;
                }
            }
        });

    }

    private void contentListener() {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = et_content.getText();
                int len = editable.length();
                if (len>0){
                    iv_delect.setVisibility(View.VISIBLE);
                }else{
                    iv_delect.setVisibility(View.GONE);
                }
                switch (flag){
                    //昵称
                    case 1:
                        if (len>8){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            //取出前8个字符
                            String newStr = str.substring(0,8);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            int newLen = editable.length();
                            if(selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                        //
                    case 2:
                        if (len>16){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            String newStr = str.substring(0,16);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            int newLen = editable.length();
                            if(selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                        default:
                            break;


                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
