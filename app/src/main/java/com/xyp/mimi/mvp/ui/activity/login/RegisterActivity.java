package com.xyp.mimi.mvp.ui.activity.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.rx.IOCallBack;
import com.github.customview.MyEditText;
import com.github.customview.MyImageView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.user.DaggerUserComponent;
import com.xyp.mimi.di.module.user.UserModule;
import com.xyp.mimi.im.bean.HashKit;
import com.xyp.mimi.im.bean.ResponseIMTokenInfo;
import com.xyp.mimi.im.bean.ResponseUserInfo;
import com.xyp.mimi.im.net.hjh.callback.IBaseCallBack;
import com.xyp.mimi.im.net.hjh.imp.AsynModelImp;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.im.net.hjh.OkHttpUtils;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.user.UserRegisterImgResult;
import com.xyp.mimi.mvp.presenter.user.RegisterPresenter;
import com.xyp.mimi.mvp.utils.GetSign;
import com.xyp.mimi.mvp.utils.GlideLoadUtils;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.LubanOptions;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.model.TakePhotoOptions;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Subscriber;

public class RegisterActivity extends BaseSupportActivity<RegisterPresenter> implements UserContract.RegisterView, TakePhoto.TakeResultListener, InvokeListener, IBaseCallBack {

    @BindView(R.id.app_title)
    TextView appTitle;

    @BindView(R.id.v_bottom_line)
    View vBottomLine;
    @BindView(R.id.et_phone)
    MyEditText etPhone;

    @BindView(R.id.et_username)
    MyEditText etUserName;

    @BindView(R.id.et_password)
    MyEditText etPassword;

    @BindView(R.id.et_password2)
    MyEditText etPassword2;

    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    @BindView(R.id.iv_user)
    MyImageView ivUser;


