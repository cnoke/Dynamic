package com.knd.common.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.knd.common.database.bean.HistoryUser;

import java.util.List;

@Dao
public interface HistoryUserDao {
    @Query("SELECT *  FROM HistoryUser where userId!=:userId ORDER BY createTime DESC Limit 0,5 ")
    List<HistoryUser> getAllWithout(String userId);

    @Query("SELECT * FROM HistoryUser WHERE MOBILE = :mobile ")
    HistoryUser getUserByMobile(String mobile);

    @Insert()
    void insertAll(HistoryUser ...historyUsers);

    @Delete
    void delete(HistoryUser historyUser);

    @Update
    void update(HistoryUser historyUser);
}
