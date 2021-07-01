package com.knd.common.view.powerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.knd.base.util.ScreenUtils;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class NewPowerCircleView extends View {
    private Paint mPaint;
    private Paint mPaintProgress;
    private Paint mPaintBall;
    private Paint mPaintInner;
    private int mWidth;
    private float mBorderWidth;
    private float cx;
    private float cy;
    private float sCx;
    private float sCy;
    private float mRadius;
    private float mProgress=3.0f;
    private int Max=45; //最大值
    private float mLimit=45f;//最大限制值
    private float minLimit=3.0f;//最小限制值
    private int ball_radius;
    private boolean isOperating=false;
    private int padding;
    private PowerListener listener;
    private ValueAnimator valueAnimator;
    private boolean isJustShow;//仅显示，不操作
    private boolean canTouch = true;
    private int centerColor = 0xFFE6E6E6;

    public NewPowerCircleView(Context context, float borderWidth, int progressColor) {
        this(context,null);
        mBorderWidth= ScreenUtils.dp2px(getContext(),borderWidth);
//        ball_radius= (int) ScreenUtils.dp2px(getContext(),15);
//        ball_radius= (int)borderWidth*101/100;
        ball_radius= (int)borderWidth-2;
        padding= (int) ScreenUtils.dp2px(getContext(),10);

        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0XFFF5F5F5);

        mPaintProgress=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaintProgress.setStrokeWidth(mBorderWidth);
        mPaintProgress.setStyle(Paint.Style.STROKE);
        mPaintProgress.setStrokeCap(Paint.Cap.ROUND);
        mPaintProgress.setColor(progressColor);

        mPaintBall=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaintBall.setStyle(Paint.Style.FILL);
        mPaintBall.setColor(progressColor);

        mPaintInner=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaintInner.setStyle(Paint.Style.FILL);
        mPaintInner.setColor(centerColor);

    }

    public NewPowerCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth= w;
        cx=mWidth*1.0f/2;
        cy=mWidth*1.0f/2;
        mRadius=mWidth*1.0f/2-mBorderWidth/2-padding;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆环底色
        canvas.drawCircle(cx,cy,mRadius,mPaint);
        //绘制圆环填充色
        canvas.drawCircle(cx,cy,mRadius-mBorderWidth/2,mPaintInner);
        float[] position = new float[3];
        position[0] = 0f;
        position[1] = mProgress*1.0f/Max;
        position[2]= 1.0f;
//        Shader shader= new SweepGradient(cx,cy,new int[]{ 0X0F25C688,0XFF25C688,0xFFF5F5F5},position);
//        mPaintProgress.setShader(shader);
        canvas.save();
        canvas.rotate(90,cx,cy);
        //绘制进度
        canvas.drawArc(mBorderWidth/2+padding,mBorderWidth/2+padding,mWidth-mBorderWidth/2-padding,mWidth-mBorderWidth/2-padding,0,mProgress*1.0f/ Max *360,false,mPaintProgress);
        canvas.restore();
        sCx= (float) (cx-Math.sin(mProgress*1.0f/Max *2*Math.PI)*mRadius);
        sCy= (float) (cy+Math.cos(mProgress*1.0f/Max *2*Math.PI)*mRadius);
        //绘制小球
        canvas.drawCircle( sCx, sCy,ball_radius,mPaintBall);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if(!canTouch){
//            return super.onTouchEvent(event);
//        }
        switch (event.getAction()){
            case ACTION_DOWN:
                if (isPointInPowerCircle(event.getX(),event.getY())){
                    if (canTouch){
                        isOperating=true;
                        if(listener!=null) listener.onStart();
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    return true;
//                    getPointRelativeDegree(event.getX(),event.getY());
                } else {
                    return super.onTouchEvent(event);
                }
            case ACTION_MOVE:
                if(isOperating &&mProgress<=Max && mProgress>=0){
                    getPointRelativeDegree(event.getX(),event.getY());
                    postInvalidate();
                }
                return true;
            case ACTION_UP:
                if (isOperating){
                    isOperating=false;
                    if(listener!=null){
                        listener.onSettingFinish((int) mProgress);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public boolean isCanTouch() {
        return canTouch;
    }

    public void setCanTouch(boolean canTouch) {
        this.canTouch = canTouch;
    }

    /**
     * 判断点是否在力量圈圆环内
     * @param x x坐标
     * @param y y坐标
     * @return 是否在力量圈圆环内
     */
    private boolean isPointInPowerCircle(float x, float y){
        float reX = x - cx;// 相对于零坐标的当前触点x坐标
        float reY = y - cy;// 相对于零坐标的当前触点y坐标
        float minF = (mRadius - mBorderWidth/2) * (mRadius - mBorderWidth/2);
        float maxF = (mRadius + mBorderWidth/2) * (mRadius + mBorderWidth/2);
        if (reX*reX + reY*reY > minF && reX*reX + reY*reY < maxF){
            Log.e("onTouchTrue",x + "," + y + "," + cx + "," + cy+","+maxF+","+minF);
            return true;
        }else {
            Log.e("onTouchFalse",x + "," + y + "," + cx + ","+cy+","+maxF+","+minF);
            return false;
        }
    }

    /**
     * 求当前触点与圆心和下方零点间的夹角并更新到力量盘
     * @param x x坐标
     * @param y y坐标
     */
    private void getPointRelativeDegree(float x, float y){
        float x2=cx;
        float y2=cy+mRadius;
        double a=Math.sqrt((cx-x)*(cx-x)+(cy-y)*(cy-y));
        double b=Math.sqrt((cx-x2)*(cx-x2)+(cy-y2)*(cy-y2));
        double c=Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
        double arc = Math.acos ((a*a+b*b-c*c)/(2*a*b));
        if(x>cx){
            if(mProgress<10){
                mProgress=0;
            }else{
                mProgress = (float) (Max- (arc/(2*Math.PI)*Max));
                mProgress= Math.round(mProgress*10)/10.0f;
            }
        }else{
            if(mProgress>(Max-10)){
                mProgress=Max;
            }else{
                mProgress = (float) (arc/(2*Math.PI)*Max);
                mProgress= Math.round(mProgress*10)/10.0f;
            }
        }
        if(mProgress>mLimit){
            mProgress=mLimit;
        }
        if(mProgress<minLimit){
            mProgress=minLimit;
        }
        if(listener!=null){
            listener.onProgress((int) mProgress);
        }
    }

    public void setListener(PowerListener listener) {
        this.listener = listener;
    }

    public void setCenterColor(int centerColor) {
        this.centerColor = centerColor;
        mPaintInner.setColor(centerColor);
        postInvalidate();
    }

    public interface PowerListener{
        void onStart();
        void onProgress(int progress);
        void onSettingFinish(int progress);
    }

    public void setCurrentPostion(float minLimit,float limit,float postion){
        this.mLimit=limit;
        this.minLimit=minLimit;
        setmProgress(postion);
        postInvalidate();
    }

    public void setmProgress(float mProgress) {
        this.mProgress = mProgress;
        postInvalidate();
    }

    public float getmProgress() {
        return mProgress;
    }

    public void release(){
        if(valueAnimator!=null){
            valueAnimator.removeAllUpdateListeners();
        }
    }


    public float getmRadius() {
        return mRadius;
    }

    public float getmBorderWidth() {
        return mBorderWidth;
    }

    public void setJustShow(boolean justShow) {
        isJustShow = justShow;
        invalidate();
    }


}
