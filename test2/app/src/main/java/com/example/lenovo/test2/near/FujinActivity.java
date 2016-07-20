package com.example.lenovo.test2.near;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lenovo.test2.MapActivity;
import com.example.lenovo.test2.R;

public class FujinActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fujin);
    }

    public void showMap(View v){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}
