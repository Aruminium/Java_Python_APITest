package com.example.springapp.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class postAcceptedReplies implements Replier{
  private final static String ACCEPTED = "NLPさせたいメッセージを入力してください";

  @Override
  public Message reply() {
      return new TextMessage(ACCEPTED);
  }
}
