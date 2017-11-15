package com.example.administrator.myhome.my_view;

import com.example.administrator.myhome.bean.Advertise;
import com.example.administrator.myhome.bean.EndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public interface Service {
    @GET("/emuplus/secuag/advert/{adver}/getAdvertInfo")
    Observable<Advertise> getBanner(@Path("adver") String adver) ;

    @GET
    Call<EndPoints> getAllEndpoints(@Url String url);

}
