package com.example.lenovo.test2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    startActivity(new Intent(StartActivity.this,TabActivity.class));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
