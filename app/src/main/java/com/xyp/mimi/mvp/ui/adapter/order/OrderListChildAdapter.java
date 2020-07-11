package com.xyp.mimi.mvp.ui.adapter.order;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.order.OrderListResult;
import com.xyp.mimi.mvp.utils.GlideLoadUtils;

import java.util.List;

public class OrderListChildAdapter extends BaseQuickAdapter<OrderListResult.DataBean.OrderDetailsBean,BaseViewHolder> {


    public OrderListChildAdapter(@Nullable List<OrderListResult.DataBean.OrderDetailsBean> data) {
        super(R.layout.item_order_detail, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, OrderListResult.DataBean.OrderDetailsBean item) {
        GlideLoadUtils.getInstance().glideLoad(mContext, item.getPicNo(), helper.getView(R.id.iv_order_goods), R.drawable.default_image);
        helper.setText(R.id.tv_order_goods_name,item.getProductName())
                .setText(R.id.tv_order_goods_price, "¥"+item.getUnitPrice())
                .setText(R.id.tv_order_goods_guige,item.getSpecText())
                .setText(R.id.tv_order_goods_num,"x"+item.getNumber());

    }
}
