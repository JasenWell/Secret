package com.xyp.mimi.di.module.cart;


import dagger.Module;
import dagger.Provides;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.cart.CartContract;
import com.xyp.mimi.mvp.model.cart.CartModel;

@Module
public  class CartModule {

    CartContract.View view;
    public CartModule(CartContract.View view) {
        this.view = view;
    }


    @Provides
    @FragmentScope
    public CartContract.Model provideModel(IRepositoryManager repositoryManager){
        return new CartModel(repositoryManager);
    }

    @Provides
    @FragmentScope
    public CartContract.View provideView(){
        return view;
    }

}