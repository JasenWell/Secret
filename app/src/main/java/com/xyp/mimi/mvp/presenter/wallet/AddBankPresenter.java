package com.xyp.mimi.mvp.presenter.wallet;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.AddBankPost;
import com.xyp.mimi.mvp.http.entity.wallet.CodePost;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class AddBankPresenter extends BasePresenter<BankContract.Model,BankContract.AddBankView> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public AddBankPresenter(BankContract.Model model, BankContract.AddBankView rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void addBank(AddBankPost addBankPost){
        mModel.addBank(addBankPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode() == 0){
                            mRootView.getAddBankResult(baseResponse);
                        }else{
                            mRootView.showMessage("");
                        }
                    }
                });
    }

    public void getCode(CodePost codePost){
        mModel.getBindingBankCardsCode(codePost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode() == 0){
                            mRootView.getCodeResult(baseResponse);
                        }else{
                            mRootView.showMessage("");
                        }
                    }
                });
    }

}
