package com.xyp.mimi.mvp.ui.adapter.cart;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.cart.CartListResult;
import java.util.List;

public class CartListAdapter extends BaseQuickAdapter<CartListResult.DataBean.CartListBean, BaseViewHolder> {

    private boolean isEdit;

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public CartListAdapter(@Nullable List<CartListResult.DataBean.CartListBean> data) {
        super(R.layout.item_shopping_cart_father, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CartListResult.DataBean.CartListBean item) {
        helper.setText(R.id.tv_shop_title,item.getShopName());
        RecyclerView rv_order_goods = (RecyclerView) helper.getView(R.id.rv_goods_list);
        rv_order_goods.setNestedScrollingEnabled(false);
        rv_order_goods.setLayoutManager(new LinearLayoutManager(mContext));
        CartListChildAdapter  adapter = new CartListChildAdapter(mContext, R.layout.item_shopping_cart_child);
        rv_order_goods.setAdapter(adapter);
        adapter.setEdit(isEdit);
        adapter.setList(item.getProData());
        adapter.notifyDataSetChanged();
    }

}
