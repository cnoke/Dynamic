package com.knd.common.view.powerview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.knd.base.util.SpUtils;
import com.knd.common.R;
import com.knd.common.event.PowerChangeEvent;
import com.knd.common.manager.RouteManager;

import org.greenrobot.eventbus.EventBus;

import static com.knd.common.key.Flag.FLAG_BACK_POWER;
import static com.knd.common.key.Flag.FLAG_BASE_POWER;
import static com.knd.common.key.Flag.FLAG_CONTINOUTS_POWER;
import static com.knd.common.key.Flag.FLAG_STATE;

public class CommonPowerView extends RelativeLayout {

//    private CommonLayoutPowerVeiwBinding viewBinding;
    private View view;
    private LittlePowerSettingView newPowerView,conPowerView,backPowerView;
    private LinearLayout conPowerViewLly,backPowerViewLly;
    private TextView powerViewUnableTv;
//    private CircleProgress currentPowerCircle;

    private OnProgressChangeListener progressChangeListener;
    private Handler mHandler = new Handler();
    private int basePower = 0;
    private int continuosPower=0;
    private int backPower=0;
    private int MaxPower=45; //最大力不能超过45
    private float mSetContinuosPowerPercent=0f;//手动设置的持续力占当前基础力的百分比
    private float mSetBackPowerPercent=0f;//手动设置的回心力占当前基础力的百分比
    private int conLimit = 0;//持续力最大值
    private int backLimit = 0;//回心力最大值
    private boolean needAutoClose = true;
    private boolean needShowAnim = true;//是否需要显示回心力持续力力量盘

    public enum TypeEnum {
        CON_POWER,//持续力
        BACK_POWER,//回心力
        BASIS_POWER//基础力
    }

    public CommonPowerView(Context context) {
        this(context,null);
    }

