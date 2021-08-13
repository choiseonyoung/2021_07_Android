package com.choiseonyoung.oneday_movie.service;

import com.choiseonyoung.oneday_movie.model.NaverParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NaverRetroFit {
    @GET("movie.json")
    public Call<NaverParent> getNews(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("start") int start,
            @Query("display") int display
    );
}
