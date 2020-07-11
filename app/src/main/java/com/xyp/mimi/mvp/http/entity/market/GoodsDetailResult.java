package com.xyp.mimi.mvp.http.entity.market;

import java.util.List;

public class GoodsDetailResult {
    /**
     * code : 0
     * msg : 获取成功
     * data : {"ShopData":{"ShopId":"700C95D9A1768AFC","ShopNick":"优视家旗舰店","MapReservation":"","Logo":"","Address":"成都","ServiceScore":100,"Phone":"028-65222200","BusinessHours":"早9点-晚9点","Lat":"30.61163","Lng":"104.08397","TransactionNumber":11,"Distance":"10526.94km"},"PicData":[{"PicUrl":"http://shop.dadanyipin.com/upload/admin/shopL2S-shop-2020-06-17/2020061718202256266.jpg"},{"PicUrl":"http://shop.dadanyipin.com/upload/admin/shopL2S-shop-2020-06-17/2020061718594450600.png"}],"Id":588,"BrandId":31,"TypeId":61,"ClassId":0,"Name":"端午大放\u201c棕\u201d礼包一","Price":"39","VipPrice":"38.61","MarketPrice":"59","SalesVolume":6,"KeywordName":"邀请5位新用户即可0元购","Synopsis":"产品一：折叠晴雨两用伞（加粗8骨UV黑胶版）；\r\n产品二：端午嘉兴粽子礼袋（内含3只120g香粽，蛋黄粽或板栗鲜肉粽随机发货）","ContentDetail":"<p><b>老用户邀请5位新用户注册，即可获得29元\u201c0元购\u201d优惠券。","Score":0,"MonSales":0,"SpecificationValue":"{\"版本\":[{\"name\":\"香粽\"},{\"name\":\"雨伞\"}]}","IsSku":1,"Sku":[{"Id":628,"ProId":588,"SpecName":"版本","SpecText":"香粽","SpecValue":"{\"版本\":\"香粽\"}","Price":"29","VipPrice":"28.71","ProStock":999,"SpecImage":"","PlusPrice":"0"},{"Id":629,"ProId":588,"SpecName":"版本","SpecText":"雨伞","SpecValue":"{\"版本\":\"雨伞\"}","Price":"29","VipPrice":"28.71","ProStock":993,"SpecImage":"","PlusPrice":"0"}],"IsCollection":{"Text":"是否已有收藏Id, Value>0:是","Value":0},"Freight":0,"FreightInfo":{"ChargeMode":0,"FreePrice":0,"IsFree":0},"IsPinkage":0,"MinBuyNum":1,"MaxBuyNum":0,"Stock":1992,"ParameterJson":"{}","ServiceInfo":[{"Name":"推荐返利","Info":""},{"Name":"未成交全额退款","Info":""},{"Name":"品牌授权","Info":""},{"Name":"破损补寄","Info":"商家支持开具发票"},{"Name":"及时发货","Info":"商家举行优惠客户大活动，凡在店消费或在小程序上点餐都送优惠券"}],"TimePrice":"0","IsFlashSale":0,"FlashSaleStartTime":"2020-06-17 00:00:00","FlashSaleEndTime":"2020-06-17 18:13:24","DiscountNum":6.610169491525424,"StockProportion":99.69969969969969,"IsPlusPrice":0,"PlusPrice":"0","Unit":"","Lng":"0","Lat":"0","Address":"","AreaSite":"","Distance":"","DistributionIncome":"0","ServiceKeys":"","GroupId":0,"IsAloneBuy":0,"IsSalesOffice":0,"ProType":{"Name":"家居","Intro":""}}
     * count : 0
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
         * ShopData : {"ShopId":"700C95D9A1768AFC","ShopNick":"优视家旗舰店","MapReservation":"","Logo":"","Address":"成都","ServiceScore":100,"Phone":"028-65222200","BusinessHours":"早9点-晚9点","Lat":"30.61163","Lng":"104.08397","TransactionNumber":11,"Distance":"10526.94km"}
         * PicData : [{"PicUrl":"http://shop.dadanyipin.com/upload/admin/shopL2S-shop-2020-06-17/2020061718202256266.jpg"},{"PicUrl":"http://shop.dadanyipin.com/upload/admin/shopL2S-shop-2020-06-17/2020061718594450600.png"}]
         * Id : 588
         * BrandId : 31
         * TypeId : 61
         * ClassId : 0
         * Name : 端午大放“棕”礼包一
         * Price : 39
         * VipPrice : 38.61
         * MarketPrice : 59
         * SalesVolume : 6
         * KeywordName : 邀请5位新用户即可0元购
         * Synopsis : 产品一：折叠晴雨两用伞（加粗8骨UV黑胶版）；
         产品二：端午嘉兴粽子礼袋（内含3只120g香粽，蛋黄粽或板栗鲜肉粽随机发货）
         * ContentDetail : <p><b>老用户邀请5位新用户注册，即可获得29元“0元购”优惠券。
         * Score : 0
         * MonSales : 0
         * SpecificationValue : {"版本":[{"name":"香粽"},{"name":"雨伞"}]}
         * IsSku : 1
         * Sku : [{"Id":628,"ProId":588,"SpecName":"版本","SpecText":"香粽","SpecValue":"{\"版本\":\"香粽\"}","Price":"29","VipPrice":"28.71","ProStock":999,"SpecImage":"","PlusPrice":"0"},{"Id":629,"ProId":588,"SpecName":"版本","SpecText":"雨伞","SpecValue":"{\"版本\":\"雨伞\"}","Price":"29","VipPrice":"28.71","ProStock":993,"SpecImage":"","PlusPrice":"0"}]
         * IsCollection : {"Text":"是否已有收藏Id, Value>0:是","Value":0}
         * Freight : 0
         * FreightInfo : {"ChargeMode":0,"FreePrice":0,"IsFree":0}
         * IsPinkage : 0
         * MinBuyNum : 1
         * MaxBuyNum : 0
         * Stock : 1992
         * ParameterJson : {}
         * ServiceInfo : [{"Name":"推荐返利","Info":""},{"Name":"未成交全额退款","Info":""},{"Name":"品牌授权","Info":""},{"Name":"破损补寄","Info":"商家支持开具发票"},{"Name":"及时发货","Info":"商家举行优惠客户大活动，凡在店消费或在小程序上点餐都送优惠券"}]
         * TimePrice : 0
         * IsFlashSale : 0
         * FlashSaleStartTime : 2020-06-17 00:00:00
         * FlashSaleEndTime : 2020-06-17 18:13:24
         * DiscountNum : 6.610169491525424
         * StockProportion : 99.69969969969969
         * IsPlusPrice : 0
         * PlusPrice : 0
         * Unit :
         * Lng : 0
         * Lat : 0
         * Address :
         * AreaSite :
         * Distance :
         * DistributionIncome : 0
         * ServiceKeys :
         * GroupId : 0
         * IsAloneBuy : 0
         * IsSalesOffice : 0
         * ProType : {"Name":"家居","Intro":""}
         */

