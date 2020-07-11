package com.xyp.mimi.di.component.market;

import dagger.BindsInstance;
import dagger.Component;
import com.jess.arms.di.component.AppComponent;

import com.xyp.mimi.di.module.market.GoodsDetailActivityModule;

import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.mvp.contract.market.GoodsDetailActivityContract;
import com.xyp.mimi.mvp.ui.activity.market.GoodsDetailActivity;


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
@ActivityScope
@Component(modules = GoodsDetailActivityModule.class, dependencies = AppComponent.class)
public interface GoodsDetailActivityComponent {
    void inject(GoodsDetailActivity activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        GoodsDetailActivityComponent.Builder view(GoodsDetailActivityContract.View view);
        GoodsDetailActivityComponent.Builder appComponent(AppComponent appComponent);
        GoodsDetailActivityComponent build();
    }
}