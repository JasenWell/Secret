package com.xyp.mimi.di.module.wallet;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.model.wallet.BankModel;
import dagger.Module;
import dagger.Provides;

@Module
public class BankModule {

    BankContract.BankInfoListView bankInfoListView;

    BankContract.AddBankView addBankView;

    BankContract.MyBankCardView myBankCardView;

    BankContract.MyEarningView myEarningView;

    BankContract.MyEarningAndTixianView myEarningAndTixianView;

    BankContract.MyYueTixianView myYueTixianView;


    public BankModule(BankContract.BankInfoListView bankInfoListView) {
        this.bankInfoListView = bankInfoListView;
    }
    public BankModule(BankContract.AddBankView addBankView) {
        this.addBankView = addBankView;
    }
    public BankModule(BankContract.MyBankCardView myBankCardView) {
        this.myBankCardView = myBankCardView;
    }
    public BankModule(BankContract.MyEarningView myEarningView) {
        this.myEarningView = myEarningView;
    }
    public BankModule(BankContract.MyEarningAndTixianView myEarningAndTixianView) {
        this.myEarningAndTixianView = myEarningAndTixianView;
    }

    public BankModule(BankContract.MyYueTixianView myYueTixianView) {
        this.myYueTixianView = myYueTixianView;
    }


    @Provides
    @ActivityScope
    public BankContract.Model provideModel(IRepositoryManager repositoryManager){
        return new BankModel(repositoryManager);
    }

    @Provides
    @ActivityScope
    public BankContract.BankInfoListView provideView(){
        return bankInfoListView;
    }

    @Provides
    @ActivityScope
    public BankContract.AddBankView provideView1(){
        return addBankView;
    }

    @Provides
    @ActivityScope
    public BankContract.MyBankCardView provideView2(){
        return myBankCardView;
    }

    @Provides
    @ActivityScope
    public BankContract.MyEarningView provideView3(){
        return myEarningView;
    }

    @Provides
    @ActivityScope
    public BankContract.MyEarningAndTixianView provideView4(){
        return myEarningAndTixianView;
    }

    @Provides
    @ActivityScope
    public BankContract.MyYueTixianView provideView5(){
        return myYueTixianView;
    }

}
