package com.knd.common.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
    private ExecutorService executors;
    private static ThreadPoolManager instance;
    private ThreadPoolManager(){

        int CPU_COUNT = Runtime.getRuntime().availableProcessors();
        //核心线程数量大小
        int corePoolSize = Math.max(2, Math.min(CPU_COUNT - 1, 4));
        //线程池最大容纳线程数
        int maximumPoolSize = CPU_COUNT * 2 + 1;
        executors=new ThreadPoolExecutor(corePoolSize,maximumPoolSize,20, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
    }

    public static ThreadPoolManager getInstance(){
        if(instance==null){
            synchronized (ThreadPoolManager.class){
                if(instance==null){
                    instance=new ThreadPoolManager();
                }
            }
        }
        return instance;
    }

    public void execute(Runnable runnable){
        if (!executors.isShutdown()) {
            executors.execute(runnable);
        }
    }

}
