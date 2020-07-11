package com.xyp.mimi.mvp.http.entity;

import java.util.List;

public class TaskList {

    /**
     * result : success
     * msg : 成功
     * con : [{"cnamme":"金融类"},{"cnamme":"培训类"},{"cnamme":"加盟类"},{"cnamme":"游戏类"},{"cnamme":"农业类"},{"cnamme":"家电类"},{"cnamme":"消费类"},{"cnamme":"旅游类"},{"cnamme":"数码类"},{"cnamme":"饮料类"}]
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
         * cnamme : 金融类
         */

        private String cnamme;

        public String getCnamme() {
            return cnamme;
        }

        public void setCnamme(String cnamme) {
            this.cnamme = cnamme;
        }
    }
}
