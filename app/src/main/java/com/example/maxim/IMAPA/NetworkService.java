package com.example.maxim.IMAPA;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "http://fb5d3d79.ngrok.io/api/";
    private Retrofit mRetrofit;

    private NetworkService() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + Global.token.getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public JsonPlaceholderApi getJSONApi() {
        return mRetrofit.create(JsonPlaceholderApi.class);
    }
}