package com.xyp.mimi.mvp.listener;

import android.view.View;

import java.util.Calendar;

public abstract class MyOnClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 900;
    private long lastClickTime = 0L;

    public MyOnClickListener() {
    }

    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - this.lastClickTime > 900L) {
            this.lastClickTime = currentTime;
            this.onNoDoubleClick(v);
        }

    }

    protected abstract void onNoDoubleClick(View var1);
}
