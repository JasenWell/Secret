package com.xyp.mimi.mvp.ui.adapter.wallet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.wallet.MyBankListResult;
import com.xyp.mimi.mvp.utils.GlideLoadUtils;

public class MyBankListAdapter extends BaseRecyclerAdapter<MyBankListResult.DataBean> {
    Context context;

    public MyBankListAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
        context= mContext;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerViewHolder holder = new RecyclerViewHolder(this.mContext, this.mInflater.inflate(R.layout.item_mybank, parent, false));
        return holder;

    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void bindData(RecyclerViewHolder holder, int i, MyBankListResult.DataBean bean) {
//        iv_account_icon
        GlideLoadUtils.getInstance().glideLoad(context,bean.getBankLogo(), (ImageView) holder.getView(R.id.iv_account_icon),R.drawable.icon_empty);
        holder.setText(R.id.tv_account_bank,bean.getBankName());
        holder.setText(R.id.tv_banknumber,bean.getBankCardNo());

        holder.getView(R.id.tv_jiebang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickBankDeleteListener!=null){
                    onClickBankDeleteListener.deleteBank(bean.getId());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickBankListener!=null){
                    onClickBankListener.onClick(bean.getBankName());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }


    public interface  OnClickBankListener{
        void onClick(String s);
    }
    public interface  OnClickBankDeleteListener{
        void deleteBank(int id);
    }

    private  OnClickBankListener onClickBankListener;
    private  OnClickBankDeleteListener onClickBankDeleteListener;

    public void setOnBankListener(OnClickBankListener onClickBankListener){
        this.onClickBankListener =onClickBankListener;
    }
    public void setOnBankDeleteListener(OnClickBankDeleteListener onClickBankDeleteListener){
        this.onClickBankDeleteListener =onClickBankDeleteListener;
    }


}
