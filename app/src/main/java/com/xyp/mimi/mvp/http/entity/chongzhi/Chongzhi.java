package com.xyp.mimi.mvp.http.entity.chongzhi;

import java.util.List;

public class Chongzhi {


    /**
     * result : success
     * msg : 成功
     * con : [{"order_code":"Y504829186200","moneys":"240","res":"已完成"},{"order_code":"Y850417870282","moneys":"240","res":"已完成"},{"order_code":"Y591799706220","moneys":"240","res":"已完成"}]
     */

    private String result;
    private String msg;
    private List<ConBean> con;

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

    public List<ConBean> getCon() {
        return con;
    }

    public void setCon(List<ConBean> con) {
        this.con = con;
    }

    public static class ConBean {
        /**
         * order_code : Y504829186200
         * moneys : 240
         * res : 已完成
         */

        private String order_code;
        private String moneys;
        private String res;

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getMoneys() {
            return moneys;
        }

        public void setMoneys(String moneys) {
            this.moneys = moneys;
        }

        public String getRes() {
            return res;
        }

        public void setRes(String res) {
            this.res = res;
        }
    }
}
