package com.xyp.mimi.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.xyp.mimi.mvp.contract.ProblemContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.problem.ProblemListResult;
import com.xyp.mimi.mvp.http.entity.user.UserPost;
import com.xyp.mimi.mvp.utils.RxUtils;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/20/2020 18:41
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class ProblemPresenter extends BasePresenter<ProblemContract.Model, ProblemContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ProblemPresenter(ProblemContract.Model model, ProblemContract.View rootView) {
        super(model, rootView);
    }

    public void getHelpList(UserPost userPost){
        mModel.getHelpList(userPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<ProblemListResult>(mErrorHandler) {
                    @Override
                    public void onNext(ProblemListResult problemListResult) {
//                        if(problemListResult.getCode()== 0 ){
//                            mRootView.helpListResult(problemListResult);
//                        }else{
//                            mRootView.showMessage(problemListResult.getMsg());
//                        }
                    }
                });
    }

    public void getHelpInfo(UserPost userPost){
        mModel.getHelpInfo(userPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse userBeanBaseResponse) {
                        if(userBeanBaseResponse.getCode()== 0 ){
                            mRootView.helpInfoResult(userBeanBaseResponse);
                        }else{
                            mRootView.showMessage(userBeanBaseResponse.getMsg());
                        }
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
