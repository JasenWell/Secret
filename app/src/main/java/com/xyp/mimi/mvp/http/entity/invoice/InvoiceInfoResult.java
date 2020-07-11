package com.xyp.mimi.mvp.http.entity.invoice;

import android.os.Parcel;
import android.os.Parcelable;

public class InvoiceInfoResult {


    /**
     * code : 0
     * count : 0
     * data : {"BankAccount":"","BankName":"","Email":"","HeaderName":"geren","Id":23,"InvoiceTitle":1,"InvoiceTitleStr":"个人","IsDefault":0,"Note":"","Phone":"","RegAddress":"","RegCall":"","TaxNumber":""}
     * msg : 获取成功
     */
    private int code;
    private int count;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean implements Parcelable {
        /**
         * BankAccount :
         * BankName :
         * Email :
         * HeaderName : geren
         * Id : 23
         * InvoiceTitle : 1
         * InvoiceTitleStr : 个人
         * IsDefault : 0
         * Note :
         * Phone :
         * RegAddress :
         * RegCall :
         * TaxNumber :
         */

        private String BankAccount;
        private String BankName;
        private String Email;
        private String HeaderName;
        private int Id;
        private int InvoiceTitle;
        private String InvoiceTitleStr;
        private int IsDefault;
        private String Note;
        private String Phone;
        private String RegAddress;
        private String RegCall;
        private String TaxNumber;

        protected DataBean(Parcel in) {
            BankAccount = in.readString();
            BankName = in.readString();
            Email = in.readString();
            HeaderName = in.readString();
            Id = in.readInt();
            InvoiceTitle = in.readInt();
            InvoiceTitleStr = in.readString();
            IsDefault = in.readInt();
            Note = in.readString();
            Phone = in.readString();
            RegAddress = in.readString();
            RegCall = in.readString();
            TaxNumber = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getBankAccount() {
            return BankAccount;
        }

        public void setBankAccount(String BankAccount) {
            this.BankAccount = BankAccount;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getHeaderName() {
            return HeaderName;
        }

        public void setHeaderName(String HeaderName) {
            this.HeaderName = HeaderName;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getInvoiceTitle() {
            return InvoiceTitle;
        }

        public void setInvoiceTitle(int InvoiceTitle) {
            this.InvoiceTitle = InvoiceTitle;
        }

        public String getInvoiceTitleStr() {
            return InvoiceTitleStr;
        }

        public void setInvoiceTitleStr(String InvoiceTitleStr) {
            this.InvoiceTitleStr = InvoiceTitleStr;
        }

        public int getIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(int IsDefault) {
            this.IsDefault = IsDefault;
        }

        public String getNote() {
            return Note;
        }

        public void setNote(String Note) {
            this.Note = Note;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getRegAddress() {
            return RegAddress;
        }

        public void setRegAddress(String RegAddress) {
            this.RegAddress = RegAddress;
        }

        public String getRegCall() {
            return RegCall;
        }

        public void setRegCall(String RegCall) {
            this.RegCall = RegCall;
        }

        public String getTaxNumber() {
            return TaxNumber;
        }

        public void setTaxNumber(String TaxNumber) {
            this.TaxNumber = TaxNumber;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(BankAccount);
            dest.writeString(BankName);
            dest.writeString(Email);
            dest.writeString(HeaderName);
            dest.writeInt(Id);
            dest.writeInt(InvoiceTitle);
            dest.writeString(InvoiceTitleStr);
            dest.writeInt(IsDefault);
            dest.writeString(Note);
            dest.writeString(Phone);
            dest.writeString(RegAddress);
            dest.writeString(RegCall);
            dest.writeString(TaxNumber);
        }
    }
}
