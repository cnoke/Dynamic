package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SeriesCourse implements Parcelable{
    private String id;
    private String name;
    private String synopsis;//简介
    private String picAttachUrl;

    public SeriesCourse() {
    }

    protected SeriesCourse(Parcel in) {
        id = in.readString();
        name = in.readString();
        synopsis = in.readString();
        picAttachUrl = in.readString();
    }

    public static final Creator<SeriesCourse> CREATOR = new Creator<SeriesCourse>() {
        @Override
        public SeriesCourse createFromParcel(Parcel in) {
            return new SeriesCourse(in);
        }

        @Override
        public SeriesCourse[] newArray(int size) {
            return new SeriesCourse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(synopsis);
        parcel.writeString(picAttachUrl);
    }

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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPicAttachUrl() {
        return picAttachUrl;
    }

    public void setPicAttachUrl(String picAttachUrl) {
        this.picAttachUrl = picAttachUrl;
    }
}
