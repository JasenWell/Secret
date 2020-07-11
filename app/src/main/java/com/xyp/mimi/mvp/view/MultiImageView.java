package com.xyp.mimi.mvp.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.baseclass.view.pickerview.pic.DensityUtil;
import com.github.customview.MyLinearLayout;

import java.util.List;

/**
 * @ClassName MultiImageView.java
 * @author shoyu
 * @version 
 * @Description: 显示1~N张图片的View
 */

public class MultiImageView extends MyLinearLayout {
	public static int MAX_WIDTH = 0;

	// 照片的Url列表
	private List<PhotoInfo> imagesList;

	/** 长度 单位为Pixel **/
	private int pxOneMaxWandH;  // 单张图最大允许宽高
	private int pxMoreWandH = 0;// 多张图的宽高
	private int pxImagePadding = DensityUtil.dip2px(getContext(), 8);// 图片间的间距

	private int MAX_PER_ROW_COUNT = 3;// 每行显示最大数

	private LayoutParams onePicPara;
	private LayoutParams morePara, moreParaColumnFirst;
	private LayoutParams rowPara;

	private OnItemClickListener mOnItemClickListener;
	public void setOnItemClickListener(OnItemClickListener onItemClickListener){
		mOnItemClickListener = onItemClickListener;
	}

	public MultiImageView(Context context) {
		super(context);
	}

