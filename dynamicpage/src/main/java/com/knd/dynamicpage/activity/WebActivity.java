package com.knd.dynamicpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.knd.base.util.ToastUtils;
import com.knd.common.activity.BaseActivity;
import com.knd.common.key.ArouterKey;
import com.knd.common.route.ParamKey;
import com.knd.common.utils.StringUtils;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.databinding.DynamicActivityWebBinding;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Route(path = ArouterKey.DYNAMIC_ACTIVITY_WEB)
public class WebActivity extends BaseActivity<DynamicActivityWebBinding>{

    @Autowired(name = ParamKey.URL ,required = true)
    public String url;

    private static String TAG = "WebActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        hookWebView();
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);
        if(StringUtils.isEmpty(url)){
            finish();
            return;
        }
        Uri uri = Uri.parse(url);
        viewBinding.ivBack.setOnClickListener(v -> onBackPressed());
        viewBinding.tvTitle.setText(uri.getQueryParameter("quinnoidTitle"));
        viewBinding.webContent.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                try {
                    String url = request.getUrl().toString();
                    if(!url.startsWith("http://")
                            && !url.startsWith("https://")) {
                        ToastUtils.show(WebActivity.this,"?????????????????????APP");
//                        Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
//                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //??????crash (???????????????????????????????????????scheme?????????url???APP, ?????????crash)
                    return true;//???????????????app????????????true?????????????????????????????????????????????????????????????????????????????????
                }

                //??????http???https?????????url
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); // Android?????????????????????
                handler.proceed();  // ???????????????????????????
                //handleMessage(Message msg); // ??????????????????
            }
        });
        viewBinding.webContent.setHorizontalScrollBarEnabled(false);
        viewBinding.webContent.getSettings().setJavaScriptEnabled(true);
        viewBinding.webContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        viewBinding.webContent.loadUrl(url);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dynamic_activity_web;
    }

    @Override
    public void onRetryBtnClick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((ViewGroup)viewBinding.webContent.getParent()).removeView(viewBinding.webContent);
        viewBinding.webContent.stopLoading();
        viewBinding.webContent.getSettings().setJavaScriptEnabled(false);
        viewBinding.webContent.clearHistory();
        viewBinding.webContent.removeAllViews();
        viewBinding.webContent.destroy();
    }

    public void hookWebView(){
        int sdkInt = Build.VERSION.SDK_INT;
        try {
            Class<?> factoryClass = Class.forName("android.webkit.WebViewFactory");
            Field field = factoryClass.getDeclaredField("sProviderInstance");
            field.setAccessible(true);
            Object sProviderInstance = field.get(null);
            if (sProviderInstance != null) {
                Log.i(TAG,"sProviderInstance isn't null");
                return;
            }

            Method getProviderClassMethod;
            if (sdkInt > 22) {
                getProviderClassMethod = factoryClass.getDeclaredMethod("getProviderClass");
            } else if (sdkInt == 22) {
                getProviderClassMethod = factoryClass.getDeclaredMethod("getFactoryClass");
            } else {
                Log.i(TAG,"Don't need to Hook WebView");
                return;
            }
            getProviderClassMethod.setAccessible(true);
            Class<?> factoryProviderClass = (Class<?>) getProviderClassMethod.invoke(factoryClass);
            Class<?> delegateClass = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> delegateConstructor = delegateClass.getDeclaredConstructor();
            delegateConstructor.setAccessible(true);
            if(sdkInt < 26){//??????Android O??????
                Constructor<?> providerConstructor = factoryProviderClass.getConstructor(delegateClass);
                if (providerConstructor != null) {
                    providerConstructor.setAccessible(true);
                    sProviderInstance = providerConstructor.newInstance(delegateConstructor.newInstance());
                }
            } else {
                Field chromiumMethodName = factoryClass.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
                chromiumMethodName.setAccessible(true);
                String chromiumMethodNameStr = (String)chromiumMethodName.get(null);
                if (chromiumMethodNameStr == null) {
                    chromiumMethodNameStr = "create";
                }
                Method staticFactory = factoryProviderClass.getMethod(chromiumMethodNameStr, delegateClass);
                if (staticFactory!=null){
                    sProviderInstance = staticFactory.invoke(null, delegateConstructor.newInstance());
                }
            }

            if (sProviderInstance != null){
                field.set("sProviderInstance", sProviderInstance);
                Log.i(TAG,"Hook success!");
            } else {
                Log.i(TAG,"Hook failed!");
            }
        } catch (Throwable e) {
            Log.w(TAG,e);
        }
    }
}
