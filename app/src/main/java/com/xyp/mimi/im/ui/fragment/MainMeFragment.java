package com.xyp.mimi.im.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;

import com.xyp.mimi.R;
import com.xyp.mimi.im.common.IntentExtra;
import com.xyp.mimi.im.db.model.UserInfo;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.model.VersionInfo;
import com.xyp.mimi.im.model.qrcode.QrCodeDisplayType;
import com.xyp.mimi.im.ui.view.SettingItemView;
import com.xyp.mimi.im.ui.view.UserInfoItemView;
import com.xyp.mimi.im.utils.ImageLoaderUtils;
import com.xyp.mimi.im.viewmodel.AppViewModel;
import com.xyp.mimi.im.viewmodel.UserInfoViewModel;

import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utilities.LangUtils;
import io.rong.imlib.model.CSCustomServiceInfo;

public class MainMeFragment extends BaseSupportFragment {

    private SettingItemView sivAbout;
    private UserInfoItemView uivUserInfo;
    private AppViewModel appViewModel;
    private SettingItemView sivLanguage;


    @Override
    public void onInitView(Bundle savedInstanceState, Intent intent) {

    }

    @Override
    public void onInitViewModel() {
        UserInfoViewModel userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);

        userInfoViewModel.getUserInfo().observe(this, new Observer<Resource<UserInfo>>() {
            @Override
            public void onChanged(Resource<UserInfo> resource) {
                if (resource.data != null) {
                    UserInfo info = resource.data;
                    uivUserInfo.setName(info.getName());
                    ImageLoaderUtils.displayUserPortraitImage(info.getPortraitUri(), uivUserInfo.getHeaderImageView());
                }

            }
        });

        appViewModel.getHasNewVersion().observe(this, new Observer<Resource<VersionInfo.AndroidVersion>>() {
            @Override
            public void onChanged(Resource<VersionInfo.AndroidVersion> resource) {
                if (resource.status == Status.SUCCESS && resource.data != null) {
                    sivAbout.setTagImageVisibility(View.VISIBLE);
                }
            }
        });

        appViewModel.getLanguageLocal().observe(this, new Observer<LangUtils.RCLocale>() {
            @Override
            public void onChanged(LangUtils.RCLocale rcLocale) {
//                if (rcLocale == LangUtils.RCLocale.LOCALE_US) {
//                    sivLanguage.setValue(R.string.lang_english);
//                } else {
//                    sivLanguage.setValue(R.string.lang_chs);
//                }
            }
        });
    }

    @OnClick({R.id.uiv_userinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.siv_setting_qrcode:
//                Intent qrCodeIntent = new Intent(getActivity(), QrCodeDisplayActivity.class);
//                qrCodeIntent.putExtra(IntentExtra.STR_TARGET_ID, RongIM.getInstance().getCurrentUserId());
//                qrCodeIntent.putExtra(IntentExtra.SERIA_QRCODE_DISPLAY_TYPE, QrCodeDisplayType.PRIVATE);
//                startActivity(qrCodeIntent);
//                break;
//            case R.id.uiv_userinfo:
//                Intent intentUserInfo = new Intent(getActivity(), MyAccountActivity.class);
//                startActivity(intentUserInfo);
//                break;
//            case R.id.siv_setting_account:
//                startActivity(new Intent(getActivity(), AccountSettingActivity.class));
//
//                break;
//            case R.id.siv_language:
//                startActivity(new Intent(getActivity(), ChangeLanguageActivity.class));
//
//                break;
//            case R.id.siv_feedback:
//                CSCustomServiceInfo.Builder builder = new CSCustomServiceInfo.Builder();
//                builder.province(getString(R.string.beijing));
//                builder.city(getString(R.string.beijing));
//                io.rong.imlib.model.UserInfo info = RongUserInfoManager.getInstance().getUserInfo(RongIM.getInstance().getCurrentUserId());
//                if (info != null && !TextUtils.isEmpty(info.getName())) {
//                    builder.name(info.getName());
//                }
//                //佳信客服配置
//                builder.referrer("10001");
//                RongIM.getInstance().startCustomerServiceChat(getActivity(), "service", getString(R.string.seal_main_mine_online_custom_service), builder.build());
//                break;
//            case R.id.siv_about:
//                Intent intent = new Intent(getActivity(), AboutSealTalkActivity.class);
//                Resource<VersionInfo.AndroidVersion> resource = appViewModel.getHasNewVersion().getValue();
//                if (resource != null && resource.data != null && !TextUtils.isEmpty(resource.data.getUrl())) {
//                    intent.putExtra(IntentExtra.URL, resource.data.getUrl());
//                }
//                startActivity(intent);
//                break;
            default:
                break;
        }
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_me, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
