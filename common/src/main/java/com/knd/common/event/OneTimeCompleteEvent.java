package com.knd.common.event;

public class OneTimeCompleteEvent {
    public float kcal;//单次卡路里
    public int mMaxPower; //单次最大功率
    public int mMaxStrength; //单次最大发力
    public float expolsiveF; //单次爆发力

    public OneTimeCompleteEvent(int mMaxPower, int mMaxStrength, float kcal, float expolsiveF) {
        this.mMaxPower = mMaxPower;
        this.mMaxStrength = mMaxStrength;
        this.kcal = kcal;
        this.expolsiveF = expolsiveF;
    }
}
