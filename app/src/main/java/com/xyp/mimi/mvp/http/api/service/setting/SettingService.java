package com.xyp.mimi.mvp.http.api.service.setting;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.user.UserCodePost;
import com.xyp.mimi.mvp.http.entity.user.UserLoginPasswordPost;
import com.xyp.mimi.mvp.http.entity.user.UserPayPasswordPost;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SettingService {

    // 获得账号绑定的手机号
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/GetMemberMobile")
    Observable<BaseResponse<Object>> getMemberMobile(

    );

    //获取验证码[绑定手机号、手机号修改]
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/GetBindTelCode")
    Observable<BaseResponse<Object>> getBindTelCode(

    );

    // 绑定手机号、手机号修改
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/UpdateMobile")
    Observable<BaseResponse<Object>> updateMobile(

    );

    //获取验证码[修改登录密码、修改支付密码]
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/GetUpdatePswCode")
    Observable<BaseResponse> getUpdatePswCode(
            @Body UserCodePost userCodePost
    );

    //设置支付密码
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/UpdatePayPwd")
    Observable<BaseResponse> updatePayPwd(
            @Body UserPayPasswordPost userPayPasswordPost
    );

    // 修改登录密码
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/UpdatePassword")
    Observable<BaseResponse> updatePassword(
            @Body UserLoginPasswordPost userLoginPasswordPost
    );

    //意见反馈
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/MemberFeedBack")
    Observable<BaseResponse<Object>> memberFeedBack(

    );

    //获取意见反馈类型
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/FeedBackType")
    Observable<BaseResponse<Object>> feedBackType(

    );


    //获取系统基础配置
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/System/GetWebConfiguration")
    Observable<BaseResponse<Object>> getWebConfiguration(

    );

}
