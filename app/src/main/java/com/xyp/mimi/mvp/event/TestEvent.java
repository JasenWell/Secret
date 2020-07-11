package com.xyp.mimi.mvp.event;

import com.xyp.mimi.mvp.http.entity.NetData;

public class TestEvent {
    private String type;

    private NetData netData;

    public TestEvent(String type, NetData netData) {
        this.type = type;
        this.netData = netData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NetData getNetData() {
        return netData;
    }

    public void setNetData(NetData netData) {
        this.netData = netData;
    }
}
