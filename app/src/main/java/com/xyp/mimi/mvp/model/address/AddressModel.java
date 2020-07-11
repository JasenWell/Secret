package com.xyp.mimi.mvp.model.address;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.address.AddressContract;
import com.xyp.mimi.mvp.http.api.service.address.AddressService;
import com.xyp.mimi.mvp.http.entity.address.AddAddressPost;
import com.xyp.mimi.mvp.http.entity.address.AddressDeletePost;
import com.xyp.mimi.mvp.http.entity.address.AddressListResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.address.AddressListPost;

import io.reactivex.Observable;

public class AddressModel extends BaseModel implements AddressContract.Model {

    public AddressModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<AddressListResult> getAddressList(AddressListPost addressListPost) {
        return mRepositoryManager.obtainRetrofitService(AddressService.class)
                .getAddressList(addressListPost);
    }

    @Override
    public Observable<BaseResponse> addAddress(AddAddressPost addAddressPost) {
        return mRepositoryManager.obtainRetrofitService(AddressService.class)
                .addAddress(addAddressPost);
    }

    @Override
    public Observable<BaseResponse> updateAddress(AddAddressPost addAddressPost) {
        return mRepositoryManager.obtainRetrofitService(AddressService.class)
               .editAddress(addAddressPost);
    }

    @Override
    public Observable<BaseResponse> deleteAddress(AddressDeletePost addressDeletePost) {
        return mRepositoryManager.obtainRetrofitService(AddressService.class)
                .deleteAddress(addressDeletePost);
    }

}
