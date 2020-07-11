package com.xyp.mimi.di.module.market;

import com.xyp.mimi.mvp.contract.market.GoodsDetailActivityContract;
import com.xyp.mimi.mvp.model.market.GoodsDetailActivityModel;

import dagger.Binds;
import dagger.Module;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/18/2020 17:35
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class GoodsDetailActivityModule {

    @Binds
    abstract GoodsDetailActivityContract.Model bindGoodsDetailActivityModel(GoodsDetailActivityModel model);
}