package com.knd.common.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class TypeConverterUtils {

    @TypeConverter
    public long dateToTimestamp(Date date){
        if(date != null)return date.getTime();
        return 0;
    }

    @TypeConverter
    public Date fromTimeStamp(long timeStamp){
        return new Date(timeStamp);
    }

}
