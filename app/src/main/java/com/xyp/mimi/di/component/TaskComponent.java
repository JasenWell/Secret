package com.xyp.mimi.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.xyp.mimi.di.module.TaskModule;
import com.xyp.mimi.mvp.ui.fragment.FindTaskFragment;

import dagger.Component;

@FragmentScope
@Component(modules = TaskModule.class ,dependencies = AppComponent.class)
public interface TaskComponent {

    void inject(FindTaskFragment findTaskFragment);


}
