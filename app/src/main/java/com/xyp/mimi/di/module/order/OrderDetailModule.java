package com.xyp.mimi.di.module.order;


import dagger.Binds;
import dagger.Module;

import com.xyp.mimi.mvp.contract.order.OrderDetailContract;
import com.xyp.mimi.mvp.model.order.OrderDetailModel;


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
@Module
public abstract class OrderDetailModule {

    @Binds
    abstract OrderDetailContract.Model bindOrderDetailModel(OrderDetailModel model);
}