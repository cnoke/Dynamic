package com.knd.common.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.knd.common.bean.UserInfo;

public interface IUserCenterProvider extends IProvider {
    public void changeUser();
    public void goToLogin();
    public void goToUserCenter();
    public void goToMyPlan();
    public void goToRecommendPlan();
    public void logout(UserInfo userInfo,boolean isToHome);
    public void getPlan(Context context);
}
