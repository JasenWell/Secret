package com.xyp.mimi.mvp.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import androidx.fragment.app.Fragment;

public class GlideLoadUtils {
    private String TAG = "ImageLoader";

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */
    public GlideLoadUtils() {
    }

    private static class GlideLoadUtilsHolder {
        private final static GlideLoadUtils INSTANCE = new GlideLoadUtils();
    }

    public static GlideLoadUtils getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }

    /**
     * Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     *
     * @param context
     * @param url           加载图片的url地址  String
     * @param imageView     加载图片的ImageView 控件
     * @param default_image 图片展示错误的本地图片 id
     */
    public void glideLoad(Context context, String url, ImageView imageView, int default_image) {
        if (context != null) {
            Glide.with(context).load(url).apply(new RequestOptions().error(default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,context is null");
        }
    }

    public void glideLoadRoundedCorners(Context context, String url, ImageView imageView) {
        if (context != null) {
//            Glide.with(context).load(url)
//            .apply(new RequestOptions().optionalTransform(new RoundedCorners(20)))
//           .into(imageView);


            //设置图片圆角角度
            RoundedCorners roundedCorners = new RoundedCorners(40);
            //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
            // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
            Glide.with(context).load(url).apply(options).into(imageView);


//            Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(new RoundedCorners(20)).error(default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,context is null");
        }
    }

    public void glideLoadCircle(Context context, String url, ImageView imageView, int default_image) {
        if (context != null) {
            Glide.with(context).load(url).apply(new RequestOptions().error(default_image).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop()).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,context is null");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void glideLoad(Context context, int url, ImageView imageView, int default_image) {
        if (context != null) {
            Glide.with(context).load(url).apply(new RequestOptions().error(default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,activity is Destroyed");
        }
    }

    public void glideLoad(Fragment fragment, String url, ImageView imageView, int default_image) {
        if (fragment != null && fragment.getActivity() != null) {
            Glide.with(fragment).load(url).apply(new RequestOptions().error(default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,fragment is null");
        }
    }

    public void glideLoad(android.app.Fragment fragment, String url, ImageView imageView, int default_image) {
        if (fragment != null && fragment.getActivity() != null) {
            Glide.with(fragment).load(url).apply(new RequestOptions().error(default_image).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,android.app.Fragment is null");
        }
    }

}
