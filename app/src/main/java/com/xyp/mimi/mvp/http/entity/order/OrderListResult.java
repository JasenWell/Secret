package com.xyp.mimi.mvp.http.entity.order;

import java.util.List;

public class OrderListResult {
    /**
     * code : 0
     * msg : 获取成功
     * count : 5
     * data : [{"Addr":"四川省 成都市 成华区  丽景苑","AuditStatus":0,"ContactName":"王","ExpressPrice":"0","GroupRecordId":0,"IsApplyInvoice":1,"IsCancel":0,"IsComment":1,"IsConfirmReceipt":0,"IsDel":0,"IsNeedAudit":0,"IsRefund":0,"Ispay":0,"OrderDetails":[{"ActualPay":4.5,"ConsumeCode":"","Id":3712,"IsComment":1,"IsRefund":0,"Number":5,"OrderStatusName":"已完成","PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-04-30/2020043020420349348.jpg","ProductId":506,"ProductName":"五一特惠 小龙坎 菌汤火锅底料100g","RefundId":0,"SaveMoney":0,"SpecText":"","UnitPrice":4.5}],"OrderId":718,"OrderNumber":"202005012109154602082","OrderTime":"2020-05-01 21:09:15","Payment":"微信支付","RefundId":0,"ShopId":"88B626087EF7B04D","ShopName":"大单易拼商城","StatusName":"已完成","Tel":"18683573857","Total":"4.5"},{"Addr":"四川省 成都市 成华区  丽景苑","AuditStatus":0,"ContactName":"王","ExpressPrice":"0","GroupRecordId":0,"IsApplyInvoice":1,"IsCancel":0,"IsComment":1,"IsConfirmReceipt":0,"IsDel":0,"IsNeedAudit":0,"IsRefund":0,"Ispay":0,"OrderDetails":[{"ActualPay":4.5,"ConsumeCode":"","Id":3604,"IsComment":1,"IsRefund":0,"Number":5,"OrderStatusName":"已完成","PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-04-30/2020043020200524735.jpg","ProductId":505,"ProductName":"传味方砖 小龙坎醇香牛油火锅底料，限时特惠","RefundId":0,"SaveMoney":0,"SpecText":"","UnitPrice":4.5}],"OrderId":618,"OrderNumber":"202005012100146770571","OrderTime":"2020-05-01 21:00:14","Payment":"微信支付","RefundId":0,"ShopId":"88B626087EF7B04D","ShopName":"大单易拼商城","StatusName":"已完成","Tel":"18683573857","Total":"4.5"},{"Addr":"四川省 成都市 成华区  丽景苑","AuditStatus":0,"ContactName":"王","ExpressPrice":"0","GroupRecordId":0,"IsApplyInvoice":0,"IsCancel":0,"IsComment":0,"IsConfirmReceipt":0,"IsDel":1,"IsNeedAudit":0,"IsRefund":0,"Ispay":0,"OrderDetails":[{"ActualPay":29.5,"ConsumeCode":"","Id":3583,"IsComment":0,"IsRefund":0,"Number":5,"OrderStatusName":"交易关闭","PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-04-30/2020043020200524735.jpg","ProductId":505,"ProductName":"传味方砖 小龙坎醇香牛油火锅底料，限时特惠","RefundId":0,"SaveMoney":0,"SpecText":"","UnitPrice":29.5}],"OrderId":597,"OrderNumber":"202005010926158767575","OrderTime":"2020-05-01 09:26:15","Payment":"微信支付","RefundId":0,"ShopId":"88B626087EF7B04D","ShopName":"大单易拼商城","StatusName":"交易关闭","Tel":"18683573857","Total":"29.5"},{"Addr":"","AuditStatus":0,"ContactName":"向","ExpressPrice":"0","GroupRecordId":0,"IsApplyInvoice":0,"IsCancel":0,"IsComment":0,"IsConfirmReceipt":0,"IsDel":1,"IsNeedAudit":1,"IsRefund":0,"Ispay":0,"OrderDetails":[{"ActualPay":599,"ConsumeCode":"2R5QDM","Id":3569,"IsComment":0,"IsRefund":0,"Number":1,"OrderStatusName":"交易关闭","PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2S-shop-2020-04-22/2020042220351605692.jpg","ProductId":492,"ProductName":"锦绣·滨江华府新房补贴特权 仅限前30名","RefundId":0,"SaveMoney":0,"SpecText":"","UnitPrice":599}],"OrderId":583,"OrderNumber":"202004301835514710487","OrderTime":"2020-04-30 18:35:51","Payment":"支付宝支付","RefundId":0,"ShopId":"700C95D9A1768AFC","ShopName":"优视家旗舰店","StatusName":"交易关闭","Tel":"18683573857","Total":"599"}]
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

    public static class DataBean {
        /**
         * Addr : 四川省 成都市 成华区  丽景苑
         * AuditStatus : 0
         * ContactName : 王
         * ExpressPrice : 0
         * GroupRecordId : 0
         * IsApplyInvoice : 1
         * IsCancel : 0
         * IsComment : 1
         * IsConfirmReceipt : 0
         * IsDel : 0
         * IsNeedAudit : 0
         * IsRefund : 0
         * Ispay : 0
         * OrderDetails : [{"ActualPay":4.5,"ConsumeCode":"","Id":3712,"IsComment":1,"IsRefund":0,"Number":5,"OrderStatusName":"已完成","PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-04-30/2020043020420349348.jpg","ProductId":506,"ProductName":"五一特惠 小龙坎 菌汤火锅底料100g","RefundId":0,"SaveMoney":0,"SpecText":"","UnitPrice":4.5}]
         * OrderId : 718
         * OrderNumber : 202005012109154602082
         * OrderTime : 2020-05-01 21:09:15
         * Payment : 微信支付
         * RefundId : 0
         * ShopId : 88B626087EF7B04D
         * ShopName : 大单易拼商城
         * StatusName : 已完成
         * Tel : 18683573857
         * Total : 4.5
         */