    public CommonPowerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    private void initView(){
        view = LayoutInflater.from(getContext()).inflate(R.layout.common_layout_power_veiw,null,false);
//        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.common_layout_power_veiw, null, false);
        LayoutParams lp= new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(CENTER_IN_PARENT);
//        viewBinding.getRoot().setLayoutParams(lp);
//        addView(viewBinding.getRoot());
        view.setLayoutParams(lp);
        addView(view);

        newPowerView = view.findViewById(R.id.new_powerView);
        conPowerView = view.findViewById(R.id.con_power_view);
        backPowerView = view.findViewById(R.id.back_power_view);
        conPowerViewLly = view.findViewById(R.id.con_power_view_lly);
        backPowerViewLly = view.findViewById(R.id.back_power_view_lly);
        powerViewUnableTv = view.findViewById(R.id.power_view_unable_tv);
//        currentPowerCircle = view.findViewById(R.id.current_power_circle);

        newPowerView.setProgressChangeListener(new LittlePowerSettingView.OnProgressChangeListener() {
            @Override
            public void onStart() {
                showPowerViewAnim();
                mHandler.removeCallbacks(closePowerViewRun);

                if(progressChangeListener != null){
                    progressChangeListener.onStart(TypeEnum.BASIS_POWER);
                }

            }

            @Override
            public void onProgress(int progress) {
                //调整基础力
                basePower = progress;
                if((progress+progress*mSetContinuosPowerPercent+progress*mSetBackPowerPercent)>45){
                    if(mSetBackPowerPercent*100>0 || mSetContinuosPowerPercent*100>0){
                        if(mSetBackPowerPercent*100==0){
                            continuosPower= 45-progress;
                        }
                        if(mSetContinuosPowerPercent*100==0){
                            backPower= 45-progress;
                        }
                        if(1-mSetContinuosPowerPercent>0.6-mSetBackPowerPercent){
                            backPower= (int)((45-progress)*mSetBackPowerPercent/(mSetContinuosPowerPercent+mSetBackPowerPercent));
                            continuosPower=45-progress-backPower;
                        }else{
                            continuosPower= (int) ((45-progress)*mSetContinuosPowerPercent/(mSetContinuosPowerPercent+mSetBackPowerPercent));
                            backPower=45-progress-continuosPower;
                        }
                    }
                }else{
                    float temp1= (45-progress)*(mSetContinuosPowerPercent/(mSetContinuosPowerPercent+mSetBackPowerPercent))/progress;
                    float temp2= (45-progress)*(mSetBackPowerPercent/(mSetContinuosPowerPercent+mSetBackPowerPercent))/progress;

                    if(temp1<mSetContinuosPowerPercent){
                        continuosPower=(int)(progress*temp1);
                    }else{
                        continuosPower=(int)(progress*mSetContinuosPowerPercent);
                    }

                    if(temp2<mSetBackPowerPercent){
                        backPower=(int)(progress*temp2);
                    }else{
                        backPower=(int)(progress*mSetBackPowerPercent);
                    }
                }
                setConAndBackLimit();
                if(progressChangeListener != null){
                    progressChangeListener.onProgress(TypeEnum.BASIS_POWER,progress);
                }
            }

            @Override
            public void onEnd(int progress) {
                basePower=progress;
                operationAfterProgress(false);
                if(progressChangeListener != null){
                    progressChangeListener.onEnd(TypeEnum.BASIS_POWER,progress);
                }
            }
        });

        conPowerView.setProgressChangeListener(new LittlePowerSettingView.OnProgressChangeListener() {
            @Override
            public void onStart() {
                mHandler.removeCallbacks(closePowerViewRun);
                if(progressChangeListener != null){
                    progressChangeListener.onStart(TypeEnum.CON_POWER);
                }
            }

            @Override
            public void onProgress(int progress) {
                continuosPower = progress;
                setConPowerLimit();
                if(progressChangeListener != null){
                    progressChangeListener.onProgress(TypeEnum.CON_POWER,progress);
                }
            }

            @Override
            public void onEnd(int progress) {
                if(continuosPower!=progress){
                    continuosPower=  progress;
                    mSetContinuosPowerPercent=continuosPower*1.0f/basePower;
                    mSetBackPowerPercent=backPower*1.0f/basePower;
                }
                operationAfterProgress(true);
                if(progressChangeListener != null){
                    progressChangeListener.onEnd(TypeEnum.CON_POWER,progress);
                }
            }
        });

        backPowerView.setProgressChangeListener(new LittlePowerSettingView.OnProgressChangeListener() {
            @Override
            public void onStart() {
                mHandler.removeCallbacks(closePowerViewRun);
                if(progressChangeListener != null){
                    progressChangeListener.onStart(TypeEnum.BACK_POWER);
                }
            }

            @Override
            public void onProgress(int progress) {
                backPower = progress;
                setBackPowerLimit();
                if(progressChangeListener != null){
                    progressChangeListener.onProgress(TypeEnum.BACK_POWER,progress);
                }
            }

            @Override
            public void onEnd(int progress) {
                //回心力
                if(backPower!=progress){
                    backPower=  progress;
                    mSetBackPowerPercent=progress*1.0f/basePower;
                    mSetContinuosPowerPercent=continuosPower*1.0f/basePower;
                }
                operationAfterProgress(true);
                if(progressChangeListener != null){
                    progressChangeListener.onEnd(TypeEnum.BACK_POWER,progress);
                }
            }
        });


        basePower= (int) SpUtils.getInstance().getData(FLAG_BASE_POWER,3);
        continuosPower= (int) SpUtils.getInstance().getData(FLAG_CONTINOUTS_POWER,0);
        backPower= (int) SpUtils.getInstance().getData(FLAG_BACK_POWER,0);
        int state= (int) SpUtils.getInstance().getData(FLAG_STATE,0);

        mSetContinuosPowerPercent=continuosPower*1.0f/basePower;
        mSetBackPowerPercent=backPower*1.0f/basePower;

        newPowerView.setPower(basePower);
        conPowerView.setPower(continuosPower);
        backPowerView.setPower(backPower);
        newPowerView.setState(state==1,false);
        newPowerView.setNeedSaveState(false);

    }

    public void setNeedSaveState(boolean needSaveState){
        newPowerView.setNeedSaveState(needSaveState);
    }

    public void setNeedShowAnim(boolean needShowAnim){
        this.needShowAnim = needShowAnim;
    }

    public void setCanTouch(boolean canTouch){
        newPowerView.setCanTouch(canTouch);
    }

    public boolean isCanTouch() {
        return newPowerView.isCanTouch();
    }

    /**
     * 更新力量盘实时力
     * @param realtimePower 实时力数值
     * @param isPowerLimited 是否为力量限制状态
     */
    public void setValue(int realtimePower, boolean isPowerLimited) {
        newPowerView.setValue(realtimePower, isPowerLimited);
    }

    public void setState(boolean isAddPower,boolean needSaveState){
        getPowerSettingView(TypeEnum.BASIS_POWER).setState(isAddPower,needSaveState);
    }

    public void setPower(int baseP, int conP, int backP, boolean isAuto) {
        getPowerSettingView(TypeEnum.BASIS_POWER).setPower(baseP);

        if(!isAuto){
            this.mSetContinuosPowerPercent=conP*1.0f/baseP;
            this.mSetBackPowerPercent=backP*1.0f/baseP;
        }
    }

    //不发送变力指令到下控
    public void setPowerNoChange(int baseP, int conP, int backP, boolean isAuto){
        getPowerSettingView(TypeEnum.BASIS_POWER).setPower(baseP);
        if(!isAuto){
            this.mSetContinuosPowerPercent=conP*1.0f/baseP;
            this.mSetBackPowerPercent=backP*1.0f/baseP;
        }
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        if (clickable){
            powerViewUnableTv.setVisibility(GONE);
        }else {
            powerViewUnableTv.setVisibility(VISIBLE);
        }
    }

