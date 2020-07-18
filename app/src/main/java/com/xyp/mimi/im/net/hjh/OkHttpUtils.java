package com.xyp.mimi.im.net.hjh;



import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 自定义快捷网络请求
 * Created by zlang on 2018/1/2.
 */

public class OkHttpUtils {
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    protected static final String UTF_8 = "UTF-8";

    /**
     * 构造方法
     * @param okHttpClient
     */
    public OkHttpUtils(OkHttpClient okHttpClient)
    {
        if (okHttpClient == null)
        {
            mOkHttpClient = new OkHttpClient();
        } else
        {
            mOkHttpClient = okHttpClient;
        }
    }

    /**
     * 初始化 OkHttpClient
     * @param okHttpClient
     * @return
     */
    public static OkHttpUtils initClient(OkHttpClient okHttpClient)
    {
        if (mInstance == null)
        {
            synchronized (OkHttpUtils.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance()
    {
        return initClient(null);
    }

    //拼装get参数
    protected String pingGetParams(StringBuilder url,Map<String, Object> params){
        if(url.indexOf("?") < 0){
            url.append('?');
        }

        StringBuilder parampart = new StringBuilder();
        Object temp = null;

        try {
            for (String name : params.keySet()) {
                temp = params.get(name);
                if (temp == null) {
                    continue;
                }

                parampart.append('&');
                parampart.append(URLEncoder.encode(String.valueOf(name), UTF_8));
                parampart.append('=');
                parampart.append(URLEncoder.encode(String.valueOf(temp), UTF_8));
            }
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }

        url.append(parampart);

        return url.toString().replace("?&", "?");
    }

    //拼装get参数
    protected String pingPostParams(Map<String, String> params){
        StringBuilder parampart = new StringBuilder();
        parampart.append("?");
        Object temp = null;

        try {
            for (String name : params.keySet()) {
                temp = params.get(name);
                if (temp == null) {
                    continue;
                }

                parampart.append('&');
                parampart.append(String.valueOf(name));
                parampart.append('=');
                parampart.append(String.valueOf(temp));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        return parampart.toString().replace("?&", "");
    }

    public Call postWrapper(String data,String url){
        Call call;
        MediaType MEDIA_TYPE_NORAML_FORM = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        RequestBody requestBody=RequestBody.create(MEDIA_TYPE_NORAML_FORM,data);
        Request requestPost=new Request.Builder().url(url).post(requestBody).build();
        call =  mOkHttpClient.newBuilder()
                .connectTimeout(60 * 1000, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.SECONDS)
                .writeTimeout(60 * 1000, TimeUnit.SECONDS)
                .build()
                .newCall(requestPost);
        return call;
    }

    public Call post(Map<String,String> map,String url){
        return post(map,url,null);
    }

    public Call postIdentityHashMapByheader(String data,String url,Map<String,String> header){
        Call call;
        MediaType MEDIA_TYPE_NORAML_FORM = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        RequestBody requestBody=RequestBody.create(MEDIA_TYPE_NORAML_FORM,data);
        Request.Builder reBuilder = new Request.Builder().url(url).post(requestBody);
        if(header != null) {
            for (String key : header.keySet()) {
                reBuilder.addHeader(key, header.get(key));
            }
        }
        Request requestPost = reBuilder.build();
        call =  mOkHttpClient.newBuilder()
                .connectTimeout(60 * 1000, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.SECONDS)
                .writeTimeout(60 * 1000, TimeUnit.SECONDS)
                .build()
                .newCall(requestPost);
        return call;
    }

    public Call postIdentityHashMap(Map<String,String> map,String url,Map<String,String> header){
        return postIdentityHashMapByheader(pingPostParams(map),url,header);
    }

    /**
     * post带参数请求数据
     * @param map 参数
     * @param url 网络请求地址
     * @return
     */
    public Call post(Map<String,String> map,String url,Map<String,String> header){
        if(false){
            return postWrapper(pingPostParams(map),url);
        }
        Call call;
        /**
         * 创建请求的参数body
         */
        FormBody.Builder builder = new FormBody.Builder();
        if(map == null || map.isEmpty()){
            //没有参数情况
        }else {
            /**
             * 遍历参数
             */
            for(String key : map.keySet()){
                builder.add(key,map.get(key));
            }
        }
        RequestBody body = builder.build();
        Request.Builder reBuilder = new Request.Builder()
                .url(url)
                //.addHeader("Connection","close")
                .post(body);
        if(header != null) {
            for (String key : header.keySet()) {
                reBuilder.addHeader(key, header.get(key));
            }
        }
        Request request = reBuilder.build();
        call =  mOkHttpClient.newBuilder()
                .connectTimeout(60 * 1000, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.SECONDS)
                .writeTimeout(60 * 1000, TimeUnit.SECONDS)
                .build()
                .newCall(request);
        return call;
//        return mOkHttpClient.newCall(request);
    }

    public Call postJson(String json,String url){
        Call call;
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8") , json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        call =  mOkHttpClient.newBuilder()
                .connectTimeout(60 * 1000, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.SECONDS)
                .writeTimeout(60 * 1000, TimeUnit.SECONDS)
                .build()
                .newCall(request);
        return call;
    }


    /**
     * post 上传多个文件以及带参数到服务器
     * @param file
     * @param map
     * @param url
     * @return
     */
    public Call postFileFrom(File file,Map<String, String> map, String url){
        Call call;
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file == null){
            //没有文件或者说文件为空的情况
        }else {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", file.getName(), body);
        }
        if(map == null || map.isEmpty()){
            //没有参数情况
        }else {
            /**
             * 遍历参数
             */
            for(String key : map.keySet()){
                requestBody.addFormDataPart(key,map.get(key));
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody.build())
                .build();
        call =  mOkHttpClient.newBuilder()
                .connectTimeout(60 * 1000, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.SECONDS)
                .writeTimeout(60 * 1000, TimeUnit.SECONDS)
                .build()
                .newCall(request);
        return call;
//        return mOkHttpClient.newCall(request);
    }


}
