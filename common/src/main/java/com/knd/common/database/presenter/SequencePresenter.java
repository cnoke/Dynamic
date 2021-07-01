package com.knd.common.database.presenter;

import com.knd.base.activity.LifecycleNetWork;
import com.knd.common.database.DatabaseManager;
import com.knd.common.database.bean.BuriedPoint;
import com.knd.common.database.bean.BuriedPointParameter;
import com.knd.common.database.bean.BuriedPointRelation;
import com.knd.common.database.bean.BuriedPointValue;
import com.knd.common.database.bean.CityData;
import com.knd.common.database.observer.BaseSingleObserver;

import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class SequencePresenter extends BasePresenter implements LifecycleNetWork {

    public void findAllCity(BaseSingleObserver<List<CityData>> maybeObserver,long time) {
        addDisposable(DatabaseManager.getInstance().database.cityDao().findData(time)
                ,maybeObserver);
    }


    public void insertAll(List<CityData> cityDataList) {
        addDisposable(DatabaseManager.getInstance().database.cityDao().insertAll(cityDataList),new BaseSingleObserver<>(this));
    }

    public void insertBuriedPoint(BaseSingleObserver<Long> maybeObserver,BuriedPoint buriedPoint) {
        addDisposable(DatabaseManager.getInstance().database.buriedPoint().insertBuriedPoint(buriedPoint),maybeObserver);
    }

    public void findBuriedPointRelation(BaseSingleObserver<List<BuriedPointRelation>> maybeObserver, Date time) {
        addDisposable(DatabaseManager.getInstance().database.buriedPoint().findBuriedPointRelation(time)
                ,maybeObserver);
    }

    public void deleteBuriedPoint(BuriedPoint buriedPoint) {
        addDisposable(DatabaseManager.getInstance().database.buriedPoint().deleteBuriedPoint(buriedPoint),new BaseSingleObserver<>(this));
    }

    public void singleInsertValueTransaction(BuriedPointValue buriedPointValue, List<BuriedPointParameter> buriedPointParameters) {
        addDisposable(DatabaseManager.getInstance().database.buriedPoint().singleInsertValueTransaction(buriedPointValue,buriedPointParameters),new BaseSingleObserver<>(this));
    }

    @Override
    public void addRequest(Disposable d) {

    }
}
