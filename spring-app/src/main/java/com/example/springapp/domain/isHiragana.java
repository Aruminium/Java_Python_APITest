package com.example.springapp.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class isHiragana {
  final static Pattern p = Pattern.compile("^[\u3040-\u309F]+$");
  public static Boolean Hiragana(final String text) {
    Matcher m = p.matcher(text);
    return m.find();
  }
}
