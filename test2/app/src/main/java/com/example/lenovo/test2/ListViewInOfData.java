package com.example.lenovo.test2;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by lenovo on 2016/7/14.
 */
public class ListViewInOfData {
    ArrayList<menu> arrayList = new ArrayList<menu>();
    ArrayList<stepDetail> arrayListStep = new ArrayList<stepDetail>();
    ArrayList<quan> arrayListQuan = new ArrayList<quan>();
    ArrayList<SearchSuggest> arrayListSearchSuggest = new ArrayList<SearchSuggest>();
    ArrayList<material> arrayListMaterail = new ArrayList<material>();
    ArrayList<evaluate> arrayListEvaluate = new ArrayList<evaluate>();

    public ArrayList<SearchSuggest> getArrayListSearchSuggest() {
        return arrayListSearchSuggest;
    }

    public void setArrayListSearchSuggest(ArrayList<SearchSuggest> arrayListSearchSuggest) {
        this.arrayListSearchSuggest = arrayListSearchSuggest;
    }

    String address;
    String params;

    public ArrayList<quan> getArrayListQuan() {
        return arrayListQuan;
    }

    public void setArrayListQuan(ArrayList<quan> arrayListQuan) {
        this.arrayListQuan = arrayListQuan;
    }

    public ArrayList<menu> getArrayList(){
        return arrayList;
    }
    public ArrayList<stepDetail> getArrayListStep(){
        return arrayListStep;
    }
    public void setArrayList(ArrayList<menu> arrayList){
        this.arrayList = arrayList;
    }
    public void setArrayListStep(ArrayList<stepDetail> arrayListStep){
        this.arrayListStep = arrayListStep;
    }
    public ArrayList<menu> getArrayListViewInfo(final String deviceId,HttpCallBackListener listener){
        address = "http://172.25.132.12:8088/menu";
        params = "deviceId=" + deviceId + "&page=0&amount=10";
        HttpUtil.sendHttpRequest(params, address,listener );
        return arrayList;
//        menu list_menu = new menu();
//        list_menu.setImg(String.valueOf(R.mipmap.baizhuoxia));
//        list_menu.setName("白灼虾");
//        list_menu.setAuthor("江宝宝");
//        arrayList.add(list_menu);
//
//        list_menu = new menu();
//        list_menu.setImg(String.valueOf(R.mipmap.songrenyumi));
//        list_menu.setName("松仁玉米");
//        list_menu.setAuthor("小雪花儿");
//        arrayList.add(list_menu);
//
//        list_menu = new menu();
//        list_menu.setImg(String.valueOf(R.mipmap.lizhirou));
//        list_menu.setName("荔枝肉");
//        list_menu.setAuthor("国桥");
//        arrayList.add(list_menu);
//
//        list_menu = new menu();
//        list_menu.setImg(String.valueOf(R.mipmap.shuiguosu));
//        list_menu.setName("千层水果酥");
//        list_menu.setAuthor("达哥");
//        arrayList.add(list_menu);
//
//        list_menu = new menu();
//        list_menu.setImg(String.valueOf(R.mipmap.yuwan));
//        list_menu.setName("鱼丸");
//        list_menu.setAuthor("Haiyang 张");
//        arrayList.add(list_menu);
//
//        list_menu = new menu();
//        list_menu.setImg(String.valueOf(R.mipmap.meishi));
//        list_menu.setName("水果宴");
//        list_menu.setAuthor("tanxilu");
//        arrayList.add(list_menu);
//        return arrayList;
    }
    public ArrayList<SearchSuggest> getSearchListInfo(String deviceId,String name){
        address = "http://172.25.132.12:8088/menu_search";
        params="deviceId=" + deviceId + "&name=" + name;
        HttpUtil.sendHttpRequest(address, params, new HttpCallBackListener() {
            @Override
            public void Finish(String response) {
                arrayListSearchSuggest = parseJsonWithGson(response,1);
            }

            @Override
            public void OnError(Exception e) {

            }
        });
        return arrayListSearchSuggest;
    }
    public stepDetail getArrayListOf(String deviceId,int currentId){
        menu selected = arrayList.get(currentId);

        address = "http://172.25.132.12:8088/material";
        params="$deviceId=" + deviceId + "&id=" + currentId;
        HttpUtil.sendHttpRequest(address, params, new HttpCallBackListener() {
            @Override
            public void Finish(String response) {
                arrayListMaterail = parseJsonWithGson(response,2);
            }

            @Override
            public void OnError(Exception e) {

            }
        });
        stepDetail list_step = new stepDetail();
        list_step.description = selected.getDiscription();
        list_step.materials = arrayListMaterail;
        list_step.image = selected.getImg();
//        stepDetail list_step = new stepDetail();
//        list_step.setImage(String.valueOf(R.mipmap.s0));
//        list_step.setStepDesc("准备好食材");
//        arrayListStep.add(list_step);
//
//        list_step = new stepDetail();
//        list_step.setImage(String.valueOf(R.mipmap.s1));
//        list_step.setStepDesc("鸡翅用盐和红酒抹匀腌制半个小时");
//        arrayListStep.add(list_step);
//
//        list_step = new stepDetail();
//        list_step.setImage(String.valueOf(R.mipmap.s2));
//        list_step.setStepDesc("黄油放入锅内烧热");
//        arrayListStep.add(list_step);
//
//        list_step = new stepDetail();
//        list_step.setImage(String.valueOf(R.mipmap.s3));
//        list_step.setStepDesc("将腌制好的琵琶腿用厨房用纸把表面的水分吸干，放入锅内煎制两面金黄色后盛出");
//        arrayListStep.add(list_step);
//
//        list_step = new stepDetail();
//        list_step.setImage(String.valueOf(R.mipmap.s4));
//        list_step.setStepDesc("把洋葱，鲜蘑菇，土豆，部分番茄和胡萝卜切成小块，放入油锅中炒香");
//        arrayListStep.add(list_step);
//
//        list_step = new stepDetail();
//        list_step.setImage(String.valueOf(R.mipmap.s5));
//        list_step.setStepDesc("把煎好的鸡腿铺在蔬菜上面，用盐、红葡萄酒‘蚝油调制的酱汁均匀浇进锅里烧开后关火");
//        arrayListStep.add(list_step);
//
//        list_step = new stepDetail();
//        list_step.setImage(String.valueOf(R.mipmap.s6));
//        list_step.setStepDesc("把鸡腿的边缘放上整个的蒜和串番茄，撒上黑胡椒粉");
//        arrayListStep.add(list_step);
//
//        list_step = new stepDetail();
//        list_step.setImage(String.valueOf(R.mipmap.s7));
//        list_step.setStepDesc("盖上锅盖，烤箱预热200度，放入烤箱烤20分钟后取出将鸡腿翻面，在放入小朵西兰花，在洒些黑胡椒碎，盖上锅盖送入烤箱再烤10分钟即可");
//        arrayListStep.add(list_step);
//
//        list_step = new stepDetail();
//        list_step.setImage(String.valueOf(R.mipmap.s8));
//        list_step.setStepDesc("成品");
//        arrayListStep.add(list_step);

        return list_step;
    }

//    public ArrayList<evaluate> getArrayListEvaluate(){
//
//    }

