package com.knd.common.view.powerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.knd.common.R;
import com.knd.common.utils.ColorUtil;

public class PowerProgressView extends View {
    private int max = 45;
    private int currentValue = 0;//当前值
    private Paint bgLinePaint;//底部彩色线
    private Paint linePaint;//遮罩线
    private Paint thumbPaint;//圆形游标
    private int width;
    private int height;
    //渐变色位置
    float position[] = {0f, 0.3f,0.6f, 1f};
    //对应位置的颜色
    int mColors[] = {Color.parseColor("#37C7A7"),Color.parseColor("#68D908"),Color.parseColor("#F29423"),Color.parseColor("#FF6B6B")};

    public PowerProgressView(Context context) {
        this(context, null);
    }

    public PowerProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PowerProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        //初始化画笔
        bgLinePaint = new Paint();
        bgLinePaint.setAntiAlias(true);
        bgLinePaint.setStrokeWidth(dp2px(6));
        bgLinePaint.setStyle(Paint.Style.STROKE);
        bgLinePaint.setStrokeCap(Paint.Cap.ROUND);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(dp2px(6));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeCap(Paint.Cap.ROUND);

        thumbPaint = new Paint();
        thumbPaint.setAntiAlias(true);
        thumbPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w-dp2px(16);
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        PaintFlagsDrawFilter paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        canvas.setDrawFilter(paintFlagsDrawFilter);
        //绘制底部彩色线渐变色
        LinearGradient linearGradient = new LinearGradient(dp2px(8),height/2,width+dp2px(8),height/2,mColors,position, Shader.TileMode.CLAMP);
        bgLinePaint.setShader(linearGradient);
        canvas.drawLine(dp2px(8),height/2,width+dp2px(8),height/2,bgLinePaint);
        //从右向左绘制灰色遮罩线
        linePaint.setColor(Color.parseColor("#FAFAFA"));
        canvas.drawLine(width*currentValue*0.01f+dp2px(8),height/2,width+dp2px(8),height/2,linePaint);

        //根据位置设置游标颜色
        thumbPaint.setColor(ColorUtil.getColor(currentValue*0.01f, mColors,position));
        canvas.drawCircle(width*currentValue*0.01f+dp2px(8), height/2, dp2px(8), thumbPaint);
        thumbPaint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawCircle(width*currentValue*0.01f+dp2px(8), height/2, dp2px(3), thumbPaint);

    }

    /**
     * 设置实时进度值并换算至0-100
     * @param value
     */
    public void setPower(int value){
        currentValue = value*100/max;
        invalidate();
    }

    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    /**
     * 设置最大值
     * @param maxProgress
     */
    public void setMax(int maxProgress) {
        max = maxProgress;
    }
}
