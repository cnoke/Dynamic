package com.knd.common.bean;

//初始化信息
public class InitialBean {


    //0x01：硬件初始化状态
//0x02：软件初始化状态
//0x03：卸力状态
//0x04：加力状态
    public byte downState;// 下控状态
    public String downSoftwareVersion;// 下控软件版本
    public String downHardwareVersion;// 下控硬件版本
    public int error;// 故障码
}
