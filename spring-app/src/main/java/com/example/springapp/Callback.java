package com.example.springapp;

import com.example.springapp.replier.Intent;
import com.example.springapp.replier.PostImageReply;
import com.example.springapp.replier.PostMessageReply;
import com.example.springapp.replier.PostSiritoriReply;
import com.example.springapp.replier.noHiraganaError;
import com.example.springapp.replier.postAcceptedReplies;
import com.example.springapp.Service.postService;
import com.example.springapp.domain.isHiragana;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springapp.replier.Error;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@LineMessageHandler
public class Callback {
    private final postService postService;
    private Boolean isNLP;
    private Boolean SiritoriMode;
    @Autowired
    public Callback(postService postService){
        this.postService = postService;
        isNLP = false;
        SiritoriMode = false;
    }

    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) throws IOException {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
            if(isNLP){
                isNLP=false;
                PostMessageReply postMessageReply = postService.doReplyOfNLPResult(event);
                return postMessageReply.reply();
            }
            if(SiritoriMode) {
                if (!isHiragana.Hiragana(text)){
                    return new noHiraganaError().reply();
                }
                SiritoriMode = false;
                PostSiritoriReply postSiritoriReply = postService.doReplyOfSiritoriResult(event);
                SiritoriMode = postService.getCol();
                return postSiritoriReply.reply();
            }
            switch (Intent.whichIntent(text)){
                case NLP:
                    isNLP = true;
                    return new postAcceptedReplies().reply();
                case SIRITORI:
                    SiritoriMode = true;
                    PostSiritoriReply postSiritoriReply = postService.doReplyOfSiritoriResult(event);
                    SiritoriMode = postService.getCol();
                    return postSiritoriReply.reply();
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
