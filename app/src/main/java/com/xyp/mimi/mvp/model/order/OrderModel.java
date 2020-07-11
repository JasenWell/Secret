package com.xyp.mimi.mvp.model.order;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import com.xyp.mimi.mvp.contract.order.OrderContract;
import com.xyp.mimi.mvp.http.api.service.order.OrderService;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.order.CancelOrderPost;
import com.xyp.mimi.mvp.http.entity.order.OrderListPost;
import com.xyp.mimi.mvp.http.entity.order.OrderListResult;

import io.reactivex.Observable;


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
public class OrderModel extends BaseModel implements OrderContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public OrderModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<OrderListResult> getOrderList(OrderListPost orderListPost) {
        return mRepositoryManager.obtainRetrofitService(OrderService.class)
                .orderList(orderListPost);
    }

    @Override
    public Observable<BaseResponse> cancelOrder(CancelOrderPost cancelOrderPost) {
        return  mRepositoryManager.obtainRetrofitService(OrderService.class)
                .cancelOrders(cancelOrderPost);
    }
}