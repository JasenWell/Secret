package com.xyp.mimi.im.net.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.xyp.mimi.im.bean.ResponseWrapperGroupInfo;
import com.xyp.mimi.im.bean.ResponseWrapperInfo;
import com.xyp.mimi.im.db.model.GroupEntity;
import com.xyp.mimi.im.db.model.GroupExitedMemberInfo;
import com.xyp.mimi.im.db.model.GroupMemberInfoDes;
import com.xyp.mimi.im.model.AddMemberResult;
import com.xyp.mimi.im.model.CopyGroupResult;
import com.xyp.mimi.im.model.GroupNoticeInfoResult;
import com.xyp.mimi.im.model.GroupNoticeResult;
import com.xyp.mimi.im.model.GroupMemberInfoResult;
import com.xyp.mimi.im.model.GroupResult;
import com.xyp.mimi.im.model.RegularClearStatusResult;
import com.xyp.mimi.im.model.Result;
import com.xyp.mimi.im.net.SealTalkUrl;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GroupService {
    //---------------hjh start----------------

    //创建群
    @FormUrlEncoded
    @POST("/mall/interface/Group")
    LiveData<Result<GroupResult>> createGroup(
            @Field("uid") String userId,//用户id
            @Field("idList") String idList,//拉入好友群聊id(多个用逗号隔开,):
            @Field("context") String groupName
    );

    //查询群信息
    @FormUrlEncoded
    @POST("/mall/interface/selecrtGroup")
    LiveData<Result<ResponseWrapperGroupInfo>> getGroupInfo(@Field("gid") String groupId);






    //---------------hjh end----------------

    @POST(SealTalkUrl.GROUP_CREATE)
    LiveData<Result<GroupResult>> createGroup(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_ADD_MEMBER)
    LiveData<Result<List<AddMemberResult>>> addGroupMember(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_JOIN)
    LiveData<Result> joinGroup(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_KICK_MEMBER)
    LiveData<Result> kickMember(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_QUIT)
    LiveData<Result> quitGroup(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_DISMISS)
    LiveData<Result> dismissGroup(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_TRANSFER)
    LiveData<Result> transferGroup(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_RENAME)
    LiveData<Result> renameGroup(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_SET_BULLETIN)
    LiveData<Result> setGroupBulletin(@Body RequestBody body);

    @GET(SealTalkUrl.GROUP_GET_BULLETIN)
    LiveData<Result<GroupNoticeResult>> getGroupBulletin(@Query("groupId") String id);

    @POST(SealTalkUrl.GROUP_SET_PORTRAIT_URL)
    LiveData<Result> setGroupPortraitUri(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_SET_DISPLAY_NAME)
    LiveData<Result> setMemberDisplayName(@Body RequestBody body);

//    @GET(SealTalkUrl.GROUP_GET_INFO)
//    @Deprecated
//    LiveData<Result<GroupEntity>> getGroupInfo(@Path("group_id") String groupId);

    @GET(SealTalkUrl.GROUP_GET_MEMBER_INFO)
    LiveData<Result<List<GroupMemberInfoResult>>> getGroupMemberList(@Path("group_id") String groupId);

    @POST(SealTalkUrl.GROUP_REMOVE_MANAGER)
    LiveData<Result> removeManager(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_ADD_MANAGER)
    LiveData<Result> addManager(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_SAVE_TO_CONTACT)
    LiveData<Result> saveToContact(@Body RequestBody body);

    @HTTP(method = "DELETE", path = SealTalkUrl.GROUP_SAVE_TO_CONTACT, hasBody = true)
    LiveData<Result> removeFromContact(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_SET_REGULAR_CLEAR)
    LiveData<Result> setRegularClear(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_GET_REGULAR_CLEAR_STATE)
    LiveData<Result<RegularClearStatusResult>> getRegularClearState(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_MUTE_ALL)
    LiveData<Result> muteAll(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_MEMBER_PROTECTION)
    LiveData<Result> setGroupProtection(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_SET_CERTIFICATION)
    LiveData<Result<Void>> setCertification(@Body RequestBody body);

    @GET(SealTalkUrl.GROUP_GET_NOTICE_INFO)
    LiveData<Result<List<GroupNoticeInfoResult>>> getGroupNoticeInfo();

    @POST(SealTalkUrl.GROUP_SET_NOTICE_STATUS)
    LiveData<Result<Void>> setGroupNoticeStatus(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_CLEAR_NOTICE)
    LiveData<Result<Void>> clearGroupNotice();

    @POST(SealTalkUrl.GROUP_COPY)
    LiveData<Result<CopyGroupResult>> copyGroup(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_GET_EXITED)
    LiveData<Result<List<GroupExitedMemberInfo>>> getGroupExitedMemberInfo(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_GET_MEMBER_INFO_DES)
    LiveData<Result<GroupMemberInfoDes>> getGroupInfoDes(@Body RequestBody body);

    @POST(SealTalkUrl.GROUP_SET_MEMBER_INFO_DES)
    LiveData<Result<Void>> setGroupInfoDes(@Body RequestBody body);

}
