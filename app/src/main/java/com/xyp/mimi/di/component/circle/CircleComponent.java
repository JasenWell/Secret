package com.xyp.mimi.di.component.circle;

<<<<<<< HEAD
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.di.module.circle.CircleModule;
import com.xyp.mimi.mvp.ui.activity.circle.AddCircleActivity;
import com.xyp.mimi.mvp.ui.activity.circle.CircleActivity;

import dagger.Component;

=======
import dagger.Component;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.di.module.circle.CircleModule;
import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.mvp.ui.activity.circle.AddCircleActivity;
import com.xyp.mimi.mvp.ui.activity.circle.CircleActivity;

>>>>>>> c1102d2024adae19d2e36885137bae797473fb2e
@ActivityScope
@Component(modules = CircleModule.class, dependencies = AppComponent.class)
public interface CircleComponent {

    void inject(CircleActivity activity);

    void inject(AddCircleActivity addCircleActivity);


}