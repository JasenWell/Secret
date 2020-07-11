package com.xyp.mimi.mvp.presenter.address;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.address.AddressContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.address.AddressDeletePost;
import com.xyp.mimi.mvp.http.entity.address.AddressListResult;
import com.xyp.mimi.mvp.http.entity.address.AddressListPost;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class AddressPresenter extends BasePresenter<AddressContract.Model,AddressContract.View> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public AddressPresenter(AddressContract.Model model, AddressContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void getAddressList(AddressListPost addressListPost){
        mModel.getAddressList(addressListPost)
                .compose(RxUtils.applySchedulersNoloading(mRootView))
                .subscribe(new ErrorHandleSubscriber<AddressListResult>(rxErrorHandler) {
                    @Override
                    public void onNext(AddressListResult addressListResult) {
                        if(addressListResult.getCode() == 0){
                            mRootView.addressListResult(addressListResult);
                        }else{
                            mRootView.showMessage(addressListResult.getMsg());
                        }
                    }
                })
        ;
    }

    //删除
    public void deleteAddress(AddressDeletePost addressDeletePost){
        mModel.deleteAddress(addressDeletePost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode() == 0 ){
                            mRootView.deleteResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                })
        ;
    }

}
