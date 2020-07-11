package com.xyp.mimi.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.di.module.PasswordModule;
import com.xyp.mimi.mvp.ui.activity.user.PasswordActivity;
import dagger.Component;

@ActivityScope
@Component(modules = PasswordModule.class ,dependencies = AppComponent.class)
public interface PasswordComponent {

    void inject(PasswordActivity activity);
}
