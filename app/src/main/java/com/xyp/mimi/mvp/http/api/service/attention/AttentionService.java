package com.xyp.mimi.mvp.http.api.service.attention;

import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AttentionService {

    //5.6. 我的关注(技师)、收藏(产品 、商家、品牌资源)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/MemberCollections")
    Observable<BaseResponse<Object>> getMemberCollections(

    );
    //添加关注(技师)、收藏(产品 、商家、品牌资源)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/AddCollections")
    Observable<BaseResponse<Object>> addCollections(

    );
    //取消关注(技师)、收藏(产品 、商家、品牌资源)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/ReCollections")
    Observable<BaseResponse<Object>> reCollection(

    );
    //删除/清空关注(技师)、收藏(产品 、商家、品牌资源)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/DelCollections")
    Observable<BaseResponse<Object>> deleteCollection(

    );
}
