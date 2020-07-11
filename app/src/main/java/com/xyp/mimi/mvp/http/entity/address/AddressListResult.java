package com.xyp.mimi.mvp.http.entity.address;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AddressListResult {

    /**
     * code : 0
     * msg : 获取成功
     * data : [{"Id":773,"Consignee":"向","Mobile":"18683573857","Address":"四川省 成都市 锦江区  十几块","ProvinceCode":"510000","IsDefault":0}]
     * count : 1
     */
    private int code;
    private String msg;
    private int count;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * Id : 773
         * Consignee : 向
         * Mobile : 18683573857
         * Address : 四川省 成都市 锦江区  十几块
         * ProvinceCode : 510000
         * IsDefault : 0
         */

        private int Id;
        private String Consignee;
        private String Mobile;
        private String Address;
        private String ProvinceCode;
        private int IsDefault;

        protected DataBean(Parcel in) {
            Id = in.readInt();
            Consignee = in.readString();
            Mobile = in.readString();
            Address = in.readString();
            ProvinceCode = in.readString();
            IsDefault = in.readInt();
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

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getConsignee() {
            return Consignee;
        }

        public void setConsignee(String Consignee) {
            this.Consignee = Consignee;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getProvinceCode() {
            return ProvinceCode;
        }

        public void setProvinceCode(String ProvinceCode) {
            this.ProvinceCode = ProvinceCode;
        }

        public int getIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(int IsDefault) {
            this.IsDefault = IsDefault;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(Id);
            dest.writeString(Consignee);
            dest.writeString(Mobile);
            dest.writeString(Address);
            dest.writeString(ProvinceCode);
            dest.writeInt(IsDefault);
        }
    }
}
