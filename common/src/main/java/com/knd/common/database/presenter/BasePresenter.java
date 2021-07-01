package com.knd.common.database.presenter;

import com.knd.common.database.DatabaseManager;
import com.knd.common.database.observer.BaseSingleObserver;

import java.util.concurrent.Callable;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter {

    public  <T> void transactionRun(Run<T> run, BaseSingleObserver<T> singleObserver) {
        Single<T> single = Single.fromCallable((Callable<T>) () -> {
            DatabaseManager.getInstance().database.beginTransaction();
            try {
                T result = run.start();
                DatabaseManager.getInstance().database.setTransactionSuccessful();
                return result;
            } finally {
                DatabaseManager.getInstance().database.endTransaction();
            }
        });
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(singleObserver);
    }

    public  <T> void addDisposable(Single<T> single, BaseSingleObserver<T> singleObserver) {
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(singleObserver);
    }

    public  <T> void transactionRun(Run<T> run, MaybeObserver<T> maybeObserver) {
        Maybe<T> maybe = Maybe.fromCallable((Callable<T>) () -> {
            DatabaseManager.getInstance().database.beginTransaction();
            try {
                T result = run.start();
                DatabaseManager.getInstance().database.setTransactionSuccessful();
                return result;
            } finally {
                DatabaseManager.getInstance().database.endTransaction();
            }
        });
        maybe.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(maybeObserver);
    }

    public  <T> void addDisposable(Maybe maybe, MaybeObserver<T> maybeObserver) {
        maybe.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(maybeObserver);
    }

    @FunctionalInterface
    public interface Run<T>{
        T start();
    }
}
