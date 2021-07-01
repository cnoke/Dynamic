package com.knd.common.listener;

import android.bluetooth.BluetoothDevice;

public interface BlueToothCallback {

    /**
     * 开始扫描
     */
    void onStartDiscovery();


    void onFindDevice(BluetoothDevice device);

    /**
     * 扫描结束
     */
    void onStopDiscovery();

}
