package com.xyp.mimi.im.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import com.xyp.mimi.im.model.UserCacheInfo;
import com.xyp.mimi.im.utils.StringTools;

import lombok.Synchronized;

/**
 * 缓存登录后的用户信息。 即最有一个登录用户。
 * 当用户退出时可不清理。 可用于登录时自动填充用户账号和密码。
 * 在应用登录运行过程中， 可通过缓存获取当前运行的用户信息。
 * 此类不支持多进程使用
 */
public class UserCache {
    private static final String SP_NAME = "User_cache";
    private static final String SP_CACHE_USER = "last_login_user";
    private SharedPreferences sp;
    private static UserCache  userCacheInstance;

    public static Context context;

    private UserCache(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static void init(Context mcontext){
        context = mcontext;
    }

    public static UserCache getInstance(){
        if(userCacheInstance == null) {
            synchronized (UserCache.class) {
                if (userCacheInstance == null) {
                    userCacheInstance = new UserCache(context);
                }
            }
        }
        return userCacheInstance;
    }

    /**
     * 缓存去登录之后 User 的信息。
     * @param userCacehInfo
     */
    public void saveUserCache(UserCacheInfo userCacehInfo) {
        if (userCacehInfo == null || TextUtils.isEmpty(userCacehInfo.getId())) {
            return ;
        }
        UserCacheInfo tmpCacheInfo = getUserCache();
        if (tmpCacheInfo != null && !TextUtils.isEmpty(tmpCacheInfo.getId()) && !userCacehInfo.getId().equals(tmpCacheInfo.getId())) {
            // 另一个不同的用户
            Gson gson = new Gson();
            String userJson = gson.toJson(userCacehInfo);
            sp.edit().putString(SP_CACHE_USER, userJson).commit();
            return;
        }
        // 同一个用户或者第一个用户
        if (tmpCacheInfo != null) {
            tmpCacheInfo.setUserCacheInfo(userCacehInfo);
        } else {
            tmpCacheInfo = userCacehInfo;
        }

        Gson gson = new Gson();
        String userJson = gson.toJson(tmpCacheInfo);
        sp.edit().putString(SP_CACHE_USER, userJson).commit();
    }



    /**
     * 获取用户缓存信息
     * @return
     */
    public UserCacheInfo getUserCache() {
        try {
            String userJson = sp.getString(SP_CACHE_USER, "");
            if (TextUtils.isEmpty(userJson)) {
                return  null;
            }

            Gson gson = new Gson();
            UserCacheInfo userCacheInfo = gson.fromJson(userJson, UserCacheInfo.class);
            return userCacheInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前的用户ID
     * @return
     */
    public String getCurrentUserId() {
        UserCacheInfo userCache = getUserCache();
        if (userCache == null) {
            return null;
        }
        return userCache.getId();
    }

    /**
     * 退出登录所要清理的缓存
     */
    public void logoutClear() {
        UserCacheInfo userCache = getUserCache();
        if (userCache == null) {
            return ;
        }
        userCache.setLoginToken("");
        userCache.setId("");
        Gson gson = new Gson();
        String userJson = gson.toJson(userCache);
        sp.edit().putString(SP_CACHE_USER, userJson).commit();
    }

    public static String KEY_USER_ID = "key_user_id";
    public static String KEY_USER_TOKEN = "key_user_token";

//    String loginToken = "SurIKWS2E4nr0wDr5piVhfOgWNL4EzDZ@zegh.cn.rongnav.com;zegh.cn.rongcfg.com";//72
//    loginToken = "s+3bqXLcrSbr0wDr5piVhR4B4wF2jeGy@zegh.cn.rongnav.com;zegh.cn.rongcfg.com";//13402375956 74
//    loginToken = "aFCpDkUQ1w7r0wDr5piVhZARhmSB1Z+Q@zegh.cn.rongnav.com;zegh.cn.rongcfg.com";//13526542876 73 123456

    public void putString(String key,String value){
        sp.edit().putString(key,value).commit();
    }

    public String getString(String key,String defaultValue){
        return sp.getString(key,defaultValue);
    }
}
