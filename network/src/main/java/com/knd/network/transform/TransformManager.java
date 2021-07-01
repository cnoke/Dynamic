package com.knd.network.transform;

import io.reactivex.ObservableTransformer;

public class TransformManager {
    public static  <T> ObservableTransformer<T,T> getTransfromer(){
        return new CommonComposer<>();
    }
}
