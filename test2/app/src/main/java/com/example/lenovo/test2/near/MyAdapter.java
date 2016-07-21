package com.example.lenovo.test2.near;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.test2.ListViewInOfData;
import com.example.lenovo.test2.QuanListViewBaseAdapter;
import com.example.lenovo.test2.R;
import com.example.lenovo.test2.quan;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.lenovo.test2.firstFragment.setListViewHeightBasedOnChildren;

/**
 * Created by Jerome on 2016/7/20.
 */

public class MyAdapter extends BaseAdapter {
    public MyAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    private LayoutInflater inflater;

    public void insert(String name, String address, double myLogi, double myLati, double resLogi, double resLati) {
        list.add(new FujinItem(name, address, myLogi, myLati, resLogi, resLati));
        notifyDataSetChanged();
    }

    public ArrayList<FujinItem> list = new ArrayList<FujinItem>();

    class FujinItem {
        private String name;

        private String address;

        private double logitude;

        private double latitude;

        private double distance;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLogitude() {
            return logitude;
        }

        public void setLogitude(double logitude) {
            this.logitude = logitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public FujinItem(String name, String address, double myLogi, double myLati, double resLogi, double resLati) {
            this.name = name;
            this.address = address;
            this.distance = Math.pow(Math.pow(myLogi - resLogi, 2) + Math.pow(myLati - resLati, 2), 0.5);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.fujin_one_listview_layout, null);
            viewHolder = new ViewHolder();
//            viewHolder.img_touxiang = (ImageView) view.findViewById(R.id.img_touxiang);
//            viewHolder.img_content = (ImageView) view.findViewById(R.id.img_quan);
//            viewHolder.author_name = (TextView) view.findViewById(R.id.author_name);
//            viewHolder.content = (TextView) view.findViewById(R.id.quan_content);

            viewHolder.title = (TextView) view.findViewById(R.id.textView9);
            viewHolder.position = (TextView) view.findViewById(R.id.textView10);
            viewHolder.distance = (TextView) view.findViewById(R.id.textView11);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        FujinItem list = (FujinItem) getItem(position);
//        viewHolder.img_touxiang.setImageResource(Integer.parseInt(quan_list.getAuthor_img()));
//        viewHolder.img_content.setImageResource(Integer.parseInt(quan_list.getContent_img()));
//        viewHolder.author_name.setText(quan_list.getAuthor_name());
//        viewHolder.content.setText(quan_list.getContent());

        viewHolder.title.setText(list.getName());
        viewHolder.position.setText(list.getAddress());
        viewHolder.distance.setText(String.format("%.2f", list.getDistance()) + " km");

        return view;
    }

    class ViewHolder {
//        ImageView img_touxiang;
//        ImageView img_content;
//        TextView author_name;
//        TextView content;

        TextView title, position, distance;
    }
}

