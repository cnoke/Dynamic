package com.knd.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BatteryView extends View {
    private Paint mPaint;
    private Paint mStrokePaint;
    private int rightDistance=4;
    private int innerGap=3;
    private Paint mInnerPaint;
    private int percent=0;
    private int strokeWidth=2;
    private int innerWidth;
    public BatteryView(Context context) {
        this(context,null);
    }

    public BatteryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.WHITE);

        mStrokePaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(strokeWidth);
        mStrokePaint.setColor(0xFF595C66);

        mInnerPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mInnerPaint.setColor(0xFF00FF85);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        innerWidth=getWidth()-rightDistance-2*innerGap-2*strokeWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(0,0,getWidth()-rightDistance,getHeight(),5,5,mPaint);
        canvas.drawRoundRect(getWidth()-2*rightDistance,getHeight()/4,getWidth(),getHeight()*3/4,5,5,mPaint);
        canvas.drawRoundRect(innerGap,innerGap,getWidth()-rightDistance-innerGap,getHeight()-innerGap,4,4,mStrokePaint);
        if(percent>0)
        canvas.drawRoundRect(innerGap+strokeWidth/2,innerGap+strokeWidth/2,innerGap+strokeWidth+innerWidth*percent/100,getHeight()-innerGap-strokeWidth/2,4,4,mInnerPaint);
    }

    public void setPercent(int percent) {
        this.percent = percent;
        invalidate();
    }
}
