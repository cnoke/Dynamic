package com.knd.common.database.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity = BuriedPoint.class,
        parentColumns = "id",
        childColumns = "pointId"
        ,onDelete = CASCADE))
public class BuriedPointValue{

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "userId")
    public String userId;
    @NonNull
    @ColumnInfo(name = "pointId")
    public long pointId;
    @ColumnInfo(name = "action")
    public String action;
    @ColumnInfo(name = "value")
    public String value;
    @ColumnInfo(name = "createTime")
    public Date createTime;

}
