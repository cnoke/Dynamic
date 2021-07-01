package com.example.dynamic.application;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.effective.android.anchors.task.Task;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.ClassicFlattener;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy;
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;
import com.example.dynamic.BuildConfig;
import com.kingja.loadsir.core.LoadSir;
import com.knd.base.application.BaseApplication;
import com.knd.base.loadsir.EmptyCallback;
import com.knd.base.loadsir.ErrorCallback;
import com.knd.base.loadsir.ErrorCallback2;
import com.knd.base.loadsir.LoadingCallback;
import com.knd.base.loadsir.TransparentLoadingCallback;
import com.knd.base.util.ProcessUtils;
import com.knd.common.BaseModuleApp;
import com.knd.common.database.DatabaseManager;
import com.knd.dynamicpage.ModuleApp;
import com.knd.network.NetWorkModuleApp;
import com.tencent.bugly.crashreport.CrashReport;

import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class LApplication extends BaseApplication {
    @Override
    protected void attachBaseContext(Context base) {
        addModule(BaseModuleApp.getInstance());
        addModule(NetWorkModuleApp.getInstance());
        addModule(ModuleApp.getInstance());
        addModule(com.knd.dynamicpage.ModuleApp.getInstance());
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {

        isDebug= BuildConfig.DEBUG;
        if("com.example.dynamic".equals(ProcessUtils.getProcessName(this))){
            taskMap.put("initcomm", new Task("initcomm" ,false) {
                @Override
                protected void run(@NotNull String s) {
                    LoadSir.beginBuilder()
                            .addCallback(new ErrorCallback())
                            .addCallback(new ErrorCallback2())
                            .addCallback(new LoadingCallback())
                            .addCallback(new TransparentLoadingCallback())
                            .addCallback(new EmptyCallback())
                            .commit();
                }
            });
            taskMap.put("CrashReport", new Task("CrashReport" ,false) {
                @Override
                protected void run(@NotNull String s) {
                    // 初始化SDK
                    CrashReport.initCrashReport(LApplication.this, BuildConfig.BUGLY_KEY, true);
                }
            });
            taskMap.put("DatabaseManager", new Task("DatabaseManager" ,false) {
                @Override
                protected void run(@NotNull String s) {
                    DatabaseManager.getInstance().init(LApplication.this,"knd_gym");
                }
            });

            taskMap.put("initLog", new Task("initLog" ,false) {
                @Override
                protected void run(@NotNull String s) {
                    initLog();
                }
            });
        }
//        handleSSLHandshake();


        super.onCreate();
    }

    private void initLog(){
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel( LogLevel.ALL)
                .tag("KND")                                         // Specify TAG, default: "X-LOG"
                .b()                                                   // Enable border, disabled by default
                .build();

        Printer androidPrinter = new AndroidPrinter();             // Printer that print the log using android.util.Log
        Printer consolePrinter = new ConsolePrinter();             // Printer that print the log to console using System.out
        Printer filePrinter = new FilePrinter                      // Printer that print the log to the file system
                .Builder("/sdcard/knd_log/")                              // Specify the path to save log file
                .fileNameGenerator(new DateFileNameGenerator())        // Default: ChangelessFileNameGenerator("log")
                .flattener(new ClassicFlattener())
                .backupStrategy(new NeverBackupStrategy())             // Default: FileSizeBackupStrategy(1024 * 1024)
                .cleanStrategy(new FileLastModifiedCleanStrategy(3*24*60*60*1000))     // Default: NeverCleanStrategy()
                .build();

        XLog.init(config,androidPrinter,
                consolePrinter,
                filePrinter);
    }
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception ignored) {
        }
    }
}
