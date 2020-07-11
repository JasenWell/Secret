package com.xyp.mimi.mvp.presenter.user;

import android.app.Application;
import android.util.Log;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.user.UserRegisterPost;
import com.xyp.mimi.mvp.utils.RxUtils;

import java.util.Map;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/20/2020 10:45
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class RetrievePasswordPresenter extends BasePresenter<UserContract.Model, UserContract.RetrievePasswordView> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public RetrievePasswordPresenter(UserContract.Model model, UserContract.RetrievePasswordView rootView) {
        super(model, rootView);
    }

    //获取修改密码的验证码
    public void getRetrievePasswordCode(Map m){
        mModel.getRetrievePasswordCode(m)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()== 0 ){
                            mRootView.RetrievePasswordCodeResult(baseResponse);
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

    //找回密码
    public void retrievePassword(UserRegisterPost userRegisterPost){
        mModel.retrievePassword(userRegisterPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse userBeanBaseResponse) {
                        if(userBeanBaseResponse.getCode()== 0 ){
                            mRootView.RetrievePasswordResult(userBeanBaseResponse);
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
