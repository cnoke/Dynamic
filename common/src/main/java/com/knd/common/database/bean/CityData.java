package com.knd.common.database.bean;

import android.text.TextUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.knd.common.fuzzsearch.IFuzzySearchItem;
import com.knd.common.utils.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity(indices = {@Index(value = {"name"},
        unique = true)})
public class CityData implements MultiItemEntity
        ,Comparable<CityData>, IFuzzySearchItem, Serializable {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "code")
    public String code;
    @ColumnInfo(name = "pinyin")
    public String pinyin;
    @ColumnInfo(name = "fullpinyin")
    public String fullPinYin;
    @ColumnInfo(name = "latitude")
    public double latitude;
    @ColumnInfo(name = "longitude")
    public double longitude;
    @ColumnInfo(name = "updatetime")
    public long updatetime;
    @Ignore
    public int type = 2;
    @Ignore
    public List<String> fullList;

    public CityData(String name, String code,double latitude,double longitude
            , String pinyin,String fullPinYin,long updatetime) {
        this.pinyin = pinyin;
        this.code = code;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fullPinYin = fullPinYin;
        this.updatetime = updatetime;
    }

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public int compareTo(CityData o) {
        if(TextUtils.isEmpty(pinyin)){
            return 0;
        }else{
            return pinyin.compareTo(o.pinyin);
        }
    }

    @Override
    public String getSourceKey() {
        return name;
    }

    @Override
    public List<String> getFuzzyKey() {
        if(fullList != null){
            return fullList;
        }
        if(StringUtils.isEmpty(fullPinYin)){
            return null;
        }
        String[] full = fullPinYin.split(",");
        if(full == null || full.length <= 0){
            return null;
        }
        fullList = Arrays.asList(full);
        return fullList;
    }

    @Override
    public String toString() {
        return "CityData{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", fullPinYin='" + fullPinYin + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", updatetime=" + updatetime +
                '}';
    }
}
