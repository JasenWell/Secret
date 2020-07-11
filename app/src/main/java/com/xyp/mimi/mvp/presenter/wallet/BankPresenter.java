package com.xyp.mimi.mvp.presenter.wallet;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.wallet.BankInfoListResult;
import com.xyp.mimi.mvp.utils.RxUtils;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class BankPresenter extends BasePresenter<BankContract.Model,BankContract.BankInfoListView> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public BankPresenter(BankContract.Model model, BankContract.BankInfoListView rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void getBankInfoList(){
        mModel.getBankInfoList()
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BankInfoListResult>(rxErrorHandler) {
                    @Override
                    public void onNext(BankInfoListResult bankInfoListResult) {
                        if(bankInfoListResult.getCode() == 0){
                            mRootView.getBankInfoListResult(bankInfoListResult);
                        }else{
                            mRootView.showMessage("");
                        }
                    }
                });
    }
}
