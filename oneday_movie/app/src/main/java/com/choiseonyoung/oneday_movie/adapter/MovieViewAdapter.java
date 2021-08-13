package com.choiseonyoung.oneday_movie.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.choiseonyoung.oneday_movie.databinding.MovieItemViewBinding;
import com.choiseonyoung.oneday_movie.model.NaverMovieDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NaverMovieDTO> movieList;

    public MovieViewAdapter(List<NaverMovieDTO> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MovieItemViewBinding viewBinding = MovieItemViewBinding.inflate(layoutInflater,parent,false);

        return new MovieViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder viewHolder = (MovieViewHolder) holder;

        MovieItemViewBinding movieBinding = viewHolder.viewBinding;

        NaverMovieDTO movieDTO = movieList.get(position);

        viewHolder.viewBinding.movieItemTitle.setText(movieDTO.getTitle());

        Spanned movieTitle = Html.fromHtml(movieDTO.getTitle(), Html.FROM_HTML_MODE_LEGACY);
        movieBinding.movieItemTitle.setText(movieTitle);

        Spanned movieDir = Html.fromHtml(movieDTO.getDirector(), Html.FROM_HTML_MODE_LEGACY);
        movieBinding.movieItemDir.setText(movieDir);

        Spanned movieActor = Html.fromHtml(movieDTO.getActor(), Html.FROM_HTML_MODE_LEGACY);
        movieBinding.movieItemActor.setText(movieActor);

        Spanned movieRate = Html.fromHtml(movieDTO.getUserRating(), Html.FROM_HTML_MODE_LEGACY);
        movieBinding.movieItemRate.setText(movieRate);

        if(!movieDTO.getImage().isEmpty()) {
            Picasso.get().load(movieDTO.getImage()).into(viewHolder.viewBinding.movieItemImage);
        }
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public MovieItemViewBinding viewBinding;

        public MovieViewHolder(@NonNull MovieItemViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
