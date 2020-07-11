package com.xyp.mimi.mvp.presenter.market;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.market.GoodsDetailActivityContract;
import com.xyp.mimi.mvp.http.entity.market.EvaluationListPost;
import com.xyp.mimi.mvp.http.entity.market.EvalutionListResult;
import com.xyp.mimi.mvp.http.entity.market.GoodsDetailPost;
import com.xyp.mimi.mvp.http.entity.market.GoodsDetailResult;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


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
public class GoodsDetailActivityPresenter extends BasePresenter<GoodsDetailActivityContract.Model, GoodsDetailActivityContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public GoodsDetailActivityPresenter (GoodsDetailActivityContract.Model model, GoodsDetailActivityContract.View rootView) {
        super(model, rootView);
    }

    //商品详情
    public void getGoodsDetail(GoodsDetailPost goodsDetailPost){
        mModel.getGoodsDetail(goodsDetailPost)
                .compose(RxUtils.applySchedulersNoloading(mRootView))
                .subscribe(new ErrorHandleSubscriber<GoodsDetailResult>(mErrorHandler) {
                    @Override
                    public void onNext(GoodsDetailResult goodsDetailResult) {
                        if(goodsDetailResult.getCode()== 0){
                            mRootView.goodsDetailResult(goodsDetailResult);
                        }else{
                            mRootView.showMessage(goodsDetailResult.getMsg());
                        }
                    }
                });
    }

    //商品评价列表
    public void getEvaluation(EvaluationListPost evaluationListPost){
        mModel.getEvaluation(evaluationListPost)
                .compose(RxUtils.applySchedulersNoloading(mRootView))
                .subscribe(new ErrorHandleSubscriber<EvalutionListResult>(mErrorHandler) {
                    @Override
                    public void onNext(EvalutionListResult evalutionListResult) {
                        if(evalutionListResult.getCode()== 0){
                            mRootView.getEvaluationResult(evalutionListResult);
                        }else{
                            mRootView.showMessage(evalutionListResult.getMsg());
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
