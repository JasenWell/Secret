package com.xyp.mimi.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.product.Product;

import io.reactivex.Observable;

public interface BindContract {

    //刷新banner还有list
    interface View extends IView {

        void bindSuccess(String s);

        void getBindRecommendSuccess(Product data);

        void deleteSuccess(String s);
    }

    //获取数据
    interface Model extends IModel {
        Observable<BaseResponse<Object>> bindUse( String useId,String acc,String impwd);

        Observable<Product> getBindRecommend(String useId);

        Observable<Product> delete(String useId,String netId);

    }

}
