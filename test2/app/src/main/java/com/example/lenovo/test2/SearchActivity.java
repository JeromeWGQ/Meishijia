package com.example.lenovo.test2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.lenovo.test2.firstFragment.setListViewHeightBasedOnChildren;

public class SearchActivity extends AppCompatActivity {
    private EditText search_text;
    private Button search_button;
    private ListView mlistView;
    String address = "http://172.25.132.12:8088/menu_search";
    String params;
    String deviceId = null;
    ListViewInOfData listData = new ListViewInOfData();
    ArrayList<SearchSuggest> listSearch = new ArrayList<SearchSuggest>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        deviceId = Installation.id(this);
        search_text = (EditText) findViewById(R.id.search_title_edit);
        search_button = (Button) findViewById(R.id.searchactivity_button);
        mlistView = (ListView) findViewById(R.id.you_search_listview);
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable != null) {
                   listSearch = listData.getSearchListInfo(deviceId, search_text.getText().toString());
//                    handler.sendMessage()

                }
            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listData.getSearchListInfo(deviceId,search_text.getText().toString());

            }
        });



        SearchListViewBaseAdapter listAdapter = new SearchListViewBaseAdapter(this);
        mlistView.setAdapter(listAdapter);
        listSearch = listData.getSearchListInfo(deviceId,search_text.getText().toString());
        listAdapter.setListData(listSearch);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listSearch.get(i).getId();
                String name = listSearch.get(i).getName();
                listData.getSearchListInfo(deviceId,name);
            }
        });
        setListViewHeightBasedOnChildren(mlistView);
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
            }
        }
    };
}
