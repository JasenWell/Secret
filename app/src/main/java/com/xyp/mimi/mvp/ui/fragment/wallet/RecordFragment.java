package com.xyp.mimi.mvp.ui.fragment.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RecordFragment extends BaseSupportFragment {
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallet_all,container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
