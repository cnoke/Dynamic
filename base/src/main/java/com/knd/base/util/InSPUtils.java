package com.knd.base.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

class InSPUtils {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Object object;

    public void init(Context context){
        if(sp==null)
            sp=context.getSharedPreferences("knd_gym",Context.MODE_PRIVATE);
//            sp=PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    /**
     * 保存数据 , 所有的类型都适用
     *
     * @param key
     * @param object
     */
    public synchronized void putData(String key, Object object){
        if(sp==null) throw new IllegalArgumentException("SpUtils init should be called first!!! ");
        if (editor == null){
            editor = sp.edit();
        }
        if(object instanceof String){
            // 保存String 类型
            editor.putString(key, (String) object);
        }else if(object instanceof Integer){
            // 保存integer 类型
            editor.putInt(key, (Integer) object);
        }else if(object instanceof Boolean){
            // 保存 boolean 类型
            editor.putBoolean(key, (Boolean) object);
        }else if(object instanceof Float){
            // 保存float类型
            editor.putFloat(key, (Float) object);
        }else if(object instanceof Long){
            // 保存long类型
            editor.putLong(key, (Long) object);
        }else{
            if (!(object instanceof Serializable)) {
                throw new IllegalArgumentException(object.getClass().getName() + " 必须实现Serializable接口!");
            }
            try {
                editor.putString(key, Object2String(object));
            } catch (Exception e) {
                editor.remove(key);
                e.printStackTrace();
            }
        }
        editor.apply();
    }


    public static String objectToString(Object object){
        return new Gson().toJson(object);
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

    /**
     * 移除信息
     */
    public synchronized void remove(String key) {
        if(sp==null) throw new IllegalArgumentException("SpUtils init should be called first!!! ");
        if (editor == null)
            editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public <T> T getData(String key, T defaultObject) {
        if(sp==null) throw new IllegalArgumentException("SpUtils init should be called first!!! ");
        if (defaultObject == null) {
            return getObject(key);
        }
        Object obj;
        if(defaultObject instanceof String){
            obj = sp.getString(key, (String) defaultObject);
        }else if(defaultObject instanceof Integer){
            obj = sp.getInt(key, (Integer) defaultObject);
        }else if(defaultObject instanceof Boolean){
            obj = sp.getBoolean(key, (Boolean) defaultObject);
        }else if(defaultObject instanceof Float){
            obj = sp.getFloat(key, (Float) defaultObject);
        }else if(defaultObject instanceof Long){
            obj = sp.getLong(key, (Long) defaultObject);
        }else{
            obj = getObject(key);
        }
        return (T) obj;
    }

    private <T> T getObject(String key) {
        try {
            object =  String2Object(sp.getString(key, ""));
            return (T) object;
        } catch (Exception e) {
            remove(key);
            return null;
        }
    }

    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    protected static String Object2String(Object object) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        objectOutputStream.close();
        return string;
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    protected static Object String2Object(String objectString) throws Exception{
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }

}
