package com.example.administrator.myhome.login_tab;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.administrator.myhome.R;
import com.example.administrator.myhome.my_view.Image_Identify_View;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Fast_Login extends AppCompatActivity implements View.OnClickListener,TextWatcher,View.OnFocusChangeListener{
    private EditText fast_phone_edit,captcha_image_edit,identify_sms;
    private ImageView clear_phone,clear_captcha,clear_sms;
    private ImageView captcha_image;
    private Button send_sms;
    private Button fast_login;
    private Image_Identify_View image_identify_view;//自定义图片验证码的类
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast__login);
        initView();
    }

    private void initView() {
        fast_phone_edit=(EditText)findViewById(R.id.fast_phone_edit);//手机号
        captcha_image_edit=(EditText)findViewById(R.id.captcha_image_edit);//图片验证码
        identify_sms=(EditText)findViewById(R.id.identify_sms);//手机短信验证码
        clear_phone=(ImageView)findViewById(R.id.clear_phone);//清空手机号
        clear_captcha=(ImageView)findViewById(R.id.clear_captcha);//清空图片验证码
        clear_sms=(ImageView)findViewById(R.id.clear_sms);//清空短信验证码
        captcha_image=(ImageView)findViewById(R.id.captcha_image);//发送图片验证码
        send_sms=(Button)findViewById(R.id.send_sms);//发送短信验证码
        fast_login=(Button)findViewById(R.id.fast_login);//登录

        clear_phone.setOnClickListener(this);
        clear_captcha.setOnClickListener(this);
        clear_sms.setOnClickListener(this);
        captcha_image.setOnClickListener(this);
        fast_phone_edit.addTextChangedListener(this);
        fast_phone_edit.setOnFocusChangeListener(this);
        captcha_image_edit.addTextChangedListener(this);
        captcha_image_edit.setOnFocusChangeListener(this);
        identify_sms.addTextChangedListener(this);
        identify_sms.setOnFocusChangeListener(this);

        send_sms.setOnClickListener(this);//发送短信
        fast_login.setOnClickListener(this);//登录

        image_identify_view=Image_Identify_View.getInstance();
        Bitmap bitmap=image_identify_view.createBitmap();
        captcha_image.setImageBitmap(bitmap);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clear_phone:
                fast_phone_edit.setText("");
                break;
            case R.id.clear_captcha:
                captcha_image_edit.setText("");
                break;
            case R.id.clear_sms:
                identify_sms.setText("");
                break;
            case R.id.captcha_image:
                image_identify_view=Image_Identify_View.getInstance();
                Bitmap bitmap=image_identify_view.createBitmap();
                captcha_image.setImageBitmap(bitmap);
                break;
            case R.id.send_sms:

                judgeImageIdentify();
                break;

        }
    }
    public void judgeImageIdentify(){

       String codeStr = captcha_image_edit.getText().toString().trim();
        Log.e("codeStr", codeStr);
        if (null == codeStr || TextUtils.isEmpty(codeStr)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;

        }
        String code = image_identify_view.getCode();
        Log.e("code", code);
        if (code.equalsIgnoreCase(codeStr)) {
            Toast.makeText(this, "验证码正确", Toast.LENGTH_SHORT).show();
//            getSmsIdentify();//获得短信验证码
        } else {
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
        }
    }

    private void getSmsIdentify() {
        OkHttpClient clients=new OkHttpClient();
        String number=fast_phone_edit.getText().toString().trim();
    RequestBody body=RequestBody.create(MediaType.parse("\"application/json; charset=utf-8\""),number);

        Request requese=new Request.Builder()
                .url("https://uhome.haier.net:7503/secuag/user/getMsgCode")
                .post(body)
                .build();
        Call call=clients.newCall(requese);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String results=response.body().string();
                Log.d("tag",results+"返回结果");
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (fast_phone_edit.hasFocus()){
            if (charSequence.length()>0){
                clear_phone.setVisibility(View.VISIBLE);
            }else {
                clear_phone.setVisibility(View.GONE);
            }
        }else if (captcha_image_edit.hasFocus()){
            if (charSequence.length()>0){
                clear_captcha.setVisibility(View.VISIBLE);
            }else {
                clear_captcha.setVisibility(View.GONE);
            }
        }else if (identify_sms.hasFocus()){
            if (charSequence.length()>0){
                clear_sms.setVisibility(View.VISIBLE);
            }else {
                clear_sms.setVisibility(View.GONE);
            }
        }else {
            clear_phone.setVisibility(View.GONE);
            clear_captcha.setVisibility(View.GONE);
            clear_sms.setVisibility(View.GONE);

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.fast_phone_edit:
                if (!b){
                    clear_phone.setVisibility(View.GONE);
                }else {
                    if (fast_phone_edit.getText().length()>0){
                        clear_phone.setVisibility(View.VISIBLE);
                    }
                }break;
            case R.id.captcha_image_edit:
                if (!b){
                    clear_captcha.setVisibility(View.GONE);
                }else {
                    if (captcha_image_edit.getText().length()>0){
                        clear_captcha.setVisibility(View.VISIBLE);
                    }
                }break;
            case R.id.identify_sms:
                if (!b){
                    clear_sms.setVisibility(View.GONE);
                }else {
                    if (identify_sms.getText().length()>0){
                        clear_sms.setVisibility(View.VISIBLE);
                    }
                }break;
        }
    }
}
