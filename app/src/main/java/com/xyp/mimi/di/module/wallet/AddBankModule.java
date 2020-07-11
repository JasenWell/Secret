package com.xyp.mimi.di.module.wallet;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.model.wallet.BankModel;

import dagger.Module;
import dagger.Provides;

@Module
public class AddBankModule {


    BankContract.AddBankView addBankView;

    public AddBankModule(BankContract.AddBankView addBankView) {
        this.addBankView = addBankView;
    }


    @Provides
    @ActivityScope
    public BankContract.Model provideModel(IRepositoryManager repositoryManager){
        return new BankModel(repositoryManager);
    }

    @Provides
    @ActivityScope
    public BankContract.AddBankView provideView(){
        return addBankView;
    }


}
