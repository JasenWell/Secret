package com.xyp.mimi.mvp.http.api.service.main;

import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MainService {


    //获取广告图
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Banner/BannerList")
    Observable<BaseResponse<Object>> getBannber(
    );


    //更新广告图点击量
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Banner/BannerHits")
    Observable<BaseResponse<Object>> addBannberHits(

    );


    //根据定位城市名称获取城市代码
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Area/GetCityCode")
    Observable<BaseResponse<Object>> getCityCode(

    );


}
