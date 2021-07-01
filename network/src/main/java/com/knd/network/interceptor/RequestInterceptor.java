package com.knd.network.interceptor;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.knd.base.util.PackageUtils;
import com.knd.base.util.SpUtils;
import com.knd.common.utils.SidUtil;
import com.knd.common.utils.StringUtils;
import com.knd.network.IRequestInfo;
import com.knd.common.bean.UserInfo;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    private IRequestInfo requestInfo;
    private Context mContext;

    private static final String KEY_VERSION="version";
    private static final String KEY_PLATFORM="platform";
    public static final String KEY_TOKEN="token";
    public static final String KEY_SID = "sid";

    public RequestInterceptor(Context context, IRequestInfo info){
        this.requestInfo=info;
        this.mContext=context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=null;
        if("GET".equals(chain.request().method().toUpperCase())){
            HttpUrl httpUrl;
            HttpUrl old = chain.request().url();
            HttpUrl.Builder  builder = old.newBuilder();
            if(StringUtils.isEmpty(old.queryParameter("userId"))){
                UserInfo userInfo= (UserInfo) SpUtils.getInstance().getData("user",null);
                String userId = "";
                if(userInfo!=null){
                    userId = userInfo.getUserId();
                }
                builder.addQueryParameter("userId",userId);
            }
            httpUrl= builder.addQueryParameter(KEY_VERSION, PackageUtils.getVersionName(mContext))
                    .addQueryParameter(KEY_PLATFORM,"ANDROID").build();
            request = chain.request().newBuilder().url(httpUrl).build();
            Log.e("RETROFIT","url="+request.url().toString());

        }else if("POST".equals(chain.request().method().toUpperCase())) {
            if(chain.request().body() instanceof FormBody){
                FormBody.Builder builder = new FormBody.Builder();
                FormBody body=(FormBody)chain.request().body();
                for(int i=0;i<body.size();i++){
                    builder.addEncoded(body.encodedName(i),body.encodedValue(i));
                }
                body= builder.addEncoded(KEY_VERSION,PackageUtils.getVersionName(mContext))
                        .addEncoded(KEY_PLATFORM,"ANDROID").build();
                request =chain.request().newBuilder().post(body).build();
            }
            Log.e("RETROFIT","url="+chain.request().url().toString());
        }
        if(request==null) request=chain.request();
        Request.Builder builder  = request.newBuilder();
        if(requestInfo!=null){
            for(String key:requestInfo.getRequestHead().keySet()){
                String header=requestInfo.getRequestHead().get(key);
                if(!TextUtils.isEmpty(header)){
                    builder.addHeader(key,header);
                }
            }
        }

        builder.addHeader(KEY_SID, SidUtil.getInstance().getSid());
        String token= SpUtils.getInstance().getData(KEY_TOKEN,"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJzRUo0dkU0UyIsInBsYXRmb3JtIjoiYXBwIiwiaWF0IjoxNTk2MDk5MjQwfQ._LYGVTmcA1izjPUBP3Xqfx3GqhfljZkzBFLN424TVrM").toString();
        builder =  builder.header("Authorization",token);
        return chain.proceed(builder.build());
    }
}
