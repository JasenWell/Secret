package com.xyp.mimi.mvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Locale;

public class Utils {

    /**
     * check if the network connected or not
     * @param context context
     * @return true: connected, false:not, null:unknown
     */
    public static Boolean isNetworkConnected(Context context) {
        try {
            context = context.getApplicationContext();
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public static String getErrorImage() {
        return "http://www." + System.currentTimeMillis() + ".com/abc.png";
    }

    public static String getRandomImage() {
        int id = (int) (Math.random() * 100000);
        return String.format(Locale.CHINA, "https://www.thiswaifudoesnotexist.net/example-%d.jpg", id);
    }

    public static String getNameByPosition(int itemPosition,int i) {
        String name = itemPosition+"_"+Name.IMAGE_1;
        switch (i){
            case 0:
                name = itemPosition+"_"+Name.IMAGE_1;
                break;
            case 1:
                name = itemPosition+"_"+Name.IMAGE_2;
                break;
            case 2:
                name = itemPosition+"_"+Name.IMAGE_3;
                break;
            case 3:
                name = itemPosition+"_"+Name.IMAGE_4;
                break;
            case 4:
                name = itemPosition+"_"+Name.IMAGE_5;
                break;
            case 5:
                name = itemPosition+"_"+Name.IMAGE_6;
                break;
            case 6:
                name = itemPosition+"_"+Name.IMAGE_7;
                break;
            case 7:
                name = itemPosition+"_"+Name.IMAGE_8;
                break;
            case 8:
                name = itemPosition+"_"+Name.IMAGE_9;
                break;

        }
        return name;
    }

}