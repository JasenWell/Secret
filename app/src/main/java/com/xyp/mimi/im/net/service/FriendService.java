package com.xyp.mimi.im.net.service;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

import com.xyp.mimi.im.bean.ResponseSearchFriendInfo;
import com.xyp.mimi.im.bean.ResponseWrapperInfo;
import com.xyp.mimi.im.db.model.FriendDescription;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.model.AddFriendResult;
import com.xyp.mimi.im.model.GetContactInfoResult;
import com.xyp.mimi.im.model.Result;
import com.xyp.mimi.im.model.SearchFriendInfo;
import com.xyp.mimi.im.net.SealTalkUrl;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface FriendService {
    //-------------------add by hjh start-----------------
    /**
     * 添加好友请求
     *
     * @param queryMap
     * @return
     */
    @POST("/mall/interface/insertFriendslist")
    LiveData<Result<ResponseSearchFriendInfo>> addFriendRequest(@QueryMap(encoded = true) Map<String, String> queryMap);

    //添加好友
    @Deprecated
    @FormUrlEncoded
    @POST("/mall/interface/insertFriendslist")
    Observable<BaseResponse<Object>> insertFriendslist(
            @Field("mianUid") String username,//用户id
            @Field("phone") String friendPhone  //好友电话
    );

    //查询好友信息
    @FormUrlEncoded
    @POST("/mall/interface/selectuserid")
    LiveData<Result<LoginUserResult>> searchFriendFromServer(
            @Field("userId") String userId//用户id
    );

    //获取好友列表
    @FormUrlEncoded
    @POST("/mall/interface/selectFriendslist")
    LiveData<Result<ResponseWrapperInfo>> getAllFriendList(
            @Field("userId") String username//用户id
    );




    //-------------------add by hjh end-----------------

    /**
     * 获取所有好友信息
     *
     * @return
     */
    @GET(SealTalkUrl.GET_FRIEND_ALL)
    LiveData<Result<List<FriendShipInfo>>> getAllFriendList();

    /**
     * 获取好友信息
     *
     * @param friendId
     * @return
     */
    @Deprecated
    @GET(SealTalkUrl.GET_FRIEND_PROFILE)
    LiveData<Result<FriendShipInfo>> getFriendInfo(@Path("friendId") String friendId);

    /**
     * 同意添加好友
     *
     * @return
     */
    @POST(SealTalkUrl.ARGEE_FRIENDS)
    LiveData<Result<Boolean>> agreeFriend(@Body RequestBody body);

    /**
     * 忽略好友请求
     *
     * @return
     */
    @POST(SealTalkUrl.INGORE_FRIENDS)
    LiveData<Result<Void>> ingoreFriend(@Body RequestBody body);

    /**
     * 设置好友备注名
     *
     * @param body
     * @return
     */
    @POST(SealTalkUrl.SET_DISPLAY_NAME)
    LiveData<Result> setFriendAlias(@Body RequestBody body);

    /**
     * 申请添加好友
     *
     * @param body
     * @return
     */
    @POST(SealTalkUrl.INVITE_FRIEND)
    LiveData<Result<AddFriendResult>> inviteFriend(@Body RequestBody body);


    @POST(SealTalkUrl.DELETE_FREIND)
    LiveData<Result> deleteFriend(@Body RequestBody body);

    /**
     * 获取手机通讯录中的人员信息
     *
     * @param body
     * @return
     */
    @POST(SealTalkUrl.GET_CONTACTS_INFO)
    LiveData<Result<List<GetContactInfoResult>>> getContactsInfo(@Body RequestBody body);

    /**
     * 设置朋友备注和描述
     *
     * @param body
     * @return
     */
    @POST(SealTalkUrl.SET_FRIEND_DESCRIPTION)
    LiveData<Result<Void>> setFriendDescription(@Body RequestBody body);

    @POST(SealTalkUrl.GET_FRIEND_DESCRIPTION)
    LiveData<Result<FriendDescription>> getFriendDescription(@Body RequestBody body);

    /**
     * 批量删除好友
     *
     * @param body
     * @return
     */
    @POST(SealTalkUrl.MULTI_DELETE_FRIEND)
    LiveData<Result> deleteMultiFriend(@Body RequestBody body);
}
