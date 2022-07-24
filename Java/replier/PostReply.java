package com.example.linebot.replier;

import com.example.linebot.value.PostType;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class PostReply implements Replier{
    private final String text;

    public PostReply(final String text) {
        this.text = text;
    }

    @Override
    public Message reply() {
        return new TextMessage("レスポンス: "+text);
    }
}
