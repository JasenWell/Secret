package com.xyp.mimi.mvp.presenter.user;

import android.app.Application;
import android.util.Log;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import okhttp3.MultipartBody;
import retrofit2.http.Part;

import javax.inject.Inject;

import com.xyp.mimi.im.bean.ResponseUserInfo;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.api.Api;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;
import com.xyp.mimi.mvp.http.entity.user.UserRegisterImgResult;
import com.xyp.mimi.mvp.http.entity.user.UserRegisterPost;
import com.xyp.mimi.mvp.utils.RxUtils;

import java.util.List;
import java.util.Map;



@ActivityScope
public class RegisterPresenter extends BasePresenter<UserContract.Model, UserContract.RegisterView> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public RegisterPresenter(UserContract.Model model, UserContract.RegisterView rootView) {
        super(model, rootView);
    }
    public void getRegisterCode(Map m){
        mModel.getRegisterCode(m)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()== 0 ){
                            mRootView.registerCodeResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.d("response","error");
                    }
                })
        ;
    }

    public void insertimg(@Part List<MultipartBody.Part> partList){
        mModel.insertimg(partList)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<UserRegisterImgResult>(mErrorHandler) {
                    @Override
                    public void onNext(UserRegisterImgResult baseResponse) {
                        if(baseResponse.getCode() == 0) {
                                mRootView.insertimgResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }


                })
        ;
    }




    public void register(String account,String password,String userName, String imgUrl){
        mModel.register(account,password,userName,imgUrl)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<LoginUserResult>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<LoginUserResult> userBeanBaseResponse) {
                        if(userBeanBaseResponse.getCode() == Api.RequestSuccess) {
                            final ResponseUserInfo userInfo = userBeanBaseResponse.getData().getUser();
                            if (userInfo.getId() != null) {
                                mRootView.registerResult(userBeanBaseResponse);
                            }else{
                                mRootView.showMessage(userBeanBaseResponse.getMsg());
                            }

                        }else{
                            mRootView.showMessage(userBeanBaseResponse.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.d("response","error");
                    }
                })
        ;
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
