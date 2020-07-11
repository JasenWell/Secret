package com.xyp.mimi.di.component.project;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.FragmentScope;
import com.xyp.mimi.di.module.project.ProjectModule;
import com.xyp.mimi.mvp.contract.project.ProjectContract;
import com.xyp.mimi.mvp.ui.fragment.project.ProjectViewPagerFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/07/2020 16:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = ProjectModule.class, dependencies = AppComponent.class)
public interface ProjectComponent {

    void inject(ProjectViewPagerFragment projectViewPagerFragment);
    @Component.Builder
    interface Builder {
        @BindsInstance
        ProjectComponent.Builder view(ProjectContract.View view);

        ProjectComponent.Builder appComponent(AppComponent appComponent);

        ProjectComponent build();
    }
}