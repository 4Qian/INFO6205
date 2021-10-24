package edu.northeastern.qian.question2;

public class Question2 {
    // time complexity: o(n)
    // space complexity: o(1)
    public int missingNumber(int[] nums) {
        long sum = 0;
        long len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // If we add the missing number, the sum would be
        // (0 + n) (n + 1) / 2, where n = nums.length
        return (int) (((len + 1) * len) / 2 - sum);
    }
}
