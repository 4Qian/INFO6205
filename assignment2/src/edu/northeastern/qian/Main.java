package edu.northeastern.qian;
import static edu.northeastern.qian.Solution.*;

/**
 * Note: For questions related to Searching, please use Binary Search
 */

public class Main {
    public static void main(String[] args) {
        //question1
        System.out.println("=================question1===============");
        int[] arr = {2, 9, 11, 3, 4};
        int target = 6;
        System.out.print("Indices of the two numbers such that they add up to target are ");//[0,4]
        printArray(twoSum1(arr, 6));
        System.out.println();
        System.out.print("Indices of the two numbers such that they add up to target are ");//[0,4]
        printArray(twoSum2(arr, 6));
        System.out.println();
        System.out.println();

        //question2
        System.out.println("=================question2===============");
        int[][] arr1 = {{0,30},{5,10},{15,20}};
        System.out.println("The minimum number of conference rooms required are " + minMeetingRooms1(arr1));//2
        System.out.println("The minimum number of conference rooms required are " + minMeetingRooms2(arr1));//2
        System.out.println();

        //question3
        System.out.println("=================question3===============");
        int[] nums1 = {1,2,3,1};
        int[] nums2 = {2,4};

        int[] nums3 = {4,9,5};
        int[] nums4 ={9,4,9,8,4};

        System.out.println("The array of their intersection are ");
        printArray(intersection1(nums1, nums2));//2
        System.out.println();
        System.out.println("The array of their intersection are ");
        printArray(intersection1(nums3, nums4));//4,9
        System.out.println();
        System.out.println("The array of their intersection are ");
        printArray(intersection2(nums1, nums2));//2
        System.out.println();
        System.out.println("The array of their intersection are ");
        printArray(intersection2(nums3, nums4));//4,9
        System.out.println();
        System.out.println();

        //question4
        System.out.println("=================question4===============");
        int[] nums5 = {3,2,3,3,2,2,1};
        System.out.println("All elements that appear more than ⌊ n/3 ⌋ times: " + majorityElement(nums5));//3,2
        System.out.println();

        //question5
        System.out.println("=================question5===============");
        int[] nums6 = {5,7,7,8,8,10};
        int t = 8;
        System.out.println("The range of target is ");//[3,4]
        printArray(searchRange(nums6, t));
        System.out.println();
        System.out.println();

        //question6
        System.out.println("=================question6===============");
        int[][] grid = {{4,3,2,-1},{3,2,1,-1},{1,1,-1,-2},{-1,-1,-2,-3}};
        System.out.println("The number of negative numbers is " + countNegatives1(grid));//8
        System.out.println("The number of negative numbers is " + countNegatives2(grid));//8
        System.out.println();

        //question7
        System.out.println("=================question7===============");
        int[] nums7 = {1,2,1,3,5,6,4};
        System.out.println("The index to any of the peaks is " + findPeakElement(nums7));//5
        System.out.println();

        //question8
        System.out.println("=================question8===============");
        int[] nums8 = {1,3,4,2,2};
        int[] nums9 = {3,1,3,4,2};
        System.out.println("Repeated number is " + findDuplicate1(nums8));//2
        System.out.println("Repeated number is " + findDuplicate2(nums9));//3
        System.out.println("Repeated number is " + findDuplicate1(nums8));//2
        System.out.println("Repeated number is " + findDuplicate2(nums9));//3
        System.out.println();

        //question9
        System.out.println("=================question9===============");
        int[] nums10 = {2,3,4,7,11};
        int k = 5;
        System.out.println("The kth positive integer that is missing from this array is " +
                findKthPositive1(nums10, k));//9
        System.out.println("The kth positive integer that is missing from this array is " +
                findKthPositive2(nums10, k));//9
    }

    public static void printArray(int[] nums) {
        System.out.print("[");
        for (int i = 0; i < nums.length - 1; i++) {
            System.out.print(nums[i]);
            System.out.print(", ");
        }
        System.out.print(nums[nums.length - 1]);
        System.out.print("] ");
    }
}
