package com.example.administrator.myhome.main_tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myhome.R;
import com.youth.banner.Banner;


public class Home_page extends AppCompatActivity {
    private Banner host_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        host_banner=(Banner)findViewById(R.id.host_banner);
    }
}
