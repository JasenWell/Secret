package com.xyp.mimi.app.config.applyOptions;

import android.content.Context;

import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.mvp.event.ResponseErrorEvent;

import org.greenrobot.eventbus.EventBus;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import retrofit2.HttpException;

public class MyResponseErrorListener implements ResponseErrorListener {

    @Override
    public void handleResponseError(Context context, Throwable t) {
//        Timber.tag("Catch_Error").w(t.getMessage());
        String msg = "";
        if (t instanceof UnknownHostException) {
            msg = "网络不可用";
            EventBus.getDefault().post(new ResponseErrorEvent());
            ArmsUtils.snackbarText(msg);
        } else if (t instanceof SocketTimeoutException) {
            msg = "请求网络超时";
            EventBus.getDefault().post(new ResponseErrorEvent());
            ArmsUtils.snackbarText(msg);
        } else if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            msg = convertStatusCode(httpException);
            EventBus.getDefault().post(new ResponseErrorEvent());
            ArmsUtils.snackbarText(msg);
        }
//        else if (t instanceof JsonParseException || t instanceof ParseException || t instanceof JSONException || t instanceof JsonIOException) {
//            msg = "数据解析错误";
//        }

    }


    private String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() == 500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在";
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == 307) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}
