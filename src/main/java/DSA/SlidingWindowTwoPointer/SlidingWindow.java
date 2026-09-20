package DSA.SlidingWindowTwoPointer;

import java.util.*;

public class SlidingWindow {




        public int lengthOfLongestSubstring(String s) {
            HashMap<Character,Integer> mp = new HashMap<>();

            int i = 0;
            int n = s.length();
            int j = 0;
            int ans = 0;
            while(i<n){
                char ch = s.charAt(i);
                mp.put(s.charAt(i),mp.getOrDefault(ch,0)+1);
                if(i-j+1>mp.size()){
                    while(i-j+1>mp.size()){
                        char chr1 = s.charAt(j);
                        if(mp.get(chr1)==1){
                            mp.remove(chr1);
                        }else{
                            mp.put(chr1,mp.get(chr1)-1);
                        }
                        j++;
                    }
                }
                ans = Math.max(ans,i-j+1);
                i++;
            }

            return ans;
        }



        //Minimum Window Substring
        public String minWindow1(String s, String t) {
            if (t.length() > s.length()) return "";

            Map<Character, Integer> need = new HashMap<>();
            for (char c : t.toCharArray()) {
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            Map<Character, Integer> window = new HashMap<>();
            int have = 0, needCount = need.size();
            int l = 0, minLen = Integer.MAX_VALUE, startIndex = 0;

            for (int r = 0; r < s.length(); r++) {
                char c = s.charAt(r);
                window.put(c, window.getOrDefault(c, 0) + 1);

                if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                    have++;
                }

                while (have == needCount) {
                    // Update minimum
                    if ((r - l + 1) < minLen) {
                        minLen = r - l + 1;
                        startIndex = l;
                    }

                    // Shrink from left
                    char leftChar = s.charAt(l);
                    window.put(leftChar, window.get(leftChar) - 1);
                    if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) {
                        have--;
                    }
                    l++;
                }
            }

