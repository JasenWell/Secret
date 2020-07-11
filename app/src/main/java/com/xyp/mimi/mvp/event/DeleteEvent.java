package com.xyp.mimi.mvp.event;

import com.xyp.mimi.mvp.http.entity.history.HistoryResult;

import java.util.List;

public class DeleteEvent {

    private  int  number;
    private  boolean isSelectAll;
    private String historyId;
    private  List<HistoryResult.DataBean> selectIndex;
    public DeleteEvent(int number, boolean isSelectAll) {
        this.number = number;
        this.isSelectAll = isSelectAll;
    }
    public DeleteEvent(int number, String historyId, boolean isSelectAll) {
        this.number = number;
        this.isSelectAll = isSelectAll;
        this.historyId = historyId;
    }
    public DeleteEvent(int number, String historyId, boolean isSelectAll, List<HistoryResult.DataBean> selectIndex) {
        this.number = number;
        this.isSelectAll = isSelectAll;
        this.historyId = historyId;
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

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public  List<HistoryResult.DataBean> getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex( List<HistoryResult.DataBean> selectIndex) {
        this.selectIndex = selectIndex;
    }
}
