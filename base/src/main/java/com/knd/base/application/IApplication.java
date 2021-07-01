package com.knd.base.application;

import android.content.Context;
import android.content.res.Configuration;

public interface IApplication {

    void onAttachBaseConext(Context context);

    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);

    void onConfigurationChanged(Configuration newConfig);
}
