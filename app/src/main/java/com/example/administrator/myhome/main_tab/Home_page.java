package com.example.administrator.myhome.main_tab;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.example.administrator.myhome.R;
import com.example.administrator.myhome.bean.Advertise;
import com.example.administrator.myhome.my_view.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Home_page extends AppCompatActivity {
    private List<Advertise.DataBean> beanList;
    private ConvenientBanner host_banner;
    private ImageView bannerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        host_banner=(ConvenientBanner)findViewById(R.id.host_banner);
        beanList=new ArrayList<>();

        initView();
    }

    private void initView() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://uhome.haier.net:7500/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Service server=retrofit.create(Service.class);

        server.getBanner("1001")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//指定onnext数据处理发生在主线程
                .subscribe(new Observer<Advertise>() {
                    @Override
                    public void onCompleted() {
                        host_banner.setPages(new CBViewHolderCreator() {
                            @Override
                            public Object createHolder() {
                                return new MyHoder();
                            }
                        },beanList)
                                .startTurning(2000)
                                .setPageIndicator(new int[]{R.drawable.white,R.drawable.black})
                                .setPointViewVisible(true)
                                .setManualPageable(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Advertise advertise) {
                        beanList.addAll(advertise.getData());

                    }
                });
    }



    private class MyHoder implements Holder<Advertise.DataBean>{
        @Override
        public View createView(Context context) {
            bannerImage = new ImageView(context);
            bannerImage.setScaleType(ImageView.ScaleType.FIT_XY);

            return bannerImage;
        }

        @Override
        public void UpdateUI(Context context, int position, Advertise.DataBean data) {
            Glide.with(context).load(data.getImageUrl()).into(bannerImage);
        }
    }
}
