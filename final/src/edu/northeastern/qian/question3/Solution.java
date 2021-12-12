package edu.northeastern.qian.question3;

import java.util.Arrays;

public class Solution {
    public int jump(int[] nums) {
        // int[] record the minimum number of jumps to reach the "i" of array
        if (nums == null) {
           return -1;
        }
        int[] jumps = new int[nums.length];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + i >= j) {
                    if (jumps[i] + 1 < jumps[j]) {
                        jumps[j] = jumps[i] + 1;
                    }
                }
            }
        }
        return jumps[jumps.length - 1];
    }
}
