package com.xyp.mimi.mvp.http.api.service.circle;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.Circle.CirclePost;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CircleService {

    //查询动态
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/mall/interface/dynamicList")
    Observable<BaseResponse> getCircleList(
            @Body CirclePost circlePost
    );


    //发布动态
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/mall/interface/dynamic")
    Observable<BaseResponse> pushCircle(
            @Body CirclePost circlePost
    );
}
