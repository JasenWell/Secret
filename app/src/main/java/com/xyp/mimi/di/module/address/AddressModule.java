package com.xyp.mimi.di.module.address;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.address.AddressContract;
import com.xyp.mimi.mvp.model.address.AddressModel;

import dagger.Module;
import dagger.Provides;

@Module
public class AddressModule {

    AddressContract.View view;

    AddressContract.EditView editView;

    public AddressModule(AddressContract.View view) {
        this.view = view;
    }

    public AddressModule(AddressContract.EditView editView) {
        this.editView = editView;
    }

    @Provides
    @ActivityScope
    public AddressContract.Model provideModel(IRepositoryManager repositoryManager){
     return new AddressModel(repositoryManager);
    }
    @Provides
    @ActivityScope
    public AddressContract.View provideView(){
        return view;
    }

    @Provides
    @ActivityScope
    public AddressContract.EditView provideView1(){
        return editView;
    }


}
