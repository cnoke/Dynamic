package com.knd.dynamicpage;

import android.content.Context;
import android.content.res.Configuration;

import com.knd.base.application.IApplication;

//application中的回调
public class ModuleApp implements IApplication {
    public Context appContext;
    private static ModuleApp moduleApp;
    private ModuleApp(){}

    @Override
    public void onAttachBaseConext(Context context) {
        appContext=context;
    }


    public static ModuleApp getInstance(){
        if(moduleApp==null){
            synchronized (ModuleApp.class){
                if(moduleApp==null){
                    moduleApp=new ModuleApp();
                }
            }
        }
        return moduleApp;
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
