package com.xyp.mimi.mvp.contract.address;



import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.address.AddAddressPost;
import com.xyp.mimi.mvp.http.entity.address.AddressDeletePost;
import com.xyp.mimi.mvp.http.entity.address.AddressListResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.address.AddressListPost;

import io.reactivex.Observable;

public interface AddressContract {

    interface  View extends IView{

        void  addressListResult(AddressListResult s);

        void  deleteResult(BaseResponse baseResponse);
    }

    interface  EditView extends IView{

        void addAddressResult(BaseResponse baseResponse);

        void updateAddressResult(BaseResponse baseResponse);
    }


    interface  Model extends IModel{
        Observable<AddressListResult> getAddressList(AddressListPost addressListPost);

        Observable<BaseResponse> addAddress(AddAddressPost addAddressPost);

        Observable<BaseResponse> updateAddress(AddAddressPost addAddressPost);

        Observable<BaseResponse> deleteAddress(AddressDeletePost addressDeletePost);
    }
}
