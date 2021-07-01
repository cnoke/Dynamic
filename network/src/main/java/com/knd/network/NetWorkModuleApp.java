package com.knd.network;

import android.content.Context;
import android.content.res.Configuration;

import com.knd.base.application.IApplication;

import java.util.HashMap;

public class NetWorkModuleApp implements IApplication {

    private static NetWorkModuleApp baseModuleApp;

    public static NetWorkModuleApp getInstance(){
        if(baseModuleApp ==null){
            synchronized (NetWorkModuleApp.class){
                if(baseModuleApp ==null){
                    baseModuleApp =new NetWorkModuleApp();
                }
            }
        }
        return baseModuleApp;
    }

    @Override
    public void onAttachBaseConext(Context context) {
        ApiBase.setRequestInfo(context, () -> new HashMap<>());
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }
}
