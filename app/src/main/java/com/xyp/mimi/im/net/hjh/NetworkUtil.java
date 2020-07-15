package com.xyp.mimi.im.net.hjh;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
	private Context context;
	private static NetworkUtil mInstance;

	public static NetworkUtil getInstance() {
		if (mInstance == null) {
			synchronized (NetworkUtil.class) {
				if (mInstance == null) {
					mInstance = new NetworkUtil();
				}
			}
		}
		return mInstance;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * 检测网络是否可用
	 * 
	 * @return true 可用 false 不可用
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnectedOrConnecting();
	}

	/**
	 * 获取当前的网络类型
	 * 
	 * @return 1:wifi 0:除了wifi以外的网络或没有网络
	 */
	public int getNetworkType() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {// wifi
			return ConnectivityManager.TYPE_WIFI;
		} else {
			return 0;
		}
	}
}
