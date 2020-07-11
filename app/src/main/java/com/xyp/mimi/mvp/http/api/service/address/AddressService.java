package com.xyp.mimi.mvp.http.api.service.address;

import com.xyp.mimi.mvp.http.entity.address.AddressDeletePost;
import com.xyp.mimi.mvp.http.entity.address.AddressListResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.address.AddAddressPost;
import com.xyp.mimi.mvp.http.entity.address.AddressListPost;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddressService {

    //地址列表
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("/api/Address/AddressList")
    Observable<AddressListResult> getAddressList(
            @Body AddressListPost addressListPost
    );



    //新增地址
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("/api/Address/AddAddress")
    Observable<BaseResponse> addAddress(
            @Body AddAddressPost addAddressPost
    );



    //修改地址
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("/api/Address/UpdateAddress")
    Observable<BaseResponse>  editAddress(
            @Body AddAddressPost addAddressPost
    );


    //删除地址
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Address/DeleteAddress")
    Observable<BaseResponse>  deleteAddress(
            @Body AddressDeletePost addressDeletePost
    );


    //设置默认地址
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("/api/Address/SetDefaultaddress")
    Observable<BaseResponse>  setDefaultaddress(
            @Body AddAddressPost addAddressPost
    );


//    //获取省市区街道列表
//    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
//    @POST("/api/Area/GetArea")
//    Observable<BaseResponse>  setDefaultaddress(
//            @Body AddAddressPost addAddressPost
//    );







}
