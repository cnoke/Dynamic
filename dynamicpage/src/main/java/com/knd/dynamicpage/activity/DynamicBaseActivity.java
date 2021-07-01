package com.knd.dynamicpage.activity;

import android.widget.ImageView;

import androidx.databinding.ViewDataBinding;

import com.knd.common.activity.mvp.MvpActivity;
import com.knd.dynamicpage.R;

public abstract class DynamicBaseActivity<V extends ViewDataBinding> extends MvpActivity<V> {

    @Override
    protected void initView() {
        super.initView();
        ImageView iv= findViewById(R.id.iv_back);
        if(iv!=null){
            iv.setOnClickListener(v->{
                onBackPressed();
            });
        }
    }
}
