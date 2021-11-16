package edu.northeastern.qian;
import java.util.*;

public class Solution {

    //==============================================question 1=============================================

    //Time complexity: O(n * 3^m) where n is the number of cells in the board and m is the length of the word to be matched.
    // Space complexity: O(m) where m is the length of the word to be matched.

    class question1 {
        String word;
        public boolean exist(char[][] board, String word) {
            this.word = word;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (backtracking (board, i , j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean backtracking (char[][] board, int i , int j, int index) {
            if (word.length() == index) {
                return true;
            }
            //  Check the boundaries
            if (i < 0 || j < 0 || i >= board.length|| j >= board[0].length) {
                return false;
            }
            if (board[i][j] != word.charAt(index)) {
                return false;
            }
            char temp = board[i][j];
            // mark the grid as 'X' to say it is visited.
            board[i][j] = 'X';
            if (backtracking (board, i - 1, j, index + 1) || backtracking (board, i + 1, j, index + 1) ||
                    backtracking (board, i, j - 1, index + 1) || backtracking (board, i, j + 1, index + 1)) {
                return true;
            }
            board[i][j] = temp;
            return false;
        }
    }


    //==============================================question 2=============================================

    // Time complexity: O(m), where m refers to the number of valid permutations
    // Space complexity: O(n). visited array of size n is used

    class question2 {
        int cnt = 0;
        public int countArrangement(int n) {
            boolean[] visited = new boolean[n + 1];
            dfs(n, visited, 1);
            return cnt;
        }

        private void dfs(int n, boolean[] visited, int index) {
            if (index > n) {
                cnt++;
            }
            for (int i = 1; i <= n; i++) {
                // find the number i that can "match" index
                if (!visited[i] && (i % index == 0 || index % i == 0)) {
                    visited[i] = true;
                    dfs(n, visited, index + 1);
                    visited[i] = false;
                }
            }
        }
    }


    //==============================================question 3=============================================

    // time complexity: O(1)
    // space complexity: O(1)

    static class Question3 {
        int n;
        String s;
        LinkedList<String> segments = new LinkedList<>();
        ArrayList<String> res = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            n = s.length();
            this.s = s;
            dfs(0, 1);
            return res;
        }

        /**
         * start from the startInd in string, check the nth segment
         * @param startInd
         * @param nthSegment
         */
        public void dfs(int startInd, int nthSegment) {
            for (int endInd = startInd; endInd < n && endInd < startInd + 3; endInd++) {
                String segment = s.substring(startInd, endInd+1);
                if (valid(segment)) {
                    segments.add(segment);
                    if (nthSegment == 3) {
                        String finalSegment = s.substring(endInd + 1, n);
                        if (valid(finalSegment)) {
                            segments.add(finalSegment);
                            res.add(String.join(".", segments));
                            segments.removeLast();
                        }
                    } else {
                        dfs(endInd+1, nthSegment + 1);
                    }
                    // backtracking
                    segments.removeLast();
                }
            }
        }

        /**
         * check if this is a valid segment
         * @param segment
         * @return
         */
        public boolean valid(String segment) {
            if (segment.length() > 3 || segment.length() == 0)
                return false;
            return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (segment.length() == 1);
        }
    }


    //==============================================question 4=============================================

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word;
        public TrieNode() {
            this.word = null;
        }
    }

    // time complexity: O(n * 4^L) where n is the number of cells in the board, L is the maximum length of a word.
    // spade complexity: O(m) where m is the total number of letters in words.

    class Question4 {
        char[][] board = null;
        ArrayList<String> res = new ArrayList<String>();
        int[][] dirs = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };

        private TrieNode buildTrie(String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode cur = root;
                for (char ch : word.toCharArray()) {
                    cur.children.putIfAbsent(ch, new TrieNode());
                    cur = cur.children.get(ch);
                }
                cur.word = word;  // store word in the ending TrieNode
            }
            return root;
        }

        public List<String> findWords(char[][] board, String[] words) {
            this.board = board;
            // Build the trie
            TrieNode root = buildTrie(words);
            // Run dfs starting from each cell in the board
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    dfs(i, j, root);
                }
            }
            return res;
        }

        private void dfs(int i, int j, TrieNode cur) {
            Character ch = board[i][j];
            TrieNode childNode = cur.children.get(ch);
            if (childNode == null) {
                return;
            }

            // check if there is a word
            if (childNode.word != null) {
                res.add(childNode.word);
                childNode.word = null; // remove this word from Trie since we already find it
            }
            // mark the current char in the board as "visited"
            board[i][j] = '#';
            // visit 4 neighbors
            for (int[] dir : dirs) {
                int newI = i + dir[0];
                int newJ = j + dir[1];
                if (newI < 0 || newI >= board.length || newJ < 0 || newJ >= board[0].length) {
                    continue;
                }
                dfs(newI, newJ, childNode);
            }
            // backtracking
            board[i][j] = ch;
            if (childNode.children.isEmpty()) {
                cur.children.remove(ch);
            }
        }
    }


    //==============================================question 5=============================================

    // time complexity: O(2^n) where n is the length of arr
    // space complexity: O(2^(min(n, k))) where k is the number of distinct characters in arr

    class question5 {
        public int maxLength(List<String> arr) {
            return dfs(arr, 0, new HashMap<>());
        }

        private boolean mapHasDuplicateChars(Map<Character, Integer> resMap) {
            for (int cnt : resMap.values()) {
                if (cnt > 1) {
                    return true;
                }
            }
            return false;
        }

        private void removeWordCharsFromMap(Map<Character, Integer> resMap, char[] chars) {
            for (char ch : chars) {
                if (resMap.get(ch) == 1) {
                    resMap.remove(ch);
                } else {
                    resMap.put(ch, resMap.get(ch) - 1);
                }
            }
        }

        private int dfs(List<String> arr, int pos, Map<Character, Integer> resMap) {
            // Check for duplicate characters
            int maxLen = resMap.size();
            for (int i = pos; i < arr.size(); i++) {
                char[] chars = arr.get(i).toCharArray();
                // add the word chars into the map
                for (char ch : chars) {
                    resMap.put(ch, resMap.getOrDefault(ch, 0) + 1);
                }
                // only if map does not have duplicate chars, proceed to the next position
                if (!mapHasDuplicateChars(resMap)) {
                    maxLen = Math.max(maxLen, dfs(arr, i + 1, resMap));
                }
                // backtracking
                removeWordCharsFromMap(resMap, chars);
            }
            return maxLen;
        }
    }
}
