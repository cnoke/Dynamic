package com.knd.common.event;

import com.knd.common.bean.InitialBean;
import com.knd.common.utils.ErrorMsgUtil;

import static com.knd.common.bean.Constants.EQUIP_ERRORS;

public class EquipInitEvent {
    public String errorMsg;
    public int code;
    public String downSoftVersion;
    public String downHardVersion;
    public EquipInitEvent(InitialBean bean) {
        code=bean.error;
        downHardVersion=bean.downHardwareVersion;
        downSoftVersion=bean.downSoftwareVersion;
        errorMsg = ErrorMsgUtil.getErrorMsg(bean.error);
    }
}
