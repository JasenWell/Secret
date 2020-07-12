package com.xyp.mimi.im.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.im.ui.adapter.ListWithSideBarBaseAdapter;
import com.xyp.mimi.im.ui.widget.SideBar;

import butterknife.BindView;

/**
 * 此 Fragment 带有 RecyclerView 和 SideBar 功能控件。 继承此 Fragment 后即可使用上面两个功能控件。
 * 当您不想使用 SideBar 控件时，可通过复写 {@link #isUseSideBar()} 方法并返回 false， 默认为 true。
 *
 * 此 Fragment 有一个 {@link #getAdapter()} 的抽象方法。 子类需要实现此方法， 并返回 {@link ListWithSideBarBaseAdapter}
 * 或及其子类。 可更具自己的需求去实现 adapter 的逻辑。
 *
 * @see ListWithSideBarBaseAdapter
 */
public abstract class ListWithSidebarBaseFragment extends BaseSupportFragment implements SideBar.OnTouchingLetterChangedListener {
    private static String TAG = "ListWithSidebarBaseFragment";

    private ListWithSideBarBaseAdapter adapter;

    @BindView(R.id.rv_contacts)
    RecyclerView recyclerView;

    @BindView(R.id.sv_sidebar)
    SideBar sideBar;

    @BindView(R.id.tv_group_dialog)
    TextView textView;

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_contacts_list, container, false);
    }

    @Override
    public void onInitView(Bundle savedInstanceState, Intent intent) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sideBar.setOnTouchingLetterChangedListener(this);
        sideBar.setTextView(textView);
        adapter = getAdapter();
        recyclerView.setAdapter(adapter);
        sideBar.setVisibility(isUseSideBar()? View.VISIBLE:View.GONE);
    }


    /**
     * 获取 RecyclerView
     * @return
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 获取侧边栏
     * @return
     */
    public SideBar getSideBar() {
        return sideBar;
    }


    /**
     * 右侧字母点击
     *
     * @param s
     */
    @Override
    public void onTouchingLetterChanged(String s) {
        if (adapter != null) {
            int position = adapter.getPositionForSection(s.charAt(0));
            if (position != -1) {
                recyclerView.scrollToPosition(position);
            }
        }
    }

    /**
     * 设置是否使用显示侧边栏。
     * 默认返回 true。 如果不想使用， 可复写此方法返回 false
     *
     * @return true 使用；false 不使用。
     */
    protected boolean isUseSideBar() {
        return true;
    }


    /**
     * 设置所使用到的 adapter 。 此方法必须要实现
     * @return
     */
    protected abstract ListWithSideBarBaseAdapter getAdapter();



}
