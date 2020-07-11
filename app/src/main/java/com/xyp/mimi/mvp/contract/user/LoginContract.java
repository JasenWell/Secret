//package com.yiwuzhijia.ddyp.mvp.contract.user;
//
//
//
//import com.jess.arms.mvp.IModel;
//import com.jess.arms.mvp.IView;
//import com.yiwuzhijia.ddyp.mvp.http.entity.BaseResponse;
//import com.yiwuzhijia.ddyp.mvp.http.entity.login.LoginUserResult;
//import com.yiwuzhijia.ddyp.mvp.http.entity.login.UserPost;
//
//import io.reactivex.Observable;
//
//public interface LoginContract {
//
//    interface  View extends IView{
//        void  loginResult(LoginUserResult s);
//    }
//
//
//    interface  Model extends IModel{
//        Observable<BaseResponse<LoginUserResult>> login(UserPost userPost);
//    }
//}
