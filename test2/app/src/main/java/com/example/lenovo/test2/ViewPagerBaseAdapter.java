package com.example.lenovo.test2;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/7/17.
 */

public class ViewPagerBaseAdapter extends PagerAdapter {
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
