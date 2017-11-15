package com.example.administrator.myhome.main_tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myhome.R;
import com.example.administrator.myhome.bean.EndPoints;
import com.example.administrator.myhome.my_view.Service;
import com.example.administrator.myhome.retrofit.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Device_page extends AppCompatActivity implements OnClickListener{

    private Button retrofit_01;
    private Button retrofit_rxjava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_page);
        initView();
    }

    private void initView() {
        retrofit_01 = (Button)findViewById(R.id.retrofit_01);
        retrofit_rxjava = (Button)findViewById(R.id.retrofit_rxjava);
        retrofit_01.setOnClickListener(this);
        retrofit_rxjava.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.retrofit_01:
                retrofit_click();
                break;
            case R.id.retrofit_rxjava:
                retrofit_rxjavas();
                break;
        }
    }

    private void retrofit_click() {
        Service service= ServiceGenerator.createService(Service.class);
        Call<EndPoints> calls=service.getAllEndpoints("");
        calls.enqueue(new Callback<EndPoints>() {
            @Override
            public void onResponse(Call<EndPoints> call, Response<EndPoints> response) {
                        EndPoints endpoints=response.body();
                        String userpoint=endpoints.getUser_url();
                        Log.d("user","user_url-------------------user"+userpoint);
                Toast.makeText(Device_page.this, "user_url"+userpoint, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<EndPoints> call, Throwable t) {

            }
        });
    }
    private void retrofit_rxjavas() {
    }


}
