package DSA.DP;

import java.util.*;

public class DP_ON_LIS {

    public static void main(String[] args) {

    }



//  Input:  nums = [10, 9, 2, 5, 3, 7, 101, 18]
//  Output: 4
//  Reason: [2, 3, 7, 101] or [2, 5, 7, 101]


//At every index i, ask:
//        "What is the longest increasing subsequence ending at i?"
//
//          For every j < i:
//              if nums[j] < nums[i]:
//                      dp[i] = max(dp[i], 1 + dp[j])
//f
//              Answer = max of all dp[i]


    private int solve(int idx, int prevIdx, int[] nums) {
        // base case
        if (idx == nums.length) return 0;

        // option 1: skip current element
        int skip = solve(idx + 1, prevIdx, nums);

        // option 2: take current element (if greater than prev)
        int take = 0;
        if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
            take = 1 + solve(idx + 1, idx, nums);
        }

        return Math.max(take, skip);
    }

    public int lengthOfLIS(int[] nums) {
        return solve(0, -1, nums);
    }


    int[][] memo;

    private int solve1(int idx, int prevIdx, int[] nums) {
        if (idx == nums.length) return 0;

        // shift prevIdx by 1 to handle -1 index
        // prevIdx=-1 → stored at memo[idx][0]
        // prevIdx= 0 → stored at memo[idx][1] ... and so on
        if (memo[idx][prevIdx + 1] != -1)
            return memo[idx][prevIdx + 1];

        // option 1: skip
        int skip = solve1(idx + 1, prevIdx, nums);

        // option 2: take
        int take = 0;
        if (prevIdx == -1 || nums[idx] > nums[prevIdx])
            take = 1 + solve1(idx + 1, idx, nums);

        return memo[idx][prevIdx + 1] = Math.max(take, skip);
    }



//
public int lengthOfLIS1(int[] nums) {
    int n = nums.length;

    // dp[idx][prevIdx+1]
    // same dimensions as memo table
    // prevIdx shifted by 1 → index 0 means prevIdx=-1
    int[][] dp = new int[n + 1][n + 1];

    // base case: idx == n → 0
    // already 0 by default

    // fill bottom-up (reverse of recursion)
    // recursion goes idx: 0→n, tabulation goes idx: n→0
    for (int idx = n - 1; idx >= 0; idx--) {
        for (int prevIdx = idx - 1; prevIdx >= -1; prevIdx--) {

            // option 1: skip current element
            int skip = dp[idx + 1][prevIdx + 1];

            // option 2: take current element
            int take = 0;
            if (prevIdx == -1 || nums[idx] > nums[prevIdx])
                take = 1 + dp[idx + 1][idx + 1];

            dp[idx][prevIdx + 1] = Math.max(take, skip);
        }
    }

    // answer: idx=0, prevIdx=-1 → dp[0][0]
    return dp[0][0];
}

//Time: O(n²) | Space: O(n²)


//Step 4: Space Optimisation ✅

public int lengthOfLIS2(int[] nums) {
    int n = nums.length;

    // only need next row → reduce to 1D
    int[] next = new int[n + 1];  // dp[idx+1][*]
    int[] curr = new int[n + 1];  // dp[idx][*]

    // fill bottom-up
    for (int idx = n - 1; idx >= 0; idx--) {
        for (int prevIdx = idx - 1; prevIdx >= -1; prevIdx--) {

            // skip
            int skip = next[prevIdx + 1];

            // take
            int take = 0;
            if (prevIdx == -1 || nums[idx] > nums[prevIdx])
                take = 1 + next[idx + 1];

            curr[prevIdx + 1] = Math.max(take, skip);
        }
        next = curr.clone();
    }

    // answer: idx=0, prevIdx=-1 → next[0]
    return next[0];
}



