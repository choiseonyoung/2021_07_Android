package com.choiseonyoung.library.service;

import com.choiseonyoung.library.model.NaverBookDTO;

public interface NaverBookService {
    public NaverBookDTO getBooks(String search);
}
