package com.miapp.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lebang on 16-4-11.
 */
public class DivView extends TextView {


    public DivView(Context context) {
        super(context);
    }

    public DivView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaintOut = new Paint();
        mPaintOut.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaintOut.setStyle(Paint.Style.FILL);

        Paint mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaintOut);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint);
        canvas.save();
        canvas.translate(10,10);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureWidth(heightMeasureSpec));

    }

    private int measureNum(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else {
            result = 200;
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureWidth(int measureSpec){
        return measureNum(measureSpec);
    }

    private int measureHeight(int measureSpec){
        return measureNum(measureSpec);
    }
}
