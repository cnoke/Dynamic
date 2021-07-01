package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class DetectionBean implements Parcelable{

    private final String[] ERROR_CODE = new String[]{
            "0x01","0x02","0x03"
            ,"0x04","0x05","0x06"
            ,"0x07","0x08","0x0A"
            ,"0x12","0x16","0x0B"
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""
            ,"0x1A","0x1B","","",""
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""};
    private String[] ERROR_DESCRIBE = new String[]{
            "电机霍尔传感器故障","皮带光电编码器故障","码盘传感器故障"
            ,"电机温度传感器故障","制动电阻温度传感器故障","左臂通讯故障"
            ,"右臂通讯故障","蓝牙接收板通讯故障","风扇故障"
            ,"48V电源故障","电机三相故障","驱动板故障"
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""
            ,"门未关闭","唤醒按钮按下","","",""
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""};
    private String[] ERROR_REASON = new String[]{
            "1，电机霍尔传感器损坏。\n2，电机霍尔传感器线或者端子损坏 "
            ,"1，皮带旋转编码器损坏。\n2，皮带旋转编码器线或者端子损坏"
            ,"1，码盘传感器损坏。\n2，码盘传感器线或者端子损坏"
            ,"1，电机温度传感器损坏。\n2，电机温度传感器线或者对接端子损坏"
            ,"1，制动电阻温度传感器损坏。\n2，制动电阻温度传感器线或者对接端子损坏"
            ,"1，左臂位置传感器PCB板损坏。\n2，左臂位置传感器线或者对接端子损坏"
            ,"1，右臂位置传感器损坏。\n2，右臂位置传感器线或者对接端子损坏"
            ,"1，蓝牙接收板损坏。\n2，蓝牙接收板连接线或者对接端子损坏"
            ,"1，风扇损坏,\n2，接线端子损坏"
            ,"电源故障","下控板A,B,C缺失。","下控板损坏"
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""
            ,"1，门未关闭。\n2，门霍尔传感器故障","1，唤醒按钮按下。\n2，唤醒按钮故障","","",""
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""};
    private String[] ERROR_COUNTERPLAN = new String[]{
            "更换电机","更换皮带光电编码","更换码盘传感器"
            ,"更换电机","更换制动电阻温度传感器","检查左臂位置传感器PCB板，线或者对接端子"
            ,"检查并更换左臂位置传感器PCB板，线或者对接端子","检查并更换蓝牙接收板，线或者对接端子","检查并更换风扇或端子"
            ,"更换电源","检查电机线连接是否松动","更换下控板"
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""
            ,"检查门是否关闭，门霍尔传感器是否故障","检查唤醒按钮","","",""
            ,"","","","",""
            ,"","","","",""
            ,"","","","",""};
    private int index;
    private boolean isOk;
    //33 门状态   1：关门状态，0：开门状态
    //34 唤醒按钮状态 1：弹起状态，0：按下状态


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getErrorCode() {
        if(index < 0 || index >= ERROR_CODE.length){
            return "";
        }
        return ERROR_CODE[index];
    }

    public void setDescribe(String describe) {
        if(index < 0 || index >= ERROR_DESCRIBE.length){
            return;
        }
        ERROR_DESCRIBE[index] = describe;
    }

    public String getDescribe() {
        if(index < 0 || index >= ERROR_DESCRIBE.length){
            return "";
        }
        return ERROR_DESCRIBE[index];
    }

    public void setReason(String reason) {
        if(index < 0 || index >= ERROR_REASON.length){
            return;
        }
        ERROR_REASON[index] = reason;
    }

    public String getReason() {
        if(index < 0 || index >= ERROR_REASON.length){
            return "";
        }
        return ERROR_REASON[index];
    }

    public void setCounterplan(String counterplan) {
        if(index < 0 || index >= ERROR_COUNTERPLAN.length){
            return;
        }
        ERROR_COUNTERPLAN[index] = counterplan;
    }

    public String getCounterplan() {
        if(index < 0 || index >= ERROR_COUNTERPLAN.length){
            return "";
        }
        return ERROR_COUNTERPLAN[index];
    }


    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public DetectionBean() {
    }

    protected DetectionBean(Parcel in) {
        index = in.readInt();
        isOk = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(index);
        parcel.writeByte((byte) (isOk ? 1 : 0));
    }

    public static final Creator<DetectionBean> CREATOR = new Creator<DetectionBean>() {
        @Override
        public DetectionBean createFromParcel(Parcel in) {
            return new DetectionBean(in);
        }

        @Override
        public DetectionBean[] newArray(int size) {
            return new DetectionBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
