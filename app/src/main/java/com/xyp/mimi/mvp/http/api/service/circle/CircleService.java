package com.xyp.mimi.mvp.http.api.service.circle;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
<<<<<<< HEAD
import com.xyp.mimi.mvp.http.entity.circle.CirclePost;
=======
import com.xyp.mimi.mvp.http.entity.Circle.CirclePost;
>>>>>>> c1102d2024adae19d2e36885137bae797473fb2e

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
