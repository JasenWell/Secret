package com.xyp.mimi.mvp.presenter.wallet;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.AddBankPost;
import com.xyp.mimi.mvp.http.entity.wallet.DeleteMyBankCardPost;
import com.xyp.mimi.mvp.http.entity.wallet.MyBankListResult;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class MyBankListPresenter extends BasePresenter<BankContract.Model,BankContract.MyBankCardView> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public MyBankListPresenter(BankContract.Model model, BankContract.MyBankCardView rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void getMyBankCard(AddBankPost addBankPost){
        mModel.getMyBankCard(addBankPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<MyBankListResult>(rxErrorHandler) {
                    @Override
                    public void onNext(MyBankListResult baseResponse) {
                        if(baseResponse.getCode() == 0){
                            mRootView.getMyBankCardResult(baseResponse);
                        }else{
                            mRootView.showMessage("");
                        }
                    }
                });
    }
    public void deleteMyBankCard(DeleteMyBankCardPost deleteMyBankCardPost){
        mModel.deleteMyBankCard(deleteMyBankCardPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode() == 0){
                            mRootView.getDeleteBankCardResult(baseResponse);
                        }else{
                            mRootView.showMessage("");
                        }
                    }
                });
    }

}
