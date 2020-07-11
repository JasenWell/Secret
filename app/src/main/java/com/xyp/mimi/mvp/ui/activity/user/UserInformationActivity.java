package com.xyp.mimi.mvp.ui.activity.user;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.rx.IOCallBack;
import com.github.customview.MyImageView;
import com.github.customview.MyTextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.di.component.user.DaggerUserComponent;
import com.xyp.mimi.di.module.user.UserModule;
import com.xyp.mimi.mvp.contract.user.UserContract;
import com.xyp.mimi.mvp.event.RefreshUserInfoEvent;
import com.xyp.mimi.mvp.http.entity.BaseResponse;
import com.xyp.mimi.mvp.http.entity.user.UserInfoEditPost;
import com.xyp.mimi.mvp.http.entity.user.UserInformationResult;
import com.xyp.mimi.mvp.http.entity.user.UserPost;
import com.xyp.mimi.mvp.http.entity.user.UserPhotoPost;
import com.xyp.mimi.mvp.http.entity.user.UserPhotoResult;
import com.xyp.mimi.mvp.presenter.user.UserPresenter;
import com.xyp.mimi.mvp.utils.AppConstant;
import com.xyp.mimi.mvp.utils.BitmapUtils;
import com.xyp.mimi.mvp.utils.GlideLoadUtils;
import com.xyp.mimi.mvp.view.datepicker.DataPickerDialog;
import com.xyp.mimi.mvp.view.datepicker.DatePickerDialog;
import com.xyp.mimi.mvp.view.datepicker.DateUtil;

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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class UserInformationActivity extends BaseSupportActivity<UserPresenter> implements UserContract.View, TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.iv_user)
    MyImageView ivUser;
    @BindView(R.id.tv_name)
    MyTextView tvName;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_sex) //性别
            TextView tvSex;
    @BindView(R.id.tv_birthday) //生日
            TextView tvBirthday;
    @BindView(R.id.tv_inviter)
    TextView tvInviter;

    private BottomSheetDialog selectPhotoDialog;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private Dialog birthdayDialog, sixDialog;
    String userId;
    String token;
    String nickName;
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
        DaggerUserComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_user_information; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshUserInfoEvent event) {
        getData();
    }

    private void getData() {
        mPresenter.User(new UserPost(userId, token));
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setAppTitle("个人资料");
        size = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        userId = SPUtils.getInstance().getString(AppConstant.User.USER_ID);
        token = SPUtils.getInstance().getString(AppConstant.User.TOKEN);
        getData();
    }



    @OnClick({R.id.iv_user,R.id.rl_name,R.id.rl_sex,R.id.rl_birthday,R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user:
                showSelectPhotoDialog();
                break;

            case R.id.rl_name:
                Intent intent = new Intent();
                intent.putExtra("NICKNAME", nickName);
                STActivity(intent, UserNameActivity.class);
                break;
            case R.id.rl_sex:
                showSexDialog();
                break;
            case R.id.rl_birthday://生日
                showBithdayDialog(DateUtil.getDateForString("1990-01-01"));
                break;
            case R.id.tv_save://保存
                String birthday = tvBirthday.getText().toString();
                int sex;
                if(tvSex.getText().toString().equals("保密")){
                    sex = 0;
                }else if(tvSex.getText().toString().equals("女")){
                    sex = 1;
                }else{
                    sex = 2;
                }
                mPresenter.editUserInfo(new UserInfoEditPost(userId,token,nickName,birthday,sex));
                break;
        }
    }


    //选择性别
    private void showSexDialog() {
        List<String> mlist = Arrays.asList("保密","男", "女");
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        sixDialog = builder.setData(mlist).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        tvSex.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {
                    }
                }).create();

        sixDialog.show();
    }

    //生日
    private void showBithdayDialog(List<Integer> date) {

        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                tvBirthday.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
            }

            @Override
            public void onCancel() {
            }
        })

                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);

        builder.setMaxYear(DateUtil.getYear());
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        birthdayDialog = builder.create();
        birthdayDialog.show();
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
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void takeSuccess(TResult result) {
        RXStart(new IOCallBack<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String imgStr = BitmapUtils.bitmapToString(result.getImages().get(0).getCompressPath());
                    Log.d("UserActivity", imgStr);
                    subscriber.onNext(imgStr);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }

            @Override
            public void onMyNext(String baseImg) {
                showLoading();
//                List<MultipartBody.Part> parts = new ArrayList<>();
//                File file = new File(baseImg);
//                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                MultipartBody.Part part = MultipartBody.Part.createFormData("file", ".png", requestFile);
//                parts.add(part);

                mPresenter.upload(new UserPhotoPost(userId, token, baseImg));

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

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
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
    public void uploadPhotoResult(UserPhotoResult userPhotoResult) {
        showLoadSuccess();
        GlideLoadUtils.getInstance().glideLoad(mContext, userPhotoResult.getData(), ivUser, R.drawable.sj);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void informationResult(UserInformationResult UserResult) {
        showLoadSuccess();
        GlideLoadUtils.getInstance().glideLoad(mContext, UserResult.getData().getAvatar(), ivUser, R.drawable.sj);
        tvName.setText(UserResult.getData().getNickName());
        nickName = UserResult.getData().getNickName();//赋值

        tvId.setText(UserResult.getData().getId()+"");
        tvPhone.setText(UserResult.getData().getMobile());
        if(UserResult.getData().getSex()== 0){
            tvSex.setText("保密");
        }else if(UserResult.getData().getSex()== 1){
            tvSex.setText("女");
        }else{
            tvSex.setText("男");
        }
        tvBirthday.setText(UserResult.getData().getBirthday());
        tvInviter.setText(UserResult.getData().getInviter());
    }

    //编辑资料
    @Override
    public void userInfoEditResult(BaseResponse baseResponse) {
        showLoadSuccess();
        finish();
    }

}
