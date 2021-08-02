package com.callor.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView myImage = null;
    private int count = 0;
    private int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImage = findViewById(R.id.my_image);
        images = new int[3];
        images[0] = R.drawable.my4;
        images[1] = R.drawable.my6;
        images[2] = R.drawable.my7;

        // ImageView를 클릭했을 때
        myImage.setOnClickListener(view -> {
            // count 변수를 1씩 증가하고
            count++;
            // 증가된 count 변수를 숫자 3으로 나머지 연산 수행
            // 0,1,2 중의 1개의 값이 된다
            int imgNum = count % 3;

            // 3개의 배열에 담긴 정보중에 imgNum요소의 정보를 ImageView에 세팅하기
            myImage.setImageResource(images[imgNum]);
        });
    }
}