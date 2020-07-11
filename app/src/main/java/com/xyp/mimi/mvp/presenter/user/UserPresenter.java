package com.xyp.mimi.mvp.presenter.user;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.user.UserInfoEditPost;
import com.xyp.mimi.mvp.http.entity.user.UserInformationResult;
import com.xyp.mimi.mvp.http.entity.user.UserPost;
import com.xyp.mimi.mvp.http.entity.user.UserPhotoPost;
import com.xyp.mimi.mvp.http.entity.user.UserPhotoResult;
import com.xyp.mimi.mvp.utils.RxUtils;


@ActivityScope
public class UserPresenter extends BasePresenter<UserContract.Model, UserContract.View> {

    @Inject
    RxErrorHandler rxErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public UserPresenter(UserContract.Model model, UserContract.View rootView) {
        super(model, rootView);
    }


    public void upload(UserPhotoPost userPhotoPost){
        mModel.uploadPhoto(userPhotoPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<UserPhotoResult>(rxErrorHandler) {
                    @Override
                    public void onNext(UserPhotoResult userPhotoResult) {
                        if(userPhotoResult.getCode()== 0){
                            mRootView.uploadPhotoResult(userPhotoResult);
                        }else{
                            mRootView.showMessage(userPhotoResult.getMsg());
                        }
                    }
                });
    }

    public void User(UserPost UserPost){
        mModel.User(UserPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<UserInformationResult>(rxErrorHandler) {
                    @Override
                    public void onNext(UserInformationResult UserResult) {
                        if(UserResult.getCode()== 0){
                            mRootView.informationResult(UserResult);
                        }else{
                            mRootView.showMessage(UserResult.getMsg());
                        }
                    }
                });
    }

    public void editUserInfo(UserInfoEditPost userInfoEditPost){
        mModel.editUserInfo(userInfoEditPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()== 0){
                            mRootView.userInfoEditResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                });
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        this.rxErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
