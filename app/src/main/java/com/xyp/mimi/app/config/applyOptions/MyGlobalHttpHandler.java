package com.xyp.mimi.app.config.applyOptions;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.http.log.RequestInterceptor;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.mvp.event.ResponseErrorEvent;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class MyGlobalHttpHandler  implements GlobalHttpHandler {
    @NonNull
    @Override
    public Response onHttpResultResponse(@Nullable String httpResult, @NonNull Interceptor.Chain chain, @NonNull Response response) {
        // 统一处理http响应。eg:状态码不是200时，根据状态码做相应的处理。

        if (!TextUtils.isEmpty(httpResult) && RequestInterceptor.isJson(response.body().contentType())) {
            try {
                JSONObject object = JSONObject.parseObject(httpResult);
                Timber.w("Result ------> "+ object.toString());

                int code = object.getInteger("code");
                String msg = object.getString("msg");
                if(code != 0){
                    EventBus.getDefault().post(new ResponseErrorEvent());
                    ArmsUtils.snackbarText(msg);
                    return response;
                }else{

                }

            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }

        return response;
    }

    @NonNull
    @Override
    public Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request) {
        // 统一处理http请求。eg:给request统一添加token或者header以及参数加密等操作
        return request;
    }
}
