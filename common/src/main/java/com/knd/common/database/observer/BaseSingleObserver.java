package com.knd.common.database.observer;

import com.knd.base.activity.LifecycleNetWork;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class BaseSingleObserver<T> implements SingleObserver<T> {

    private LifecycleNetWork lifecycleNetWork;

    public BaseSingleObserver(Object object) {
        if(object instanceof LifecycleNetWork){
            this.lifecycleNetWork = (LifecycleNetWork) object;
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if(lifecycleNetWork !=null ){
            lifecycleNetWork.addRequest(d);
        }
    }

    @Override
    public void onSuccess(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }
}
