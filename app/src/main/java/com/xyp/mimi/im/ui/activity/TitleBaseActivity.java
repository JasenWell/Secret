package com.xyp.mimi.im.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;

import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.im.ui.view.SealTitleBar;

import butterknife.BindView;

public abstract class TitleBaseActivity extends BaseSupportActivity {
    @BindView(R.id.layout_container)
    ViewFlipper contentContainer;

    @BindView(R.id.title_bar)
    SealTitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        titleBar =  findViewById(R.id.title_bar);
        contentContainer = findViewById(R.id.layout_container);
        setTitleBarType(SealTitleBar.Type.NORMAL);
        getTitleBar().setOnBtnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initChildView(){

    }

    @Override
    public void setContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        contentContainer.addView(view, lp);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }

    public SealTitleBar getTitleBar() {
        return titleBar;
    }

    public void setTitleBarType(SealTitleBar.Type type) {
        titleBar.setType(type);
    }

    @Override
    public void finish() {
        super.finish();
        hideInputKeyboard();
    }
}
