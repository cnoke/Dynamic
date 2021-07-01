package com.knd.common.listener;

import com.knd.base.activity.LifecycleNetWork;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ObsDownloadObserver <T> implements Observer<T> {

    private LifecycleNetWork lifecycleNetWork;

    public ObsDownloadObserver(Object object) {
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
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
    }

    @Override
    public void onComplete() {

    }
}