package com.xyp.mimi.mvp.http.entity.wallet;

import java.util.List;

public class MyBankListResult {


    /**
     * code : 0
     * data : [{"BankCardName":"向应平","BankCardNo":"6226090218728067","BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211521551423.png","BankName":"招商银行","Id":23}]
     * msg : 获取成功
     */

    private int code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * BankCardName : 向应平
         * BankCardNo : 6226090218728067
         * BankLogo : http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211521551423.png
         * BankName : 招商银行
         * Id : 23
         */

        private String BankCardName;
        private String BankCardNo;
        private String BankLogo;
        private String BankName;
        private int Id;

        public String getBankCardName() {
            return BankCardName;
        }

        public void setBankCardName(String BankCardName) {
            this.BankCardName = BankCardName;
        }

        public String getBankCardNo() {
            return BankCardNo;
        }

        public void setBankCardNo(String BankCardNo) {
            this.BankCardNo = BankCardNo;
        }

        public String getBankLogo() {
            return BankLogo;
        }

        public void setBankLogo(String BankLogo) {
            this.BankLogo = BankLogo;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }
}
