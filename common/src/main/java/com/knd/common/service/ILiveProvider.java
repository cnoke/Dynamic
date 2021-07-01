package com.knd.common.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface ILiveProvider extends IProvider {
    void roomReserveList(Context context);
}
