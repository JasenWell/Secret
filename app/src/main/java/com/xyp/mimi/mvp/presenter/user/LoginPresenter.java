package com.xyp.mimi.mvp.presenter.user;

import android.util.Log;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.im.bean.ResponseUserInfo;
import com.xyp.mimi.im.common.ResultCallback;
import com.xyp.mimi.im.im.IMManager;
import com.xyp.mimi.im.model.UserCacheInfo;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.api.Api;
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

    public void login(String account,String password){
        mModel.login(account,password)
        .compose(RxUtils.applySchedulers(mRootView))
        .subscribe(new ErrorHandleSubscriber<BaseResponse<LoginUserResult>>(rxErrorHandler) {
            @Override
            public void onNext(BaseResponse<LoginUserResult> userBeanBaseResponse) {
                Log.d("response",userBeanBaseResponse.toString());//BaseResponse count
                if(userBeanBaseResponse.getCode() == Api.RequestSuccess) {
                    final ResponseUserInfo userInfo = userBeanBaseResponse.getData().getUser();
                    if (userInfo.getId() != null) {
                        final String token =  UserCache.getInstance().getString(UserCache.KEY_USER_TOKEN+userInfo.getId(),"");
                        IMManager.getInstance().connectIM(token, true, new ResultCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                // 存储当前登录成功的用户信息
                                UserCacheInfo info = new UserCacheInfo(userInfo.getId(),token,
                                        userInfo.getAccount(), userInfo.getPassword(), userInfo.getCountr());
                                UserCache.getInstance().saveUserCache(info);
                            }

                            @Override
                            public void onFail(int errorCode) {
                            }
                        });
                        mRootView.loginResult(userBeanBaseResponse.getData());
                    } else {
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
}
