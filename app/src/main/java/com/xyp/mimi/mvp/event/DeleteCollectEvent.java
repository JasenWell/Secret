package com.xyp.mimi.mvp.event;

import com.xyp.mimi.mvp.http.entity.history.HistoryResult;

import java.util.List;

public class DeleteCollectEvent {

    private  int  number;
    private  boolean isSelectAll;
    private String VideoId;
    private List<HistoryResult.DataBean> selectIndex;
    public DeleteCollectEvent(int number, boolean isSelectAll) {
        this.number = number;
        this.isSelectAll = isSelectAll;
    }
    public DeleteCollectEvent(int number, String VideoId, boolean isSelectAll) {
        this.number = number;
        this.isSelectAll = isSelectAll;
        this.VideoId = VideoId;
    }
    public DeleteCollectEvent(int number, String VideoId, boolean isSelectAll, List<HistoryResult.DataBean> selectIndex) {
        this.number = number;
        this.isSelectAll = isSelectAll;
        this.VideoId = VideoId;
        this.selectIndex = selectIndex;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSelectAll() {
        return isSelectAll;
    }

    public void setSelectAll(boolean selectAll) {
        isSelectAll = selectAll;
    }

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }

    public List<HistoryResult.DataBean> getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(List<HistoryResult.DataBean> selectIndex) {
        this.selectIndex = selectIndex;
    }
}