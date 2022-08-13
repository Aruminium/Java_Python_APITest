package com.example.springapp.domain;

import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPost {
    /**
     * Post通信を行う
     * @return POSTレスポンス
     */
    ResponseEntity<String> Post() throws JsonProcessingException;
}
