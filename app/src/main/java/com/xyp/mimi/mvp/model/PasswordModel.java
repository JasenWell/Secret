package com.xyp.mimi.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xyp.mimi.mvp.contract.PasswordContract;
import com.xyp.mimi.mvp.http.api.service.PasswordService;
import com.xyp.mimi.mvp.http.entity.BaseResponse;

import io.reactivex.Observable;


public class PasswordModel extends BaseModel implements PasswordContract.Model {
    public PasswordModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<Object>> changePassword(String useid, String opwd, String npwd) {
        return mRepositoryManager.obtainRetrofitService(PasswordService.class)
                .changePassword(useid,opwd,npwd);
    }
}
