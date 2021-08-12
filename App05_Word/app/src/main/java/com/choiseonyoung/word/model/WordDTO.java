package com.choiseonyoung.word.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * 이 클래스는 DTO 역할을 수행하면서
 * table을 만들기 위한 구조화 model이다
 */
// 지금부터 wordDTO를 사용해서 tbl_word라는 테이블을 만들어라
@Entity(tableName = "tbl_word")
public class WordDTO {
    
    @PrimaryKey // 이 값은 primarykey다
    @NonNull // android. 이 값은 null값이 올 수 없다
    @ColumnInfo(name="word")
    String word; // 영어단어

    @ColumnInfo(name="korea")
    private String korea; // 한글 뜻

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    public String getKorea() {
        return korea;
    }

    public void setKorea(String korea) {
        this.korea = korea;
    }

    @Override
    public String toString() {
        return "WordDTO{" +
                "word='" + word + '\'' +
                ", korea='" + korea + '\'' +
                '}';
    }
}
