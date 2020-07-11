package com.xyp.mimi.mvp.http.entity.product;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/10 下午3:05
 * @Package com.zack.shop.mvp.http.entity.product
 **/


public class Product implements Parcelable{


    /**
     * result : success
     * msg : 登录成功
     * endtime : 2021/4/28 19:17:09
     * con : [{"id":"29690","acc":"刺激","appid":"100732"},{"id":"29730","acc":"好记星","appid":"100729"},{"id":"29731","acc":"好好学","appid":"100730"},{"id":"29732","acc ":"高昂","appid":"100731"},{"id":"29733","acc":"撒打算","appid":"100733"}]
     */

    private String result;
    private String msg;
    private String endtime;
    private List<ConBean> con;

    protected Product(Parcel in) {
        result = in.readString();
        msg = in.readString();
        endtime = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public List<ConBean> getCon() {
        return con;
    }

    public void setCon(List<ConBean> con) {
        this.con = con;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(result);
        parcel.writeString(msg);
        parcel.writeString(endtime);
    }

    public static class ConBean {
        /**
         * id : 29690
         * acc : 刺激
         * appid : 100732
         * acc  : 高昂
         */

        private String id;
        private String acc;
        private String appid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAcc() {
            return acc;
        }

        public void setAcc(String acc) {
            this.acc = acc;
        }
    }
}
