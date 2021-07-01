package com.knd.common.activity.mvp;

import com.knd.base.activity.LifecycleNetWork;

import java.lang.ref.SoftReference;
import java.lang.reflect.Proxy;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseMvpModel <T extends IBasePresenter>
        implements IBaseModel, LifecycleNetWork {

    private T mProxyP;
    private SoftReference<IBasePresenter> mReferenceP;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void attach(IBasePresenter presenter) {
        //使用软引用创建对象
        mReferenceP = new SoftReference<>(presenter);
//
//        //使用动态代理做统一的逻辑判断 aop 思想
        mProxyP = (T) Proxy.newProxyInstance(presenter.getClass().getClassLoader(), presenter.getClass().getInterfaces(), (o, method, objects) -> {
            if (mReferenceP == null || mReferenceP.get() == null) {
                return null;
            }
            return method.invoke(mReferenceP.get(), objects);
        });
    }

    public T getPresenter(){
        return mProxyP;
    }

    @Override
    public void addRequest(Disposable d) {
        if(compositeDisposable != null){
            compositeDisposable.add(d);
        }
    }

    @Override
    public void detach() {
        if(compositeDisposable != null){
            compositeDisposable.clear();
            compositeDisposable = null;
        }
        mReferenceP.clear();
        mReferenceP = null;
    }
}