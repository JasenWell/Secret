package com.xyp.mimi.mvp.http.api.service.market;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.market.GoodsDetailPost;
import com.xyp.mimi.mvp.http.entity.market.GoodsDetailResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MarketService {

    //获取商品品牌
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/BrandList")
    Observable<BaseResponse<Object>> getBrandList(
    );


    //获取类型(商品)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/TypeList")
    Observable<BaseResponse<Object>> addTypeList(

    );


    //获取分类(商品)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/GetProductClass")
    Observable<BaseResponse<Object>> getProductClass(

    );


    //获取商品列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/GoodsList")
    Observable<BaseResponse<Object>> getGoodsList(

    );

    //获取商品详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/Goodsxq")
    Observable<GoodsDetailResult> getGoodsxq(
            @Body GoodsDetailPost goodsDetailPost
    );

    //有限时特卖的时间段
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/FlashSaleTimeList")
    Observable<BaseResponse<Object>> getFlashSaleTimeList(

    );

    //获取限时特卖产品列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/FlashSaleGoodList")
    Observable<BaseResponse<Object>> getFlashSaleGoodList(

    );

}
