package com.xyp.mimi.mvp.http.api.service;

import com.xyp.mimi.mvp.http.entity.NetData;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TestService {

    @GET("/ping")
    Observable<NetData> searchTask();

}
