package com.xyp.mimi.mvp.contract.circle;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.circle.CircleListResult;
import com.xyp.mimi.mvp.http.entity.circle.CirclePost;
import io.reactivex.Observable;

public interface CircleContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface CircleListView extends IView {

        void  getCircleListResult(CircleListResult s);

        void  getDeleteCircleResult(BaseResponse s);

    }

    interface PushCircleView extends  IView{

        void  pushCircleResult(BaseResponse baseResponse);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<CircleListResult> getCircleList(String uid);

        Observable<BaseResponse> pushCircle(String uid,String context);

        Observable<BaseResponse> deleteCircle(String id);
    }
}
