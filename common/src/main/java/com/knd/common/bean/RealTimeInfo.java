package com.knd.common.bean;

import java.util.Objects;

//实时数据信息
public class RealTimeInfo {
    //0x01：硬件初始化状态
//0x02：软件初始化状态
//0x03：卸力状态
//0x04：加力状态
    public byte downState;// 下控状态
    public int realTimeValue;// 实时力
    public int power;// 功率
    public int distance;// 运动距离
    public int cEffectiveCount;// 当前有效动作次数
    public short cMaxStrength;// 当次动作最大发力

//    手臂位置：
//    Bit0-3：	0x1~0x9  	高度9档 其它异常档；
//    Bit4-7：	0x1x~0x5x 	水平面角度5档 其它异常档；
//    Bit8-11：0x1xx~0x5xx 垂直面角度5档，0x1为最下，0x5为最上


    public int leftArmPostion;// 左臂位置
    public int rightArmPostion;// 右臂位置
    public int leftArmAngleHon;// 左臂水平角度
    public int rightArmAngleHon;// 右臂水平角度
    public int leftArmAngleVer;// 左臂垂直角度
    public int rightArmAngleVer;// 右臂垂直角度
    public int error;// 故障码
    //0	正常，无故障//1	电机霍尔传感器故障//2	皮带光电编码器故障//3	码盘传感器故障//4	电机温度传感器故障//5	制动电阻温度传感器故障//6	驱动板温度传感器故障
    //7	驱动板I2C通讯故障//8	左臂通讯故障//9	右臂通讯故障//10	蓝牙接收板通讯故障//11	屏通讯故障//12	风扇故障//13	48V电源使能故障//14	驱动芯片使能故障
    //15	电机缺相//16	绳子断开//17	驱动硬件故障//100	48V电源过压故障//101	48V电源欠压故障//102	电机过温故障//103	电机过流故障//104	电机超速故障
    //105	制动电阻过温故障//106	MOS管过温故障//200	电机过温警告//201	电机过流警告//202	MOS管过温警告//203	制动电阻过温警告

    public String temp1;//功率模块温度

    public String temp2;//电机温度

    public String temp3;//制动电阻温度

    public int control;// 控制字

    public  boolean isAddingPower=false; //加力状态还是卸力状态

    public boolean leftArmRegister=false; //左臂是否注册

    public boolean rightArmRegister=false; //右臂是否注册

    public boolean leftArmUnlock=false;//左臂是否解锁

    public boolean rightArmUnlock=false ;//右臂是否解锁

    public boolean isActCompleted=false;//当前动作是否完成  1：未完成 0：完成

    public boolean isDoorOpened=false;//门状态  1：关 0：开

    public boolean isPowerLimited=false;//是否为功率限制状态

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealTimeInfo that = (RealTimeInfo) o;
        return downState == that.downState &&
                realTimeValue == that.realTimeValue &&
                power == that.power &&
                distance == that.distance &&
                cEffectiveCount == that.cEffectiveCount &&
                cMaxStrength == that.cMaxStrength &&
                leftArmPostion == that.leftArmPostion &&
                rightArmPostion == that.rightArmPostion &&
                leftArmAngleHon == that.leftArmAngleHon &&
                rightArmAngleHon == that.rightArmAngleHon &&
                leftArmAngleVer == that.leftArmAngleVer &&
                rightArmAngleVer == that.rightArmAngleVer &&
                error == that.error &&
                control == that.control &&
                isAddingPower == that.isAddingPower &&
                leftArmRegister == that.leftArmRegister &&
                rightArmRegister == that.rightArmRegister &&
                leftArmUnlock == that.leftArmUnlock &&
                rightArmUnlock == that.rightArmUnlock &&
                isActCompleted == that.isActCompleted &&
                isDoorOpened == that.isDoorOpened &&
                isPowerLimited == that.isPowerLimited;
    }

    @Override
    public int hashCode() {
        return Objects.hash(downState, realTimeValue, power, distance, cEffectiveCount, cMaxStrength, leftArmPostion, rightArmPostion, error, control, isAddingPower, leftArmRegister, rightArmRegister, leftArmUnlock, rightArmUnlock, isActCompleted, isDoorOpened, isPowerLimited);
    }

    @Override
    public String toString() {
        return "RealTimeInfo{" +
                "downState=" + downState +
                ", realTimeValue=" + realTimeValue +
                ", power=" + power +
                ", distance=" + distance +
                ", cEffectiveCount=" + cEffectiveCount +
                ", cMaxStrength=" + cMaxStrength +
                ", leftArmPostion=" + leftArmPostion +
                ", rightArmPostion=" + rightArmPostion +
                ", leftArmAngleHon=" + leftArmAngleHon +
                ", rightArmAngleHon=" + rightArmAngleHon +
                ", leftArmAngleVer=" + leftArmAngleVer +
                ", rightArmAngleVer=" + rightArmAngleVer +
                ", error=" + error +
                ", temp1='" + temp1 + '\'' +
                ", temp2='" + temp2 + '\'' +
                ", temp3='" + temp3 + '\'' +
                ", control=" + control +
                ", isAddingPower=" + isAddingPower +
                ", leftArmRegister=" + leftArmRegister +
                ", rightArmRegister=" + rightArmRegister +
                ", leftArmUnlock=" + leftArmUnlock +
                ", rightArmUnlock=" + rightArmUnlock +
                ", isActCompleted=" + isActCompleted +
                ", isDoorOpened=" + isDoorOpened +
                ", isPowerLimited=" + isPowerLimited +
                '}';
    }
}
