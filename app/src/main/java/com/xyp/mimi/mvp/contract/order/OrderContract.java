package com.xyp.mimi.mvp.contract.order;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.order.CancelOrderPost;
import com.xyp.mimi.mvp.http.entity.order.OrderListPost;
import com.xyp.mimi.mvp.http.entity.order.OrderListResult;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/18/2020 15:27
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public interface OrderContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getOrderListResult(OrderListResult orderListResult);

        void getCancelOrderResult(BaseResponse baseResponse);

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<OrderListResult> getOrderList(OrderListPost orderListPost);

        Observable<BaseResponse> cancelOrder(CancelOrderPost cancelOrderPost);

    }
}
