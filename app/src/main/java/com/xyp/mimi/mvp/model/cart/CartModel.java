package com.xyp.mimi.mvp.model.cart;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import javax.inject.Inject;
import com.xyp.mimi.mvp.contract.cart.CartContract;
import com.xyp.mimi.mvp.http.api.service.cart.CartService;
import com.xyp.mimi.mvp.http.entity.cart.CartListPost;
import com.xyp.mimi.mvp.http.entity.cart.CartListResult;

import io.reactivex.Observable;
public class CartModel extends BaseModel implements CartContract.Model {

    @Inject
    Gson mGson;

    @Inject
    Application mApplication;

    @Inject
    public CartModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<CartListResult> getCartListResult(CartListPost cartListPost) {
        return mRepositoryManager.obtainRetrofitService(CartService.class)
                .getShopsCartList(cartListPost);
    }

}