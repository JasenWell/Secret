package com.xyp.mimi.mvp.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.product.Product;
import java.util.List;

//首页recycleview
public class BindRecommendQuickAdapter extends BaseQuickAdapter<Product.ConBean, BaseViewHolder> {


    public BindRecommendQuickAdapter(@Nullable List<Product.ConBean> data) {
        super(R.layout.adapter_item_bind_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product.ConBean item) {
        helper.setText(R.id.tv_number,helper.getPosition()+"");
        helper.setText(R.id.tv_name, item.getAcc());
        helper.setText(R.id.tv_app_id, item.getAppid());
        helper.getView(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickDeleteListener!=null){
                    onClickDeleteListener.onClick(item);
                }
            }
        });

    }


    public interface  OnClickDeleteListener{
        void onClick(Product.ConBean c);
    }

    private  OnClickDeleteListener onClickDeleteListener;


    public void setOnClickDeleteListener(OnClickDeleteListener onClickDeleteListener){
        this.onClickDeleteListener =onClickDeleteListener;
    }

}
