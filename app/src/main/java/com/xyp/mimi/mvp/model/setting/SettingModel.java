package com.xyp.mimi.mvp.model.setting;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import com.xyp.mimi.mvp.contract.setting.SettingContract;
import com.xyp.mimi.mvp.http.api.service.member.MemberService;
import com.xyp.mimi.mvp.http.api.service.setting.SettingService;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.setting.UserPasswordResult;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/14/2020 15:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class SettingModel extends BaseModel implements SettingContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SettingModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<UserPasswordResult> getUserPassword(String phone) {
        return mRepositoryManager.obtainRetrofitService(SettingService.class)
                .getUserPassword(phone);
    }

    @Override
    public Observable<BaseResponse> userLoginOut(String userId) {
        return mRepositoryManager.obtainRetrofitService(SettingService.class)
                .userLoginOut(userId);
    }
}