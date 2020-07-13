package com.xyp.mimi.mvp.contract.user;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
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
public interface UserContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void uploadPhotoResult(UserPhotoResult userPhotoResult);

        void informationResult(UserInformationResult userInformationResult);

        void userInfoEditResult(BaseResponse baseResponse);

    }
    interface UserNameEditView extends IView {
        void editResult(BaseResponse baseResponse);
    }

    interface  LoginView extends IView{
        void  loginResult(LoginUserResult s);
    }

    interface  RegisterView extends IView{

        void  registerCodeResult(BaseResponse baseResponse);

        void  registerResult(BaseResponse baseResponse);
    }


    interface  RetrievePasswordView extends IView{  //找回密碼

        void  RetrievePasswordCodeResult(BaseResponse baseResponse);

        void  RetrievePasswordResult(BaseResponse baseResponse);
    }


    interface  LoginPasswordView extends IView{  //修改登录密碼

        void  LoginPasswordCodeResult(BaseResponse baseResponse);

        void  LoginPasswordResult(BaseResponse baseResponse);
    }


    interface  PayPasswordView extends IView{  //修改支付密碼

        void  PayPasswordCodeResult(BaseResponse baseResponse);

        void  PayPasswordResult(BaseResponse baseResponse);
    }





    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<UserPhotoResult> uploadPhoto(UserPhotoPost userPhotoPost);

        Observable<UserInformationResult> User(UserPost UserPost);

        Observable<BaseResponse<LoginUserResult>> login(String phone, String password);

        Observable<BaseResponse> editName(UserEditPost userEditPost);

        //编辑会员信息
        Observable<BaseResponse> editUserInfo(UserInfoEditPost userInfoEditPost);

        Observable<BaseResponse> getRegisterCode(Map map);

        Observable<BaseResponse<LoginUserResult>> register(String phone, String password,String userName, String money,String payPwd);

        Observable<BaseResponse> getRetrievePasswordCode(Map map);

        Observable<BaseResponse> retrievePassword(UserRegisterPost userRegisterPost);

        //获取验证码[修改登录密码、修改支付密码]
        Observable<BaseResponse> getCode(UserCodePost userCodePost);

        //设置登录密码
        Observable<BaseResponse> changeLoginPassword(UserLoginPasswordPost userLoginPasswordPost);

        //设置支付密码
        Observable<BaseResponse> setPayPassword(UserPayPasswordPost userPayPasswordPost);

    }
}
