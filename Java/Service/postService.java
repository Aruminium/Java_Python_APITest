package com.example.linebot.service;

import com.example.linebot.domain.IPost;
import com.example.linebot.domain.imgEncoder;
import com.example.linebot.domain.postImage;
import com.example.linebot.domain.postMessage;
import com.example.linebot.replier.PostImageReply;
import com.example.linebot.replier.PostMessageReply;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.linecorp.bot.client.LineBlobClient;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class postService {
    private final LineBlobClient client;

    @Autowired
    postService(final LineBlobClient client){
        this.client = client;
    }

    public PostMessageReply doReplyOfNLPResult(final MessageEvent<TextMessageContent> event) throws JsonProcessingException {
        final TextMessageContent tmc = event.getMessage();
        final String text = tmc.getText();
        final IPost iPost = new postMessage(text);
        return new PostMessageReply(iPost.Post());
    }

    public PostImageReply doReplyOfImgResult(final MessageEvent<ImageMessageContent> event) throws IOException, ExecutionException, InterruptedException {
        final String id = event.getMessage().getId();
        final imgEncoder imgEncoder = new imgEncoder(id, client);
        final String base64 = imgEncoder.imgEncode();
        final IPost iPost = new postImage(base64);
        return new PostImageReply(iPost.Post());
    }
}
