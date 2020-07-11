//package com.yiwuzhijia.ddyp.mvp.model;
//
//import android.app.Application;
//
//import com.google.gson.Gson;
//import com.jess.arms.integration.IRepositoryManager;
//import com.jess.arms.mvp.BaseModel;
//
//import com.jess.arms.di.scope.ActivityScope;
//
//import javax.inject.Inject;
//
//import com.yiwuzhijia.ddyp.mvp.contract.user.RegisterContract;
//import com.yiwuzhijia.ddyp.mvp.http.api.service.user.UserService;
//import com.yiwuzhijia.ddyp.mvp.http.entity.BaseResponse;
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
//@ActivityScope
//public class RegisterModel extends BaseModel implements RegisterContract.Model {
//    @Inject
//    Gson mGson;
//    @Inject
//    Application mApplication;
//
//    @Inject
//    public RegisterModel(IRepositoryManager repositoryManager) {
//        super(repositoryManager);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        this.mGson = null;
//        this.mApplication = null;
//    }
//
//    @Override
//    public Observable<BaseResponse> getRegisterCode(Map m) {
//        return mRepositoryManager.obtainRetrofitService(UserService.class)
//                .getSms(m);
//    }
//
//    @Override
//    public Observable<BaseResponse> register(UserRegisterPost userRegisterPost) {
//        return mRepositoryManager.obtainRetrofitService(UserService.class)
//                .register(userRegisterPost);
//    }
//}