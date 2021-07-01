package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BlockBean implements Parcelable {
    private String blockId;
    private String block;
    private int aimSetNum;
    private List<ActionBean> actionList;

    protected BlockBean(Parcel in) {
        blockId = in.readString();
        block = in.readString();
        aimSetNum = in.readInt();
        actionList = in.createTypedArrayList(ActionBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(blockId);
        dest.writeString(block);
        dest.writeInt(aimSetNum);
        dest.writeTypedList(actionList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BlockBean> CREATOR = new Creator<BlockBean>() {
        @Override
        public BlockBean createFromParcel(Parcel in) {
            return new BlockBean(in);
        }

        @Override
        public BlockBean[] newArray(int size) {
            return new BlockBean[size];
        }
    };

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getAimSetNum() {
        return aimSetNum;
    }

    public void setAimSetNum(int aimSetNum) {
        this.aimSetNum = aimSetNum;
    }

    public List<ActionBean> getActionList() {
        return actionList;
    }

    public void setActionList(List<ActionBean> actionList) {
        this.actionList = actionList;
    }

    @Override
    public String toString() {
        return "BlockBean{" +
                "blockId='" + blockId + '\'' +
                ", block='" + block + '\'' +
                ", aimSetNum='" + aimSetNum + '\'' +
                ", actionList=" + actionList +
                '}';
    }
}
