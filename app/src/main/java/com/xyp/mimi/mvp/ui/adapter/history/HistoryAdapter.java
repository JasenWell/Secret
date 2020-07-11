package com.xyp.mimi.mvp.ui.adapter.history;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.customview.MyCheckBox;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.history.HistoryResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseQuickAdapter<HistoryResult.DataBean,HistoryAdapter.MyViewHolder> {

    private boolean isEdit;

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    List<HistoryResult.DataBean> history = new ArrayList<>();

    List<HistoryResult.DataBean> selectIndex = new ArrayList<>();

    public HistoryAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
        history = data;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MyViewHolder helper, HistoryResult.DataBean item) {
//        GlideLoadUtils.getInstance().glideLoad(mContext,item.getPicFrist(),helper.goods,R.drawable.icon_empty);
//        helper.name.setText(item.getProName());
//        helper.price.setText(item.getPrice()+"");
//
//        if(item.isSelect()){
//            helper.myCheckBox.setChecked(true);
//        }else{
//            helper.myCheckBox.setChecked(false);
//        }
//        //编辑状态左侧圆圈出现 ，否则隐藏
//        if(isEdit){
//            helper.myCheckBox.setVisibility(View.VISIBLE);
//        }else{
//            helper.myCheckBox.setVisibility(View.GONE);
//        }
//        int position = helper.getLayoutPosition();
//
//        helper.myCheckBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                history.get(position).setSelect(!history.get(position).isSelect());
//
//                jiSuanTotalSelect();
//
//            }
//        });
//

    }

    static class MyViewHolder extends  BaseViewHolder{
        private TextView name,price;
        private ImageView goods;
        private MyCheckBox myCheckBox;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_name);
            price = view.findViewById(R.id.tv_price);
            goods = view.findViewById(R.id.iv_goods);
            myCheckBox = view.findViewById(R.id.cb_goods);

        }
    }

//    public void setSelectAll(boolean selectAll) {
//        StringBuilder historyBuilder =new StringBuilder();
//        if(selectAll){
//            for (int i = 0;  i <history.size() ;  i++) {
//                if(!history.get(i).isSelect()){
//                    history.get(i).setSelect(true);
//                    selectIndex.add(history.get(i));
//                    historyBuilder.append(history.get(i).getId()+",");
//                }
//            }
//            String historyString = historyBuilder.toString();
//            EventBus.getDefault().post(new DeleteCollectEvent(history.size(),historyString,selectAll,selectIndex));
//        }else{
//            //全不选则不管
//            for (int i = 0;  i <history.size() ;  i++) {
//                if(history.get(i).isSelect()){
//                    history.get(i).setSelect(false);
//
//                }
//            }
//            selectIndex.clear();
//            EventBus.getDefault().post(new DeleteCollectEvent(0,selectAll));
//        }
//        notifyDataSetChanged();
//    }
//
//    private void jiSuanTotalSelect() {
//        int total=0;
//        selectIndex.clear();
//        StringBuilder historyBuilder =new StringBuilder();
//        boolean isSelectAll=true;
//        if(history.size()==0){
//            isSelectAll=false;
//        }
//        for (int i = 0; i < history.size(); i++) {
//            if(history.get(i).isSelect()){
//                total= total+1;
//                historyBuilder.append(history.get(i).getId()+",");
//                selectIndex.add(history.get(i));
//            }else{
//                isSelectAll=false;
//            }
//        }
//        String historyString = historyBuilder.toString();
//        EventBus.getDefault().post(new DeleteCollectEvent(total,historyString,isSelectAll,selectIndex));
//    }

}
