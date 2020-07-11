package com.xyp.mimi.mvp.http.entity.history;

import com.xyp.mimi.mvp.http.entity.cart.CartListResult;

import java.util.List;

public class HistoryResult {

    /**
     * code : 0
     * data : {"CartData":[{"CartId":538,"ProId":602,"SpecText":"","Total":1},{"CartId":537,"ProId":604,"SpecText":"","Total":1},{"CartId":489,"ProId":498,"SpecText":"","Total":1}],"CartList":[{"AddressId":908,"AllNumber":2,"AllPrice":138,"AllScore":0,"CouponId":-1,"Freight":0,"IsInvoice":1,"ProData":[{"Id":538,"Isinvalid":0,"MarketPrice":"129","MaxBuyNum":0,"MinBuyNum":1,"Name":"注水加热双层饭盒+帆布饭盒袋","Number":1,"PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-06-30/2020063016034174819.jpg","Price":"99","ProductId":602,"Score":0,"SpecId":0,"SpecText":"","Stock":999,"VipPrice":"98.01"},{"Id":537,"Isinvalid":0,"MarketPrice":"45","MaxBuyNum":0,"MinBuyNum":1,"Name":"网红泡泡机","Number":1,"PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-07-01/2020070116422546794.jpg","Price":"39","ProductId":604,"Score":0,"SpecId":0,"SpecText":"","Stock":999,"VipPrice":"38.61"}],"ShopId":"88B626087EF7B04D","ShopName":"大单易拼商城","TotalPrice":138,"UseCouponList":[],"yhPrice":0,"zkPrice":0},{"AddressId":908,"AllNumber":1,"AllPrice":869,"AllScore":0,"CouponId":-1,"Freight":0,"IsInvoice":1,"ProData":[{"Id":489,"Isinvalid":0,"MarketPrice":"1898","MaxBuyNum":0,"MinBuyNum":1,"Name":"央视推荐 勇艺达学习机器人智能机器人小学同步课程小孩智能学习机器人多功能学习助手绘本阅读高科技儿童教育早教机","Number":1,"PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2e-shop-2020-04-23/2020042320232858719.jpg","Price":"869","ProductId":498,"Score":0,"SpecId":0,"SpecText":"","Stock":999,"VipPrice":"860.31"}],"ShopId":"2AD269E437320FA3","ShopName":"勇艺达机器人","TotalPrice":869,"UseCouponList":[],"yhPrice":0,"zkPrice":0}],"CouponId":-1,"CouponsList":[],"PayAmount":1007,"PayFreight":0,"PlatDisPrice":0,"yhAmount":0}
     * msg : 获取成功
     */

    private int code;
    private CartListResult.DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CartListResult.DataBean getData() {
        return data;
    }

