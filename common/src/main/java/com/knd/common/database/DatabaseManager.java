package com.knd.common.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.knd.common.database.bean.PowerData;
import com.knd.common.database.presenter.SequencePresenter;

public class DatabaseManager {
    private static DatabaseManager manager;
    public AppDatabase database;
    public SequencePresenter presenter;

    private DatabaseManager(){
    }
    public static DatabaseManager getInstance(){
        if(manager==null){
            synchronized (DatabaseManager.class){
                if(manager==null){
                    manager=new DatabaseManager();
                }
            }
        }
        return manager;
    }

    public void saveOrUpdatePowerData(String key,float basePower,float continousPower,float backPower){
        PowerData powerData=  DatabaseManager.getInstance().database.powerDataDao().findDataByKey(key);
        if(powerData==null){
            DatabaseManager.getInstance().database.powerDataDao().insertAll(new PowerData(key,basePower,continousPower,backPower));
        }else{
            powerData.backPower=backPower;
            powerData.basePower=basePower;
            powerData.continousPower=continousPower;
            DatabaseManager.getInstance().database.powerDataDao().update(powerData);
            Log.e("com.database","update==="+powerData.toString());
        }
    }

    public void init(Context appContext,String name){
        if(database==null){
            database=Room.databaseBuilder(appContext,AppDatabase.class,
                    name).fallbackToDestructiveMigration().build();
            presenter = new SequencePresenter();
        }

    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //此处对于数据库中的所有更新都需要写下面的代码
//            database.execSQL("CREATE TABLE ");
        }
    };

}
