package com.xyp.mimi.im.ui.fragment;

import androidx.lifecycle.ViewModelProviders;

import com.xyp.mimi.im.viewmodel.SelectBaseViewModel;
import com.xyp.mimi.im.viewmodel.SelectSingleViewModel;

public class SelectSingleFragment extends SelectBaseFragment {

    private static final String TAG = "SelectSingleFragment";
    @Override
    protected SelectBaseViewModel getViewModel() {
        return ViewModelProviders.of(this).get(SelectSingleViewModel.class);
    }
}
