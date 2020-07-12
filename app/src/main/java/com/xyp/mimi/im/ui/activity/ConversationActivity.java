package com.xyp.mimi.im.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.app.base.BaseSupportActivity;

public class ConversationActivity extends BaseSupportActivity {

    /**
     * 在会话类型为群组时：是否为群主
     */
    private boolean isGroupOwner;
    /**
     * 在会话类型为群组时：是否为群管理员
     */
    private boolean isGroupManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 显示软键盘
     */
    public void showSoftInput() {

    }

    /**
     * 在当前会话时是否为群主
     *
     * @return
     */
    public boolean isGroupOwner() {
        return isGroupOwner;
    }

    /**
     * 在当前会话时是否为群管理员
     *
     * @return
     */
    public boolean isGroupManager() {
        return isGroupManager;
    }
}
