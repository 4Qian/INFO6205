package edu.northeastern.qian.question3;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] arr2 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        int[] arr3 = {};
        int[] arr4 = {0, 1};

        Solution s = new Solution();
        System.out.println(s.jump(arr1));// Expected 10
        System.out.println(s.jump(arr2));// Expected 3
        System.out.println(s.jump(arr3));// Expected -1
        System.out.println(s.jump(arr4)); // Expected -1
    }
}
