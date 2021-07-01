package com.knd.common.activity.mvp;

import com.knd.base.activity.IBaseView;

import java.lang.ref.SoftReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public abstract class BasePresenter<V extends IBaseView, M extends BaseMvpModel>
        implements IBasePresenter {

    private SoftReference<IBaseView> mReferenceView;
    private V mProxyView;
    private M mModel;

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    public void attach(IBaseView view) {
        //使用软引用创建对象
        mReferenceView = new SoftReference<>(view);

        //使用动态代理做统一的逻辑判断 aop 思想
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), (o, method, objects) -> {
            if (mReferenceView == null || mReferenceView.get() == null) {
                return null;
            }
            return method.invoke(mReferenceView.get(), objects);
        });

        //通过获得泛型类的父类，拿到泛型的接口类实例，通过反射来实例化 model
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        if (type != null) {
            Type[] types = type.getActualTypeArguments();
            try {
                mModel = ((Class<M>) types[1]).newInstance();
                mModel.attach(this);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @SuppressWarnings("unchecked")
    public V getView() {
        return mProxyView;
    }

    protected M getModel() {
        return mModel;
    }

    @Override
    public void detach() {
        if(mModel != null){
            mModel.detach();
            mModel = null;
        }
        mReferenceView.clear();
        mReferenceView = null;
    }
}
