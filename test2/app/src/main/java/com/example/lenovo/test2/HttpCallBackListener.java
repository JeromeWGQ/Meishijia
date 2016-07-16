package com.example.lenovo.test2;

/**
 * Created by lenovo on 2016/7/12.
 */
public interface HttpCallBackListener {
    void Finish(String response);
    void OnError(Exception e);
}
