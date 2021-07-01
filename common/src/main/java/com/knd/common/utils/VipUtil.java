package com.knd.common.utils;

import com.knd.common.bean.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VipUtil {

    public static boolean isVip(UserInfo userInfo){
        if(userInfo == null || userInfo.getVipStatus() <= 0){
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date now = new Date();
            Date endDate = format.parse(userInfo.getVipEndDate());
            if(endDate.getTime() > now.getTime()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isExpired(UserInfo userInfo){
        if(userInfo == null || userInfo.getVipStatus() <= 0){
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date now = new Date();
            Date endDate = format.parse(userInfo.getVipEndDate());
            if(endDate.getTime() < now.getTime()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
