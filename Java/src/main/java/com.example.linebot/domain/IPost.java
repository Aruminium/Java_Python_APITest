package com.example.linebot.domain;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPost {
    /**
     * Post通信を行う
     * @return POSTレスポンス
     */
    String Post() throws JsonProcessingException;
}
