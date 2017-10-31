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

import com.example.administrator.myhome.login_tab.Fast_Login;
import com.example.administrator.myhome.login_tab.Normal_Login;
public class Login extends TabActivity {
    private TabHost tabhost;
    private ImageView login_return;
    private TextView regist;
    private String[] login_tab={"普通登录","快速登录"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        tabhost=getTabHost();
        addTabSpec(new int[]{0,1},null,null,getintent());
        tabhost.setCurrentTab(0);
        changeImage(0);
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
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
            TextView text=(TextView)v.findViewById(R.id.select_text);
            if (i==pos){
                text.setText(login_tab[i]);
                text.setTextColor(Color.BLUE);
            }else{
                text.setText(login_tab[i]);
                text.setTextColor(Color.BLACK);
            }
        }
    }

    private void addTabSpec(int[] is, int[] noClick1, String[] tab_taxtname1, Intent[] getintent){
        for (int i=0;i<is.length;i++){
            View v=View.inflate(Login.this,R.layout.select,null);
            tabhost.addTab(tabhost.newTabSpec(is[i]+"")
                    .setIndicator(v)
                    .setContent(getintent[i]));
        }
    }
    private Intent[] getintent(){
        Intent tab1=new Intent(Login.this, Normal_Login.class);
        Intent tab2=new Intent(Login.this, Fast_Login.class);
        return new Intent[]{tab1,tab2};
    }

}
