package com.knd.common.database.observer;

import com.knd.base.activity.LifecycleNetWork;

import io.reactivex.MaybeObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class BaseMaybeObserver<T>  implements MaybeObserver<T> {

    private LifecycleNetWork lifecycleNetWork;

    public BaseMaybeObserver(Object object) {
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

    //若数据库中有数据，那么Maybe就会被onSuccess
    @Override
    public void onSuccess(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    //若数据库中没有数据，那么Maybe就会被complete
    @Override
    public void onComplete() {

    }
}