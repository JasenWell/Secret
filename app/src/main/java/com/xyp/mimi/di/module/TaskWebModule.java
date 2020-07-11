package com.xyp.mimi.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.TaskWebContract;
import com.xyp.mimi.mvp.model.TaskWebModel;
import dagger.Module;
import dagger.Provides;

@Module
public class TaskWebModule {

    TaskWebContract.View view;

    public TaskWebModule(TaskWebContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public TaskWebContract.Model provideModel(IRepositoryManager repositoryManager){
     return new TaskWebModel(repositoryManager);
    }
    @Provides
    @ActivityScope
    public TaskWebContract.View provideView(){
        return view;
    }

}
