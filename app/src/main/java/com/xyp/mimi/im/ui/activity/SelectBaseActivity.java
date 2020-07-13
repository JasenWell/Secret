package com.xyp.mimi.im.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectBaseActivity extends TitleAndSearchBaseActivity {
    @BindView(R.id.tv_search_count)
    TextView tvSelectCount;

    @BindView(R.id.tv_search_confirm)
    TextView tvConfirm;

    @BindView(R.id.select_bottom_layout)
    View bottomLayout;

    @OnClick({R.id.tv_search_confirm,R.id.tv_search_count})
    public void onViewClicked(View view) {
        if(view.getId() == R.id.tv_search_confirm){
            onConfirmClick();
        }else if(view.getId() == R.id.tv_search_count){
            onSelectedDetail();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setClildView(R.layout.activity_select_base_layout);
        // 若没有开启搜索则隐藏搜索框
        if(!isSearchable()){
            getSearchTextView().setVisibility(View.GONE);
        }
    }

    /**
     * 重写此方法来实现搜索刷新逻辑
     * 当标题栏中的输入框输入文字时回调此方法
     * 此方法会在输入文字一定时间延迟内没有再输入文字时会回调
     * @param keyword
     */
    @Override
    public void onSearch(String keyword) {
    }

    /**
     * 覆盖获取选择结果
     *
     * @param selectIds
     */
    protected void onConfirmClicked(ArrayList<String> selectIds, ArrayList<String> selectGroups) {

    }

    /**
     * 右下角点击确定
     */
    protected void onConfirmClick() {

    }

    /**
     * 点击选择禅看选择详情
     */
    protected void onSelectedDetail() {

    }

    /**
     * 是否可以搜索
     * 重写此方法返回 true 来启用搜索
     * 需要实现 onSearch 方法具体实现搜索逻辑
     *
     * @return
     */
    protected boolean isSearchable(){
        return false;
    }

    protected void showBottomSelectedCount(boolean isShow){
        if(isShow){
            String shownText = tvSelectCount.getText().toString();
            if(!TextUtils.isEmpty(shownText)){
                bottomLayout.setVisibility(View.VISIBLE);
            } else {
                bottomLayout.setVisibility(View.GONE);
            }
        } else {
            bottomLayout.setVisibility(View.GONE);
        }
    }

    protected void updateBottomCount(int groupCount, int userCount) {
        String userOnly = getString(R.string.seal_selected_contacts_count);
        String groupOnly = getString(R.string.seal_selected_only_group);
        String both = getString(R.string.seal_selected_groups_count);

        String countString = "";
        if (groupCount == 0 && userCount == 0) {
            bottomLayout.setVisibility(View.GONE);
            return;
        } else {
            bottomLayout.setVisibility(View.VISIBLE);
        }

        if (groupCount == 0 && userCount > 0) {
            countString = String.format(userOnly, userCount);
        } else if (groupCount > 0 && userCount == 0) {
            countString = String.format(groupOnly, groupCount);
        } else {
            countString = String.format(both, userCount, groupCount);
        }
        tvSelectCount.setText(countString);
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;//R.layout.activity_select_base_layout
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
