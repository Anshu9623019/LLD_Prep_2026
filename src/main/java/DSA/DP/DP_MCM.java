package DSA.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DP_MCM {

//All MCM problems share same structure:
//
//solve(i, j) = optimal answer for subarray/subproblem [i.j]
//
//Try every partition point k between i and j:
//answer = best of all:
//solve(i, k) + solve(k+1, j) + cost(i, k, j)
//
//Base case: i == j → 0 (single element)


//Problem Statement
//
//Given dimensions array arr[] where matrix i has dimensions
//arr[i-1] × arr[i], find the minimum number of multiplications to multiply all matrices.
//
//Input:  arr = [10, 20, 30, 40, 30]
//Output: 30000
//Reason: Optimal: (A1(A2A3))A4


//Matrix i has dimensions: arr[i-1] × arr[i]
//Cost of multiplying (i..k) × (k+1..j):
//        = arr[i-1] × arr[k] × arr[j]
//
//Try all partition points k from i to j-1
//Take minimum cost


    private int solve(int i, int j, int[] arr) {
        // base: single matrix → no multiplication needed
        if (i == j) return 0;

        int minCost = Integer.MAX_VALUE;

        // try every partition point k
        for (int k = i; k < j; k++) {
            int cost = arr[i - 1] * arr[k] * arr[j]  // cost of merging
                    + solve(i, k, arr)              // left part
                    + solve(k + 1, j, arr);           // right part

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    public int matrixMultiplication(int[] arr) {
        int n = arr.length;
        // matrices are 1-indexed: matrix 1 to n-1
        return solve(1, n - 1, arr);
    }


    int[][] memo;

    private int solve1(int i, int j, int[] arr) {
        if (i == j) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int cost = arr[i - 1] * arr[k] * arr[j]
                    + solve(i, k, arr)
                    + solve(k + 1, j, arr);

            minCost = Math.min(minCost, cost);
        }

        return memo[i][j] = minCost;
    }

    public int matrixMultiplication1(int[] arr) {
        int n = arr.length;
        memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);
        return solve1(1, n - 1, arr);
    }


    public int matrixMultiplication2(int[] arr) {

        int n = arr.length;

        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 1; i--) {

            for (int j = i + 1; j < n; j++) {

                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {

                    int cost =
                            dp[i][k]
                                    + dp[k + 1][j]
                                    + arr[i - 1] * arr[k] * arr[j];

                    dp[i][j] =
                            Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][n - 1];
    }

    // This will also work.

//    for (int i = n - 2; i >= 1; i--) {
//        for (int j = i + 1; j < n; j++) {
//        ...
//        }
//    }

//       j
//       1    2    3    4
//
//    i=1    0    ?    ?    ?
//    i=2         0    ?    ?
//    i=3              0    ?
//    i=4                   0


//Problem Statement
//
//You have a stick of length n. Given an array cuts[] of positions where you can cut the stick, the cost of a cut is the length of the stick being cut.
//Return the minimum total cost of all cuts.
//
//Input:  n = 7, cuts = [1, 3, 4, 5]
//Output: 16
//Reason: Cut at 3 (cost 7) → [0,3] and [3,7]
//Cut at 5 (cost 4) → [3,5] and [5,7]
//Cut at 1 (cost 3) → [0,1] and [1,3]
//Cut at 4 (cost 2) → [3,4] and [4,5]
//Total = 7+4+3+2 = 16
//
//Key Insight — MCM Pattern
//Add boundaries 0 and n to cuts array:
//cuts = [0, 1, 3, 4, 5, 7]  (sorted with boundaries)
//
//Now for range [i, j] in cuts array:
//cost of any cut k between i and j
//  = cuts[j] - cuts[i]   ← length of current stick piece
//
//Try every cut k as the FIRST cut in range [i,j]:
//solve(i, j) = min over all k:
//        (cuts[j] - cuts[i]) + solve(i,k) + solve(k,j)
//         ↑                    ↑            ↑
//cut cost            left part    right part
//
//Base case: j - i <= 1 → no cuts possible → return 0
//
//Why Add Boundaries?
//cuts = [1, 3, 4, 5], n = 7
//
//Without boundaries:
//First cut in [1,5] → cost is unknown (don't know stick length)
//
//                  With boundaries [0, 1, 3, 4, 5, 7]:
//                  First cut in range i=0,j=5 → cost = cuts[5]-cuts[0] = 7-0 = 7 ✅
//                Clearly represents the full stick length
//
//              Solution 1: Recursion (Brute Force)

    private int solve2(int i, int j, int[] cuts) {
        // base: no cuts possible between i and j
        if (j - i <= 1) return 0;

        int minCost = Integer.MAX_VALUE;

        // try every cut point k between i and j
        for (int k = i + 1; k < j; k++) {
            int cost = (cuts[j] - cuts[i])   // cost of this cut
                    + solve2(i, k, cuts)      // left part
                    + solve2(k, j, cuts);     // right part

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    public int minCost(int n, int[] cuts) {
        int c = cuts.length;

        // add boundaries 0 and n
        int[] arr = new int[c + 2];
        arr[0] = 0;
        arr[c + 1] = n;
        for (int i = 0; i < c; i++) arr[i + 1] = cuts[i];

        // sort cuts (including boundaries)
        Arrays.sort(arr);

        return solve(0, arr.length - 1, arr);
    }

//Time: O(3^n) | Space: O(n) stack


    int[][] memo6;

    private int solve9(int i, int j, int[] cuts) {
        // base: no cuts possible
        if (j - i <= 1) return 0;

        if (memo[i][j] != -1) return memo[i][j];

        int minCost = Integer.MAX_VALUE;

        for (int k = i + 1; k < j; k++) {
            int cost = (cuts[j] - cuts[i])
                    + solve9(i, k, cuts)
                    + solve9(k, j, cuts);

            minCost = Math.min(minCost, cost);
        }

        return memo6[i][j] = minCost;
    }


//Time: O(n³) | Space: O(n²)


    public int minCost4(int n, int[] cuts) {

        int c = cuts.length;

        // Add boundaries
        int[] arr = new int[c + 2];

        arr[0] = 0;
        arr[c + 1] = n;

        for (int i = 0; i < c; i++) {
            arr[i + 1] = cuts[i];
        }

        // Sort boundaries + cuts
        Arrays.sort(arr);

        int[][] dp = new int[c + 2][c + 2];

        // Same structure as MCM
        for (int i = c; i >= 0; i--) {

            for (int j = i + 1; j < c + 2; j++) {

                dp[i][j] = Integer.MAX_VALUE;

                // Try every possible cut k
                for (int k = i + 1; k < j; k++) {

                    int cost =
                            dp[i][k]
                                    + dp[k][j]
                                    + (arr[j] - arr[i]);

                    dp[i][j] =
                            Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][c + 1];
    }

//        ### MCM vs Minimum Cut Cost
//```
//MCM                    Min Cost to Cut
//────────────────────────────────────────────────────────────
//Array          dimensions arr[]        cuts[] + boundaries
//Range          matrix indices          cut positions
//Partition k    split matrices          cut position
//Cost formula   arr[i-1]*arr[k]*arr[j]  arr[j]-arr[i]
//Goal           minimize multiplications minimize cut cost
//Base           i==j → 0               j-i<=1 → 0
//        ```
//
//        ---
//
//        ### Complexity Summary
//
//|        Approach | Time | Space |
//        |---|---|---|
//        | Recursion | `O(3^n)` | `O(n)` |
//        | Memoization | `O(n³)` | `O(n²)` |
//        | Tabulation | `O(n³)` | `O(n²)` |
//
//        ---
//
//        ### Key Takeaways
//```
//        1. Add boundaries 0 and n to cuts array
//        2. Sort the augmented array
//        3. MCM pattern: try every k as FIRST cut in [i,j]
//        4. Cost = arr[j]-arr[i] (length of current stick)
//        5. Base: j-i<=1 → return 0 (no cuts possible)
//        6. Answer: dp[0][len-1]
//
//          Trick to remember:
//          MCM  → arr[i-1] * arr[k] * arr[j]   (product)
//          Cut Stick    → arr[j] - arr[i]               (difference)
//          Both follow same solve(i,k) + solve(k+1,j) template
//


    //3. Bollon Burst


//Burst Balloons — Deep Dive
//
//Problem Statement
//
//Given n balloons with values nums[], bursting balloon i gives
//nums[i-1] × nums[i] × nums[i+1] coins.
//        After bursting, the adjacent balloons become neighbors.
//Return maximum coins you can collect.
//
//Input:  nums = [3, 1, 5, 8]
//Output: 167
//Reason: Burst 1  → 3×1×5  = 15,  remaining [3,5,8]
//Burst 5  → 3×5×8  = 120, remaining [3,8]
//Burst 3  → 1×3×8  = 24,  remaining [8]
//Burst 8  → 1×8×1  = 8,   remaining []
//        Total = 15+120+24+8 = 167


//Solution: Think BACKWARDS
//Instead of "which to burst FIRST"
//Think "which to burst LAST" in range [i,j]
//
//      Key Insight — Burst LAST
//      If balloon k is burst LAST in range [i,j]:
//      All other balloons in [i,j] already gone
//      Only boundaries arr[i-1] and arr[j+1] remain
//
//      Coins from bursting k last:
//        = arr[i-1] × arr[k] × arr[j+1]
//
//      Left  subproblem [i, k-1] solved independently
//      Right subproblem [k+1, j] solved independently
//
//      → No dependency between subproblems! ✅
//
//      Boundary Setup
//      Add virtual balloons of value 1 at both ends:
//      nums = [3, 1, 5, 8]
//      arr  = [1, 3, 1, 5, 8, 1]
//              0  1  2  3  4  5
//
//Why?
//Bursting leftmost balloon → no left neighbor  → treat as 1
//Bursting rightmost balloon → no right neighbor → treat as 1


//Recurrence
//solve(i, j) = max coins from bursting all balloons in [i,j]
//
//        for k = i to j:    // k = last balloon to burst
//        coins = arr[i-1] * arr[k] * arr[j+1]   // burst k last
//        + solve(i, k-1)                   // left subproblem
//          + solve(k+1, j)                   // right subproblem
//
//maxCoins = max(maxCoins, coins)
//
//Base: i > j → return 0 (no balloons)

//Solution 1: Recursion (Brute Force)

    int[] arr;

    private int solve3(int i, int j) {
        // base: no balloons in range
        if (i > j) return 0;

        int maxCoins = 0;

        // try every balloon k as LAST to burst in [i,j]
        for (int k = i; k <= j; k++) {
            // coins from bursting k last
            int coins = arr[i - 1] * arr[k] * arr[j + 1]
                    + solve3(i, k - 1)    // left
                    + solve3(k + 1, j);  // right

            maxCoins = Math.max(maxCoins, coins);
        }

        return maxCoins;
    }

    public int maxCoins1(int[] nums) {
        int n = nums.length;

        // add virtual boundaries
        arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        for (int i = 1; i <= n; i++) arr[i] = nums[i - 1];

        return solve3(1, n);
    }
//Time: O(n! × n) | Space: O(n) stack

//Solution 2: Memoization (Top-Down) ✅

    int[][] memo1;
    int[] arr1;

    private int solve4(int i, int j) {
        if (i > j) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int maxCoins = 0;

        for (int k = i; k <= j; k++) {
            int coins = arr[i - 1] * arr[k] * arr[j + 1]
                    + solve4(i, k - 1)
                    + solve4(k + 1, j);

            maxCoins = Math.max(maxCoins, coins);
        }

        return memo[i][j] = maxCoins;
    }

    public int maxCoins2(int[] nums) {
        int n = nums.length;
        arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        for (int i = 1; i <= n; i++) arr[i] = nums[i - 1];

        memo = new int[n + 2][n + 2];
        for (int[] row : memo) Arrays.fill(row, -1);

        return solve4(1, n);
    }


    public int maxCoins(int[] nums) {

        int n = nums.length;

        // Add virtual boundaries
        int[] arr = new int[n + 2];

        arr[0] = 1;
        arr[n + 1] = 1;

        for (int i = 1; i <= n; i++) {
            arr[i] = nums[i - 1];
        }

        // dp[i][j] =
        // maximum coins from bursting balloons i...j
        int[][] dp = new int[n + 2][n + 2];

        // i -> backward
        for (int i = n; i >= 1; i--) {

            // j -> forward
            for (int j = i; j <= n; j++) {

                // Try every balloon k as the LAST balloon to burst
                for (int k = i; k <= j; k++) {

                    int coins =
                            dp[i][k - 1]
                                    + dp[k + 1][j]
                                    + arr[i - 1] * arr[k] * arr[j + 1];

                    dp[i][j] = Math.max(dp[i][j], coins);
                }
            }
        }

        return dp[1][n];
    }


// Forward vs Backward Thinking


//        1. Think LAST burst, not FIRST burst
//        2. Add virtual boundaries arr[-1]=arr[n]=1
//        3. k = last balloon to burst in [i,j]
//        4. Cost = arr[i-1] * arr[k] * arr[j+1]
//        5. Subproblems independent → no overlap
//        6. Base: i>j → return 0
//        7. Answer: dp[1][n]


//      MCM: arr[i-1] * arr[k]   * arr[j]    (matrix dims)
//      Burst Ballons: arr[i-1] * arr[k]   * arr[j+1]  (boundaries)


// Boolean Expresion

//Problem Statement
//
//Given a boolean expression string with operands T/F and operators &, |, ^,
//find the number of ways to parenthesize the expression so it evaluates to True.
//
//Input:  expr = "T|T&F^T"
//Output: 4
//Reason: 4 different parenthesizations evaluate to True
//
//Expression Structure
//expr = "T | T & F ^ T"
//idx:    0  1  2  3  4  5  6
//
//Operands  → even indices: 0, 2, 4, 6
//Operators → odd  indices: 1, 3, 5
//
//Always alternates: operand op operand op operand ...
//
//Key Insight — MCM Pattern
//Try every operator as the ROOT (last operation):
//
//For each operator at index k (odd indices):
//Split expression into LEFT and RIGHT parts
//Count ways LEFT = T, LEFT = F
//Count ways RIGHT = T, RIGHT = F
//Combine based on operator

//solve(i, j, isTrue) = number of ways expr[i..j] = isTrue
//
//Operator Truth Tables
//AND (&):
//T & T = T     F & T = F
//T & F = F     F & F = F
//ways(True)  = LT * RT
//ways(False) = LT * RF + LF * RT + LF * RF
//
//OR  (|):
//T | T = T     F | T = T
//T | F = T     F | F = F
//ways(True)  = LT * RT + LT * RF + LF * RT
//ways(False) = LF * RF
//
//XOR (^):
//T ^ T = F     F ^ T = T
//T ^ F = T     F ^ F = F
//ways(True)  = LT * RF + LF * RT
//ways(False) = LT * RT + LF * RF


//LT = left  evaluates to True
//LF = left  evaluates to False
//RT = right evaluates to True
//RF = right evaluates to False

//Solution 1: Recursion (Brute Force)

    private int solve6(int i, int j, boolean isTrue, String expr) {
        // base: single operand
        if (i == j) {
            if (isTrue) return expr.charAt(i) == 'T' ? 1 : 0;
            else return expr.charAt(i) == 'F' ? 1 : 0;
        }

        // base: invalid range
        if (i > j) return 0;

        int ways = 0;

        // try every operator as root
        // operators at odd indices: i+1, i+3, i+5 ...
        for (int k = i + 1; k < j; k += 2) {
            long lt = solve6(i, k - 1, true, expr);  // left  = True
            long lf = solve6(i, k - 1, false, expr);  // left  = False
            long rt = solve6(k + 1, j, true, expr);  // right = True
            long rf = solve6(k + 1, j, false, expr);  // right = False

            char op = expr.charAt(k);

            if (op == '&') {
                if (isTrue) ways += lt * rt;
                else ways += lt * rf + lf * rt + lf * rf;
            } else if (op == '|') {
                if (isTrue) ways += lt * rt + lt * rf + lf * rt;
                else ways += lf * rf;
            } else if (op == '^') {
                if (isTrue) ways += lt * rf + lf * rt;
                else ways += lt * rt + lf * rf;
            }
        }

        return ways;
    }

    public int countWays3(String expr) {
        int n = expr.length();
        return solve6(0, n - 1, true, expr);
    }

//Time: O(3^n) | Space: O(n) stack

//Solution 2: Memoization (Top-Down) ✅
//javaclass Solution {
    /// / key = "i_j_isTrue"
    Map<String, Long> memo3 = new HashMap<>();

    private long solve(int i, int j, boolean isTrue, String expr) {
        // base cases
        if (i > j) return 0;
        if (i == j) {
            if (isTrue) return expr.charAt(i) == 'T' ? 1 : 0;
            else return expr.charAt(i) == 'F' ? 1 : 0;
        }

        String key = i + "_" + j + "_" + isTrue;
        if (memo3.containsKey(key)) return memo3.get(key);

        long ways = 0;

        for (int k = i + 1; k < j; k += 2) {
            long lt = solve(i, k - 1, true, expr);
            long lf = solve(i, k - 1, false, expr);
            long rt = solve(k + 1, j, true, expr);
            long rf = solve(k + 1, j, false, expr);

            char op = expr.charAt(k);

            if (op == '&') {
                if (isTrue) ways += lt * rt;
                else ways += lt * rf + lf * rt + lf * rf;
            } else if (op == '|') {
                if (isTrue) ways += lt * rt + lt * rf + lf * rt;
                else ways += lf * rf;
            } else if (op == '^') {
                if (isTrue) ways += lt * rf + lf * rt;
                else ways += lt * rt + lf * rf;
            }
        }

        memo3.put(key, ways);
        return ways;
    }

    public int countWays(String expr) {
        return (int) solve(0, expr.length() - 1, true, expr);
    }


//`       | Approach | Time | Space |
//        |---|---|---|
//        | Recursion | `O(3^n)` | `O(n)` |
//        | Memoization | `O(n³)` | `O(n²)` |
//        | Tabulation | `O(n³)` | `O(n²)` |
//
//        ---
//
//        ### Key Takeaways
//```
//        1. Operands at EVEN indices, operators at ODD indices
//        2. Try every operator k as ROOT of expression
//        3. Compute LT, LF, RT, RF for each split
//        4. Combine based on operator:
//        &: True  = LT*RT
//        False = LT*RF + LF*RT + LF*RF
//        |: True  = LT*RT + LT*RF + LF*RT
//        False = LF*RF
//        ^: True  = LT*RF + LF*RT
//        False = LT*RT + LF*RF
//        5. Base: single operand → 1 if matches isTrue, else 0
//        6. Answer: dp[0][n-1][1]
//
//          MCM connection:
//          MCM         → try every split → min cost
//          Burst Balls → try every last  → max coins
//          Bool Expr   → try every op    → count ways
//          All use: solve(i,k) + solve(k+1,j) + combine()
//


//
//Problem Statement
//
//Given a string s, partition it such that every substring is a palindrome.
//Return the minimum number of cuts needed.
//
//        Input:  s = "aabbc"
//Output: 3
//Reason: "a|a|bb|c" → 3 cuts  ✅
//        "aa|b|b|c" → 3 cuts  ✅
//
//Key Insight — MCM Pattern
//Try every cut point k in range [i,j]:
//If s[i..k] is palindrome:
//cuts = 1 + solve(k+1, j)
//
//Answer = minimum cuts over all valid k
//
//Base:
//If s[i..j] is already palindrome → 0 cuts needed
//
//Two Steps
//Step 1: Precompute isPalin[i][j]
//        → is s[i..j] a palindrome?
//
//Step 2: DP to find minimum cuts
//        → try every partition point
//
//isPalin Precomputation
//isPalin[i][j] = true if s[i..j] is palindrome
//
//Recurrence:
//        if s[i] == s[j]:
//        if j-i <= 2:           → always palindrome ("a", "aa", "aba")
//isPalin[i][j] = true
//        else:
//isPalin[i][j] = isPalin[i+1][j-1]
//        else:
//isPalin[i][j] = false
//
//Solution 1: Recursion (Brute Force)


    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    private int solve8(int i, int n, String s) {
        // base: reached end
        if (i == n) return 0;

        int minCuts = Integer.MAX_VALUE;

        // try every end point j
        for (int j = i; j < n; j++) {
            if (isPalindrome(s, i, j)) {
                int cuts = 1 + solve8(j + 1, n, s);
                minCuts = Math.min(minCuts, cuts);
            }
        }

        return minCuts;
    }

    public int minCut4(String s) {
        // subtract 1: last partition needs no cut
        return solve8(0, s.length(), s) - 1;
    }

// Time: O(n² × 2^n) | Space: O(n) stack


// Tabulation (Bottom-Up)

    public int minCut(String s) {
        int n = s.length();

        // Step 1: precompute isPalin[i][j]
        boolean[][] isPalin = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j))
                    isPalin[i][j] = (j - i <= 2) || isPalin[i + 1][j - 1];
            }
        }

        // Step 2: dp[i] = min cuts for s[i..n-1]
        int[] dp = new int[n + 1];
        dp[n] = 0;   // base: empty string needs 0 cuts

        for (int i = n - 1; i >= 0; i--) {
            int minCuts = Integer.MAX_VALUE;

            for (int j = i; j < n; j++) {
                if (isPalin[i][j]) {
                    int cuts = 1 + dp[j + 1];
                    minCuts = Math.min(minCuts, cuts);
                }
            }

            dp[i] = minCuts;
        }

        // subtract 1: no cut needed for last partition
        return dp[0] - 1;
    }

//        ```
//
//        **Time:** `O(n²)` | **Space:** `O(n²)` for isPalin + `O(n)` for dp
//
//        ### Detailed Dry Run
//
//        `s = "aabbc"`
//
//        ---
//
//        **Step 1 — Precompute isPalin[][]:**
//        ```
//      s = "a a b b c"
//      idx: 0 1 2 3 4
//
//          Fill from bottom-right to top-left:
//
//          i=4: isPalin[4][4] = true  (single 'c')
//
//          i=3: isPalin[3][3] = true  (single 'b')
//          isPalin[3][4]: s[3]='b' != s[4]='c' → false
//
//          i=2: isPalin[2][2] = true  (single 'b')
//          isPalin[2][3]: s[2]='b' == s[3]='b', j-i=1<=2 → true  ✅
//          isPalin[2][4]: s[2]='b' != s[4]='c' → false
//
//          i=1: isPalin[1][1] = true  (single 'a')
//          isPalin[1][2]: s[1]='a' != s[2]='b' → false
//          isPalin[1][3]: s[1]='a' != s[3]='b' → false
//          isPalin[1][4]: s[1]='a' != s[4]='c' → false
//
//          i=0: isPalin[0][0] = true  (single 'a')
//          isPalin[0][1]: s[0]='a' == s[1]='a', j-i=1<=2 → true  ✅
//          isPalin[0][2]: s[0]='a' != s[2]='b' → false
//          isPalin[0][3]: s[0]='a' != s[3]='b' → false
//          isPalin[0][4]: s[0]='a' != s[4]='c' → false
//
//        isPalin table:
//              0     1     2     3     4
//        0  [  T,    T,    F,    F,    F  ]
//        1  [  F,    T,    F,    F,    F  ]
//        2  [  F,    F,    T,    T,    F  ]
//        3  [  F,    F,    F,    T,    F  ]
//        4  [  F,    F,    F,    F,    T  ]
//        ```


//        **Step 2 — Fill dp[] (right to left):**
//        ```
//        dp[5] = 0   ← base case


//        i=4, s[4..4]="c":
//        j=4: isPalin[4][4]=T → cuts = 1+dp[5] = 1+0 = 1
//        dp[4] = 1


//      i=3, s[3..4]="bc":
//      j=3: isPalin[3][3]=T → cuts = 1+dp[4] = 1+1 = 2
//      j=4: isPalin[3][4]=F → skip
//      dp[3] = 2


//      i=2, s[2..4]="bbc":
//      j=2: isPalin[2][2]=T → cuts = 1+dp[3] = 1+2 = 3
//      j=3: isPalin[2][3]=T → cuts = 1+dp[4] = 1+1 = 2  ✅
//      j=4: isPalin[2][4]=F → skip
//      dp[2] = min(3,2) = 2


//        i=1, s[1..4]="abbc":
//        j=1: isPalin[1][1]=T → cuts = 1+dp[2] = 1+2 = 3
//        j=2: isPalin[1][2]=F → skip
//        j=3: isPalin[1][3]=F → skip
//        j=4: isPalin[1][4]=F → skip
//        dp[1] = 3


//        i=0, s[0..4]= "aabbc"
//        j=0: isPalin[0][0]=T → cuts = 1+dp[1] = 1+3 = 4
//        j=1: isPalin[0][1]=T → cuts = 1+dp[2] = 1+2 = 3  ✅
//        j=2: isPalin[0][2]=F → skip
//        j=3: isPalin[0][3]=F → skip
//        j=4: isPalin[0][4]=F → skip


//        dp[0] = min(4,3) = 3 (wait: wrong)
//        Actually wait:
//        j=0: isPalin[0][0]=T → 1+dp[1]=4
//        j=1: isPalin[0][1]=T → 1+dp[2]=3
//        dp[0] = 3

//        Answer: dp[0] - 1 = 3 - 1 = wait...


//dp[5] = 0
//dp[4] = 1    → "c"       → 1 partition
//dp[3] = 2    → "b|c"     → 2 partitions
//dp[2] = 2    → "bb|c"    → 2 partitions  (bb is palindrome)
//dp[1] = 3    → "a|bb|c"  → 3 partitions
//dp[0] = 3    → "aa|bb|c" → 3 partitions
//
//Answer = dp[0]-1 = 3-1 = 2
//
//        "aa|bb|c" → 2 cuts
//
//

//
//        ### Complexity Summary
//
//| Approach | Time | Space |
//        |---|---|---|
//        | Recursion | `O(n² × 2^n)` | `O(n)` |
//        | Memoization | `O(n²)` | `O(n²)` |
//        | **Tabulation** | **`O(n²)`** | **`O(n²)`**  |
//
//        ---
//
//        ### Key Takeaways


//       1. Two steps:
//          a) Precompute isPalin[i][j] → O(n²)
//          b) DP for min cuts          → O(n²)
//
//       2. isPalin[i][j]:
//          s[i]==s[j] AND (j-i<=2 OR isPalin[i+1][j-1])
//
//       3. dp[i] = min partitions for s[i.n-1]
//        if isPalin[i][j]: dp[i] = min(dp[i], 1+dp[j+1])
//
//       4. Answer = dp[0] - 1
//        (partitions - 1 = cuts)
//       5. MCM connection:
//        try every j as end of first palindrome partition
//     = try every cut point
//     = same MCM template


//Problem Statement
//
//          Given an integer array arr and integer k, partition the array into contiguous subarrays of length at most k.
//          After partitioning, replace every element in each subarray with the maximum value of that subarray.
//          Return the maximum sum possible.
//
//        Input:  arr = [1, 15, 7, 9, 2, 5, 10], k = 3
//          Output: 84
//          Reason: [15,15,15 | 9 | 10,10,10]
//          15*3 + 9*1 + 10*3 = 45+9+30 = 84
//
//Key Insight — MCM Pattern
//At every index i, try all partition lengths from 1 to k:
//For each length len (1 to k):
//Take subarray arr[i..i+len-1]
//maxVal = max of arr[i..i+len-1]
//contribution = maxVal * len
//        remaining = solve(i+len)
//
//Answer = max over all valid lengths
//
//Base: i == n → return 0
//
//Recurrence
//solve(idx) = max sum from arr[idx..n-1]
//
//maxVal = 0
//        for len = 1 to k (and idx+len-1 < n):
//maxVal = max(maxVal, arr[idx+len-1])
//sum    = maxVal * len + solve(idx+len)
//
//return max sum

    private int solve9(int idx, int[] arr, int k) {
        int n = arr.length;

        // base: reached end
        if (idx == n) return 0;

        int maxVal = 0;
        int maxSum = 0;

        // try all partition lengths 1 to k
        for (int len = 1; len <= k && idx + len - 1 < n; len++) {
            maxVal = Math.max(maxVal, arr[idx + len - 1]);

            int sum = maxVal * len          // contribution of this partition
                    + solve9(idx + len, arr, k);  // remaining array

            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public int maxSumAfterPartitioning4(int[] arr, int k) {
        return solve9(0, arr, k);
    }

//Time: O(n × k^n) | Space: O(n) stack

//Solution 2: Memoization (Top-Down)

    int[] memo4;

    private int solve(int idx, int[] arr, int k) {
        int n = arr.length;
        if (idx == n) return 0;
        if (memo4[idx] != -1) return memo4[idx];

        int maxVal = 0;
        int maxSum = 0;

        for (int len = 1; len <= k && idx + len - 1 < n; len++) {
            maxVal = Math.max(maxVal, arr[idx + len - 1]);

            int sum = maxVal * len
                    + solve(idx + len, arr, k);

            maxSum = Math.max(maxSum, sum);
        }

        return memo4[idx] = maxSum;
    }

    public int maxSumAfterPartitioning3(int[] arr, int k) {
        int n = arr.length;
        memo4 = new int[n];
        Arrays.fill(memo4, -1);
        return solve(0, arr, k);
    }

//Time: O(n × k) | Space: O(n)

//Solution 3: Tabulation (Bottom-Up) ✅

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];

        // base: dp[n] = 0
        // fill from right to left
        for (int idx = n - 1; idx >= 0; idx--) {
            int maxVal = 0;
            int maxSum = 0;

            for (int len = 1; len <= k && idx + len - 1 < n; len++) {
                // expand window, track max
                maxVal = Math.max(maxVal, arr[idx + len - 1]);

                // contribution + future
                int sum = maxVal * len + dp[idx + len];
                maxSum = Math.max(maxSum, sum);
            }

            dp[idx] = maxSum;
        }

        return dp[0];
    }
}


//        ### MCM Problems Comparison
//```
//Problem                Template              Goal
//─────────────────────────────────────────────────────────────────
//MCM                    solve(i,k)+solve(k+1,j)  min multiplications
//Burst Balloons         solve(i,k-1)+solve(k+1,j) max coins
//Cut Stick              solve(i,k)+solve(k,j)    min cut cost
//Palindrome Part        solve(j+1)               min cuts
//Boolean Expr           solve(i,k-1)+solve(k+1,j) count ways
//Partition Max Sum      solve(idx+len)           max sum