        private ShopDataBean ShopData;
        private int Id;
        private int BrandId;
        private int TypeId;
        private int ClassId;
        private String Name;
        private String Price;
        private String VipPrice;
        private String MarketPrice;
        private int SalesVolume;
        private String KeywordName;
        private String Synopsis;
        private String ContentDetail;
        private int Score;
        private int MonSales;
        private String SpecificationValue;
        private int IsSku;
        private IsCollectionBean IsCollection;
        private int Freight;
        private FreightInfoBean FreightInfo;
        private int IsPinkage;
        private int MinBuyNum;
        private int MaxBuyNum;
        private int Stock;
        private String ParameterJson;
        private String TimePrice;
        private int IsFlashSale;
        private String FlashSaleStartTime;
        private String FlashSaleEndTime;
        private double DiscountNum;
        private double StockProportion;
        private int IsPlusPrice;
        private String PlusPrice;
        private String Unit;
        private double Lng;
        private double Lat;
        private String Address;
        private String AreaSite;
        private String Distance;
        private String DistributionIncome;
        private String ServiceKeys;
        private int GroupId;
        private int IsAloneBuy;
        private int IsSalesOffice;
        private ProTypeBean ProType;
        private List<PicDataBean> PicData;
        private List<SkuBean> Sku;
        private List<ServiceInfoBean> ServiceInfo;

        public ShopDataBean getShopData() {
            return ShopData;
        }

