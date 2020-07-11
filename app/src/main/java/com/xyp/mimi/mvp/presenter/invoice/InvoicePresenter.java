package com.xyp.mimi.mvp.presenter.invoice;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.invoice.InvoiceContract;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceDeletePost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.utils.RxUtils;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class InvoicePresenter extends BasePresenter<InvoiceContract.Model,InvoiceContract.View> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public InvoicePresenter(InvoiceContract.Model model, InvoiceContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }


    public void getInvoiceList(InvoiceListPost invoiceListPost){
        mModel.getInvoiceList(invoiceListPost)
                .compose(RxUtils.applySchedulersNoloading(mRootView))
                .subscribe(new ErrorHandleSubscriber<InvoiceListResult>(rxErrorHandler) {
                    @Override
                    public void onNext(InvoiceListResult invoiceListResult) {
                        if(invoiceListResult.getCode()== 0){
                            mRootView.getInvoiceList(invoiceListResult);
                        }else{
                            mRootView.showMessage(invoiceListResult.getMsg());
                        }
                    }
                });
    }

    public void deleteInvoice(InvoiceDeletePost invoiceDeletePost){
        mModel.deleteInvoice(invoiceDeletePost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()== 0){
                            mRootView.deleteInvoiceResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                });
    }


    public void setDefaultInvoice(InvoiceDeletePost invoiceDeletePost){
        mModel.setDefaultInvoice(invoiceDeletePost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()== 0){
                            mRootView.deleteInvoiceResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                });
    }

}