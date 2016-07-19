package com.example.lenovo.test2.personalInf;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.test2.Installation;
import com.example.lenovo.test2.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ModifyInfAcvivity extends Activity {

    private String deviceId = "";

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_inf_acvivity);

        //获取phone并显示在街面上
        phone = "987654321";
        //获取deviceId
        deviceId = Installation.id(this);
        //获取并更新用户头像
        //getProfile();

    }

//    private void getProfile() {
//        //获取头像按钮
//        Button profileBtn = (Button)findViewById(R.id.button3);
//        profileBtn.setBackground();
//    }

    public void onClickUpload(View v) {
        Thread oneThread = new Thread(new Runnable() {
            @Override
            public void run() {
                uploadInf();
            }
        });
        oneThread.start();
    }

    String name,mail;
    boolean sex;
    int year, month, day;

    private boolean uploadInf() {
        /*获取输入信息*/
        name = ((AutoCompleteTextView) findViewById(R.id.r_name)).getText().toString();
        mail = ((AutoCompleteTextView) findViewById(R.id.mail_name)).getText().toString();
        sex = ((RadioButton) findViewById(R.id.radioButton)).isSelected();
        year = Integer.parseInt(((EditText) findViewById(R.id.editText)).getText().toString());
        month = Integer.parseInt(((EditText) findViewById(R.id.editText2)).getText().toString());
        day = Integer.parseInt(((EditText) findViewById(R.id.editText7)).getText().toString());
        /*开始HTTP请求*/
        String url = "123.206.64.143/user_update";
//        //建立HTTP POST连线
//        HttpPost httpRequest = new HttpPost(url);
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("deviceid", deviceId));
//        params.add(new BasicNameValuePair("name", name));
//        params.add(new BasicNameValuePair("phone", phone));
//        //上传头像文件
//        //FileBody fileBody = new FileBody(new File(R.mipmap.profileDefault));
//
//        params.add(new BasicNameValuePair("mail", mail));
//        params.add(new BasicNameValuePair("birthday", year+"-"+month+"-"+day));
//        params.add(new BasicNameValuePair("ssex",sex?"男":"女"));

        /*Http Post*/
//        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.v("ModifyInfActivity",s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.v("ModifyInfActivity",volleyError.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<String,String>();
                map.put("deviceid", deviceId);
                map.put("name", name);
                map.put("phone", phone);
                map.put("mail", mail);
                map.put("birthday", year+"-"+month+"-"+day);
                map.put("ssex",sex?"男":"女");
                return map;
            }
        };

        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        mQueue.add(stringRequest);

        //params.add(new BasicNameValuePair("img", name));
        return true;
    }
}
