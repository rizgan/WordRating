package com.rizgan;

import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<WordPrint> wordPrints = new ArrayList<>();

//        Document doc = Jsoup.connect("http://ocw.mit.edu/ans7870/6/6.006/s08/lecturenotes/files/t8.shakespeare.txt").userAgent("Mozilla").cookie("auth", "token").timeout(3000).get();
        Document doc = Jsoup.connect("http://www.bbc.com/arabic").userAgent("Mozilla").cookie("auth", "token").timeout(3000).get();

        String cleanString = doc.text().replaceAll(",", "").replaceAll("'", "").replaceAll("\"", "").replaceAll("-", "").replaceAll("_", "").replaceAll("=", "").replaceAll("\\d+", "").replaceAll("\\:", "").replaceAll("\\?", "").replace(".", "").replace(";", "").replace("!", "").replace("(", "").replace(")", "").replace("[", "").replace("]", "").replaceAll("\\*", "").replaceAll("#", "").replaceAll("~", "").replaceAll("°", "").replaceAll("&", "").replaceAll("$", "").replaceAll("‘", "").toLowerCase();
        String[] s = cleanString.split(" ");

        int howManyWordsToKnow = 200;
        double countOfWordsToUnderstand = 0;
        int howManyAdded = 0;
        int firstTimeAdded = 1;
        ArrayList<Integer> wordCounters = new ArrayList<>();
        ArrayList<String> wordValues = new ArrayList<>();

        for (int i = 0; i < s.length; i++) {
            if (!wordValues.contains(s[i])) {
                wordValues.add(s[i]);
                wordCounters.add(howManyAdded, firstTimeAdded);
                howManyAdded++;

            } else if (wordValues.contains(s[i])) {
                int intInWordCounter = ArrayUtils.indexOf(wordValues.toArray(), s[i]);
                int oldValueInt = wordCounters.get(intInWordCounter);
                oldValueInt = oldValueInt + 1;
                wordCounters.set(intInWordCounter, oldValueInt);
            }
        }

        int maximumPopular = Collections.max(wordCounters);
        int getIndexOfPopular = ArrayUtils.indexOf(wordCounters.toArray(), maximumPopular);

        for (int i = 0; i < wordCounters.size(); i++) {
            WordPrint wordPrint = new WordPrint(wordCounters.get(i), wordValues.get(i));
            wordPrints.add(wordPrint);
        }

        Collections.sort(wordPrints);
        Collections.reverse(wordPrints);

        for (WordPrint w : wordPrints) {
            System.out.println(w.word + " " + w.repeat);
        }

        System.out.println("\nКоличество уникальных слов: " + wordValues.size() + "\n" +
                "Количество всех слов в тексте: " + s.length);
        for (int i = 0; i < howManyWordsToKnow; i++) {
            countOfWordsToUnderstand += wordPrints.get(i).repeat;
        }
        System.out.println("\nЕсли вы будете знать " + howManyWordsToKnow + " слов, то будете понимать " + Math.round((countOfWordsToUnderstand / s.length) * 100) + "% текста.");
    }
}