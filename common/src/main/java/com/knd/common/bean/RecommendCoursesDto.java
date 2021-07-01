package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RecommendCoursesDto implements Parcelable {
    private List<RecommendCourse> records;
    private int total;
    private int size;
    private int current;
    private int pages;

    protected RecommendCoursesDto(Parcel in) {
        records = in.createTypedArrayList(RecommendCourse.CREATOR);
        total = in.readInt();
        size = in.readInt();
        current = in.readInt();
        pages = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(records);
        dest.writeInt(total);
        dest.writeInt(size);
        dest.writeInt(current);
        dest.writeInt(pages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecommendCoursesDto> CREATOR = new Creator<RecommendCoursesDto>() {
        @Override
        public RecommendCoursesDto createFromParcel(Parcel in) {
            return new RecommendCoursesDto(in);
        }

        @Override
        public RecommendCoursesDto[] newArray(int size) {
            return new RecommendCoursesDto[size];
        }
    };

    public List<RecommendCourse> getRecords() {
        return records;
    }

    public void setRecords(List<RecommendCourse> records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
