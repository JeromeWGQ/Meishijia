package com.example.lenovo.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    String device_id = null;
    EditText r_name = null;
    EditText r_account = null;
    EditText r_password = null;
    EditText confirm_password = null;
    String params = null;
//    String address = "http://123.206.64.143/register";
    String address = "http://172.25.129.45:8088/user_register";
    int return_value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        device_id = Installation.id(this);
        r_name = (EditText) findViewById(R.id.r_name);
        r_account = (EditText) findViewById(R.id.r_account);
        r_password = (EditText) findViewById(R.id.r_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);

        Button login = (Button) findViewById(R.id.sign_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,Login2Activity.class);
                startActivity(intent);
            }
        });
        Button register = (Button) findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = r_name.getText().toString();
                String account = r_account.getText().toString();
                String password = r_password.getText().toString();
                String con_password = confirm_password.getText().toString();

                int value = attemptRegister(name,account,password,con_password);
                switch (value){
                    case 0:
                        r_name.setText("");
                        r_account.setText("");
                        r_password.setText("");
                        confirm_password.setText("");
                        Toast.makeText(RegisterActivity.this,"请重新输入",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        r_account.setText("");
                        Toast.makeText(RegisterActivity.this,"请输入11位数字号码",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        confirm_password.setText("");
                        Toast.makeText(RegisterActivity.this,"确认密码与密码不相符",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private int attemptRegister(String name,String account,String password,String con_password){
        if(name == null || account == null || password == null || con_password == null){
            return_value = 0;
        }
         else if(!password.equals(con_password)){
            return_value = 3;
        }
        else if(account.length() != 11){
            return_value = 2;
        }
        else{
            params = "deviceId=" + device_id + "&name=" + name + "&phone=" + account + "&password=" + MD5Util.getMD5String(password);
            HttpUtil.sendHttpRequest(params, address, new HttpCallBackListener() {
                @Override
                public void Finish(String response) {
                    int value = Integer.parseInt(response);
                    switch(value){
                        case 1:
                            Log.v("RegisterActivity", "Success");
                            Intent intent = new Intent(RegisterActivity.this, Login2Activity.class);
                            startActivity(intent);
                            break;
                        case 0:
                            Log.v("RegisterActivity","failure");
                            return_value = 0;
                            break;
                        default:
                            return_value = 0;
                            break;
                    }
                }

                @Override
                public void OnError(Exception e) {
                    Log.d("RegisterActivity","OnError");
                }
            });
        }
        return return_value;
    }
}
