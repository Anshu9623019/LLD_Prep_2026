package DSA.DP;
//
public class DP_SubsetSum {


    public static void main(String[] args) {

    }
        public static boolean subsetSum ( int arr[], int k, int i){
            if (i < 0) {
                return false;
            }
            if (k == 0) {
                return true;
            }
            boolean take = false;
            if (arr[i] <= k) {
                take = subsetSum(arr, k - arr[i], i - 1);
            }
            boolean nonTake = subsetSum(arr, k, i - 1);
            return nonTake || take;
        }

        //Tabulation
        public static boolean subSet1 ( int arr[], int k){
            int n = arr.length;
            boolean dp[][] = new boolean[n][k + 1];

            //for k=0, target sum will be true
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }
            if (arr[0] <= k) {
                dp[0][arr[0]] = true;
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    boolean skip = dp[i - 1][j];
                    boolean take = false;
                    if (arr[i] <= j) {
                        take = dp[i - 1][j - arr[i]];
                    }
                    dp[i][j] = skip || take;
                }
            }
            return dp[n - 1][k];
        }

        //Space optimization
        boolean subsetSum1 ( int arr[], int k){
            int n = arr.length;
            boolean prev[] = new boolean[k + 1];

            //for k=0, target sum will be true
            for (int i = 0; i < n; i++) {
                prev[0] = true;
            }
            if (arr[0] <= k) {
                prev[arr[0]] = true;
            }

            for (int i = 1; i < n; i++) {
                boolean[] curr = new boolean[k + 1]; // ← inside loop
                curr[0] = true;
                for (int j = 1; j <= k; j++) {
                    boolean skip = prev[j];
                    boolean take = false;
                    if (arr[i] <= j) {
                        take = prev[j - arr[i]];
                    }
                    curr[j] = skip || take;
                }
                prev = curr;
            }
            return prev[k];
        }


//    ```
//            - Time: **O(n × target)**
//            - Space: **O(target)** — single row
//
//---
//
//        **Full Comparison:**
//
//            | Approach | Time | Space | Notes |
//            |---|---|---|---|
//            | Recursion | O(2^n) | O(n) | TLE for large input |
//            | Memoization | O(n×target) | O(n×target) | Use Boolean not boolean for null check |
//            | Tabulation | O(n×target) | O(n×target) | Most readable |
//            | Space Optimized | O(n×target) | O(target) | Best answer |
//
//            ---
//
//            **The 3 base cases to always remember:**
//            ```
//            1. target == 0  → true  (empty subset always valid)
//            2. i == 0       → arr[0] == target
//3. arr[i] > j   → can only skip, never take


        //  Partition subset sum
        public static boolean partitionSubset ( int arr[]){
            int totalSum = 0;
            for (int ele : arr) {
                totalSum += ele;
            }
            if (totalSum % 2 != 0) {
                return false;
            }

            for (int ele : arr) {
                if (ele > (totalSum / 2)) {
                    return false;
                }
            }
            //divide the totalSum into two equal part and then apply subset sum.
            int s1 = totalSum / 2;
            boolean ans = subSet1(arr, s1);
            return ans;
        }

