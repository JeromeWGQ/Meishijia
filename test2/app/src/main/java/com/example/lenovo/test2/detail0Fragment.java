package com.example.lenovo.test2;

//import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.lenovo.test2.firstFragment.setListViewHeightBasedOnChildren;

/**
 * Created by lenovo on 2016/7/17.
 */

public class detail0Fragment extends Fragment {
    View view;
    String device_id;
    String name = "番茄炒蛋";
    stepDetail step;
    ListViewInOfData inOfData;

    public static detail0Fragment newInstance() {

        Bundle args = new Bundle();

        detail0Fragment fragment = new detail0Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    public detail0Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.detail0,container,false);
        TableLayout table = (TableLayout) view.findViewById(R.id.tableLayout);
        TextView description = (TextView) view.findViewById(R.id.description);
        inOfData = new ListViewInOfData();
        device_id = Installation.id(getActivity());
        step = inOfData.getArrayListOf(device_id,DetailActivity.currentId);
        table.setStretchAllColumns(true);
        String[] ingredient = null;
        String[] num = null;
        for(int i = 0;i<step.materials.size();i++){
            ingredient[i] = step.getMaterials().get(i).getName();
            num[i] = "1个";
        }
        int rowNum = ingredient.length;
        for(int i = 0;i<rowNum;i++){
            TableRow tableRow = new TableRow(container.getContext());
            tableRow.setBackgroundColor(Color.rgb(250,255,240));
            for(int j=0;j<1;j++)
            {
                TextView textView1 = new TextView(container.getContext());
                TextView textView2 = new TextView(container.getContext());
                textView1.setText(ingredient[i]);
                textView1.setGravity(1);
                textView2.setText(num[i]);
                textView2.setGravity(1);
                tableRow.addView(textView1);
                tableRow.addView(textView2);
            }
            table.addView(tableRow,new TableLayout.LayoutParams( TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }
        description.setText(step.getDescription());


        return view;
    }

}