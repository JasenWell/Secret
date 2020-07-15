package com.xyp.mimi.im.event;

/**
 * Description:
 * Created by hjh on 2020/7/14.
 */
public class MessageEvent {

    private String msg;
    private int type;

    public MessageEvent(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public  interface EventType{
        int REFRESH_FRIEND_LIST = 0;
    }
}
