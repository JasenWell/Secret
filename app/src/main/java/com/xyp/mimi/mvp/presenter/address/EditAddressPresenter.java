package com.xyp.mimi.mvp.presenter.address;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.address.AddressContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.address.AddAddressPost;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
@ActivityScope
public class EditAddressPresenter extends BasePresenter<AddressContract.Model,AddressContract.EditView> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public EditAddressPresenter(AddressContract.Model model, AddressContract.EditView rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void addAddress(AddAddressPost addAddressPost){
        mModel.addAddress(addAddressPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse addressListResult) {
                        if(addressListResult.getCode() == 0 ){
                            mRootView.addAddressResult(addressListResult);
                        }else{
                            mRootView.showMessage(addressListResult.getMsg());
                        }
                    }
                })
        ;
    }

    public void updateAddress(AddAddressPost addAddressPost){
        mModel.updateAddress(addAddressPost)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.getCode() == 0 ){
                            mRootView.updateAddressResult(baseResponse);
                        }else{
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                })
        ;
    }

}