    private String userUrl;//头像
    private BottomSheetDialog selectPhotoDialog;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private Handler handler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1901){
                ResponseIMTokenInfo tokenInfo = (ResponseIMTokenInfo) msg.obj;
                showLoadSuccess();
                showMsg("注册成功");
                UserCache.getInstance().putString(UserCache.KEY_USER_TOKEN+tokenInfo.getUserId(),tokenInfo.getToken());
                finish();
            }
        }
    };

    private AsynModelImp asynModelImp;
    int size;

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();
    }


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this))
                .build().injectRegister(RegisterActivity.this);
        asynModelImp = new AsynModelImp(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("注册");
    }

    @OnClick({R.id.tv_register, R.id.iv_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user:
                showSelectPhotoDialog();
                break;
            case R.id.tv_register:
                DeviceUtils.hideSoftKeyboard(mContext, getCurrentFocus());
                String phone = etPhone.getText().toString();
                String password = etPassword.getText().toString();
                String nick = etUserName.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    showMessage("密码不能为空/不可用");
                    return;
                }
                if (!GetSign.isMobile((phone))) {
                    showMessage("请输入正确手机号");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    showMessage("账号不能为空");
                    return;
                }

                if (TextUtils.isEmpty(nick)) {
                    showMessage("昵称不能为空");
                    return;
                }
                if(userUrl == null){
                    showMessage("用户头像需要上传");
                    return;
                }
                if (mPresenter != null) {
                    mPresenter.register(phone, password, nick, userUrl);
                }
                break;
        }
    }

    private void showSelectPhotoDialog() {
        if (selectPhotoDialog == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.popu_select_photo, null);

//            //查看大图
//            view.findViewById(R.id.tv_see_big_photo).setOnClickListener(new MyOnClickListener() {
//                @Override
//                protected void onNoDoubleClick(View view) {
//                    selectPhotoDialog.dismiss();
//
//                    GPreviewBuilder.from(EditProfileActivity.this)
//                            .setDrag(true,0.6f)
//                            .setCurrentIndex(0)
//                            .setSingleData(new UserViewInfo(portray))
//                            .setType(GPreviewBuilder.IndicatorType.Number)
//                            .start();
////                    Intent intent=new Intent();
////                    intent.putExtra(Constant.IParam.userPhoto,"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqX8ia44zC0yhIrFK6LgicxysoSZwLwII2wIcFuOdZ6NicyIdsNFCZHE8tVmoq3iaYibwE3tICibEP5Hicuw/132");
////                    STActivity(intent,BigPhotoActivity.class);
//                }
//            });
            //从相册
            view.findViewById(R.id.tv_select_photo).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    selectPhotoDialog.dismiss();
                    selectPhotoOrCamera("photo");

                }
            });
            //拍照
            view.findViewById(R.id.tv_take_photo).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    selectPhotoDialog.dismiss();
                    selectPhotoOrCamera("camera");
                }
            });
            view.findViewById(R.id.tv_photo_cancle).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    selectPhotoDialog.dismiss();
                }
            });
            selectPhotoDialog = new BottomSheetDialog(mContext);
            selectPhotoDialog.setCanceledOnTouchOutside(true);
            selectPhotoDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            selectPhotoDialog.setContentView(view);
        }
        selectPhotoDialog.show();
    }

    // 选择拍照还是相册
    public void selectPhotoOrCamera(String takePhotoOrCamera) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        switch (takePhotoOrCamera) {
            case "photo":
                int limit = 1;
//                if (limit > 1) {
//                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
//                        takePhoto.onPickMultipleWithCrop(limit, getCropOptions());
//                    } else {
//                        takePhoto.onPickMultiple(limit);
//                    }
//                    return;
//                }
//                if (rgFrom.getCheckedRadioButtonId() == R.id.rbFile) {
//                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
//                        takePhoto.onPickFromDocumentsWithCrop(imageUri, getCropOptions());
//                    } else {
//                        takePhoto.onPickFromDocuments();
//                    }
//                    return;
//                } else {
//                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
//                    } else {
//                        takePhoto.onPickFromGallery();
//                    }
//                }
                break;
            case "camera":
                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                break;
            default:
                break;
        }
    }

    private void configCompress(TakePhoto takePhoto) {
        //设置压缩规则，最大500kb
        LubanOptions option = new LubanOptions.Builder()
                .setMaxHeight(800)
                .setMaxWidth(800)
                .setMaxSize(500 * 1024)
                .create();
        CompressConfig config = CompressConfig.ofLuban(option);
        takePhoto.onEnableCompress(config, false);
    }

    // 设置TakePhoto相关配置
    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(false);//是否使用TakePhoto自带的相册进行图片选择，默认不使用，但选择多张图片会使用
        builder.setCorrectImage(true);//是对拍的照片进行旋转角度纠正
        takePhoto.setTakePhotoOptions(builder.create());
    }

    //设置剪裁
    private CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(size).setAspectY(size);
        builder.setWithOwnCrop(false);
        return builder.create();
    }

    @Override
    public void registerCodeResult(BaseResponse baseResponse) {

    }

    public  Map addTokenMap() {
        Map<String, String> map = new HashMap<String, String>();
        String key = "pvxdm17jpe5cr";
        String secret = "pbPDAAqPawQFq";
        String nonce = ""+System.currentTimeMillis();
        String timestamp = ""+System.currentTimeMillis();
        String  signature = HashKit.hexSHA1(secret+nonce+timestamp);
        map.put("RC-App-Key", key);
        map.put("RC-Nonce", nonce);
        map.put("RC-Timestamp",timestamp);
        map.put("RC-Signature",signature);
        return map;
    }

    private void getToken(ResponseUserInfo userInfo){
        Map<String,String> map = new HashMap<>();
        map.put("userId",userInfo.getId());
        map.put("name",userInfo.getUserName());
        map.put("portraitUri",userUrl);
        OkHttpUtils.getInstance().post(map,"http://api-cn.ronghub.com/user/getToken.json",addTokenMap()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = null;
                try {
                    json = response.body().string();
                    if(json != null){
                        ResponseIMTokenInfo tokenInfo  = new Gson().fromJson(json,ResponseIMTokenInfo.class);
                        if(tokenInfo != null){
                            Message message = handler.obtainMessage();
                            message.obj = tokenInfo;
                            message.what = 1901;
                            handler.sendMessage(message);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


        @Override
    public void registerResult(ResponseUserInfo userInfo) {
        getToken(userInfo);
    }

    @Override
    public void getImTokenResult(ResponseIMTokenInfo tokenInfo) {

    }

    @Override
    public void insertimgResult(UserRegisterImgResult userRegisterImgResult) {
        showLoadSuccess();
        userUrl =userRegisterImgResult .getData().getUrl();
        GlideLoadUtils.getInstance().glideLoad(mContext,userUrl,ivUser,R.drawable.people);
        ArmsUtils.snackbarText("上传成功");
    }



    @Override
    public void showMessage(@NonNull String message) {
        showLoadSuccess();
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void takeSuccess(TResult result) {
        RXStart(new IOCallBack<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
//                    String imgStr = BitmapUtils.bitmapToString(result.getImages().get(0).getOriginalPath());
//                    Log.d("UserActivity", imgStr);
                    subscriber.onNext(result.getImages().get(0).getCompressPath());
//                    subscriber.onNext(imgStr);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }

            @Override
            public void onMyNext(String baseImg) {
                showLoading();
                List<MultipartBody.Part> parts = new ArrayList<>();
                File file = new File(baseImg);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("files", ".png", requestFile);
                parts.add(part);
                mPresenter.insertimg(parts);
//                ApiRequest.addImage(parts, new MyCallBack<ImageBean>(mContext) {
//                    @Override
//                    public void onSuccess(ImageBean imageBean) {
//                        portray = imageBean.getImageAddress();
//                        Glide.with(EditProfileActivity.this).load(portray).into(civMyImg);
//                    }
//                });
            }

            @Override
            public void onMyError(Throwable e) {
                super.onMyError(e);

//                showToastS("图片处理失败");
            }
        });
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void showErrorInfo(int code, String devMsg) {

    }

    @Override
    public void onSuccess(Object object, int type) {

    }

    @Override
    public BaseSupportActivity getCallBackActivity() {
        return this;
    }

}
