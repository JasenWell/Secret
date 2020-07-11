package com.xyp.mimi.di.module.user;

import dagger.Module;
import dagger.Provides;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.model.user.UserModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/19/2020 10:37
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public  class UserModule {

    UserContract.View view;

    UserContract.UserNameEditView userNameEditView;

    UserContract.LoginView loginView;

    UserContract.RegisterView registerView;

    UserContract.RetrievePasswordView retrievePasswordView;

    UserContract.LoginPasswordView loginPasswordView;

    UserContract.PayPasswordView payPasswordView;





    public UserModule(UserContract.View view) {
        this.view = view;
    }

    public UserModule(UserContract.UserNameEditView userNameEditView) {
        this.userNameEditView = userNameEditView;
    }
    public UserModule(UserContract.LoginView loginView) {
        this.loginView = loginView;
    }

    public UserModule(UserContract.RegisterView registerView) {
        this.registerView = registerView;
    }

    public UserModule(UserContract.RetrievePasswordView retrievePasswordView) {
        this.retrievePasswordView = retrievePasswordView;
    }
    public UserModule(UserContract.LoginPasswordView loginPasswordView) {
        this.loginPasswordView = loginPasswordView;
    }

    public UserModule(UserContract.PayPasswordView payPasswordView) {
        this.payPasswordView = payPasswordView;
    }


    @Provides
    @ActivityScope
    public UserContract.Model provideModel(IRepositoryManager repositoryManager){
        return new UserModel(repositoryManager);
    }

    @Provides
    @ActivityScope
    public UserContract.View provideView(){
        return view;
    }


    @Provides
    @ActivityScope
    public UserContract.UserNameEditView provideView1(){
        return userNameEditView;
    }

    @Provides
    @ActivityScope
    public UserContract.LoginView provideView2(){
        return loginView;
    }


    @Provides
    @ActivityScope
    public UserContract.RegisterView provideView3(){
        return registerView;
    }

    @Provides
    @ActivityScope
    public UserContract.RetrievePasswordView provideView4(){
        return retrievePasswordView;
    }

    @Provides
    @ActivityScope
    public UserContract.LoginPasswordView provideView5(){
        return loginPasswordView;
    }

    @Provides
    @ActivityScope
    public UserContract.PayPasswordView provideView6(){
        return payPasswordView;
    }


}