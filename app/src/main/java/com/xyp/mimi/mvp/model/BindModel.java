package com.xyp.mimi.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.BindContract;
import com.xyp.mimi.mvp.http.api.service.BindService;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.product.Product;

import io.reactivex.Observable;


public class BindModel extends BaseModel implements BindContract.Model {
    public BindModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }



    @Override
    public Observable<BaseResponse<Object>> bindUse(String acc, String impwd, String useId) {
        return mRepositoryManager.obtainRetrofitService(BindService.class)
                .bindUse(acc,impwd,useId);
    }

    @Override
    public Observable<Product> getBindRecommend(String useId) {
        return mRepositoryManager.obtainRetrofitService(BindService.class)
                .getRecommendedProducts(useId);
    }

    @Override
    public Observable<Product> delete(String useId, String netId) {
        return mRepositoryManager.obtainRetrofitService(BindService.class)
                .delete(useId,netId);
    }


}
