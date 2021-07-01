package com.knd.base.fragment;

import com.knd.base.activity.IBaseView;

import java.util.List;


public interface IBasePagingView<T> extends IBaseView {

    void onLoadMoreFailure(String message);

    void onLoadMoreEmpty();

    void onLoadSuccess(List<T> list);
}
