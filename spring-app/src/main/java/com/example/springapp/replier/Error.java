package com.example.springapp.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class Error implements Replier{
    private final static String ERROR = "「しりとり」と入力すると、しりとりができます\n「NLP」と入力すると,NLPができます";

    @Override
    public Message reply() {
        return new TextMessage(ERROR);
    }
}
