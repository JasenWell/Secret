//package com.yiwuzhijia.ddyp.mvp.model;
//
//import com.jess.arms.integration.IRepositoryManager;
//import com.jess.arms.mvp.BaseModel;
//import com.yiwuzhijia.ddyp.mvp.contract.user.LoginContract;
//import com.yiwuzhijia.ddyp.mvp.http.api.service.user.UserService;
//import com.yiwuzhijia.ddyp.mvp.http.entity.BaseResponse;
//import com.yiwuzhijia.ddyp.mvp.http.entity.login.LoginUserResult;
//import com.yiwuzhijia.ddyp.mvp.http.entity.login.LoginUserPost;
//
//import io.reactivex.Observable;
//
//public class LoginModel extends BaseModel implements LoginContract.Model {
//
//    public LoginModel(IRepositoryManager repositoryManager) {
//        super(repositoryManager);
//    }
//
//
//
//    @Override
//    public Observable<BaseResponse<LoginUserResult>> login(LoginUserPost loginUserPost) {
//        return mRepositoryManager.obtainRetrofitService(UserService.class)
//                .loginByUsernamePassword(loginUserPost);
//    }
//}
