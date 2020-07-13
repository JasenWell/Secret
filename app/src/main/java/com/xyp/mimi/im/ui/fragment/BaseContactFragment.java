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

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;
import com.xyp.mimi.im.ui.widget.SideBar;

import butterknife.BindView;

public class BaseContactFragment extends BaseSupportFragment implements SideBar.OnTouchingLetterChangedListener {
    private static String TAG = "BaseContactFragment";

    @BindView(R.id.rv_contacts)
    RecyclerView recyclerView;

    @BindView(R.id.sv_sidebar)
    SideBar sideBar;

    @BindView(R.id.tv_group_dialog)
    TextView textView;


    /**
     * 右侧字母点击
     *
     * @param s
     */
    @Override
    public void onTouchingLetterChanged(String s) {

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_contacts_list, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sideBar.setOnTouchingLetterChangedListener(this);
        sideBar.setTextView(textView);
    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
