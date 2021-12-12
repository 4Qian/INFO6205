package edu.northeastern.qian.question3;
import java.util.Arrays;

public class Solution {
    public int jump(int[] nums) {
        // int[] record the minimum number of jumps to reach the "i" of array
        if (nums == null || nums.length == 0) {
           return -1;
        }
        int numOfJumps = 0;
        int currentPosition = 0;
        int rangeEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            rangeEnd = Math.max(nums[i] + i, rangeEnd);
            if (i == currentPosition) {
                numOfJumps++;
                if (rangeEnd == currentPosition) {
                    return -1;
                }
                currentPosition = rangeEnd;
            }
        }
        return numOfJumps;
    }
}
