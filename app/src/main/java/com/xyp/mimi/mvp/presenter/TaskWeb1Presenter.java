package com.xyp.mimi.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.TaskWebContract;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@FragmentScope
public class TaskWeb1Presenter extends BasePresenter<TaskWebContract.Model, TaskWebContract.View> {
    @Inject
    RxErrorHandler rxErrorHandler;

    @Inject
    public TaskWeb1Presenter(TaskWebContract.Model model, TaskWebContract.View rootView, RxErrorHandler rxErrorHandler) {
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




}