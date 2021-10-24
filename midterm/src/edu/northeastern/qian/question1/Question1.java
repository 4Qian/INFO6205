package edu.northeastern.qian.question1;

public class Question1 {
    // time complexity: o(n)
    // space complexity: o(1)
    public static int[] moveZeroes(int[] nums) {
        int index = 0;
        // write all the non-zero numbers
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        // write all the zeros in the remaining positions
        for (int i = index; i < nums.length; i++) {
            nums[index++] = 0;
        }
        return nums;
    }
}
