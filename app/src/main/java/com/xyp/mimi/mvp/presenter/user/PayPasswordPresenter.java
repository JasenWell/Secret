package com.xyp.mimi.mvp.presenter.user;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.user.UserCodePost;
import com.xyp.mimi.mvp.http.entity.user.UserPayPasswordPost;
import com.xyp.mimi.mvp.utils.RxUtils;

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
public class PayPasswordPresenter extends BasePresenter<UserContract.Model, UserContract.PayPasswordView> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public PayPasswordPresenter(UserContract.Model model, UserContract.PayPasswordView rootView) {
        super(model, rootView);
    }

    //设置支付密码
    public void setPayPassword(UserPayPasswordPost userPayPasswordPost){
        mModel.setPayPassword(userPayPasswordPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()== 0 ){
                            mRootView.PayPasswordResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                })
        ;
    }

    public void getCode(UserCodePost userCodePost){
        mModel.getCode(userCodePost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse userBeanBaseResponse) {
                        if(userBeanBaseResponse.getCode()== 0 ){
                            mRootView.PayPasswordCodeResult(userBeanBaseResponse);
                        }else{
                            mRootView.showMessage(userBeanBaseResponse.getMsg());
                        }
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
