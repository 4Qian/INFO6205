import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Assignment 1 Searching & Sorting
 */
public class Solutions {
    /**
     * Question 1: Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects
     * of the same color are adjacent, with the colors in the order red, white, and blue.
     * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
     *
     * Example 1:
     * Input: nums = [2,0,2,1,1,0] Output: [0,0,1,1,2,2]
     * Example 2:
     * Input: nums = [2,0,1] Output: [0,1,2]
     * Example 3:
     * Input: nums = [0] Output: [0]
     * Example 4:
     * Input: nums = [1] Output: [1]
     * Constraints:
     * •n == nums.length
     * •1 <= n <= 300
     * .nums[i] is 0, 1, or 2.
     * @param arr
     */
    //Time Complexity: O(n), Space Complexity: O(1)
    public static void question1(int[] arr) {
        int pivot = 1;
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        while (mid <= high) {
            if (arr[mid] < pivot) {
                swap(arr, mid, low);
                low ++;
                mid++;
            }else if (arr[mid] > pivot) {
                swap(arr, mid, high);
                high--;
            }else {
                mid++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Question 2: Given an array of meeting time intervals where intervals[i] = [starti, endi],
     * determine if a person could attend all meetings.
     * Example 1:
     * Input: intervals = [[0,30],[5,10],[15,20]] Output: false
     * Example 2:
     * Input: intervals = [[7,10],[2,4]] Output: true
     * Constraints:
     * 0 <= intervals.length <= 104;
     * intervals[i].length == 2;
     * 0 <= starti < endi <= 106
     * @param intervals
     * @return
     */
    // Time Complexity: O(nlogn) Space Complexity: O(1)
    public static boolean question2(ArrayList<Interval> intervals) {
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval x, Interval y) {
                return Integer.compare(x.start, y.start);
            }
        });

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i - 1).end > intervals.get(i).start) {
                return false;
            }
        }
        return true;
    }

    /**
     * Question 3: Given an integer array of 2n integers, group these integers into n
     * pairs (a1, b1), (a2, b2), ..., (an, bn) such that the sum of min(ai, bi) for all i is maximized.
     * Return the maximized sum.
     * Example 1:
     *  Input: nums = [1,4,3,2] Output: 4
     *  Explanation: All possible pairings (ignoring the ordering of elements) are:
     *  1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
     * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
     * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
     * So the maximum possible sum is 4.
     *  Example 2:
     *  Input: nums = [6,2,6,5,1,2]
     *  Output: 9
     * Explanation: The optimal pairing is (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9.
     *  Constraints:
     * 1 <= n <= 104
     * nums.length == 2 * n
     * -10^4 <= nums[i] <= 10^4
     * @param arr
     * @return
     */
    //solution 1:
    // Time Complexity: O(nlogn) Space Complexity: O(1)
    public static int question3Solution1(int[] arr) {
        Arrays.sort(arr);
        int maxSum = 0;
        for (int i = 0; i < arr.length; i += 2) {
            maxSum += arr[i];
        }
        return maxSum;
    }

    //solution 2:
    // Time Complexity: O(n + m) Space Complexity: O(m)
    public static int question3Solution2(int[] arr) {
        int[] bucket = new int[20001];
        int maxNum = Integer.MIN_VALUE;
        int minNum = Integer.MAX_VALUE;
        for (int num : arr) {
            bucket[num + 10000]++;
            maxNum = Math.max(num, maxNum);
            minNum = Math.min(num, minNum);
        }
        int res = 0;
        boolean toTake = true;
        for (int i = minNum; i <= maxNum; i++) {
            int count = bucket[i + 10000];
            for (int j = 0; j < count; j++) {
                if (toTake) {
                    res += i;
                }
                toTake = !toTake;
            }
        }
        return res;
    }

    /**
     * Question 4: Given an integer array nums sorted in non-decreasing order, return an array of the
     * squares of each number sorted in non-decreasing order.
     * Example 1:
     *Input: nums = [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Explanation: After squaring, the array becomes [16,1,0,9,100].After sorting, it becomes [0,1,9,16,100].
     * Example 2:
     *  Input: nums = [-7,-3,2,3,11]
     *  Output: [4,9,9,49,121]
     *  Constraints:
     * 1 <= nums.length <= 104
     *  -104 <= nums[i] <= 104
     * nums is sorted in non-decreasing order.
     */
    //solution1:
    // Time Complexity: O(nlogn) Space Complexity: O(n)
    public static int[] question4Solution1(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] * nums[i];
        }
        Arrays.sort(res);
        return res;
    }

    //solution2:
    // Time Complexity: O(n) Space Complexity: O(n)
    public static int[] question4Solution2(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        int left = 0;
        int right = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int square;
            if (Math.abs(arr[left]) < Math.abs(arr[right])) {
                square = arr[right];
                right--;
            } else {
                square = arr[left];
                left++;
            }
            res[i] = square * square;
        }
        return res;
    }

    /**
     * Question 5: Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * Example 1:
     *  Input: s = "anagram", t = "nagaram"
     *  Output: true
     *  Example 2:
     *  Input: s = "rat", t = "car" Output: false
     *  Constraints:
     *   1 <= s.length, t.length <= 5 * 104
     *   s and t consist of lowercase English letters.
     * @return
     */
    //solution1:
    // Time Complexity: O(nlogn) Space Complexity: O(n)
    public static boolean question5Solution1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(s2);
        return Arrays.equals(s1, s2);
    }

    //solution2:
    // Time Complexity: O(n) Space Complexity:  O(n)
    public static boolean question5Solution2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            if (count[c - 'a'] == 0) {
                return false;
            }
            count[c - 'a']--;
        }
        return true;
    }

    /**
     * Question 6: Given an integer array nums, move all the even integers at the beginning of the array
     * followed by all the odd integers. Return any array that satisfies this condition.
     * Example 1:
     * Input: nums = [3,1,2,4] Output: [2,4,3,1]
     * Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
     * Example 2:
     * Input: nums = [0] Output: [0]
     * Constraints:
     * 1 <= nums.length <= 5000
     * 0 <= nums[i] <= 5000*
     */

    //solution1:
    // Time Complexity: O(n) Space Complexity: O(1)
    public static int[] question6Solution1(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if (arr[i] % 2 > arr[j] % 2) {//put even number in front of odd number
                swap(arr, i, j);
            }
            if (arr[i] % 2 == 0) {
                i++;
            }
            if (arr[j] % 2 == 1) {
                j--;
            }
        }
        return arr;
    }

    //solution2:
    // Time Complexity: O(nlogn) Space Complexity: O(n)
    public static int[] question6Solution2(int[] arr) {
        Integer[] res = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++)
            res[i] = arr[i];

        Arrays.sort(res, (a, b) -> Integer.compare(a % 2, b % 2));
        for (int i = 0; i < arr.length; i++){
            arr[i] = res[i];
        }
        return arr;
    }
}
