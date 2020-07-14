package com.xyp.mimi.mvp.http.api.service.circle;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.circle.CircleListResult;
import com.xyp.mimi.mvp.http.entity.circle.CirclePost;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CircleService {


    //查询动态
    @FormUrlEncoded
    @POST("/mall/interface/dynamicList")
    Observable<CircleListResult> getCircleList(
            @Field("uid") String uid
    );


    //发布动态
    @FormUrlEncoded
    @POST("/mall/interface/dynamic")
    Observable<BaseResponse> pushCircle(
            @Field("uid") String uid,
            @Field("context") String context
    );

    //删除个人动态
    @FormUrlEncoded
    @POST("/mall/interface/deletedynamic")
    Observable<BaseResponse> deleteCircle(
            @Field("id") String id
    );

}
