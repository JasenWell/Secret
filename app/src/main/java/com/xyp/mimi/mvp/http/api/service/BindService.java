package com.xyp.mimi.mvp.http.api.service;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.product.Product;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BindService {

    @FormUrlEncoded
    @POST("/api/bangding.asp")
    Observable<BaseResponse<Object>> bindUse(
            @Field("useid") String useid,
            @Field("acc") String acc,
            @Field("lmpwd") String impwd

    );


    @FormUrlEncoded
    @POST("/api/zhanghaos.asp")
    Observable<Product> getRecommendedProducts(
            @Field("useid") String useid
    );



    @FormUrlEncoded
    @POST("/api/zhanghao_del.asp")
    Observable<Product> delete(
            @Field("useid") String useid,
            @Field("id") String id
    );


}
