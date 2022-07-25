package com.example.linebot;

import com.example.linebot.replier.Intent;
import com.example.linebot.replier.PostImageReply;
import com.example.linebot.replier.PostMessageReply;
import com.example.linebot.Service.postService;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.linebot.replier.Error;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@LineMessageHandler
public class Callback {
    private final postService postService;

    @Autowired
    public Callback(postService postService){
        this.postService = postService;
    }
    
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) throws IOException {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
            switch (Intent.whichIntent(text)){
                case POST_MESSAGE:
                    PostMessageReply postMessageReply = postService.doReplyOfNLPResult(event);
                    return postMessageReply.reply();

                case UNKNOWN:
                default:
            }
        return new Error().reply();
    }

    //追加する 画像が送信されたとき実行される
    @EventMapping
    public Message handleImage(MessageEvent<ImageMessageContent> event) throws IOException, ExecutionException, InterruptedException {
        PostImageReply postImageReply = postService.doReplyOfImgResult(event);
        return postImageReply.reply();
    }
}
