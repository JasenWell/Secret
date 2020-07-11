package com.xyp.mimi.mvp.presenter.wallet;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.wallet.BankContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.TixianPost;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class YueTixianPresenter extends BasePresenter<BankContract.Model,BankContract.MyYueTixianView> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public YueTixianPresenter(BankContract.Model model, BankContract.MyYueTixianView rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void yueTixian(TixianPost tixianPost){
        mModel.yueTixian(tixianPost)
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
