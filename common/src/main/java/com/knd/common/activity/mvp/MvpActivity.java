package com.knd.common.activity.mvp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.kingja.loadsir.core.LoadService;
import com.knd.base.activity.IBaseView;
import com.knd.common.activity.BaseActivity;

public abstract class MvpActivity<V extends ViewDataBinding> extends BaseActivity<V> implements IBaseView {

    private ProxyActivity mProxyActivity;


    @SuppressWarnings("SameParameterValue")
    protected <T extends View> T $(@IdRes int viewId) {
        return findViewById(viewId);
    }

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mProxyActivity = createProxyActivity();
        mProxyActivity.bindPresenter();
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private ProxyActivity createProxyActivity() {
        if (mProxyActivity == null) {
            return new ProxyActivity(this);
        }
        return mProxyActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxyActivity.unbindPresenter();
    }

}
