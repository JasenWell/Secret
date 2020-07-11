package com.xyp.mimi.di.component.order;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.FragmentScope;
import com.xyp.mimi.di.module.order.OrderModule;
import com.xyp.mimi.mvp.contract.order.OrderContract;

import com.xyp.mimi.mvp.ui.fragment.AllOrderFragment;


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
@Component(modules = OrderModule.class, dependencies = AppComponent.class)
public interface OrderComponent {
    void inject(AllOrderFragment allOrderFragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        OrderComponent.Builder view(OrderContract.View view);

        OrderComponent.Builder appComponent(AppComponent appComponent);

        OrderComponent build();
    }
}