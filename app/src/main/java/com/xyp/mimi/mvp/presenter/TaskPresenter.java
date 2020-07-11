package com.xyp.mimi.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.xyp.mimi.mvp.contract.TaskContract;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.TaskList;
import com.xyp.mimi.mvp.utils.RxUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@FragmentScope
public class TaskPresenter extends BasePresenter<TaskContract.Model, TaskContract.View> {
    @Inject
    RxErrorHandler rxErrorHandler;

    @Inject
    public TaskPresenter(TaskContract.Model model, TaskContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model,rootView);
        this.rxErrorHandler =rxErrorHandler;
    }

    public void searchTask(){
        mModel.searchTask()
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<TaskList>(rxErrorHandler) {
                    @Override
                    public void onNext(TaskList userBeanBaseResponse) {
                        if(userBeanBaseResponse.getResult().equals("success")){
                            mRootView.searchTaskSuccess(userBeanBaseResponse.getCon());
                        }else{
                            mRootView.showMessage(userBeanBaseResponse.getMsg());
                        }
                    }
                })
        ;
    }

    public void startTask(String useid, String cname){
//        mModel.startTask(useid,cname)
//                .compose(RxUtils.applySchedulers(mRootView))
//                .subscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(rxErrorHandler) {
//                    @Override
//                    public void onNext(BaseResponse<Object> userBeanBaseResponse) {
//                        if(userBeanBaseResponse.getResult().equals("success")){
//                            mRootView.startTaskSuccess();
//                        }else{
//                            mRootView.showMessage(userBeanBaseResponse.getMsg());
//                        }
//                    }
//                })
//        ;
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