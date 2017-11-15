package com.example.administrator.myhome;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    private String isReg;
    private String result;
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * --------------------------------------------------------------------------------------------------------普通登录
     * */
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


    /**
     * --------------------------------------------------------------------------------------------------发送短信验证码
     * */
    public String sendSms(String url, String json) throws IOException {
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
        Log.d("sendSms", "sendSms_onResponse: 成功"+response);
        if (response.isSuccessful()) {
            String result = response.body().string();
            Log.d("sendSms==result", result);
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(result);
                JSONObject datas = jsonObj.getJSONObject("data");
                isReg = datas.getString("isReg");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("isReg", isReg);

            return isReg;
        }
        return isReg;
    }

    /**
     * --------------------------------------------------------------------------------------------------快速登录
     * */
    public String FastLogin(String url, String msgCode,String mobile) throws IOException, JSONException {
        //把请求的内容字符串转换为json
        FormBody formBody=new FormBody.Builder()
                .add("mobile",mobile)
                .add("msgCode",msgCode)
                .add("isReg","0")
                .build();
        final Request request = new Request.Builder()
                .addHeader("appId","MB-UZHSH-0000")
                .addHeader("appKey","f50c76fbc8271d361e1f")
                .addHeader("appVersion","V0.5.01_0228")
                .addHeader("clientId","2014022801010")
                .addHeader("timestamp","20160111141341")
                .addHeader("accessToken","TGTRZAX9DYUYJT62RAIL")
                .addHeader("sign","1")
                .url(url)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        Log.d("fastresponse", "onResponse: 成功"+response);
        if (response.isSuccessful()) {
            String result = response.body().string();
            Log.d("smslogin==result","smslogin"+result);
            return result;
        }
        return result;

    }


    /**
     * --------------------------------------------------------------------------------------------------首页广告轮播
     * */





    public String bolwingJson(String loginId, String password,String loginType,String accType) {
        return "{'loginId':" + loginId + "," + "'password':" + password + "," + "'loginType':" + loginType + "," + "'accType':" + accType + "}";
    }
public String smsIdentify(String mobile,int type){
    return "{'mobile':" + mobile + "," + "'type':" + type + "}";
}
public String FastJson(String mobile,String smsCode,String isReg){
    return "{'mobile':" + mobile + "," + "'smsCode':" + smsCode  + "," + "'isReg':" + isReg +  "}";
}

}