	public MultiImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public void setList(List<PhotoInfo> lists) throws IllegalArgumentException {
		if(lists==null){
			throw new IllegalArgumentException("imageList is null...");
		}
		imagesList = lists;
		
		if(MAX_WIDTH > 0){
			pxMoreWandH = (MAX_WIDTH - pxImagePadding*2 )/3; //解决右侧图片和内容对不齐问题
			pxOneMaxWandH = MAX_WIDTH * 2 / 3;
			initImageLayoutParams();
		}

		initView();
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(MAX_WIDTH == 0){
			int width = measureWidth(widthMeasureSpec);
			if(width>0){
				MAX_WIDTH = width;
				if(imagesList!=null && imagesList.size()>0){
					setList(imagesList);
				}
			}
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * Determines the width of this view
	 * 
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The width of the view, honoring constraints from measureSpec
	 */
	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			// We were told how big to be
			result = specSize;
		} else {
			// Measure the text
			// result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
			// + getPaddingRight();
			if (specMode == MeasureSpec.AT_MOST) {
				// Respect AT_MOST value if that was what is called for by
				// measureSpec
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	private void initImageLayoutParams() {
		int wrap = LayoutParams.WRAP_CONTENT;
		int match = LayoutParams.MATCH_PARENT;

		onePicPara = new LayoutParams(wrap, wrap);
		onePicPara=new LayoutParams(pxOneMaxWandH, wrap);
		moreParaColumnFirst = new LayoutParams(pxMoreWandH, pxMoreWandH);
		morePara = new LayoutParams(pxMoreWandH, pxMoreWandH);
		morePara.setMargins(pxImagePadding, 0, 0, 0);

		rowPara = new LayoutParams(match, wrap);
	}

	// 根据imageView的数量初始化不同的View布局,还要为每一个View作点击效果
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	private void initView() {
		this.setOrientation(VERTICAL);
		this.removeAllViews();
		if(MAX_WIDTH == 0){
			//为了触发onMeasure()来测量MultiImageView的最大宽度，MultiImageView的宽设置为match_parent
			addView(new View(getContext()));
			return;
		}
		
		if (imagesList == null || imagesList.size() == 0) {
			return;
		}

		if (imagesList.size() == 1) {
			addView(createImageView(0, false));
		} else {
			int allCount = imagesList.size();
			if(allCount == 4){
				MAX_PER_ROW_COUNT = 2;
			}else{
				MAX_PER_ROW_COUNT = 3;
			}
			int rowCount = allCount / MAX_PER_ROW_COUNT
					+ (allCount % MAX_PER_ROW_COUNT > 0 ? 1 : 0);// 行数
			for (int rowCursor = 0; rowCursor < rowCount; rowCursor++) {
				LinearLayout rowLayout = new LinearLayout(getContext());
				rowLayout.setOrientation(LinearLayout.HORIZONTAL);
				rowLayout.setLayoutParams(rowPara);
				if (rowCursor != 0) {
					rowLayout.setPadding(0, pxImagePadding, 0, 0);
				}

				int columnCount = allCount % MAX_PER_ROW_COUNT == 0 ? MAX_PER_ROW_COUNT
						: allCount % MAX_PER_ROW_COUNT;//每行的列数
				if (rowCursor != rowCount - 1) {
					columnCount = MAX_PER_ROW_COUNT;
				}
				addView(rowLayout);

				int rowOffset = rowCursor * MAX_PER_ROW_COUNT;// 行偏移
				for (int columnCursor = 0; columnCursor < columnCount; columnCursor++) {
					int position = columnCursor + rowOffset;
					rowLayout.addView(createImageView(position, true));
				}
			}
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	private ImageView createImageView(int position, final boolean isMultiImage) {
		PhotoInfo photoInfo = imagesList.get(position);
//		RatioImageView imageView = new RatioImageView(getContext());
		ImageView imageView = new ImageView(getContext());
//		imageView.setRadius(20);
		if(isMultiImage){
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageView.setLayoutParams(position % MAX_PER_ROW_COUNT == 0 ?moreParaColumnFirst : morePara);
		}else {
//			imageView.setAdjustViewBounds(true);
			imageView.setScaleType(ScaleType.CENTER_CROP);
//			imageView.setMaxHeight(pxOneMaxWandH);

            int expectW = ConvertUtils.dp2px(108f);
            int expectH = ConvertUtils.dp2px(108f);

//            if(expectW == 0 || expectH == 0){
//                imageView.setLayoutParams(onePicPara);
//            }else{
//				int scaledW = 0;
//				int scaledH = 0;
//				int width = expectW;
//				int height =expectH;
//				if(width>height){
//					 scaledH =  ConvertUtils.dp2px(108f);
//					 scaledW =  ConvertUtils.dp2px(108f);
////                            int scaledW = (width * scaledH) / height;//计算出按比缩放后的宽
////					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(scaledW,scaledH);
////					myImageView.setLayoutParams(lp);
//				}else if(width== height){
//					 scaledW =  ConvertUtils.dp2px(108f);
//					 scaledH =  ConvertUtils.dp2px(108f);
////                            int scaledW = (width * scaledH) / height;//计算出按比缩放后的宽
////					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(scaledW,scaledH);
////					myImageView.setLayoutParams(lp);
//				}else{
//					scaledW =  ConvertUtils.dp2px(108f);
//					scaledH =  ConvertUtils.dp2px(108f);
//				}
//                int actualW = 0;
//                int actualH = 0;
//                float scale = ((float) expectH)/((float) expectW);
//                if(expectW > pxOneMaxWandH){
//                    actualW = pxOneMaxWandH;
//                    actualH = (int)(actualW * scale);
//                } else if(expectW < pxMoreWandH){
//                    actualW = pxMoreWandH;
//                    actualH = (int)(actualW * scale);
//                }else{
//                    actualW = expectW;
//                    actualH = expectH;
//                }
                imageView.setLayoutParams(new LayoutParams(expectW, expectH));
//            }
		}

//		imageView.setId(photoInfo.url.hashCode());
		imageView.setOnClickListener(new ImageOnClickListener(position,photoInfo.getUrl(),imagesList,imageView));
//		imageView.setTag(Utils.getNameByPosition(position,position));
//		imageView.setTransitionName(Utils.getNameByPosition(position,position));
		//设置图片圆角角度
		RoundedCorners roundedCorners = new RoundedCorners(40);
		//通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
		// RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
		RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
		Glide.with(getContext()).load(photoInfo.getUrl()).apply(options).into(imageView);
//		GlideLoadUtils.getInstance().glideLoadRoundedCorners(getContext(),photoInfo.getUrl(),imageView);
		return imageView;
	}

	private class ImageOnClickListener implements OnClickListener{

		private int position;
		private String url;
		private List<PhotoInfo>  imagesList;
		private ImageView imageView;

		public ImageOnClickListener(int position, String url, List<PhotoInfo> imagesList, ImageView imageView) {
			this.position = position;
			this.url = url;
			this.imagesList = imagesList;
			this.imageView = imageView;
		}

		@Override
		public void onClick(View view) {
			if(mOnItemClickListener != null){
				mOnItemClickListener.onItemClick(view, position,url,imagesList,imageView);
			}
		}
	}

	public interface OnItemClickListener{
		public void onItemClick(View view, int position, String url, List<PhotoInfo> imagesList, ImageView imageView);
	}
}