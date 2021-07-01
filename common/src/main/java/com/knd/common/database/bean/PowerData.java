package com.knd.common.database.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PowerData {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "key")
    public String key;
    @ColumnInfo(name = "basePower")
    public float basePower;
    @ColumnInfo(name = "backPower")
    public float backPower;
    @ColumnInfo(name = "continousPower")
    public float continousPower;

    public PowerData(String key, float basePower,float continousPower,float backPower) {
        this.key = key;
        this.basePower = basePower;
        this.backPower = backPower;
        this.continousPower = continousPower;
    }

    @Override
    public String toString() {
        return "PowerData{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", basePower=" + basePower +
                ", backPower=" + backPower +
                ", continousPower=" + continousPower +
                '}';
    }
}
