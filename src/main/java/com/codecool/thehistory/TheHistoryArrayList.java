package com.codecool.thehistory;

import java.util.*;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        wordsArrayList.addAll(Arrays.asList(text.split("\\s")));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        wordsArrayList.removeIf(wordToBeRemoved::equals);
    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        Collections.replaceAll(wordsArrayList, from, to);
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        int correct;
        List<String> newArray = new ArrayList<String>();
        for (int i=0; i < wordsArrayList.size(); i++) {
            if (wordsArrayList.size() - i < fromWords.length) {
                for (int j=i; j < wordsArrayList.size(); j++) {
                    newArray.add(wordsArrayList.get(j));
                }
                break;
            }
            correct = 0;
            for (int j=0; j < fromWords.length; j++) {
                if (wordsArrayList.get(i+j).equals(fromWords[j])) {
                    correct++;
                } else {
                    break;
                }
            }
            if (correct == fromWords.length) {
                for (int j=0; j < toWords.length; j++) {
                    newArray.add(toWords[j]);
                }
                i = i + fromWords.length - 1;
            } else {
                newArray.add(wordsArrayList.get(i));
            }
        }
        wordsArrayList = newArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
