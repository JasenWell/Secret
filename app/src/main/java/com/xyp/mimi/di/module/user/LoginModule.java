//package com.yiwuzhijia.ddyp.di.module.user;
//
//import com.jess.arms.di.scope.ActivityScope;
//import com.jess.arms.integration.IRepositoryManager;
//import com.yiwuzhijia.ddyp.mvp.contract.user.LoginContract;
//import com.yiwuzhijia.ddyp.mvp.model.LoginModel;
//
//import dagger.Module;
//import dagger.Provides;
//
//@Module
//public class LoginModule {
//
//    LoginContract.View view;
//
//    public LoginModule(LoginContract.View view) {
//        this.view = view;
//    }
//
//    @Provides
//    @ActivityScope
//    public LoginContract.Model provideModel(IRepositoryManager repositoryManager){
//     return new LoginModel(repositoryManager);
//    }
//    @Provides
//    @ActivityScope
//    public LoginContract.View provideView(){
//        return view;
//    }
//
//}
