package com.xyp.mimi.mvp.ui.adapter.order;


import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.Constant;
import com.xyp.mimi.mvp.http.entity.order.OrderDetailResult;
import com.xyp.mimi.mvp.ui.activity.market.GoodsDetailActivity;
import com.xyp.mimi.mvp.utils.GlideLoadUtils;
import java.util.List;

public class OrderDetailListAdapter extends BaseQuickAdapter<OrderDetailResult.DataBean.OrderDetailsBean,BaseViewHolder> {

    public OrderDetailListAdapter(@Nullable List<OrderDetailResult.DataBean.OrderDetailsBean> data) {
        super(R.layout.item_order_detail, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, OrderDetailResult.DataBean.OrderDetailsBean item) {
        GlideLoadUtils.getInstance().glideLoad(mContext, item.getPicNo(), helper.getView(R.id.iv_order_goods), R.drawable.default_image);
        helper.setText(R.id.tv_order_goods_name,item.getProductName())
                .setText(R.id.tv_order_goods_price, "¥"+item.getUnitPrice())
                .setText(R.id.tv_order_goods_guige,item.getSpecText())
                .setText(R.id.tv_order_goods_num,"x"+item.getNumber());


        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra(Constant.GoodsId, item.getProductId());
                mContext.startActivity(intent);
            }
        });
    }


    //删除
    public interface  OnClickCancelListener{
        void cancelOrder(String orderNo);
    }

    private OrderDetailListAdapter.OnClickCancelListener onClickCancelListener;


    public void setOnCancelListener(OrderDetailListAdapter.OnClickCancelListener onCancelListener){
        this.onClickCancelListener =onCancelListener;
    }

}
