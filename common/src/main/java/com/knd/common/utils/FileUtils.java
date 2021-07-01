package com.knd.common.utils;

import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class FileUtils {
    public static long getFoldSize(String fold){
        File file=new File(fold);
        long fileSize=0;
        if(file.isDirectory()){
            //是目录
            for(File f:file_array(fold)){
                fileSize+=f.length();
            }
        }
        return fileSize;
    }

    public static void file_update(String file_path){
        File file=new File(file_path);
        if(file.exists()){
            file.setLastModified(new Date().getTime());
        }
    }

    public static void start_delete_file(String path,long minSize){
        File[] files=file_array(path);
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return (int) (o2.lastModified()-o1.lastModified());
            }
        });
        long deleteSize=0;
        for(int i=files.length-1;i>=0;i--){
            deleteSize+=files[i].length();
            String temp_path= files[i].getAbsolutePath().replace("mp4","temp");
            File file=new File(temp_path);
            if(file.exists()){
                file.delete();
            }
            files[i].delete();
            if(deleteSize>=minSize){
                return;
            }
        }
    }

    /** 删除单个文件
     * @param filePath$Name 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteSingleFile(String filePath$Name) {
        File file = new File(filePath$Name);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public static File[] file_array(String path){
        final File file=new File(path);
        return file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.isDirectory() && pathname.getName().endsWith(".mp4");
            }
        });
    }


}
