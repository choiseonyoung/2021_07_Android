package com.choiseonyoung.movie.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.choiseonyoung.movie.databinding.MovieItemViewBinding;
import com.choiseonyoung.movie.model.MovieDTO;

import java.util.List;

public class NaverMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieDTO> movieList;
    public NaverMovieAdapter(List<MovieDTO> movieList) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        MovieItemViewBinding movieBinding = MovieItemViewBinding.inflate(layoutInflater, parent, false);

        return new MovieViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder viewHolder = (MovieViewHolder) holder;
        MovieDTO movieDTO = movieList.get(position);
        MovieItemViewBinding mBinding = viewHolder.movieBinding;

        Spanned sTitle = Html.fromHtml(movieDTO.getTitle(), Html.FROM_HTML_MODE_LEGACY);
        mBinding.movieItemTitle.setText(movieDTO.getTitle());

        String strDirect = String.format("<b>감독 : </b> %s", movieDTO.getDirector());
        Spanned sDirect = Html.fromHtml(movieDTO.getTitle(), Html.FROM_HTML_MODE_LEGACY);
        mBinding.movieItemDirect.setText(movieDTO.getDirector());

        String strActor = String.format("<b>출연 : </b>", movieDTO.getActor());
        Spanned sActor = Html.fromHtml(strActor, Html.FROM_HTML_MODE_LEGACY);
        mBinding.movieItemActor.setText(sActor);

        Double intRating = Double.valueOf(movieDTO.getUserRating());
        String strRating = String.format("<b>평점:</b> %3.2f", intRating);
        Spanned sRating = Html.fromHtml(strRating, Html.FROM_HTML_MODE_LEGACY);
        mBinding.movieItemRating.setText(sRating);
        
        /*
        glide를 사용하여 이미지 링크를 참조하여 이미지 표현하기
         */
        // movieDTO.getImage().isEmpty() == false)
        if(!movieDTO.getImage().isEmpty()) {
            Glide.with(mBinding.movieItemImage.getContext()).load(movieDTO.getImage()).into(mBinding.movieItemImage);
            // 현재 이 컨텍스트에 지정돼있는 항목을 참조하는데 여기 이미지에 들어있는 링크를 가져와서 이미지를 로드한 다음에 바인딩에 무비아이템이미지뷰에다가 갖다 보여줘라
        }
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public MovieItemViewBinding movieBinding;
        public MovieViewHolder(@NonNull MovieItemViewBinding movieBinding) {
            super(movieBinding.getRoot());
            this.movieBinding = movieBinding;
        }
    }
}
