package com.xyp.mimi.mvp.model.wallet;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.wallet.MoneyDetailContract;
import com.xyp.mimi.mvp.http.api.service.wallet.WalletService;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailPost;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailResult;

import io.reactivex.Observable;


public class MoneyDetailModel extends BaseModel implements MoneyDetailContract.Model {
    public MoneyDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<MoneyDetailResult> getMoneyDetail(MoneyDetailPost moneyDetailPost) {
        return mRepositoryManager.obtainRetrofitService(WalletService.class)
                .getRechargeList(moneyDetailPost);
    }
}
