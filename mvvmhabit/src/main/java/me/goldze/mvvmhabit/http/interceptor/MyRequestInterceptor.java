package me.goldze.mvvmhabit.http.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyRequestInterceptor implements Interceptor {

    private static String token;


    public MyRequestInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request orgReq = chain.request();
        Request build = orgReq.newBuilder()
                .header("accept", "application/json")
                .header("accept", "application/json")
                .header("token", token)
                .build();
            return chain.proceed(build);
    }
}
