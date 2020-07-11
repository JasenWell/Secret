package com.xyp.mimi.di.component.history;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.di.module.history.HistoryModule;
import com.xyp.mimi.mvp.ui.activity.history.HistoryActivity;

import dagger.Component;

@ActivityScope
@Component(modules = HistoryModule.class ,dependencies = AppComponent.class)
public interface HistoryComponent {

    void inject(HistoryActivity historyActivity);

}
