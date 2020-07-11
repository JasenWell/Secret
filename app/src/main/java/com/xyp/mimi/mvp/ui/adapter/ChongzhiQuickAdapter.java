package com.xyp.mimi.mvp.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.chongzhi.Chongzhi;
import com.xyp.mimi.mvp.http.entity.product.Product;

import java.util.List;

//首页recycleview
public class ChongzhiQuickAdapter extends BaseQuickAdapter<Chongzhi.ConBean, BaseViewHolder> {


    public ChongzhiQuickAdapter(@Nullable List<Chongzhi.ConBean> data) {
       super(R.layout.adapter_item_chongzhi, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Chongzhi.ConBean item) {
        helper.setText(R.id.tv_number,helper.getPosition()+"");
        helper.setText(R.id.tv_name, item.getOrder_code());
        helper.setText(R.id.tv_price, item.getMoneys());
        helper.setText(R.id.tv_status, item.getRes());


    }


    public interface  OnClickDeleteListener{
        void onClick(Product.ConBean c);
    }

    private  OnClickDeleteListener onClickDeleteListener;


    public void setOnClickDeleteListener(OnClickDeleteListener onClickDeleteListener){
        this.onClickDeleteListener =onClickDeleteListener;
    }

}
