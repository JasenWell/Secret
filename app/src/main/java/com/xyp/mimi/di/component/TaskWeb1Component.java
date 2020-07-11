package com.xyp.mimi.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.xyp.mimi.di.module.TaskWeb1Module;
import com.xyp.mimi.mvp.ui.fragment.AllWebFragment;

import dagger.Component;

@FragmentScope
@Component(modules = TaskWeb1Module.class ,dependencies = AppComponent.class)
public interface TaskWeb1Component {
    void inject(AllWebFragment allWebFragment);
}
