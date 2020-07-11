package com.xyp.mimi.mvp.model.wallet;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.api.service.wallet.WalletService;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.AddBankPost;
import com.xyp.mimi.mvp.http.entity.wallet.BankInfoListResult;
import com.xyp.mimi.mvp.http.entity.wallet.CodePost;
import com.xyp.mimi.mvp.http.entity.wallet.DeleteMyBankCardPost;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailPost;
import com.xyp.mimi.mvp.http.entity.wallet.MyBankListResult;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningPost;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningResult;
import com.xyp.mimi.mvp.http.entity.wallet.TixianPost;

import io.reactivex.Observable;


public class BankModel extends BaseModel implements BankContract.Model {
    public BankModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BankInfoListResult> getBankInfoList() {
        return mRepositoryManager.obtainRetrofitService(WalletService.class)
                .bankinfoList();
    }

    @Override
    public Observable<BaseResponse> addBank(AddBankPost addBankPost) {
        return mRepositoryManager.obtainRetrofitService(WalletService.class)
                .addBank(addBankPost);
    }

    @Override
    public Observable<BaseResponse> getBindingBankCardsCode(CodePost codePost) {
        return mRepositoryManager.obtainRetrofitService(WalletService.class)
                .bindingBankCards(codePost);
    }

    @Override
    public Observable<MyBankListResult> getMyBankCard(AddBankPost addBankPost) {
        return mRepositoryManager.obtainRetrofitService(WalletService.class)
                .bankList(addBankPost);
    }

    @Override
    public Observable<BaseResponse> deleteMyBankCard(DeleteMyBankCardPost deleteMyBankCardPost) {
        return mRepositoryManager.obtainRetrofitService(WalletService.class)
                .deleteBank(deleteMyBankCardPost);
    }

    @Override
    public Observable<BaseResponse<MyEarningResult>> getMyEarning(MyEarningPost myEarningPost) {
        return mRepositoryManager.obtainRetrofitService(WalletService.class)
                .incomeDetailsTZ(myEarningPost);
    }

    @Override
    public Observable<BaseResponse<Object>> getMyEarningDetail(MoneyDetailPost moneyDetailPost) {
        return mRepositoryManager.obtainRetrofitService(WalletService.class)
                .incomeDetails(moneyDetailPost);
    }

    @Override
    public Observable<BaseResponse<Object>> yueTixian(TixianPost tixianPost) {
        return mRepositoryManager.obtainRetrofitService(WalletService.class)
                .yueMoneyApply(tixianPost);
    }

}
