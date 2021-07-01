package com.knd.network;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.knd.network.errorhandler.AppDataErrorHandler;
import com.knd.network.errorhandler.HttpErrorHandler;
import com.knd.network.interceptor.RequestInterceptor;
import com.knd.network.interceptor.ResponseInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public abstract class ApiBase {
    protected Retrofit mRetrofit;
    private static ErrorTransformer sErrorTransformer = new ErrorTransformer();
    private static RequestInterceptor mRequestInterceptor;
    private static ResponseInterceptor mResponseInterceptor;
    public ApiBase(String baseurl){
        mRetrofit=new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MyGsonConverterFactory.create())
                .build();
    }

    /**
     * 全局设置需要加入请求头的信息
     */
    public static void setRequestInfo(Context context, IRequestInfo info){
        mRequestInterceptor=new RequestInterceptor(context,info);
        mResponseInterceptor=new ResponseInterceptor();
    }

    public OkHttpClient getOkHttpClient(){
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(mRequestInterceptor)
                .addInterceptor(mResponseInterceptor)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        return client;
    }


    /**
     * 封装线程管理和订阅的过程
     */
    protected void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach()//管道断开时，中断上游对下游的引用
                .compose(sErrorTransformer)
                .onTerminateDetach()//管道断开时，中断上游对下游的引用
                .subscribe(observer);

    }

//    protected void merge(Observable observable,Observable observable2,Observer observer){
//        Observable.merge(observable,observable2)
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(sErrorTransformer)
//                .subscribe(observer);
//    }

    /**
     * 处理错误的变换
     * 网络请求的错误处理，其中网络错误分为两类：
     * 1、http请求相关的错误，例如：404，403，socket timeout等等；
     * 2、http请求正常，但是返回的应用数据里提示发生了异常，表明服务器已经接收到了来自客户端的请求，但是由于
     * 某些原因，服务器没有正常处理完请求，可能是缺少参数，或者其他原因；
     */
    private static class ErrorTransformer<T> implements ObservableTransformer {

        @Override
        public ObservableSource apply(io.reactivex.Observable upstream) {
            //onErrorResumeNext当发生错误的时候，由另外一个Observable来代替当前的Observable并继续发射数据
            return (io.reactivex.Observable<T>) upstream
                    .map(new AppDataErrorHandler())/*返回的数据统一错误处理*/
                    .onErrorResumeNext(new HttpErrorHandler<T>());/*Http 错误处理**/
        }
    }

}
