//package com.yiwuzhijia.ddyp.di.component;
//
//import dagger.BindsInstance;
//import dagger.Component;
//
//import com.jess.arms.di.component.AppComponent;
//
//import com.yiwuzhijia.ddyp.di.module.ProblemModule;
//import com.yiwuzhijia.ddyp.mvp.contract.ProblemContract;
//
//import com.jess.arms.di.scope.ActivityScope;
//import com.yiwuzhijia.ddyp.mvp.ui.activity.ProblemActivity;
//
//
///**
// * ================================================
// * Description:
// * <p>
// * Created by MVPArmsTemplate on 05/20/2020 18:41
// * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
// * <a href="https://github.com/JessYanCoding">Follow me</a>
// * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
// * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
// * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
// * ================================================
// */
//@ActivityScope
//@Component(modules = ProblemModule.class, dependencies = AppComponent.class)
//public interface ProblemComponent {
//    void inject(ProblemActivity activity);
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        ProblemComponent.Builder view(ProblemContract.View view);
//
//        ProblemComponent.Builder appComponent(AppComponent appComponent);
//
//        ProblemComponent build();
//    }
//}