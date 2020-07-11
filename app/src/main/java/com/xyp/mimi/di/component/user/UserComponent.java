package com.xyp.mimi.di.component.user;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.xyp.mimi.di.module.user.UserModule;

import com.jess.arms.di.scope.ActivityScope;
import com.xyp.mimi.mvp.ui.activity.login.LoginActivity;
import com.xyp.mimi.mvp.ui.activity.login.RegisterActivity;
import com.xyp.mimi.mvp.ui.activity.user.ChangeLoginPasswordActivity;
import com.xyp.mimi.mvp.ui.activity.user.RetrievePasswordActivity;
import com.xyp.mimi.mvp.ui.activity.user.SetPayPasswordActivity;
import com.xyp.mimi.mvp.ui.activity.user.UserInformationActivity;
import com.xyp.mimi.mvp.ui.activity.user.UserNameActivity;


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
@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {

    void inject(UserInformationActivity activity);

    void injectUserName(UserNameActivity userNameActivity);

    void injectLogin(LoginActivity activity);

    void injectRegister(RegisterActivity activity);

    void injectRetrievePassword(RetrievePasswordActivity retrievePasswordActivity);
    void injectChangeLoginPassword(ChangeLoginPasswordActivity changeLoginPasswordActivity);

    void injectChangePayPassword(SetPayPasswordActivity setPayPasswordActivity);


}


