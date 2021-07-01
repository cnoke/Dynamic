package com.knd.common.database;

import androidx.room.Database;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.knd.base.room.BaseRoomDataBase;
import com.knd.common.database.bean.BuriedPoint;
import com.knd.common.database.bean.BuriedPointParameter;
import com.knd.common.database.bean.BuriedPointValue;
import com.knd.common.database.bean.BuriedPointValueRelation;
import com.knd.common.database.bean.CityData;
import com.knd.common.database.bean.HistoryUser;
import com.knd.common.database.bean.PowerData;
import com.knd.common.database.dao.BuriedPointDao;
import com.knd.common.database.dao.CityDao;
import com.knd.common.database.dao.HistoryUserDao;
import com.knd.common.database.dao.PowerDataDao;


@TypeConverters(value = {TypeConverterUtils.class})
@Database(entities = {HistoryUser.class, PowerData.class, CityData.class
        , BuriedPoint.class, BuriedPointValue.class
        , BuriedPointParameter.class}, views = {BuriedPointValueRelation.class},version = 5)
public abstract class AppDatabase extends BaseRoomDataBase {

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };

    public abstract HistoryUserDao historyUserDao();
    public abstract PowerDataDao powerDataDao();
    public abstract CityDao cityDao();
    public abstract BuriedPointDao buriedPoint();
}
