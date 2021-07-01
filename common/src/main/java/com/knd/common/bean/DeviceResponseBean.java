package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

//蓝牙设备响应信息
public class DeviceResponseBean implements Parcelable {

    public byte handler; //句柄
//设备类别
//0x01    左臂
//0x02    右臂
//0x03    手柄
//0x04    杆

    public byte mType;//类别

    public int mDeviceId = 0; //设备Id

    public String mVersion; // 蓝牙外设板卡固件版本（3字节）

   public byte mBattery;// 设备电池电量（1字节，1=1%）

//    public byte mState;//连接状态 1连接，0未连接


    public DeviceResponseBean() {
    }

    protected DeviceResponseBean(Parcel in) {
        handler = in.readByte();
        mType = in.readByte();
        mDeviceId = in.readInt();
        mVersion = in.readString();
        mBattery = in.readByte();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(handler);
        dest.writeByte(mType);
        dest.writeInt(mDeviceId);
        dest.writeString(mVersion);
        dest.writeByte(mBattery);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeviceResponseBean> CREATOR = new Creator<DeviceResponseBean>() {
        @Override
        public DeviceResponseBean createFromParcel(Parcel in) {
            return new DeviceResponseBean(in);
        }

        @Override
        public DeviceResponseBean[] newArray(int size) {
            return new DeviceResponseBean[size];
        }
    };
}