        private String Addr;
        private int AuditStatus;
        private String ContactName;
        private String ExpressPrice;
        private int GroupRecordId;
        private int IsApplyInvoice;
        private int IsCancel;
        private int IsComment;
        private int IsConfirmReceipt;
        private int IsDel;
        private int IsNeedAudit;
        private int IsRefund;
        private int Ispay;
        private int OrderId;
        private String OrderNumber;
        private String OrderTime;
        private String Payment;
        private int RefundId;
        private String ShopId;
        private String ShopName;
        private String StatusName;
        private String Tel;
        private String Total;
        private List<OrderDetailsBean> OrderDetails;

        public String getAddr() {
            return Addr;
        }

        public void setAddr(String Addr) {
            this.Addr = Addr;
        }

        public int getAuditStatus() {
            return AuditStatus;
        }

        public void setAuditStatus(int AuditStatus) {
            this.AuditStatus = AuditStatus;
        }

        public String getContactName() {
            return ContactName;
        }

        public void setContactName(String ContactName) {
            this.ContactName = ContactName;
        }

        public String getExpressPrice() {
            return ExpressPrice;
        }

        public void setExpressPrice(String ExpressPrice) {
            this.ExpressPrice = ExpressPrice;
        }

        public int getGroupRecordId() {
            return GroupRecordId;
        }

        public void setGroupRecordId(int GroupRecordId) {
            this.GroupRecordId = GroupRecordId;
        }

        public int getIsApplyInvoice() {
            return IsApplyInvoice;
        }

        public void setIsApplyInvoice(int IsApplyInvoice) {
            this.IsApplyInvoice = IsApplyInvoice;
        }

        public int getIsCancel() {
            return IsCancel;
        }

        public void setIsCancel(int IsCancel) {
            this.IsCancel = IsCancel;
        }

        public int getIsComment() {
            return IsComment;
        }

        public void setIsComment(int IsComment) {
            this.IsComment = IsComment;
        }

        public int getIsConfirmReceipt() {
            return IsConfirmReceipt;
        }

        public void setIsConfirmReceipt(int IsConfirmReceipt) {
            this.IsConfirmReceipt = IsConfirmReceipt;
        }

        public int getIsDel() {
            return IsDel;
        }

        public void setIsDel(int IsDel) {
            this.IsDel = IsDel;
        }

        public int getIsNeedAudit() {
            return IsNeedAudit;
        }

        public void setIsNeedAudit(int IsNeedAudit) {
            this.IsNeedAudit = IsNeedAudit;
        }

        public int getIsRefund() {
            return IsRefund;
        }

