package com.knd.common.database.dao;

import android.database.sqlite.SQLiteDatabaseCorruptException;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.knd.common.database.bean.BuriedPoint;
import com.knd.common.database.bean.BuriedPointParameter;
import com.knd.common.database.bean.BuriedPointRelation;
import com.knd.common.database.bean.BuriedPointValue;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;

@Dao
public abstract class BuriedPointDao {

    @Insert()
    public abstract Single<Long> insertBuriedPoint(BuriedPoint buriedPoint);

    @Delete()
    public abstract Single<Integer> deleteBuriedPoint(BuriedPoint buriedPoint);

    @Query("SELECT * FROM BuriedPoint WHERE BuriedPoint.createTime <= :now")
    public abstract Single<List<BuriedPointRelation>> findBuriedPointRelation(Date now);

    @Insert()
    public abstract long insertBuriedPointValue(BuriedPointValue buriedPointValue);

    @Insert()
    public abstract List<Long> insertBuriedPointParameters(List<BuriedPointParameter> buriedPointParameters);

    @Transaction()
    public long insertBuriedPointValueTransaction(BuriedPointValue buriedPointValue, List<BuriedPointParameter> buriedPointParameters) {
        long id  = insertBuriedPointValue(buriedPointValue);
        if(id > 0){
            for(BuriedPointParameter buriedPointParameter :  buriedPointParameters){
                buriedPointParameter.pointValueId = id;
            }
            insertBuriedPointParameters(buriedPointParameters);
        }else{
            throw new SQLiteDatabaseCorruptException("buriedPointValue inert failed!");
        }
        return id;
    }

    public Single<Long> singleInsertValueTransaction(BuriedPointValue buriedPointValue, List<BuriedPointParameter> buriedPointParameters) {
        return Single.fromCallable((Callable<Long>) () -> {
            if(buriedPointParameters == null || buriedPointParameters.isEmpty()){
                return insertBuriedPointValue(buriedPointValue);
            }else{
                return insertBuriedPointValueTransaction(buriedPointValue,buriedPointParameters);
            }
        });
    }

}
