package com.knd.common.database.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HistoryUser {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "userId")
    public String userId;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "mobile")
    public String mobile;
    @ColumnInfo(name = "headPicUrl")
    public String headPicUrl;
    @ColumnInfo(name = "createTime")
    public String createTime;
}
