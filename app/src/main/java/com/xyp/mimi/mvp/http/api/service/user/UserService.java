package com.xyp.mimi.mvp.http.api.service.user;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;
import com.xyp.mimi.mvp.http.entity.login.LoginUserPost;
import com.xyp.mimi.mvp.http.entity.problem.ProblemListResult;
import com.xyp.mimi.mvp.http.entity.user.UserPost;
import com.xyp.mimi.mvp.http.entity.user.UserRegisterPost;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface UserService {

//    //登录
//    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
//    @POST("/mall/interface/login")
//    Observable<BaseResponse<LoginUserResult>> loginByUsernamePassword(
//            @Body LoginUserPost loginUserPost
//            );


    @FormUrlEncoded
    @POST("/mall/interface/login")
    Observable<BaseResponse<LoginUserResult>> loginByUsernamePassword(
            @Field("account") String username,
            @Field("password") String password
    );






    //获取手机号注册短信
    @POST("/api/Login/GetRegSMSCode")
    Observable<BaseResponse> getSms(
            @QueryMap Map<String, String> map
    );



    //用户注册
    @FormUrlEncoded
    @POST("/mall/interface/register")
    Observable<BaseResponse<LoginUserResult>> register(
            @Field("account") String userAaccount,
            @Field("password") String password,
            @Field("userName") String userName,
            @Field("money") String money,
            @Field("payPassword") String payPwd
    );



    //获取手机号找回密码短信
    @POST("/api/Login/FindPwdCode")
    Observable<BaseResponse> getPasswordSms(
            @QueryMap Map<String, String> map
    );


    //找回账号密码
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Login/ChangeLoginPwd")
    Observable<BaseResponse> changePassword(
            @Body UserRegisterPost userRegisterPost
    );


    //帮助列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Help/HelpList")
    Observable<ProblemListResult> getHelpList(
            @Body UserPost userPost
    );



    //帮助详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Help/GetHelpInfo")
    Observable<BaseResponse> getHelpInfo(
            @Body UserPost userPost
    );




}
