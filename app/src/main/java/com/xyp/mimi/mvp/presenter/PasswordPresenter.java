package com.xyp.mimi.mvp.presenter;

import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.PasswordContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

public class PasswordPresenter extends BasePresenter<PasswordContract.Model,PasswordContract.View> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public PasswordPresenter(PasswordContract.Model model, PasswordContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void changePassword(String useid, String opwd, String npwd){
//        mModel.changePassword(useid,opwd,npwd)
//                .compose(RxUtils.applySchedulers(mRootView))
//                .subscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(rxErrorHandler) {
//                    @Override
//                    public void onNext(BaseResponse<Object> userBeanBaseResponse) {
//                        if(userBeanBaseResponse.getResult().equals("success")){
//                            mRootView.changePasswordSuccess(userBeanBaseResponse.getUseid());
//                        }else{
//                            mRootView.showMessage(userBeanBaseResponse.getMsg());
//                        }
//                    }
//                });
    }
}