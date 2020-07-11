package com.xyp.mimi.mvp.http.api.service.history;

import com.xyp.mimi.mvp.http.entity.history.HistoryDeletePost;
import com.xyp.mimi.mvp.http.entity.history.HistoryPost;
import com.xyp.mimi.mvp.http.entity.history.HistoryResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HistoryService {

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/MemberFootprintList")
    Observable<HistoryResult> getHistoryData(
            @Body HistoryPost  historyPost
    );


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/DeleteMyFootprint")
    Observable<BaseResponse> deleteHistory(
            @Body HistoryDeletePost historyDeletePost
    );


}
