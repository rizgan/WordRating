package com.rizgan;

/**
 * Created by rizgan on 28.01.2016.
 */
public class WordPrint implements Comparable<WordPrint> {

    Integer repeat = 0;
    String word = "";

    public Integer getRepeat() {
        return repeat;
    }

    public String getWord() {
        return word;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordPrint(Integer i, String s) {
        repeat = i;
        word = s;
    }

    @Override
    public int compareTo(WordPrint o) {
        return (this.getRepeat()).compareTo(o.getRepeat());
    }
}
