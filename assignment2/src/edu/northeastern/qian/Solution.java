package edu.northeastern.qian;
import java.util.*;

public class Solution {
    /**
     * 1.Given an array of integers nums and an integer target, return indices of the two numbers
     * such that they add up to target. You may assume that each input would have exactly one solution,
     * and you may not use the same element twice.
     * You can return the answer in any order.
     * Example 1:
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Constraints:
     * 2 <= nums.length <= 104; -109 <= nums[i] <= 109; -109 <= target <= 109; Only one valid answer exists.
     */

    //solution1
    //O(n^2)
    public static int[] twoSum1(int[] arr, int target) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] + arr[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    //solution2
    //O(n)
    public static int[] twoSum2(int[] arr, int target) {
        //map key: nums, value: nums' indices
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int num = target - arr[i];
            if (map.containsKey(num)) {
                return new int[]{map.get(num), i};
            }
            map.put(arr[i], i);
        }
        return null;
    }


    /**
     * 2. Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return
     * the minimum number of conference rooms required.
     * Example 1:
     * Input: intervals = [[0,30],[5,10],[15,20]] Output: 2
     * Constraints: 1 <= intervals.length <= 104; 0 <= starti < endi <= 106
     */

    //solution1
    //O(nlogn)
    public static int minMeetingRooms1(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int room = 0;
        int endIndex = 0;
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[endIndex]) {//???????<
                room++;
            }else{
                endIndex++;
            }
        }
        return room;
    }

    //solution2
    //O(nlogn)
    public static int minMeetingRooms2(int[][] intervals) {
        PriorityQueue<Integer> rooms = new PriorityQueue<Integer>();
        //sort starts(ascending)
        Arrays.sort(intervals, (a,b) -> (a[0] - b[0]));

        rooms.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (rooms.peek() <= intervals[i][0]) {
                rooms.poll();
            }
            rooms.offer(intervals[i][1]);
        }
        return rooms.size();
    }


    /**
     * 3. Given two integer arrays nums1 and nums2, return an array of their intersection. Each element
     * in the result must be unique and you may return the result in any order.
     * Example 1:
     * Input: nums1 = [1,2,2,1], nums2 = [2,2] Output: [2]
     * Constraints: 1 <= nums1.length, nums2.length <= 1000; 0 <= nums1[i], nums2[i] <= 1000
     */

    //solution1
    //O(nlogn)
    public static int[] intersection1(int[] nums1, int[] nums2) {
        int[] longerArray = nums1;
        int[] shorterArray = nums2;

        if (nums1.length < nums2.length) {
            longerArray = nums2;
            shorterArray = nums1;
        }

        Arrays.sort(longerArray);

        Set<Integer> set = new HashSet<>();
        for (int n : shorterArray) {
            if (binarySearch(n, longerArray)){
                set.add(n);
            }
        }

        int i = 0;
        int[] res = new int[set.size()];
        for (int num : set) {
            res[i] = num;
            i++;
        }

        return res;
    }
    //binary search
    //if we find target, will be true
    public static boolean binarySearch(int target, int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;;
            }else {
                return true;
            }
        }
        return false;
    }

    //solution2
    //O(n)
    public static int[] intersection2(int[] arr1, int[] arr2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int n : arr1) {
            set1.add(n);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (int n : arr2) {
            set2.add(n);
        }

        if (set1.size() < set2.size()) {
            return find_intersection(set1, set2);
        }else{
            return find_intersection(set2, set1);
        }
    }

    public static int[] find_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int[] res = new int[set1.size()];
        int i = 0;
        for (int n : set1) {
            if (set2.contains(n)) {
                res[i++] = n;
            }
        }
        return Arrays.copyOf(res, i);
    }


    /**
     * 4. Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     * Example 1:
     * Input: nums = [3,2,3] Output: [3]
     * Example 2:
     * Input: nums = [1] Output: [1]
     * Constraints
     * • 1 <= nums.length <= 5 * 104
     * • -109 <= nums[i] <= 109
     */
    //O(n)
    public static List<Integer> majorityElement(int[] nums) {

        Integer c1 = null;
        Integer c2 = null;
        int cnt1 = 0;
        int cnt2 = 0;

        for (int n : nums) {
            if (c1 != null && c1 == n) {
                cnt1++;
            }else if (c2 != null && c2 == n) {
                cnt2++;
            }else if (cnt1 == 0) {
                c1 = n;
                cnt1++;
            }else if (cnt2 == 0) {
                c2 = n;
                cnt2++;
            }else{
                cnt1--;
                cnt2--;
            }
        }

        List<Integer> res = new ArrayList<>();

        cnt1 = 0;
        cnt2 = 0;
        for (int n : nums) {
            if (c1 != null && n == c1) {
                cnt1++;
            }
            if (c2 != null && n == c2){
                cnt2++;
            }
        }
        if (cnt1 > nums.length / 3) {
            res.add(c1);
        }
        if (cnt2 > nums.length / 3) {
            res.add(c2);
        }
        return res;
    }


    /**
     * 5. Given an array of integers nums sorted in ascending order, find the starting and ending
     * position of a given target value. If target is not found in the array, return [-1, -1].
     * Example 1:
     * Input: nums = [5,7,7,8,8,10], target = 8 Output: [3,4]
     * Example 2:
     * Input: nums = [5,7,7,8,8,10], target = 6 Output: [-1,-1]
     * Constraints:
     * • 0 <= nums.length <= 105
     * • -109 <= nums[i] <= 109
     * • nums is a non-decreasing array. • -109 <= target <= 109
     */
    // O(logn)
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }

        int left = findLeftIndex(nums, target);
        int right = findRightIndex(nums, target);

        if (left == -1) {
            return new int[] {-1, -1};
        }else {
            return new int[] {left, right};
        }
    }
    //Find the leftmost index
    public static int findLeftIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int leftMost = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }else{
                leftMost = mid;
                right = mid - 1;
            }
        }
        return leftMost;
    }
    //Find the rightmost index
    public static int findRightIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int rightMost = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }else{
                rightMost = mid;
                left = mid + 1;
            }
        }
        return rightMost;
    }


    /**
     * 6. Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column- wise,
     * return the number of negative numbers in grid.
     * Example 1:
     * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]] Output: 8
     * Explanation: There are 8 negatives number in the matrix.
     * Example 2:
     * Input: grid = [[3,2],[1,0]] Output: 0
     * Constraints:
     * • m == grid.length
     * • n == grid[i].length
     * • 1 <= m, n <= 100
     * • -100 <= grid[i][j] <= 100
     */

    //solution1
    //O(mlogn)
    public static int countNegatives1(int[][] grid) {
        //binary search
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            int left = 0;
            int right = grid[0].length - 1;
            int pos = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (grid[i][mid] < 0) {
                    pos = mid;
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
            if (pos != -1) {
                res += grid[0].length - pos;
            }
        }
        return res;
    }

    //solution2
    //O(m+n)
    public static int countNegatives2(int[][] grid) {
        int cnt = 0;
        int i = 0;
        int j = grid[i].length - 1;

        while (i >= 0 && j >= 0 &&
                i < grid.length && j < grid[i].length) {
            if (grid[i][j] < 0) {
                cnt += (grid.length - i);
                --j;
            } else {
                ++i;
            }
        }
        return cnt;
    }


    /**
     7.A peak element is an element that is strictly greater than its neighbors. Given an integer array nums,
     find a peak element, and return its index. If the array contains multiple peaks, return the index to
     any of the peaks.
     You may imagine that nums[-1] = nums[n] = -infinity
     Example 1:
     Input: nums = [1,2,3,1] Output: 2
     Explanation: 3 is a peak element and your function should return the index number 2.
     Constraints:
     • 1 <= nums.length <= 1000
     • -231 <= nums[i] <= 231 - 1
     • nums[i] != nums[i + 1] for all valid i.**/

    //binary search
    //O(logn)
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }


     /**
     *8. Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n]
      inclusive. There is only one repeated number in nums, return this repeated number.
     Example 1:
     Input: nums = [1,3,4,2,2] Output: 2
     Example 2:
     Input: nums = [3,1,3,4,2] Output: 3
     Constraints:
     1 <= n <= 105; nums.length == n + 1;1 <= nums[i] <= n;
      All the integers in nums appear only once except for precisely one integer which appears
     two or more times.**/

     //solution1(binary search)
     //O(nlogn)
     public static int findDuplicate1(int[] nums) {
         int left = 1;
         int right = nums.length - 1;

         while (left <= right) {
             int mid = left + (right - left) / 2;
             int cnt = 0;
             for (int i = 0; i < nums.length; i++) {
                 if (nums[i] <= mid) {
                     cnt++;
                 }
             }
             if (cnt > mid) {
                 right = mid - 1;
             }else {
                 left = mid + 1;
             }
         }
         return left;
     }

    //solution2
    //O(n)
     public static int findDuplicate2(int[] arr) {
         while (arr[0] != arr[arr[0]]) {
             int tmp = arr[arr[0]];
             arr[arr[0]] = arr[0];
             arr[0] = tmp;
         }
         return arr[0];
     }


     /**9. Given an array arr of positive integers sorted in a strictly increasing order, and an
      integer k. Find the kth positive integer that is missing from this array.
     Example 1:
     Input: arr = [2,3,4,7,11], k = 5
     Output: 9
     Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer
      is 9.
     Example 2:
     Input: arr = [1,2,3,4], k = 2
     Output: 6
     Constraints:
     • 1 <= arr.length <= 1000
     • 1 <= arr[i] <= 1000 • 1<=k<=1000
     • arr[i] < arr[j] for 1 <= i < j <= arr.length
     **/

     //solution1(binary search)
     //O(logn)
     public static int findKthPositive1(int[] arr, int k) {
         int left = 0;
         int right = arr.length - 1;

         while (left <= right) {
             int mid = left + (right - left) / 2;
             if (arr[mid] - mid - 1 < k) {
                 left = mid + 1;
             }else {
                 right = right - 1;
             }
         }
         return left + k;
     }

    //solution2
    //O(n)
     public static int findKthPositive2(int[] arr, int k) {
         //brute force
         for (int i = 0; i < arr.length; i++) {
             if (arr[i] - i - 1 >= k) {//arr[i] = i + 1
                 return i + k;
             }
         }
         return k + arr.length;
     }
}
