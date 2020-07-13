package com.xyp.mimi.di.component.circle;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.di.module.circle.CircleModule;
import com.xyp.mimi.mvp.ui.activity.circle.AddCircleActivity;
import com.xyp.mimi.mvp.ui.activity.circle.CircleActivity;

import dagger.Component;

@ActivityScope
@Component(modules = CircleModule.class, dependencies = AppComponent.class)
public interface CircleComponent {

    void inject(CircleActivity activity);

    void inject(AddCircleActivity addCircleActivity);


}