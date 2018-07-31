package com.example.vst.apptest.ServiceRetrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient(String baseurl) {

        OkHttpClient builder = new OkHttpClient.Builder()
                                                .readTimeout(5000, TimeUnit.MILLISECONDS)
                                                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                                                .connectTimeout(5000,TimeUnit.MILLISECONDS)
                                                .retryOnConnectionFailure(true)
                                                 .build();
        Gson gson = new GsonBuilder().setLenient().create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(builder)
                // chuyển dữ liệu gson về biến java
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

            return mRetrofit;

    }

}