public int lengthOfLIS4(int[] nums) {
    int n = nums.length;

    // dp[i] = length of LIS ending at index i
    int[] dp = new int[n];
    Arrays.fill(dp, 1);   // every element is LIS of length 1

    int maxLen = 1;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {                    // strictly increasing
                dp[i] = Math.max(dp[i], 1 + dp[j]);    // extend LIS
            }
        }
        maxLen = Math.max(maxLen, dp[i]);
    }

    return maxLen;
}
//
//Print LIS

    public List<Integer> printLIS(int[] nums) {
        int n = nums.length;
        int[] dp   = new int[n];
        int[] hash = new int[n];    // track parent index
        Arrays.fill(dp, 1);

        int maxLen = 1;
        int lastIdx = 0;            // index where LIS ends

        for (int i = 0; i < n; i++) {
            hash[i] = i;            // initially points to itself
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[i] < 1 + dp[j]) {
                    dp[i]   = 1 + dp[j];
                    hash[i] = j;    // came from j
                }
            }
            if (dp[i] > maxLen) {
                maxLen  = dp[i];
                lastIdx = i;
            }
        }

        // backtrack using hash array
        List<Integer> lis = new ArrayList<>();
        lis.add(nums[lastIdx]);

        while (hash[lastIdx] != lastIdx) {
            lastIdx = hash[lastIdx];
            lis.add(nums[lastIdx]);
        }

        Collections.reverse(lis);
        return lis;
    }

//// nums=[10,9,2,5,3,7,101,18] → [2,3,7,101] or [2,5,7,101]
//```
//
//        ---
//
//        ## Problem 3 — LIS using Binary Search (O(n log n))
//
//        ### Intuition
//```
//         Maintain a tails[] array:
//         tails[i] = smallest tail element of all
//         increasing subsequences of length i+1
//
//          For each num:
//        if num > tails.last  → extend LIS
//          else
//              → replace first element >= num (binary search)
//
//
//
         public int lengthOfLIS5(int[] nums) {
             // tails[i] = smallest tail of IS with length i+1
             List<Integer> tails = new ArrayList<>();

             for (int num : nums) {
                 // binary search: find le4ftmost pos where tails[pos] >= num
                 int lo = 0, hi = tails.size();

                 while (lo < hi) {
                     int mid = lo + (hi - lo) / 2;
                     if (tails.get(mid) < num)
                         lo = mid + 1;   // num can extend this
                     else
                         hi = mid;       // tails[mid] >= num
                 }

                 if (lo == tails.size())
                     tails.add(num);      // extend LIS
                 else
                     tails.set(lo, num);  // replace to optimise
             }

             return tails.size();   // length of LIS
         }
