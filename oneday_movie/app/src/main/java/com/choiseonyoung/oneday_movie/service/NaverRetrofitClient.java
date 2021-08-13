package com.choiseonyoung.oneday_movie.service;

import com.choiseonyoung.oneday_movie.config.NaverAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NaverRetrofitClient {
    private static Retrofit getInstance() {
        return new Retrofit.Builder().baseUrl(NaverAPI.NAVER_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static NaverRetroFit getApiClient() {
        return getInstance().create(NaverRetroFit.class);
    }
}
