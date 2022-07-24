package com.example.linebot;

import com.example.linebot.replier.*;
import com.example.linebot.service.affectiveAnalysis;
import com.example.linebot.service.postService;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;
import com.example.linebot.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@LineMessageHandler
public class Callback {
    private final ReminderService reminderService;
    private final postService postService;

    @Autowired
    public Callback(ReminderService reminderService, postService postService){
        this.reminderService = reminderService;
        //追加する
        this.postService = postService;
    }

    private static final Logger log =
            LoggerFactory.getLogger(Callback.class);

    @EventMapping
    public Message handleFollow(FollowEvent event) {
        Follow follow = new Follow(event);
        return follow.reply();
    }

    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) throws IOException {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
            switch (Intent.whichIntent(text)){
                case REMINDER:
                    RemindOn remindOn = reminderService.doReplyOfNewItem(event);
                    return remindOn.reply();

                //追加する
                case POSTMESSAGE:
                    PostReply postReply = postService.doReplyOfResult(event);
                    return postReply.reply();

                case UNKNOWN:
                default:
                    Parrot parrot = new Parrot(event);
                    return parrot.reply();
            }
    }
}
