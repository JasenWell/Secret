package com.xyp.mimi.mvp.http.api.service.coupon;

import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CouponService {
    //我的优惠券列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/CouponList")
    Observable<BaseResponse<Object>> couponList(
    );


    //领券中心
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Coupon/CouponCenter")
    Observable<BaseResponse<Object>> couponCenter(
    );


    //领取优惠券
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Coupon/GetCoupon")
    Observable<BaseResponse<Object>> getCoupon(


    );



}
