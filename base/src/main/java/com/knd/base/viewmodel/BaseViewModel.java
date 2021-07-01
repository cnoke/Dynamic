package com.knd.base.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;

import com.knd.base.activity.IBaseView;
import com.knd.base.model.SuperBaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;


public class BaseViewModel<V extends IBaseView, M extends SuperBaseModel> extends ViewModel implements IBaseViewModel<V> {
    private Reference<V> mUIRef;
    protected M model;

    public void attachUI(V ui) {
        mUIRef = new WeakReference<>(ui);
    }

    @Nullable
    public V getPageView() {
        if (mUIRef == null) {
            return null;
        }
        return mUIRef.get();
    }

    public boolean isUIAttached() {
        return mUIRef != null && mUIRef.get() != null;
    }

    public void detachUI() {
        if (mUIRef != null) {
            mUIRef.clear();
            mUIRef = null;

        }
        if(model != null) {
            model.cancel();
        }
    }
}
