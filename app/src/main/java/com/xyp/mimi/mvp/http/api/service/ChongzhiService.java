package com.xyp.mimi.mvp.http.api.service;

import com.xyp.mimi.mvp.http.entity.chongzhi.Chongzhi;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ChongzhiService {

    //余额充值    http://www.googleuservip.com/api/chong_google.asp
//需要参数    用户ID（useid）、充值账户（email）、GOOGLE账号（useurl）、GOOGLE密码（upwd）
    @FormUrlEncoded
    @POST("/api/chong_google.asp")
    Observable<BaseResponse<Object>> chongzhi(
            @Field("useid") String useid,
            @Field("email") String usename,
            @Field("useurl") String lname,
            @Field("lpwd") String lpwd
    );
    //    usdt充值    http://www.googleuservip.com/api/chong_usdt.asp
//    需要参数    用户ID（useid）、数量（sl）、充值账户（email）、收款USDT（usdt）、交易ID后六位（upwd）
    @FormUrlEncoded
    @POST("/api/chong_usdt.asp")
    Observable<BaseResponse<Object>> chongzhiusdt(
            @Field("useid") String useid,
            @Field("sl") String sl,
            @Field("email") String email,
            @Field("usdt") String usdt,
            @Field("upwd") String upwd
    );

    //    余额挂靠    http://www.googleuservip.com/api/buy_google.asp
//    需要参数    用户ID（useid）、数量（sl）、挂靠账户（email）、GOOGLE账号（useurl）、GOOGLE密码（upwd）
    @FormUrlEncoded
    @POST("/api/buy_google.asp")
    Observable<BaseResponse<Object>> guaYue(
            @Field("useid") String useid,
            @Field("sl") String sl,
            @Field("email") String email,
            @Field("useurl") String useurl,
            @Field("upwd") String upwd
    );


    //    usdt挂靠    http://www.googleuservip.com/api/buy_usdt.asp
//    需要参数    用户ID（useid）、数量（sl）、挂靠账户（email）、收款USDT（usdt）、交易ID后六位（upwd）
    @FormUrlEncoded
    @POST("/api/buy_usdt.asp")
    Observable<BaseResponse<Object>> guaUSDT(
            @Field("useid") String useid,
            @Field("sl") String sl,
            @Field("email") String email,
            @Field("usdt") String usdt,
            @Field("upwd") String upwd
    );

    @FormUrlEncoded
    @POST("/api/chongs.asp")
    Observable<Chongzhi> getChongzhi(
            @Field("useid") String useid

    );

}
