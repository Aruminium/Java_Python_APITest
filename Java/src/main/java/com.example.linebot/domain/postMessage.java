package com.example.linebot.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class postMessage implements IPost{
    private final HttpHeaders headers = new HttpHeaders();
    /**
     * URLは http://localhost:5000(ホスト) + /test(パス) のホスト+パスで構成されている
     * パスの部分を@app.route("/message", methods=['POST'])の"/message"と同じにする
     */
    private final static String URL = "http://localhost:5000/message";
    /**
     * KEY 連想配列のkey
     * KEY = "text" とした場合
     * pythonでのres = req["message"]の処理を
     * res = req["text"]にする
     */
    private final static String KEY = "message";
    private final Map<String, String> map = new HashMap<>();

    public postMessage(final String message) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        map.put(KEY, message);
    }

    @Override
    public String Post() throws JsonProcessingException {
        final RestTemplate restTemplate = new RestTemplate();
        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(map);
        final HttpEntity<String> request = new HttpEntity<>(json, headers);
        final String res = restTemplate.postForObject(
                URL, request, String.class);
        return res;
    }
}
