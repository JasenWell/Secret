package com.xyp.mimi.mvp.http.entity.wallet;

import java.util.List;

public class BankInfoListResult {


    /**
     * code : 0
     * data : [{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211519061415.png","BankName":"中国银行","Id":1},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211519441327.png","BankName":"中国建设银行","Id":2},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211520013603.png","BankName":"中国工商银行","Id":3},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211520593005.png","BankName":"中国邮政储蓄银行","Id":4},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211520485981.png","BankName":"中国农业银行","Id":5},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211521169466.png","BankName":"浦发银行","Id":6},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211521395128.png","BankName":"交通银行","Id":7},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211521551423.png","BankName":"招商银行","Id":8},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211522134464.png","BankName":"华夏银行","Id":9},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211522429534.png","BankName":"平安银行","Id":10},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211523003368.png","BankName":"中信银行","Id":11},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211523369681.png","BankName":"兴业银行","Id":12},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211523582094.png","BankName":"民生银行","Id":13},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211524141885.png","BankName":"光大银行","Id":14},{"BankLogo":"http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211524314752.png","BankName":"广发银行","Id":15}]
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
         * BankLogo : http://shop.dadanyipin.com/upload/admin/2018-11-21/201811211519061415.png
         * BankName : 中国银行
         * Id : 1
         */

        private String BankLogo;
        private String BankName;
        private int Id;

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
