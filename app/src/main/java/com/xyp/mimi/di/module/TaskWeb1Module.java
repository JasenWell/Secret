package com.xyp.mimi.di.module;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.TaskWebContract;
import com.xyp.mimi.mvp.model.TaskWebModel;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskWeb1Module {

    TaskWebContract.View view;

    public TaskWeb1Module(TaskWebContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public TaskWebContract.Model provideModel(IRepositoryManager repositoryManager){
     return new TaskWebModel(repositoryManager);
    }
    @Provides
    @FragmentScope
    public TaskWebContract.View provideView(){
        return view;
    }

}
