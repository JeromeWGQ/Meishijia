package com.example.lenovo.test2.near;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.test2.MapActivity;
import com.example.lenovo.test2.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.lenovo.test2.firstFragment.setListViewHeightBasedOnChildren;

public class FujinActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fujin);

        showContent();
    }

//    private class NormalPostRequest extends Request<JSONObject> {
//        private Map<String, String> mMap;
//        private Response.Listener<JSONObject> mListener;
//
//        public NormalPostRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Map<String, String> map) {
//            super(Request.Method.POST, url, errorListener);
//            mListener = listener;
//            mMap = map;
//        }
//
//        //mMap是已经按照前面的方式,设置了参数的实例
//        @Override
//        protected Map<String, String> getParams() throws AuthFailureError {
//            return mMap;
//        }
//
//        //此处因为response返回值需要json数据,和JsonObjectRequest类一样即可
//        @Override
//        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
//            try {
//                String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//                return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
//            } catch (UnsupportedEncodingException e) {
//                return Response.error(new ParseError(e));
//            } catch (JSONException je) {
//                return Response.error(new ParseError(je));
//            }
////            return null;
//        }
//
//        @Override
//        protected void deliverResponse(JSONObject response) {
//            mListener.onResponse(response);
//        }
//    }

    private MyAdapter adapter;
    private ListView list;

    private void showContent() {
        list = (ListView) findViewById(R.id.fujin_listview);
        adapter = new MyAdapter(this);
        list.setAdapter(adapter);
        /*获取数据*/
        getCurrentPosition();
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("logitude", currentLog + "");
        hashMap.put("latitude", currentLat + "");
        hashMap.put("bound", 1 + "");

        //客户端以普通的post方式进行提交,服务端返回字符串
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //http://172.25.132.12:8088/shop_in_bound
        //
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://10.0.2.2/fake/fujin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d(TAG, "response -> " + response);
                        insertItems(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FujinActivity", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("logitude", currentLog + "");
                map.put("latitude", currentLat + "");
                map.put("bound", 1 + "");
                return map;
            }
        };
        requestQueue.add(stringRequest);

//        requestQueue.add(newMissRequest);
//        Request<JSONObject> request = new NormalPostRequest("http://172.25.132.12:8088/shop_in_bound",
//        Request<JSONObject> request = new NormalPostRequest("http://10.0.2.2/fake/fujin.php",
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        responseJson = response.toString();
////                        Log.d(TAG, "response -> " + response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                responseJson="error";
//                Log.e(TAG, error.getMessage(), error);
//            }
//        }, hashMap);
        //requestQueue.add(request);
        /*并插入*/
        adapter.insert("益民餐厅", "北京交通大学东门", 0.0, 0.0, 3.0, 4.0);
        adapter.insert("巫山烤鱼", "北京交通大学南门", 0.0, 0.0, 5.0, 7.0);
        setListViewHeightBasedOnChildren(list);
    }

    private void insertItems(String json){
        Gson gson = new Gson();
        ArrayList<OneResponse> listRes = new ArrayList<OneResponse>();
        listRes = gson.fromJson(json,new TypeToken<ArrayList<OneResponse>>(){}.getType());
        for(OneResponse one:listRes){
            adapter.insert(one.getName(), one.getAddress(), currentLog, currentLat, one.getLogitude(), one.getLatitude());
        }
        setListViewHeightBasedOnChildren(list);
    }

    private double currentLog, currentLat;

    private void getCurrentPosition() {
        currentLog = 0.0;
        currentLat = 0.0;
    }

    public void showMap(View v) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

}
