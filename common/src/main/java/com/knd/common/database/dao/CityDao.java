package com.knd.common.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.knd.common.database.bean.CityData;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CityDao {

    @Query("SELECT * FROM CityData where CityData.updatetime >= :time order by CityData.pinyin asc")
    Single<List<CityData>> findData(long time);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertAll(List<CityData> cityDataList);

}
