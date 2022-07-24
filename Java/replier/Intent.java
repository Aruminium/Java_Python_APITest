package com.example.linebot.replier;

import java.util.EnumSet;
import java.util.regex.Pattern;

public enum Intent {
    REMINDER("^(\\d{1,2}):(\\d{1,2})に(.{1,32})$"),
    //追加する
    POSTMESSAGE("^post .+"),
    UNKNOWN(".+");

    private final String regexp;

    private Intent(String regexp){
        this.regexp = regexp;
    }
    public static Intent whichIntent(String text){
        EnumSet<Intent> set = EnumSet.allOf(Intent.class);
        for (Intent intent: set){
            if(Pattern.matches(intent.regexp, text)){
                return intent;
            }
        }
        return  UNKNOWN;
    }

    public String getRegexp(){
        return regexp;
    }
}
