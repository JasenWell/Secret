package com.xyp.mimi.mvp.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.NetData;

import java.util.List;

//首页recycleview
public class TestQuickAdapter extends BaseQuickAdapter<NetData.ConBean, BaseViewHolder> {

    public TestQuickAdapter(@Nullable List<NetData.ConBean> data) {
        super(R.layout.adapter_item_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NetData.ConBean item) {
        helper.setText(R.id.tv_number, helper.getPosition()+"");

    }
}
