package com.example.lenovo.test2;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.view.menu.MenuView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.nio.channels.ClosedSelectorException;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by lenovo on 2016/7/13.
 */
public class firstFragment extends Fragment {
    View view;
    ViewPager mViewPager;
    ImageView mImageView0,mImageView1,mImageView2;
    ListView mlistView;
    public static firstFragment newInstance() {

        Bundle args = new Bundle();

        firstFragment fragment = new firstFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public firstFragment(){}



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.firstfragment,container,false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mImageView0 = (ImageView) view.findViewById(R.id.viewpager_0);
        mImageView1 = (ImageView) view.findViewById(R.id.viewpager_1);
        mImageView2 = (ImageView) view.findViewById(R.id.viewpager_2);

        ArrayList<View> data = new ArrayList<View>();
        setArraylistView(data, container);
        ViewPagerBaseAdapter adapter = new ViewPagerBaseAdapter();
        mViewPager.setAdapter(adapter);
        adapter.setData(data);
        mImageView0.setImageResource(R.mipmap.dot_choice);
        ViewPagerOnChangeListener();

        mlistView = (ListView) view.findViewById(R.id.you_like_listview);
        TabOneListViewBaseAdapter listAdapter = new TabOneListViewBaseAdapter(getActivity());
        mlistView.setAdapter(listAdapter);
        ListViewInOfData listData = new ListViewInOfData();
        listAdapter.setListData(listData.getArrayListViewInfo());
        setListViewHeightBasedOnChildren(mlistView);

        //listview点击事件
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(container.getContext(),DetailActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void ViewPagerOnChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mImageView0.setImageResource(R.mipmap.dot_choice);
                        mImageView1.setImageResource(R.mipmap.dot_no_choice);
                        mImageView2.setImageResource(R.mipmap.dot_no_choice);
                        break;
                    case 1:
                        mImageView0.setImageResource(R.mipmap.dot_no_choice);
                        mImageView1.setImageResource(R.mipmap.dot_choice);
                        mImageView2.setImageResource(R.mipmap.dot_no_choice);
                        break;
                    case 2:
                        mImageView0.setImageResource(R.mipmap.dot_no_choice);
                        mImageView1.setImageResource(R.mipmap.dot_no_choice);
                        mImageView2.setImageResource(R.mipmap.dot_choice);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setArraylistView(ArrayList<View> data,ViewGroup container) {
        View v0 = LayoutInflater.from(container.getContext()).inflate(R.layout.item0, null);
        data.add(v0);
        View v1 = LayoutInflater.from(container.getContext()).inflate(R.layout.item1,null);
        data.add(v1);
        View v2 = LayoutInflater.from(container.getContext()).inflate(R.layout.item2,null);
        data.add(v2);
    }
    public class ViewPagerBaseAdapter extends PagerAdapter{
        ArrayList<View> data = new ArrayList<View>();

        public void setData(ArrayList<View> data){
            this.data = data;
            notifyDataSetChanged();
        }
        @Override
        public int getCount(){
            return data.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = data.get(position);
            container.addView(v);
            Log.v("tag", "instantiateItem position " + position);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = data.get(position);
            container.removeView(v);// 这里是remove而不是add方法，否则加载最后一页之后会出现非法描述异常java.lang.IllegalStateException
            Log.v("tag", "destroyItem position " + position);
        }
    }
}
