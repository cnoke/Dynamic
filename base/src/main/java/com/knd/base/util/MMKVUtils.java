package com.knd.base.util;

import android.content.Context;
import android.util.Log;

import com.tencent.mmkv.MMKV;
import java.io.Serializable;

class MMKVUtils extends InSPUtils{

    private static final String TAG = "MMKVUtils";
    private MMKV kv;

    @Override
    public void init(Context context) {
        String rootDir = MMKV.initialize(context);
        Log.e(TAG,"rootDir : " + rootDir);
    }

    @Override
    public synchronized void putData(String key, Object object) {
        if(kv == null){
            kv = MMKV.defaultMMKV();
        }
        if(object instanceof String){
            // 保存String 类型
            kv.encode(key, (String) object);
        }else if(object instanceof Integer){
            // 保存integer 类型
            kv.encode(key, (Integer) object);
        }else if(object instanceof Boolean){
            // 保存 boolean 类型
            kv.encode(key, (Boolean) object);
        }else if(object instanceof Float){
            // 保存float类型
            kv.encode(key, (Float) object);
        }else if(object instanceof Long){
            // 保存long类型
            kv.encode(key, (Long) object);
        }else if(object instanceof Double){
            // 保存long类型
            kv.encode(key, (Double) object);
        }else{
            if (!(object instanceof Serializable)) {
                throw new IllegalArgumentException(object.getClass().getName() + " 必须实现Serializable接口!");
            }
            try {
                kv.encode(key, Object2String(object));
            } catch (Exception e) {
                Log.e(TAG,e.toString());
                remove(key);
            }
        }
    }

    @Override
    public synchronized void remove(String key) {
        if(kv == null){
            kv = MMKV.defaultMMKV();
        }
        kv.remove(key);
    }

    @Override
    public <T> T getData(String key, T defaultObject) {
        if(kv == null){
            kv = MMKV.defaultMMKV();
        }
        if (defaultObject == null) {
            return getObject(key);
        }
        Object obj;
        if(defaultObject instanceof String){
            obj = kv.decodeString(key, (String) defaultObject);
        }else if(defaultObject instanceof Integer){
            obj = kv.decodeInt(key, (Integer) defaultObject);
        }else if(defaultObject instanceof Boolean){
            obj = kv.decodeBool(key, (Boolean) defaultObject);
        }else if(defaultObject instanceof Float){
            obj = kv.decodeFloat(key, (Float) defaultObject);
        }else if(defaultObject instanceof Long){
            obj = kv.decodeLong(key, (Long) defaultObject);
        }else if(defaultObject instanceof Double){
            obj = kv.decodeDouble(key, (Double) defaultObject);
        }else{
            obj = getObject(key);
        }
        return (T) obj;
    }

    private <T> T getObject(String key) {
        if(kv == null){
            kv = MMKV.defaultMMKV();
        }
        try {
            return (T) String2Object(kv.decodeString(key, ""));
        } catch (Exception e) {
            Log.e(TAG,e.toString());
            remove(key);
            return null;
        }
    }
}
