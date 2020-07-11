package com.xyp.mimi.di.component.cart;

import dagger.Component;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.xyp.mimi.di.module.cart.CartModule;
import com.xyp.mimi.mvp.ui.fragment.cart.CartFragment;

@FragmentScope
@Component(modules = CartModule.class, dependencies = AppComponent.class)

public interface CartComponent {

    void inject(CartFragment cartFragment);


}