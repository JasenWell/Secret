package com.xyp.mimi.mvp.http.api.service.member;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.user.UserEditPost;
import com.xyp.mimi.mvp.http.entity.user.UserInfoEditPost;
import com.xyp.mimi.mvp.http.entity.user.UserInformationResult;
import com.xyp.mimi.mvp.http.entity.user.UserPost;
import com.xyp.mimi.mvp.http.entity.user.UserPhotoPost;
import com.xyp.mimi.mvp.http.entity.user.UserPhotoResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MemberService {

    //个人中心信息
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/GetCenterInfo")
    Observable<BaseResponse<Object>> getCenterInfo(

    );

    //个人基本信息
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/GetMemInfo")
    Observable<UserInformationResult> getMemInfo(
            @Body UserPost UserPost
    );

    //修改会员头像
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/UploadPhoto")
    Observable<UserPhotoResult> uploadPhoto(
            @Body UserPhotoPost userPhotoPost
            );


    // 修改会员昵称
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/EditUserNick")
    Observable<BaseResponse> editUserNick(
            @Body UserEditPost userEditPost
    );

    // 编辑会员信息
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/EditUserInfo")
    Observable<BaseResponse> editUserInfo(
            @Body UserInfoEditPost userInfoEditPost
    );

    //邀请好友
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/InviteFriends")
    Observable<BaseResponse<Object>> inviteFriends(

    );

    //绑定推荐关系（无推荐关系可绑定）
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Login/BindRelationMobile")
    Observable<BaseResponse<Object>> bindRelationMobile(

    );

    // 我的客户
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/MyCustom")
    Observable<BaseResponse<Object>> myCustom(

    );

    // 我的浏览足迹
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/MemberFootprintList")
    Observable<BaseResponse<Object>> memberFootprintList(

    );

    //删除/清空浏览
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/DeleteMyFootprint")
    Observable<BaseResponse<Object>> deleteMyFootprint(

    );


}