        public void setIsRefund(int IsRefund) {
            this.IsRefund = IsRefund;
        }

        public int getIspay() {
            return Ispay;
        }

        public void setIspay(int Ispay) {
            this.Ispay = Ispay;
        }

        public int getOrderId() {
            return OrderId;
        }

        public void setOrderId(int OrderId) {
            this.OrderId = OrderId;
        }

        public String getOrderNumber() {
            return OrderNumber;
        }

        public void setOrderNumber(String OrderNumber) {
            this.OrderNumber = OrderNumber;
        }

        public String getOrderTime() {
            return OrderTime;
        }

        public void setOrderTime(String OrderTime) {
            this.OrderTime = OrderTime;
        }

        public String getPayment() {
            return Payment;
        }

        public void setPayment(String Payment) {
            this.Payment = Payment;
        }

        public int getRefundId() {
            return RefundId;
        }

        public void setRefundId(int RefundId) {
            this.RefundId = RefundId;
        }

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String ShopId) {
            this.ShopId = ShopId;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public String getStatusName() {
            return StatusName;
        }

        public void setStatusName(String StatusName) {
            this.StatusName = StatusName;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getTotal() {
            return Total;
        }

        public void setTotal(String Total) {
            this.Total = Total;
        }

        public List<OrderDetailsBean> getOrderDetails() {
            return OrderDetails;
        }

        public void setOrderDetails(List<OrderDetailsBean> OrderDetails) {
            this.OrderDetails = OrderDetails;
        }

        public static class OrderDetailsBean {
            /**
             * ActualPay : 4.5
             * ConsumeCode :
             * Id : 3712
             * IsComment : 1
             * IsRefund : 0
             * Number : 5
             * OrderStatusName : 已完成
             * PicNo : http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-04-30/2020043020420349348.jpg
             * ProductId : 506
             * ProductName : 五一特惠 小龙坎 菌汤火锅底料100g
             * RefundId : 0
             * SaveMoney : 0.0
             * SpecText :
             * UnitPrice : 4.5
             */

            private double ActualPay;
            private String ConsumeCode;
            private int Id;
            private int IsComment;
            private int IsRefund;
            private int Number;
            private String OrderStatusName;
            private String PicNo;
            private int ProductId;
            private String ProductName;
            private int RefundId;
            private double SaveMoney;
            private String SpecText;
            private double UnitPrice;

            public double getActualPay() {
                return ActualPay;
            }

            public void setActualPay(double ActualPay) {
                this.ActualPay = ActualPay;
            }

            public String getConsumeCode() {
                return ConsumeCode;
            }

            public void setConsumeCode(String ConsumeCode) {
                this.ConsumeCode = ConsumeCode;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getIsComment() {
                return IsComment;
            }

            public void setIsComment(int IsComment) {
                this.IsComment = IsComment;
            }

            public int getIsRefund() {
                return IsRefund;
            }

            public void setIsRefund(int IsRefund) {
                this.IsRefund = IsRefund;
            }

            public int getNumber() {
                return Number;
            }

            public void setNumber(int Number) {
                this.Number = Number;
            }

            public String getOrderStatusName() {
                return OrderStatusName;
            }

            public void setOrderStatusName(String OrderStatusName) {
                this.OrderStatusName = OrderStatusName;
            }

            public String getPicNo() {
                return PicNo;
            }

            public void setPicNo(String PicNo) {
                this.PicNo = PicNo;
            }

            public int getProductId() {
                return ProductId;
            }

            public void setProductId(int ProductId) {
                this.ProductId = ProductId;
            }

            public String getProductName() {
                return ProductName;
            }

            public void setProductName(String ProductName) {
                this.ProductName = ProductName;
            }

            public int getRefundId() {
                return RefundId;
            }

            public void setRefundId(int RefundId) {
                this.RefundId = RefundId;
            }

            public double getSaveMoney() {
                return SaveMoney;
            }

            public void setSaveMoney(double SaveMoney) {
                this.SaveMoney = SaveMoney;
            }

            public String getSpecText() {
                return SpecText;
            }

            public void setSpecText(String SpecText) {
                this.SpecText = SpecText;
            }

            public double getUnitPrice() {
                return UnitPrice;
            }

            public void setUnitPrice(double UnitPrice) {
                this.UnitPrice = UnitPrice;
            }
        }
    }
}
