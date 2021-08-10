package com.choiseonyoung.library.service;

// Ctrl + Alt + O : import 정리 (이클립스에서는 컨트롤+쉬프트+O 였음)
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.choiseonyoung.library.adapter.BookAdapter;
import com.choiseonyoung.library.config.Naver;
import com.choiseonyoung.library.databinding.FragmentFirstBinding;
import com.choiseonyoung.library.model.BookDTO;
import com.choiseonyoung.library.model.NaverParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverAPIServiceV1 {

    private BookAdapter bookAdapter = null;
    FragmentFirstBinding binding = null;

    public NaverAPIServiceV1(BookAdapter bookAdapter, FragmentFirstBinding binding) {
        this.bookAdapter = bookAdapter;
        this.binding = binding;
    }

    // 3. 생성된 Connection을 통하여 데이터를 가져오고
    // 필요한 데이터를 parsing하여 books 객체에 담기
    public void getNaverBooks(String search) {

        // 아래의 코드를 실행하면 Retrofit 설정된 값을 기준으로 naver에 요청을 수행한다.
        // 이때 이 코드는 비동기 방식으로 작동이 된다.
        Call<NaverParent> naverCall = RetrofitClient.getClient().getNaverBook(Naver.CLIENT_ID,Naver.CLIENT_SECRET, search, 50, 1);
        /**
         * Retrofit은 API 요청을 비동기 방식으로 수행을 한다
         * 일반적으로 Network이나 외부 다른 장치와 데이터를 주고 받을 때는
         * 대부분의 코드를 비동기, 또는 thread 방식으로 사용한다
         * (우리 아직 thread 안 배웠는데 어려운 방식임. 현재 사용되고 있는 코드 외에 다른 프로세스?를 하나 분리해서 쓰는 방식)
         *
         * 동기방식
         * 요청수행 ===> 결과가 return 되어 올 때까지 대기
         *
         * 비동기방식
         * 요청수행 ===> 결과가 return 되든 말든 다른일 수행하기
         * 결과가 return되면 그 결과를 수신하여 처리할 event handler를 작성해야 한다.
         */
        // Retrofit event 핸들러 작성
        naverCall.enqueue(new Callback<NaverParent>() {

            private NaverParent naverParent;

            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
            //데이터가 수신되면 작동되는 메서드

                // return된 response 객체 확인
                Log.d("Naver Res Return",response.toString());
                int resCode = response.code();
                if(resCode < 300) {
                    naverParent = response.body();
                    Log.d("Naver Return",naverParent.toString());

                    List<BookDTO> bookDTOList = naverParent.items;
                    // 그 데이터에서 items를 추출해서 bookDTOList에 담음
                    bookAdapter = new BookAdapter(bookDTOList);

                    binding.bookListView.setAdapter(bookAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(binding.getRoot().getContext());
                    binding.bookListView.setLayoutManager(layoutManager);

                }
                // 300 이하면 정상적인 코드가 왔다는 얘기
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

                Log.d("오류가 발생했습니다",t.toString());
            }
        });
        // 엔큐가 이벤트핸들러의 주메서드임
    }

}