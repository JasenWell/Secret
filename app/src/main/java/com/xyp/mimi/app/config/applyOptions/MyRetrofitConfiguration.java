//package com.yiwuzhijia.ddyp.app.config.applyOptions;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//
//import com.jess.arms.di.module.ClientModule;
//import com.yiwuzhijia.ddyp.BuildConfig;
//import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//
//public class MyRetrofitConfiguration implements ClientModule.RetrofitConfiguration {
//    @Override
//    public void configRetrofit(@NonNull Context context, @NonNull Retrofit.Builder builder) {
//        builder.client(client).build();
//
////        OkHttpClient.Builder b = new OkHttpClient.Builder();
////        if(BuildConfig.LOG_DEBUG){
////            // Log信息拦截器
////            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
////            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
////
////            //设置 Debug Log 模式
////            b.addInterceptor(loggingInterceptor);
////        }
////        builder.client(RetrofitUrlManager.getInstance().with(b).build()).build();
//    }
//}
