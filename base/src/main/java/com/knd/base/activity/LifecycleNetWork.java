package com.knd.base.activity;

import io.reactivex.disposables.Disposable;

public interface LifecycleNetWork {

    void addRequest(Disposable d);
}
