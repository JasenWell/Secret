package com.xyp.mimi.mvp.presenter.order;

import android.app.Application;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.xyp.mimi.mvp.contract.order.OrderContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.order.CancelOrderPost;
import com.xyp.mimi.mvp.http.entity.order.OrderListPost;
import com.xyp.mimi.mvp.http.entity.order.OrderListResult;
import com.xyp.mimi.mvp.utils.RxUtils;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/18/2020 15:27
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
public class OrderPresenter extends BasePresenter<OrderContract.Model, OrderContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public OrderPresenter(OrderContract.Model model, OrderContract.View rootView) {
        super(model, rootView);
    }

    //获取订单列表
    public void getOrderList(OrderListPost orderListPost){
        mModel.getOrderList(orderListPost)
                .compose(RxUtils.applySchedulersNoloading(mRootView))
                .subscribe(new ErrorHandleSubscriber<OrderListResult>(mErrorHandler) {
                    @Override
                    public void onNext(OrderListResult orderListResult) {
                        if(orderListResult.getCode()== 0){
                            mRootView.getOrderListResult(orderListResult);
                        }else{
                            mRootView.showMessage(orderListResult.getMsg());
                        }
                    }
                });
    }

    //取消订单
    public void cancelOrder(CancelOrderPost cancelOrderPost){
        mModel.cancelOrder(cancelOrderPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()== 0){
                            mRootView.getCancelOrderResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
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
