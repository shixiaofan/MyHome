package com.example.administrator.myhome;

import android.util.Log;

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
                .addHeader("appId","MB-UZHSH-0000")
                .addHeader("appKey","f50c76fbc8271d361e1f")
                .addHeader("appVersion","V0.5.01_0228")
                .addHeader("clientId","2014022801010")
                .addHeader("timestamp","20160111141341")
                .addHeader("accessToken","TGTRZAX9DYUYJT62RAIL")
                .addHeader("sign","1")
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Log.d("qqqqqqqqqpp", "onResponse: 成功"+response);
        if (response.isSuccessful()) {
            String result = response.body().string();
            Log.d("sss==resultresult",result);
            return result;
        }

    return result;

    }
    public String bolwingJson(String loginId, String password,String loginType,String accType) {
        return "{'loginId':" + loginId + "," + "'password':" + password + "," + "'loginType':" + loginType + "," + "'accType':" + accType + "}";
    }



}