package com.xyp.mimi.mvp.http.api.service.order;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.order.CancelOrderPost;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailPost;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailResult;
import com.xyp.mimi.mvp.http.entity.order.OrderListPost;
import com.xyp.mimi.mvp.http.entity.order.OrderListResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OrderService {

    //购物车订单提交(单店铺)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/BuyCart")
    Observable<BaseResponse<Object>> buyCart(
    );

    //立即购买确认订单(商品)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/BuyNowGoods")
    Observable<BaseResponse<Object>> buyNowGoods(
    );

    //立即购买提交订单(商品)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/BuyNowSubmitOrder")
    Observable<BaseResponse<Object>> buyNowSubmitOrder(
    );

    //立即购买提交订单获取运费
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/BuyNowToFreight")
    Observable<BaseResponse<Object>> buyNowToFreight(
    );

    //购物车提交订单获取运费
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/BuyCartToFreight")
    Observable<BaseResponse<Object>> buyCartToFreight(
    );

    //购物车订单提交(多店铺)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/ShopsBuyCart")
    Observable<BaseResponse<Object>> shopsBuyCart(
    );

    /////////////////////////////////////////////////////////////
    //13我的订单

    //13.0. 订单列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/OrderList")
    Observable<OrderListResult> orderList(
           @Body OrderListPost orderListPost
    );

    //订单详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/OrderDetails")
    Observable<OrderDetailResult> orderDetails(
            @Body OrderDetailPost orderDetailPost
            );

    //取消订单
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/CancelOrders")
    Observable<BaseResponse> cancelOrders(
            @Body CancelOrderPost cancelOrderPost
    );

    //13.3. 删除订单
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/DeleteOrders")
    Observable<BaseResponse<Object>> deleteOrders(
    );

    //订单确认收货
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/ConfirmReceipt")
    Observable<BaseResponse<Object>> confirmReceipt(
    );

    //获取取消原因_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/CancelReason")
    Observable<BaseResponse<Object>> cancelReason(
    );

    //提醒发货_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/Remind")
    Observable<BaseResponse<Object>> remind(
    );

    // 获取物流详细_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/GetLogistics")
    Observable<BaseResponse<Object>> getLogistics(
    );

    // 获取物流公司列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/GetExpressCompanyList")
    Observable<BaseResponse<Object>> getExpressCompanyList(
    );

    // 根据订单和订单详情Id查询订单Item详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/GetOrderListItemDetail")
    Observable<BaseResponse<Object>> getOrderListItemDetail(
    );





}
