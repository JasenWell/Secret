package com.xyp.mimi.mvp.model.circle;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.circle.CircleContract;
import com.xyp.mimi.mvp.http.api.service.circle.CircleService;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.circle.CirclePost;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/13/2020 09:45
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */

@ActivityScope
public class CircleModel extends BaseModel implements CircleContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public CircleModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponse> getCircleList(CirclePost circlePost) {
        return mRepositoryManager.obtainRetrofitService(CircleService.class)
                .getCircleList(circlePost);
    }

    @Override
    public Observable<BaseResponse> pushCircle(CirclePost circlePost) {
        return mRepositoryManager.obtainRetrofitService(CircleService.class)
                .pushCircle(circlePost);
    }

}