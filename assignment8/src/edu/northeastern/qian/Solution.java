package edu.northeastern.qian;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    // =============================================== Question 1 ========================================================

    class Solution1{
        class Trie {
            TrieNode root;

            /** Initialize data structure.*/
            public Trie() {
                root = new TrieNode(' ');
            }

            /**
             * Time Complexity: o(n), where n is the length of "word"
             * Space Complexity: o(n), where n is the length of "word"
             *
             * Inserts a word into the trie
             */
            public void insert(String word) {
                TrieNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);

                    if (cur.children[c - 'a'] == null) {
                        cur.children[c - 'a'] = new TrieNode(c);
                    }
                    // If the children[c - 'a'] of 'cur' already exists here, update 'cur'
                    cur = cur.children[c - 'a'];
                }
                cur.isWord = true;
            }

            /**
             * Time Complexity: o(n), where n is the length of "word"
             * Space Complexity: o(1)
             *
             * Returns if the word is in the trie.
             */
            public boolean search(String word) {
                TrieNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    // if one of the “word” is not matched in the trie, indicates that the word is not in the trie
                    if (cur.children[c - 'a'] == null) {
                        return false;
                    }
                    cur = cur.children[c - 'a'];
                }
                return cur.isWord;
            }

            /**
             * Time Complexity: o(n),  where n is the length of "prefix"
             * Space Complexity: o(1)
             *
             * Returns if there is any word in the trie that starts with the given prefix.
             */
            public boolean startsWith(String prefix) {
                TrieNode cur = root;
                for (int i = 0; i < prefix.length(); i++) {
                    char c = prefix.charAt(i);
                    if (cur.children[c - 'a'] == null) {
                        return false;
                    }
                    cur = cur.children[c - 'a'];
                }
                // each char of the "prefix" is find,
                //      means there is a word in the trie that starts with the given prefix.
                return true;
            }
        }

        class TrieNode {
            char c;
            boolean isWord;
            TrieNode[] children;

            public TrieNode (char c) {
                this.c = c;
                isWord = false;
                children = new TrieNode[26];
            }
        }
    }


    // =============================================== Question 2 ========================================================

    /**
     * Time Complexity: O(E * V)
     * Space Complexity: O(V)
     */
    class Solution2 {
        public boolean validPath(int n, int[][] edges, int start, int end) {
            // Connect all nodes to see if there is a valid path from start to end
            Union uni = new Union(n);
            for (int i = 0; i < edges.length; i++) {
                uni.union(edges[i][0], edges[i][1]);
            }
            return uni.isConnected(start, end);
        }
    }

    class Union {
        int n;
        int[] father;

        /**
         * Time Complexity: o(n)
         * Space Complexity: o(n)
         */
        public Union (int n) {
            this.n = n;
            father = new int[n];
            // The father of all nodes at the beginning is itself
            for (int i = 0; i < father.length; i++) {
                father[i] = i;
            }
        }

        /**
         * Time Complexity: o(n)
         * Space Complexity: o(1)
         */
        private int root (int a) {// find the root of a
            int b = father[a];
            if (b != a) {
                return root(b);
            }
            return b;
        }

        /**
         * Time Complexity: o(n)
         * Space Complexity: o(1)
         */
        public void union (int a, int b) {// Connect the root of two nodes
            int c = root(a);
            int d = root(b);
            if (c != d) {
                father[c] = d;
            }
        }

        /**
         * Time Complexity: o(n)
         * Space Complexity: o(1)
         */
        public boolean isConnected (int a, int b) {// Check if the two nodes are in the same set
            return root(a) == root(b);
        }
    }


    //=============================================== Question 3 ========================================================

    /**
     * Time Complexity: o(m *  n), where m is the number of rows and n is the number of columns.
     * Space Complexity: o(m * n) as required by UnionFind data structure.
     */
    class Solution3 {
        public int numIslands(char[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            UnionFind uni = new UnionFind(n * m);
            int waterCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1') {//Found the beginning of the island
                        if (j + 1 < m && grid[i][j + 1] == '1') {// right forward
                            // Connect the nodes and expression formula:
                                        // horizontal ordinate * grid[0].length + vertical ordinate
                            uni.union(i * m + j, i * m + (j + 1));
                        }
                        if (i + 1 < n && grid[i + 1][j] == '1') {
                            uni.union(i * m + j, (i + 1) * m + j);// down
                        }
                    }else{
                        waterCnt++;
                    }
                }
            }
            return uni.getCnt() - waterCnt;
        }
    }

    class UnionFind {
        int[] father;
        int cnt;
        public UnionFind (int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
            this.cnt = n;// There are as many cnts as there are cells
        }

        private int root (int a) {
            int b = father[a];
            if (b != a) {
                return root(b);
            }
            return b;
        }

        public void union (int a, int b) {
            int c = root(a);
            int d = root(b);
            if (c != d) {
                father[c] = d;
                cnt--;
            }
        }

        public int getCnt () {
            return cnt;
        }
    }


    //=============================================== Question 4 ========================================================

    /**
     * Time Complexity: o(n^2), the matrix of size n^2 is traversed
     * Space Complexity: o(n), visited array of size n is used
     */
    class Solution4 {
        public int findCircleNum(int[][] isConnected) {
            int cnt = 0;
            int[] visited = new int[isConnected.length];
            for (int i = 0; i < isConnected.length; i++) {// the range of cities
                if (visited[i] == 0) {
                    visited[i] = 1;
                    dfs (isConnected, i, visited);// Find out all the cities connected to city i
                    cnt++;
                }
            }
            return cnt;
        }

        private void dfs (int[][] isConnected, int i, int[] visited) {
            for (int j = 0; j < isConnected.length; j++) {// Potential city range connected to city i
                // City i is connected to city j and city j is not visited
                if (isConnected[i][j] == 1 && visited[j] == 0) {
                    visited[j] = 1;
                    dfs (isConnected, j, visited);
                }
            }
        }
    }


    //=============================================== Question 5 ========================================================

    /**
     * Time Complexity: O(E + V), where E is number of edges, V is number of vertices
     * Space Complexity: O(E + V), where E is number of edges, V is number of vertices
     */
    class Solution5 {
        List<Integer>[] canBeConnected;

        public int countComponents(int n, int[][] edges) {
            //Connect edges: connect the node that nodes can be connected to it in turn
            canBeConnected = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                canBeConnected[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                canBeConnected[edge[0]].add(edge[1]);
                canBeConnected[edge[1]].add(edge[0]);
            }
            // count the number of connected components
            int[] visited = new int[n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    cnt++;
                    dfs (i, visited);
                }
            }
            return cnt;
        }

        private void dfs (int i, int[] visited) {
            for (int j : canBeConnected[i]) {
                if (visited[j] == 0) {
                    visited[j] = 1;
                    dfs (j, visited);
                }
            }
        }
    }


    //=============================================== Question 6 ========================================================

    /**
     * Time complexity: o(n), where n is the number of grid's cells
     * Space complexity: o(n),where n is the number of grid's cells
     */
    class Solution6 {
        private int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, 1}, {1, -1}, {-1, -1}, {1, 1}};

        public int shortestPathBinaryMatrix(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            // If the value in the top-left cell is 1, we cannot reach the bottom-right cell
            if (grid[0][0] == 1) {
                return -1;
            }
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] {0,0});
            grid[0][0] = 1;
            // First level's path length
            int cnt = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = q.remove();
                    // we can return the shortest path length when we reach the end
                    if (cur[0] == m - 1 && cur[1] == n - 1) {
                        return cnt;
                    }
                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0];
                        int y = cur[1] + dir[1];
                        if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 0) {
                            q.add(new int[] {x, y});
                            grid[x][y] = 1;
                        }
                    }
                }
                cnt++;
            }
            return -1;
        }
    }


    //=============================================== Question 7 ========================================================

    /**
     * Time complexity: o(n), where n is the number of grid's cells
     * Space complexity: o(n),where n is the number of grid's cells
     */
    class Solution7 {
        private int[][] dirs = {{-1, 0}, {1 , 0}, {0, -1}, {0, 1}};

        public int orangesRotting(int[][] grid) {
            // BFS
            int m = grid.length;
            int n = grid[0].length;
            Queue<int[]> q = new LinkedList<>();
            // Put all the rotten oranges at 0 minute into q
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 2) {
                        q.add(new int[]{i, j});
                    }
                }
            }
            // Calculate the time when there are rotten oranges at 0 minute and
            // these rotten oranges will rot all fresh oranges
            int cnt = -1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = q.remove();
                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0];
                        int y = cur[1] + dir[1];
                        if ( x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1) {
                            grid[x][y] = 2;
                            q.add(new int[]{x, y});
                        }
                    }

                }
                cnt++;
            }
            // Check if there are fresh oranges that are not in the range where rotten oranges can rot
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        return -1;
                    }
                }
            }
            // If cnt equals to -1, means that there is no rotten orange at 0 minute.
            //if cnt not equal to -1, which means that there is rotten orange at 0 minute,
            // then we calculate the rotten time
            return cnt == -1? 0 : cnt;
        }
    }
}
