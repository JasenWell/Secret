package com.xyp.mimi.mvp.http.entity.address;

public class AddAddressPost {

    private  String UserId;
    private  String Token;
    private  int Id;
    private  String Consignee;
    private  String Mobile;
    private  int IsDefault;
    private  String ProvinceCode;
    private  String ProvinceName;
    private  String CityCode;
    private  String CityName;
    private  String DistrictCode;
    private  String DistrictName;
    private  String StreetCode;
    private  String StreetName;
    private  String FullAddress;
    private  String PostCode;

    public AddAddressPost(String userId, String token, int id, String consignee, String mobile, int isDefault, String provinceCode, String provinceName, String cityCode, String cityName, String districtCode, String districtName, String fullAddress) {
        UserId = userId;
        Token = token;
        Id = id;
        Consignee = consignee;
        Mobile = mobile;
        IsDefault = isDefault;
        ProvinceCode = provinceCode;
        ProvinceName = provinceName;
        CityCode = cityCode;
        CityName = cityName;
        DistrictCode = districtCode;
        DistrictName = districtName;
        FullAddress = fullAddress;
    }

    public AddAddressPost(String userId, String token, String consignee, String mobile, int isDefault, String provinceCode, String provinceName, String cityCode, String cityName, String districtCode, String districtName, String fullAddress) {
        UserId = userId;
        Token = token;
        Consignee = consignee;
        Mobile = mobile;
        IsDefault = isDefault;
        ProvinceCode = provinceCode;
        ProvinceName = provinceName;
        CityCode = cityCode;
        CityName = cityName;
        DistrictCode = districtCode;
        DistrictName = districtName;
        FullAddress = fullAddress;
    }
}
