package com.xyp.mimi.mvp.http.entity.product;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by administartor on 2017/9/4.
 */

public class TabEntity implements CustomTabEntity {
    String title;
    public TabEntity(String title) {
        this.title=title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