    //设置持续力和回心力的限制
    private void setConAndBackLimit(){
        setBackPowerLimit();
        setConPowerLimit();
    }

    //设置持续力的限制
    private void setConPowerLimit(){
        //持续力
        conLimit=MaxPower-backPower-basePower;
        if(conLimit>basePower) conLimit=basePower;
        if(conLimit<continuosPower){
            continuosPower=conLimit;
            conPowerView.setPower(continuosPower);
        }
//        Log.e("conp",conLimit+"--"+continuosPower);
        conPowerView.setLimit(conLimit,continuosPower);
    }

    //设置回心力的限制
    private void setBackPowerLimit(){
        //回心力
        backLimit=MaxPower-continuosPower-basePower;
        int limit2=basePower*60/100;
        if(backLimit>limit2) backLimit=limit2;
        if(backLimit < backPower){
            backPower=backLimit;
            backPowerView.setPower(backPower);
        }
        backPowerView.setLimit(backLimit,backPower);
    }

    //力量盘调整结束时的操作
    private void operationAfterProgress(boolean resetRadio){
        if(needAutoClose){
            closePowerViewsLater();
        }
        SpUtils.getInstance().putData(FLAG_BASE_POWER,basePower);
        SpUtils.getInstance().putData(FLAG_CONTINOUTS_POWER,continuosPower);
        SpUtils.getInstance().putData(FLAG_BACK_POWER,backPower);
        //如果是加力状态，直接发送力量改变信号
        int mState = newPowerView.getState();
        EventBus.getDefault().post(new PowerChangeEvent(mState,basePower,backPower,continuosPower,resetRadio));

    }

    //弹出回心力持续力力量盘的动画
    public void showPowerViewAnim(){
        if (!needShowAnim)return;//力量测试不需要回心力持续力
        if (isPowerViewOpen)return;
        conPowerViewLly.setVisibility(View.VISIBLE);
        backPowerViewLly.setVisibility(View.VISIBLE);
        final AnimatorSet translationAnimatorSet = new AnimatorSet();
        translationAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(conPowerViewLly, "translationX", -350f)
                        .setDuration(500),
                ObjectAnimator.ofFloat(conPowerViewLly, "alpha", 0, 1)
                        .setDuration(500),
                ObjectAnimator.ofFloat(backPowerViewLly, "translationX", 350f)
                        .setDuration(500),
                ObjectAnimator.ofFloat(backPowerViewLly, "alpha", 0, 1)
                        .setDuration(500)
        );
        translationAnimatorSet.start();
        isPowerViewOpen = true;
    }
    //收起回心力持续力力量盘的动画
    public void closePowerViewAnim(){
        if (!needShowAnim)return;//力量测试不需要回心力持续力
        final AnimatorSet translationAnimatorSet = new AnimatorSet();
        translationAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(conPowerViewLly, "translationX", 0f)
                        .setDuration(400),
                ObjectAnimator.ofFloat(conPowerViewLly, "alpha", 1, 0, 0)
                        .setDuration(400),
                ObjectAnimator.ofFloat(backPowerViewLly, "translationX", 0f)
                        .setDuration(400),
                ObjectAnimator.ofFloat(backPowerViewLly, "alpha", 1, 0, 0)
                        .setDuration(400)
        );
        translationAnimatorSet.start();
        isPowerViewOpen = false;
    }

    //三秒后收起小力量盘
    boolean isPowerViewOpen = false;
    Runnable closePowerViewRun = new Runnable() {
        @Override
        public void run() {
            closePowerViewAnim();
        }
    };

    public void notAutoClose(){
        needAutoClose = false;
    }

    private void closePowerViewsLater(){
        mHandler.removeCallbacks(closePowerViewRun);
        mHandler.postDelayed(closePowerViewRun,3000);
    }

    public void setProgressChangeListener(OnProgressChangeListener progressChangeListener) {
        this.progressChangeListener = progressChangeListener;
    }

    public interface OnProgressChangeListener{
        void onStart(TypeEnum typeEnum);
        void onProgress(TypeEnum typeEnum, int progress);
        void onEnd(TypeEnum typeEnum, int progress);
    }

    public int getBasePower(){
        return basePower;
    }

    public int getContinuosPower() {
        return continuosPower;
    }

    public int getBackPower() {
        return backPower;
    }

    public int getState() {
        return getPowerSettingView(TypeEnum.BASIS_POWER).getState();
    }

    private LittlePowerSettingView getPowerSettingView(TypeEnum typeEnum){
        LittlePowerSettingView littlePowerSettingView;
        switch (typeEnum){
            case BACK_POWER:
                littlePowerSettingView = backPowerView;
                break;
            case CON_POWER:
                littlePowerSettingView = conPowerView;
                break;
            default:
                littlePowerSettingView = newPowerView;
                break;
        }
        return littlePowerSettingView;
    }
}
