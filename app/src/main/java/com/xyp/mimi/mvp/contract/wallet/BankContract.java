package com.xyp.mimi.mvp.contract.wallet;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.wallet.AddBankPost;
import com.xyp.mimi.mvp.http.entity.wallet.BankInfoListResult;
import com.xyp.mimi.mvp.http.entity.wallet.CodePost;
import com.xyp.mimi.mvp.http.entity.wallet.DeleteMyBankCardPost;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailPost;
import com.xyp.mimi.mvp.http.entity.wallet.MyBankListResult;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningPost;
import com.xyp.mimi.mvp.http.entity.wallet.MyEarningResult;
import com.xyp.mimi.mvp.http.entity.wallet.TixianPost;

import io.reactivex.Observable;

//
public interface BankContract {

    interface BankInfoListView extends IView {
        void getBankInfoListResult(BankInfoListResult bankInfoListResult);
    }

    interface AddBankView extends IView {
        void getAddBankResult(BaseResponse baseResponse);
        void getCodeResult(BaseResponse baseResponse);
    }
    interface MyBankCardView extends IView {
        void getMyBankCardResult(MyBankListResult myBankListResult);
        void getDeleteBankCardResult(BaseResponse baseResponse);
    }

    interface MyEarningView extends IView {
        void getMyEarningResult(BaseResponse<MyEarningResult> myEarningResult);
    }

    interface MyEarningAndTixianView extends IView {
        void getMyEarningResult(BaseResponse<Object> myEarningResult);
    }

    interface MyYueTixianView extends IView {
        void getMyEarningResult(BaseResponse<Object> myEarningResult);
    }




    //获取数据
    interface Model extends IModel {

        Observable<BankInfoListResult> getBankInfoList();
        Observable<BaseResponse> addBank(AddBankPost addBankPost);
        Observable<BaseResponse> getBindingBankCardsCode(CodePost codePost);
        Observable<MyBankListResult> getMyBankCard(AddBankPost addBankPost);
        Observable<BaseResponse> deleteMyBankCard(DeleteMyBankCardPost deleteMyBankCardPost);

        Observable<BaseResponse<MyEarningResult>> getMyEarning(MyEarningPost myEarningPost);

        Observable<BaseResponse<Object>> getMyEarningDetail(MoneyDetailPost moneyDetailPost);

        Observable<BaseResponse<Object>> yueTixian(TixianPost tixianPost);
    }
}
