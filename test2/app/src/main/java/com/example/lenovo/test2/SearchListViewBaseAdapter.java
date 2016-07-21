package com.example.lenovo.test2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by lenovo on 2016/7/20.
 */

public class SearchListViewBaseAdapter extends BaseAdapter {
    ArrayList<SearchSuggest> list = new ArrayList<SearchSuggest>();
    LayoutInflater inflater;
    public SearchListViewBaseAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }
    public void setListData(ArrayList<SearchSuggest> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount(){
        return list.size();
    }
    @Override
    public Object getItem(int position){
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.search_one_listview_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.search_title_edit);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        SearchSuggest suggest = (SearchSuggest) getItem(position);
        viewHolder.name.setText(suggest.getName());
        viewHolder.id = suggest.getId();
        viewHolder.type = suggest.getType();
        return view;
    }

    class ViewHolder{
        TextView name;
        String id;
        int type;
    }

}
