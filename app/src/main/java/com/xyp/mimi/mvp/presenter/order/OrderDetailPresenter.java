package com.xyp.mimi.mvp.presenter.order;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.xyp.mimi.mvp.contract.order.OrderDetailContract;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailPost;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailResult;
import com.xyp.mimi.mvp.utils.RxUtils;


@ActivityScope
public class OrderDetailPresenter extends BasePresenter<OrderDetailContract.Model, OrderDetailContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public OrderDetailPresenter(OrderDetailContract.Model model, OrderDetailContract.View rootView) {
        super(model, rootView);
    }


    //获取订单详情
    public void getOrderDetail(OrderDetailPost orderDetailPost){
        mModel.getOrderDetail(orderDetailPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<OrderDetailResult>(mErrorHandler) {
                    @Override
                    public void onNext(OrderDetailResult orderDetailResult) {
                        if(orderDetailResult.getCode()== 0){
                            mRootView.getOrderDetailResult(orderDetailResult);
                        }else{
                            mRootView.showMessage(orderDetailResult.getMsg());
                        }
                    }
                });
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
