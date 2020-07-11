package com.xyp.mimi.mvp.presenter.history;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.history.HistoryContract;
import com.xyp.mimi.mvp.http.entity.history.HistoryDeletePost;
import com.xyp.mimi.mvp.http.entity.history.HistoryPost;
import com.xyp.mimi.mvp.http.entity.history.HistoryResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class HistoryPresenter extends BasePresenter<HistoryContract.Model,HistoryContract.View> {

    @Inject
    RxErrorHandler rxErrorHandler;

    @Inject
    public HistoryPresenter(HistoryContract.Model model, HistoryContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model, rootView);
        this.rxErrorHandler = rxErrorHandler;
    }

    public void  getHistoryData(HistoryPost historyPost){
        mModel.getHistoryData(historyPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<HistoryResult>(rxErrorHandler) {
                    @Override
                    public void onNext(HistoryResult history) {
//                        if(history.getCode()==0){
//                            mRootView.HistoryResult(history);
//                        }else{
//                            mRootView.showMessage(history.getMsg());
//                        }
                    }
                });
    }

    public void  deleteHistory(HistoryDeletePost historyDeletePost){
        mModel.deleteHistory(historyDeletePost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()==0){
                            mRootView.deleteHistory(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                });
    }






}
