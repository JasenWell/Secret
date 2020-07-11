package com.xyp.mimi.mvp.http.api.service;

import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PasswordService {
    @FormUrlEncoded
    @POST("/api/pwd.asp")
    Observable<BaseResponse<Object>> changePassword(
            @Field("useid") String useid,
            @Field("opwd") String opwd,
            @Field("npwd") String npwd
    );
}
