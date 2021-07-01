package com.knd.common.utils;

import android.content.Context;
import android.provider.Settings;

import static android.provider.Settings.Secure.ANDROID_ID;

public class DeviceUtils {
    public static String getAndroidId(Context context){
        return  SidUtil.getInstance().getSid();
    }
}
