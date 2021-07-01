package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CommonBean implements Parcelable {
    private String id;
    private String name;
    private boolean isSelect;

    public CommonBean() {
    }

    public CommonBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected CommonBean(Parcel in) {
        id = in.readString();
        name = in.readString();
        isSelect = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommonBean> CREATOR = new Creator<CommonBean>() {
        @Override
        public CommonBean createFromParcel(Parcel in) {
            return new CommonBean(in);
        }

        @Override
        public CommonBean[] newArray(int size) {
            return new CommonBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "CommonBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
