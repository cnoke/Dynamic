package com.knd.common.event;

import android.util.Log;

public class PowerChangeEvent {
    public int state;//0 卸力 1 加力
    public int mBasePower;
    public int mBackPower;
    public int mContiousePower;
    public boolean resetRadio;//重新设置持续力和回心力的比例
    public PowerChangeEvent(int state, int mBasePower, int mBackPower, int mContiousePower, boolean resetRadio) {
        this.state = state;
        this.mBasePower = mBasePower;
        this.mBackPower = mBackPower;
        this.mContiousePower = mContiousePower;
        this.resetRadio=resetRadio;

        Log.e("com.database","post data==="+mBasePower+","+mContiousePower+","+mBackPower);
    }
}
