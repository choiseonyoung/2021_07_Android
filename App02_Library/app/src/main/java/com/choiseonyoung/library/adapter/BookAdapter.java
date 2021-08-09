package com.choiseonyoung.library.adapter;

import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.choiseonyoung.library.databinding.ActivityMainBinding;
import com.choiseonyoung.library.databinding.BookItemViewBinding;
import com.choiseonyoung.library.model.BookDTO;
import com.choiseonyoung.library.service.NaverAPIServiceV1;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 리스트에 담겨있는 내용을 리싸이클러뷰를 이용해 표시를 하고 싶다
    private List<BookDTO> bookList;

    public BookAdapter(List<BookDTO> bookDTOList) {
        this.bookList = bookDTOList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        BookItemViewBinding bookItemViewBinding = BookItemViewBinding.inflate(layoutInflater, parent, false);

        RecyclerView.ViewHolder viewHolder = new BookViewHolder(bookItemViewBinding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookViewHolder bookHolder = (BookViewHolder) holder;
        BookDTO bookDTO = bookList.get(position);

        TextView txt_title = bookHolder.bookItemView.itemTxtTitle;
        /**
         * HtmlCompat.fromHtml()
         * 문자열 내에 HTML tag가 포함되어 있으면
         * tag에 효과를 적용하여 문자열을 화면에 그리기 위한 변환 method
         * Nougat(7.0) 이상에서만 작동되는 method
         * Nougat 이하에서는 원래는 작동되었는데 최근 Android에서는 제거되었다
         */
        String strTitle = "<font color=blue>" + bookDTO.getTitle() + "</font>";
        // 위 같은 코드는 요즘 거의 없
        strTitle = "<span style='color:#0000FF'>";
        strTitle += bookDTO.getTitle() + "</span>";

        // 글자색을 파란색으로 바꿔라
        txt_title.setText(HtmlCompat.fromHtml(strTitle, HtmlCompat.FROM_HTML_MODE_LEGACY));
        // HtmlCompat : 원래 html 태그를 제거하는 코드였음.

//        Spannable span = (Spannable) txt_title.getText();
//        span.setSpan(new ForegroundColorSpan(Color.BLUE), 0, txt_title.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 원래 글자색이나 바탕색 그릴 때 이렇게 그렸음. 너무 복잡함. 안드로이드가 권장하는 방법은 이거긴 함
    }

    @Override
    public int getItemCount() {
        return bookList == null ? 0 : bookList.size();
    }

    /**
     * 각 데이터 item을 표현하기 위한 View 객체 생성하기
     */
    public static class BookViewHolder extends RecyclerView.ViewHolder {

        /**
         * DataBinding이 true로 되어 있을 때
         * book_item_view.xml layout 파일을 생성하면 자동으로 선언되는 클래스
         * 
         * DataBinding을 선언하면
         * layout.xml에 선언된 Component를 일일이 한개씩 세팅하지 않아도
         * binding 객체 한개만 세팅하면 나머지는 자동으로 같이 세팅이 된다
         */
        public BookItemViewBinding bookItemView;
        // public TextView txt_name; 이런식으로 썼던 걸 안 써도 된다는 말임

        public BookViewHolder(@NonNull BookItemViewBinding bookItemViewBinding) {
            super(bookItemViewBinding.getRoot());

            bookItemView = bookItemViewBinding;
        }

        public void bind(BookDTO bookDTO) {
        }

    }
}
