package com.xyp.mimi.mvp.model.invoice;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.invoice.InvoiceContract;
import com.xyp.mimi.mvp.http.api.service.invoice.InvoiceService;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceAddPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceDeletePost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceEditorPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceInfoResult;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import io.reactivex.Observable;

public class InvoiceModel extends BaseModel implements InvoiceContract.Model {

    public InvoiceModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
    @Override
    public Observable<InvoiceListResult> getInvoiceList(InvoiceListPost invoiceListPost) {
        return mRepositoryManager.obtainRetrofitService(InvoiceService.class)
                .invoiceList(invoiceListPost);
    }

    @Override
    public Observable<BaseResponse> addInvoice(InvoiceAddPost invocieAddPost) {
        return mRepositoryManager.obtainRetrofitService(InvoiceService.class)
                .addinvoice(invocieAddPost);
    }

    @Override
    public Observable<BaseResponse> deleteInvoice(InvoiceDeletePost invoiceDeletePost) {
        return mRepositoryManager.obtainRetrofitService(InvoiceService.class)
                .deleteinvoice(invoiceDeletePost);
    }

    @Override
    public Observable<InvoiceInfoResult> invoiceInfo(InvoiceDeletePost invoiceDeletePost) {
        return mRepositoryManager.obtainRetrofitService(InvoiceService.class)
                .getInfo(invoiceDeletePost);

    }

    @Override
    public Observable<BaseResponse> editorInvoice(InvoiceEditorPost invoiceEditorPost) {
        return mRepositoryManager.obtainRetrofitService(InvoiceService.class)
                .updateinvoice(invoiceEditorPost);
    }

    @Override
    public Observable<BaseResponse> setDefaultInvoice(InvoiceDeletePost invoiceDeletePost) {
        return mRepositoryManager.obtainRetrofitService(InvoiceService.class)
                .setDefaultinvoice(invoiceDeletePost);
    }


}
