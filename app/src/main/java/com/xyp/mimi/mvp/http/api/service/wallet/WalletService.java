package com.xyp.mimi.mvp.http.api.service.wallet;

import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.AddBankPost;
import com.xyp.mimi.mvp.http.entity.wallet.BankInfoListResult;
import com.xyp.mimi.mvp.http.entity.wallet.CodePost;
import com.xyp.mimi.mvp.http.entity.wallet.DeleteMyBankCardPost;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailPost;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailResult;
import com.xyp.mimi.mvp.http.entity.wallet.MyBankListResult;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningPost;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningResult;
import com.xyp.mimi.mvp.http.entity.wallet.TixianPost;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WalletService {

    //积分记录列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/User/ScoreList")
    Observable<BaseResponse<Object>> scoreList(
    );

    //钱包明细
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Recharge/GetRechargeList")
    Observable<MoneyDetailResult> getRechargeList(
            @Body MoneyDetailPost moneyDetailPost
    );

    //收益明细
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Recharge/IncomeDetails")
    Observable<BaseResponse<Object>> incomeDetails(
            @Body MoneyDetailPost moneyDetailPost
    );


    //我的收益统计
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Recharge/IncomeDetailsTZ")
    Observable<BaseResponse<MyEarningResult>> incomeDetailsTZ(
            @Body MyEarningPost myEarningPost

    );


    // 银行列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Bank/BankinfoList")
    Observable<BankInfoListResult> bankinfoList(
    );


    //银行卡列表
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Bank/BankList")
    Observable<MyBankListResult> bankList(
            @Body AddBankPost addBankPost
    );


    //绑定银行卡获取手机短信验证码
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Bank/BindingBankCards")
    Observable<BaseResponse> bindingBankCards(
            @Body CodePost codePost
    );

    //新增银行卡
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Bank/AddBank")
    Observable<BaseResponse> addBank(
            @Body AddBankPost addBankPost
    );

    //删除银行卡
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/Bank/DeleteBank")
    Observable<BaseResponse> deleteBank(
            @Body DeleteMyBankCardPost deleteMyBankCardPost
    );

    //提现限制条件
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/DrawMoney/LimitationOfWithdrawal")
    Observable<BaseResponse<Object>> limitationOfWithdrawal(
    );

    //佣金提现接口
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/DrawMoney/memberDrawMoneyApply")
    Observable<BaseResponse<Object>> memberDrawMoneyApply(
            @Body TixianPost tixianPost
    );

    //余额提现接口
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/api/DrawMoney/memberDrawMoneyApply123")
    Observable<BaseResponse<Object>> yueMoneyApply(
            @Body TixianPost tixianPost
    );



}
