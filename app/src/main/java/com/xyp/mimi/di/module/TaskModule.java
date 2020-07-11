package com.xyp.mimi.di.module;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.TaskContract;
import com.xyp.mimi.mvp.model.TaskModel;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskModule {

    TaskContract.View view;

    public TaskModule(TaskContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public TaskContract.Model provideModel(IRepositoryManager repositoryManager){
     return new TaskModel(repositoryManager);
    }
    @Provides
    @FragmentScope
    public TaskContract.View provideView(){
        return view;
    }

}
