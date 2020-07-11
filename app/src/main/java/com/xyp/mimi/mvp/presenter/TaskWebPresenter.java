package com.xyp.mimi.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.TaskWebContract;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.product.Product;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class TaskWebPresenter extends BasePresenter<TaskWebContract.Model, TaskWebContract.View> {
    @Inject
    RxErrorHandler rxErrorHandler;

    @Inject
    public TaskWebPresenter(TaskWebContract.Model model, TaskWebContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void getTask(String useid){
        mModel.getTask(useid)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<TaskData>(rxErrorHandler) {
                    @Override
                    public void onNext(TaskData taskData) {
                        if(taskData.getResult().equals("success")){
                            mRootView.getTaskSuccess(taskData);
                        }else{
                            mRootView.showMessage(taskData.getMsg());
                        }
                    }
                })
        ;
    }
    public void getRecommend(String useid){
        mModel.getRecommend(useid)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<Product>(rxErrorHandler) {
                    @Override
                    public void onNext(Product product) {
                        if (product.getResult().equals("success")&&!product.getMsg().equals("没有记录")) {
                            mRootView.getRecommendList(product);
                        } else if(product.getResult().equals("success")&&product.getMsg().equals("没有记录")){
                            mRootView.showMessage(product.getMsg());
                        } else{
                            mRootView.showMessage(product.getMsg());
                        }
                    }
                });
    }

}