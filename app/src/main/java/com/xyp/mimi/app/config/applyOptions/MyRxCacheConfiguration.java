package com.xyp.mimi.app.config.applyOptions;

import android.content.Context;

import androidx.annotation.NonNull;

import com.jess.arms.di.module.ClientModule;

import io.rx_cache2.internal.RxCache;

public class MyRxCacheConfiguration implements ClientModule.RxCacheConfiguration {
    @Override
    public RxCache configRxCache(@NonNull Context context, @NonNull RxCache.Builder builder) {
        // 当数据无法加载时，使用过期数据
        builder.useExpiredDataIfLoaderNotAvailable(true);
        return null;
    }
}
