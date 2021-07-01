package com.knd.dynamicpage.adapter.base;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.knd.common.bean.ElementBean;

public abstract class ElementDelegateAdapter extends BaseDelegateAdapter<ElementBean> {


    public ElementDelegateAdapter(@LayoutRes int layoutId,@NonNull String viewType) {
        super(layoutId, viewType);
    }

    public ElementDelegateAdapter(@LayoutRes int layoutId, int count,@NonNull String viewType) {
        super(layoutId, count, viewType);
    }

}
