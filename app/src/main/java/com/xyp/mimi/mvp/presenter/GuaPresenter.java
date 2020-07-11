package com.xyp.mimi.mvp.presenter;

import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.YueAndUsdtContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


public class GuaPresenter extends BasePresenter<YueAndUsdtContract.Model, YueAndUsdtContract.ViewUsdt> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public GuaPresenter(YueAndUsdtContract.Model model, YueAndUsdtContract.ViewUsdt rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }



    public void usdt(String useid,
                       String sl,
                       String email,
                       String usdt,
                       String upwd){
//        mModel.usdt( useid,  sl,  email,  usdt,  upwd)
//                .compose(RxUtils.applySchedulers(mRootView))
//                .subscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(rxErrorHandler) {
//                    @Override
//                    public void onNext(BaseResponse<Object> userBeanBaseResponse) {
//                        if(userBeanBaseResponse.getResult().equals("success")){
//                            mRootView.usdtSuccess(userBeanBaseResponse.getMsg());
//                        }else{
//                            mRootView.showMessage(userBeanBaseResponse.getMsg());
//                        }
//                    }
//                });
    }

    public void guaUSDT(String useid, String sl, String email, String usdt, String upwd) {
//        mModel .guaUSDT( useid,  sl,  email,  usdt,  upwd)
//                .compose(RxUtils.applySchedulers(mRootView))
//                .subscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(rxErrorHandler) {
//                    @Override
//                    public void onNext(BaseResponse<Object> userBeanBaseResponse) {
//                        if(userBeanBaseResponse.getResult().equals("success")){
//                            mRootView.guaUSDTSuccess(userBeanBaseResponse.getMsg());
//                        }else{
//                            mRootView.showMessage(userBeanBaseResponse.getMsg());
//                        }
//                    }
//                });
    }





}