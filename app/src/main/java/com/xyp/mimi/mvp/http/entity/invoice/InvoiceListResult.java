package com.xyp.mimi.mvp.http.entity.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class InvoiceListResult {

    /**
     * code : 0
     * count : 1
     * data : [{"HeaderName":"geren","Id":23,"InvoiceTitle":1,"InvoiceTitleStr":"个人","IsDefault":1,"TaxNumber":""}]
     * msg : 获取成功
     */

    private int code;
    private int count;
    private String msg;
    private List<DataBean> data;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * HeaderName : geren
         * Id : 23
         * InvoiceTitle : 1
         * InvoiceTitleStr : 个人
         * IsDefault : 1
         * TaxNumber :
         */

        private String HeaderName;
        private int Id;
        private int InvoiceTitle;
        private String InvoiceTitleStr;
        private int IsDefault;
        private String TaxNumber;

        protected DataBean(Parcel in) {
            HeaderName = in.readString();
            Id = in.readInt();
            InvoiceTitle = in.readInt();
            InvoiceTitleStr = in.readString();
            IsDefault = in.readInt();
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
            dest.writeString(HeaderName);
            dest.writeInt(Id);
            dest.writeInt(InvoiceTitle);
            dest.writeString(InvoiceTitleStr);
            dest.writeInt(IsDefault);
            dest.writeString(TaxNumber);
        }
    }
}