//    ```
//            - Time: **O(n × target)**
//            - Space: **O(n × target)**
//
//            ---
//
//            **Dry Run:**
//            ```
//    arr = [1, 5, 11, 5]
//    totalSum = 22, target = 11
//
//    dp table (rows = elements, cols = 0..11):
//
//            0  1  2  3  4  5  6  7  8  9  10  11
//    i=0(1)  T  T  F  F  F  F  F  F  F  F   F   F
//    i=1(5)  T  T  F  F  F  T  T  F  F  F   F   F
//    i=2(11) T  T  F  F  F  T  T  F  F  F   F   T ✅
//    i=3(5)  T  T  F  F  F  T  T  F  F  F   T   T
//
//return dp[3][11] = true ✅


        //Minimum Subset Sum Difference
        public int minimumDifference ( int[] arr){
            int n = arr.length;
            int totalSum = 0;
            for (int x : arr) totalSum += x;

            // Step 1 — run subset sum tabulation for target = totalSum
            boolean[][] dp = new boolean[n][totalSum + 1];

            for (int i = 0; i < n; i++) dp[i][0] = true;
            if (arr[0] <= totalSum) dp[0][arr[0]] = true;

            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= totalSum; j++) {
                    boolean skip = dp[i - 1][j];
                    boolean take = (arr[i] <= j) ? dp[i - 1][j - arr[i]] : false;
                    dp[i][j] = skip || take;
                }
            }

            // Step 2 — scan last row for all reachable S1 values
            int minDiff = Integer.MAX_VALUE;
            for (int s1 = 0; s1 <= totalSum / 2; s1++) {
                if (dp[n - 1][s1]) {
                    int s2 = totalSum - s1;
                    minDiff = Math.min(minDiff, s2 - s1); // s2 >= s1 since s1 <= totalSum/2
                }
            }
            return minDiff;
        }

//        - Time: **O(n × totalSum)**
//            - Space: **O(n × totalSum)**
//
//            ---
//
//            **Dry Run:**
//            ```
//    arr = [1, 2, 3, 4], totalSum = 10
//
//    dp last row (all reachable subset sums):
//    sum:  0  1  2  3  4  5  6  7  8  9  10
//    T  T  T  T  T  T  T  T  T  T  T
//            (every sum 0–10 reachable)
//
//    Scan s1 from 0 to 5:
//    s1=0 → |10-0|  = 10
//    s1=1 → |10-2|  = 8
//    s1=2 → |10-4|  = 6
//    s1=3 → |10-6|  = 4
//    s1=4 → |10-8|  = 2
//    s1=5 → |10-10| = 0 ✅
//
//    Answer: 0

        //Count Subset with Sum K
        public static int countSubsetSum ( int arr[], int k, int i){
            if (i == 0) {
                if (k == 0 && arr[0] == 0) return 2; // take or skip zero
                if (k == 0 || arr[0] == k) return 1;
                return 0;
            }

            // NOT just k==0 alone!
            int take = 0;
            if (arr[i] <= k)
                take = countSubsetSum(arr, k - arr[i], i - 1);
            int skip = countSubsetSum(arr, k, i - 1);
            return take + skip;
        }

//    **Why `return 2` when `arr[0]==0 && k==0`:**
//            ```
//    At last element (i=0):
//    arr[0] = 0, k = 0
//
//    Take it  → sum still 0 ✅ valid
//    Skip it  → sum still 0 ✅ valid
//
//    Both are valid subsets → return 2
//            ```
//
//            ---
//
//            **Quick mental checklist for base cases in count problems:**
//            ```
//            if(i == 0):
//            ┌── arr[0] == 0 && k == 0 → return 2  (take or skip)
//            ├── k == 0                → return 1  (skip only)
//            ├── arr[0] == k           → return 1  (take only)
//            └── else                  → return 0  (neither works)


        //Tabulation count subset Sum
        public static int countSubsetSum ( int arr[], int k){
            int n = arr.length;
            int[][] dp = new int[n][k + 1];

            // base case — i=0
            if (arr[0] == 0)
                dp[0][0] = 2;       // take or skip zero
            else
                dp[0][0] = 1;       // only skip (empty subset)

            if (arr[0] != 0 && arr[0] <= k)
                dp[0][arr[0]] = 1;  // take first element

            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= k; j++) {
                    int take = 0;
                    if (arr[i] <= j)
                        take = dp[i - 1][j - arr[i]];
                    int nonTake = dp[i - 1][j];
                    dp[i][j] = take + nonTake;
                }
            }

            return dp[n - 1][k];
        }

