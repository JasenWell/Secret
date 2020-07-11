package com.xyp.mimi.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.TaskWebContract;
import com.xyp.mimi.mvp.http.api.service.TaskService;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.product.Product;

import io.reactivex.Observable;


public class TaskWebModel extends BaseModel implements TaskWebContract.Model {
    public TaskWebModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<TaskData> getTask(String useid) {
        return  mRepositoryManager.obtainRetrofitService(TaskService.class)
                .getTask(useid);
    }

    @Override
    public Observable<Product> getRecommend(String useId) {
        return mRepositoryManager.obtainRetrofitService(TaskService.class)
                .getRecommendedProducts(useId);
    }
}