    public ArrayList<quan> getArrayListOfQuan(){
        quan quan = new quan();
        quan.setAuthor_img(String.valueOf(R.mipmap.touxiang0));
        quan.setContent_img(String.valueOf(R.mipmap.content0));
        quan.setAuthor_name("江宝宝");
        quan.setContent("我最好看");
        arrayListQuan.add(quan);

        quan = new quan();
        quan.setAuthor_img(String.valueOf(R.mipmap.touxiang1));
        quan.setContent_img(String.valueOf(R.mipmap.content1));
        quan.setAuthor_name("江宝宝");
        quan.setContent("我最好看");
        arrayListQuan.add(quan);

        quan = new quan();
        quan.setAuthor_img(String.valueOf(R.mipmap.touxiang2));
        quan.setContent_img(String.valueOf(R.mipmap.content2));
        quan.setAuthor_name("江宝宝");
        quan.setContent("我最好看");
        arrayListQuan.add(quan);

        return arrayListQuan;
    }



    public synchronized static ArrayList parseJsonWithGson(String jsonData, int caseNum){
        Gson gson = new Gson();
        ArrayList listtest = new ArrayList();
        switch (caseNum){
            case 0:
                ArrayList<menu> am = new ArrayList<menu>();
//                ArrayList<menu> listmenu = gson.fromJson(jsonData, ArrayList.class);
                ArrayList<menu> listmenu = (ArrayList<menu>) gson.fromJson(jsonData, new TypeToken<ArrayList<menu>>(){}.getRawType());
//                for(menu m: listmenu){
//                    m.getId();
//                }
//                for(int i =0;i<listmenu.size();i++){
//                    am.get(i).setId();
//                }
                return listmenu;
            case 1:
                ArrayList<SearchSuggest> listsuggest = (ArrayList<SearchSuggest>) gson.fromJson(jsonData,new TypeToken<ArrayList<SearchSuggest>>(){}.getRawType());
                return listsuggest;
            case 2:
                ArrayList<material> materials = (ArrayList<material>) gson.fromJson(jsonData,new TypeToken<ArrayList<material>>(){}.getRawType());
                return materials;
            default:
                return listtest;
        }
//        List<menu> list = (List<menu>) gson.fromJson(jsonData,new TypeToken<List<menu>>(){}.getRawType());
//        for (menu menu:list){
//            if(name == menu.getName()){
//                description = menu.getDiscription();
//            }
//            Log.d("DetailActivity","id:" + menu.getId());
//            Log.d("DetailActivity","name:"+ menu.getName());
//            Log.d("DetailActivity", "price:" + menu.getPrice());
//            Log.d("DetailActivity", "resource:" + menu.getRes());
//            Log.d("DetailActivity","discription:"+menu.getDiscription());
//        }
    }

}
