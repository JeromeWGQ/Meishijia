package com.example.lenovo.test2;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.lenovo.test2.firstFragment.setListViewHeightBasedOnChildren;

public class DetailActivity extends FragmentActivity {
    ViewPager viewPager;
    FragmentBaseAdapter adapter ;
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private ArrayList<Fragment> list = new ArrayList<Fragment>();
    private TabLayout mTabLayout;
    private FragmentManager fManager;
    private Fragment detail0fragment;
    private Fragment detail1fragment;
    private View fragment;
    public static int currentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        initial();

//        mTabLayout = (TabLayout) findViewById(R.id.tab_viewpager);
//        mTabLayout.addTab(mTabLayout.newTab().setText("做法详情"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("营养价值"));
//        viewPager = (ViewPager) findViewById(R.id.top_viewPager);
//
//        fManager = getSupportFragmentManager();
//        list.add(detail0fragment);
//        list.add(detail1fragment);
//
//        adapter =new FragmentBaseAdapter(fManager,list);
//        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(0);
//        ViewPagerOnChangeListener();

    }
    public void initial(){

                mTabLayout = (TabLayout) findViewById(R.id.tab_viewpager);
//                viewPager = (ViewPager) findViewById(R.id.top_viewPager);
                detail0fragment = new detail0Fragment();
                detail1fragment = new detail1Fragment();

//                fragment = findViewById(R.id.detailcontainer_frame)

                list.add(detail0fragment);
//                list.add(detail1fragment);
//
                adapter =new FragmentBaseAdapter(getSupportFragmentManager());
                viewPager.setAdapter(adapter);
                adapter.setData(list);

                mTabLayout.setupWithViewPager(viewPager);
                mTabLayout.getTabAt(0).setText("做法详情");
//                mTabLayout.getTabAt(1).setText("评价");
                viewPager.setCurrentItem(0);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
                    int two = one * 2;// 页卡1 -> 页卡3 偏移量
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        Animation animation = new TranslateAnimation(one*currIndex, one*position, 0, 0);

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
//                ViewPagerOnChangeListener();
    }

    public void ViewPagerOnChangeListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
            int two = one * 2;// 页卡1 -> 页卡3 偏移量
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Animation animation = new TranslateAnimation(one*currIndex, one*position, 0, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
