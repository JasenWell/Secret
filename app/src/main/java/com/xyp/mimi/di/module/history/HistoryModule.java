package com.xyp.mimi.di.module.history;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.history.HistoryContract;
import com.xyp.mimi.mvp.model.history.HistoryModel;
import dagger.Module;
import dagger.Provides;

@Module
public class HistoryModule {

    HistoryContract.View view;
    public HistoryModule(HistoryContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public HistoryContract.Model provideModel(IRepositoryManager repositoryManager){
     return new HistoryModel(repositoryManager);
    }

    @Provides
    @ActivityScope
    public HistoryContract.View provideView(){
        return view;
    }

}
