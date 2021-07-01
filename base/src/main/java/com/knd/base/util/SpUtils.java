package com.knd.base.util;

import android.content.Context;

import com.google.gson.Gson;
import com.knd.base.BuildConfig;

import java.lang.reflect.Type;
import java.util.List;

public class SpUtils {
    private static SpUtils instance;

    InSPUtils utils;
    private boolean isInit = false;

    private SpUtils(){
        if(BuildConfig.NEED_MMKV){
            utils = new MMKVUtils();
        }else{
            utils = new InSPUtils();
        }
    }

    public static SpUtils getInstance(){
        if(instance==null){
            synchronized (SpUtils.class){
                if(instance==null){
                    instance=new SpUtils();
                }
            }
        }
        return instance;
    }

    public void init(Context context){
        utils.init(context);
        isInit = true;
    }

    /**
     * 保存数据 , 所有的类型都适用
     *
     * @param key
     * @param object
     */
    public synchronized void putData(String key, Object object){
        if(!isInit){
            throw new IllegalArgumentException("SpUtils init should be called first!!! ");
        }
        utils.putData(key,object);
    }


    public synchronized void remove(String key) {
        if(!isInit){
            throw new IllegalArgumentException("SpUtils init should be called first!!! ");
        }
        utils.remove(key);
    }

    public Object getData(String key, Object defaultObject) {
        if(!isInit){
            throw new IllegalArgumentException("SpUtils init should be called first!!! ");
        }
        return utils.getData(key,defaultObject);
    }

    public <T> T  getBean(String key, T defaultObject) {
        if(!isInit){
            throw new IllegalArgumentException("SpUtils init should be called first!!! ");
        }
        return utils.getData(key,defaultObject);
    }

    public static String listToString(List<?> list){
        return new Gson().toJson(list);
    }

    public static <T> T StringToObj(String listString, Class<T> classOfT){
        return new Gson().fromJson(listString,classOfT);
    }

    public static <T> T StringToObj(String listString, Type typeOfT){
        return new Gson().fromJson(listString,typeOfT);
    }

}
