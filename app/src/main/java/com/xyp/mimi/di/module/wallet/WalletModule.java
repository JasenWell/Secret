package com.xyp.mimi.di.module.wallet;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.wallet.MoneyDetailContract;
import com.xyp.mimi.mvp.model.wallet.MoneyDetailModel;

import dagger.Module;
import dagger.Provides;

@Module
public class WalletModule {

    MoneyDetailContract.View view;

    public WalletModule(MoneyDetailContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public MoneyDetailContract.Model provideModel(IRepositoryManager repositoryManager){
     return new MoneyDetailModel(repositoryManager);
    }

    @Provides
    @FragmentScope
    public MoneyDetailContract.View provideView(){
        return view;
    }

}
