package com.example.lenovo.test2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lenovo on 2016/7/12.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String parmas,final String address,final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes(parmas);
                    int responseCode = connection.getResponseCode();
                    if(responseCode == 200){
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while((line = reader.readLine())!= null){
                            response.append(line);
                        }
                        if(listener != null){
                            listener.Finish(response.toString());
                        }
                    }
                }catch (Exception e){
                    if(listener != null){
                        listener.OnError(e);
                    }
                }finally {
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
