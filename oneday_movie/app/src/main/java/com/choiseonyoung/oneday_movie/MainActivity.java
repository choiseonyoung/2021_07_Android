package com.choiseonyoung.oneday_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;

import com.choiseonyoung.oneday_movie.databinding.ActivityMainBinding;
import com.choiseonyoung.oneday_movie.service.NaverMovieService;
import com.choiseonyoung.oneday_movie.service.impl.NaverMovieServiceImplV1;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding main_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main_binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(main_binding.getRoot());

        setSupportActionBar(main_binding.mainAppToolbar);

//        NaverMovieService naverMovieService = new NaverMovieServiceImplV1(main_binding.movieListView);
//        naverMovieService.getMovie("공주");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setQueryHint("영화명 검색");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("검색버튼 클릭 : ", query);

                NaverMovieService naverMovieService = new NaverMovieServiceImplV1(main_binding.movieListView);
                naverMovieService.getMovie(query.trim());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("현재 입력하는 문자열 : ", newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}