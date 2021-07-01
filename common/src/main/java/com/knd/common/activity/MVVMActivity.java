package com.knd.common.activity;


import android.os.Bundle;


import androidx.annotation.Nullable;

import androidx.databinding.ViewDataBinding;


import com.knd.base.activity.IBaseView;
import com.knd.base.loadsir.EmptyCallback;
import com.knd.base.loadsir.ErrorCallback;
import com.knd.base.loadsir.LoadingCallback;

import com.knd.base.viewmodel.IBaseViewModel;

public abstract class MVVMActivity<V extends ViewDataBinding, VM extends IBaseViewModel> extends BaseActivity<V> implements IBaseView {
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();

    }

    protected abstract VM getViewModel();

    private void initViewModel() {
        viewModel = getViewModel();
        if (viewModel != null) {
            viewModel.attachUI(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null && viewModel.isUIAttached())
            viewModel.detachUI();
    }

    @Override
    public void onRefreshEmpty() {
        if (mLoadService != null) {
            mLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onRefreshFailure(String message) {
        if (mLoadService != null) {
            mLoadService.showCallback(ErrorCallback.class);
        }
    }

    @Override
    public void showLoading() {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showContent() {
        if (mLoadService != null) {
            mLoadService.showSuccess();
        }
    }

}
