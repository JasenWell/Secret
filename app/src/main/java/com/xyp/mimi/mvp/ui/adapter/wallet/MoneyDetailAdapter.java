package com.xyp.mimi.mvp.ui.adapter.wallet;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.wallet.MoneyDetailResult;

import java.util.List;

import androidx.annotation.Nullable;

public class MoneyDetailAdapter extends BaseQuickAdapter<MoneyDetailResult.DataBean.ListBean, MoneyDetailAdapter.MyViewHodler> {

    private  int id;

    public MoneyDetailAdapter(int layoutResId, @Nullable List<MoneyDetailResult.DataBean.ListBean> data, int id) {

        super(layoutResId, data);
        this.id = id;
    }
    @Override
    protected void convert(MyViewHodler helper, MoneyDetailResult.DataBean.ListBean dataBean) {
        helper.title.setText(dataBean.getTitle());
        helper.time.setText(dataBean.getAddTime());
        helper.money.setText(dataBean.getChange());

//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra(Constant.IParam.SearchTag, dataBean.getLoginName());
//                ActUtils.STActivity((Activity) mContext,intent, SearchCoursesResultActivity.class);
//            }
//        });
//        int position = helper.getLayoutPosition();
//        helper.ivAttention.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("beFocusedId", dataBean.getBeFocusedId()+"");
//                if(dataBean.getAttention() == 0 ){
//                    ApiRequest.addConcern(map,new MyCallBack<BaseObj>(mContext) {
//                        @Override
//                        public void onSuccess(BaseObj obj) {
//                            dataBean.setAttention(1);
//                            notifyDataSetChanged();
//                            if(id==1){
//                                RxBus.getInstance().post(new RefreshAttentionEvent(0));
//                            }else{
//                                RxBus.getInstance().post(new RefreshAttentionEvent(1));
//                            }
//                        }
//                    });
//                }else{
//                    ApiRequest.deleteConcern(map,new MyCallBack<BaseObj>(mContext) {
//                        @Override
//                        public void onSuccess(BaseObj obj) {
//                            dataBean.setAttention(0);
//                            notifyDataSetChanged();
//                            if(id==1){
//                                RxBus.getInstance().post(new RefreshAttentionEvent(0));
//                            }else{
//                                RxBus.getInstance().post(new RefreshAttentionEvent(1));
//                            }
//                        }
//                    });
//                }
//
//            }
//        });
    }

    class MyViewHodler extends BaseViewHolder {
        private TextView title,time,money;
        public MyViewHodler(View view) {
            super(view);
            title = view.findViewById(R.id.tv_title);
            time = view.findViewById(R.id.tv_time);
            money = view.findViewById(R.id.iv_money);
        }
    }
}