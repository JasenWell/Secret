package com.xyp.mimi.mvp.ui.adapter.address;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.Constant;
import com.xyp.mimi.mvp.http.entity.address.AddressListResult;
import com.xyp.mimi.mvp.ui.activity.address.AddAddressActivity;


public class AddressAdapter extends BaseRecyclerAdapter<AddressListResult.DataBean> {

    Context context;

    public AddressAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
        context= mContext;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerViewHolder holder = new RecyclerViewHolder(this.mContext, this.mInflater.inflate(R.layout.item_address, parent, false));
        return holder;

    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void bindData(RecyclerViewHolder holder, int i, AddressListResult.DataBean bean) {
        holder.setText(R.id.tv_address_name,bean.getConsignee())
                .setText(R.id.tv_address_phone,bean.getMobile())
                .setText(R.id.tv_address_detail,bean.getAddress());


        holder.getView(R.id.address_editor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AddAddressActivity.class);
                intent.putExtra(Constant.editAddress,true);
                intent.putExtra(Constant.Address, bean);
                context.startActivity(intent);
            }
        });

        //删除
        holder.getView(R.id.address_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickAddressDeleteListener!=null){
                    onClickAddressDeleteListener.deleteAddress(bean.getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }


    class MyViewHodler extends BaseViewHolder {
        private TextView name,phone,detail;
        public MyViewHodler(View view) {
            super(view);
            name = view.findViewById(R.id.tv_address_name);
            phone = view.findViewById(R.id.tv_address_phone);
            detail = view.findViewById(R.id.tv_address_detail);

        }

    }

    //删除
    public interface  OnClickAddressDeleteListener{
        void deleteAddress(int id);
    }

    private AddressAdapter.OnClickAddressDeleteListener onClickAddressDeleteListener;


    public void setOnAddressDeleteListener(AddressAdapter.OnClickAddressDeleteListener onAddressDeleteListener){
        this.onClickAddressDeleteListener =onAddressDeleteListener;
    }

}
