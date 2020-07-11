package com.xyp.mimi.mvp.model.order;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import com.xyp.mimi.mvp.contract.order.OrderDetailContract;
import com.xyp.mimi.mvp.http.api.service.order.OrderService;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailPost;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailResult;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/03/2020 10:32
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class OrderDetailModel extends BaseModel implements OrderDetailContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public OrderDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<OrderDetailResult> getOrderDetail(OrderDetailPost orderDetailPost) {
        return mRepositoryManager.obtainRetrofitService(OrderService.class)
                .orderDetails(orderDetailPost);
    }
}