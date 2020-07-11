package com.xyp.mimi.mvp.http.api.service.evaluation;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.market.EvaluationListPost;
import com.xyp.mimi.mvp.http.entity.market.EvalutionListResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

//评价
public interface EvalutionService {


    //获取待评价订单详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/GetOrderComment")
    Observable<BaseResponse<Object>> getOrderComment(

    );
    // 提交评价订单详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/OrderComment")
    Observable<BaseResponse<Object>> orderComment(

    );

    //获取订单评价信息
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/OrderCommentInfo")
    Observable<BaseResponse<Object>> orderCommentInfo(

    );

    //获取订单评价信息列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/OrderCommentList")
    Observable<EvalutionListResult> orderCommentList(
            @Body EvaluationListPost evaluationListPost
    );

    //我的订单评价信息列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/MyOrderCommentList")
    Observable<BaseResponse<Object>> myOrderCommentList(

    );

    ////////////////////////////////////////////
    // 订单评价，社交

    //我的订单评价信息列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/CommentList")
    Observable<BaseResponse<Object>> commentList(

    );

    // 获得评论时的产品_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/GetProductofComment")
    Observable<BaseResponse<Object>> getProductofComment(

    );

    //评论产品_
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Order/CommentProduct")
    Observable<BaseResponse<Object>> commentProduct(

    );

    //分页获取商品评价
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/GetEvaluate")
    Observable<BaseResponse<Object>> getEvaluate(

    );

    //获取产品某个评价详请
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/ProductCommentInfo")
    Observable<BaseResponse<Object>> productCommentInfo(

    );

    //分页获取评价评论列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/ProductCommentReply")
    Observable<BaseResponse<Object>> productCommentReply(

    );
    //提交评价评论
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/SubmitCommentReply")
    Observable<BaseResponse<Object>> submitCommentReply(

    );

    // 评价以及评价的评论点赞、取消点赞
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Goods/ClickLikes")
    Observable<BaseResponse<Object>> ClickLikes(

    );

}
