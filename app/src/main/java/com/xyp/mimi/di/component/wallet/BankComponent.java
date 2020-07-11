package com.xyp.mimi.di.component.wallet;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.di.module.wallet.BankModule;
import com.xyp.mimi.mvp.ui.activity.wallet.AddBankCardActivity;
import com.xyp.mimi.mvp.ui.activity.wallet.BankListActivity;
import com.xyp.mimi.mvp.ui.activity.wallet.EarningAndTixianActivity;
import com.xyp.mimi.mvp.ui.activity.wallet.MyEarningActivity;
import com.xyp.mimi.mvp.ui.activity.wallet.SelelctBankActivity;
import com.xyp.mimi.mvp.ui.activity.wallet.TixianActivity;

import dagger.Component;

@ActivityScope
@Component(modules = {BankModule.class},dependencies = AppComponent.class)

public interface BankComponent {

    void injectSelelctBank(SelelctBankActivity selelctBankActivity);

    void injectAddBankCard(AddBankCardActivity addBankCardActivity);

    void injectBankList(BankListActivity bankListActivity);

    void injectEarning(MyEarningActivity myEarningActivity);

    void injectEarnAndTixian(EarningAndTixianActivity earningAndTixianActivity);

    void injectYueTixian(TixianActivity tixianActivity);

}
