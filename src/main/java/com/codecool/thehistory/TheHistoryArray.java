package com.codecool.thehistory;

import java.util.Arrays;
import java.util.*;

public class TheHistoryArray implements TheHistory {
    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        wordsArray = text.split("\\s+");
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        int count = 0;
        for (int i=0; i < wordsArray.length; i++) {
            if (wordsArray[i] == wordToBeRemoved) {
                count++;
            }
        }
        String[] newArray = new String[wordsArray.length - count];
        int newIndex = 0;
        for (int i=0; i < wordsArray.length; i++) {
            if (wordsArray[i] != wordToBeRemoved) {
                newArray[newIndex] = wordsArray[i];
                newIndex++;
            }
        }
        wordsArray = newArray;
    }

    @Override
    public int size() {
        return wordsArray.length;
    }

    @Override
    public void clear() {
        wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (int i=0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(from)) {
                wordsArray[i] = to;
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        List<Integer> positions = new ArrayList<Integer>();
        long start;

        int ok;
        for (int i=0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(fromWords[0]) ) {
                if (fromWords.length > 1) {
                    ok = 1;
                    try {
                        for (int j = 1; j < fromWords.length; j++) {
                           if (wordsArray[i + j].equals(fromWords[j])) {
                               ok++;
                           } else {
                               break;
                           }
                        }
                        if (ok == fromWords.length) {
                            positions.add(i);
                            i = i + fromWords.length - 1;
                        }
                    } catch(Exception e) {

                    }
                } else {
                    positions.add(i);
                }
            }
        }
        start = System.currentTimeMillis();
        String[] tempWordsArray = new String[wordsArray.length + positions.size() * (toWords.length - fromWords.length)];
        int indexForTempArray = 0;
        for (int i=0; i < wordsArray.length; i++) {
            if (positions.contains(i)) {
                for (int j=0; j < toWords.length; j++) {
                    tempWordsArray[indexForTempArray] = toWords[j];
                    indexForTempArray++;
                }
                i = i + fromWords.length - 1;
            } else {
                tempWordsArray[indexForTempArray] = wordsArray[i];
                indexForTempArray++;
            }
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
        wordsArray = tempWordsArray;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }
}
