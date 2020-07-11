package com.xyp.mimi.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.TestContract;
import com.xyp.mimi.mvp.http.entity.NetData;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class TestPresenter extends BasePresenter<TestContract.Model, TestContract.View> {
    @Inject
    RxErrorHandler rxErrorHandler;

    @Inject
    public TestPresenter(TestContract.Model model, TestContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void getTest(){
        mModel.getTest()
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<NetData>(rxErrorHandler) {
                    @Override
                    public void onNext(NetData p) {
                        if(p.getResult().equals("success")){
                            mRootView.getTestSuccess(p);
                        }else{
                            mRootView.showMessage(p.getMsg());
                        }
                    }
                })
        ;
    }

}