package com.xyp.mimi.mvp.contract.order;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailPost;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailResult;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/03/2020 10:32
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public interface OrderDetailContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getOrderDetailResult(OrderDetailResult orderDetailResult);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<OrderDetailResult> getOrderDetail(OrderDetailPost orderDetailPost);
    }
}
