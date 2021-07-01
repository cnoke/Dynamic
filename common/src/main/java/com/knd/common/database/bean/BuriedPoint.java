package com.knd.common.database.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class BuriedPoint{

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "createTime")
    public Date createTime;
}
