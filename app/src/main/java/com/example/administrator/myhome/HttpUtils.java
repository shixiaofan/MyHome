package com.example.administrator.myhome;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    private String result;
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public String login(String url, String json) throws IOException {
        //把请求的内容字符串转换为json
        final RequestBody body = RequestBody.create(JSON, json);
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            return result;
        }
    return result;
    }


    public String bolwingJson(String loginId, String password) {
        return "{'username':" + loginId + "," + "'password':" + password + "}";
        //     "{'username':" + username + ","+"'password':"+password+"}";
    }


}