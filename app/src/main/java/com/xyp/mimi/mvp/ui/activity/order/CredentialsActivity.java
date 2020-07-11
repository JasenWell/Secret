package com.xyp.mimi.mvp.ui.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.baseclass.utils.ActUtils;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.utils.P;
import java.util.ArrayList;
import java.util.List;
import butterknife.OnClick;

/**
 *
 *  审核信息提交
 *
 */

public class CredentialsActivity extends BaseSupportActivity {

    List<String> picList = new ArrayList<>();//图片地址列表
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_pingzheng_information;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @OnClick({R.id.iv_problem1,R.id.iv_problem2,R.id.iv_problem3,R.id.iv_problem4})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_problem1:
                picList.add("pic_problem1");
                intent.putStringArrayListExtra("imageList", (ArrayList<String>) picList);
                intent.putExtra(P.START_ITEM_POSITION, 1);
                intent.putExtra(P.START_IAMGE_POSITION, 1);
                intent.putExtra("from_local",true);
                ActUtils.STActivity(mContext, intent, ImagePreviewActivity.class);
                break;
            case R.id.iv_problem2:
                picList.add("pic_problem2");
                intent.putStringArrayListExtra("imageList", (ArrayList<String>) picList);
                intent.putExtra(P.START_ITEM_POSITION, 1);
                intent.putExtra(P.START_IAMGE_POSITION, 1);
                intent.putExtra("from_local",true);
                ActUtils.STActivity(mContext, intent, ImagePreviewActivity.class);
                break;
            case R.id.iv_problem3:
                picList.add("pic_problem3");
                intent.putStringArrayListExtra("imageList", (ArrayList<String>) picList);
                intent.putExtra(P.START_ITEM_POSITION, 1);
                intent.putExtra(P.START_IAMGE_POSITION, 1);
                intent.putExtra("from_local",true);
                ActUtils.STActivity(mContext, intent, ImagePreviewActivity.class);
                break;
            case R.id.iv_problem4:
                picList.add("pic_problem4");
                intent.putStringArrayListExtra("imageList", (ArrayList<String>) picList);
                intent.putExtra(P.START_ITEM_POSITION, 1);
                intent.putExtra(P.START_IAMGE_POSITION, 1);
                intent.putExtra("from_local",true);
                ActUtils.STActivity(mContext, intent, ImagePreviewActivity.class);
                break;
        }
    }
}
