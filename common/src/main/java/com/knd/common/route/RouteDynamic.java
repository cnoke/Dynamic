package com.knd.common.route;

import com.knd.common.manager.RouteManager;

import java.util.Map;

public abstract class RouteDynamic {

    public abstract void route(String url, Param param);

    public abstract RouteManager.Build routeBuild(String key, Param param);

    public abstract String getName();

    protected RouteManager.Build defaultRoute(String key,Param param){
        RouteManager.Build build = RouteManager.getInstance().routerBuild(key);
        if(param != null){
            Map<String,Object> map = param.getMap();
            for(Map.Entry<String,Object> entry : map.entrySet()){
                if(entry.getValue() != null){
                    build.addParams(entry.getKey(),entry.getValue());
                }
            }
        }
        return build;
    }
}
