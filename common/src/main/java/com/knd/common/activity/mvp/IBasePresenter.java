package com.knd.common.activity.mvp;

import com.knd.base.activity.IBaseView;

public interface IBasePresenter {

    void attach(IBaseView view);

    void detach();
}
