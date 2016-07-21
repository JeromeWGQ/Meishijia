package com.example.lenovo.test2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Jerome on 2016/7/15.
 */

public class TabOneListViewBaseAdapter extends BaseAdapter {
    ArrayList<menu> arrayList = new ArrayList<menu>();
    LayoutInflater inflater;

    public TabOneListViewBaseAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setListData(ArrayList<menu> arrayList) {
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
            view = inflater.inflate(R.layout.tab_one_listview_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.tab_one_listview_image);
            viewHolder.title = (TextView) view.findViewById(R.id.tab_one_listview_title);
            viewHolder.author = (TextView) view.findViewById(R.id.tab_one_listview_author);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        menu menu_list = (HashMap<>) getItem(position);
        viewHolder.imageView.setBackgroundResource(Integer.parseInt(menu_list.getImg()));
        viewHolder.title.setText(menu_list.getName());
        viewHolder.author.setText(menu_list.getType());
        return view;
    }

    class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView author;
    }
}
