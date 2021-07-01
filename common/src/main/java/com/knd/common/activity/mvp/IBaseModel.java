package com.knd.common.activity.mvp;

public interface IBaseModel {

    void attach(IBasePresenter presenter);

    void detach();

}
