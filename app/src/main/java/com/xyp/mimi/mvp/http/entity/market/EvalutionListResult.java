package com.xyp.mimi.mvp.http.entity.market;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class EvalutionListResult {
    /**
     * code : 0
     * count : 5
     * data : [{"AddTime":"2020-06-07 08:55:05","Avatar":"https://wx.qlogo.cn/mmopen/vi_32/Z8DQouPCMRYibkeLKywicIODXJVhicIv0CVzdibpoL6b8vD4Px9yc2LGibZ5N9KjEwQyMImPoKevEeCqrrib5jZP1sPQ/132","BrandId":32,"ContentText":"确实是赚了钱，","NickName":"wpqqn492","PicData":[{"PicUrl":"http://shop.dadanyipin.com/upload/OrderComment/2020060708550577752633.jpg"}],"ProPicNo":"http://shop.dadanyipin.com/upload/admin/shopL2S-shop-2020-04-29/2020042917183019071.jpg","ProductId":504,"ProductName":"威兰德小镇 车位 现金补贴特权","ProductSpecText":"","Rank":5,"Reply":"","ReplyTime":"1900-01-01 00:00:00","Title":"产品【威兰德小镇 车位 现金补贴特权】评价"},{"AddTime":"2020-05-25 22:48:54","Avatar":"https://wx.qlogo.cn/mmopen/vi_32/ZwLEEpglswfZ9JMyU6rrPUtGstrGVqUnmnbA6yMiaD51SmWeYmgNNvZu3fGChlE1spbvuMPpWgcT2TJ1DajMv3Q/132","BrandId":32,"ContentText":"我是威兰德小镇小镇2栋13楼1号业主，参与大单易拼买了7号车位，真的是拼单返现，本人已真实返现到账7200元+800元，还没拼单的朋友赶紧快来，这么好的平台就该让更多的人知道。","NickName":"vbjbt940","PicData":[{"PicUrl":"http://shop.dadanyipin.com/upload/OrderComment/2020052522485439814477.jpg"}],"ProPicNo":"http://shop.dadanyipin.com/upload/admin/shopL2S-shop-2020-04-29/2020042917183019071.jpg","ProductId":504,"ProductName":"威兰德小镇 车位 现金补贴特权","ProductSpecText":"","Rank":5,"Reply":"","ReplyTime":"1900-01-01 00:00:00","Title":"产品【威兰德小镇 车位 现金补贴特权】评价"}]
     * msg : 获取成功
     */

    private int code;
    private int count;
    private String msg;
    private List<DataBean> data;

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

    public static class DataBean  implements MultiItemEntity {
        /**
         * AddTime : 2020-06-07 08:55:05
         * Avatar : https://wx.qlogo.cn/mmopen/vi_32/Z8DQouPCMRYibkeLKywicIODXJVhicIv0CVzdibpoL6b8vD4Px9yc2LGibZ5N9KjEwQyMImPoKevEeCqrrib5jZP1sPQ/132
         * BrandId : 32
         * ContentText : 确实是赚了钱，
         * NickName : wpqqn492
         * PicData : [{"PicUrl":"http://shop.dadanyipin.com/upload/OrderComment/2020060708550577752633.jpg"}]
         * ProPicNo : http://shop.dadanyipin.com/upload/admin/shopL2S-shop-2020-04-29/2020042917183019071.jpg
         * ProductId : 504
         * ProductName : 威兰德小镇 车位 现金补贴特权
         * ProductSpecText :
         * Rank : 5
         * Reply :
         * ReplyTime : 1900-01-01 00:00:00
         * Title : 产品【威兰德小镇 车位 现金补贴特权】评价
         */

        private String AddTime;
        private String Avatar;
        private int BrandId;
        private String ContentText;
        private String NickName;
        private String ProPicNo;
        private int ProductId;
        private String ProductName;
        private String ProductSpecText;
        private int Rank;
        private String Reply;
        private String ReplyTime;
        private String Title;
        private List<PicDataBean> PicData;

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public int getBrandId() {
            return BrandId;
        }

        public void setBrandId(int BrandId) {
            this.BrandId = BrandId;
        }

        public String getContentText() {
            return ContentText;
        }

        public void setContentText(String ContentText) {
            this.ContentText = ContentText;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getProPicNo() {
            return ProPicNo;
        }

        public void setProPicNo(String ProPicNo) {
            this.ProPicNo = ProPicNo;
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

        public String getProductSpecText() {
            return ProductSpecText;
        }

        public void setProductSpecText(String ProductSpecText) {
            this.ProductSpecText = ProductSpecText;
        }

        public int getRank() {
            return Rank;
        }

        public void setRank(int Rank) {
            this.Rank = Rank;
        }

        public String getReply() {
            return Reply;
        }

        public void setReply(String Reply) {
            this.Reply = Reply;
        }

        public String getReplyTime() {
            return ReplyTime;
        }

        public void setReplyTime(String ReplyTime) {
            this.ReplyTime = ReplyTime;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public List<PicDataBean> getPicData() {
            return PicData;
        }

        public void setPicData(List<PicDataBean> PicData) {
            this.PicData = PicData;
        }

        @Override
        public int getItemType() {
            return 0;
        }

        public static class PicDataBean {
            /**
             * PicUrl : http://shop.dadanyipin.com/upload/OrderComment/2020060708550577752633.jpg
             */

            private String PicUrl;

            public String getPicUrl() {
                return PicUrl;
            }

            public void setPicUrl(String PicUrl) {
                this.PicUrl = PicUrl;
            }
        }
    }
}
