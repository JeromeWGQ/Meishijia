package com.example.lenovo.test2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.test2.quanquan.QuanFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends FragmentActivity implements QuanFragment.OnFragmentInteractionListener{

    private TextView txt_topbar;
    private TextView txt_channel;
    private TextView txt_message;
    private TextView txt_better;
    private TextView txt_setting;
    private FrameLayout ly_content;
    private EditText search_text;
    private Button search_button;
    private String params;
    private String address;
    String device_id = null ;

    private boolean logined = false;

    private MyFragment fg1,fg2,fgLogined;
    private QuanFragment fg4;
    private Fragment fg3;
    private firstFragment fgfirst;
    private FragmentManager fManager;

    public void OnFragmentInteractionListener(){

    }

    public void onClickLogin(View v){
        Log.v("TabActivity","点击啦");
        Intent intent = new Intent(this,Login2Activity.class);
        startActivity(intent);
    }

    public void onClickLogined(View v){
        Log.v("TabActivity","点击了我已登录");
        logined = true;
        /*切换至已经登录的activity*/
        switchToActivity2Logined();
    }

    public void onOneMenuClicked(View v){
        Intent intent = new Intent(this,DetailActivity.class);
        startActivity(intent);
    }

    private void switchToActivity2Logined() {
        Intent intent = new Intent(this,MyLoginedActivity.class);
        startActivity(intent);
    }

    private void switchTofragment2Logined(){
        fragmentTransaction = fManager.beginTransaction();
        hideAllFragment(fragmentTransaction);

        if (fgLogined == null) {
            fgLogined = new MyFragment();
            fragmentTransaction.add(R.id.ly_content, fgLogined);
        } else {
            fragmentTransaction.show(fgLogined);
        }

        fragmentTransaction.commit();
    }

    FragmentTransaction fragmentTransaction;

    private void showActivityMap(){
        Intent intent = new Intent(this,MapActivity.class);
        startActivity(intent);
    }

    public void Click(View v){
        if(v.getId()==R.id.txt_better){
            showActivityMap();
            return;
        }
//        if(v.getId()==R.id.txt_nessage&&logined) {
//            setSelected();
//            txt_message.setSelected(true);
//            switchTofragment2Logined();
//            return;
//        }
        Log.v("TabActivity","点击");
        fragmentTransaction = fManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (v.getId()){
            case R.id.txt_channel:
            {
                setSelected();
                txt_channel.setSelected(true);
                if(fgfirst == null){
                    fgfirst = new firstFragment();
//                    fg1 = new MyFragment("First Fragment");
                    fragmentTransaction.add(R.id.ly_content,fgfirst);
                }
                else{
                    fragmentTransaction.show(fgfirst);
                }
                break;
            }
            case R.id.txt_settings:
            {
                Log.v("TabActivity","点击我的");
                setSelected();
                txt_setting.setSelected(true);
                if (fg2 == null) {
                    fg2 = new MyFragment();
                    fragmentTransaction.add(R.id.ly_content, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;
            }
            case R.id.txt_better:
            {
                setSelected();
                txt_better.setSelected(true);
                if(fg3 == null){
                    fg3 = MapFragment.newInstance(null,null);
//                    fragmentTransaction.add(R.id.ly_content,fg3);
                }
                else{
                    fragmentTransaction.show(fg3);
                }
                break;
            }
            case R.id.txt_message:
            {
                Log.v("TabActivity","点击了圈圈按钮");
                setSelected();
                txt_message.setSelected(true);
                if(fg4 == null){
                    fg4 = new QuanFragment();
                    fragmentTransaction.add(R.id.ly_content,fg4);
                }
                else{
                    fragmentTransaction.show(fg4);
                }
                break;
            }
            case R.id.button_my_login:
            {
                Log.v("TabActivity","点击登录");
                Toast.makeText(getApplicationContext(),"登录",Toast.LENGTH_SHORT);
                break;
            }
        }
        fragmentTransaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tab);
        fManager = getFragmentManager();
        bindViews();
        txt_channel.performLongClick();

        txt_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click(txt_channel);
            }
        });
        txt_setting.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                Click(txt_setting);
            }
        });
        txt_better.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                Click(txt_better);
            }
        });
        txt_message.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Click(txt_message);
            }
        });
        device_id = Installation.id(this);
        search_text = (EditText) findViewById(R.id.mainactivity_title_edit);
        search_button = (Button) findViewById(R.id.search_button);
//        search_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String search_content = search_text.getText().toString();
//                params = "device_id=" + device_id + "&name=" + search_content;
//                address = "http://172.25.130.248:8088/menu_search";
//                HttpUtil.sendHttpRequest(params, address, new HttpCallBackListener() {
//                    @Override
//                    public void Finish(String response) {
//                        parseJsonWithGson(response);
//                    }
//
//                    @Override
//                    public void OnError(Exception e) {
//                        Log.d("TabActivity",e.toString());
//                    }
//                });
//            }
//        });
        ArrayList<Fragment> data = new ArrayList<Fragment>();
        ininFragmentData(data);

        //切换至第一个标签
        fragmentTransaction = fManager.beginTransaction();
        hideAllFragment(fragmentTransaction);

        setSelected();
        txt_channel.setSelected(true);
        if(fgfirst == null){
            ImageView imageView;
            ImageView[] images;
            ViewGroup group;
            group = (ViewGroup) findViewById(R.id.pageGroup);
            fgfirst = new firstFragment();
//                    fg1 = new MyFragment("First Fragment");
            fragmentTransaction.add(R.id.ly_content,fgfirst);
        }
        else{
            fragmentTransaction.show(fgfirst);
        }

        fragmentTransaction.commit();
    }

    public void ininFragmentData(ArrayList<Fragment> data) {

        data.add(firstFragment.newInstance());
//        data.add(TwoFragmentItem.newInstance());
//        data.add(ThreeFragmentItem.newInstance());
//        data.add(FourFragmentItem.newInstance());
    }

    private void bindViews(){

        txt_channel = (TextView) findViewById(R.id.txt_channel);
        txt_message = (TextView) findViewById(R.id.txt_message);
        txt_better = (TextView) findViewById(R.id.txt_better);
        txt_setting = (TextView) findViewById(R.id.txt_settings);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

//        txt_channel.setOnClickListener(this);
//        txt_message.setOnClickListener(this);
//        txt_better.setOnClickListener(this);
//        txt_setting.setOnClickListener(this);
    }

    private void setSelected(){
        txt_message.setSelected(false);
        txt_channel.setSelected(false);
        txt_better.setSelected(false);
        txt_setting.setSelected(false);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)
            fragmentTransaction.hide(fg1);
        if(fg2 != null)
            fragmentTransaction.hide(fg2);
        if(fg3 != null)
            fragmentTransaction.hide(fg3);
        if(fg4 != null)
            fragmentTransaction.hide(fg4);
    }

    private void parseJsonWithGson(String jsonData){
        Gson gson = new Gson();
        List<searchResult> list = (List<searchResult>) gson.fromJson(jsonData,new TypeToken<List<menu>>(){}.getRawType());
        for(searchResult result:list){
            Log.d("TabActivity","id:"+result.getId());
            Log.d("TabActivity","name:"+result.getName());
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
