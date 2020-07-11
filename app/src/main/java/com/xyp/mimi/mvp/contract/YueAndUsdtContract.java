package com.xyp.mimi.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.chongzhi.Chongzhi;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;

public interface YueAndUsdtContract {

    //刷新banner还有list
    interface ViewYue extends IView {
        void yueSuccess(String s);//余额充值
        void guaYueSuccess(String s);//余额挂靠
        void getYueSuccess(Chongzhi c);
    }

    interface ViewUsdt extends IView {
        void usdtSuccess(String s);//usdt充值
        void guaUSDTSuccess(String s);//usdt挂靠

    }


    //获取数据
    interface Model extends IModel {
        Observable<BaseResponse<Object>> yue(String useid,
                                                  String usename,
                                                  String lname,
                                                  String lpwd);



        Observable<BaseResponse<Object>> guaYue(   String useid,
                                                   String sl,
                                                   String email,
                                                   String useurl,
                                                   String upwd);

        Observable<BaseResponse<Object>> usdt(  String useid,
                                                        String sl,
                                                        String email,
                                                        String usdt,
                                                        String upwd);




        Observable<BaseResponse<Object>> guaUSDT( String useid,
                                                  String sl,
                                                  String email,
                                                  String usdt,
                                                  String upwd);


        Observable<Chongzhi> getChongzhi(String useid);
    }

}
