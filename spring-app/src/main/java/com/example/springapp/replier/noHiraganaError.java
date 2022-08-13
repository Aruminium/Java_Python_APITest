package com.example.springapp.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class noHiraganaError implements Replier{
  private final static String ERROR = "ひらがなで入力してください";

  @Override
  public Message reply() {
      return new TextMessage(ERROR);
  }
}
