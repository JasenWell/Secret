package com.xyp.mimi.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import io.reactivex.Observable;


public interface PasswordContract {

    //刷新banner还有list
    interface View extends IView {
        void changePasswordSuccess(String s);
    }

    //获取数据
    interface Model extends IModel {
        Observable<BaseResponse<Object>> changePassword(String useid, String opwd, String npwd);
    }

}
