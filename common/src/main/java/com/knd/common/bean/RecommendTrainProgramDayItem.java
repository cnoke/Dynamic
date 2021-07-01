package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class RecommendTrainProgramDayItem implements Parcelable {

    /**
     * 训练项目类型 0课程 1动作序列
     */
//    private int itemType;
    /**
     * 训练项目Id
     */
    private String id;
    private String course;
    private String courseType;
    private double amount;
    private boolean pay;

    public RecommendTrainProgramDayItem() {
    }

    protected RecommendTrainProgramDayItem(Parcel in) {
        id = in.readString();
        course = in.readString();
        courseType = in.readString();
        amount = in.readDouble();
        pay = in.readByte() != 0;
    }


    public static final Creator<RecommendTrainProgramDayItem> CREATOR = new Creator<RecommendTrainProgramDayItem>() {
        @Override
        public RecommendTrainProgramDayItem createFromParcel(Parcel in) {
            return new RecommendTrainProgramDayItem(in);
        }

        @Override
        public RecommendTrainProgramDayItem[] newArray(int size) {
            return new RecommendTrainProgramDayItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(course);
        parcel.writeString(courseType);
        parcel.writeDouble(amount);
        parcel.writeByte((byte) (pay ? 1 : 0));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }
}
