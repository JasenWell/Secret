package com.xyp.mimi.mvp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xyp.mimi.R;

import java.text.DecimalFormat;
import java.util.Arrays;

public class LineView extends View {

    private String[] xLabel;
    private String[] yLabel;
    private String[] data; //数据点
    private String[] mDataLineColors; //线的颜色

    private Paint mScaleLinePaint;
    private Paint mDataLinePaint;
    private Paint mScaleValuePaint;
    private Paint mBackColorPaint;

    private float[][] mDataCoords = new float[7][2];
    private float xLength;
    private float yLength;
    private float startPointX;
    private float startPointY;
    private float xScale;
    private float yScale;
    private float coordTextSize;
    private boolean isClick;                // 是否点击了数据点
    private int clickIndex = -1;            // 被点击的数据点的索引值

    private PopupWindow mPopWin;

    Rect bounds = new Rect();
    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化
    private void init() {
        this.setBackgroundColor(Color.WHITE);

        if(xLabel == null){
            xLabel = new String[]{"12-11", "12-12", "12-13", "12-14", "12-15", "12-16", "12-17"};
        }

        if(data == null){
            data = new String[]{"2.98", "2.99", "2.99", "2.98", "2.92", "2.94", "2.95"};
        }
        //根据设置的数据值生成y坐标刻度
        yLabel  =  createYLabel();
        mDataLineColors = new String[]{"#fbbc14", "#fbaa0c", "#fbaa0c", "#fb8505", "#ff6b02", "#ff5400", "#ff5400"};
        // 新建画笔
        mDataLinePaint = new Paint();       // 数据(点和连线)画笔
        mScaleLinePaint = new Paint();      // 坐标(刻度线条)值画笔
        mScaleValuePaint = new Paint();      // 图表(刻度值)画笔
        mBackColorPaint = new Paint();       // 背景(色块)画笔

        // 画笔抗锯齿
        mDataLinePaint.setAntiAlias(true);
        mScaleLinePaint.setAntiAlias(true);
        mScaleValuePaint.setAntiAlias(true);
        mBackColorPaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 这里就不去判断几种测量模式了，直接设置大小
        // 需要适配多种分辨率屏幕的课自行根据测量模式来设置最终大小
        initParams();
    }

