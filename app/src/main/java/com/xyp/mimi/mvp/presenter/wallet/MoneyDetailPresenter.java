package com.xyp.mimi.mvp.presenter.wallet;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.wallet.MoneyDetailContract;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailPost;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailResult;
import com.xyp.mimi.mvp.utils.RxUtils;
import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@FragmentScope
public class MoneyDetailPresenter extends BasePresenter<MoneyDetailContract.Model,MoneyDetailContract.View> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public MoneyDetailPresenter(MoneyDetailContract.Model model, MoneyDetailContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void getMoneyDetail(MoneyDetailPost moneyDetailPost){
        mModel.getMoneyDetail(moneyDetailPost)
                .compose(RxUtils.applySchedulersNoloading(mRootView))
                .subscribe(new ErrorHandleSubscriber<MoneyDetailResult>(rxErrorHandler) {
                    @Override
                    public void onNext(MoneyDetailResult moneyDetailResult) {
                        if(moneyDetailResult.getCode() == 0){
                            mRootView.getMoneyDetail(moneyDetailResult);
                        }else{
                            mRootView.showMessage("");
                        }
                    }
                });
    }
}
