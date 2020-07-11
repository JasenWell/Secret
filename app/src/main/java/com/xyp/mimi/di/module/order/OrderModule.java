package com.xyp.mimi.di.module.order;

import dagger.Binds;
import dagger.Module;

import com.xyp.mimi.mvp.contract.order.OrderContract;
import com.xyp.mimi.mvp.model.order.OrderModel;


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
@Module
public abstract class OrderModule {

    @Binds
    abstract OrderContract.Model bindOrderModel(OrderModel model);
}