package com.knd.common.event;

import com.knd.common.bean.DetectionBean;

import java.util.ArrayList;

public class DetectionEvent {

    public final ArrayList<DetectionBean> result;

    public DetectionEvent(ArrayList<DetectionBean> result) {
        this.result = result;
    }

}
