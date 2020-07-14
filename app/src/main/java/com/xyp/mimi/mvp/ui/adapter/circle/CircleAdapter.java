package com.xyp.mimi.mvp.ui.adapter.circle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.androidtools.PhoneUtils;
import com.github.baseclass.utils.ActUtils;
import com.github.customview.MyImageView;

import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.circle.CircleListResult;
import com.xyp.mimi.mvp.ui.adapter.address.AddressAdapter;
import com.xyp.mimi.mvp.utils.FormatCurrentData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
public class CircleAdapter extends BaseMultiItemQuickAdapter<CircleListResult.DataBean.DlistBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CircleAdapter(List<CircleListResult.DataBean.DlistBean> data) {
        super(data);
        addItemType(0, R.layout.item_circle_moment);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, CircleListResult.DataBean.DlistBean dataBean) {
        switch (helper.getItemViewType()) {
            case 0: //没有图片的时候
                TextView name0 = helper.getView(R.id.tv_name);
                TextView circleContent0 = helper.getView(R.id.tv_circle_content);
                RelativeLayout relativeLayout0 = helper.getView(R.id.ll_imageview);   //没有图片的时候隐藏控件
                relativeLayout0.setVisibility(View.GONE);
                //头像
//                Glide.with(mContext).load(dataBean.getId()).apply(bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.civ_my_img));
                //名字
                name0.setText("TEST");
                //标题
//                TextView circleTitle0 = helper.getView(R.id.tv_circle_title);
//                circleTitle0.setText(com.petzm.training.tools.StringUtils.convertUTF8ToString(dataBean.getCircleName()));
//                circleTitle0.setVisibility(dataBean.getCircleName().length() > 0 ? View.VISIBLE : View.GONE);
                //内容
                circleContent0.setText(dataBean.getContext());
                //点赞
//                ImageView ivPraise0 = helper.getView(R.id.iv_praise);
//                ivPraise0.setBackgroundResource(dataBean.getLikeButton() == 1 ? R.mipmap.circle_praised : R.mipmap.circle_zan);
//
//                TextView tvPraise0 = helper.getView(R.id.tv_praise);//点赞次数
//                tvPraise0.setText(dataBean.getLikeCount() + "");
//                TextView tvComment0 = helper.getView(R.id.tv_comment);//评论总数
//                tvComment0.setText(dataBean.getCommentCount() + "");
//                Log.i("==========", "" +"dataBean.getCreateTime()"+ FormatCurrentData.getTimeRange("2019-12-20 17:12:12"));
//                Log.i("==========", "" +"dataBean.getCreateTime()"+FormatCurrentData.getTimeRange("2019-12-20 15:12:12"));
//                Log.i("==========", "" +"dataBean.getCreateTime()"+ FormatCurrentData.getTimeRange("2019-12-19 12:12:12"));
//                Log.i("==========", "" +"dataBean.getCreateTime()"+ FormatCurrentData.getTimeRange("2019-12-17 12:12:12"));
                TextView circleTime0 = helper.getView(R.id.tv_circle_time);//发布时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                // time为转换格式后的字符串
                String time = simpleDateFormat.format(new Date(dataBean.getCreateTime()));
                circleTime0.setText(FormatCurrentData.getTimeRange(time));

                //删除
                helper.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onClickCircleDeleteListener!=null){
                            onClickCircleDeleteListener.deleteCircle(dataBean.getId());
                        }
                    }
                });

//                LinearLayout linearLayout = helper.getView(R.id.ll_praise);//点赞linear
//                linearLayout.setOnClickListener(new OnMultiClickListener() {
//                                                    @Override
//                                                    public void onMultiClick(View v) {
//                                                        Map<String, String> map = new HashMap<String, String>();
//                                                        map.put("circleId", dataBean.getTId() + "");
//                                                        if (dataBean.getLikeButton() == 0) {//没有点赞
//                                                            ApiRequest.getCirclePraise(map, new MyCallBack<Object>(mContext) {
//                                                                @SuppressLint("SetTextI18n")
//                                                                @Override
//                                                                public void onSuccess(Object obj) {
//                                                                    ivPraise0.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.like_anim));
//                                                                    dataBean.setLikeButton(1);
//                                                                    dataBean.setLikeCount(dataBean.getLikeCount() + 1);
//                                                                    tvPraise0.setText(dataBean.getLikeCount() + " ");
//                                                                    ivPraise0.setBackgroundResource(R.mipmap.circle_praised);
//                                                                }
//                                                            });
//                                                        } else {
//                                                            ApiRequest.getCirclePraise(map, new MyCallBack<Object>(mContext) {
//                                                                @SuppressLint("SetTextI18n")
//                                                                @Override
//                                                                public void onSuccess(Object obj) {
//                                                                    ivPraise0.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.like_anim));
//                                                                    dataBean.setLikeButton(0);
//                                                                    dataBean.setLikeCount(dataBean.getLikeCount() - 1);
//                                                                    tvPraise0.setText(dataBean.getLikeCount() + " ");
//                                                                    ivPraise0.setBackgroundResource(R.mipmap.circle_zan);
//                                                                }
//                                                            });
//                                                        }
//                                                    }
//                                                }
//                );
//
//                helper.itemView.setOnClickListener(view -> {
//                    Intent intent = new Intent();
//                    intent.putExtra(Constant.IParam.tid, dataBean.getTId());
//                    intent.putExtra(Constant.IParam.position, helper.getPosition());
//                    ActUtils.STActivity((Activity) mContext, intent, ThemeActivity.class);
//                });

                break;

        }

    }

    //删除
    public interface  OnClickCircleDeleteListener{
        void deleteCircle(String id);
    }

    private CircleAdapter.OnClickCircleDeleteListener onClickCircleDeleteListener;


    public void setOnCircleDeleteListener(CircleAdapter.OnClickCircleDeleteListener onCircleDeleteListener){
        this.onClickCircleDeleteListener =onCircleDeleteListener;
    }

}
