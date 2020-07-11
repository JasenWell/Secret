package com.xyp.mimi.mvp.http.api.service.orderService;

import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OrderServiceService {

    //获取退货退款换货订单列表_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/RefundOrderList")
    Observable<BaseResponse<Object>> refundOrderList(

    );

    //获取退货/退款的订单详细
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/RefundOrderInfo")
    Observable<BaseResponse<Object>> refundOrderInfo(

    );

    //获取退货原因_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/GetRefundReason")
    Observable<BaseResponse<Object>> getRefundReason(

    );

    // 申请退款
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/ApplicationRefund")
    Observable<BaseResponse<Object>> applicationRefund(

    );

    // 申请退货
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/ApplicationReturn")
    Observable<BaseResponse<Object>> applicationReturn(

    );

    //申请换货
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/ApplicationBarter")
    Observable<BaseResponse<Object>> applicationBarter(

    );

    //提交退货快递信息_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/SubmitRefundExpress")
    Observable<BaseResponse<Object>> submitRefundExpress(

    );

    //  提交换货快递信息_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/SubmitBarterExpress")
    Observable<BaseResponse<Object>> submitBarterExpress(

    );

    //撤销退货退款申请_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/CanelRefund")
    Observable<BaseResponse<Object>> canelRefund(

    );

    ///帮助中心

    //帮助列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Help/HelpList")
    Observable<BaseResponse<Object>> helpList(

    );

    //获取帮助详细信息
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Help/GetHelpInfo")
    Observable<BaseResponse<Object>> getHelpInfo(

    );


}
