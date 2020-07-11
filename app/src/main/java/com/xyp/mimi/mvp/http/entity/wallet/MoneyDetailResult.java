package com.xyp.mimi.mvp.http.entity.wallet;

import java.util.List;

public class MoneyDetailResult {

    /**
     * code : 0
     * msg : 获取成功
     * data : {"list":[{"Id":2884,"Title":"微信支付","Change":"-4.5","After":0,"Remark":"微信支付，付款金额:4.5,订单号:202005012109154602082","AddTime":"2020-05-01 21:09:30","Note":"流水号：202005012109154602082"},{"Id":2813,"Title":"微信支付","Change":"-4.5","After":0,"Remark":"微信支付，付款金额:4.5,订单号:202005012100146770571","AddTime":"2020-05-01 21:00:28","Note":"流水号：202005012100146770571"}],"AmountMon_hz":"-9"}
     * count : 2
     */

    private int code;
    private String msg;
    private DataBean data;
    private int count;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class DataBean {
        /**
         * list : [{"Id":2884,"Title":"微信支付","Change":"-4.5","After":0,"Remark":"微信支付，付款金额:4.5,订单号:202005012109154602082","AddTime":"2020-05-01 21:09:30","Note":"流水号：202005012109154602082"},{"Id":2813,"Title":"微信支付","Change":"-4.5","After":0,"Remark":"微信支付，付款金额:4.5,订单号:202005012100146770571","AddTime":"2020-05-01 21:00:28","Note":"流水号：202005012100146770571"}]
         * AmountMon_hz : -9
         */

        private String AmountMon_hz;
        private List<ListBean> list;

        public String getAmountMon_hz() {
            return AmountMon_hz;
        }

        public void setAmountMon_hz(String AmountMon_hz) {
            this.AmountMon_hz = AmountMon_hz;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * Id : 2884
             * Title : 微信支付
             * Change : -4.5
             * After : 0
             * Remark : 微信支付，付款金额:4.5,订单号:202005012109154602082
             * AddTime : 2020-05-01 21:09:30
             * Note : 流水号：202005012109154602082
             */

            private int Id;
            private String Title;
            private String Change;
            private int After;
            private String Remark;
            private String AddTime;
            private String Note;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getChange() {
                return Change;
            }

            public void setChange(String Change) {
                this.Change = Change;
            }

            public int getAfter() {
                return After;
            }

            public void setAfter(int After) {
                this.After = After;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public String getAddTime() {
                return AddTime;
            }

            public void setAddTime(String AddTime) {
                this.AddTime = AddTime;
            }

            public String getNote() {
                return Note;
            }

            public void setNote(String Note) {
                this.Note = Note;
            }
        }
    }
}
