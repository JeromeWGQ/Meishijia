package com.example.lenovo.test2;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.view.menu.MenuView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.nio.channels.ClosedSelectorException;
import java.util.ArrayList;
import java.util.zip.Inflater;

import static com.example.lenovo.test2.ListViewInOfData.parseJsonWithGson;

/**
 * Created by lenovo on 2016/7/13.
 */
public class firstFragment extends Fragment {
    View view;
    ViewPager mViewPager;
    ImageView mImageView0,mImageView1,mImageView2;
    ListView mlistView;
    private EditText search_text;

    String device_id = null;
    String params = null;
    int page = 1;
    int amount = 2;
    String address = "http://172.25.132.12:8088/menu";
    public static firstFragment newInstance() {

        Bundle args = new Bundle();

        firstFragment fragment = new firstFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public firstFragment(){}


//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//        }
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.firstfragment,container,false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mImageView0 = (ImageView) view.findViewById(R.id.viewpager_0);
        mImageView1 = (ImageView) view.findViewById(R.id.viewpager_1);
        mImageView2 = (ImageView) view.findViewById(R.id.viewpager_2);
        device_id = Installation.id(getActivity());
        search_text = (EditText) view.findViewById(R.id.mainactivity_title_edit);
        search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(container.getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<View> data = new ArrayList<View>();
        setArraylistView(data, container);
        ViewPagerBaseAdapter adapter = new ViewPagerBaseAdapter();
        mViewPager.setAdapter(adapter);
        adapter.setData(data);
        mImageView0.setImageResource(R.mipmap.dot_choice);
        ViewPagerOnChangeListener();

        mlistView = (ListView) view.findViewById(R.id.you_like_listview);
        final TabOneListViewBaseAdapter listAdapter = new TabOneListViewBaseAdapter(getActivity());

        ListViewInOfData listData = new ListViewInOfData();
//        ArrayList<menu> testList = new ArrayList<menu>();
//        listData.getArrayListViewInfo(device_id,testList);
        mlistView.setAdapter(listAdapter);
        listData.getArrayListViewInfo(device_id,new HttpCallBackListener() {
            @Override
            public void Finish(String response) {
                ArrayList<menu> testList = parseJsonWithGson(response,0);
                listAdapter.setListData(testList);
               listAdapter.notifyDataSetChanged();
//                Message msg = new Message();
//                msg.what = 1;
//                Bundle bundle = new Bundle();
//                bundle.a
            }

            @Override
            public void OnError(Exception e) {

            }
        });
        setListViewHeightBasedOnChildren(mlistView);

        //listview点击事件
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DetailActivity.currentId = i;
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


    public void setArraylistView(ArrayList<View> data, final ViewGroup container) {
        View v0 = LayoutInflater.from(container.getContext()).inflate(R.layout.item0, null);
        v0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.currentId = 0;
                Intent intent = new Intent(container.getContext(),DetailActivity.class);
                startActivity(intent);
            }
        });
        data.add(v0);
        View v1 = LayoutInflater.from(container.getContext()).inflate(R.layout.item1,null);
        data.add(v1);
        View v2 = LayoutInflater.from(container.getContext()).inflate(R.layout.item2,null);
        data.add(v2);
    }
}
