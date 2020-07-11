package com.xyp.mimi.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.PasswordContract;
import com.xyp.mimi.mvp.model.PasswordModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PasswordModule {

    PasswordContract.View view;

    public PasswordModule(PasswordContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public PasswordContract.Model provideModel(IRepositoryManager repositoryManager){
     return new PasswordModel(repositoryManager);
    }
    @Provides
    @ActivityScope
    public PasswordContract.View provideView(){
        return view;
    }

}
