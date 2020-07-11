//package com.yiwuzhijia.ddyp.mvp.contract.user;
//
//import com.jess.arms.mvp.IView;
//import com.jess.arms.mvp.IModel;
//import com.yiwuzhijia.ddyp.mvp.http.entity.BaseResponse;
//import com.yiwuzhijia.ddyp.mvp.http.entity.login.LoginUserResult;
//import com.yiwuzhijia.ddyp.mvp.http.entity.login.UserPost;
//import com.yiwuzhijia.ddyp.mvp.http.entity.user.UserRegisterPost;
//
//import java.util.Map;
//
//import io.reactivex.Observable;
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
//public interface RegisterContract {
//
//    interface  View extends IView{
//        void  registerCodeResult(BaseResponse baseResponse);
//
//        void  registerResult(BaseResponse baseResponse);
//    }
//
//
//    interface  Model extends IModel{
//        Observable<BaseResponse> getRegisterCode(Map map);
//
//        Observable<BaseResponse> register(UserRegisterPost userRegisterPost);
//
//
//    }
//
//}
