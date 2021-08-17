package com.choiseonyoung.movie.service.impl;

import com.choiseonyoung.movie.adapter.NaverMovieAdapter;
import com.choiseonyoung.movie.config.NaverAPI;
import com.choiseonyoung.movie.databinding.FragmentSecondBinding;
import com.choiseonyoung.movie.model.NaverParent;
import com.choiseonyoung.movie.service.NaverAPIService;
import com.choiseonyoung.movie.service.RetrofitClient;

public class NaverMovieServiceImplV1 implements NaverAPIService {

    private NaverMovieAdapter adapter;
    private FragmentSecondBinding secondBinding;

    public NaverMovieServiceImplV1(FragmentSecondBinding secondBinding) {
        this.secondBinding = secondBinding;
    }

    @Override
    public void getNaverMovie(String search) {

        /*
        Naver에 API 조회를 수행하기 위한 객체를 생성하기
         */
        CALL<NaverParent> naverCall = RetrofitClient.getApiClient().getMovies(NaverAPI.NAVER_CLIENT_ID, NaverAPI.NAVER_CLIENT_SECRET, search, 1, 20);
        naverCall.enq
    }
}
