package com.xyp.mimi.mvp.contract.wallet;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailPost;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailResult;

import io.reactivex.Observable;

//
public interface MoneyDetailContract {

    interface View extends IView {
        void getMoneyDetail(MoneyDetailResult moneyDetailResult);
    }

    //获取数据
    interface Model extends IModel {
        Observable<MoneyDetailResult> getMoneyDetail(MoneyDetailPost moneyDetailPost);

    }
}