        public void setShopData(ShopDataBean ShopData) {
            this.ShopData = ShopData;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getBrandId() {
            return BrandId;
        }

        public void setBrandId(int BrandId) {
            this.BrandId = BrandId;
        }

        public int getTypeId() {
            return TypeId;
        }

        public void setTypeId(int TypeId) {
            this.TypeId = TypeId;
        }

        public int getClassId() {
            return ClassId;
        }

        public void setClassId(int ClassId) {
            this.ClassId = ClassId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getVipPrice() {
            return VipPrice;
        }

        public void setVipPrice(String VipPrice) {
            this.VipPrice = VipPrice;
        }

        public String getMarketPrice() {
            return MarketPrice;
        }

        public void setMarketPrice(String MarketPrice) {
            this.MarketPrice = MarketPrice;
        }

        public int getSalesVolume() {
            return SalesVolume;
        }

        public void setSalesVolume(int SalesVolume) {
            this.SalesVolume = SalesVolume;
        }

        public String getKeywordName() {
            return KeywordName;
        }

        public void setKeywordName(String KeywordName) {
            this.KeywordName = KeywordName;
        }

        public String getSynopsis() {
            return Synopsis;
        }

        public void setSynopsis(String Synopsis) {
            this.Synopsis = Synopsis;
        }

        public String getContentDetail() {
            return ContentDetail;
        }

        public void setContentDetail(String ContentDetail) {
            this.ContentDetail = ContentDetail;
        }

        public int getScore() {
            return Score;
        }

        public void setScore(int Score) {
            this.Score = Score;
        }

        public int getMonSales() {
            return MonSales;
        }

        public void setMonSales(int MonSales) {
            this.MonSales = MonSales;
        }

        public String getSpecificationValue() {
            return SpecificationValue;
        }

        public void setSpecificationValue(String SpecificationValue) {
            this.SpecificationValue = SpecificationValue;
        }

        public int getIsSku() {
            return IsSku;
        }

        public void setIsSku(int IsSku) {
            this.IsSku = IsSku;
        }

        public IsCollectionBean getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(IsCollectionBean IsCollection) {
            this.IsCollection = IsCollection;
        }

        public int getFreight() {
            return Freight;
        }

        public void setFreight(int Freight) {
            this.Freight = Freight;
        }

        public FreightInfoBean getFreightInfo() {
            return FreightInfo;
        }

        public void setFreightInfo(FreightInfoBean FreightInfo) {
            this.FreightInfo = FreightInfo;
        }

        public int getIsPinkage() {
            return IsPinkage;
        }

        public void setIsPinkage(int IsPinkage) {
            this.IsPinkage = IsPinkage;
        }

        public int getMinBuyNum() {
            return MinBuyNum;
        }

        public void setMinBuyNum(int MinBuyNum) {
            this.MinBuyNum = MinBuyNum;
        }

        public int getMaxBuyNum() {
            return MaxBuyNum;
        }

        public void setMaxBuyNum(int MaxBuyNum) {
            this.MaxBuyNum = MaxBuyNum;
        }

        public int getStock() {
            return Stock;
        }

        public void setStock(int Stock) {
            this.Stock = Stock;
        }

        public String getParameterJson() {
            return ParameterJson;
        }

        public void setParameterJson(String ParameterJson) {
            this.ParameterJson = ParameterJson;
        }

        public String getTimePrice() {
            return TimePrice;
        }

        public void setTimePrice(String TimePrice) {
            this.TimePrice = TimePrice;
        }

        public int getIsFlashSale() {
            return IsFlashSale;
        }

        public void setIsFlashSale(int IsFlashSale) {
            this.IsFlashSale = IsFlashSale;
        }

        public String getFlashSaleStartTime() {
            return FlashSaleStartTime;
        }

        public void setFlashSaleStartTime(String FlashSaleStartTime) {
            this.FlashSaleStartTime = FlashSaleStartTime;
        }

        public String getFlashSaleEndTime() {
            return FlashSaleEndTime;
        }

        public void setFlashSaleEndTime(String FlashSaleEndTime) {
            this.FlashSaleEndTime = FlashSaleEndTime;
        }

        public double getDiscountNum() {
            return DiscountNum;
        }

        public void setDiscountNum(double DiscountNum) {
            this.DiscountNum = DiscountNum;
        }

        public double getStockProportion() {
            return StockProportion;
        }

        public void setStockProportion(double StockProportion) {
            this.StockProportion = StockProportion;
        }

        public int getIsPlusPrice() {
            return IsPlusPrice;
        }

        public void setIsPlusPrice(int IsPlusPrice) {
            this.IsPlusPrice = IsPlusPrice;
        }

        public String getPlusPrice() {
            return PlusPrice;
        }

        public void setPlusPrice(String PlusPrice) {
            this.PlusPrice = PlusPrice;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String Unit) {
            this.Unit = Unit;
        }

        public double getLng() {
            return Lng;
        }

        public void setLng(double Lng) {
            this.Lng = Lng;
        }

        public double getLat() {
            return Lat;
        }

        public void setLat(double Lat) {
            this.Lat = Lat;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getAreaSite() {
            return AreaSite;
        }

        public void setAreaSite(String AreaSite) {
            this.AreaSite = AreaSite;
        }

        public String getDistance() {
            return Distance;
        }

        public void setDistance(String Distance) {
            this.Distance = Distance;
        }

        public String getDistributionIncome() {
            return DistributionIncome;
        }

        public void setDistributionIncome(String DistributionIncome) {
            this.DistributionIncome = DistributionIncome;
        }

        public String getServiceKeys() {
            return ServiceKeys;
        }

        public void setServiceKeys(String ServiceKeys) {
            this.ServiceKeys = ServiceKeys;
        }

        public int getGroupId() {
            return GroupId;
        }

        public void setGroupId(int GroupId) {
            this.GroupId = GroupId;
        }

        public int getIsAloneBuy() {
            return IsAloneBuy;
        }

        public void setIsAloneBuy(int IsAloneBuy) {
            this.IsAloneBuy = IsAloneBuy;
        }

        public int getIsSalesOffice() {
            return IsSalesOffice;
        }

        public void setIsSalesOffice(int IsSalesOffice) {
            this.IsSalesOffice = IsSalesOffice;
        }

        public ProTypeBean getProType() {
            return ProType;
        }

        public void setProType(ProTypeBean ProType) {
            this.ProType = ProType;
        }

        public List<PicDataBean> getPicData() {
            return PicData;
        }

        public void setPicData(List<PicDataBean> PicData) {
            this.PicData = PicData;
        }

        public List<SkuBean> getSku() {
            return Sku;
        }

        public void setSku(List<SkuBean> Sku) {
            this.Sku = Sku;
        }

        public List<ServiceInfoBean> getServiceInfo() {
            return ServiceInfo;
        }

        public void setServiceInfo(List<ServiceInfoBean> ServiceInfo) {
            this.ServiceInfo = ServiceInfo;
        }

        public static class ShopDataBean {
            /**
             * ShopId : 700C95D9A1768AFC
             * ShopNick : 优视家旗舰店
             * MapReservation :
             * Logo :
             * Address : 成都
             * ServiceScore : 100
             * Phone : 028-65222200
             * BusinessHours : 早9点-晚9点
             * Lat : 30.61163
             * Lng : 104.08397
             * TransactionNumber : 11
             * Distance : 10526.94km
             */

            private String ShopId;
            private String ShopNick;
            private String MapReservation;
            private String Logo;
            private String Address;
            private int ServiceScore;
            private String Phone;
            private String BusinessHours;
            private String Lat;
            private String Lng;
            private int TransactionNumber;
            private String Distance;

            public String getShopId() {
                return ShopId;
            }

            public void setShopId(String ShopId) {
                this.ShopId = ShopId;
            }

            public String getShopNick() {
                return ShopNick;
            }

            public void setShopNick(String ShopNick) {
                this.ShopNick = ShopNick;
            }

            public String getMapReservation() {
                return MapReservation;
            }

            public void setMapReservation(String MapReservation) {
                this.MapReservation = MapReservation;
            }

            public String getLogo() {
                return Logo;
            }

            public void setLogo(String Logo) {
                this.Logo = Logo;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public int getServiceScore() {
                return ServiceScore;
            }

            public void setServiceScore(int ServiceScore) {
                this.ServiceScore = ServiceScore;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public String getBusinessHours() {
                return BusinessHours;
            }

            public void setBusinessHours(String BusinessHours) {
                this.BusinessHours = BusinessHours;
            }

            public String getLat() {
                return Lat;
            }

            public void setLat(String Lat) {
                this.Lat = Lat;
            }

            public String getLng() {
                return Lng;
            }

            public void setLng(String Lng) {
                this.Lng = Lng;
            }

            public int getTransactionNumber() {
                return TransactionNumber;
            }

            public void setTransactionNumber(int TransactionNumber) {
                this.TransactionNumber = TransactionNumber;
            }

            public String getDistance() {
                return Distance;
            }

            public void setDistance(String Distance) {
                this.Distance = Distance;
            }
        }

        public static class IsCollectionBean {
            /**
             * Text : 是否已有收藏Id, Value>0:是
             * Value : 0
             */

            private String Text;
            private int Value;

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }

            public int getValue() {
                return Value;
            }

            public void setValue(int Value) {
                this.Value = Value;
            }
        }

        public static class FreightInfoBean {
            /**
             * ChargeMode : 0
             * FreePrice : 0
             * IsFree : 0
             */

            private int ChargeMode;
            private int FreePrice;
            private int IsFree;

            public int getChargeMode() {
                return ChargeMode;
            }

            public void setChargeMode(int ChargeMode) {
                this.ChargeMode = ChargeMode;
            }

            public int getFreePrice() {
                return FreePrice;
            }

            public void setFreePrice(int FreePrice) {
                this.FreePrice = FreePrice;
            }

            public int getIsFree() {
                return IsFree;
            }

            public void setIsFree(int IsFree) {
                this.IsFree = IsFree;
            }
        }

        public static class ProTypeBean {
            /**
             * Name : 家居
             * Intro :
             */

            private String Name;
            private String Intro;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getIntro() {
                return Intro;
            }

            public void setIntro(String Intro) {
                this.Intro = Intro;
            }
        }

        public static class PicDataBean {
            /**
             * PicUrl : http://shop.dadanyipin.com/upload/admin/shopL2S-shop-2020-06-17/2020061718202256266.jpg
             */

            private String PicUrl;

            public String getPicUrl() {
                return PicUrl;
            }

            public void setPicUrl(String PicUrl) {
                this.PicUrl = PicUrl;
            }
        }

        public static class SkuBean {
            /**
             * Id : 628
             * ProId : 588
             * SpecName : 版本
             * SpecText : 香粽
             * SpecValue : {"版本":"香粽"}
             * Price : 29
             * VipPrice : 28.71
             * ProStock : 999
             * SpecImage :
             * PlusPrice : 0
             */

            private int Id;
            private int ProId;
            private String SpecName;
            private String SpecText;
            private String SpecValue;
            private String Price;
            private String VipPrice;
            private int ProStock;
            private String SpecImage;
            private String PlusPrice;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getProId() {
                return ProId;
            }

            public void setProId(int ProId) {
                this.ProId = ProId;
            }

            public String getSpecName() {
                return SpecName;
            }

            public void setSpecName(String SpecName) {
                this.SpecName = SpecName;
            }

            public String getSpecText() {
                return SpecText;
            }

            public void setSpecText(String SpecText) {
                this.SpecText = SpecText;
            }

            public String getSpecValue() {
                return SpecValue;
            }

            public void setSpecValue(String SpecValue) {
                this.SpecValue = SpecValue;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String Price) {
                this.Price = Price;
            }

            public String getVipPrice() {
                return VipPrice;
            }

            public void setVipPrice(String VipPrice) {
                this.VipPrice = VipPrice;
            }

            public int getProStock() {
                return ProStock;
            }

            public void setProStock(int ProStock) {
                this.ProStock = ProStock;
            }

            public String getSpecImage() {
                return SpecImage;
            }

            public void setSpecImage(String SpecImage) {
                this.SpecImage = SpecImage;
            }

            public String getPlusPrice() {
                return PlusPrice;
            }

            public void setPlusPrice(String PlusPrice) {
                this.PlusPrice = PlusPrice;
            }
        }

        public static class ServiceInfoBean {
            /**
             * Name : 推荐返利
             * Info :
             */

            private String Name;
            private String Info;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getInfo() {
                return Info;
            }

            public void setInfo(String Info) {
                this.Info = Info;
            }
        }
    }
}
