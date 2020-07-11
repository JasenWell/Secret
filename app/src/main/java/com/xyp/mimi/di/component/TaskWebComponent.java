package com.xyp.mimi.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.di.module.TaskWebModule;
import com.xyp.mimi.mvp.ui.activity.WebFragmentActivity;

import dagger.Component;

@ActivityScope
@Component(modules = TaskWebModule.class ,dependencies = AppComponent.class)
public interface TaskWebComponent {

    void inject(WebFragmentActivity webFragmentActivity);

    
}
