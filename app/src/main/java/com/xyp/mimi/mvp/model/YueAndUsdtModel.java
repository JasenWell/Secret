package com.xyp.mimi.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.YueAndUsdtContract;
import com.xyp.mimi.mvp.http.api.service.ChongzhiService;
import com.xyp.mimi.mvp.http.entity.chongzhi.Chongzhi;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;


public class YueAndUsdtModel extends BaseModel implements YueAndUsdtContract.Model {
    public YueAndUsdtModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<Object>> yue(String useid, String usename, String lname, String lpwd) {
        return mRepositoryManager.obtainRetrofitService(ChongzhiService.class)
                .chongzhi(useid,usename,lname,lpwd);
    }

    @Override
    public Observable<BaseResponse<Object>> guaYue(String useid, String sl, String email, String useurl, String upwd) {
        return  mRepositoryManager.obtainRetrofitService(ChongzhiService.class)
                .guaYue( useid,  sl,  email,  useurl,  upwd);
    }

    @Override
    public Observable<BaseResponse<Object>> usdt(String useid, String sl, String email, String usdt, String upwd) {
        return mRepositoryManager.obtainRetrofitService(ChongzhiService.class)
                .chongzhiusdt(useid,sl,email,usdt,upwd);
    }

    @Override
    public Observable<BaseResponse<Object>> guaUSDT(String useid, String sl, String email, String usdt, String upwd) {
        return mRepositoryManager.obtainRetrofitService(ChongzhiService.class)
                .guaUSDT( useid,  sl,  email,  usdt,  upwd);
    }

    @Override
    public Observable<Chongzhi> getChongzhi(String useid) {
        return mRepositoryManager.obtainRetrofitService(ChongzhiService.class)
                .getChongzhi(useid);
    }
}
