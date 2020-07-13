package com.xyp.mimi.mvp.http.entity.circle;

public class CirclePost {

    private String uid;
    private String context;
    public CirclePost(String uid) {
        this.uid = uid;
    }

    public CirclePost(String uid, String context) {
        this.uid = uid;
        this.context = context;
    }
}
