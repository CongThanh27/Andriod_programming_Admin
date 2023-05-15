package com.example.appfood.retrofitClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;


    public static Retrofit getRetrofitCategory(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://app.iotstar.vn/appfoods/")
                    .addConverterFactory(GsonConverterFactory.create((gson)))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitProduct(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://app.iotstar.vn/appfoods/")
                    .addConverterFactory(GsonConverterFactory.create((gson)))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitUserLogin() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://app.iotstar.vn/appfoods/")
                    .addConverterFactory(GsonConverterFactory.create((gson)))
                    .build();
        }
        return retrofit;
    }
}
