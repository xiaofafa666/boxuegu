package com.example.xiaofafa.boxuegu2.adpater;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
//import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofafa.boxuegu2.R;
import com.example.xiaofafa.boxuegu2.bean.ExercisesBean;
import com.example.xiaofafa.boxuegu2.utils.AnalysisUtils;

import java.util.ArrayList;
import java.util.List;

public class ExercisesDetailAdapter extends BaseAdapter {
    private List<ExercisesBean> ebl;
    private Context mContext;
    private List<String> selectedPosition = new ArrayList<>();
    private OnSelectListener onselectListener;

    public ExercisesDetailAdapter(Context mContext, OnSelectListener onselectListener) {
        this.onselectListener = onselectListener;
        this.mContext = mContext;
    }

//    public ExercisesDetailAdapter(Context mContext ) {
//        this.mContext = mContext;
//    }

    @Override
    public int getCount() {
        return ebl == null ? 0 : ebl.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return ebl == null ? null : ebl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    //

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exercises_detail_list_item, null);
            vh.subject = ((TextView) convertView.findViewById(R.id.tv_subject));

            vh.tv_a = ((TextView) convertView.findViewById(R.id.tv_a));
            vh.tv_b = ((TextView) convertView.findViewById(R.id.tv_b));
            vh.tv_c = ((TextView) convertView.findViewById(R.id.tv_c));
            vh.tv_d = ((TextView) convertView.findViewById(R.id.tv_d));

            vh.iv_a = ((ImageView) convertView.findViewById(R.id.iv_a));
            vh.iv_b = ((ImageView) convertView.findViewById(R.id.iv_b));
            vh.iv_c = ((ImageView) convertView.findViewById(R.id.iv_c));
            vh.iv_d = ((ImageView) convertView.findViewById(R.id.iv_d));

            convertView.setTag(vh);

        } else {
            vh = ((ViewHolder) convertView.getTag());
        }
        //
        final ExercisesBean bean = getItem(position);
        if (bean != null) {
            vh.subject.setText(bean.subject);
            vh.tv_a.setText(bean.a);
            vh.tv_b.setText(bean.b);
            vh.tv_c.setText(bean.c);
            vh.tv_d.setText(bean.d);
        }
        if (!selectedPosition.contains("" + position)) {
            vh.iv_a.setImageResource(R.drawable.exercises_a);
            vh.iv_b.setImageResource(R.drawable.exercises_b);
            vh.iv_c.setImageResource(R.drawable.exercises_c);
            vh.iv_d.setImageResource(R.drawable.exercises_d);
            AnalysisUtils.setABCDEnable(true, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
        } else {
            AnalysisUtils.setABCDEnable(false, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
            switch (bean.selsct) {
                //用户答对
                case 0:
                    if (bean.answer == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 2) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 3) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 4) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 1:
                    //用户选A答错
                    vh.iv_a.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 2) {
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 3) {
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 4) {
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2:
                    //用户选B答错
                    vh.iv_b.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 3) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 4) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 3:
                    //用户选C答错
                    vh.iv_c.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 2) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 4) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 4:
                    //用户选D答错
                    vh.iv_d.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 1) {
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                    } else if (bean.answer == 2) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                    } else if (bean.answer == 3) {
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                    }

                    break;
                default:
                    break;
            }
        }

        vh.iv_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext,"你点击了A选项",Toast.LENGTH_SHORT).show();

                    if (selectedPosition.contains("" + position)) {
//                    selectedPosition.remove(""+position);

                    } else {
                        selectedPosition.add("" + position);
                    }
                    onselectListener.onSelectA(position, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
                }
            });
            vh.iv_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext,"你点击了A选项",Toast.LENGTH_SHORT).show();

                    if (selectedPosition.contains("" + position)) {


                    } else {
                        selectedPosition.add("" + position);
                    }
                    onselectListener.onSelectB(position, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
                }
            });
            vh.iv_c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition.contains("" + position)) {


                    } else {
                        selectedPosition.add("" + position);
                    }
                    onselectListener.onSelectC(position, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
                }
            });
            vh.iv_d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition.contains("" + position)) {


                    } else {
                        selectedPosition.add("" + position);
                    }
                    onselectListener.onSelectD(position, vh.iv_a, vh.iv_b, vh.iv_c, vh.iv_d);
                }
            });



        return convertView;
    }

     public void setData(List<ExercisesBean> ebl){
        this.ebl = ebl;
        notifyDataSetChanged();
    }


    public class ViewHolder {
        public TextView subject;

        public TextView tv_a;
        public TextView tv_b;
        public TextView tv_c;
        public TextView tv_d;

        public ImageView iv_a;
        public ImageView iv_b;
        public ImageView iv_c;
        public ImageView iv_d;
    }

    public interface OnSelectListener {
        void onSelectA(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
        void onSelectB(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
        void onSelectC(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
        void onSelectD(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d);
    }
}
