package com.xyp.mimi.mvp.contract.history;


import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xyp.mimi.mvp.http.entity.history.HistoryDeletePost;
import com.xyp.mimi.mvp.http.entity.history.HistoryPost;
import com.xyp.mimi.mvp.http.entity.history.HistoryResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;


public interface HistoryContract {

    interface  View extends IView{
        void  HistoryResult(HistoryResult s);
        void deleteHistory(BaseResponse baseResponse);
    }

    interface  Model extends IModel{

        Observable<HistoryResult> getHistoryData(HistoryPost historyPost);

        Observable<BaseResponse> deleteHistory(HistoryDeletePost historyDeletePost);
    }
}
