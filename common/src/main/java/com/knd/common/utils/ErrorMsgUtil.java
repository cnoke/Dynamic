package com.knd.common.utils;

import com.knd.common.bean.RealTimeInfo;

public class ErrorMsgUtil {
    //获取带当前温度的错误信息
    public static String getErrorMsgWithTemp(RealTimeInfo realTimeInfo){
        String result = getErrorMsg(realTimeInfo.error);
        if (realTimeInfo.error==106||realTimeInfo.error==202){
            result+=(realTimeInfo.temp1+"℃");
        }else if (realTimeInfo.error==102||realTimeInfo.error==200){
            result+=(realTimeInfo.temp2+"℃");
        }else if (realTimeInfo.error==105||realTimeInfo.error==203){
            result+=(realTimeInfo.temp3+"℃");
        }
        return result;
    }

    public static String getErrorMsg(int errorCode){
        String msg = "设备未知故障";
        switch (errorCode){
            case 0:
                msg = "正常";
                break;
            case 1:
                msg = "电机霍尔传感器故障";
                break;
            case 2:
                msg = "皮带光电编码器故障";
                break;
            case 3:
                msg = "码盘传感器故障";
                break;
            case 4:
                msg = "电机温度传感器故障";
                break;
            case 5:
                msg = "制动电阻温度传感器故障";
                break;
            case 6:
                msg = "驱动板温度传感器故障";
                break;
            case 7:
                msg = "驱动板I2C通讯故障";
                break;
            case 8:
                msg = "左臂通讯故障";
                break;
            case 9:
                msg = "右臂通讯故障";
                break;
            case 10:
                msg = "蓝牙接收板通讯故障";
                break;
            case 11:
                msg = "屏通讯故障";
                break;
            case 12:
                msg = "风扇故障";
                break;
            case 13:
                msg = "48V电源使能故障";
                break;
            case 14:
                msg = "驱动芯片使能故障";
                break;
            case 15:
                msg = "电机缺相";
                break;
            case 16:
                msg = "绳子断开";
                break;
            case 17:
                msg = "驱动硬件故障";
                break;
            case 100:
                msg = "48V电源过压故障";
                break;
            case 101:
                msg = "48V电源欠压故障";
                break;
            case 102:
                msg = "电机过温故障";
                break;
            case 103:
                msg = "电机过流故障";
                break;
            case 104:
                msg = "电机超速故障";
                break;
            case 105:
                msg = "制动电阻过温故障";
                break;
            case 106:
                msg = "MOS管过温故障";
                break;
            case 200:
                msg = "电机过温警告";
                break;
            case 201:
                msg = "电机过流警告";
                break;
            case 202:
                msg = "MOS管过温警告";
                break;
            case 203:
                msg = "制动电阻过温警告";
                break;
        }
        return msg;
    }
}
