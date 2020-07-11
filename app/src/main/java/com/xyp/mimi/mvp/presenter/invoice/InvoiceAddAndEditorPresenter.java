package com.xyp.mimi.mvp.presenter.invoice;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.invoice.InvoiceContract;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceAddPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceDeletePost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceEditorPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceInfoResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.utils.RxUtils;
import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class InvoiceAddAndEditorPresenter extends BasePresenter<InvoiceContract.Model,InvoiceContract.InvoiceAddAndEditorView> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public InvoiceAddAndEditorPresenter(InvoiceContract.Model model, InvoiceContract.InvoiceAddAndEditorView rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }


    public void addInvoice(InvoiceAddPost invoiceAddPost){
        mModel.addInvoice(invoiceAddPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()== 0){
                            mRootView.addInvoiceResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                });
    }


    public void invoideInfo(InvoiceDeletePost invoiceDeletePost){
        mModel.invoiceInfo(invoiceDeletePost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<InvoiceInfoResult>(rxErrorHandler) {
                    @Override
                    public void onNext(InvoiceInfoResult baseResponse) {
                        if(baseResponse.getCode()== 0){
                            mRootView.invoiceInfoResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                });
    }


    public void editorInvoice(InvoiceEditorPost invoiceEditorPost){
        mModel.editorInvoice(invoiceEditorPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode()== 0){
                            mRootView.addInvoiceResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                });
    }


}