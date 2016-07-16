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
            viewHolder.imageView = (ImageView) view.findViewById(R.id.step_detail_one_listview_image);
            viewHolder.desc = (TextView) view.findViewById(R.id.step_detail_desc);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        stepDetail step_list = (stepDetail) getItem(position);
        viewHolder.imageView.setBackgroundResource(Integer.parseInt(step_list.getImage()));
        viewHolder.desc.setText(step_list.getStepDesc());
        return view;
    }
        class ViewHolder{
            ImageView imageView;
            TextView desc;
        }
}
