package com.xyp.mimi.mvp.contract.cart;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.xyp.mimi.mvp.http.entity.cart.CartListPost;
import com.xyp.mimi.mvp.http.entity.cart.CartListResult;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/02/2020 09:49
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public interface CartContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getCartListResult(CartListResult cartListResult);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<CartListResult> getCartListResult(CartListPost cartListPost);
    }
}
