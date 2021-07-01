package com.knd.common.utils;

import androidx.annotation.NonNull;

import com.knd.common.api.DownloadService;
import com.knd.common.listener.DownloadListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadUtil {
    public static  Call<ResponseBody> call;

    /**
     *
     * @param url
     * @param path
     * @param listener
     */
    public static void download(String url, final String appType, final String path, final DownloadListener listener){

//        String[] segments= url.split("/");
//        if(segments.length<1) return;
        int index=  url.lastIndexOf("/");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.substring(0,index+1))
                //通过线程池获取一个线程，指定callback在子线程中运行。
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .client(new OkHttpClient.Builder().sslSocketFactory(SslSocketClient.getSSLSocketFactory())
                        .hostnameVerifier(SslSocketClient.getHostnameVerifier()).build())
                .build();

        DownloadService service = retrofit.create(DownloadService.class);
//        StringBuilder sb=new StringBuilder();
//        for(int i=1;i<segments.length;i++){
//            if(i==segments.length-1){
//               sb=sb.append(segments[i]);
//            }else{
//                sb=sb.append(segments[i]+"/");
//            }
//        }
        if(call!=null) return;
        call= service.download(url.substring(index+1));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                //将Response写入到从磁盘中，详见下面分析
                //注意，这个方法是运行在子线程中的
                if (appType.equals("0")){
                    writeResponseToDisk(path, response, listener);
                }else if (appType.equals("1")){
                    writeBinFileToDisk(path, response, listener);
                }else if (appType.equals("2")){
                    writeSysFileToDisk(path, response, listener);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                listener.onFail("网络错误～");
                DownloadUtil.call=null;
            }
        });

    }

    //下载系统文件update.img
    private static void writeSysFileToDisk(String path, Response<ResponseBody> response, DownloadListener downloadListener) {
        //从response获取输入流以及总大小
        if(response.body()!=null) {
            writeFileFromIS(new File(path+"/update.zip"), response.body().byteStream(), response.body().contentLength(), downloadListener);
        }else{
            DownloadUtil.call=null;
            downloadListener.onFail("返回数据异常");
        }
    }

    //下载固件文件FOC_HALL_ENC.bin
    private static void writeBinFileToDisk(String path, Response<ResponseBody> response, DownloadListener downloadListener) {
        //从response获取输入流以及总大小
        if(response.body()!=null) {
            writeFileFromIS(new File(path+"/FOC_HALL_ENC.bin"), response.body().byteStream(), response.body().contentLength(), downloadListener);
        }else{
            DownloadUtil.call=null;
            downloadListener.onFail("返回数据异常");
        }
    }


    //下载app更新包knd_gym.apk
    private static void writeResponseToDisk(String path, Response<ResponseBody> response, DownloadListener downloadListener) {
        //从response获取输入流以及总大小
        if(response.body()!=null) {
            writeFileFromIS(new File(path+"/knd_gym.apk"), response.body().byteStream(), response.body().contentLength(), downloadListener);
        }else{
            DownloadUtil.call=null;
            downloadListener.onFail("返回数据异常");
        }
    }

    public static void downloadVideo(String url, final DownloadListener listener){
        String[] videoUrlStrArray = url.split("\\?")[0].split("/");
        final String filename = videoUrlStrArray[videoUrlStrArray.length-1];
        final String filePath = "/sdcard/knd_video/"+filename;
        if (fileIsExists(filePath)){
            listener.onFinish(filePath);
        }else {
            int index=  url.lastIndexOf("/");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url.substring(0,index+1))
                    //通过线程池获取一个线程，指定callback在子线程中运行。
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .client(new OkHttpClient.Builder().sslSocketFactory(SslSocketClient.getSSLSocketFactory())
                            .hostnameVerifier(SslSocketClient.getHostnameVerifier()).build())
                    .build();
            DownloadService service = retrofit.create(DownloadService.class);
            if(call!=null) return;
            call= service.download(url.substring(index+1));
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                    //将Response写入到从磁盘中，详见下面分析
                    //注意，这个方法是运行在子线程中的
                    //从response获取输入流以及总大小
                    if(response.body()!=null) {
                        writeFileFromIS(new File(filePath), response.body().byteStream(), response.body().contentLength(), listener);
                    }else{
                        DownloadUtil.call=null;
                        listener.onFail("返回数据异常");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                    listener.onFail("网络错误～");
                    DownloadUtil.call=null;
                }
            });
        }
    }

    /**
     * 从视频地址中取出文件路径
     * @param fileUrl 视频url
     * @return
     */
    public static String getFilePathFromUrl(String fileUrl){
        String[] videoUrlStrArray = fileUrl.split("\\?")[0].split("/");
        final String filename = videoUrlStrArray[videoUrlStrArray.length-1];
        final String filePath = "/sdcard/knd_video/"+filename;
        return filePath;
    }

    //判断文件url包含的文件是否已下载
    public static boolean urlFileIsExists(String fileUrl){
        final String filePath = getFilePathFromUrl(fileUrl);
        try
        {
            File f = new File(filePath);
            if(!f.exists())
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    //判断文件是否存在
    public static boolean fileIsExists(String filePath) {
        try
        {
            File f = new File(filePath);
            if(!f.exists())
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    private static int sBufferSize = 8192;

    //将输入流写入文件
    private static void writeFileFromIS(File file, InputStream is, long totalLength, DownloadListener downloadListener) {
        //开始下载
        downloadListener.onStart();

        if(file.exists()){
            file.delete();
        }
        //创建文件
        if (!file.exists()) {
            if (!file.getParentFile().exists())
                file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                downloadListener.onFail("createNewFile IOException");
                DownloadUtil.call=null;
            }
        }

        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;
                //计算当前下载进度
                downloadListener.onProgress((int) (100 * currentLength / totalLength));
            }
            //下载完成，并返回保存的文件路径
            downloadListener.onFinish(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            downloadListener.onFail("下载失败");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            DownloadUtil.call=null;

        }
    }
}


