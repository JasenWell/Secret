package com.xyp.mimi.mvp.ui.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.mvp.ui.adapter.order.CustomViewPager;
import com.xyp.mimi.mvp.ui.adapter.order.ImagePreviewAdapter;
import com.xyp.mimi.mvp.utils.P;

import java.util.List;

import butterknife.BindView;

/**
 * 图片预览 Activity
 */
public class ImagePreviewActivity extends BaseSupportActivity {

    @BindView(R.id.imageBrowseViewPager)
    CustomViewPager viewPager;
//    @BindView(R.id.main_linear)
//    LinearLayout main_linear;
    private int itemPosition;
    private List<String> imageList;
    private boolean mIsReturning;
    private int mStartPosition;
    private int mCurrentPosition;
    private boolean fromLocal;
    private ImagePreviewAdapter adapter;


//    private void initShareElement() {
//        setEnterSharedElementCallback(mCallback);
//    }

    private void setListener() {
//        main_linear.getChildAt(mCurrentPosition).setEnabled(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                hideAllIndicator(position);
//                main_linear.getChildAt(position).setEnabled(true);
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);
            }
        });
    }

    private void hideAllIndicator(int position) {
        for (int i = 0; i < imageList.size(); i++) {
            if (i != position) {
//                main_linear.getChildAt(i).setEnabled(false);
            }
        }
    }

//    @Override
//    protected void initImmersionBar() {
//        super.initImmersionBar();
//        mImmersionBar.statusBarColor(R.color.black)
//                .hideBar(BarHide.FLAG_HIDE_BAR)
//                .init();
//    }


    private void renderView() {
        if (imageList == null) return;
//        if (imageList.size() == 1) {
//            main_linear.setVisibility(View.GONE);
//        } else {
//            main_linear.setVisibility(View.VISIBLE);
//        }
        adapter = new ImagePreviewAdapter(this, imageList, itemPosition,fromLocal);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(mCurrentPosition);
    }

    private void getIntentData() {
        if (getIntent() != null) {
            mStartPosition = getIntent().getIntExtra(P.START_IAMGE_POSITION, 0);
            mCurrentPosition = mStartPosition;
            itemPosition = getIntent().getIntExtra(P.START_ITEM_POSITION, 0);
            fromLocal = getIntent().getBooleanExtra("from_local",false);
            imageList = getIntent().getStringArrayListExtra("imageList");
        }
    }

    /**
     * 获取数据
     */
    private void getData() {
        View view;
        for (String pic : imageList) {
            //创建底部指示器(小圆点)
            view = new View(ImagePreviewActivity.this);
//            view.setBackgroundResource(R.drawable.indicator);
            view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            //设置间隔
            if (!pic.equals(imageList.get(0))) {
                layoutParams.leftMargin = 20;
            }
            //添加到LinearLayout
//            main_linear.addView(view, layoutParams);
        }
    }


    @Override
    public void finishAfterTransition() {
        mIsReturning = true;
        Intent data = new Intent();
        data.putExtra(P.START_IAMGE_POSITION, mStartPosition);
        data.putExtra(P.CURRENT_IAMGE_POSITION, mCurrentPosition);
        data.putExtra(P.CURRENT_ITEM_POSITION, itemPosition);
        setResult(RESULT_OK, data);
        super.finishAfterTransition();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_image_preview1;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getIntentData();
        renderView();
        getData();
        setListener();
    }
//    private final SharedElementCallback mCallback = new SharedElementCallback() {
//
//        @Override
//        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
//            if (mIsReturning) {
//                ImageView sharedElement = adapter.getPhotoView();
//                if (sharedElement == null) {
//                    names.clear();
//                    sharedElements.clear();
//                } else if (mStartPosition != mCurrentPosition) {
//                    names.clear();
//                    names.add(sharedElement.getTransitionName());
//                    sharedElements.clear();
//                    sharedElements.put(sharedElement.getTransitionName(), sharedElement);
//                }
//            }
//        }
//    };
}
