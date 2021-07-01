package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TrainProgramDayItemRequest implements Parcelable {

    /**
     * 训练项目类型 0课程 1动作序列
     */
    private int itemType;
    /**
     * 训练项目Id
     */
    private String itemId;
    private String itemName;

    private boolean isChecked = false;

    public TrainProgramDayItemRequest() {
    }

    protected TrainProgramDayItemRequest(Parcel in) {
        itemType = in.readInt();
        itemId = in.readString();
        itemName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(itemType);
        parcel.writeString(itemId);
        parcel.writeString(itemName);
    }

    public static final Creator<TrainProgramDayItemRequest> CREATOR = new Creator<TrainProgramDayItemRequest>() {
        @Override
        public TrainProgramDayItemRequest createFromParcel(Parcel in) {
            return new TrainProgramDayItemRequest(in);
        }

        @Override
        public TrainProgramDayItemRequest[] newArray(int size) {
            return new TrainProgramDayItemRequest[size];
        }
    };

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
