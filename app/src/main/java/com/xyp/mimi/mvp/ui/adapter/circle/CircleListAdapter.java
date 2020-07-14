package com.xyp.mimi.mvp.ui.adapter.circle;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.Constant;
import com.xyp.mimi.mvp.http.entity.address.AddressListResult;
import com.xyp.mimi.mvp.ui.activity.address.AddAddressActivity;
import com.xyp.mimi.mvp.ui.adapter.address.AddressAdapter;

import java.util.List;

public class CircleListAdapter extends BaseQuickAdapter<AddressListResult.DataBean, BaseViewHolder> {


    public CircleListAdapter(@Nullable List<AddressListResult.DataBean> data) {
       super(R.layout.item_address, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressListResult.DataBean bean) {


        helper.setText(R.id.tv_address_name,bean.getConsignee())
                .setText(R.id.tv_address_phone,bean.getMobile())
                .setText(R.id.tv_address_detail,bean.getAddress());


//        helper.setText(R.id.tv_taitou,"抬头名称："+item.getHeaderName());
//        helper.setText(R.id.tv_type, "类型："+item.getInvoiceTitleStr());
        if(bean.getIsDefault() == 1){
             helper.getView(R.id.tv_default).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.tv_default).setVisibility(View.GONE);
        }
//
//        if(item.getInvoiceTitle() == 2){
//            helper.setText(R.id.tv_shuihao, "税号："+item.getTaxNumber());
//        }else{
//            helper.getView(R.id.tv_shuihao).setVisibility(View.GONE);
//        }
       //编辑
        helper.getView(R.id.address_editor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, AddAddressActivity.class);
                intent.putExtra(Constant.editAddress,true);
                intent.putExtra(Constant.Address, bean);
                mContext.startActivity(intent);
            }
        });

        //删除
        helper.getView(R.id.address_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickAddressDeleteListener!=null){
                    onClickAddressDeleteListener.deleteAddress(bean.getId());
                }
            }
        });
//        //设置默认
//        helper.getView(R.id.cb_default).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(onClickInvoiceDefaultListener!=null){
//                    onClickInvoiceDefaultListener.setDefaultInvoice(item.getId());
//                }
//            }
//        });
//
//        //删除
//        helper.getView(R.id.tv_invoice_delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(onClickInvoiceDeleteListener!=null){
//                    onClickInvoiceDeleteListener.deleteInvoice(item.getId());
//                }
//            }
//        });
//
//        //编辑
//        helper.getView(R.id.tv_invoice_edit).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(onClickInvoiceEditorListener!=null){
//                    onClickInvoiceEditorListener.editorInvoice(item);
//                }
//            }
//        });

    }
    //删除
    public interface  OnClickAddressDeleteListener{
        void deleteAddress(int id);
    }

    private AddressAdapter.OnClickAddressDeleteListener onClickAddressDeleteListener;


    public void setOnAddressDeleteListener(AddressAdapter.OnClickAddressDeleteListener onAddressDeleteListener){
        this.onClickAddressDeleteListener =onAddressDeleteListener;
    }


    //设置默认

    public interface  OnClickInvoiceDefaultListener{
        void setDefaultInvoice(int id);
    }

    private CircleListAdapter.OnClickInvoiceDefaultListener onClickInvoiceDefaultListener;


    public void setOnInvoiceDefaultListener(CircleListAdapter.OnClickInvoiceDefaultListener onInvoiceDefaultListener){
        this.onClickInvoiceDefaultListener =onInvoiceDefaultListener;
    }



}
