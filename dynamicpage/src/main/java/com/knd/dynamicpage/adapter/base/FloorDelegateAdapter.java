package com.knd.dynamicpage.adapter.base;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.knd.common.bean.FloorBean;

public abstract class FloorDelegateAdapter extends BaseDelegateAdapter<FloorBean>{

    public FloorDelegateAdapter(@LayoutRes int layoutId,@NonNull String viewType) {
        super(layoutId, viewType);
    }

    public FloorDelegateAdapter(@LayoutRes int layoutId, int count,@NonNull String viewType) {
        super(layoutId, count, viewType);
    }

}