//
//
//
////```
////
////     ---
////
////### Detailed Dry Run
////
////`nums = [10, 9, 2, 5, 3, 7, 101, 18]`
////```
////     tails = []
////
////━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
////     num = 10:
////     tails = [] → lo=0=size → EXTEND
////     tails = [10]
////
////━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
////     num = 9:
////     tails = [10]
////     binary search: lo=0,hi=1
////     mid=0: tails[0]=10 >= 9 → hi=0
////     lo=0 != size=1 → REPLACE tails[0]=9
////     tails = [9]
////
////━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
////     num = 2:
////     tails = [9]
////     binary search: lo=0,hi=1
////     mid=0: tails[0]=9 >= 2 → hi=0
////     lo=0 != size=1 → REPLACE tails[0]=2
////     tails = [2]
////
////━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
////     num = 5:
////     tails = [2]
////     binary search: lo=0,hi=1
////     mid=0: tails[0]=2 < 5 → lo=1
////     lo=1 == size=1 → EXTEND
////     tails = [2, 5]
////
////━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
////     num = 3:
////     tails = [2, 5]
////     binary search: lo=0,hi=2
////     mid=1: tails[1]=5 >= 3 → hi=1
////     mid=0: tails[0]=2 < 3  → lo=1
////     lo=1 != size=2 → REPLACE tails[1]=3
////     tails = [2, 3]
////
////━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
////     num = 7:
////     tails = [2, 3]
////     binary search: lo=0,hi=2
////     mid=1: tails[1]=3 < 7 → lo=2
////     lo=2 == size=2 → EXTEND
////     tails = [2, 3, 7]
////
////━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
////     num = 101:
////     tails = [2, 3, 7]
////     binary search: lo=0,hi=3
////     mid=1: tails[1]=3  < 101 → lo=2
////     mid=2: tails[2]=7  < 101 → lo=3
////     lo=3 == size=3 → EXTEND
////     tails = [2, 3, 7, 101]
////
////━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
////     num = 18:
////     tails = [2, 3, 7, 101]
////     binary search: lo=0,hi=4
////     mid=2: tails[2]=7   < 18  → lo=3
////     mid=3: tails[3]=101 >= 18 → hi=3
////     lo=3 != size=4 → REPLACE tails[3]=18
////     tails = [2, 3, 7, 18]
////
////     Answer: tails.size() = 4  ✅
////```
////
////     ---
////
////### tails[] Step by Step
////```
////     num:   10    9    2    5    3    7   101   18
////       ──────────────────────────────────────
////     tails:[10]
////      [9]
////      [2]
////      [2,  5]
////      [2,  3]
////      [2,  3,  7]
////      [2,  3,  7, 101]
////      [2,  3,  7,  18]   ← answer = 4  ✅
////
////     Note: tails is NOT the actual LIS
////     tails = [2,3,7,18] but actual LIS = [2,3,7,101]
////     tails.size() = LIS LENGTH only
////```
////
////     ---
////
////### Important Note
////```
////     tails[] does NOT represent the actual LIS!
////
////             tails = [2, 3, 7, 18]
////     but actual LIS = [2, 3, 7, 101]  or  [2, 5, 7, 101]
////
////     tails[] gives only the LENGTH correctly.
////     To print actual LIS → use O(n²) approach with hash[]
//
//
//```
//
//     ---
//
//      ### Complexity Comparison
//
//              | Approach | Time | Space | Print LIS? |
//             |---|---|---|---|
//             | Tabulation | `O(n²)` | `O(n)` | ✅ Yes (hash[]) |
//|             Binary Search | `O(n log n)` | `O(n)` | ⚠️ Complex |
//
//             ---
//
//### Key Takeaways
//```
//     Print LIS (O(n²)):
//     1. hash[i] = parent index of i in LIS
//     2. when dp[i] updates → hash[i] = j
//     3. backtrack from lastIdx using hash[]
//     4. stop when hash[i] == i (self loop)
//     5. reverse the collected elements











         private boolean isPredecessor1(String w1, String w2) {
             if (w2.length() - w1.length() != 1) return false;
             int i = 0, j = 0;
             while (i < w2.length()) {
                 if (j < w1.length() && w2.charAt(i) == w1.charAt(j)) j++;
                 i++;
             }
             return j == w1.length();
         }

         private int solve(int idx, int prevIdx, String[] words) {
             if (idx == words.length) return 0;

             // skip current word
             int skip = solve(idx + 1, prevIdx, words);

             // take current word
             int take = 0;
             if (prevIdx == -1 || isPredecessor(words[prevIdx], words[idx]))
                 take = 1 + solve(idx + 1, idx, words);

             return Math.max(take, skip);
         }


         public int longestStrChain(String[] words) {
             // sort by length first
             Arrays.sort(words, (a, b) -> a.length() - b.length());
             return solve(0, -1, words);
         }






         private boolean isPredecessor(String w1, String w2) {
             if (w2.length() - w1.length() != 1) return false;
             int i = 0, j = 0;
             while (i < w2.length()) {
                 if (j < w1.length() && w2.charAt(i) == w1.charAt(j)) j++;
                 i++;
             }
             return j == w1.length();
         }


         public int longestStrChain1(String[] words) {
             int n = words.length;

             // Step 1: sort by word length
             Arrays.sort(words, (a, b) -> a.length() - b.length());

             // Step 2: LIS dp
             int[] dp = new int[n];
             Arrays.fill(dp, 1);   // every word is chain of length 1

             int maxLen = 1;

             for (int i = 1; i < n; i++) {
                 for (int j = 0; j < i; j++) {
                     // check if words[j] is predecessor of words[i]
                     if (isPredecessor(words[j], words[i])) {
                         dp[i] = Math.max(dp[i], 1 + dp[j]);
                     }
                 }
                 maxLen = Math.max(maxLen, dp[i]);
             }

             return maxLen;
         }

