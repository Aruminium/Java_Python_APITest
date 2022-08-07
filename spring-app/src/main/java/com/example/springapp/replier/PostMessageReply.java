package com.example.springapp.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class PostMessageReply implements Replier{
    private final String text;

    public PostMessageReply(final String text) {
        this.text = text;
    }

    @Override
    public Message reply() {
        return new TextMessage("レスポンス: "+text);
    }
}
