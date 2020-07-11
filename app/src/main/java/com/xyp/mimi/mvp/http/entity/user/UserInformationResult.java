package com.xyp.mimi.mvp.http.entity.user;

public class UserInformationResult {
    /**
     * code : 0
     * data : {"Amount":0,"Avatar":"http://shop.dadanyipin.com/upload/Avatar/2020051915442451925006.jpg","Birthday":"1900-01-01","FrozenAmount":0,"Id":10659,"Inviter":"*** ","IsBind":1,"IsPlus":false,"IsSetPayPwd":0,"Mobile":"18683573857","NickName":"bmras583","PM":{"Avatar":"","Mobile":"***","NickName":"","UserRName":""},"PlusEndTime":"1900-01-01T00:00:00","ReferralCode":"883844","Score":0,"Sex":0,"SumAmount":0,"UserRName":"","Wallet":0}
     * msg : 获取成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
         * Amount : 0.0
         * Avatar : http://shop.dadanyipin.com/upload/Avatar/2020051915442451925006.jpg
         * Birthday : 1900-01-01
         * FrozenAmount : 0.0
         * Id : 10659
         * Inviter : ***
         * IsBind : 1
         * IsPlus : false
         * IsSetPayPwd : 0
         * Mobile : 18683573857
         * NickName : bmras583
         * PM : {"Avatar":"","Mobile":"***","NickName":"","UserRName":""}
         * PlusEndTime : 1900-01-01T00:00:00
         * ReferralCode : 883844
         * Score : 0.0
         * Sex : 0
         * SumAmount : 0.0
         * UserRName :
         * Wallet : 0.0
         */

        private double Amount;
        private String Avatar;
        private String Birthday;
        private double FrozenAmount;
        private int Id;
        private String Inviter;
        private int IsBind;
        private boolean IsPlus;
        private int IsSetPayPwd;
        private String Mobile;
        private String NickName;
        private PMBean PM;
        private String PlusEndTime;
        private String ReferralCode;
        private double Score;
        private int Sex;
        private double SumAmount;
        private String UserRName;
        private double Wallet;

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
        }

        public double getFrozenAmount() {
            return FrozenAmount;
        }

        public void setFrozenAmount(double FrozenAmount) {
            this.FrozenAmount = FrozenAmount;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getInviter() {
            return Inviter;
        }

        public void setInviter(String Inviter) {
            this.Inviter = Inviter;
        }

        public int getIsBind() {
            return IsBind;
        }

        public void setIsBind(int IsBind) {
            this.IsBind = IsBind;
        }

        public boolean isIsPlus() {
            return IsPlus;
        }

        public void setIsPlus(boolean IsPlus) {
            this.IsPlus = IsPlus;
        }

        public int getIsSetPayPwd() {
            return IsSetPayPwd;
        }

        public void setIsSetPayPwd(int IsSetPayPwd) {
            this.IsSetPayPwd = IsSetPayPwd;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public PMBean getPM() {
            return PM;
        }

        public void setPM(PMBean PM) {
            this.PM = PM;
        }

        public String getPlusEndTime() {
            return PlusEndTime;
        }

        public void setPlusEndTime(String PlusEndTime) {
            this.PlusEndTime = PlusEndTime;
        }

        public String getReferralCode() {
            return ReferralCode;
        }

        public void setReferralCode(String ReferralCode) {
            this.ReferralCode = ReferralCode;
        }

        public double getScore() {
            return Score;
        }

        public void setScore(double Score) {
            this.Score = Score;
        }

        public int getSex() {
            return Sex;
        }

        public void setSex(int sex) {
            Sex = sex;
        }

        public double getSumAmount() {
            return SumAmount;
        }

        public void setSumAmount(double SumAmount) {
            this.SumAmount = SumAmount;
        }

        public String getUserRName() {
            return UserRName;
        }

        public void setUserRName(String UserRName) {
            this.UserRName = UserRName;
        }

        public double getWallet() {
            return Wallet;
        }

        public void setWallet(double Wallet) {
            this.Wallet = Wallet;
        }

        public static class PMBean {
            /**
             * Avatar :
             * Mobile : ***
             * NickName :
             * UserRName :
             */

            private String Avatar;
            private String Mobile;
            private String NickName;
            private String UserRName;

            public String getAvatar() {
                return Avatar;
            }

            public void setAvatar(String Avatar) {
                this.Avatar = Avatar;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String NickName) {
                this.NickName = NickName;
            }

            public String getUserRName() {
                return UserRName;
            }

            public void setUserRName(String UserRName) {
                this.UserRName = UserRName;
            }
        }
    }
}
