package com.xyp.mimi.mvp.ui.adapter.order;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import com.bm.library.PhotoView;
import com.xyp.mimi.R;
import com.xyp.mimi.mvp.utils.GlideLoadUtils;
import com.xyp.mimi.mvp.utils.Utils;

import java.util.List;


public class ImagePreviewAdapter extends PagerAdapter {
    private Context context;
    private List<String> imageList;
    private int itemPosition;
    private PhotoView photoView;
    private  Boolean local;

    public ImagePreviewAdapter(Context context, List<String> imageList, int itemPosition,boolean local) {
        this.context = context;
        this.imageList = imageList;
        this.itemPosition = itemPosition;
        this.local = local;
    }

    @Override
    public int getCount() {
        return imageList==null?0:imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final PhotoView image = new PhotoView(context);
        // 启用图片缩放功能
        image.enable();
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        String sImage = imageList.get(position);
        if(local){
            int images = context.getResources().getIdentifier(sImage, "drawable", context.getPackageName());
            GlideLoadUtils.getInstance().glideLoad(context,images,image, R.drawable.default_image);
        }else{
            GlideLoadUtils.getInstance().glideLoad(context,sImage,image, R.drawable.default_image);
        }

//        Glide.with(context).load(images).into(image);

        image.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               image.setEnabled(false);
               ((Activity)context).onBackPressed();
           }
       });
        container.addView(image);
        return image;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        photoView = (PhotoView) object;
//      photoView.setTag(Utils.getNameByPosition(itemPosition,position));
        photoView.setTransitionName(Utils.getNameByPosition(itemPosition,position));
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public PhotoView getPhotoView() {
        return photoView;
    }

    //将字符串转化成Drawable
    public synchronized static Drawable StringToDrawable(String icon) {
        if (icon == null || icon.length() < 10)
            return null;
        byte[] img = Base64.decode(icon.getBytes(), Base64.DEFAULT);
        Bitmap bitmap;
        if (img != null) {
            bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bitmap);

            return drawable;
        }
        return null;
    }


}
