package com.knd.common.view.powerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.knd.common.R;

public class RotatePowerSettingView extends View {
    private OnProgressChangeListener listener;
    // 控件宽
    private int width;
    // 控件高
    private int height;
    // 刻度盘半径
    private int dialRadius;
    // 中心按钮半径
    private int btnRadius;
    // 刻度高
    private int scaleHeight = dp2px(9);
    // 刻度盘画笔
    private Paint dialPaint;
    // 刻度盘刻度点画笔
    private Paint dialPointPaint;
    // 文字画笔
    private Paint titlePaint;
    // 旋转按钮画笔
    private Paint buttonPaint;
    // 力量显示画笔
    private Paint tempPaint;
    // 文本提示
    private String title = "基础力";
    // 刻度
    private int mProgress = 0;
    // 最小刻度值
    private int minPower = 0;
    // 最大刻度
    private int maxPower = 45;
    // 最大力限制
    private int maxLimit = 45;
    // 最小力限制
    private int minLimit = 0;
    // 2格代表1kg
    private int angleRate = 2;
    // 每格的角度
    private float angleOne;
    // 按钮图片
    private Bitmap buttonImage = BitmapFactory.decodeResource(getResources(),
            R.mipmap.btn_rotate).copy(Bitmap.Config.ARGB_8888, true);
    // 抗锯齿
    private PaintFlagsDrawFilter paintFlagsDrawFilter;
    // 控件点击监听
    private OnClickListener onClickListener;

    // 以下为旋转按钮相关

    // 当前按钮旋转的角度
    private float rotateAngle;
    // 当前的角度
    private float currentAngle;
    // 控件宽度
    private boolean isBasePowerView;
    /**
     * 是否可以旋转
     */
    private boolean canRotate = true;

    public RotatePowerSettingView(Context context) {
        this(context, null);
    }

