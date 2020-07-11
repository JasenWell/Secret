package com.xyp.mimi.mvp.http.entity.order;

import java.util.List;

public class OrderDetailResult {

    /**
     * code : 0
     * count : 0
     * data : {"Addr":"四川省 成都市 金牛区  222","AuditStatus":0,"ContactName":"3","DiscountedAmount":"0","ExpressPrice":"0","GroupRecordId":0,"IsApplyInvoice":0,"IsCancel":0,"IsComment":0,"IsConfirmReceipt":0,"IsDel":1,"IsNeedAudit":0,"IsRefund":0,"Ispay":0,"OrderDetails":[{"ActualPay":349,"ConsumeCode":"","Id":4054,"IsComment":0,"IsRefund":0,"Number":1,"OrderStatusName":"交易关闭","PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2e-shop-2020-04-23/2020042318562491434.jpg","ProductId":482,"ProductName":"勇艺达F2（盼-萌）现金补贴50元/个","RefundId":0,"SaveMoney":0,"SpecText":"","UnitPrice":349}],"OrderId":1018,"OrderNumber":"202006011410040378147","OrderTime":"2020-06-01 14:10:04","Payment":"","Paytime":"--","PlatDisPrice":"0","RefundId":0,"Remarks":"","ShopId":"2AD269E437320FA3","ShopName":"勇艺达机器人","StatusName":"交易关闭","Tel":"23","Total":"349","TotalAmount":"349","YhPrice":"0","ZkPrice":"0"}
     * msg : 获取成功
     */

    private int code;
    private int count;
    private DataBean data;
    private String msg;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * Addr : 四川省 成都市 金牛区  222
         * AuditStatus : 0
         * ContactName : 3
         * DiscountedAmount : 0
         * ExpressPrice : 0
         * GroupRecordId : 0
         * IsApplyInvoice : 0
         * IsCancel : 0
         * IsComment : 0
         * IsConfirmReceipt : 0
         * IsDel : 1
         * IsNeedAudit : 0
         * IsRefund : 0
         * Ispay : 0
         * OrderDetails : [{"ActualPay":349,"ConsumeCode":"","Id":4054,"IsComment":0,"IsRefund":0,"Number":1,"OrderStatusName":"交易关闭","PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2e-shop-2020-04-23/2020042318562491434.jpg","ProductId":482,"ProductName":"勇艺达F2（盼-萌）现金补贴50元/个","RefundId":0,"SaveMoney":0,"SpecText":"","UnitPrice":349}]
         * OrderId : 1018
         * OrderNumber : 202006011410040378147
         * OrderTime : 2020-06-01 14:10:04
         * Payment :
         * Paytime : --
         * PlatDisPrice : 0
         * RefundId : 0
         * Remarks :
         * ShopId : 2AD269E437320FA3
         * ShopName : 勇艺达机器人
         * StatusName : 交易关闭
         * Tel : 23
         * Total : 349
         * TotalAmount : 349
         * YhPrice : 0
         * ZkPrice : 0
         */

        private String Addr;
        private int AuditStatus;
        private String ContactName;
        private String DiscountedAmount;
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
        private String Paytime;
        private String PlatDisPrice;
        private int RefundId;
        private String Remarks;
        private String ShopId;
        private String ShopName;
        private String StatusName;
        private String Tel;
        private String Total;
        private String TotalAmount;
        private String YhPrice;
        private String ZkPrice;
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

        public String getDiscountedAmount() {
            return DiscountedAmount;
        }

        public void setDiscountedAmount(String DiscountedAmount) {
            this.DiscountedAmount = DiscountedAmount;
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

        public String getPaytime() {
            return Paytime;
        }

        public void setPaytime(String Paytime) {
            this.Paytime = Paytime;
        }

        public String getPlatDisPrice() {
            return PlatDisPrice;
        }

        public void setPlatDisPrice(String PlatDisPrice) {
            this.PlatDisPrice = PlatDisPrice;
        }

        public int getRefundId() {
            return RefundId;
        }

        public void setRefundId(int RefundId) {
            this.RefundId = RefundId;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
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

        public String getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(String TotalAmount) {
            this.TotalAmount = TotalAmount;
        }

        public String getYhPrice() {
            return YhPrice;
        }

        public void setYhPrice(String YhPrice) {
            this.YhPrice = YhPrice;
        }

        public String getZkPrice() {
            return ZkPrice;
        }

        public void setZkPrice(String ZkPrice) {
            this.ZkPrice = ZkPrice;
        }

        public List<OrderDetailsBean> getOrderDetails() {
            return OrderDetails;
        }

        public void setOrderDetails(List<OrderDetailsBean> OrderDetails) {
            this.OrderDetails = OrderDetails;
        }

        public static class OrderDetailsBean {
            /**
             * ActualPay : 349.0
             * ConsumeCode :
             * Id : 4054
             * IsComment : 0
             * IsRefund : 0
             * Number : 1
             * OrderStatusName : 交易关闭
             * PicNo : http://shop.dadanyipin.com/upload/admin/shopL2e-shop-2020-04-23/2020042318562491434.jpg
             * ProductId : 482
             * ProductName : 勇艺达F2（盼-萌）现金补贴50元/个
             * RefundId : 0
             * SaveMoney : 0.0
             * SpecText :
             * UnitPrice : 349.0
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
