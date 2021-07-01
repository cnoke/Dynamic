package com.knd.common.view.powerview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryfork.spanny.Spanny;
import com.elvishew.xlog.XLog;
import com.knd.base.util.ScreenUtils;
import com.knd.base.util.SpUtils;
import com.knd.common.R;
import com.knd.common.manager.RouteManager;

import static com.knd.common.key.Flag.FLAG_BACK_POWER;
import static com.knd.common.key.Flag.FLAG_CONTINOUTS_POWER;
import static com.knd.common.key.Flag.FLAG_STATE;

public class LittlePowerSettingView extends RelativeLayout {
    private final int colorNameText;
    private final float sizeNameText;
    private float sizeText;
    private float borderWidth;
    private NewPowerCircleView powerCircleView;
    private TextView powerTv;
    private int basePower=3;
    private int MaxPower=45; //最大力不能超过45
    private Handler mHandler=new Handler();
    private OnProgressChangeListener progressChangeListener;
    private int colorProgress;//进度条
    private int colorText;//力量颜色
    private int mState = 0; //0 卸力状态 1加力状态
    private boolean ispowerview = false;
    private boolean needSaveState;
    private View contentView;
    private TextView tv;

    private int childWidth;
    private int childHeight;
    private int childWidth2;
    private int childHeight2;
    private NewPowerCircleView childView;
    private CircleProgress circleProgress;
    private int circleHeight;
    private int circleWidth;

    public LittlePowerSettingView(Context context) {
        this(context,null);
    }

    public LittlePowerSettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = getContext().obtainStyledAttributes(attrs,R.styleable.common_NewPowerCircleViewStyle);
        colorProgress = ta.getColor(R.styleable.common_NewPowerCircleViewStyle_common_progress_color, Color.BLACK);

        colorText = ta.getColor(R.styleable.common_NewPowerCircleViewStyle_common_progress_text_color, Color.BLACK);
        sizeText = ta.getDimension(R.styleable.common_NewPowerCircleViewStyle_common_progress_text_size, 15);
        borderWidth = ta.getDimension(R.styleable.common_NewPowerCircleViewStyle_common_progress_border_width, 20);

        colorNameText = ta.getColor(R.styleable.common_NewPowerCircleViewStyle_common_progress_text_name_color, Color.BLACK);
        sizeNameText = ta.getDimension(R.styleable.common_NewPowerCircleViewStyle_common_progress_text_name_size, 15);

        ispowerview = ta.getBoolean(R.styleable.common_NewPowerCircleViewStyle_common_progress_ispowerview, false);
        ta.recycle();
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;
        childView = null;
        for(int i = 0 , m = getChildCount() ; i < m ; i++){
            View view = getChildAt(i);
            if(view instanceof NewPowerCircleView){
                childView = (NewPowerCircleView)view;
            }else if(view instanceof CircleProgress){
                circleProgress = (CircleProgress)view;
            }else{
                childWidth2 = view.getMeasuredWidth();
                childHeight2 = view.getMeasuredHeight();
                if(childWidth2 > width){
                    width = childWidth2;
                }
                if(childHeight2 > height){
                    height = childHeight2;
                }

            }
        }
        if(width != 0 && height != 0){
            int longSize = (int) Math.sqrt(width *width + height *height);
            childHeight = longSize + (int)ScreenUtils.dp2px(getContext(),borderWidth);
            childWidth = childHeight;
            circleHeight = childHeight + (int)ScreenUtils.dp2px(getContext(),80);
            circleWidth = circleHeight;
        }

