package com.example.linebot.domain;

import com.linecorp.bot.client.LineBlobClient;
import com.linecorp.bot.client.MessageContentResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

public class imgEncoder {
    private final LineBlobClient client;
    private final String id;

    public imgEncoder(final String id, final LineBlobClient client) {
        this.client = client;
        this.id = id;
    }

    public String imgEncode() throws ExecutionException, InterruptedException, IOException {
        final MessageContentResponse response  = client.getMessageContent(id).get();
        final InputStream stream = response.getStream();
        final byte[] bytes = stream.readAllBytes();
        final String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }

}
