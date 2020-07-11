//package com.yiwuzhijia.ddyp.di.component.user;
//
//import dagger.BindsInstance;
//import dagger.Component;
//
//import com.jess.arms.di.component.AppComponent;
//
//import com.yiwuzhijia.ddyp.di.module.user.RegisterModule;
//import com.yiwuzhijia.ddyp.mvp.contract.user.RegisterContract;
//
//import com.jess.arms.di.scope.ActivityScope;
//import com.yiwuzhijia.ddyp.mvp.ui.activity.user.RegisterActivity;
//
//
///**
// * ================================================
// * Description:
// * <p>
// * Created by MVPArmsTemplate on 05/20/2020 10:45
// * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
// * <a href="https://github.com/JessYanCoding">Follow me</a>
// * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
// * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
// * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
// * ================================================
// */
//@ActivityScope
//@Component(modules = RegisterModule.class, dependencies = AppComponent.class)
//public interface RegisterComponent {
//    void inject(RegisterActivity activity);
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        RegisterComponent.Builder view(RegisterContract.View view);
//
//        RegisterComponent.Builder appComponent(AppComponent appComponent);
//
//        RegisterComponent build();
//    }
//}