package com.knd.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SeriesCoursesDto implements Parcelable {
    private List<SeriesCourse> records;
    private int total;
    private int size;
    private int current;
    private int pages;

    protected SeriesCoursesDto(Parcel in) {
        records = in.createTypedArrayList(SeriesCourse.CREATOR);
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

    public static final Creator<SeriesCoursesDto> CREATOR = new Creator<SeriesCoursesDto>() {
        @Override
        public SeriesCoursesDto createFromParcel(Parcel in) {
            return new SeriesCoursesDto(in);
        }

        @Override
        public SeriesCoursesDto[] newArray(int size) {
            return new SeriesCoursesDto[size];
        }
    };

    public List<SeriesCourse> getRecords() {
        return records;
    }

    public void setRecords(List<SeriesCourse> records) {
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
