package com.xyp.mimi.im.net.hjh.imp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.xyp.mimi.im.net.hjh.EConfig;
import com.xyp.mimi.im.net.hjh.HttpHelper;
import com.xyp.mimi.im.net.hjh.MessageEvent;
import com.xyp.mimi.im.net.hjh.NetworkUtil;
import com.xyp.mimi.im.net.hjh.OkHttpUtils;
import com.xyp.mimi.im.net.hjh.ResponseJson;
import com.xyp.mimi.im.net.hjh.callback.IAsynModel;
import com.xyp.mimi.im.net.hjh.callback.IBaseCallBack;
import com.xyp.mimi.im.net.hjh.param.Params;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class AsynModelImp implements IAsynModel {

    private IBaseCallBack iBaseCallBack;
    private OkHttpUtils okHttpUtils;
    private String errorNet;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1000){
                if(iBaseCallBack.getCallBackActivity()!= null) {
                    iBaseCallBack.getCallBackActivity().showLoadSuccess();
                }
            }else if(msg.what  == 1233){
                HttpHelper.BUSINESS business = (HttpHelper.BUSINESS) msg.obj;
                iBaseCallBack.showErrorInfo(business.getCode()+1,business.getErrorMsg());
            }else if(msg.what == 1234){
                HttpHelper.BUSINESS business = (HttpHelper.BUSINESS) msg.obj;
                iBaseCallBack.onSuccess(business.getResponseJson(), business.getCode());
            }else if(msg.what == 1235){
                MessageEvent event = (MessageEvent) msg.obj;
                iBaseCallBack.showErrorInfo(event.getType(),event.getDes());
            }
        }
    };

    public  byte[] readBytes(InputStream is){
        try {
            byte[] buffer = new byte[1024*5];
            int len = -1 ;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((len = is.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            baos.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    public  String readString(InputStream is){
        return new String(readBytes(is));
    }

    @Override
    public void addFriendRequest(HttpHelper.BUSINESS business, String mianUid, String phone) {
        if(checkNetStatus()) {
            okHttpUtils.post(Params.addFriendRequestParams(mianUid,phone), HttpHelper.PostGetUrl(business.getBusiness())).enqueue(new CallbackImp(business));
        }
    }

    @Override
    public void agreeFriendRequest(HttpHelper.BUSINESS business, String viceUid, String mianUid) {
        if(checkNetStatus()) {
            okHttpUtils.post(Params.agreeFriendRequestParams(mianUid,viceUid), HttpHelper.PostGetUrl(business.getBusiness())).enqueue(new CallbackImp(business));
        }
    }

    @Override
    public void searchFriendRequest(HttpHelper.BUSINESS business, String userId) {
        if(checkNetStatus()) {
            okHttpUtils.post(Params.searchFriendRequestParams(userId), HttpHelper.PostGetUrl(business.getBusiness())).enqueue(new CallbackImp(business));
        }
    }

    @Override
    public void startSingleChat(HttpHelper.BUSINESS business, String myId, String hisId, String content) {
        if(checkNetStatus()) {
            okHttpUtils.post(Params.startSingleChatParams(myId,hisId,content), HttpHelper.PostGetUrl(business.getBusiness())).enqueue(new CallbackImp(business));
        }
    }

    @Override
    public void searchChatRecord(HttpHelper.BUSINESS business, String myId, String hisId) {
        if(checkNetStatus()) {
            okHttpUtils.post(Params.searchChatRecordParams(myId,hisId), HttpHelper.PostGetUrl(business.getBusiness())).enqueue(new CallbackImp(business));
        }
    }

    @Override
    public void searchFriendList(HttpHelper.BUSINESS business, String userId) {
        if(checkNetStatus()) {
            okHttpUtils.post(Params.searchfriendListParams(userId), HttpHelper.PostGetUrl(business.getBusiness())).enqueue(new CallbackImp(business));
        }
    }

    private class  CallbackImp implements Callback{

        HttpHelper.BUSINESS business;

        public CallbackImp(HttpHelper.BUSINESS business){
            setBusiness(business);
        }

        public void setBusiness(HttpHelper.BUSINESS business) {
            this.business = business;
        }

        @Override
        public void onFailure(Call call,final IOException e) {
            handler.sendEmptyMessage(1000);
            Log.e(getClass().toString(),e.toString());
            Message message = new Message();
            message.what = 1233;
            business.setErrorMsg(e.toString());
            message.obj = business;
            handler.sendMessage(message);

        }
        @Override
        public void onResponse(Call call, final Response response) throws IOException {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    handler.sendEmptyMessage(1000);
                    int status = 0;
                    Message message = new Message();
                    business.setResponse(response);

                    String json = null;
                    try {
                        json = response.body().string();
                        status = 1;
                        Log.e(getClass().toString(), json);
                        if (!onCheckCode(response, business.getCode() + 1)) {
                            return;
                        }
                        ResponseJson responseJson = ResponseJson.fromJson(json, business.getClazz());
                        responseJson.setJson(json);
                        business.setResponseJson(responseJson);
                    } catch (Exception e) {
                        e.printStackTrace();
                        business.setErrorMsg(e.toString());
                        return;
                    }

                    if(status == 0){
                        message.what = 1233;
                    }else {
                        message.what = 1234;
                    }
                    message.obj = business;
                    handler.sendMessage(message);
                }
            }).start();
        }
    }

    public AsynModelImp(IBaseCallBack iBaseCallBack){
        this.iBaseCallBack = iBaseCallBack;
        okHttpUtils = OkHttpUtils.getInstance();
        errorNet = "请检查网络连接";
    }

//    @Override
//    public void postJson(HttpHelper.BUSINESS business, String json) {
//        if(!NetworkUtil.getInstance().isNetworkConnected()){
//            iBaseCallBack.showErrorInfo(EConfig.HttpResult.ERR_NET,errorNet);
//            return;
//        }
//
//        okHttpUtils.postJson(json,HttpHelper.PostGetUrl(business.getBusiness())).enqueue(new CallbackImp(business));
//    }

    private boolean checkNetStatus(){
        if(!NetworkUtil.getInstance().isNetworkConnected()){
            iBaseCallBack.showErrorInfo(EConfig.HttpResult.ERR_NET,errorNet);
            if(iBaseCallBack.getCallBackActivity()!= null) {
                iBaseCallBack.getCallBackActivity().showLoadFailed();
            }
            return false;
        }

        return true;
    }

    /*
     * 判断返回的状态码是否正确
     */
    private boolean onCheckCode(Response response,int...args){
        if(response.code() != 200){
            MessageEvent event = new MessageEvent();
            event.setType(args.length == 0 ? EConfig.RESPONSE_FAILED : args[0]);
            event.setDes(response.message());
            Message message = handler.obtainMessage();
            message.obj = event;
            message.what = 1235;
            handler.sendMessage(message);
            return false;
        }

        return true;
    }
}
