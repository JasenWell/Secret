package com.xyp.mimi.mvp.model.history;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.history.HistoryContract;
import com.xyp.mimi.mvp.http.api.service.history.HistoryService;
import com.xyp.mimi.mvp.http.entity.history.HistoryDeletePost;
import com.xyp.mimi.mvp.http.entity.history.HistoryPost;
import com.xyp.mimi.mvp.http.entity.history.HistoryResult;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;


public class HistoryModel extends BaseModel implements HistoryContract.Model {

    public HistoryModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<HistoryResult> getHistoryData(HistoryPost historyPost) {
        return mRepositoryManager.obtainRetrofitService(HistoryService.class)
                .getHistoryData(historyPost);
    }

    @Override
    public Observable<BaseResponse> deleteHistory(HistoryDeletePost historyDeletePost) {
        return mRepositoryManager.obtainRetrofitService(HistoryService.class)
                .deleteHistory(historyDeletePost);
    }


}
