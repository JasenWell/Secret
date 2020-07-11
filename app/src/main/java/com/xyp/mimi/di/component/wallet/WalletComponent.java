package com.xyp.mimi.di.component.wallet;


import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.xyp.mimi.di.module.wallet.WalletModule;
import com.xyp.mimi.mvp.ui.fragment.wallet.AllFragment;

import dagger.Component;

@FragmentScope
@Component(modules = WalletModule.class ,dependencies = AppComponent.class)
public interface WalletComponent {

    void inject(AllFragment allFragment);

}
