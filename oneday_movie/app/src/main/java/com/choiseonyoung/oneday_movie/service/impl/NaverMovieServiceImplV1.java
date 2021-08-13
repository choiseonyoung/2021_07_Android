package com.choiseonyoung.oneday_movie.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.choiseonyoung.oneday_movie.adapter.MovieViewAdapter;
import com.choiseonyoung.oneday_movie.config.NaverAPI;
import com.choiseonyoung.oneday_movie.model.NaverMovieDTO;
import com.choiseonyoung.oneday_movie.model.NaverParent;
import com.choiseonyoung.oneday_movie.service.NaverMovieService;
import com.choiseonyoung.oneday_movie.service.NaverRetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMovieServiceImplV1 implements NaverMovieService {

    private RecyclerView movieListView;
    public NaverMovieServiceImplV1(RecyclerView movieListView) {
        this.movieListView = movieListView;
    }

    @Override
    public void getMovie(String search) {
        Call<NaverParent> naverCall = NaverRetrofitClient.getApiClient().getNews(NaverAPI.CLIENT_ID, NaverAPI.CLIENT_SECRET, search, 1, 20);

        naverCall.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                int resCode = response.code();
                if(resCode < 300) {
                    Log.d("네이버 영화 조회", response.body().toString());
                    List<NaverMovieDTO> movieList = response.body().items;
                    MovieViewAdapter viewAdapter = new MovieViewAdapter(movieList);
                    movieListView.setAdapter(viewAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(movieListView.getContext());
                    movieListView.setLayoutManager(layoutManager);

                } else {
                    Log.d("오류발생", response.toString());
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });
    }
}
