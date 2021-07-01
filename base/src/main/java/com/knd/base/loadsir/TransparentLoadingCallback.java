package com.knd.base.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.knd.base.R;

public class TransparentLoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.base_view_transparent;
    }
}