    private void initParams() {

        int width = getMeasuredWidth()-getPaddingLeft()-getPaddingRight();
        int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        yScale = height / 7.5f;         // y轴刻度
        xScale = width / 7.5f;          // x轴刻度

        startPointX = xScale /2 ;    // 开始绘图的x坐标
        startPointY = yScale / 2;       // 开始UI图的y坐标
        xLength = 6f * xScale;        // x轴长度
        yLength = 5.5f * yScale;        // y轴长度

        float chartLineStrokeWidth = xScale / 50;     // 图表线条的线宽
        coordTextSize = xScale / 5;             // 坐标刻度文字的大小
        float dataLineStrodeWidth = xScale / 15;      // 数据线条的线宽

        // 设置画笔相关属性
        mBackColorPaint.setColor(0x11DEDE68);
        mScaleLinePaint.setStrokeWidth(chartLineStrokeWidth);
        mScaleLinePaint.setColor(0xFFDEDCD8);
        mScaleValuePaint.setColor(0xFF999999);
        mScaleValuePaint.setTextSize(coordTextSize);
        mDataLinePaint.setStrokeWidth(dataLineStrodeWidth);
        mDataLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mDataLinePaint.setTextSize(1.5f * coordTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackColor(canvas);//绘制背景
        drawYAxisAndXScaleValue(canvas);
        drawXAxisAndYScaleValue(canvas);    // 绘制x轴和y刻度值
        drawDataLines(canvas);
        drawDataPoints(canvas);
    }

    private void drawDataLines(Canvas canvas) {

        float originalPointX = startPointX;
        float originalPointY = startPointY + yLength - yScale;
        for (int i = 0; i < data.length; i++) {
            mDataCoords[i][0] = originalPointX + i * xScale;
            float dataY = Float.parseFloat(data[i]);
            float oriY = Float.parseFloat(yLabel[0]);
            mDataCoords[i][1] = originalPointY - (yScale * (dataY - oriY) / (Float.parseFloat(yLabel[1]) - Float.parseFloat(yLabel[0])));
        }

        for (int i = 0; i < 6; i++) {
            mDataLinePaint.setColor(Color.parseColor(mDataLineColors[i]));
            canvas.drawLine(mDataCoords[i][0], mDataCoords[i][1], mDataCoords[i + 1][0], mDataCoords[i + 1][1], mDataLinePaint);
        }
    }

    private void drawDataPoints(Canvas canvas) {
        if (clickIndex > -1) {
            mDataLinePaint.setColor(Color.parseColor(mDataLineColors[clickIndex]));
            canvas.drawCircle(mDataCoords[clickIndex][0], mDataCoords[clickIndex][1], xScale / 10, mDataLinePaint);
            mDataLinePaint.setColor(Color.WHITE);
            canvas.drawCircle(mDataCoords[clickIndex][0], mDataCoords[clickIndex][1], xScale / 20, mDataLinePaint);
//            mDataLinePaint.setColor(Color.parseColor(mDataLineColors[clickIndex]));
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        for(int i = 0;i<7;i++){
            float dataX =mDataCoords[i][0];
            float dataY = mDataCoords[i][1];
            // 控制触摸/点击的范围，在有效范围内才触发
            if(Math.abs(touchX - dataX) < xScale / 2 && Math.abs(touchY - dataY) < yScale / 2){
                isClick = true;
                clickIndex = i;
                invalidate();
                showDetails(i);   // 通过PopupWindow展示详细数据信息
                return  true;
            }else{
                hideDetails();
            }
            clickIndex = -1;
            invalidate();

        }
        return super.onTouchEvent(event);
    }

    private void hideDetails() {
        if (mPopWin != null) mPopWin.dismiss();
    }

    /**
     * 点击数据点后，展示详细的数据值
     */
    private void showDetails(int index) {
        if (mPopWin != null) mPopWin.dismiss();
        TextView tv = new TextView(getContext());
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundResource(R.drawable.shape_pop_bg);
        GradientDrawable myGrad = (GradientDrawable) tv.getBackground();
        myGrad.setColor(Color.parseColor(mDataLineColors[index]));
        tv.setPadding(20, 0, 20, 0);
        tv.setGravity(Gravity.CENTER);
        tv.setText(data[index] + "%");
        mPopWin = new PopupWindow(tv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWin.setBackgroundDrawable(new ColorDrawable(0));
        mPopWin.setFocusable(false);
        // 根据坐标点的位置计算弹窗的展示位置
        int xoff = (int) (mDataCoords[index][0] - 0.5 * xScale);
        int yoff = -(int) (getHeight() - mDataCoords[index][1] + 0.75f * yScale);
        mPopWin.showAsDropDown(this, xoff, yoff);
        mPopWin.update();
    }


    private void drawXAxisAndYScaleValue(Canvas canvas) {
        for(int i = 0; i<=yLabel.length; i++){
            canvas.drawLine(startPointX,startPointY+i*yLength/5,startPointX+xLength,startPointY+i*yLength/5,mScaleLinePaint);
        }

    }

    private void drawYAxisAndXScaleValue(Canvas canvas) {
        for(int i=0;i<7;i++){
            canvas.drawLine(startPointX+i*xScale,startPointY,startPointX+i*xScale,
                    startPointY+yLength,mScaleLinePaint);

            mScaleValuePaint.getTextBounds(xLabel[i],0,xLabel[i].length(),bounds);

            if (i == 0) {
                canvas.drawText(xLabel[i],startPointX,startPointY+yLength+bounds.height()+yScale/15,mScaleValuePaint);

            }else {
                canvas.drawText(xLabel[i],
                        startPointX + i * xScale - bounds.width() / 2,
                        startPointY + yLength + bounds.height() + yScale / 15,
                        mScaleValuePaint);
            }
        }

    }

    private void drawBackColor(Canvas canvas) {

        for(int i = 0;i<7;i++){
            if(i == 2 || i == 4 || i== 6){
                canvas.drawRect(startPointX+(i-1)*xScale,startPointY,startPointX + i * xScale,yLength+startPointY,mBackColorPaint);
            }
        }
    }

    private String[] createYLabel() {
        float[] dataFloats = new float[7];
        for(int i = 0;i<data.length;i++){
            dataFloats[i] = Float.parseFloat(data[i]);
        }
        //讲数据值从小到大排序
        Arrays.sort(dataFloats);
        // 中间值
        float middle = format3Bit((dataFloats[0] + dataFloats[6]) / 2f);
        // Y刻度值
        float scale = format3Bit((dataFloats[6] - dataFloats[0]) / 4 + 0.01f);
        String[] yText = new String[5];
        yText[0] = (middle - 2 * scale) + "";
        yText[1] = (middle - scale) + "";
        yText[2] = middle + "";
        yText[3] = (middle + scale) + "";
        yText[4] = (middle + 2 * scale) + "";
        for (int i = 0; i < yText.length; i++) {
            yText[i] = format3Bit(yText[i]);
        }
        return yText;
    }


    /**
     * 格式化数字 ###.000
     *
     * @return ###.000
     */
    private float format3Bit(float number) {
        DecimalFormat decimalFormat = new DecimalFormat("###.000");
        String target = decimalFormat.format(number);
        if (target.startsWith(".")) {
            target = "0" + target;
        }
        return Float.parseFloat(target);
    }

    /**
     * 格式化数据 ###.000
     *
     * @param numberStr 格式化后的字符串形式
     * @return ###.000
     */
    private String format3Bit(String numberStr) {
        if (TextUtils.isEmpty(numberStr)) {
            return "0.000";
        }
        float numberFloat = Float.parseFloat(numberStr);
        DecimalFormat decimalFormat = new DecimalFormat("###.000");
        String target = decimalFormat.format(numberFloat);
        if (target.startsWith(".")) {
            target = "0" + target;
        }
        return target;
    }

    public void setxLabel(String[] xLabel){
        this.xLabel = xLabel;
    }
    /**
     * 设置数据
     *
     * @param data 数据值
     */
    public void setData(String[] data) {
        this.data = data;
    }

    public void fresh(){
        init();
        requestLayout();
        postInvalidate();
    }

}
