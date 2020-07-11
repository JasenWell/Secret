package com.xyp.mimi.mvp.ui.adapter.evalution;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.http.entity.market.EvalutionListResult;
import com.xyp.mimi.mvp.ui.activity.market.GoodsDetailActivity;
import com.xyp.mimi.mvp.utils.GlideLoadUtils;
import com.xyp.mimi.mvp.view.PhotoInfo;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

//评价
public class EvalutionAdapter extends BaseMultiItemQuickAdapter<EvalutionListResult.DataBean, BaseViewHolder>  {

     private GoodsDetailActivity activity;

    public EvalutionAdapter(List<EvalutionListResult.DataBean> data,GoodsDetailActivity activity) {
        super(data);
        this.activity = activity;
        addItemType(0, R.layout.item_goodsdetail_evalution);
//        addItemType(1, R.layout.item_circle_moment1);
//        addItemType(3, R.layout.item_circle_moment);
    }






    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, EvalutionListResult.DataBean dataBean) {
        switch (helper.getItemViewType()) {
            case 0: //没有图片的时候
                TextView  name = helper.getView(R.id.tv_name);//姓名
                name.setText(dataBean.getNickName());
                TextView  time = helper.getView(R.id.tv_time);//时间
                time.setText(dataBean.getAddTime());
                TextView  content = helper.getView(R.id.tv_content);//内容
                content.setText(dataBean.getContentText());

                List<PhotoInfo> list = new ArrayList<>();//图片列表
                ArrayList<String> picList = new ArrayList<>();//图片地址列表
                BGANinePhotoLayout ninePhotoLayout= helper.getView(R.id.npl_item_moment_photos);//多图
                if (dataBean.getPicData().size() > 0) {
                    for (int i = 0; i < dataBean.getPicData().size(); i++) {
                        PhotoInfo photoInfo = new PhotoInfo();
                        photoInfo.setUrl(dataBean.getPicData().get(0).getPicUrl());
                        list.add(photoInfo);
                        picList.add(dataBean.getPicData().get(0).getPicUrl());
                    }
                    ninePhotoLayout.setDelegate(activity);
                    ninePhotoLayout.setData(picList);
//                    GlideLoadUtils.getInstance().glideLoad(mContext, dataBean.getAvatar(), helper.getView(R.id.civ_my_img), R.drawable.sj,1);
                }
                GlideLoadUtils.getInstance().glideLoadRoundedCorners(mContext,dataBean.getAvatar(), helper.getView(R.id.civ_my_img));

                //查看大图
//                multiImageView.setOnItemClickListener((view, position, url, imagesList, imageView) -> {
//                    Intent intent = new Intent();
//                    intent.putStringArrayListExtra("imageList", (ArrayList<String>) picList);
//                    intent.putExtra(P.START_ITEM_POSITION, position);
//                    intent.putExtra(P.START_IAMGE_POSITION, position);
//                    intent.putExtra("from_local",false);
//                    ActUtils.STActivity((Activity) mContext, intent, ImagePreviewActivity.class);
//                });
                break;
        }
    }


}
