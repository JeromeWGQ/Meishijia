package com.example.lenovo.test2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by lenovo on 2016/7/17.
 */

public class detail1Fragment extends Fragment {
    View view;
    ListView mlistView;
    ListViewInOfData inOfData;
    String device_id;
    public static detail1Fragment newInstance() {

        Bundle args = new Bundle();

        detail1Fragment fragment = new detail1Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    public detail1Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,Bundle saveInstanceState){
        view = inflater.inflate(R.layout.detail1,container,false);
        device_id = Installation.id(getActivity());
        mlistView = (ListView) view.findViewById(R.id.evaluation);

//        inOfData = new ListViewInOfData(device_id);
//        inOfData.getArrayListEvaluate();
         return view;
    }
}
