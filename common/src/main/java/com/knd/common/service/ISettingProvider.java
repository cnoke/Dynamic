package com.knd.common.service;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.knd.common.listener.BlueToothCallback;
import com.knd.common.listener.OnLocationListener;

public interface ISettingProvider extends IProvider {
    void setInitAble(boolean canInit);
    //设备初始化
    void equip_init();
    //左臂重新注册
    void left_arm_register();
    //右臂重新注册
    void right_arm_register();
    //蓝牙设备添加
    void bluetooth_device_add();
    //蓝牙设备取消添加
    void bluetooth_device_cancel_add();
    //蓝牙设备详情
    void bluetooth_device_detail(byte handler);
    //蓝牙设备移除
    void bluetooth_device_delete(byte handler);
    //左臂解锁
    void left_arm_unlock();
    //右臂解锁
    void right_arm_unlock();
    //左臂加锁
    void left_arm_lock();
    //右臂加锁
    void right_arm_lock();

    //开始新的一组
    void start_new_set(boolean isAddPower,int basePower,int continousPower ,int backPower);
    //
    void  change_power(boolean isAddPower,int basePower,int continousPower,int backPower);

    void startLocation(OnLocationListener listener);
    void stopLocation();

    //蓝牙
    int getBluetoothState();
    BluetoothDevice getConnectDevice();
    int getDeviceState(BluetoothDevice device);
    void setBluetoothCallback(BlueToothCallback callback, Context mContext);
    void removeBluetoothCallback(BlueToothCallback callback);
    int getConnectState(BluetoothDevice s);
    void disconnect(BluetoothDevice mCurrentDevice);
    void connect(BluetoothDevice s);
    void startDiscovery();
    void setBtEnabled(boolean isChecked);
    boolean isBTEnabled();

    void test(String command);

    void setReceiver(boolean flag);

    void serial_port_release();

    //更新固件
    void updateBinFile();

    //发送固件包内容
    void sendBinContent(byte[] b);

    //更改灯颜色
    void changeLight(byte[] color);

    //更改灯颜色
    void changeLight(int color);

    //警告灯颜色
    void warringLight();

    //错误灯颜色
    void errorLight();

    //正常灯颜色
    void normalLight();

    //重设灯颜色
    void resetLight(byte[] color);

    //重设灯颜色
    void resetLight(int color);

    //音乐灯光模式
    void musicLight(int currentVolume ,int currentFrequency);

    //诊断模式
    void diagnosticMode(boolean state);
}
