package com.knd.base.loadsir;

import android.content.Context;
import android.view.View;

import com.knd.base.R;
import com.kingja.loadsir.callback.Callback;

public class ErrorCallback extends Callback {


    @Override
    protected int onCreateView() {
        return R.layout.base_layout_load_error;
    }

}
