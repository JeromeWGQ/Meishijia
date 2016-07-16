package com.example.lenovo.test2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class UploadActivity extends Activity {

    private ArrayList<Ingredient> listInd = new ArrayList<Ingredient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Button addButton = (Button) findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clicked();
                EditText text1, text2, text3;
                text1 = (EditText) findViewById(R.id.editText3);
                text2 = (EditText) findViewById(R.id.editText4);
                text3 = (EditText) findViewById(R.id.editText5);
                String n, p, nu;
                n = text1.getText().toString();
                p = text2.getText().toString();
                nu = text3.getText().toString();
                if (n.equals("") || p.equals("") || nu.equals(""))
                    return;
                addOneIngredient(n, p, nu);
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadMenu();
            }
        });
    }

    private void clicked() {
        Toast.makeText(this, "按钮被点击", Toast.LENGTH_SHORT).show();
    }

    public void addOneIngredient(String n, String p, String nu) {
        listInd.add(new Ingredient(n, p, nu));
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
    }

    private int result = 0;

    public int uploadMenu() {
        UploadMenu returnMenu = new UploadMenu();
        returnMenu.resource = this.listInd;
        String name = ((EditText) findViewById(R.id.textName)).getText().toString();
        String content = ((EditText) findViewById(R.id.editText6)).getText().toString();
        returnMenu.name = name;
        returnMenu.method = content;
        Gson gson = new Gson();
        String uploadJson = gson.toJson(returnMenu);

        HttpUtil.sendHttpRequest(uploadJson, "172.25.130.248:8088/menu_upload", new HttpCallBackListener() {
            @Override
            public void Finish(String response) {
                result = 1;
            }

            @Override
            public void OnError(Exception e) {
                result = 0;
            }
        });

        switch (result){
            case 0:Toast.makeText(this,"上传失败", Toast.LENGTH_SHORT).show();break;
            case 1:Toast.makeText(this,"上传成功", Toast.LENGTH_SHORT).show();break;
        }
        return result;
    }

}
