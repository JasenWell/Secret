package com.xyp.mimi.im.net.hjh.imp;

import okhttp3.Callback;

/**封装请求对象参数
 * Created by hjh on 2018/1/30.
 */

public abstract class OkHttpCallBackImp<T> implements Callback {
    private T param;//T为对象
    public OkHttpCallBackImp(T param){
        setParam(param);
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
