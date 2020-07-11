package com.xyp.mimi.mvp.http.api.service.cart;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.cart.CartListPost;
import com.xyp.mimi.mvp.http.entity.cart.CartListResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CartService {

    //添加购物车(商品)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Cart/AddCart")
    Observable<BaseResponse<Object>> addCart(
    );


    //编辑购物车(商品)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Cart/EditCart")
    Observable<BaseResponse<Object>> editCart(

    );

    //删除购物车(商品)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Cart/DelCart")
    Observable<BaseResponse<Object>> delCart(

    );

    //获取购物车列表(单店铺)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Cart/GoodsCartList")
    Observable<BaseResponse<Object>> getGoodsCartList(

    );

    //购物车数量(商品)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Cart/GoodsCartNum")
    Observable<BaseResponse<Object>> getGoodsCartNum(

    );

    //获取购物车列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Cart/CartList")
    Observable<BaseResponse<Object>> getCartList(

    );

    //获取购物车列表(多店铺)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Cart/ShopsCartList")
    Observable<CartListResult> getShopsCartList(
            @Body CartListPost cartListPost
            );
}
