package com.xyp.mimi.mvp.http.api.service.tuan;

import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TuanService {

    //获取团购产品列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/GroupBuy/GetGroupProductList")
    Observable<BaseResponse<Object>> getGroupProductList(
    );


    //获取团购产品详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/GroupBuy/GroupProductInfo")
    Observable<BaseResponse<Object>> getGroupProductInfo(
    );


    //获取某团购产品的记录(拼团中、最新拼团)
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/GroupBuy/GetGroupRecordList")
    Observable<BaseResponse<Object>> getGetGroupRecordList(


    );

    //验证是否可以参团
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/GroupBuy/VerificationIsJoin")
    Observable<BaseResponse<Object>> verificationIsJoin(


    );

    //5.5. 确认团购订单页面
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/GroupBuy/ConfirmationGroup")
    Observable<BaseResponse<Object>> confirmationGroup(


    );

    //5.6购买团购产品发起开团、参团创建订单
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/GroupBuy/CreateGroupOrder")
    Observable<BaseResponse<Object>> createGroupOrder(


    );

    //5.7. 获取我参与的拼团记录
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/GroupBuy/GroupRecordList")
    Observable<BaseResponse<Object>> groupRecordList(

    );

    //5.8. 查看我的拼团详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/GroupBuy/GroupMemberInfo")
    Observable<BaseResponse<Object>> getGroupMemberInfo(

    );

}
