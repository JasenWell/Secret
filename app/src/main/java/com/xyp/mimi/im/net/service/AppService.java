package com.xyp.mimi.im.net.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.xyp.mimi.im.model.ChatRoomResult;
import com.xyp.mimi.im.model.Result;
import com.xyp.mimi.im.model.VersionInfo;
import com.xyp.mimi.im.net.SealTalkUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface AppService {
    /**
     * 获取版本信息
     *
     * @return
     */
    @GET(SealTalkUrl.CLIENT_VERSION)
    LiveData<VersionInfo> getNewVersion();

    /**
     * 获取发现中聊天室
     *
     * @return
     */
    @GET(SealTalkUrl.GET_DISCOVERY_CHAT_ROOM)
    LiveData<Result<List<ChatRoomResult>>> getDiscoveryChatRoom();

    /**
     * 通用下载方法
     * @param fileUrl 文件地址
     * @return
     */
    @GET()
    @Streaming
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
