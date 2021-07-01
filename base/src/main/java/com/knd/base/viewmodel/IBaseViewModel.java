package com.knd.base.viewmodel;


import com.knd.base.activity.IBaseView;

public interface IBaseViewModel<V extends IBaseView> {

    void attachUI(V view);

    V getPageView();

    boolean isUIAttached();

    void detachUI();
}
