package com.xyp.mimi.mvp.presenter.cart;

import android.app.Application;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import javax.inject.Inject;
import com.xyp.mimi.mvp.contract.cart.CartContract;
import com.xyp.mimi.mvp.http.entity.cart.CartListPost;
import com.xyp.mimi.mvp.http.entity.cart.CartListResult;
import com.xyp.mimi.mvp.utils.RxUtils;

@FragmentScope
public class CartPresenter extends BasePresenter<CartContract.Model, CartContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public CartPresenter(CartContract.Model model, CartContract.View rootView) {
        super(model, rootView);
    }

    public void getCartListResult(CartListPost cartListPost){
        mModel.getCartListResult(cartListPost)
                .compose(RxUtils.applySchedulersNoloading(mRootView))
                .subscribe(new ErrorHandleSubscriber<CartListResult>(mErrorHandler) {
                    @Override
                    public void onNext(CartListResult cartListResult) {
                        if(cartListResult.getCode() == 0){
                            mRootView.getCartListResult(cartListResult);
                        }else{
                            mRootView.showMessage(cartListResult.getMsg());
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }


}
