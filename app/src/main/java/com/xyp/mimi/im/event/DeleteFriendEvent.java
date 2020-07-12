package com.xyp.mimi.im.event;

public class DeleteFriendEvent {
    public String userId;
    public boolean result;

    public DeleteFriendEvent(String userId, boolean result) {
        this.userId = userId;
        this.result = result;
    }
}
