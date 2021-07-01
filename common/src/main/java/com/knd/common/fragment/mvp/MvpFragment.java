package com.knd.common.fragment.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.knd.base.activity.IBaseView;
import com.knd.common.fragment.BaseFragment;

public abstract class MvpFragment<V extends ViewDataBinding> extends BaseFragment<V> implements IBaseView {

    private ProxyFragment mProxyFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mProxyFragment = createProxyFragment();
        mProxyFragment.bindPresenter();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private ProxyFragment createProxyFragment() {
        if (mProxyFragment == null) {
            return new ProxyFragment<>(this);
        }
        return mProxyFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxyFragment.unbindPresenter();
    }
}
