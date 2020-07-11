package com.xyp.mimi.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.xyp.mimi.di.module.YueAndUsdtModule;
import com.xyp.mimi.mvp.ui.fragment.USDTBuyAndGuaFragment;
import com.xyp.mimi.mvp.ui.fragment.YueBuyAndGuaFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {YueAndUsdtModule.class},dependencies = AppComponent.class)

public interface YueAndUsdtComponent {

    void inject(YueBuyAndGuaFragment yueBuyFragment);

    void inject(USDTBuyAndGuaFragment usdtBuyAndGuaFragment);


}