            return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
        }

        // Longest repeating character replacement
            public int characterReplacement(String s, int k) {

                int[] freq = new int[26];

                int left = 0;
                int maxFreq = 0;
                int ans = 0;

                for (int right = 0; right < s.length(); right++) {

                    int index = s.charAt(right) - 'A';

                    freq[index]++;

                    maxFreq = Math.max(maxFreq, freq[index]);

                    // Invalid window
                    while ((right - left + 1) - maxFreq > k) {

                        freq[s.charAt(left) - 'A']--;
                        left++;
                    }

                    ans = Math.max(ans, right - left + 1);
                }

                return ans;
            }


        // Permutation in string
            public boolean checkInclusion(String s1, String s2) {

                int n1 = s1.length();
                int n2 = s2.length();

                // Impossible if s2 is shorter
                if (n2 < n1) {
                    return false;
                }

                // Frequency of s1
                int[] count1 = new int[26];

                // Frequency of current window in s2
                int[] count2 = new int[26];

                // Create first window
                for (int i = 0; i < n1; i++) {
                    count1[s1.charAt(i) - 'a']++;
                    count2[s2.charAt(i) - 'a']++;
                }

                // Check first window
                if (Arrays.equals(count1, count2)) {
                    return true;
                }

                // Slide window
                for (int i = n1; i < n2; i++) {

                    // Add new character entering window
                    count2[s2.charAt(i) - 'a']++;

                    // Remove old character leaving window
                    count2[s2.charAt(i - n1) - 'a']--;

                    // Check current window
                    if (Arrays.equals(count1, count2)) {
                        return true;
                    }
                }

                return false;
            }





        // Fruit Into Baskets


        public int totalFruit(int[] fruits) {

            int ans = 0;
            Map<Integer, Integer> mp = new HashMap<>();

            int left = 0;

            for (int right = 0; right < fruits.length; right++) {

                mp.put(
                        fruits[right],
                        mp.getOrDefault(fruits[right], 0) + 1
                );

                while (mp.size() > 2) {

                    int fruit = fruits[left];

                    if (mp.get(fruit) == 1) {
                        mp.remove(fruit);
                    } else {
                        mp.put(fruit, mp.get(fruit) - 1);
                    }

                    left++;
                }

                ans = Math.max(ans, right - left + 1);
            }

            return ans;
        }


    // 239. Sliding Window Maximum (VVI)

        public int[] maxSlidingWindow(int[] nums, int k) {

            int n = nums.length;
            int[] ans = new int[n - k + 1];

            Deque<Integer> deque = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {

                // 1. Remove elements outside the window
                while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }

                // 2. Remove smaller elements from the back
                while (!deque.isEmpty() &&
                        nums[deque.peekLast()] <= nums[i]) {
                    deque.pollLast();
                }

                // 3. Add current index
                deque.offerLast(i);

                // 4. Window is ready
                if (i >= k - 1) {
                    ans[i - k + 1] = nums[deque.peekFirst()];
                }
            }

            return ans;
        }


    // Binary Subarrays With Sum


        public int numSubarraysWithSum(int[] nums, int goal) {
            return atMost0(nums, goal) - atMost(nums, goal - 1);
        }

        private int atMost0(int[] nums, int goal) {

            if (goal < 0) return 0;

            int left = 0;
            int sum = 0;
            int count = 0;

            for (int right = 0; right < nums.length; right++) {

                sum += nums[right];

                while (sum > goal) {
                    sum -= nums[left++];
                }

                count += right - left + 1;
            }

            return count;
        }


    // 1248. Count Number of Nice Subarrays



        public int numberOfSubarrays(int[] nums, int k) {
            return atMost1(nums, k) - atMost(nums, k - 1);
        }

        private int atMost1(int[] nums, int k) {

            int left = 0;
            int count = 0;
            int ans = 0;

            for (int right = 0; right < nums.length; right++) {

                // Treat odd number as 1
                if (nums[right] % 2 == 1) {
                    count++;
                }

                // More than k odd numbers
                while (count > k) {
                    if (nums[left] % 2 == 1) {
                        count--;
                    }
                    left++;
                }

                // All subarrays ending at right
                ans += right - left + 1;
            }

            return ans;
        }


    // 1358. Number of Substrings Containing All Three Characters


        public int numberOfSubstrings(String s) {

            int[] count = new int[3];

            int left = 0;
            int ans = 0;

            for (int right = 0; right < s.length(); right++) {

                count[s.charAt(right) - 'a']++;

                while (count[0] > 0 &&
                        count[1] > 0 &&
                        count[2] > 0) {

                    // Current window is valid
                    ans += left + 1;

                    count[s.charAt(left) - 'a']--;
                    left++;
                }
            }

            return ans;
        }


    // 1423. Maximum Points You Can Obtain from Cards

        public int maxScore(int[] cardPoints, int k) {

            int n = cardPoints.length;

            int total = 0;

            for (int card : cardPoints) {
                total += card;
            }

            int windowSize = n - k;

            // If taking all cards
            if (windowSize == 0) {
                return total;
            }

            int windowSum = 0;

            // First window
            for (int i = 0; i < windowSize; i++) {
                windowSum += cardPoints[i];
            }

            int minWindow = windowSum;

            // Sliding window
            for (int i = windowSize; i < n; i++) {

                windowSum += cardPoints[i];
                windowSum -= cardPoints[i - windowSize];

                minWindow = Math.min(minWindow, windowSum);
            }

            return total - minWindow;
        }


    // 992. Subarrays with K Different Integers

        public int subarraysWithKDistinct(int[] nums, int k) {
            return atMost(nums, k) - atMost(nums, k - 1);
        }

        private int atMost(int[] nums, int k) {

            Map<Integer, Integer> freq = new HashMap<>();

            int left = 0;
            int ans = 0;

            for (int right = 0; right < nums.length; right++) {

                freq.put(
                        nums[right],
                        freq.getOrDefault(nums[right], 0) + 1
                );

                while (freq.size() > k) {

                    int num = nums[left];

                    freq.put(num, freq.get(num) - 1);

                    if (freq.get(num) == 0) {
                        freq.remove(num);
                    }

                    left++;
                }

                // Number of valid subarrays ending at right
                ans += right - left + 1;
            }

            return ans;
        }


    // Longest Substring With At Most K Distinct Characters


        public int kDistinctChars(String s, int k) {

            Map<Character, Integer> freq = new HashMap<>();

            int left = 0;
            int ans = 0;

            for (int right = 0; right < s.length(); right++) {

                char ch = s.charAt(right);

                freq.put(ch, freq.getOrDefault(ch, 0) + 1);

                // Invalid window
                while (freq.size() > k) {

                    char leftChar = s.charAt(left);

                    freq.put(leftChar, freq.get(leftChar) - 1);

                    if (freq.get(leftChar) == 0) {
                        freq.remove(leftChar);
                    }

                    left++;
                }

                // Valid window
                ans = Math.max(ans, right - left + 1);
            }

            return ans;
        }


    // Minimum Window Subsequence

        public String minWindow(String s1, String s2) {

            int n = s1.length();
            int m = s2.length();

            int minLen = Integer.MAX_VALUE;
            int start = -1;

            int i = 0;

            while (i < n) {

                int j = 0;

                // Phase 1: Find s2 as a subsequence
                while (i < n) {

                    if (s1.charAt(i) == s2.charAt(j)) {
                        j++;

                        if (j == m) {
                            break;
                        }
                    }

                    i++;
                }

                // s2 not found
                if (j != m) {
                    break;
                }

                // Phase 2: Move backward to minimize window
                int end = i;
                j = m - 1;

                while (j >= 0) {

                    if (s1.charAt(i) == s2.charAt(j)) {
                        j--;
                    }

                    i--;
                }

                int windowStart = i + 1;

                if (end - windowStart + 1 < minLen) {
                    minLen = end - windowStart + 1;
                    start = windowStart;
                }

                // Start searching again from next position
                i = windowStart + 1;
            }

            return start == -1
                    ? ""
                    : s1.substring(start, start + minLen);
        }

}
