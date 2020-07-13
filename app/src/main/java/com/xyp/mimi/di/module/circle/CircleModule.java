package com.xyp.mimi.di.module.circle;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.circle.CircleContract;
import com.xyp.mimi.mvp.model.circle.CircleModel;

import dagger.Module;
import dagger.Provides;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/13/2020 09:45
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public  class CircleModule {

    CircleContract.CircleListView circleListView;

    CircleContract.PushCircleView pushCircleView;

    public CircleModule(CircleContract.CircleListView circleListView) {
        this.circleListView = circleListView;
    }

    public CircleModule(CircleContract.PushCircleView pushCircleView) {
        this.pushCircleView = pushCircleView;
    }

    @Provides
    @ActivityScope
    public CircleContract.Model provideModel(IRepositoryManager repositoryManager){
        return new CircleModel(repositoryManager);
    }

    @Provides
    @ActivityScope
    public CircleContract.CircleListView provideView(){
        return circleListView;
    }

    @Provides
    @ActivityScope
    public CircleContract.PushCircleView provideView1(){
        return pushCircleView;
    }

}