package com.xyp.mimi.mvp.model.market;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.market.GoodsDetailActivityContract;
import com.xyp.mimi.mvp.http.api.service.evaluation.EvalutionService;
import com.xyp.mimi.mvp.http.api.service.market.MarketService;
import com.xyp.mimi.mvp.http.entity.market.EvaluationListPost;
import com.xyp.mimi.mvp.http.entity.market.EvalutionListResult;
import com.xyp.mimi.mvp.http.entity.market.GoodsDetailPost;
import com.xyp.mimi.mvp.http.entity.market.GoodsDetailResult;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/18/2020 17:35
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class GoodsDetailActivityModel extends BaseModel implements GoodsDetailActivityContract.Model{
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public GoodsDetailActivityModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<GoodsDetailResult> getGoodsDetail(GoodsDetailPost goodsDetailPost) {
        return mRepositoryManager.obtainRetrofitService(MarketService.class)
                .getGoodsxq(goodsDetailPost);
    }

    @Override
    public Observable<EvalutionListResult> getEvaluation(EvaluationListPost evaluationListPost) {
        return mRepositoryManager.obtainRetrofitService(EvalutionService.class)
                .orderCommentList(evaluationListPost);
    }

}