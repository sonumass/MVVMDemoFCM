package com.peoplestrong.mvvmdemo.retrofit;

import com.peoplestrong.mvvmdemo.response.ArticleResponse;
import com.peoplestrong.mvvmdemo.response.LoginRasponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("work/findAll")
    Call<ArticleResponse> getMovieArticles(
            @Field("q") String query,
            @Field("apikey") String apiKey
    );
    @FormUrlEncoded
    @POST("student/login")
    Call<LoginRasponse> isUser(
            @Field("stuCode") String stuCode,
            @Field("type") String apiKey
    );
}
