package com.xyp.mimi.mvp.http.entity.market;

public class EvaluationListPost {

     private  String UserId;
     private  String Token;
     private  int Type;
     private  String ProId;
     private  int Page;
     private  int PageSize;

    public EvaluationListPost(String userId, String token, int type, String proId, int page, int pageSize) {
        UserId = userId;
        Token = token;
        Type = type;
        ProId = proId;
        Page = page;
        PageSize = pageSize;
    }
}
