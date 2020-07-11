package com.xyp.mimi.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.TaskData;
import com.xyp.mimi.mvp.http.entity.product.Product;

import io.reactivex.Observable;

//任务
public interface TaskWebContract {


    //刷新banner还有list
    interface View extends IView {

        void getTaskSuccess(TaskData taskData);
        void getRecommendList(Product data);
    }

    //获取数据
    interface Model extends IModel {
        Observable<TaskData> getTask(String useid);
        Observable<Product> getRecommend(String useId);
    }
}
