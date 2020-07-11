package com.xyp.mimi.mvp.presenter;

import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.BindContract;
import com.xyp.mimi.mvp.http.entity.product.Product;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class BindPresenter extends BasePresenter<BindContract.Model,BindContract.View> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public BindPresenter(BindContract.Model model, BindContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }


    public void bind( String useId,String acc, String impwd){
//        mModel.bindUse(useId,acc,impwd)
//                .compose(RxUtils.applySchedulers(mRootView))
//                .subscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(rxErrorHandler) {
//                    @Override
//                    public void onNext(BaseResponse<Object> userBeanBaseResponse) {
//                        if(userBeanBaseResponse.getResult().equals("success")){
//                            mRootView.bindSuccess(userBeanBaseResponse.getUseid());
//                        }else{
//                            mRootView.showMessage(userBeanBaseResponse.getMsg());
//                        }
//                    }
//                })
//        ;
    }


    public void getBindRecommend(String useId){
        mModel.getBindRecommend(useId)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<Product>(rxErrorHandler) {
                    @Override
                    public void onNext(Product product) {
                        if (product.getResult().equals("success")&&!product.getMsg().equals("没有记录")) {
                            mRootView.getBindRecommendSuccess(product);
                        } else if(product.getResult().equals("success")&&product.getMsg().equals("没有记录")){
                            mRootView.showMessage(product.getMsg());
                        } else{
                            mRootView.showMessage(product.getMsg());
                        }

                    }
                });
    }

    public void delete(String useId,String netId){
        mModel.delete(useId,netId)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<Product>(rxErrorHandler) {
                    @Override
                    public void onNext(Product product) {
                        if (product.getResult().equals("success")) {
                            mRootView.deleteSuccess(product.getMsg());
                        } else {
                            mRootView.showMessage(product.getMsg());
                        }
                    }
                });

    }


}