//        ```
//                - Time: **O(n × K)**
//            - Space: **O(n × K)**


        //Count SubSet partition
        public int countPartitions ( int[] arr, int D){
            int totalSum = 0;
            for (int x : arr) totalSum += x;
            if ((totalSum + D) % 2 != 0) return 0;
            if (D > totalSum) return 0;

            int n = arr.length;
            int target = (totalSum + D) / 2;
            int[][] dp = new int[n][target + 1];

            // base case i=0
            if (arr[0] == 0)
                dp[0][0] = 2;
            else
                dp[0][0] = 1;

            if (arr[0] != 0 && arr[0] <= target)
                dp[0][arr[0]] = 1;

            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= target; j++) {
                    int skip = dp[i - 1][j];
                    int take = 0;
                    if (arr[i] <= j)
                        take = dp[i - 1][j - arr[i]];
                    dp[i][j] = skip + take;
                }
            }

            return dp[n - 1][target];
        }
//```
//        - Time: **O(n × target)**
//            - Space: **O(n × target)**
//            **Dry Run:**
//            ```
//    arr = [1, 1, 2, 3], D = 1
//    totalSum = 7
//    target = (7+1)/2 = 4


        //TargetSum
        public int findTargetSumWays ( int[] arr, int target){
            int totalSum = 0;
            for (int x : arr) totalSum += x;

            if ((totalSum + target) % 2 != 0) return 0;
            if (Math.abs(target) > totalSum) return 0;

            int k = (totalSum + target) / 2;
            if (k < 0) return 0;

            int n = arr.length;
            int[] prev = new int[k + 1];

            // base case
            if (arr[0] == 0)
                prev[0] = 2;
            else
                prev[0] = 1;

            if (arr[0] != 0 && arr[0] <= k)
                prev[arr[0]] = 1;

            for (int i = 1; i < n; i++) {
                int[] curr = new int[k + 1];
                curr[0] = 1;

                for (int j = 0; j <= k; j++) {
                    int skip = prev[j];
                    int take = (arr[i] <= j) ? prev[j - arr[i]] : 0;
                    curr[j] = skip + take;
                }
                prev = curr;
            }

            return prev[k];
        }
//```
//        - Time: **O(n × k)**
//            - Space: **O(k)**
//
//            ---
//
//            **Full Comparison:**
//
//            | Approach | Time | Space | Notes |
//            |---|---|---|---|
//            | Recursion | O(2^n) | O(n) | TLE |
//            | Memoization (HashMap) | O(n×totalSum) | O(n×totalSum) | Handles negative targets |
//            | Tabulation | O(n×k) | O(n×k) | Via Count Partitions reduction |
//            | Space Optimized | O(n×k) | O(k) | Best answer |
//
//            ---
//
//            **Two ways to think about this problem:**
//            ```
//    Way 1 — Direct recursion:
//    assign + → solve(i-1, target - arr[i])
//    assign - → solve(i-1, target + arr[i])
//    Use HashMap memo (target can go negative)
//
//    Way 2 — Reduction (interview preferred):
//    S1 - S2 = target
//            S1 = (totalSum + target) / 2
//  → Count subsets with sum = k
//    Use 2D array memo (target always positive)
//```
//
//        > Way 2 is cleaner in interviews because it reuses known patterns.
//
//---
//
//        **Complete Subset Sum Family — ALL DONE ✅:**
//            ```
//    Subset Sum ✅
//            ├── Partition Equal Subset Sum ✅
//            │       target = totalSum/2
//            │
//            ├── Count Subsets with Sum K ✅
//            │       count instead of exists
//    │
//            ├── Minimum Subset Sum Difference ✅
//            │       scan last row for min|S1-S2|
//            │
//            ├── Count Partitions with Diff D ✅
//            │       target = (totalSum+D)/2, count
//    │
//            └── Target Sum (+ / -) ✅
//            = Count Partitions with D=target
//```
//
//        ---
//
//        **The master reduction chain:**
//            ```
//    Any partition/assignment problem
//        ↓
//    Express as S1 - S2 = something
//        ↓
//    S1 = (totalSum + something) / 2
//            ↓
//    Count Subsets with Sum K
//        ↓
//    Tabulation / Space Optimized DP

    }