package com.peoplestrong.mvvmdemo.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://newsapi.org/";
    private static final String BASE_URL_LOGIN = "https://raashan123.herokuapp.com/";

    public static Retrofit getRetrofitInstance(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
