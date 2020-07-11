package com.xyp.mimi.mvp.http.api.service;

import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.TaskList;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.product.Product;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TaskService {
    @GET("/api/fl.asp")
    Observable<TaskList> searchTask();

    @FormUrlEncoded
    @POST("/api/chose.asp")
    Observable<BaseResponse<Object>> startTask(
            @Field("useid") String useid,
            @Field("cname") String cname
    );
    //
    @FormUrlEncoded
    @POST("/api/renwu.asp")
    Observable<TaskData> getTask(
            @Field("useid") String useid
    );

    @FormUrlEncoded
    @POST("/api/zhanghaos.asp")
    Observable<Product> getRecommendedProducts(
            @Field("useid") String useid
    );
}
