package com.xyp.mimi.mvp.presenter.user;

import android.util.Log;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;
import com.xyp.mimi.mvp.http.entity.login.LoginUserPost;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class LoginPresenter extends BasePresenter<UserContract.Model, UserContract.LoginView> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public LoginPresenter(UserContract.Model model, UserContract.LoginView rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void login(LoginUserPost loginUserPost){
        mModel.login(loginUserPost)
        .compose(RxUtils.applySchedulers(mRootView))
        .subscribe(new ErrorHandleSubscriber<BaseResponse<LoginUserResult>>(rxErrorHandler) {
            @Override
            public void onNext(BaseResponse<LoginUserResult> userBeanBaseResponse) {
                 if(userBeanBaseResponse.getCode()== 0 ){
                     mRootView.loginResult(userBeanBaseResponse.getData());
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
}
