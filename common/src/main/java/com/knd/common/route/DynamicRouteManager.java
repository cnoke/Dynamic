package com.knd.common.route;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.ArrayMap;
import android.util.Log;

import com.alibaba.android.arouter.utils.ClassUtils;
import com.knd.common.manager.RouteManager;
import com.knd.common.manager.RouteManager.Build;
import com.knd.common.utils.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

public class DynamicRouteManager {

    private final static String TAG = "DynamicRouteManager";
    private volatile static boolean hasInit = false;
    private final static Map<String,RouteDynamic> groupMap = new ArrayMap<>();
    private static Context mContext;
    private final static String ROUTE_ROOT_PAKCAGE = "com.knd.common.route.manager";
    private static DynamicRouteManager instance;

    private DynamicRouteManager() {
    }

    public static DynamicRouteManager getInstance(){
        if (!hasInit) {
            throw new RuntimeException("DynamicRouteManager::Init::Invoke init(context) first!");
        } else {
            if (instance == null) {
                synchronized (DynamicRouteManager.class) {
                    if (instance == null) {
                        instance = new DynamicRouteManager();
                    }
                }
            }
            return instance;
        }
    }

    public static void init(Context application) {
        if (!hasInit) {
            mContext = application;
            try {
                loadInfo();
                hasInit = true;
            } catch (Exception e) {
                e.printStackTrace();
                hasInit = false;
                Log.e(TAG, "初始化失败!", e);
            }
        }
    }

    public void route(String url, Param param){
        if(StringUtils.isEmpty(url)){
            return;
        }
        if(param == null){
            param = new Param();
        }
        param.initFromUrl(url);
        Uri uri = Uri.parse(url);
        String scheme = uri.getScheme();
        if("http".equals(scheme) || "https".equals(scheme)){
            RouteManager.getInstance().router(url);
            return;
        }
        String key = getKey(uri.getPath());
        String group = getGroup(key);
        RouteDynamic dynamic = groupMap.get(group);
        if(dynamic == null){
            addClass(key,param,group);
            return;
        }
        dynamic.route(key,param);
    }

    public Build routeBuild(String url, Param param){
        if(StringUtils.isEmpty(url)){
            return new Build();
        }
        if(param == null){
            param = new Param();
        }
        param.initFromUrl(url);
        Uri uri = Uri.parse(url);
        String scheme = uri.getScheme();
        if("http".equals(scheme) || "https".equals(scheme)){
            return RouteManager.getInstance().routerBuild(url);
        }
        String key = getKey(uri.getPath());
        String group = getGroup(key);
        RouteDynamic dynamic = groupMap.get(group);
        if(dynamic == null){
            addClass(key,param,group);
            return new Build();
        }
        return dynamic.routeBuild(key,param);
    }

    private void addClass(String key, Param param,String group){
        try {
            RouteDynamic routeDynamic = ((RouteDynamic) Class.forName(ROUTE_ROOT_PAKCAGE+".ARouteManager$$"+group).getConstructor().newInstance());
            if(routeDynamic != null){
                groupMap.put(routeDynamic.getName(),routeDynamic);
                routeDynamic.route(key,param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadInfo() throws InterruptedException, IOException, PackageManager.NameNotFoundException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获得所有 apt生成的路由类的全类名 (路由表)
        Set<String> routerMap = ClassUtils.getFileNameByPackageName(mContext, ROUTE_ROOT_PAKCAGE);
        for (String className : routerMap) {
            if (className.startsWith(ROUTE_ROOT_PAKCAGE)) {
                //root中注册的是分组信息 将分组信息加入仓库中
                RouteDynamic routeDynamic = ((RouteDynamic) Class.forName(className).getConstructor().newInstance());
                if(routeDynamic != null){
                    groupMap.put(routeDynamic.getName(),routeDynamic);
                    Log.e(TAG,"init  " + routeDynamic.getName());
                }
            }
        }
    }

    public String getKey(String path){
        StringBuilder builder = new StringBuilder();
        if(StringUtils.isEmpty(path)){
            return builder.toString();
        }
        builder.append(path);
        return builder.toString();
    }

    private String getGroup(String path){
        StringBuilder builder = new StringBuilder();
        String[] paths = path.split("/");
        if(paths == null || paths.length < 3){
            return builder.toString();
        }
        builder.append(paths[1]);
        return builder.toString();
    }
}
