package com.knd.common.event;


import com.knd.common.bean.UserInfo;

public class UserLoginEvent {
    public UserInfo userInfo;

    public UserLoginEvent(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
