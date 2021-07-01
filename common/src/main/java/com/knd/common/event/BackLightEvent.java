package com.knd.common.event;

/**
 * 背光打开或熄灭
 */
public class BackLightEvent {
    public boolean lightOn;

    public BackLightEvent(boolean lightOn) {
        this.lightOn = lightOn;
    }
}