    public void setData(CartListResult.DataBean data) {
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
         * CartData : [{"CartId":538,"ProId":602,"SpecText":"","Total":1},{"CartId":537,"ProId":604,"SpecText":"","Total":1},{"CartId":489,"ProId":498,"SpecText":"","Total":1}]
         * CartList : [{"AddressId":908,"AllNumber":2,"AllPrice":138,"AllScore":0,"CouponId":-1,"Freight":0,"IsInvoice":1,"ProData":[{"Id":538,"Isinvalid":0,"MarketPrice":"129","MaxBuyNum":0,"MinBuyNum":1,"Name":"注水加热双层饭盒+帆布饭盒袋","Number":1,"PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-06-30/2020063016034174819.jpg","Price":"99","ProductId":602,"Score":0,"SpecId":0,"SpecText":"","Stock":999,"VipPrice":"98.01"},{"Id":537,"Isinvalid":0,"MarketPrice":"45","MaxBuyNum":0,"MinBuyNum":1,"Name":"网红泡泡机","Number":1,"PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-07-01/2020070116422546794.jpg","Price":"39","ProductId":604,"Score":0,"SpecId":0,"SpecText":"","Stock":999,"VipPrice":"38.61"}],"ShopId":"88B626087EF7B04D","ShopName":"大单易拼商城","TotalPrice":138,"UseCouponList":[],"yhPrice":0,"zkPrice":0},{"AddressId":908,"AllNumber":1,"AllPrice":869,"AllScore":0,"CouponId":-1,"Freight":0,"IsInvoice":1,"ProData":[{"Id":489,"Isinvalid":0,"MarketPrice":"1898","MaxBuyNum":0,"MinBuyNum":1,"Name":"央视推荐 勇艺达学习机器人智能机器人小学同步课程小孩智能学习机器人多功能学习助手绘本阅读高科技儿童教育早教机","Number":1,"PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2e-shop-2020-04-23/2020042320232858719.jpg","Price":"869","ProductId":498,"Score":0,"SpecId":0,"SpecText":"","Stock":999,"VipPrice":"860.31"}],"ShopId":"2AD269E437320FA3","ShopName":"勇艺达机器人","TotalPrice":869,"UseCouponList":[],"yhPrice":0,"zkPrice":0}]
         * CouponId : -1
         * CouponsList : []
         * PayAmount : 1007.0
         * PayFreight : 0.0
         * PlatDisPrice : 0.0
         * yhAmount : 0.0
         */

        private int CouponId;
        private double PayAmount;
        private double PayFreight;
        private double PlatDisPrice;
        private double yhAmount;
        private List<CartListResult.DataBean.CartDataBean> CartData;
        private List<CartListResult.DataBean.CartListBean> CartList;
        private List<?> CouponsList;

        public int getCouponId() {
            return CouponId;
        }

        public void setCouponId(int CouponId) {
            this.CouponId = CouponId;
        }

        public double getPayAmount() {
            return PayAmount;
        }

        public void setPayAmount(double PayAmount) {
            this.PayAmount = PayAmount;
        }

        public double getPayFreight() {
            return PayFreight;
        }

        public void setPayFreight(double PayFreight) {
            this.PayFreight = PayFreight;
        }

        public double getPlatDisPrice() {
            return PlatDisPrice;
        }

        public void setPlatDisPrice(double PlatDisPrice) {
            this.PlatDisPrice = PlatDisPrice;
        }

        public double getYhAmount() {
            return yhAmount;
        }

        public void setYhAmount(double yhAmount) {
            this.yhAmount = yhAmount;
        }

        public List<CartListResult.DataBean.CartDataBean> getCartData() {
            return CartData;
        }

        public void setCartData(List<CartListResult.DataBean.CartDataBean> CartData) {
            this.CartData = CartData;
        }

        public List<CartListResult.DataBean.CartListBean> getCartList() {
            return CartList;
        }

        public void setCartList(List<CartListResult.DataBean.CartListBean> CartList) {
            this.CartList = CartList;
        }

        public List<?> getCouponsList() {
            return CouponsList;
        }

        public void setCouponsList(List<?> CouponsList) {
            this.CouponsList = CouponsList;
        }

        public static class CartDataBean {
            /**
             * CartId : 538
             * ProId : 602
             * SpecText :
             * Total : 1
             */

            private int CartId;
            private int ProId;
            private String SpecText;
            private int Total;

            public int getCartId() {
                return CartId;
            }

            public void setCartId(int CartId) {
                this.CartId = CartId;
            }

            public int getProId() {
                return ProId;
            }

            public void setProId(int ProId) {
                this.ProId = ProId;
            }

            public String getSpecText() {
                return SpecText;
            }

            public void setSpecText(String SpecText) {
                this.SpecText = SpecText;
            }

            public int getTotal() {
                return Total;
            }

            public void setTotal(int Total) {
                this.Total = Total;
            }
        }

        public static class CartListBean {
            /**
             * AddressId : 908
             * AllNumber : 2
             * AllPrice : 138.0
             * AllScore : 0
             * CouponId : -1
             * Freight : 0.0
             * IsInvoice : 1
             * ProData : [{"Id":538,"Isinvalid":0,"MarketPrice":"129","MaxBuyNum":0,"MinBuyNum":1,"Name":"注水加热双层饭盒+帆布饭盒袋","Number":1,"PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-06-30/2020063016034174819.jpg","Price":"99","ProductId":602,"Score":0,"SpecId":0,"SpecText":"","Stock":999,"VipPrice":"98.01"},{"Id":537,"Isinvalid":0,"MarketPrice":"45","MaxBuyNum":0,"MinBuyNum":1,"Name":"网红泡泡机","Number":1,"PicNo":"http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-07-01/2020070116422546794.jpg","Price":"39","ProductId":604,"Score":0,"SpecId":0,"SpecText":"","Stock":999,"VipPrice":"38.61"}]
             * ShopId : 88B626087EF7B04D
             * ShopName : 大单易拼商城
             * TotalPrice : 138.0
             * UseCouponList : []
             * yhPrice : 0.0
             * zkPrice : 0.0
             */

            private int AddressId;
            private int AllNumber;
            private double AllPrice;
            private int AllScore;
            private int CouponId;
            private double Freight;
            private int IsInvoice;
            private String ShopId;
            private String ShopName;
            private double TotalPrice;
            private double yhPrice;
            private double zkPrice;
            private List<CartListResult.DataBean.CartListBean.ProDataBean> ProData;
            private List<?> UseCouponList;

            public int getAddressId() {
                return AddressId;
            }

            public void setAddressId(int AddressId) {
                this.AddressId = AddressId;
            }

            public int getAllNumber() {
                return AllNumber;
            }

            public void setAllNumber(int AllNumber) {
                this.AllNumber = AllNumber;
            }

            public double getAllPrice() {
                return AllPrice;
            }

            public void setAllPrice(double AllPrice) {
                this.AllPrice = AllPrice;
            }

            public int getAllScore() {
                return AllScore;
            }

            public void setAllScore(int AllScore) {
                this.AllScore = AllScore;
            }

            public int getCouponId() {
                return CouponId;
            }

            public void setCouponId(int CouponId) {
                this.CouponId = CouponId;
            }

            public double getFreight() {
                return Freight;
            }

            public void setFreight(double Freight) {
                this.Freight = Freight;
            }

            public int getIsInvoice() {
                return IsInvoice;
            }

            public void setIsInvoice(int IsInvoice) {
                this.IsInvoice = IsInvoice;
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

            public double getTotalPrice() {
                return TotalPrice;
            }

            public void setTotalPrice(double TotalPrice) {
                this.TotalPrice = TotalPrice;
            }

            public double getYhPrice() {
                return yhPrice;
            }

            public void setYhPrice(double yhPrice) {
                this.yhPrice = yhPrice;
            }

            public double getZkPrice() {
                return zkPrice;
            }

            public void setZkPrice(double zkPrice) {
                this.zkPrice = zkPrice;
            }

            public List<CartListResult.DataBean.CartListBean.ProDataBean> getProData() {
                return ProData;
            }

            public void setProData(List<CartListResult.DataBean.CartListBean.ProDataBean> ProData) {
                this.ProData = ProData;
            }

            public List<?> getUseCouponList() {
                return UseCouponList;
            }

            public void setUseCouponList(List<?> UseCouponList) {
                this.UseCouponList = UseCouponList;
            }

            public static class ProDataBean {
                /**
                 * Id : 538
                 * Isinvalid : 0
                 * MarketPrice : 129
                 * MaxBuyNum : 0
                 * MinBuyNum : 1
                 * Name : 注水加热双层饭盒+帆布饭盒袋
                 * Number : 1
                 * PicNo : http://shop.dadanyipin.com/upload/admin/shopL2M-shop-2020-06-30/2020063016034174819.jpg
                 * Price : 99
                 * ProductId : 602
                 * Score : 0
                 * SpecId : 0
                 * SpecText :
                 * Stock : 999
                 * VipPrice : 98.01
                 */

                private int Id;
                private int Isinvalid;
                private String MarketPrice;
                private int MaxBuyNum;
                private int MinBuyNum;
                private String Name;
                private int Number;
                private String PicNo;
                private String Price;
                private int ProductId;
                private int Score;
                private int SpecId;
                private String SpecText;
                private int Stock;
                private String VipPrice;

                public int getId() {
                    return Id;
                }

                public void setId(int Id) {
                    this.Id = Id;
                }

                public int getIsinvalid() {
                    return Isinvalid;
                }

                public void setIsinvalid(int Isinvalid) {
                    this.Isinvalid = Isinvalid;
                }

                public String getMarketPrice() {
                    return MarketPrice;
                }

                public void setMarketPrice(String MarketPrice) {
                    this.MarketPrice = MarketPrice;
                }

                public int getMaxBuyNum() {
                    return MaxBuyNum;
                }

                public void setMaxBuyNum(int MaxBuyNum) {
                    this.MaxBuyNum = MaxBuyNum;
                }

                public int getMinBuyNum() {
                    return MinBuyNum;
                }

                public void setMinBuyNum(int MinBuyNum) {
                    this.MinBuyNum = MinBuyNum;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public int getNumber() {
                    return Number;
                }

                public void setNumber(int Number) {
                    this.Number = Number;
                }

                public String getPicNo() {
                    return PicNo;
                }

                public void setPicNo(String PicNo) {
                    this.PicNo = PicNo;
                }

                public String getPrice() {
                    return Price;
                }

                public void setPrice(String Price) {
                    this.Price = Price;
                }

                public int getProductId() {
                    return ProductId;
                }

                public void setProductId(int ProductId) {
                    this.ProductId = ProductId;
                }

                public int getScore() {
                    return Score;
                }

                public void setScore(int Score) {
                    this.Score = Score;
                }

                public int getSpecId() {
                    return SpecId;
                }

                public void setSpecId(int SpecId) {
                    this.SpecId = SpecId;
                }

                public String getSpecText() {
                    return SpecText;
                }

                public void setSpecText(String SpecText) {
                    this.SpecText = SpecText;
                }

                public int getStock() {
                    return Stock;
                }

                public void setStock(int Stock) {
                    this.Stock = Stock;
                }

                public String getVipPrice() {
                    return VipPrice;
                }

                public void setVipPrice(String VipPrice) {
                    this.VipPrice = VipPrice;
                }
            }
        }
    }

}
