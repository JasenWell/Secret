package com.xyp.mimi.im.task;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.HashMap;

import com.xyp.mimi.im.db.DbManager;
import com.xyp.mimi.im.model.PrivacyResult;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.Result;
import com.xyp.mimi.im.model.ScreenCaptureResult;
import com.xyp.mimi.im.net.HttpClientManager;
import com.xyp.mimi.im.net.RetrofitUtil;
import com.xyp.mimi.im.net.service.PrivacyService;
import com.xyp.mimi.im.sp.UserConfigCache;
import com.xyp.mimi.im.utils.NetworkOnlyResource;

public class PrivacyTask {

    private DbManager dbManager;
    private PrivacyService privacyService;
    private Context context;
    private UserConfigCache userConfigCache;

    public PrivacyTask(Context context) {
        this.context = context.getApplicationContext();
        userConfigCache = new UserConfigCache(context);
        dbManager = DbManager.getInstance(context);
        privacyService = HttpClientManager.getInstance(context).getClient().createService(PrivacyService.class);
    }

    /**
     * 用户隐私设置（可同时设置多项，传-1为不设置，0允许，1不允许）
     *
     * @param phoneVerify    是否可以通过电话号码查找
     * @param stSearchVerify 是否可以通过 SealTalk 号查找
     * @param friVerify      加好友验证
     * @param groupVerify    允许直接添加至群聊
     * @return
     */
    public LiveData<Resource<Void>> setPrivacy(int phoneVerify, int stSearchVerify,
                                               int friVerify, int groupVerify) {
        return new NetworkOnlyResource<Void, Result>() {

            @NonNull
            @Override
            protected LiveData<Result> createCall() {
                HashMap<String, Object> paramMap = new HashMap<>();
                if (phoneVerify != -1) {
                    paramMap.put("phoneVerify", phoneVerify);
                }
                if (stSearchVerify != -1) {
                    paramMap.put("stSearchVerify", stSearchVerify);
                }
                if (friVerify != -1) {
                    paramMap.put("friVerify", friVerify);
                }
                if (groupVerify != -1) {
                    paramMap.put("groupVerify", groupVerify);
                }
                return privacyService.setPrivacy(RetrofitUtil.createJsonRequest(paramMap));
            }
        }.asLiveData();
    }

    /**
     * 获取个人隐私设置
     *
     * @return
     */
    public LiveData<Resource<PrivacyResult>> getPrivacyState() {
        return new NetworkOnlyResource<PrivacyResult, Result<PrivacyResult>>() {

            @NonNull
            @Override
            protected LiveData<Result<PrivacyResult>> createCall() {
                return privacyService.getPrivacy();
            }
        }.asLiveData();
    }

    /**
     * 获取是否开启截屏通知状态
     *
     * @param conversationType
     * @param targetId
     * @return
     */
    public LiveData<Resource<ScreenCaptureResult>> getScreenCapture(int conversationType, String targetId) {
        return new NetworkOnlyResource<ScreenCaptureResult, Result<ScreenCaptureResult>>() {

            @NonNull
            @Override
            protected LiveData<Result<ScreenCaptureResult>> createCall() {
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("conversationType", conversationType);
                paramMap.put("targetId", targetId);
                return privacyService.getScreenCapture(RetrofitUtil.createJsonRequest(paramMap));
            }

            @Override
            protected void saveCallResult(@NonNull ScreenCaptureResult item) {
                super.saveCallResult(item);
                userConfigCache.setScreenCaptureStatus(item.status);
            }
        }.asLiveData();
    }

    /**
     * 设置是否开启截屏通知
     *
     * @param conversationType
     * @param targetId
     * @param noticeStatus     0 关闭 1 打开
     * @return
     */
    public LiveData<Resource<Void>> setScreenCapture(int conversationType, String targetId, int noticeStatus) {
        return new NetworkOnlyResource<Void, Result<Void>>() {

            @NonNull
            @Override
            protected LiveData<Result<Void>> createCall() {
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("conversationType", conversationType);
                paramMap.put("targetId", targetId);
                paramMap.put("noticeStatus", noticeStatus);
                return privacyService.setScreenCapture(RetrofitUtil.createJsonRequest(paramMap));
            }

            @Override
            protected void saveCallResult(@NonNull Void item) {
                super.saveCallResult(item);
                userConfigCache.setScreenCaptureStatus(noticeStatus);
            }
        }.asLiveData();
    }

    /**
     * 发送使用了截屏通知的消息
     *
     * @param conversationType
     * @param targetId
     * @return
     */
    public LiveData<Resource<Void>> sendScreenShotMessage(int conversationType, String targetId) {
        return new NetworkOnlyResource<Void, Result<Void>>() {

            @NonNull
            @Override
            protected LiveData<Result<Void>> createCall() {
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("conversationType", conversationType);
                paramMap.put("targetId", targetId);
                return privacyService.sendScreenShotMsg(RetrofitUtil.createJsonRequest(paramMap));
            }
        }.asLiveData();
    }
}
