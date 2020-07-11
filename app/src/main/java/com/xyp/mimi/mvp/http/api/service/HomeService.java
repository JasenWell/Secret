package com.xyp.mimi.mvp.http.api.service;

import com.xyp.mimi.mvp.http.entity.DetectRequestBean;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HomeService {


    @FormUrlEncoded
    @POST("/api/Login/LoginByMobile")
    Observable<BaseResponse<Object>>  queryDetectNodes(
            DetectRequestBean requestBean
    );
}
