package com.xyp.mimi.mvp.utils;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/5/31 上午11:25
 * @Package com.zack.shop.mvp.utils
 **/

public interface AppConstant {


    interface Api {
        String TOKEN = "token";
        String  TYPE = "type";
    }

    interface User {
        String USER_ID = "UserId";
        String TOKEN = "Token";
        String AVATAR = "Avatar";//头像
        String NICK_NAME = "NickName";//昵称
        String REFERRAL_CODE = "ReferralCode";//邀请码
        String ORDER_INDEX = "OrderIndex";//订单下标
        String PHONE = "Phone";//
    }

    interface ActivityIntent {
        String BEAN = "BEAN";
        String USER_BEAN = "user_bean";
        String PRODUCT_BEAN = "product_bean";
        String PRODUCT_IDS = "PRODUCT_IDS";

        String ORDER_STATUS = "ORDER_STATUS";
        String SEARCH_CONTENT = "SEARCH_CONTENT";
        String IMAGE_URL = "IMAGE_URL";
    }

    interface Image {
        String FILE_PROVIDER = "com.zack.shop.FileProvider";

    }

    String FIRST_OPEN = "FIRST_OPEN";
}
