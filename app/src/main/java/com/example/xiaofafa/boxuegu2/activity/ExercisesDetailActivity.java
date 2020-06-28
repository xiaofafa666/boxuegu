package com.example.xiaofafa.boxuegu2.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaofafa.boxuegu2.R;
import com.example.xiaofafa.boxuegu2.adpater.ExercisesAdapter;
import com.example.xiaofafa.boxuegu2.adpater.ExercisesDetailAdapter;
import com.example.xiaofafa.boxuegu2.bean.ExercisesBean;
import com.example.xiaofafa.boxuegu2.utils.AnalysisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ExercisesDetailActivity extends AppCompatActivity {

    private int id;
    private String title;
    private List<ExercisesBean> ebl;
    private RelativeLayout rl_title_bar;
    private TextView tv_back;
    private TextView tv_main_title;
    private ListView lv_list;
    private ExercisesDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        id = getIntent().getIntExtra("id",0);
        title = getIntent().getStringExtra("title");

        initData();
        init();
    }

    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText(title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        lv_list = (ListView) findViewById(R.id.lv_list);
        TextView tv = new TextView(this);
        tv.setBackgroundColor(Color.parseColor("#000000"));
        tv.setText("选择题");
        tv.setTextSize(16.0f);
        tv.setPadding(10,10,0,0);
        lv_list.addHeaderView(tv);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExercisesDetailActivity.this.finish();
            }
        });

        //????
        adapter = new ExercisesDetailAdapter(this, new ExercisesDetailAdapter.OnSelectListener() {
            @Override
            public void onSelectA(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                if (ebl.get(position).answer != 1) {
                    ebl.get(position).selsct = 1;
                } else {
                    ebl.get(position).selsct = 0;
                }
                switch (ebl.get(position).answer) {
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 2:
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
            }

            @Override
            public void onSelectB(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                if (ebl.get(position).answer != 2) {
                    ebl.get(position).selsct = 2;
                } else {
                    ebl.get(position).selsct = 0;
                }
                switch (ebl.get(position).answer) {
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);

                        break;
                    case 3:
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
            }

            @Override
            public void onSelectC(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                if (ebl.get(position).answer != 3) {
                    ebl.get(position).selsct = 3;
                } else {
                    ebl.get(position).selsct = 0;
                }
                switch (ebl.get(position).answer) {
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
            }

            @Override
            public void onSelectD(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                if (ebl.get(position).answer != 4) {
                    ebl.get(position).selsct = 4;
                } else {
                    ebl.get(position).selsct = 0;
                }
                switch (ebl.get(position).answer) {
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 4:
                        iv_d.setImageResource(R.drawable.exercises_right_icon);
                        break;
                }
                AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
            }
        });

        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }

    private void initData() {

        try {
            InputStream is = getResources().getAssets().open("chapter" + id + ".xml");
             ebl = AnalysisUtils.getExercisesInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
