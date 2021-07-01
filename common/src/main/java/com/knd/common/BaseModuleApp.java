package com.knd.common;

import android.content.Context;
import android.content.res.Configuration;

import com.amap.api.location.AMapLocation;
import com.effective.android.anchors.task.Task;
import com.knd.base.application.BaseApplication;
import com.knd.base.application.IApplication;
import com.knd.base.util.SpUtils;
import com.knd.common.bean.DeviceResponseBean;
import com.knd.common.database.bean.BuriedPoint;
import com.knd.common.manager.DownloadManger;
import com.knd.common.manager.RouteManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BaseModuleApp implements IApplication {

    public Context appContext;
    public  String hardwareVersion;
    public  String softwareVersion;
    public  String systemVersion = "";
    private List<DeviceResponseBean> devices;
    private static BaseModuleApp baseModuleApp;
    public AMapLocation aMapLocation;
    public BuriedPoint point;

    public static BaseModuleApp getInstance(){
        if(baseModuleApp ==null){
            synchronized (BaseModuleApp.class){
                if(baseModuleApp ==null){
                    baseModuleApp =new BaseModuleApp();
                }
            }
        }
        return baseModuleApp;
    }

    @Override
    public void onAttachBaseConext(Context context) {
        this.appContext=context;
    }

    @Override
    public void onCreate() {
        devices=new ArrayList<>();
        BaseApplication.taskMap.put("RouteManager", new Task("RouteManager" ,false) {
            @Override
            protected void run(@NotNull String s) {
                RouteManager.init(BaseApplication.sApplication, BuildConfig.DEBUG);
            }
        });
        BaseApplication.taskMap.put("initDownLoad", new Task("initDownLoad" ,false) {
            @Override
            protected void run(@NotNull String s) {
                //初始化下载
                initDownLoad();
            }
        });
        BaseApplication.taskMap.put("SpUtils", new Task("SpUtils" ,false) {
            @Override
            protected void run(@NotNull String s) {
                SpUtils.getInstance().init(appContext);
            }
        });
    }

    private void initDownLoad() {
        DownloadManger.getInstance().init(appContext);
    }

    public  void setDevices(List<DeviceResponseBean> devices) {
        this.devices=devices;
    }

    public List<DeviceResponseBean> getDevices(){
        return devices;
    }

    @Override
    public void onTerminate() {
        RouteManager.getInstance().destroy();
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
