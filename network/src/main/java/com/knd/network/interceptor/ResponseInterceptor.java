package com.knd.network.interceptor;

import android.os.Build;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request= chain.request();
        Response response= chain.proceed(request);
//        String body= response.body().string();
//        Log.e("Retrofit","response="+body);
        return response;
////        String rawJson=body==null?"":body.string();
//        return  response.newBuilder().body(ResponseBody.create(null,body)).build();
//         response;
    }
}
