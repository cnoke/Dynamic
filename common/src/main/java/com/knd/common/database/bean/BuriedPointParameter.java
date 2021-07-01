package com.knd.common.database.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = BuriedPointValue.class,
        parentColumns = "id",
        childColumns = "pointValueId"
        ,onDelete = CASCADE))
public class BuriedPointParameter {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @NonNull
    @ColumnInfo(name = "pointValueId")
    public long pointValueId;
    @ColumnInfo(name = "key")
    public String key;
    @ColumnInfo(name = "value")
    public String value;
}
