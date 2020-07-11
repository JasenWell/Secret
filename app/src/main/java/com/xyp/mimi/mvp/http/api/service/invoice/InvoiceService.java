package com.xyp.mimi.mvp.http.api.service.invoice;

import com.xyp.mimi.mvp.http.entity.invoice.InvoiceAddPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceDeletePost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceEditorPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceInfoResult;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListPost;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InvoiceService {

    //12.0. 发票列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/invoiceList")
    Observable<InvoiceListResult> invoiceList(
            @Body InvoiceListPost invoiceListPost
            );

    //12.1. 获取发票详细信息
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/GetInfo")
    Observable<InvoiceInfoResult> getInfo(
            @Body InvoiceDeletePost invoiceDeletePost
    );

    //12.2. 获取默认发票
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/Defaultinvoice")
    Observable<BaseResponse> defaultinvoice(
    );

    //12.3. 新增发票
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/Addinvoice")
    Observable<BaseResponse> addinvoice(
            @Body InvoiceAddPost invoiceAddPost
    );

    //修改发票
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/Updateinvoice")
    Observable<BaseResponse> updateinvoice(
            @Body InvoiceEditorPost invoiceEditorPost
    );

    //12.5. 设置默认发票
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/SetDefaultinvoice")
    Observable<BaseResponse> setDefaultinvoice(
            @Body InvoiceDeletePost invoiceDeletePost
    );

    //12.6. 删除发票
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/Deleteinvoice")
    Observable<BaseResponse> deleteinvoice(
            @Body InvoiceDeletePost invoiceDeletePost
    );

    //发票
    //收费服务订单,可开票,开票历史
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/FeesOrderList")
    Observable<BaseResponse> feesOrderList(

    );

    //订单批量开票申请
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/BatchApplyInvoice")
    Observable<BaseResponse> batchApplyInvoice(

    );

    //验证并获取 订单批量开票申请详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Invoice/BatchApplyInvoiceInfo")
    Observable<BaseResponse> bsatchApplyInvoiceInfo(

    );




}
