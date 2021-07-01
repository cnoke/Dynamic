package com.knd.common.bean;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String[] EQUIP_ERRORS={
            "正常","电机霍尔传感器故障","皮带光电编码器故障","码盘传感器故障",
            "电机温度传感器故障","制动电阻温度传感器故障","驱动板温度传感器故障","驱动板I2C通讯故障","左臂通讯故障","右臂通讯故障",
            "蓝牙接收板通讯故障","屏通讯故障","风扇故障","48V电源使能故障","驱动芯片使能故障","绳子断开故障","","","其它硬件故障",""
            ,"48V电源过压故障",
            "48V电源欠压故障","电机过温故障","电机过流故障","电机超速故障","电机缺相故障"
            ,"制动电阻过温故障","MOS管过温故障","","","","","","","其它软件故障"
            ,"","电机过温警告","电机过流警告","MOS管过温警告","MOS管过流警告"
    };

    public static final String ERROR_DOOR_OPEN = "机盖打开，保护状态";
    public static final String ERROR_ARM_LEFT = "请固定好左臂位置";
    public static final String ERROR_ARM_RIGHT = "请固定好右臂位置";
    public static final String ERROR_ARM_ANGLE_LEFT = "请固定好左臂横向角度";
    public static final String ERROR_ARM_ANGLE_Right = "请固定好右臂横向角度";
    public static final String ERROR_ARM_ANGLE_LEFT_HON = "请固定好左臂纵向角度";
    public static final String ERROR_ARM_ANGLE_Right_HON = "请固定好右臂纵向角度";
}
