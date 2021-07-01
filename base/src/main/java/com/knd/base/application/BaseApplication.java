package com.knd.base.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.ArrayMap;

import com.bumptech.glide.Glide;
import com.effective.android.anchors.AnchorsManager;
import com.effective.android.anchors.task.Task;
import com.effective.android.anchors.task.project.Project;
import com.kingja.loadsir.core.LoadSir;
import com.knd.base.BuildConfig;
import com.knd.base.loadsir.EmptyCallback;
import com.knd.base.loadsir.ErrorCallback;
import com.knd.base.loadsir.ErrorCallback2;
import com.knd.base.loadsir.LoadingCallback;
import com.knd.base.loadsir.TransparentLoadingCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseApplication extends Application {
    public static Application sApplication;
    public static boolean isDebug;
    private List<IApplication> modules=new ArrayList<>();
    public static Map<String, Task> taskMap = new ArrayMap<>();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        for (IApplication application:
                modules) {
            application.onAttachBaseConext(this);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication=this;
        for (IApplication application:
             modules) {
            application.onCreate();
        }
        init();
    }

    private void init() {

        AppTaskFactory appTaskFactory = new AppTaskFactory();
        Set<String> keySet = taskMap.keySet();
        String[] keys = new String[keySet.size()];
        keySet.toArray(keys);

        Project.Builder builder = new Project.Builder("app", appTaskFactory);
        for(String key : keys){
            builder.add(key);
        }
        AnchorsManager.getInstance()
                .debuggable(BuildConfig.DEBUG)
                //设置锚点
                .addAnchor(keys)
                .start(builder.build());

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplication application:
                modules) {
            application.onLowMemory();
        }
        Glide.get(this).clearMemory();
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplication application:
                modules) {
            application.onTerminate();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (IApplication application:
                modules) {
            application.onTrimMemory(level);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for (IApplication application:
                modules) {
            application.onConfigurationChanged(newConfig);
        }
    }

    public void addModule(IApplication module){
        modules.add(module);
    }


}
