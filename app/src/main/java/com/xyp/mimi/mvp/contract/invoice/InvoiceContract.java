package com.xyp.mimi.mvp.contract.invoice;


import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceAddPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceDeletePost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceEditorPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceInfoResult;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;


public interface InvoiceContract {

    interface  View extends IView{
        void getInvoiceList(InvoiceListResult invoiceListResult);
        void deleteInvoiceResult(BaseResponse baseResponse);
        void setDefaultResult(BaseResponse baseResponse);

    }

    interface  InvoiceAddAndEditorView extends IView{

        void addInvoiceResult(BaseResponse baseResponse);

        void editorInvoiceResult(BaseResponse baseResponse);

        void invoiceInfoResult(InvoiceInfoResult invoiceInfoResult);


    }




    interface  Model extends IModel{
        Observable<InvoiceListResult> getInvoiceList(InvoiceListPost invoiceListPost);

        Observable<BaseResponse> addInvoice(InvoiceAddPost invocieAddPost);

        Observable<BaseResponse> deleteInvoice(InvoiceDeletePost invoiceDeletePost);

        Observable<InvoiceInfoResult> invoiceInfo(InvoiceDeletePost invoiceDeletePost);

        Observable<BaseResponse> editorInvoice(InvoiceEditorPost invoiceEditorPost);

        Observable<BaseResponse> setDefaultInvoice(InvoiceDeletePost invoiceDeletePost);

    }
}
