package com.example.administrator.myhome;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.administrator.myhome.main_tab.Community_page;
import com.example.administrator.myhome.main_tab.Device_page;
import com.example.administrator.myhome.main_tab.Find_page;
import com.example.administrator.myhome.main_tab.Home_page;

public class MainActivity extends TabActivity {
    private TabHost tabHost;
    private int[] noClick={R.drawable.shouye_no_click,R.drawable.shebei_no_click,R.drawable.shequn_no_click,R.drawable.faxian_no_click,R.drawable.my_no_click};
    private int[] mclick={R.drawable.shouye_click,R.drawable.shebei_click,R.drawable.shequn_click,R.drawable.faxian_click,R.drawable.my_click};
    private String[] tab_taxtname={"首页","设备","社群","发现","我"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        tabHost=getTabHost();
        addTabSpec(new int[]{0,1,2,3,4},null,null,getintent());
        tabHost.setCurrentTab(0);
        changeImage(0);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int pos=Integer.parseInt(s);
                changeImage(pos);
            }
        });
    }

    private void changeImage(int pos) {
        TabWidget tabWidget=getTabWidget();
        for (int i=0;i<tabWidget.getChildCount();i++){
            View v=tabWidget.getChildAt(i);
            TextView text=(TextView)v.findViewById(R.id.tab_select_text);
            ImageView image=(ImageView)v.findViewById(R.id.tab_select_image);
            if (i==pos){
                text.setText(tab_taxtname[i]);
                text.setTextColor(Color.BLUE);
                image.setBackgroundResource(mclick[i]);
            }else{
                text.setText(tab_taxtname[i]);
                text.setTextColor(Color.BLACK);
                image.setBackgroundResource(noClick[i]);
            }
        }
    }

    private void addTabSpec(int[] is, int[] noClick1, String[] tab_taxtname1, Intent[] getintent){
        for (int i=0;i<is.length;i++){
            View v=View.inflate(MainActivity.this,R.layout.tab_select,null);
            tabHost.addTab(tabHost.newTabSpec(is[i]+"")
                                                .setIndicator(v)
                                                .setContent(getintent[i]));
        }
    }
    private Intent[] getintent(){
        Intent tab1=new Intent(MainActivity.this, Home_page.class);
        Intent tab2=new Intent(MainActivity.this, Device_page.class);
        Intent tab3=new Intent(MainActivity.this, Community_page.class);
        Intent tab4=new Intent(MainActivity.this, Find_page.class);
        Intent tab5=new Intent(MainActivity.this, Login.class);
        return new Intent[]{tab1,tab2,tab3,tab4,tab5};
    }
}
