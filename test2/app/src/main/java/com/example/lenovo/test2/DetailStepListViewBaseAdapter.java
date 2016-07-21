package com.example.lenovo.test2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/7/14.
 */
public class DetailStepListViewBaseAdapter extends BaseAdapter {
    ArrayList<stepDetail> arrayList = new ArrayList<stepDetail>();
    LayoutInflater inflater;
    public DetailStepListViewBaseAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }
    public void setListData(ArrayList<stepDetail> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount(){
        return arrayList.size();
    }
    @Override
    public Object getItem(int position){
        return arrayList.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = inflater.inflate(R.layout.content_detail_listview_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.imageTouxiang = (ImageView) view.findViewById(R.id.evaluate_touxiang);
            viewHolder.name = (TextView) view.findViewById(R.id.evaluate_name);
            viewHolder.evaluateContent = (TextView) view.findViewById(R.id.evaluate_content);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        evaluate evaluate_list  = (evaluate) getItem(position);
        viewHolder.imageTouxiang.setBackgroundResource(Integer.parseInt(evaluate_list.getImg()));
        viewHolder.name.setText(evaluate_list.getName());
        viewHolder.evaluateContent.setText(evaluate_list.getEvaluation());
        return view;
    }
        class ViewHolder{
            ImageView imageTouxiang;
            TextView name;
            TextView evaluateContent;

        }
}
