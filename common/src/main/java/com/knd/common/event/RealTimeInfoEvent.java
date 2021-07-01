package com.knd.common.event;

import com.knd.common.bean.RealTimeInfo;
import com.knd.common.utils.ErrorMsgUtil;

import static com.knd.common.bean.Constants.EQUIP_ERRORS;

public class RealTimeInfoEvent {
    public RealTimeInfo realTimeInfo;
    public String error_msg;
    public RealTimeInfoEvent(RealTimeInfo realTimeInfo) {
        this.realTimeInfo = realTimeInfo;
        error_msg = ErrorMsgUtil.getErrorMsgWithTemp(realTimeInfo);
    }
}
