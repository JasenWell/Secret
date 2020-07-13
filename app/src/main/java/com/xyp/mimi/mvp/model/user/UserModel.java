package com.xyp.mimi.mvp.model.user;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.api.service.member.MemberService;
import com.xyp.mimi.mvp.http.api.service.setting.SettingService;
import com.xyp.mimi.mvp.http.api.service.user.UserService;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.login.LoginUserPost;
import com.xyp.mimi.mvp.http.entity.login.LoginUserResult;
import com.xyp.mimi.mvp.http.entity.user.UserCodePost;
import com.xyp.mimi.mvp.http.entity.user.UserEditPost;
import com.xyp.mimi.mvp.http.entity.user.UserInfoEditPost;
import com.xyp.mimi.mvp.http.entity.user.UserInformationResult;
import com.xyp.mimi.mvp.http.entity.user.UserLoginPasswordPost;
import com.xyp.mimi.mvp.http.entity.user.UserPayPasswordPost;
import com.xyp.mimi.mvp.http.entity.user.UserPost;
import com.xyp.mimi.mvp.http.entity.user.UserRegisterPost;
import com.xyp.mimi.mvp.http.entity.user.UserPhotoPost;
import com.xyp.mimi.mvp.http.entity.user.UserPhotoResult;

import java.util.Map;

import io.reactivex.Observable;


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
public class UserModel extends BaseModel implements UserContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public UserModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<UserPhotoResult> uploadPhoto(UserPhotoPost userPhotoPost) {
        return mRepositoryManager.obtainRetrofitService(MemberService.class)
                .uploadPhoto(userPhotoPost);
    }

    @Override
    public Observable<UserInformationResult> User(UserPost UserPost) {
        return mRepositoryManager.obtainRetrofitService(MemberService.class)
                .getMemInfo(UserPost);
    }

    @Override
    public Observable<BaseResponse<LoginUserResult>> login(String phone, String password) {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .loginByUsernamePassword(phone,password);
    }

//    @Override
//    public Observable<BaseResponse<LoginUserResult>> login(LoginUserPost userPost) {
//        return mRepositoryManager.obtainRetrofitService(UserService.class)
//                .loginByUsernamePassword(userPost);
//    }
    @Override
    public Observable<BaseResponse> editName(UserEditPost userEditPost) {
        return mRepositoryManager.obtainRetrofitService(MemberService.class)
                .editUserNick(userEditPost);
    }

    @Override
    public Observable<BaseResponse> editUserInfo(UserInfoEditPost userInfoEditPost) {
        return mRepositoryManager.obtainRetrofitService(MemberService.class)
                .editUserInfo(userInfoEditPost);
    }

    @Override
    public Observable<BaseResponse> getRegisterCode(Map m) {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .getSms(m);
    }

    @Override
    public Observable<BaseResponse<LoginUserResult>> register(String phone, String password,String userName, String money,String payPwd) {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .register(phone,password,userName,money,password);
    }

    @Override
    public Observable<BaseResponse> getRetrievePasswordCode(Map map) {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .getPasswordSms(map);
    }

    @Override
    public Observable<BaseResponse> retrievePassword(UserRegisterPost userRegisterPost) {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .changePassword(userRegisterPost);
    }

    @Override
    public Observable<BaseResponse> getCode(UserCodePost userCodePost) {
        return mRepositoryManager.obtainRetrofitService(SettingService.class)
                .getUpdatePswCode(userCodePost);
    }

    @Override
    public Observable<BaseResponse> changeLoginPassword(UserLoginPasswordPost userLoginPasswordPost) {
        return mRepositoryManager.obtainRetrofitService(SettingService.class)
            .updatePassword(userLoginPasswordPost);

    }

    @Override
    public Observable<BaseResponse> setPayPassword(UserPayPasswordPost userPayPasswordPost) {
        return mRepositoryManager.obtainRetrofitService(SettingService.class)
                .updatePayPwd(userPayPasswordPost);
    }


}