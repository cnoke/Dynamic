package com.knd.common.fragment.mvp;

import com.knd.base.activity.IBaseView;
import com.knd.common.activity.mvp.ProxyImpl;

public class ProxyFragment<V extends IBaseView> extends ProxyImpl {
    public ProxyFragment(V view) {
        super(view);
    }
}
