package com.codecool.thehistory;

import java.io.IOException;
import java.util.*;
import java.util.Iterator;
import java.util.ListIterator;

public class TheHistoryLinkedList implements TheHistory {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        wordsLinkedList.addAll(Arrays.asList(text.split("\\s")));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        wordsLinkedList.removeIf(wordToBeRemoved::equals);
    }

    @Override
    public int size() {
        return wordsLinkedList.size();
    }

    @Override
    public void clear() {
        wordsLinkedList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        Collections.replaceAll(wordsLinkedList, from, to);
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {

        int correct;
        String word;
        ListIterator<String> it = wordsLinkedList.listIterator();
        while (it.hasNext()) {
            word = it.next();
            correct = 0;
            try {
                for (int j = 0; j < fromWords.length; j++) {
                    if (j != 0) {
                        word = it.next();
                    }
                    if (word.equals(fromWords[j])) {
                        correct++;
                    } else {
                        break;
                    }
                }
            } catch(Exception e) {
                break;
            }
            if (correct == fromWords.length) {
                for (int j=0; j < fromWords.length; j++) {
                    it.previous();
                    it.next();
                    it.remove();
                }
                for (int j=0; j < toWords.length; j++) {
                    it.add(toWords[j]);
                }
            } else {
                for (int j=0; j < correct; j++) {
                    it.previous();
                }
            }
        }



        /*List<String> newArray = new ArrayList<String>();
        for (int i=0; i < wordsLinkedList.size(); i++) {
            if (wordsLinkedList.size() - i < fromWords.length) {
                for (int j=i; j < wordsLinkedList.size(); j++) {
                    newArray.add(wordsLinkedList.get(j));
                }
                break;
            }
            correct = 0;
            for (int j=0; j < fromWords.length; j++) {
                if (wordsLinkedList.get(i+j).equals(fromWords[j])) {
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
                newArray.add(wordsLinkedList.get(i));
            }
        }
        wordsLinkedList = newArray;*/
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsLinkedList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
