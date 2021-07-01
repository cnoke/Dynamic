package com.knd.common.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import android.util.Log;
import android.view.View;


import com.knd.base.activity.IBaseView;
import com.knd.base.loadsir.EmptyCallback;
import com.knd.base.loadsir.ErrorCallback;
import com.knd.base.loadsir.LoadingCallback;
import com.knd.base.viewmodel.IBaseViewModel;


public abstract class MVVMFragment<V extends ViewDataBinding> extends BaseFragment<V> implements IBaseView {
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getBindingVariable() > 0) {
            viewBinding.executePendingBindings();
        }
    }


    public abstract int getBindingVariable();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefreshEmpty() {
        if(mLoadService != null){
            mLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onRefreshFailure(String message) {
        if (mLoadService != null) {
            if(!isShowedContent) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
//                ToastUtils.show(getContext(), message);
            }
        }
    }

    @Override
    public void showLoading() {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }
    private boolean isShowedContent = false;

    @Override
    public void showContent() {
        if (mLoadService != null) {
            isShowedContent = true;
            mLoadService.showSuccess();
        }
    }
}
