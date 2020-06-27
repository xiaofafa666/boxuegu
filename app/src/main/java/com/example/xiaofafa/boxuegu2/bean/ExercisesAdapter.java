package com.example.xiaofafa.boxuegu2.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaofafa.boxuegu2.R;

import java.util.List;

public class ExercisesAdapter extends BaseAdapter {

    private final Context mContext;
    private List<ExercisesBean> eb1;

    public ExercisesAdapter(Context context){
        this.mContext = context;
    }

    public void setData(List<ExercisesBean> eb1){
        this.eb1 = eb1;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return eb1 == null ? 0 : eb1.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return eb1 == null ? null : eb1.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  ViewHolder vh;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exercises_list_item,null);
            vh.order =(TextView) convertView.findViewById(R.id.tv_order);
            vh.title = (TextView) convertView.findViewById(R.id.tv_title);
            vh.content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(vh);
        }else {
            vh =((ViewHolder) convertView.getTag());

        }
        final ExercisesBean bean = getItem(position);
        if (bean != null){
            vh.order.setText(position + 1 + "");
            vh.title.setText(bean.title);
            vh.order.setBackgroundResource(bean.background);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bean == null){
                   return;
                }
                Toast.makeText(mContext,"跳转习题详情页面",Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
    private class ViewHolder{
        public TextView order;
        public TextView title;
        public TextView content;
    }
}
