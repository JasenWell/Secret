package com.xyp.mimi.di.component.order;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.di.module.order.OrderDetailModule;
import com.xyp.mimi.mvp.contract.order.OrderDetailContract;
import com.xyp.mimi.mvp.ui.activity.order.OrderDetailActivity;


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
@Component(modules = OrderDetailModule.class, dependencies = AppComponent.class)
public interface OrderDetailComponent {

    void inject(OrderDetailActivity orderDetailActivity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        OrderDetailComponent.Builder view(OrderDetailContract.View view);

        OrderDetailComponent.Builder appComponent(AppComponent appComponent);

        OrderDetailComponent build();
    }
}