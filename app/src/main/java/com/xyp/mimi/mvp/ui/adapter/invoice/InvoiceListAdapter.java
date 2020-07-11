package com.xyp.mimi.mvp.ui.adapter.invoice;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.customview.MyCheckBox;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.invoice.InvoiceListResult;

import java.util.List;

public class InvoiceListAdapter extends BaseQuickAdapter<InvoiceListResult.DataBean, InvoiceListAdapter.MyViewHodler> {


    public InvoiceListAdapter(@Nullable List<InvoiceListResult.DataBean> data) {
        super(R.layout.item_invoice, data);
    }

    @Override
    protected void convert(MyViewHodler helper, InvoiceListResult.DataBean item) {
        helper.setText(R.id.tv_taitou,"抬头名称："+item.getHeaderName());
        helper.setText(R.id.tv_type, "类型："+item.getInvoiceTitleStr());
        if(item.getIsDefault() == 0){
            helper.setText(R.id.cb_default,"设为默认");
            helper.cbDefault.setChecked(false);
        }else{
            helper.setText(R.id.cb_default,"已设为默认");
            helper.cbDefault.setChecked(true);
        }

        if(item.getInvoiceTitle() == 2){
            helper.setText(R.id.tv_shuihao, "税号："+item.getTaxNumber());
            helper.getView(R.id.tv_shuihao).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.tv_shuihao).setVisibility(View.GONE);
        }

        //设置默认
        helper.getView(R.id.cb_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickInvoiceDefaultListener!=null){
                    onClickInvoiceDefaultListener.setDefaultInvoice(item.getId());
                }
            }
        });

        //删除
        helper.getView(R.id.tv_invoice_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickInvoiceDeleteListener!=null){
                    onClickInvoiceDeleteListener.deleteInvoice(item.getId());
                }
            }
        });

        //编辑
        helper.getView(R.id.tv_invoice_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickInvoiceEditorListener!=null){
                    onClickInvoiceEditorListener.editorInvoice(item);
                }
            }
        });

    }
    class MyViewHodler extends BaseViewHolder{
        private MyCheckBox  cbDefault;
        public MyViewHodler(View view) {
            super(view);
            cbDefault = view.findViewById(R.id.cb_default);
        }
    }


    //删除
    public interface  OnClickInvoiceDeleteListener{
        void deleteInvoice(int id);
    }

    private InvoiceListAdapter.OnClickInvoiceDeleteListener onClickInvoiceDeleteListener;


    public void setOnInvoiceDeleteListener(InvoiceListAdapter.OnClickInvoiceDeleteListener onInvoiceDeleteListener){
        this.onClickInvoiceDeleteListener =onInvoiceDeleteListener;
    }

    //编辑
    public interface  OnClickInvoiceEditorListener{
        void editorInvoice(InvoiceListResult.DataBean dataBean);
    }

    private InvoiceListAdapter.OnClickInvoiceEditorListener onClickInvoiceEditorListener;


    public void setOnInvoiceEditorListener(InvoiceListAdapter.OnClickInvoiceEditorListener onInvoiceEditorListener){
        this.onClickInvoiceEditorListener =onInvoiceEditorListener;
    }


    //设置默认

    public interface  OnClickInvoiceDefaultListener{
        void setDefaultInvoice(int id);
    }

    private InvoiceListAdapter.OnClickInvoiceDefaultListener onClickInvoiceDefaultListener;


    public void setOnInvoiceDefaultListener(InvoiceListAdapter.OnClickInvoiceDefaultListener onInvoiceDefaultListener){
        this.onClickInvoiceDefaultListener =onInvoiceDefaultListener;
    }



}