//
//

         public int longestStrChain2(String[] words) {
             // sort by word length
             Arrays.sort(words, (a, b) -> a.length() - b.length());

             // map: word → longest chain ending at this word
             Map<String, Integer> dp = new HashMap<>();

             int maxLen = 1;

             for (String word : words) {
                 dp.put(word, 1);   // chain of length 1 by default

                 // try removing each character from word
                 for (int i = 0; i < word.length(); i++) {
                     // predecessor = word with char at i removed
                     String predecessor = word.substring(0, i)
                             + word.substring(i + 1);

                     if (dp.containsKey(predecessor)) {
                         int chainLen = dp.get(predecessor) + 1;
                         dp.put(word, Math.max(dp.get(word), chainLen));
                     }
                 }

                 maxLen = Math.max(maxLen, dp.get(word));
             }

             return maxLen;
         }

////`words = ["a","b","ba","bca","bda","bdca"]`




// Longest Biotonic Subsequence

         public int longestBitonicSubsequence(int[] nums) {
             int n = nums.length;

             // single array of size 2n
             // dp[i]   = LIS from left  ending   at i
             // dp[n+i] = LIS from right starting at i
             int[] dp = new int[2 * n];
             Arrays.fill(dp, 1);

             // Step 1: fill dp[0..n-1] → LIS from LEFT
             for (int i = 1; i < n; i++)
                 for (int j = 0; j < i; j++)
                     if (nums[j] < nums[i])
                         dp[i] = Math.max(dp[i], 1 + dp[j]);

             // Step 2: fill dp[n..2n-1] → LIS from RIGHT
             for (int i = n - 2; i >= 0; i--)
                 for (int j = i + 1; j < n; j++)
                     if (nums[j] < nums[i])
                         dp[n + i] = Math.max(dp[n + i], 1 + dp[n + j]);

             // Step 3: find max bitonic length
             int maxLen = 1;
             for (int i = 0; i < n; i++)
                 if (dp[i] > 1 && dp[n + i] > 1)
                     maxLen = Math.max(maxLen, dp[i] + dp[n + i] - 1);

             return maxLen;
         }


    ////### Mapping Visualised
    ////```
    ////     Single array dp[2n]:
    ////
    ////  ┌────────────────────────────────────────────┐
    ////  │  dp[0]  dp[1]  dp[2] ... dp[n-1]          │  ← LIS from LEFT
    ////  │  dp[n]  dp[n+1]      ... dp[2n-1]         │  ← LIS from RIGHT
    ////  └────────────────────────────────────────────┘
    ////
    ////     For index i:
    ////     Left  part → dp[i]
    ////     Right part → dp[n + i]
    ////
    ////     Bitonic at peak i:
    ////     dp[i] + dp[n+i] - 1
    /// ```

//
//    Given an integer array nums, return the number of longest increasing subsequences in the array.
//
//    A subsequence does not need to be contiguous.
//
//    Example
//    nums = [1, 3, 5, 4, 7]
//
//    Longest increasing subsequences are:
//
//            1 → 3 → 5 → 7
//            1 → 3 → 4 → 7
//
//    Both have length 4.
//
//    So the answer is: 2


public int findNumberOfLIS(int[] nums) {
    int n = nums.length;

    // len[i]   = length of LIS ending at i
    // count[i] = number of LIS of length len[i] ending at i
    int[] len = new int[n];
    int[] count = new int[n];

    Arrays.fill(len, 1);
    Arrays.fill(count, 1);

    int maxLen = 1;

    for (int i = 0; i < n; i++) {

        for (int j = 0; j < i; j++) {

            if (nums[j] < nums[i]) {

                // Found a LONGER LIS ending at i
                if (len[j] + 1 > len[i]) {
                    len[i] = len[j] + 1;
                    count[i] = count[j];
                }

                // Found ANOTHER LIS of same length
                else if (len[j] + 1 == len[i]) {
                    count[i] += count[j];
                }
            }
        }

        maxLen = Math.max(maxLen, len[i]);
    }

    // Count all LIS having global maximum length
    int answer = 0;

    for (int i = 0; i < n; i++) {
        if (len[i] == maxLen) {
            answer += count[i];
        }
    }

    return answer;
}

}