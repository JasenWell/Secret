package com.xyp.mimi.mvp.presenter;

import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.YueAndUsdtContract;
import com.xyp.mimi.mvp.http.entity.chongzhi.Chongzhi;
import com.xyp.mimi.mvp.utils.RxUtils;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;



public class YuePresenter extends BasePresenter<YueAndUsdtContract.Model, YueAndUsdtContract.ViewYue> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public YuePresenter(YueAndUsdtContract.Model model, YueAndUsdtContract.ViewYue rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }



    public void yue(String useid,
                    String usename,
                    String lname,
                    String lpwd){
//        mModel.yue(useid,usename,lname,lpwd)
//                .compose(RxUtils.applySchedulers(mRootView))
//                .subscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(rxErrorHandler) {
//                    @Override
//                    public void onNext(BaseResponse<Object> userBeanBaseResponse) {
//                        if(userBeanBaseResponse.getResult().equals("success")){
//                            mRootView.yueSuccess(userBeanBaseResponse.getMsg());
//                        }else{
//                            mRootView.showMessage(userBeanBaseResponse.getMsg());
//                        }
//                    }
//                });
    }

    public void guaYue(String useid, String sl, String email, String useurl, String upwd){
//        mModel.guaYue( useid,  sl,  email,  useurl,  upwd)
//                .compose(RxUtils.applySchedulers(mRootView))
//                .subscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(rxErrorHandler) {
//                    @Override
//                    public void onNext(BaseResponse<Object> userBeanBaseResponse) {
//                        if(userBeanBaseResponse.getResult().equals("success")){
//                            mRootView.guaYueSuccess(userBeanBaseResponse.getMsg());
//                        }else{
//                            mRootView.showMessage(userBeanBaseResponse.getMsg());
//                        }
//                    }
//                });
    }

    public void getChongzhi(String useid){
        mModel.getChongzhi(useid)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<Chongzhi>(rxErrorHandler) {
                    @Override
                    public void onNext(Chongzhi  chongzhi) {
                        if(chongzhi.getResult().equals("success")){
                            mRootView.getYueSuccess(chongzhi);
                        }else{
                            mRootView.showMessage(chongzhi.getMsg());
                        }
                    }
                })
        ;
    }

}