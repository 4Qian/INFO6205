package edu.northeastern.qian;
import java.util.*;

public class Solution {

    //==================================================== Question 1 ==============================================

    /**
     * Time Complexity: o(n)
     * Space Complexity: o(1)
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int i = 0;
        int total = 0;
        int sign = 1;
        if (s.length() == 0) {
            return 0;
        }
        // remove whitespaces
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        if (i == s.length()) {
            return 0;
        }
        // get sign
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = s.charAt(i) == '+'? 1 : -1;
            i++;
        }
        // Convert these digits into an integer
        while (i < s.length()) {
            int digit = s.charAt(i) - '0';
            if (digit < 0 || digit > 9) {
                break;
            }
            // remains total is in the integer range
            if (Integer.MAX_VALUE / 10 < total ||
                    Integer.MAX_VALUE / 10 == total &&
                            Integer.MAX_VALUE % 10 < digit) {
                return sign == 1? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            total = total * 10 + digit;
            i++;
        }
        return total*sign;
    }


    //==================================================== Question 2 ==============================================

    /**
     * Time complexity: o(1)
     * Space complexity: o(1)
     */
    private final Map<String, String> months = getMonth();

    public String reformatDate(String date) {
        // We divide String “date” into a String array so that we can utilize and access to
                // its parts of the year, month, and day
        String[] s = date.split("\\s+");
        StringBuilder sb = new StringBuilder();
        // Add “year，month, day” according to the style of output
        sb.append(s[2] + "-");
        sb.append(months.get(s[1]) + "-");
        sb.append(s[0].length() != 3? s[0].substring(0,2) : "0" + s[0].substring(0,1));
        return sb.toString();
    }
    // Create a map that allows us to convert the form of "Jan"， "Feb"..etc to 2 digit month
    public Map<String, String> getMonth() {
        Map<String, String> months = new HashMap<>();
        months.put("Jan", "01");
        months.put("Feb", "02");
        months.put("Mar", "03");
        months.put("Apr", "04");
        months.put("May", "05");
        months.put("Jun", "06");
        months.put("Jul", "07");
        months.put("Aug", "08");
        months.put("Sep", "09");
        months.put("Oct", "10");
        months.put("Nov", "11");
        months.put("Dec", "12");
        return months;
    }


    //==================================================== Question 3 ==============================================

    /**
     * Time complexity: o(n)
     * Space complexity: o(n)
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        // If the length of s is odd, then there is one that can never be matched, return false
        if (s.length() % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');

        Stack<Character> stack = new Stack();
        // Loop through all the elements in s,
        //If the closed parenthesis is encountered,
                // check whether it can match with the top element of the stack
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || pairs.get(c) != stack.peek()) {
                    return false;
                }
                stack.pop();
            }else{
                // If it was an opening bracket, push to the stack
                stack.push(c);
            }
        }
        // Stack should be empty for a valid expression
        return stack.isEmpty();
    }


    //==================================================== Question 4 ==============================================

    /**
     * Time complexity: o(2^n)
     * Space complexity: o(1)
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String s = "1";

        for (int i = 1; i < n; i++) {
            int cnt = 0;
            char start = s.charAt(0);
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < s.length(); j++) {
                // Count the occurrences of the same element in order
                if (s.charAt(j) == start) {
                    cnt++;
                    // If current element is different from the previous element,
                        // store counts of the previous element and  previous element into sb
                            // then update and count current element
                }else{
                    sb.append(cnt).append(start);
                    cnt = 1;
                    start = s.charAt(j);
                }
            }
            sb.append(cnt).append(start);
            s = sb.toString();
        }
        return s;
    }


    //==================================================== Question 5 ==============================================

    /**
     * Time complexity: o(n)
     * Space complexity: o(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int lowest = prices[0];
        int maxProfit = 0;
        // The price starting from prices[1] is either greater than the previous price,
            // or less than or equal to its previous price
        for (int i = 1; i < prices.length; i++) {
            // When the subsequent price is greater than the previous price,
                // we can get current maximum profit since the selling requirement is met
            if (prices[i] > lowest) {
                maxProfit = Math.max(maxProfit, prices[i] - lowest);
                // When the subsequent price is less than or equal to the previous price,
                    // we cannot sell stock and the current minimum price should be updated
            }else{
                lowest = prices[i];
            }
        }
        return maxProfit;
    }


    //==================================================== Question 6 ==============================================

    /**
     * Time complexity: o(n)
     * Space complexity: o(1)
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        int leftSum = 0;
        for (int n : nums) {
            totalSum = totalSum + n;
        }
        // check if the leftsum that are to the left of index I is equals the other sum
            // to the right of the index
        // rightSum should be totalSum - nums[i] - left sum
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum = leftSum + nums[i];
        }
        return -1;
    }


    //==================================================== Question 7 ==============================================

    /**
     * Time complexity: o(n log n)
     * Space complexity: o(n)
     *
     * @param items
     * @return
     */
    public int[][] highFive(int[][] items) {
        // key: id, value: id's scores
        TreeMap<Integer, Queue<Integer>> scores = new TreeMap<>();
        for (int[] item : items) {
            int id = item[0];
            int score = item[1];
            if (!scores.containsKey(id)) {
                // Grades are sorted in descending order
                scores.put(id, new PriorityQueue<>((a, b) -> b - a));
            }
            // add score to the heap
            scores.get(id).add(score);
        }
        // obtain the top 5 scores
        List<int[]> res = new ArrayList<>();
        for (int id : scores.keySet()) {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += scores.get(id).poll();
            }
            res.add(new int[] {id, sum / 5});
        }
        int[][] resArray = new int[res.size()][];
        return res.toArray(resArray);
    }


    //==================================================== Question 8 ==============================================

    /**
     * Time complexity: o(log n)
     * Space complexity: o(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        int peakIndex = findPeakIndex(nums, 0, nums.length - 1);
        // If target is between nums[0]-peak value of nums,
            // then binary search for target in this range, otherwise search from peakIndex + 1
        if (target >= nums[0] && target <= nums[peakIndex]) {
            return binarySearch(nums, 0, peakIndex, target);
        }else{
            return binarySearch(nums, peakIndex + 1, nums.length - 1, target);
        }
    }

    public int findPeakIndex(int[] nums, int start, int end) {
        // If the length of nums is 1, peakIndex should be 0
        if (nums.length == 1) {
            return 0;
        }
        // If nums is monotonically increasing, peakIndex should be the index of the last number
        if (nums[0] < nums[nums.length - 1]) {
            return nums.length - 1;
        }
        // Another case：
        int left = start;
        int right = end;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Since nums is a sorted rotated array,
            // when a value is greater than the value on the right,
                // this value should be the maximum value of nums
            if (nums[mid] > nums[mid + 1]) {
                return mid;
            }else if(nums[left]  <= nums[mid]) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }
    // Use binary search to search for target
    public int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
