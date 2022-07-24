package com.example.linebot.service;

import com.example.linebot.domain.IPost;
import com.example.linebot.domain.postMessage;
import com.example.linebot.replier.PostReply;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.stereotype.Service;

@Service
public class postService {
    public PostReply doReplyOfResult(final MessageEvent<TextMessageContent> event) throws JsonProcessingException {
        final TextMessageContent tmc = event.getMessage();
        final String text = tmc.getText();
        final IPost iPost = new postMessage(text);
        return new PostReply(iPost.Post());
    }
}
