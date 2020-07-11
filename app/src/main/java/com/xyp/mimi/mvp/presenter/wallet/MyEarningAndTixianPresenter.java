package com.xyp.mimi.mvp.presenter.wallet;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailPost;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

//收益明细 和提现记录
@ActivityScope
public class MyEarningAndTixianPresenter extends BasePresenter<BankContract.Model,BankContract.MyEarningAndTixianView> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public MyEarningAndTixianPresenter(BankContract.Model model, BankContract.MyEarningAndTixianView rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void getMyEarningDetail(MoneyDetailPost moneyDetailPost){
        mModel.getMyEarningDetail(moneyDetailPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<Object> baseResponse) {
                        if(baseResponse.getCode() == 0){
                            mRootView.getMyEarningResult(baseResponse);
                        }else{
                            mRootView.showMessage("");
                        }
                    }
                });
    }
}
