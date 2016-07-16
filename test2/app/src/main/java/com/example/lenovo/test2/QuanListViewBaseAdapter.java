package com.example.lenovo.test2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jerome on 2016/7/15.
 */

public class QuanListViewBaseAdapter extends BaseAdapter {
    ArrayList<quan> arrayList = new ArrayList<quan>();
    LayoutInflater inflater;

    public QuanListViewBaseAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setListData(ArrayList<quan> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view= inflater.inflate(R.layout.quan_one_listview_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.img_touxiang = (ImageView) view.findViewById(R.id.img_touxiang);
            viewHolder.img_content = (ImageView) view.findViewById(R.id.img_quan);
            viewHolder.author_name = (TextView) view.findViewById(R.id.author_name);
            viewHolder.content = (TextView) view.findViewById(R.id.quan_content);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        quan quan_list = (quan) getItem(position);
        viewHolder.img_touxiang.setImageResource(Integer.parseInt(quan_list.getAuthor_img()));
        viewHolder.img_content.setImageResource(Integer.parseInt(quan_list.getContent_img()));
        viewHolder.author_name.setText(quan_list.getAuthor_name());
        viewHolder.content.setText(quan_list.getContent());
        return view;
    }
    class ViewHolder{
        ImageView img_touxiang;
        ImageView img_content;
        TextView author_name;
        TextView content;
    }
}
