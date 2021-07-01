package com.knd.common.manager;

import android.util.Log;

import com.knd.common.BuildConfig;
import com.knd.common.listener.ObsDownloadObserver;
import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.DownloadFileRequest;
import com.obs.services.model.DownloadFileResult;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.ProgressStatus;
import com.obs.services.model.PutObjectResult;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class OBSManager {

    private final static String TAG = "OBSManager";
    public final ObsClient obsClient;

    private OBSManager() {
        // 创建ObsClient实例
        obsClient = new ObsClient(BuildConfig.OBS_AK, BuildConfig.OBS_SK, BuildConfig.OBS_ENDPOINT);
    }

    public static OBSManager getInstance() {
        return OBSManager.LittleMonkProviderHolder.sInstance;
    }

    // 静态内部类
    private static class LittleMonkProviderHolder {
        private static final OBSManager sInstance = new OBSManager();
    }

    /**
     * 上传图片流
     * @param inputStream
     * @param imageName
     * @return
     */
    public void upLoadImage(InputStream inputStream,String imageName, ObsDownloadObserver baseObserver){
        // 上传图片
        String objectKey = getFileName(imageName,BuildConfig.OBS_IMAGE);
        upLoadInput(inputStream,objectKey,baseObserver);
    }

    /**
     * 上传图片流
     * @param inputStream
     * @param imageName
     * @return
     */
    public void upLoadImageReturnUrl(InputStream inputStream,String imageName, ObsDownloadObserver baseObserver){
        // 上传图片
        String objectKey = getFileName(imageName,BuildConfig.OBS_IMAGE);
        upLoadInputReturnUrl(inputStream,objectKey,baseObserver);
    }

    /**
     * 上传流文件
     * @param inputStream
     * @param objectKey
     * @return
     */
    public void upLoadInput(InputStream inputStream,String objectKey, ObsDownloadObserver baseObserver){

        Observable<String> observable = Observable.create(emitter -> {
            try {
                PutObjectResult result = obsClient.putObject(BuildConfig.OBS_BUCKETNAME, objectKey,inputStream);
                Log.d(TAG,"upLoadInput result  " + result.toString());
                if(!emitter.isDisposed()){
                    emitter.onNext(result.getObjectKey());
                }
            }catch (Exception exception){
                exception.printStackTrace();
                if(!emitter.isDisposed()){
                    emitter.onError(exception);
                }
            }
        });
        ApiSubscribe(observable,baseObserver);
    }

    /**
     * 上传流文件
     * @param inputStream
     * @param objectKey
     * @return
     */
    public void upLoadInputReturnUrl(InputStream inputStream,String objectKey, ObsDownloadObserver baseObserver){

        Observable<String> observable = Observable.create(emitter -> {
            try {
                PutObjectResult result = obsClient.putObject(BuildConfig.OBS_BUCKETNAME, objectKey,inputStream);
                Log.d(TAG,"upLoadInputReturnUrl result  " + result.toString());
                TemporarySignatureRequest req = new TemporarySignatureRequest(HttpMethodEnum.GET, 360000);
                req.setBucketName(BuildConfig.OBS_BUCKETNAME);
                req.setObjectKey(objectKey);
                TemporarySignatureResponse res = obsClient.createTemporarySignature(req);
                if(!emitter.isDisposed()){
                    emitter.onNext(res.getSignedUrl());
                }
            }catch (Exception exception){
                exception.printStackTrace();
                if(!emitter.isDisposed()){
                    emitter.onError(exception);
                }
            }
        });
        ApiSubscribe(observable,baseObserver);
    }


    /**
     * 断点下载
     */
    public void breakPointDownload(String objectKey, String localFilePath, ObsDownloadObserver baseObserver){

        Observable<ProgressStatus> observable = Observable.create(emitter -> {

            if (fileIsExists(localFilePath)){
                if(!emitter.isDisposed()){
                    emitter.onComplete();
                }
            }else{
                DownloadFileRequest request = new DownloadFileRequest(BuildConfig.OBS_BUCKETNAME, objectKey);
                request.setProgressListener(status -> {
                    if(!emitter.isDisposed()){
                        emitter.onNext(status);
                    }
                });
                // 设置下载对象的本地文件路径
                if(localFilePath != null){
                    request.setDownloadFile(localFilePath);
                }
                // 设置分段下载时的最大并发数
                request.setTaskNum(5);
                // 设置分段大小为5MB
                request.setPartSize(5 * 1024 * 1024);
                // 开启断点续传模式
                request.setEnableCheckpoint(true);
                try{
                    // 进行断点续传下载
                    DownloadFileResult result = obsClient.downloadFile(request);
                    if(!emitter.isDisposed()){
                        emitter.onComplete();
                    }
                    Log.d(TAG,"breakPointDownload result  " + result.toString());
                }catch (ObsException e) {
                    Log.d(TAG,e.toString());
                    if(!emitter.isDisposed()){
                        emitter.onError(e);
                    }
                    // 发生异常时可再次调用断点续传下载接口进行重新下载
                }
            }
        });
        ApiSubscribe(observable,baseObserver);
    }

    public void downloadVideo(String url, ObsDownloadObserver baseObserver){
        String filename = getVideoName(url);
        String filePath = "/sdcard/knd_video/" + filename;
        Log.d(TAG,"filename    "+filename);
        breakPointDownload( BuildConfig.OBS_VIDEO + filename,filePath,baseObserver);
    }

    //判断文件是否存在
    public boolean fileIsExists(String filePath) {
        try{
            File f = new File(filePath);
            if(!f.exists())
            {
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 获取视频连接视频名称
     * @param url
     * @return
     */
    public String getVideoName(String url){
        String[] videoUrlStrArray = url.split("\\?")[0].split("/");
        return videoUrlStrArray[videoUrlStrArray.length-1];
    }

    /**
     *
     * @param name
     * @param type BuildConfig.OBS_IMAGE BuildConfig.OBS_VIDEO
     * @return
     */
    public String getFileName(String name,String type){
        name = name.substring(name.lastIndexOf("."));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(type);
        stringBuilder.append(new Date().getTime());
        stringBuilder.append(name);
        return stringBuilder.toString();
    }

    private void ApiSubscribe(Observable observable, ObsDownloadObserver baseObserver) {
        observable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

}
