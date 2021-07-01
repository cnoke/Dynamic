package com.knd.common.event;

import com.knd.common.bean.DeviceResponseBean;

public class DeviceChangeEvent {
    public DeviceResponseBean deviceResponseBean;

    public DeviceChangeEvent(DeviceResponseBean deviceResponseBean) {
        this.deviceResponseBean = deviceResponseBean;
    }
}
