package com.example.springapp.replier;

import java.util.EnumSet;
import java.util.regex.Pattern;

public enum Intent {
    SIRITORI("^しりとり$"),
    NLP("^NLP$"),
    UNKNOWN(".+");

    private final String regexp;

    Intent(String regexp){
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
