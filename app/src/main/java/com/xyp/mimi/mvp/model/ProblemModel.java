package com.xyp.mimi.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.xyp.mimi.mvp.contract.ProblemContract;
import com.xyp.mimi.mvp.http.api.service.user.UserService;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.problem.ProblemListResult;
import com.xyp.mimi.mvp.http.entity.user.UserPost;

import io.reactivex.Observable;


@ActivityScope
public class ProblemModel extends BaseModel implements ProblemContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ProblemModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<ProblemListResult> getHelpList(UserPost userPost) {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .getHelpList(userPost);
    }

    @Override
    public Observable<BaseResponse> getHelpInfo(UserPost userPost) {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .getHelpInfo(userPost);
    }
}