package com.knd.common.manager;

import android.content.Context;
import android.text.TextUtils;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.AriaManager;
import com.arialyy.aria.core.common.AbsEntity;
import com.arialyy.aria.core.common.QueueMod;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.DownloadGroupEntity;
import com.arialyy.aria.core.download.target.FtpBuilderTarget;
import com.arialyy.aria.core.download.target.GroupBuilderTarget;
import com.arialyy.aria.core.download.target.HttpBuilderTarget;
import com.arialyy.aria.core.inf.IEntity;
import com.arialyy.aria.core.wrapper.ITaskWrapper;
import com.arialyy.aria.orm.DbEntity;
import com.arialyy.aria.util.CheckUtil;
import com.arialyy.aria.util.CommonUtil;
import com.google.gson.Gson;
import com.knd.common.bean.DownloadInfo;
import com.knd.common.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DownloadManger {

    private String videoPath;

    private DownloadManger() {
    }

    public static DownloadManger getInstance() {
        return LittleMonkProviderHolder.sInstance;
    }

    // 静态内部类
    private static class LittleMonkProviderHolder {
        private static final DownloadManger sInstance = new DownloadManger();
    }

    public void init(Context context){
        AriaManager ariaManager = Aria.init(context);

        //断网重试
        ariaManager.getAppConfig().setNotNetRetry(true);

        //普通下载
        ariaManager.getDownloadConfig().setMaxTaskNum(4);
        ariaManager.getDownloadConfig().setThreadNum(5);
        ariaManager.getDownloadConfig().setQueueMod(QueueMod.NOW.getTag());
        ariaManager.getDownloadConfig().setUseBlock(false);

        //组合下载
        ariaManager.getDGroupConfig().setMaxTaskNum(4);
        ariaManager.getDGroupConfig().setQueueMod(QueueMod.NOW.getTag());
        ariaManager.getDGroupConfig().getSubConfig().setUseBlock(false);

    }

    public String start(String url) {
        return start(url,null);
    }

    public String start(String url,DownloadInfo extend) {
        String filename = getVideoName(url);
        String filePath = "/sdcard/knd_video/" + filename;

        return start(url,filePath,extend);
    }

    public String start(String url,String localFilePath,DownloadInfo extend) {
        DownloadEntity mEntity = Aria.download(this).getFirstDownloadEntity(url);
        if (!chekEntityValid(mEntity)) {
            mEntity = new DownloadEntity();
            mEntity.setUrl(url);
            mEntity.setFilePath(localFilePath);
        }
        return start(mEntity,new Gson().toJson(extend));
    }

    public String start(List<String> urls) {
        return start(urls,0,null);
    }

    public String start(List<String> urls,DownloadInfo extend) {
        return start(urls,0,extend);
    }

    public String start(List<String> urls,long fileSize,DownloadInfo extend) {
        Date date = new Date();
        String filePath = "/sdcard/knd_video/" + date.getTime() + "/";
        return start(urls,fileSize,filePath,extend);
    }

    /**
     * 不知道组合文件长度，比较耗时
     * @param urls
     * @param localDirPath
     */
    public String start(List<String> urls, String localDirPath,DownloadInfo extend) {
        return start(urls,0,localDirPath,extend);
    }

    public String start(List<String> urls,long fileSize, String localDirPath,DownloadInfo extend) {
        urls = removeDuplicate(urls);
        DownloadGroupEntity mEntity = Aria.download(this).getGroupEntity(urls);
        if (!chekEntityValid(mEntity)) {
            mEntity = new DownloadGroupEntity();
            String newHash = CommonUtil.getMd5Code(urls);
            mEntity.setGroupHash(newHash);
            mEntity.setUrls(urls);
            mEntity.setDirPath(localDirPath);
            mEntity.setFileSize(fileSize);
            DownloadEntity entity;
            List<DownloadEntity> list = new ArrayList<>();
            for(String url : urls){
                entity = new DownloadEntity();
                entity.setUrl(url);
                list.add(entity);
            }
            mEntity.setSubEntities(list);
        }
        return start(mEntity,new Gson().toJson(extend));
    }

    private String start(AbsEntity entity,String extend) {
        if(entity.getState() == IEntity.STATE_COMPLETE){
            return entity.getKey();
        }
        if (chekEntityValid(entity)) {
            resume(entity);
            return entity.getKey();
        }
        switch (entity.getTaskType()) {
            case ITaskWrapper.D_FTP:
                //Aria.download(getContext()).loadFtp((DownloadEntity) entity).login("lao", "123456").create();
                FtpBuilderTarget ftpBuilderTarget = Aria.download(this).loadFtp(entity.getKey());
                if(TextUtils.isEmpty(entity.getStr()) || !entity.getStr().equals(extend)){
                    ftpBuilderTarget.setExtendField(extend);
                }
                ftpBuilderTarget.create();
                break;
            case ITaskWrapper.D_FTP_DIR:
                break;
            case ITaskWrapper.D_HTTP:
            case ITaskWrapper.M3U8_VOD:
                DownloadEntity mEntity = (DownloadEntity) entity;
                HttpBuilderTarget builderTarget = Aria.download(this)
                        .load(mEntity.getUrl());
                if(TextUtils.isEmpty(entity.getStr()) || !entity.getStr().equals(extend)){
                    builderTarget.setExtendField(extend);
                }
                builderTarget.setFilePath(mEntity.getFilePath())
                        .resetState()
                        .create();
                break;
            case ITaskWrapper.DG_HTTP:
                DownloadGroupEntity mGroupEntity = (DownloadGroupEntity) entity;
                fileIsExists(mGroupEntity.getDirPath());
                GroupBuilderTarget groupBuilderTarget = Aria.download(this)
                        .loadGroup(mGroupEntity.getUrls());
                if(TextUtils.isEmpty(entity.getStr()) || !entity.getStr().equals(extend)){
                    groupBuilderTarget.setExtendField(extend);
                }
                if(mGroupEntity.getFileSize() > 0){
                    groupBuilderTarget.setFileSize(mGroupEntity.getFileSize());

                }else{
                    groupBuilderTarget.unknownSize();
                }
                groupBuilderTarget
                        .setDirPath(mGroupEntity.getDirPath())
                        .create();
                break;
        }
        return entity.getKey();
    }

    public void resume(String url) {
        String filename = getVideoName(url);
        String filePath = "/sdcard/knd_video/" + filename;
        resume(url,filePath);
    }

    public void resume(String url,String filePath) {
        DownloadEntity mEntity = Aria.download(this).getFirstDownloadEntity(url);
        if (!chekEntityValid(mEntity)) {
            mEntity = new DownloadEntity();
            mEntity.setUrl(url);
            mEntity.setFilePath(filePath);
        }
        resume(mEntity);
    }

    public void resume(List<String> urls) {
        resume(urls,0);
    }

    public void resume(List<String> urls,long fileSize) {
        String dirPath = "/sdcard/knd_video/";
        resume(urls,fileSize,dirPath);
    }

    public void resume(List<String> urls,long fileSize,String dirPath) {
        urls = removeDuplicate(urls);
        DownloadGroupEntity mEntity = Aria.download(this).getGroupEntity(urls);
        if (!chekEntityValid(mEntity)) {
            mEntity = new DownloadGroupEntity();
            mEntity.setUrls(urls);
            mEntity.setDirPath(dirPath);
            mEntity.setFileSize(fileSize);
        }
        resume(mEntity);
    }

    private void resume(AbsEntity entity) {
        if (!chekEntityValid(entity)) {
            start(entity,null);
            return;
        }
        switch (entity.getTaskType()) {
            case ITaskWrapper.D_FTP:
                Aria.download(this).loadFtp(entity.getId()).resume(true);
                break;
            case ITaskWrapper.D_FTP_DIR:
                Aria.download(this).loadFtpDir(entity.getId()).resume(true);
                break;
            case ITaskWrapper.D_HTTP:
            case ITaskWrapper.M3U8_VOD:
                Aria.download(this).load(entity.getId()).resume(true);
                break;
            case ITaskWrapper.DG_HTTP:
                Aria.download(this).loadGroup(entity.getId()).resume(true);
                break;
        }
    }

    public void cancel(String url) {
        cancel(getAbsEntity(url));
    }

    public void cancel(List<String> urls) {
        urls = removeDuplicate(urls);
        cancel(getAbsEntity(urls));
    }

    public void cancel(AbsEntity entity) {
        if (!chekEntityValid(entity)) {
            return;
        }
        switch (entity.getTaskType()) {
            case ITaskWrapper.D_FTP:
                Aria.download(this)
                        .loadFtp(entity.getId())
                        //.login("lao", "123456")
                        .cancel(true);
                break;
            case ITaskWrapper.D_FTP_DIR:
                Aria.download(this).loadFtpDir(entity.getId()).cancel(true);
                break;
            case ITaskWrapper.D_HTTP:
            case ITaskWrapper.M3U8_VOD:
                Aria.download(this).load(entity.getId()).cancel(true);
                break;
            case ITaskWrapper.DG_HTTP:
                Aria.download(this).loadGroup(entity.getId()).cancel(true);
                break;
        }
    }

    public void stop(String url) {
        DownloadEntity mEntity = Aria.download(this).getFirstDownloadEntity(url);
        if (chekEntityValid(mEntity)) {
            stop(mEntity);
        }
    }

    public void stop(List<String> urls) {
        urls = removeDuplicate(urls);
        DownloadGroupEntity mEntity = Aria.download(this).getGroupEntity(urls);
        if (chekEntityValid(mEntity)) {
            stop(mEntity);
        }
    }

    private void stop(AbsEntity entity) {
        if (!chekEntityValid(entity)) {
            return;
        }
        switch (entity.getTaskType()) {
            case ITaskWrapper.D_FTP:
                Aria.download(this).loadFtp(entity.getId()).stop();
                break;
            case ITaskWrapper.D_FTP_DIR:
                break;
            case ITaskWrapper.D_HTTP:
            case ITaskWrapper.M3U8_VOD:
                Aria.download(this).load(entity.getId()).stop();
                break;
            case ITaskWrapper.DG_HTTP:
                Aria.download(this).loadGroup(entity.getId()).stop();
                break;
        }
    }

    public String getKey(List<String> urls){
        DownloadGroupEntity downloadGroupEntity = Aria.download(this).getGroupEntity(removeDuplicate(urls));
        if(downloadGroupEntity == null){
            return null;
        }
        return downloadGroupEntity.getKey();
    }

    public AbsEntity getAbsEntity(List<String> urls){
        urls = removeDuplicate(urls);
        return Aria.download(this).getGroupEntity(urls);
    }

    public AbsEntity getAbsEntity(String url){
        return Aria.download(this).getFirstDownloadEntity(url);
    }

    public AbsEntity getGroupChildAbsEntity(String url){
        if (!CheckUtil.checkUrl(url)) {
            return null;
        }
        return DbEntity.findFirst(DownloadEntity.class, "url=? and isGroupChild='true'", url);
    }

    public List<AbsEntity> getTotalTaskList(){
        return Aria.download(this).getTotalTaskList();
    }

    public DownloadEntity getDownloadEntity(long taskId){
        return Aria.download(this).getDownloadEntity(taskId);
    }

    public DownloadGroupEntity getGroupEntity(long taskId){
        return Aria.download(this).getGroupEntity(taskId);
    }

    public void register(Object obj){
        Aria.download(obj).register();
    }

    public void unRegister(Object obj){
        Aria.download(obj).unRegister();
    }

    public void stopAllTask(){
        Aria.download(this).stopAllTask();
    }

    public void resumeAllTask(){
        Aria.download(this).resumeAllTask();
    }

    /**
     * 去除重复数据
     * @param list
     * @return
     */
    public List<String> removeDuplicate(List<String> list) {
        Set set = new LinkedHashSet<String>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    /**
     * 检查实体是否有效
     *
     * @return true 实体有效
     */
    public static boolean chekEntityValid(AbsEntity entity) {
        return entity != null && entity.getId() != -1;
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

    public void fileIsExists(String filePath) {
        try {
            File f = new File(filePath);
            if(!f.exists()) {
                new File(filePath).mkdirs();
            }
        } catch (Exception e) {
            try {
                new File(filePath).mkdirs();
            }catch (Exception et){
                et.printStackTrace();
            }
        }
    }

    public String getVideoPath(String url){
        List<DownloadEntity> list = getDownloadEntity(url);
        if(list != null){
            for(DownloadEntity downloadEntity : list){
                if(downloadEntity.isComplete()){
                    return downloadEntity.getFilePath();
                }
            }
        }
        return url;
    }

    public List<DownloadEntity> getDownloadEntity(String downloadUrl) {
        if (!CheckUtil.checkUrl(downloadUrl)) {
            return null;
        }
        return DbEntity.findDatas(DownloadEntity.class, "url=?", downloadUrl);
    }


    public boolean isSame(List<String> list,String key){
        if(StringUtils.isEmpty(key) || list == null || list.isEmpty()){
            return false;
        }
        list = removeDuplicate(list);
        String newHash = CommonUtil.getMd5Code(list);
        if(key.equals(newHash)){
            return true;
        }else{
            return false;
        }
    }

    private void fileDire(File file,String fileName){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files == null || files.length == 0){
            }else{
                for(File newFile : files){
                    fileDire(newFile,fileName);
                }
            }
        }else{
            if(fileName.equals(file.getName())){
                videoPath = file.getPath();
            }
        }
    }

}
