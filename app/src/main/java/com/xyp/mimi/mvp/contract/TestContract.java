package com.xyp.mimi.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.NetData;

import io.reactivex.Observable;

//任务
public interface TestContract {


    interface View extends IView {

        void getTestSuccess(NetData netData);
    }

    //获取数据
    interface Model extends IModel {
        Observable<NetData> getTest();
    }
}
