package com.example.lenovo.test2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MyLoginedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_logined);
    }

    public void uploadClicked(View v) {
        Toast.makeText(this, "上传菜谱", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
    }

    public void settingClicked(View v) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
