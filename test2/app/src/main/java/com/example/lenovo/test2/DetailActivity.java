package com.example.lenovo.test2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static com.example.lenovo.test2.firstFragment.setListViewHeightBasedOnChildren;

public class DetailActivity extends AppCompatActivity {
    String device_id = null;
    String params = null;
    int page = 1;
    int amount = 2;
    String address = "http://172.25.130.248:8088/menu";
    String name = "番茄炒蛋";
    String resource = null;
    String description = null;
    ListView mlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        StrictMode.setThreadPolicy(new
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        device_id = Installation.id(this);
        params = "device_id=" + device_id + "&page=" + page + "&amount=" + amount;
        HttpUtil.sendHttpRequest(params, address, new HttpCallBackListener() {
            @Override
            public void Finish(String response) {
                parseJsonWithGson(response);
            }

            @Override
            public void OnError(Exception e) {
                Log.v("DetailActivity","wrong");
            }
        });



        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        table.setStretchAllColumns(true);
        String[] ingredient = {"胡萝卜","西红柿","鸡蛋"};
        String[] num = {"1根","1个","2个"};
        int rowNum = ingredient.length;
        for(int i = 0;i<rowNum;i++)
        {
            TableRow tableRow = new TableRow(this);
            tableRow.setBackgroundColor(Color.rgb(250,255,240));
            for(int j=0;j<1;j++)
            {
                TextView textView1 = new TextView(this);
                TextView textView2 = new TextView(this);
                textView1.setText(ingredient[i]);
                textView1.setGravity(1);
                textView2.setText(num[i]);
                textView2.setGravity(1);
                tableRow.addView(textView1);
                tableRow.addView(textView2);
            }
            table.addView(tableRow,new TableLayout.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }

        mlistView = (ListView) findViewById(R.id.step_detail);
        DetailStepListViewBaseAdapter detailAdapter = new DetailStepListViewBaseAdapter(this);
        mlistView.setAdapter(detailAdapter);

        ListViewInOfData inOfData = new ListViewInOfData();
        detailAdapter.setListData(inOfData.getArrayListOf());
        setListViewHeightBasedOnChildren(mlistView);
    }

    private void parseJsonWithGson(String jsonData){
        Gson gson = new Gson();
        List<menu> list = (List<menu>) gson.fromJson(jsonData,new TypeToken<List<menu>>(){}.getRawType());
        for (menu menu:list){
            if(name == menu.getName()){
                description = menu.getDiscription();
            }
            Log.d("DetailActivity","id:" + menu.getId());
            Log.d("DetailActivity","name:"+ menu.getName());
            Log.d("DetailActivity", "price:" + menu.getPrice());
            Log.d("DetailActivity", "resource:" + menu.getRes());
            Log.d("DetailActivity","discription:"+menu.getDiscription());
        }
    }
}
