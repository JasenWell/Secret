package com.xyp.mimi.im.net.hjh;


import android.os.Environment;

import com.google.gson.reflect.TypeToken;
import com.xyp.mimi.im.bean.ResponseSearchFriendInfo;
import com.xyp.mimi.im.bean.ResponseWrapperInfo;
import com.xyp.mimi.mvp.http.api.Api;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;

/**
 * Descroption:
 * Created by hjh on 2016/3/1.
 */
public class HttpHelper {

    public static String WEB_HOST = "http://148.70.97.197:9088";//139.199.165.130,//192.168.1.102//112.74.218.80//a.wushangxiupin.com//120.25.74.232
    public static final int PORT  = 9092;

    public static String getURl(){
        StringBuilder sb = new StringBuilder(Api.APP_DOMAIN);//内网
        return sb.append("").toString();
    }

    public static String getDownloadDir(){
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"ssa/sas";
    }

    public static String PostGetUrl(String url){
        StringBuilder sb = new StringBuilder(getURl());
        return sb.append(url).toString();
    }

    public  interface Method{
        int POST = 1;
        int GET = 2;
    }

    public enum BUSINESS{
    	
    	REQUEST_TEST("", Method.POST,true,Object.class, EConfig.LOGIN_SUCCESS),
        REQUEST_ADD_FRIEND("/mall/interface/insertFriendslist", Method.POST,true, ResponseSearchFriendInfo.class,EConfig.ADD_FRIEND_REQUEST_SUCCESS),
        REQUEST_AGREE_FRIEND("/mall/interface/updateFriendslist", Method.POST,true,Object.class,EConfig.AGREE_FRIEND_REQUEST_SUCCESS),
        REQUEST_SEARCH_FRIEND_REQUEST("/mall/interface/selectcahts", Method.POST,true, ResponseWrapperInfo.class,EConfig.SEARCH_FRIEND_REQUEST_SUCCESS),
        REQUEST_FRIEND_LIST("/mall/interface/selectFriendslist", Method.POST,true,ResponseWrapperInfo.class,EConfig.SEARCH_FRIEND_LIST_SUCCESS),
//        REQUEST_GET_RECHARGE_LIST("api/user.html", Method.POST,true,Object.class,new TypeToken<List<Object>>(){}.getType()),

        REQUEST_GET_USER_INFO("/mall/interface/selectuserid", Method.POST,true, LoginUserResult.class,EConfig.ADD_FRIEND_REQUEST_SUCCESS),


        REQUEST_CHAT_SINGLE("/mall/interface/caht", Method.POST,true,Object.class,EConfig.CHAT_SINGLE_SUCCESS),
        REQUEST_CHAT_SINGLE_RECORD("/mall/interface/cahtlist", Method.POST,true,Object.class,EConfig.CHAT_SINGLE_RECORD_SUCCESS)




    	;

        private String business;
        private int method;
        private int code; //   失败为成功+1
        private boolean object;
        private Class clazz;
        private Type type;
        private Response response;
        private String errorMsg;
        private ResponseJson responseJson;
        /**
         *
         * @param business 具体业务
         * @param method 请求方法
         * @param object 返回对象 or数组
         * @param clazz 返回data的对象字节类型
         */
        private BUSINESS(String business,int method,boolean object,Class clazz,int code,Type ...args){
            setBusiness(business);
            setMethod(method);
            setCode(code);
            setObject(object);
            setClazz(clazz);
            if(args != null && args.length > 0) {
                setType(args[0]);
            }
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public void setResponseJson(ResponseJson responseJson) {
            this.responseJson = responseJson;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public ResponseJson getResponseJson() {
            return responseJson;
        }

        public Response getResponse() {
            return response;
        }

        public void setResponse(Response response) {
            this.response = response;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public int getMethod() {
            return method;
        }

        public void setMethod(int method) {
            this.method = method;
        }

        public boolean isObject() {
            return object;
        }

        public void setObject(boolean object) {
            this.object = object;
        }

        public Class getClazz() {
            return clazz;
        }

        public void setClazz(Class clazz) {
            this.clazz = clazz;
        }


        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }


    public static class ACTION{
        public static String KEY_IP = "key_ip";
        public static String KEY_AUTO_LOGIN = "key_auto_login";
        public static String KEY_USER_NAME = "key_user_name";
        public static String KEY_USER_NICK = "key_user_nick";
        public static String KEY_USER_ID = "key_user_id";
        public static String KEY_USER_NO = "key_user_no";
        public static String KEY_USER_ICON = "key_user_icon";
        public static String KEY_USER_PWD = "key_user_pwd";
        public static String KEY_USER_PHONE = "key_user_phone";
        public static String KEY_INSTALL_TIME = "key_install_time";
        public static String KEY_INSTALL_FIRST = "key_install_first";
        public static String KEY_LOGIN_TYPE = "key_login_type";
        public static String KEY_USER_CARD = "key_user_card";
        public static String KEY_SPLASH_AD = "key_splash_ad";

        public static String KEY_RECEIVED_MSG = "key_received_msg";
        public static String KEY_READ_MSG = "key_read_msg";
        public static String KEY_TTS_SWITCH = "key_tts_switch";
    }
    
    public interface HttpResult{
        //系统错误
        int SYSTEM_ERROR = -1;
        //成功
        int SUCCESS = 1;
        //失败
        int FAILED = 0;
        //登录信息过期
        int INVALID_TOKEN = 2;
        //参数错误
        int ERROR_PARAM = 3;
        //无数据
        int NO_DATA = 4;
        //禁用
        int DISABLED = 5;

        //远程服务器错误（Ping++ 服务器/第三方支付渠道出现错误
        int ERR_REMOTE_SERV_ERR = -9;


        //超时异常
        int TIME_OUT = -98;

        //json解析错误
        int ERR_PARSE = - 99;
        //volley分发错误
        int ERR_NET = - 100;
    }
}
