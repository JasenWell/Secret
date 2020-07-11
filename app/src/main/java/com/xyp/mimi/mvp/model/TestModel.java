package com.xyp.mimi.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.TestContract;
import com.xyp.mimi.mvp.http.api.service.TestService;
import com.xyp.mimi.mvp.http.entity.NetData;

import io.reactivex.Observable;


public class TestModel extends BaseModel implements TestContract.Model {
    public TestModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<NetData> getTest() {
        return mRepositoryManager.obtainRetrofitService(TestService.class)
                .searchTask();
    }
}
