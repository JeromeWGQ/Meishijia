package com.example.lenovo.test2;

import android.app.Notification;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login2Activity extends AppCompatActivity {
    String device_id = null;
    EditText v_account = null;
    EditText v_password = null;
    Button login;
    Button register;
    String params = null;
//    String address = "http://123.206.64.143/login";
    int return_value = 0;
    String address = "http://172.25.129.45:8088/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        v_account = (EditText) findViewById(R.id.account);
       v_password = (EditText) findViewById(R.id.edit_password);
        device_id = Installation.id(this);

        login =(Button) findViewById(R.id.sign_in_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = v_account.getText().toString();
                String password = v_password.getText().toString();
                int value = attemptLogin(account, password);
                switch(value){
                    case 0:
                        v_account.setText("");
                        v_password.setText("");
                        Toast.makeText(Login2Activity.this,"请输入账号和密码",Toast.LENGTH_LONG).show();
                        break;
                    case -1:
                        v_account.setText("");
                        v_password.setText("");
                        Toast.makeText(Login2Activity.this,"用户不存在！",Toast.LENGTH_LONG).show();
                        break;
                    case -2:
                        v_password.setText("");
                        Toast.makeText(Login2Activity.this,"密码错误，请重新输入", Toast.LENGTH_LONG).show();
                    default:
                        break;
                }
            }
        });
        register = (Button) findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login2Activity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private int attemptLogin(String account,String password){
        if(account == null || password == null){
            return_value = 0;
            return return_value;
        }
        else{
            params = "deviceId=" + device_id + "&phone=" + account +"&password=" + MD5Util.getMD5String(password);
            HttpUtil.sendHttpRequest(params, address, new HttpCallBackListener() {
                @Override
                public void Finish(String response) {
                    int value = Integer.parseInt(response);
                    switch (value) {
                        case 1:{
                            Log.v("Login2Activity", "Success");
                            Intent intent = new Intent(Login2Activity.this, TabActivity.class);
                            startActivity(intent);
                            break;
                        }
                        case 0:{
                            Log.v("Login2Activity", "Unknown Error");
                            return_value = 0;
                            break;
                        }
                        case -1:{
                            Log.v("Login2Activity", "not Exist");
                            return_value = -1;
                            break;
                        }
                        case -2:{
                            Log.v("Login2Activity", "wrong password");
                            return_value = -2;
                            break;
                        }
                        default:
                            Log.v("Login2Activity", "Nothing!!!");
                    }
                }

                @Override
                public void OnError(Exception e) {
                    Log.d("Login2Activity","Wrong");
                }
            });
            return return_value;
        }
    }
}