        if(childView != null){
            if(childHeight == 0 || childWidth == 0){
                childHeight = childView.getMeasuredHeight();
                childWidth = childView.getMeasuredWidth();
            }
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec,0,childWidth);
            int childHeightSpec  = getChildMeasureSpec(widthMeasureSpec,0,childHeight);
            childView.measure(childWidthSpec, childHeightSpec );
        }

        if(circleProgress != null){
            if(circleHeight == 0 || circleWidth == 0){
                circleHeight = circleProgress.getMeasuredHeight();
                circleWidth = circleProgress.getMeasuredWidth();
            }
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec,0,circleWidth);
            int childHeightSpec  = getChildMeasureSpec(widthMeasureSpec,0,circleHeight);
            circleProgress.measure(childWidthSpec, childHeightSpec );
        }

        if(ispowerview){
            setMeasuredDimension(circleWidth, circleHeight);
        }else{
            setMeasuredDimension(childWidth, childHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for(int i = 0 , m = getChildCount() ; i < m ; i++){
            View view = getChildAt(i);
            if(ispowerview){
                if(view instanceof NewPowerCircleView){
                    childView.layout(circleWidth/ 2 - childWidth/ 2, circleHeight/ 2 - childHeight/ 2, circleWidth / 2 + childWidth / 2, circleHeight/ 2 + childHeight/ 2);
                }else if(view instanceof CircleProgress){
                    circleProgress.layout(0, 0, circleWidth, circleHeight);
                }else {
                    view.layout(circleWidth/ 2 - childWidth2/ 2, circleHeight/ 2 - childHeight2/ 2, circleWidth / 2 + childWidth2 / 2, circleHeight/ 2 + childHeight2/ 2);
                }
            }else{
                if(view instanceof NewPowerCircleView){
                    childView.layout(0, 0, childWidth, childHeight);
                }else {
                    view.layout(childWidth/ 2 - childWidth2/ 2, childHeight/ 2 - childHeight2/ 2, childWidth / 2 + childWidth2 / 2, childHeight/ 2 + childHeight2/ 2);
                }
            }
        }
    }

    private void initView(){

        contentView = LayoutInflater.from(getContext()).inflate(R.layout.common_item_power,null,false);

        powerTv = contentView.findViewById(R.id.value);
        powerTv.setTextSize(sizeText);
        powerTv.setTextColor(ColorStateList.valueOf(colorText));

        TextView tvDef = contentView.findViewById(R.id.tv_def);
        tvDef.setTextSize(sizeText);

        tv = contentView.findViewById(R.id.tv);
        tv.setTextSize(sizeNameText);

        if(ispowerview){
            contentView.setOnClickListener(v -> changeState());
            tv.setTextColor(ColorStateList.valueOf(colorNameText));
            tv.setText("基础力");
        }

        powerCircleView = new NewPowerCircleView(getContext(),borderWidth, colorProgress);
        powerCircleView.setCenterColor(0xFFE6E6E6);//默认state为0，默认状态为卸力颜色

        if(ispowerview){
            circleProgress = (CircleProgress)LayoutInflater.from(getContext()).inflate(R.layout.common_layout_circle,null,false);
            addView(circleProgress);
        }

        addView(powerCircleView);
        addView(contentView);

        if(ispowerview){
            powerCircleView.setCurrentPostion(3,45,3);
            setText(3+"");
        }else{
            powerCircleView.setCurrentPostion(0,45,0);
            setText(0+"");
        }

        powerCircleView.setListener(new NewPowerCircleView.PowerListener() {
            @Override
            public void onStart() {
                if(progressChangeListener!=null)
                    progressChangeListener.onStart();
            }

            @Override
            public void onProgress(int progress) {
                setPower(progress);
                if(progressChangeListener!=null)
                    progressChangeListener.onProgress(progress);
            }

            @Override
            public void onSettingFinish(int progress) {
                    //基础力
                basePower=progress;
                if(progressChangeListener!=null)
                    progressChangeListener.onEnd(progress);
            }
        });
    }

    private void setText(String text){
        powerTv.setText(new Spanny(text,new ForegroundColorSpan(colorText),new StyleSpan(Typeface.BOLD)).append("Kg",new ForegroundColorSpan(0xFFACACAC),new RelativeSizeSpan(0.33f)));
    }

    public void release(){
        powerCircleView.release();
        mHandler.removeCallbacksAndMessages(null);
    }

    public void setCanTouch(boolean canTouch){
        powerCircleView.setCanTouch(canTouch);
    }

    public boolean isCanTouch() {
        return powerCircleView.isCanTouch();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void changeState(){
        int continuosPower = (int)SpUtils.getInstance().getData(FLAG_CONTINOUTS_POWER,0);
        int backPower = (int)SpUtils.getInstance().getData(FLAG_BACK_POWER,0);
        if(mState==0){
            mState=1;
            XLog.d("用户点击加力");
            powerCircleView.setCenterColor(0xFFFFFFFF);
            if(needSaveState) SpUtils.getInstance().putData(FLAG_STATE,1);
        }else{
            mState=0;
            XLog.d("用户点击卸力");
            powerCircleView.setCenterColor(0xFFE6E6E6);
            if(needSaveState) SpUtils.getInstance().putData(FLAG_STATE,0);
        }
    }

    public void setState(boolean isAddPower,boolean needSaveState){
        if(isAddPower){
            if(mState==1) return;
            mState=1;
            if(needSaveState)
                SpUtils.getInstance().putData(FLAG_STATE,1);
            powerCircleView.setCenterColor(0xFFFFFFFF);
        }else{
            if(mState==0) return;
            mState=0;
            if(needSaveState)
                SpUtils.getInstance().putData(FLAG_STATE,0);
            powerCircleView.setCenterColor(0xFFE6E6E6);
        }
    }

    public void setNeedSaveState(boolean needSaveState) {
        this.needSaveState = needSaveState;
    }

    public int getState() {
        return mState;
    }

    public int getPower(){
        return basePower;
    }

    public void setLimit(int maxLimit,int progress){
        this.basePower = progress;
        powerCircleView.setCurrentPostion(0f,maxLimit,progress);
        setText(progress+"");
    }

    public void setPower(int power) {
        this.basePower = power;
        setText(power+"");
        powerCircleView.setmProgress(basePower);
    }


    public void setProgressChangeListener(OnProgressChangeListener progressChangeListener) {
        this.progressChangeListener = progressChangeListener;
    }

    public void setValue(int realtimePower ,boolean isPowerLimited) {
        if(circleProgress != null){
            circleProgress.setValue(realtimePower);
        }
        if (isPowerLimited){
            tv.setText("功率限制中");
        }else {
            tv.setText("基础力");
        }
    }

    public interface OnProgressChangeListener{
        void onStart();
        void onProgress(int progress);
        void onEnd(int progress);
    }
}
