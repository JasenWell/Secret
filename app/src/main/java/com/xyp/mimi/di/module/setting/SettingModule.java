package com.xyp.mimi.di.module.setting;


import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.address.AddressContract;
import com.xyp.mimi.mvp.contract.setting.SettingContract;
import com.xyp.mimi.mvp.model.address.AddressModel;
import com.xyp.mimi.mvp.model.setting.SettingModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/14/2020 15:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public  class SettingModule {

    SettingContract.View view;

    public SettingModule(SettingContract.View view) {
        this.view = view;
    }


    @Provides
    @ActivityScope
    public SettingContract.Model provideModel(IRepositoryManager repositoryManager){
        return new SettingModel(repositoryManager);
    }
    @Provides
    @ActivityScope
    public SettingContract.View provideView(){
        return view;
    }



}