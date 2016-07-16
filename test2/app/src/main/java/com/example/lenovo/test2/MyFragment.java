package com.example.lenovo.test2;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lenovo on 2016/7/9.
 */

public class MyFragment extends Fragment {

    private String content;

    private Activity fatherActivity;

    private int id = 0;

    public MyFragment() {
    }


//    public MyFragment(String content, TabActivity fatherActivity) {
//        this.content = content;
//        this.fatherActivity = fatherActivity;
//    }
//
//    public MyFragment(String content, TabActivity fatherActivity, int id) {
//        this.content = content;
//        this.fatherActivity = fatherActivity;
//        this.id = id;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content, container, false);



//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TabActivity.this,R.layout.fg_content,data);
        //TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        //txt_content.setText(content);
//        ListView listView = (ListView) view.findViewById(R.id.list_view);
        return view;
    }

    public void onClickLogin(){

    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        Button button = (Button) getActivity().findViewById(R.id.button_my_login);
////        button.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(getActivity(), "点击登录", Toast.LENGTH_LONG).show();
////            }
////        });
//    }

//    public void onClick(View view){
//        Log.v("MyFragment","点击fragment");
//        switch(view.getId()){
//            case R.id.button_my_login:
//            {
//                Toast.makeText(fatherActivity,"登录",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(fatherActivity,LoginActivity.class);
//            }
//        }
//    }

}

//public class MyFragment extends Fragment{
//    private String content;
//
//    public MyFragment(String content){
//        this.content = content;
//    }
//
//    @Override
//    public View OnCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){
////       super.onCreateView(inflater,container,saveInstanceState);
//        View view = inflater.inflate(R.layout.fg_content,container,false);
//        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
//        txt_content.setText(content);
//        return view;
//    }
//
//}
