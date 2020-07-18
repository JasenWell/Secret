package com.xyp.mimi.im.net.hjh.param;



import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class Params {

    public static Map<String,String> getUserInfoParams(String userId){
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        return map;
    }

    public static Map<String,String> searchfriendListParams(String userId){
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        return map;
    }


    public static  Map<String,String> addFriendRequestParams(String mianUid, String phone){
        Map<String,String> map = new HashMap<>();
        map.put("mianUid",mianUid);
        map.put("phone",phone);
        return map;
    }

    public static  Map<String,String> agreeFriendRequestParams(String mianUid, String viceUid){
        Map<String,String> map = new HashMap<>();
        map.put("mianUid",mianUid); //好友id
        map.put("viceUid",viceUid); //我的id
        return map;
    }

    public static  Map<String,String> searchFriendRequestParams(String userId){
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId); //我的id
        return map;
    }

    public static  Map<String,String> startSingleChatParams(String mianUid, String viceUid,String context){
        Map<String,String> map = new HashMap<>();
        map.put("mianUid",mianUid); //我的id
        map.put("viceUid",viceUid); //好友id
        map.put("context",context); //
        return map;
    }

    public static  Map<String,String> searchChatRecordParams(String mianUid, String viceUid){
        Map<String,String> map = new HashMap<>();
        map.put("mianUid",mianUid); //我的id
        map.put("viceUid",viceUid); //好友id
        return map;
    }

    @Deprecated
    public static  Map<String,String> createGroupParam(String userId, String groupId,String groupName){
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        map.put("groupId",groupId);
        map.put("groupName",groupName);
        return map;
    }

    public static  Map<String,String> createGroupParam(List<String> memList, String groupId, String groupName){
        Map<String,String> identityHashMap = new IdentityHashMap<>();
        for(String id: memList){
            identityHashMap.put(new String("userId"),id);
        }
        identityHashMap.put("groupId",groupId);
        identityHashMap.put("groupName",groupName);
        return identityHashMap;
    }
}
