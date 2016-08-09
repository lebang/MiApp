package com.miapp.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ColorLineView extends View {

    private Context mContext;
    private float startX = 0;
    private Paint mPaint;
    private List<ColorLine> mDataList = new ArrayList<ColorLine>();
    private int mViewWidth, mSumRatio = 60;


    public void setData(List<ColorLine> datas){
//        mDataList = datas;
    }

    public ColorLineView(Context context) {
        super(context);
    }

    public ColorLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mDataList.size() == 0){
            mDataList.add(new ColorLine(2, "#FF0000"));
            mDataList.add(new ColorLine(6, "#AAAAAA"));
            mDataList.add(new ColorLine(1, "#CCCCCC"));
            mDataList.add(new ColorLine(2, "#FFAA00"));
            mDataList.add(new ColorLine(4, "#CCAA00"));
        }

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        mViewWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0, len = mDataList.size(); i < len; i++){
            ColorLine item = mDataList.get(i);
            int w = item.getRatio();
            startX = getStartX(i);
            mPaint.setColor(Color.parseColor(item.getColorVal()));
            canvas.drawLine(startX*mSumRatio, 0, (startX+w)*mSumRatio, 0, mPaint);
        }

    }


    private int getStartX(int pos){
        if ( pos == 0){
            return 0;
        }
        int start = 0;
        for (int i=0; i<pos; i++){
            ColorLine item = mDataList.get(i);
            start += item.getRatio();
        }
        return start;
    }
}

class ColorLine {
    public int ratio;
    public String colorVal;

    public ColorLine(int ratio, String colorVal) {
        this.ratio = ratio;
        this.colorVal = colorVal;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public String getColorVal() {
        return colorVal;
    }

    public void setColorVal(String colorVal) {
        this.colorVal = colorVal;
    }
}
