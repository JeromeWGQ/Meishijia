package com.example.lenovo.test2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/7/18.
 */

public class    FragmentBaseAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    public FragmentBaseAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void setData(ArrayList<Fragment> fragmentList){
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }

}
