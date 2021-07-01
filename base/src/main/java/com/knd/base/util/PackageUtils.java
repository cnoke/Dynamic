package com.knd.base.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageUtils {
    /**
     * @param context
     * @return app版本号
     */
    public static String getVersionName(Context context){
        PackageManager pm=context.getPackageManager();

        String versionName;
        try {
            PackageInfo mInfo= pm.getPackageInfo(context.getPackageName(),0);
            versionName= mInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            versionName="";
        }
        return  versionName;
    }

    public static String getPackageName(Context context){
        PackageManager packageManager = context.getPackageManager();

        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(

                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }

        return packageInfo.packageName;

    }
}