    public RotatePowerSettingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotatePowerSettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = getContext().obtainStyledAttributes(attrs,R.styleable.common_NewPowerCircleViewStyle);
        isBasePowerView = ta.getBoolean(R.styleable.common_NewPowerCircleViewStyle_common_progress_ispowerview, true);
        title = ta.getString(R.styleable.common_NewPowerCircleViewStyle_common_power_text);
        if (isBasePowerView){
            angleRate = 3;
            scaleHeight = dp2px(12);
            mProgress = 3;
            minLimit = 3;
        }
        angleOne = (float) 360 / (maxPower - minPower) / angleRate;
        ta.recycle();
        init();
    }

    private void init() {
        dialPaint = new Paint();
        dialPaint.setAntiAlias(true);
        dialPaint.setStrokeWidth(dp2px(1));
        dialPaint.setStyle(Paint.Style.STROKE);

        dialPointPaint = new Paint();
        dialPointPaint.setAntiAlias(true);
        dialPointPaint.setStyle(Paint.Style.FILL);

        titlePaint = new Paint();
        titlePaint.setAntiAlias(true);
        if (isBasePowerView){
            titlePaint.setTextSize(sp2px(14));
        }else {
            titlePaint.setTextSize(sp2px(12));
        }
        titlePaint.setColor(Color.parseColor("#A7A9A8"));
        titlePaint.setStyle(Paint.Style.STROKE);

        buttonPaint = new Paint();
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

        tempPaint = new Paint();
        tempPaint.setAntiAlias(true);
        if (isBasePowerView){
            tempPaint.setTextSize(sp2px(40));
            tempPaint.setColor(Color.parseColor("#37C7A7"));
        }else {
            tempPaint.setTextSize(sp2px(26));
            tempPaint.setColor(Color.parseColor("#F29423"));
        }
        tempPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 控件宽、高
        width = height = Math.min(h, w);
        // 刻度盘半径
        dialRadius = width / 2 - dp2px(10);
        // 按钮半径
        if (isBasePowerView){
            btnRadius = width / 2;
        }else {
            btnRadius = width / 2 - dp2px(5);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScale(canvas);
//        drawArc(canvas);
        drawButton(canvas);
        drawText(canvas);
        drawTemp(canvas);
    }


    /**
     * 绘制刻度盘
     *
     * @param canvas 画布
     */
    private void drawScale(Canvas canvas) {
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        // 顺时针旋转180度
        canvas.rotate(180);
        //未达到的刻度颜色
        dialPaint.setColor(Color.parseColor("#CECBCB"));
        dialPointPaint.setColor(Color.parseColor("#CECBCB"));
        for (int i = angleRate * maxPower; i > angleRate * mProgress; i--) {
            canvas.drawLine(0, -dialRadius, 0, -dialRadius + scaleHeight, dialPaint);
            if (i==0||i==angleRate*maxPower||i==angleRate*maxPower/2||i==angleRate*maxPower/4||i==angleRate*maxPower*3/4||i==angleRate*maxPower/8||i==angleRate*maxPower*3/8||i==angleRate*maxPower*5/8||i==angleRate*maxPower*7/8){
                canvas.drawCircle(0, -dialRadius + scaleHeight,dp2px(2), dialPointPaint);
            }
            canvas.rotate(-angleOne);
        }

        //已经达到的颜色
        dialPaint.setColor(Color.parseColor("#6AE600"));
        for (int i = mProgress * angleRate; i >= minPower * angleRate; i--) {
            canvas.drawLine(0, -dialRadius, 0, -dialRadius + scaleHeight, dialPaint);
            if (i==0||i==angleRate*maxPower||i==angleRate*maxPower/2||i==angleRate*maxPower/4||i==angleRate*maxPower*3/4||i==angleRate*maxPower/8||i==angleRate*maxPower*3/8||i==angleRate*maxPower*5/8||i==angleRate*maxPower*7/8){
                canvas.drawCircle(0, -dialRadius + scaleHeight,dp2px(2), dialPointPaint);
            }
            canvas.rotate(-angleOne);
        }
        canvas.restore();
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     */
    private void drawText(Canvas canvas) {
        canvas.save();
        // 绘制标题
        float titleWidth = titlePaint.measureText(title);
        float yPos = dialRadius + dp2px(30);
        if (isBasePowerView){
            yPos = dialRadius + dp2px(40);
        }
        canvas.drawText(title, (width - titleWidth) / 2, yPos, titlePaint);
    }

    /**
     * 绘制旋转按钮
     *
     * @param canvas 画布
     */
    private void drawButton(Canvas canvas) {
        buttonImage = setImgSize(buttonImage,btnRadius*2,btnRadius*2);
        // 按钮宽高
        int buttonWidth = buttonImage.getWidth();
        int buttonHeight = buttonImage.getHeight();
        Matrix matrix = new Matrix();
        // 设置按钮位置，移动到控件中心
        matrix.setTranslate((width - buttonWidth) / 2, (height - buttonHeight) / 2);
        // 设置旋转角度，旋转中心为控件中心，当前也是按钮中心
        matrix.postRotate(rotateAngle, width / 2, height / 2);

        //设置抗锯齿
        canvas.setDrawFilter(paintFlagsDrawFilter);
        canvas.drawBitmap(buttonImage, matrix, buttonPaint);

        canvas.save();
        Paint lPaint = new Paint();
        lPaint.setAntiAlias(true);
        lPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(180+rotateAngle);
        if (isBasePowerView){
            lPaint.setStrokeWidth(dp2px(2));
            lPaint.setColor(Color.parseColor("#6AE600"));
            dialPointPaint.setColor(Color.parseColor("#6AE600"));
            canvas.drawLine(0, -btnRadius+dp2px(40), 0, -btnRadius + dp2px(60), lPaint);
            canvas.drawCircle(0, -btnRadius + dp2px(66),dp2px(3), dialPointPaint);
        }else {
            lPaint.setStrokeWidth(dp2px(1));
            lPaint.setColor(Color.parseColor("#F29423"));
            canvas.drawLine(0, -btnRadius+dp2px(26), 0, -btnRadius + dp2px(40), lPaint);
        }
        canvas.restore();

//        canvas.save();
//        Paint lPaint = new Paint();
//        lPaint.setAntiAlias(true);
//        lPaint.setStrokeWidth(dp2px(2));
//        lPaint.setStyle(Paint.Style.STROKE);
//        lPaint.setColor(Color.parseColor("#6AE600"));
//        canvas.drawLine(0, -btnRadius, 0, -btnRadius + dp2px(30), lPaint);
//        canvas.rotate(rotateAngle);
//        canvas.restore();
    }

    /**
     * 绘制力量显示
     *
     * @param canvas 画布
     */
    private void drawTemp(Canvas canvas) {
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);

        float tempWidth = tempPaint.measureText(mProgress + "");
        float tempHeight = (tempPaint.ascent() + tempPaint.descent()) / 2;
        canvas.drawText(mProgress + "", -tempWidth / 2 - dp2px(10), -tempHeight-dp2px(10), tempPaint);
        canvas.drawText("kg", tempWidth / 2 - dp2px(5), -tempHeight-dp2px(10), titlePaint);
        canvas.restore();
    }

    private boolean isDown;
    private boolean isMove;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!canRotate) {
            return super.onTouchEvent(event);
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isDown = true;
                    float downX = event.getX();
                    float downY = event.getY();
                    currentAngle = calcAngle(downX, downY);
                    if(listener!=null) listener.onStart();
                    break;

                case MotionEvent.ACTION_MOVE:
                    isMove = true;
                    float targetX;
                    float targetY;
                    downX = targetX = event.getX();
                    downY = targetY = event.getY();
                    float angle = calcAngle(targetX, targetY);
                    if (angle<90&&angle>=0)angle+=360;

                    // 滑过的角度增量
                    float angleIncreased = angle - currentAngle;
                    // 防止越界
                    if (angleIncreased < -360) {
                        angleIncreased = angleIncreased + 360;
                    } else if (angleIncreased > 360) {
                        angleIncreased = angleIncreased - 360;
                    }

                    if ((angleIncreased>200||angleIncreased<-200)){

                    }else {
                        if(listener!=null){
                            listener.onProgress((int) mProgress);
                        }
                        IncreaseAngle(angleIncreased);
                    }
                    currentAngle = angle;
                    break;

                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP: {
                    if (isDown) {
                        if (isMove) {
                            // 纠正指针位置
                            rotateAngle = (float) ((mProgress - minPower) * angleRate * angleOne);
                            invalidate();
                            isMove = false;
                            if(listener!=null){
                                listener.onEnd((int) mProgress);
                            }
                        } else {
                            // 点击事件
                            if (onClickListener != null) {
                                onClickListener.onClick(mProgress);
                            }
                        }
                        isDown = false;
                    }
                    break;
                }
            }
            return true;
        }
    }

    /**
     * 以按钮圆心为坐标圆点，建立坐标系，求出(targetX, targetY)坐标与x轴的夹角
     *
     * @param targetX x坐标
     * @param targetY y坐标
     * @return (targetX, targetY)坐标与x轴的夹角
     */
    private float calcAngle(float targetX, float targetY) {
        float x = targetX - width / 2;
        float y = targetY - height / 2;
        double radian;

        if (x==0)x-=1;
        if (y==0)y-=1;

        if (x != 0) {
            float tan = Math.abs(y / x);
            if (x > 0) {
                if (y >= 0) {
                    radian = Math.atan(tan);
                } else {
                    radian = 2 * Math.PI - Math.atan(tan);
                }
            } else {
                if (y >= 0) {
                    radian = Math.PI - Math.atan(tan);
                } else {
                    radian = Math.PI + Math.atan(tan);
                }
            }
        } else {
            if (y==0){
                y-=1;
            }
            if (y > 0) {
                radian = Math.PI / 2;
            } else {
                radian = -Math.PI / 2;
            }
        }
        return (float) ((radian * 180) / Math.PI);
    }

    /**
     * 增加旋转角度
     *
     * @param angle 增加的角度
     */
    private void IncreaseAngle(float angle) {
        if (isBasePowerView){
            if (mProgress==3&&angle<=0)return;
        }
        rotateAngle += angle;
        if (rotateAngle < 0) {
            rotateAngle = 0;
        } else if (rotateAngle > 360) {
            rotateAngle = 360;
        }
        // 加上0.5是为了取整时四舍五入
        mProgress = (int) ((rotateAngle / angleOne) / angleRate + 0.5) + minPower;
        if (mProgress<minLimit){
            mProgress = minLimit;
            // 计算每格的角度
            angleOne = (float) 360 / (maxPower - minPower) / angleRate;
            // 计算旋转角度
            rotateAngle = (float) ((mProgress - minPower) * angleRate * angleOne);
        }else if (mProgress>maxLimit){
            mProgress = maxLimit;
            // 计算每格的角度
            angleOne = (float) 360 / (maxPower - minPower) / angleRate;
            // 计算旋转角度
            rotateAngle = (float) ((mProgress - minPower) * angleRate * angleOne);
        }
        invalidate();
    }

    /**
     * 设置几格代表1度，默认4格
     *
     * @param angleRate 几格代表1度
     */
    public void setAngleRate(int angleRate) {
        this.angleRate = angleRate;
    }

    /**
     * 设置旋钮是否可以旋转
     *
     * @param canRotate
     */
    public void setCanRotate(boolean canRotate) {
        this.canRotate = canRotate;
    }

    public boolean getCanRotate() {
        return this.canRotate;
    }

    /**
     * 设置点击监听
     *
     * @param onClickListener 点击回调接口
     */
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 设置力
     * @param power    设置的值
     */
    public void setPower(int power) {
        if (power < minPower) {
            this.mProgress = minPower;
        } else {
            this.mProgress = power;
        }
        // 计算每格的角度
        angleOne = (float) 360 / (maxPower - minPower) / angleRate;
        // 计算旋转角度
        rotateAngle = (float) ((power - minPower) * angleRate * angleOne);
        invalidate();
    }

    public void setLimit(int maxLimit,int progress){
        this.mProgress = progress;
        this.maxLimit = maxLimit;
        // 计算每格的角度
        angleOne = (float) 360 / (maxPower - minPower) / angleRate;
        // 计算旋转角度
        rotateAngle = (float) ((progress - minPower) * angleRate * angleOne);
        invalidate();
    }

    public int getPower() {
        return mProgress;
    }

    /**
     * 点击回调接口
     */
    public interface OnClickListener {
        /**
         * 点击回调方法
         *
         */
        void onClick(int temp);
    }

    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

    public void setProgressChangeListener(OnProgressChangeListener progressChangeListener) {
        this.listener = progressChangeListener;
    }

    public interface OnProgressChangeListener{
        void onStart();
        void onProgress(int progress);
        void onEnd(int progress);
    }

    public Bitmap setImgSize(Bitmap bm, int newWidth ,int newHeight){
        // 获得图片的宽高.
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例.
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片.
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }
}