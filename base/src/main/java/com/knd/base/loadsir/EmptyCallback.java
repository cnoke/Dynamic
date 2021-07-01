package com.knd.base.loadsir;

import com.knd.base.R;
import com.kingja.loadsir.callback.Callback;

public class EmptyCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.base_layout_load_empty;
    }
}
