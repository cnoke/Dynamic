package com.knd.common.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

import java.util.HashMap;

public interface IStatubarProvider extends IProvider {

    void startMonkServer(Context context);

    void showAttention(boolean show);//显示i信息图标(1215改为拉练部分点击无效)

    void showForceUpdate();

    void startUpload();

    int show();// 显示

    void hide();// 隐藏

    void menuShow();// 弹出

    void menuHide();// 收起

    void setCreditIsConnect(boolean show);//手柄连接状态

    void ysTurnOnBackLight(boolean on);//YS A99开启或关闭背光

    void setSelfinSpection(boolean selfinSpection);//是否是诊断模式

    void buriedPoint(String action,String value , HashMap<String,String> parameters);//埋点

    void upgradeSys(String path);//升级系统
}
