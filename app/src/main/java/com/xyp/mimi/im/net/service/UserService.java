package com.xyp.mimi.im.net.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.xyp.mimi.im.db.model.FriendBlackInfo;
import com.xyp.mimi.im.db.model.UserInfo;
import com.xyp.mimi.im.model.ContactGroupResult;
import com.xyp.mimi.im.model.GetPokeResult;
import com.xyp.mimi.im.model.LoginResult;
import com.xyp.mimi.im.model.RegionResult;
import com.xyp.mimi.im.model.RegisterResult;
import com.xyp.mimi.im.model.Result;
import com.xyp.mimi.im.model.UploadTokenResult;
import com.xyp.mimi.im.model.VerifyResult;
import com.xyp.mimi.im.net.SealTalkUrl;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST(SealTalkUrl.LOGIN)
    LiveData<Result<LoginResult>> loginLiveData(@Body RequestBody body);

    @GET(SealTalkUrl.GET_TOKEN)
    Call<Result<LoginResult>> getToken();

    @GET(SealTalkUrl.GET_USER_INFO)
    LiveData<Result<UserInfo>> getUserInfo(@Path("user_id") String userId);

    @POST(SealTalkUrl.SEND_CODE)
    LiveData<Result<String>> sendCode(@Body RequestBody body);

    @POST(SealTalkUrl.VERIFY_CODE)
    LiveData<Result<VerifyResult>> verifyCode(@Body RequestBody body);

    @POST(SealTalkUrl.REGISTER)
    LiveData<Result<RegisterResult>> register(@Body RequestBody body);

    @GET(SealTalkUrl.REGION_LIST)
    LiveData<Result<List<RegionResult>>> getRegionList();

    @POST(SealTalkUrl.CHECK_PHONE_AVAILABLE)
    LiveData<Result<Boolean>> checkPhoneAvailable(@Body RequestBody body);

    @POST(SealTalkUrl.RESET_PASSWORD)
    LiveData<Result<String>> resetPassword(@Body RequestBody body);

    @POST(SealTalkUrl.SET_NICK_NAME)
    LiveData<Result> setMyNickName(@Body RequestBody requestBody);

    @POST(SealTalkUrl.SET_ST_ACCOUNT)
    LiveData<Result> setStAccount(@Body RequestBody requestBody);

    @POST(SealTalkUrl.SET_GENDER)
    LiveData<Result> setGender(@Body RequestBody requestBody);

    @GET(SealTalkUrl.GET_IMAGE_UPLOAD_TOKEN)
    LiveData<Result<UploadTokenResult>> getImageUploadToken();

    @POST(SealTalkUrl.SET_PORTRAIT)
    LiveData<Result> setPortrait(@Body RequestBody body);

    @POST(SealTalkUrl.CHANGE_PASSWORD)
    LiveData<Result> changePassword(@Body RequestBody body);


    /**
     * 获取黑名单信息
     *
     * @return
     */
    @GET(SealTalkUrl.GET_BLACK_LIST)
    LiveData<Result<List<FriendBlackInfo>>> getFriendBlackList();

    /**
     * 添加到黑名单
     *
     * @param body
     * @return
     */
    @POST(SealTalkUrl.ADD_BLACK_LIST)
    LiveData<Result> addToBlackList(@Body RequestBody body);

    /**
     * 移除黑名单
     *
     * @param body
     * @return
     */
    @POST(SealTalkUrl.REMOVE_BLACK_LIST)
    LiveData<Result> removeFromBlackList(@Body RequestBody body);

    /**
     * 获取通讯录中的群组列表
     *
     * @return
     */
    @GET(SealTalkUrl.GROUP_GET_ALL_IN_CONTACT)
    LiveData<Result<ContactGroupResult>> getGroupListInContact();

    /**
     * 设置接收戳一下消息状态
     *
     * @param body
     * @return
     */
    @POST(SealTalkUrl.SET_RECEIVE_POKE_MESSAGE_STATUS)
    LiveData<Result> setReceivePokeMessageStatus(@Body RequestBody body);

    /**
     * 获取接收戳一下消息状态
     *
     * @return
     */
    @GET(SealTalkUrl.GET_RECEIVE_POKE_MESSAGE_STATUS)
    LiveData<Result<GetPokeResult>> getReceivePokeMessageStatus();
}
