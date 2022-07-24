package com.example.linebot.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineBlobClient;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class postImage implements IPost{

    private final HttpHeaders headers = new HttpHeaders();
    /**
     * URLは http://localhost:5000(ホスト) + /test(パス) のホスト+パスで構成されている
     * パスの部分を@app.route("/image", methods=['POST'])の"/image"と同じにする
     */
    private final static String URL = "http://localhost:5000/image";

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
