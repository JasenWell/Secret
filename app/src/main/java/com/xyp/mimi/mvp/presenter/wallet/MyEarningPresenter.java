package com.xyp.mimi.mvp.presenter.wallet;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningPost;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningResult;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class MyEarningPresenter extends BasePresenter<BankContract.Model,BankContract.MyEarningView> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public MyEarningPresenter(BankContract.Model model, BankContract.MyEarningView rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void getMyEarning(MyEarningPost myEarningPost){
        mModel.getMyEarning(myEarningPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<MyEarningResult>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<MyEarningResult> baseResponse) {
                        if(baseResponse.getCode() == 0){
                            mRootView.getMyEarningResult(baseResponse);
                        }else{
                            mRootView.showMessage("");
                        }
                    }
                });
    }
}
