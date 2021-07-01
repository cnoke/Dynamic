package com.knd.common.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.knd.common.database.bean.HistoryUser;
import com.knd.common.database.bean.PowerData;

import java.util.List;

@Dao
public interface PowerDataDao {
    @Query("SELECT *  FROM PowerData where `key`==:key")
    PowerData findDataByKey(String key);

    @Insert()
    void insertAll(PowerData... powerData);

    @Update
    void update(PowerData powerData);
}
