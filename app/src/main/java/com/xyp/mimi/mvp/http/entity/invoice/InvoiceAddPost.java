package com.xyp.mimi.mvp.http.entity.invoice;

public class InvoiceAddPost {

    private  String UserId;
    private  String Token;
    private int InvoiceTitle;
    private  String HeaderName;
    private int IsDefault;
    private  String TaxNumber;
    private  String RegAddress;
    private  String RegCall;
    private  String BankName;
    private  String BankAccount;
    private  String Phone;



    public InvoiceAddPost(String userId, String token, int invoiceTitle, String headerName, int isDefault) {
        UserId = userId;
        Token = token;
        InvoiceTitle = invoiceTitle;
        HeaderName = headerName;
        IsDefault = isDefault;
    }

    public InvoiceAddPost(String userId, String token, int invoiceTitle, String headerName, String phone ,int isDefault) {
        UserId = userId;
        Token = token;
        InvoiceTitle = invoiceTitle;
        HeaderName = headerName;
        Phone = phone;
        IsDefault = isDefault;
    }



    public InvoiceAddPost(String userId, String token, int invoiceTitle, String headerName, int isDefault, String taxNumber) {
        UserId = userId;
        Token = token;
        InvoiceTitle = invoiceTitle;
        HeaderName = headerName;
        IsDefault = isDefault;
        TaxNumber = taxNumber;

    }


    public InvoiceAddPost(String userId, String token, int invoiceTitle, String headerName, int isDefault, String taxNumber, String regAddress, String regCall, String bankName, String bankAccount) {
        UserId = userId;
        Token = token;
        InvoiceTitle = invoiceTitle;
        HeaderName = headerName;
        IsDefault = isDefault;
        TaxNumber = taxNumber;
        RegAddress = regAddress;
        RegCall = regCall;
        BankName = bankName;
        BankAccount = bankAccount;
    }

}
