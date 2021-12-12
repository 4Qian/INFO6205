package edu.northeastern.qian.question4;

import java.util.*;

public class Solution {
    Map<Character, Integer> rank;
    public boolean alienOrder(String[] words, String order) {
        rank = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            rank.put(order.charAt(i), i);
        }
        for (int i = 1; i < words.length; i++) {
            if (!isValid(words[i-1], words[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(String word1, String word2) {
        int n = Math.min(word1.length(), word2.length());
        for (int i = 0; i < n; i++) {
            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(i);
            if (ch1 != ch2) {
                if (rank.get(ch1) > rank.get(ch2)) {
                    return false;
                } else if (rank.get(ch1) < rank.get(ch2)) {
                    return true;
                }
            }
        }
        return word1.length() <= word2.length();
    }

    public static void main(String[] args) {
        Solution question4 = new Solution();
        String[] words = new String[] {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(question4.alienOrder(words, order));// true

        String[] words1 = new String[] {"word","world","row"};
        String  order1 = "worldabcefghijkmnpqstuvxyz";
        System.out.println(question4.alienOrder(words1, order1));// flase
    }
}
