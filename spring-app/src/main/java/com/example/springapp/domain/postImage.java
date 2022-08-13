package com.example.springapp.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class postImage implements IPost{

    private final HttpHeaders headers = new HttpHeaders();
    /**
     * URLは主に http://(スキーマ)+localhost:5000(ホスト)+/test(パス) のスキーマ+ホスト+パスで構成されている
     * パスの部分を@app.route("/image", methods=['POST'])の"/image"と同じにする
     */
    private final static String URL = "http://flask:5000/image";

    /**
     * KEY 連想配列のkey
     * KEY = "text" とした場合
     * pythonでのres = req["image"]の処理を
     * res = req["text"]にする
     */
    private final static String KEY = "image";
    private final Map<String, String> map = new HashMap<>();

    public postImage(final String base64) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        map.put(KEY, base64);
    }

    @Override
    public ResponseEntity<String> Post() throws JsonProcessingException {
        final RestTemplate restTemplate = new RestTemplate();
        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(map);
        final HttpEntity<String> request = new HttpEntity<>(json, headers);
        final ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);

        // final String res = restTemplate.postForObject(
        //         URL, request, String.class);
        return response;
    }


}
