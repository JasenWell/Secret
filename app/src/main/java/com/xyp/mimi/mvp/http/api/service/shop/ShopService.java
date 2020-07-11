package com.xyp.mimi.mvp.http.api.service.shop;

import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ShopService {

    //商家直播手机号登录
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/TencentCloud/ShopLiveByMobile")
    Observable<BaseResponse<Object>> shopLiveLogin(
    );


    //店铺列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Shop/ShopList")
    Observable<BaseResponse<Object>> getShopList(

    );


    //获取门店详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Shop/ReadShop")
    Observable<BaseResponse<Object>> getShopDetail(

    );
}
