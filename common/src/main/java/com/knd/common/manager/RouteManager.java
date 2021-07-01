package com.knd.common.manager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.knd.common.key.ArouterKey;
import com.knd.common.route.DynamicRouteManager;
import com.knd.common.route.ParamKey;
import com.knd.common.service.IGymProvider;
import com.knd.common.service.ILiveProvider;
import com.knd.common.service.ISettingProvider;
import com.knd.common.service.IShopProvider;
import com.knd.common.service.IStatubarProvider;
import com.knd.common.service.IUserCenterProvider;

import java.io.Serializable;

public class RouteManager {

    private static volatile RouteManager manager;

    private RouteManager(){
        ARouter.getInstance().inject(this);
    }

    public static RouteManager getInstance(){
        if(manager==null){
            synchronized (RouteManager.class){
                if(manager==null)
                    manager=new RouteManager();
            }
        }
        return manager;
    }

    public static void init(Application application,boolean isDebug){
        if(isDebug){
            //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(application);
        DynamicRouteManager.init(application);
    }

    public Postcard build(String path){
        if(TextUtils.isEmpty(path)){
            return new Postcard();
        }
        if(path.startsWith("quinnoid://")){
            return quinnoidUri(path);
        }else if(path.startsWith("http") || path.startsWith("https")){
            return new Postcard();
        }else{
            return ARouter.getInstance().build(path);
        }
    }

    public void router(String path){
        if(TextUtils.isEmpty(path)){
            return;
        }
        if(path.startsWith("quinnoid://")){
            quinnoidUri(path).navigation();
        }else if(path.startsWith("http") || path.startsWith("https")){
            ARouter.getInstance()
                    .build(ArouterKey.DYNAMIC_ACTIVITY_WEB)
                    .withString(ParamKey.URL,path).navigation();
        }else{
            ARouter.getInstance().build(path).navigation();
        }
    }

    public Build routerBuild(String path){
        Build build = new Build();
        if(TextUtils.isEmpty(path)){
            return build;
        }
        if(path.startsWith("quinnoid://")){
            build.setPostcard(quinnoidUri(path));
        }else if(path.startsWith("http") || path.startsWith("https")){
            build.setPostcard(ARouter.getInstance()
                    .build(ArouterKey.DYNAMIC_ACTIVITY_WEB)
                    .withString(ParamKey.URL,path));
        }else{
            build.setPostcard(ARouter.getInstance().build(path));
        }
        return build;
    }

    /**
     * 销毁资源
     */
    public static void destroy(){
        ARouter.getInstance().destroy();
    }

    /**
     *
     * @param path
     * @param keyAndValue 偶数个，必须key  value配对传值
     */
    public void router(String path,Object... keyAndValue){
        if(TextUtils.isEmpty(path)){
            return;
        }
        Postcard postcard = null;
        if(path.startsWith("quinnoid://")){
            postcard = quinnoidUri(path);
            postcard = addParams(postcard,keyAndValue);
        }else if(path.startsWith("http") || path.startsWith("https")){

        }else{
            postcard = ARouter.getInstance().build(path);
            postcard = addParams(postcard,keyAndValue);
        }

        if(postcard != null){
            postcard.navigation();
        }
    }

    public static Postcard addParams(Postcard postcard,Object... values){
        Postcard old = postcard;
        int length = values.length;
        if(length % 2 == 0){
            for(int i = 0 , m = length ; i < m ; i += 2){
                Object now = values[i];
                if(!(now instanceof String)){
                    return old;
                }
                Object next = values[i + 1];
                if(next == null){
                    continue;
                }
                if(next instanceof Boolean){
                    postcard.withBoolean((String)now,(boolean)next);
                }else if(next instanceof String){
                    postcard.withString((String)now,(String)next);
                }else if(next instanceof Integer){
                    postcard.withInt((String)now,(int)next);
                }else if(next instanceof Float){
                    postcard.withFloat((String)now,(float)next);
                }else if(next instanceof Byte){
                    postcard.withByte((String)now,(byte)next);
                }else if(next instanceof Character){
                    postcard.withChar((String)now,(char)next);
                }else if(next instanceof Double){
                    postcard.withDouble((String)now,(double) next);
                }else if(next instanceof Long){
                    postcard.withLong((String)now,(long) next);
                }else if(next instanceof Parcelable){
                    postcard.withParcelable((String)now,(Parcelable) next);
                }else if(next instanceof Serializable){
                    postcard.withSerializable((String)now,(Serializable) next);
                }else if(next instanceof Bundle){
                    postcard.withBundle((String)now,(Bundle) next);
                }else{
                    postcard.withObject((String)now,next);
                }
            }
        }
        return postcard;
    }

    private Postcard quinnoidUri(String path){
        Uri uri = Uri.parse(path);
        return ARouter.getInstance().build(uri);
    }

    public static class Build{

        Postcard postcard;

        public void navigation(){
            if(postcard == null){
                return;
            }
            postcard.navigation();
        }

        public void navigation(Context context){
            if(postcard == null){
                return;
            }
            postcard.navigation(context);
        }

        public void navigation(Activity mContext, int requestCode){
            if(postcard == null){
                return;
            }
            postcard.navigation(mContext,requestCode);
        }

        public void navigation(Context context, NavigationCallback callback){
            if(postcard == null){
                return;
            }
            postcard.navigation(context,callback);
        }

        public void navigation(Activity mContext, int requestCode, NavigationCallback callback) {
            if(postcard == null){
                return;
            }
            postcard.navigation(mContext,requestCode,callback);
        }


        protected void setPostcard(Postcard postcard) {
            this.postcard = postcard;
        }

        public Build addParams(String key,Object value){
            if(postcard == null){
                return this;
            }
            postcard = RouteManager.addParams(postcard,key,value);
            return this;
        }

        public Build addParams(Object... values){
            if(postcard == null){
                return this;
            }
            postcard = RouteManager.addParams(postcard,values);
            return this;
        }

        public Postcard getPostcard() {
            return postcard;
        }
    }
}
