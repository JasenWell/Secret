package com.xyp.mimi.mvp.http.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskData implements Parcelable {

    /**
     * result : success
     * msg : 成功
     * sl : 5
     * kw : %E4%BA%B2%E8%87%AA%E9%98%85%E8%AF%BB
     * ks : .35
     * ids : ||100733||100729||100732||100731
     * u : http://www.txjmw.com.cn/zjxm/89338.html
     * appid : 100733
     */

    private String result;
    private String msg;
    private String sl;
    private String kw;
    private String ks;
    private String ids;
    private String u;
    private String appid;

    protected TaskData(Parcel in) {
        result = in.readString();
        msg = in.readString();
        sl = in.readString();
        kw = in.readString();
        ks = in.readString();
        ids = in.readString();
        u = in.readString();
        appid = in.readString();
    }

    public static final Creator<TaskData> CREATOR = new Creator<TaskData>() {
        @Override
        public TaskData createFromParcel(Parcel in) {
            return new TaskData(in);
        }

        @Override
        public TaskData[] newArray(int size) {
            return new TaskData[size];
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

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
        dest.writeString(msg);
        dest.writeString(sl);
        dest.writeString(kw);
        dest.writeString(ks);
        dest.writeString(ids);
        dest.writeString(u);
        dest.writeString(appid);
    }
}
