package com.xyp.mimi.mvp.ui.adapter.order;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.Constant;
import com.xyp.mimi.mvp.http.entity.order.OrderListResult;
import com.xyp.mimi.mvp.ui.activity.order.OrderDetailActivity;

import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<OrderListResult.DataBean,BaseViewHolder> {

    public OrderListAdapter(@Nullable List<OrderListResult.DataBean> data) {
        super(R.layout.item_order_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListResult.DataBean item) {
        helper.setText(R.id.tv_order_title,item.getShopName())
                .setText(R.id.tv_order_type,item.getStatusName())
                .setText(R.id.tv_order_total,"需付款¥"+item.getTotal());
        if(item.getStatusName().equals("待付款")){
            helper.getView(R.id.tv_order_quxiao).setVisibility(View.VISIBLE);

            helper.getView(R.id.tv_order_zhifu).setVisibility(View.VISIBLE);
        }

        //删除
        helper.getView(R.id.tv_order_quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickCancelListener!=null){
                    onClickCancelListener.cancelOrder(item.getOrderNumber());
                }
            }
        });

        helper.getView(R.id.ll_my_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra(Constant.OrderNo, item.getOrderNumber());
                mContext.startActivity(intent);

            }
        });

        RecyclerView rv_order_goods = (RecyclerView) helper.getView(R.id.rv_order_goods);
        rv_order_goods.setNestedScrollingEnabled(false);
        rv_order_goods.setLayoutManager(new LinearLayoutManager(mContext));
        rv_order_goods.setAdapter(new OrderListChildAdapter(item.getOrderDetails()));


    }


    //删除
    public interface  OnClickCancelListener{
        void cancelOrder(String orderNo);
    }

    private OrderListAdapter.OnClickCancelListener onClickCancelListener;


    public void setOnCancelListener(OrderListAdapter.OnClickCancelListener onCancelListener){
        this.onClickCancelListener =onCancelListener;
    }

}
