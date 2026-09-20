package DSA.DP;
import java.util.Arrays;

public class Knapsack_problems {

    public static void main(String[] args) {

    }

    public int knapsack(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n][W + 1];

        // base — only first item
        for (int j = weights[0]; j <= W; j++)
            dp[0][j] = values[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                int skip = dp[i - 1][j];

                int take = 0;
                if (weights[i] <= j)
                    take = values[i] + dp[i - 1][j - weights[i]];

                dp[i][j] = Math.max(skip, take);
            }
        }
        return dp[n - 1][W];
    }
//```
//        - Time: **O(n × W)**
//            - Space: **O(n × W)**
//
//            ---
//
//            **Dry Run:**
//            ```
//    weights = [1, 3, 4, 5]
//    values  = [1, 4, 5, 7]
//    W = 7
//
//    Base (i=0, weight=1, value=1):
//    j:    0  1  2  3  4  5  6  7
//    i=0:  0  1  1  1  1  1  1  1


    //Unbounded Knapsack, Minimum Coin
    public static int coinChange(int[] coins, int amount) {
        int result = solve(coins, coins.length - 1, amount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int solve(int[] coins, int i, int amount) {
        // base — only one coin left
        if (i == 0) {
            if (amount % coins[0] == 0)
                return amount / coins[0];
            return Integer.MAX_VALUE; // impossible
        }

        // skip current coin
        int skip = solve(coins, i - 1, amount);

        // take current coin (stay at i — unbounded!)
        int take = Integer.MAX_VALUE;
        if (coins[i] <= amount) {
            int res = solve(coins, i, amount - coins[i]); // i not i-1!
            if (res != Integer.MAX_VALUE)
                take = 1 + res;
        }

        return Math.min(skip, take);
    }


    public static int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        int INF = Integer.MAX_VALUE;

        // base — only first coin
        for (int j = 0; j <= amount; j++) {
            if (j % coins[0] == 0)
                dp[0][j] = j / coins[0];
            else
                dp[0][j] = INF; // impossible
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                int skip = dp[i - 1][j];

                int take = INF;
                if (coins[i] <= j && dp[i][j - coins[i]] != INF)
                    take = 1 + dp[i][j - coins[i]]; // dp[i] not dp[i-1]!

                dp[i][j] = Math.min(skip, take);
            }
        }

        return dp[n - 1][amount] == INF ? -1 : dp[n - 1][amount];
    }
//        ```
//coins = [1, 2, 5], amount = 7
//
//Base (i=0, coin=1):
//j:    0  1  2  3  4  5  6  7
//i=0:  0  1  2  3  4  5  6  7
//        (every amount divisible by 1)



    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // base — 0 coins to make amount 0

        for (int i = 0; i < coins.length; i++) {
            // LEFT TO RIGHT — allows reuse of same coin
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

// Coin Change 2

    int solve(int i, int target, int[] coins) {

        if (i == 0) {
            if (target % coins[0] == 0) return 1;
            return 0;
        }

        int notPick = solve(i - 1, target, coins);

        int pick = 0;
        if (coins[i] <= target)
            pick = solve(i, target - coins[i], coins);

        return pick + notPick;
    }


    //tabulation
    int change(int amount, int[] coins) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // base case
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0)
                dp[0][t] = 1;
        }

        for (int i = 1; i < n; i++) {

            for (int t = 0; t <= amount; t++) {

                int notPick = dp[i - 1][t];

                int pick = 0;
                if (coins[i] <= t)
                    pick = dp[i][t - coins[i]];

                dp[i][t] = pick + notPick;
            }
        }

        return dp[n - 1][amount];
    }


    //Space Optimization
    int change1(int amount, int[] coins) {

        int n = coins.length;
        int[] prev = new int[amount + 1];

        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0)
                prev[t] = 1;
        }

        for (int i = 1; i < n; i++) {

            int[] curr = new int[amount + 1];

            for (int t = 0; t <= amount; t++) {

                int notPick = prev[t];

                int pick = 0;
                if (coins[i] <= t)
                    pick = curr[t - coins[i]];

                curr[t] = pick + notPick;
            }

            prev = curr;
        }

        return prev[amount];
    }


//Unbounded KnapSack

    static int[][] memo;

    public static int solve(int i, int W, int[] weight, int[] value) {
        // base case: only item 0 left
        if (i == 0) {
            return (W / weight[0]) * value[0];
        }

        if (memo[i][W] != -1) return memo[i][W];

        // skip item i
        int notTake = solve(i - 1, W, weight, value);

        // take item i (stay at i, not i-1)
        int take = 0;
        if (weight[i] <= W) {
            take = value[i] + solve(i, W - weight[i], weight, value);
        }

        return memo[i][W] = Math.max(take, notTake);
    }

    public static int unboundedKnapsack(int n, int W, int[] weight, int[] value) {
        int[][] dp = new int[n][W + 1];

        // base case: fill row 0 (only item 0 available)
        for (int cap = 0; cap <= W; cap++) {
            dp[0][cap] = (cap / weight[0]) * value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int cap = 0; cap <= W; cap++) {
                // skip item i
                int notTake = dp[i - 1][cap];

                // take item i (stay at row i)
                int take = 0;
                if (weight[i] <= cap) {
                    take = value[i] + dp[i][cap - weight[i]];
                }

                dp[i][cap] = Math.max(take, notTake);
            }
        }

        return dp[n - 1][W];
    }


    public static int unboundedKnapsackOptimised(int n, int W, int[] weight, int[] value) {
        int[] dp = new int[W + 1];

        // base case
        for (int cap = 0; cap <= W; cap++) {
            dp[cap] = (cap / weight[0]) * value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int cap = 0; cap <= W; cap++) {  // left to right (unbounded)
                int notTake = dp[cap];
                int take = 0;
                if (weight[i] <= cap) {
                    take = value[i] + dp[cap - weight[i]]; // dp[i] already updated
                }
                dp[cap] = Math.max(take, notTake);
            }
        }

        return dp[W];
    }

//        ### Dry Run
//
//`weight = [2, 4, 6]`, `value = [5, 11, 13]`, `W = 10`



//Rod Cutting 1

    // i = index (0-based), len = remaining rod length
    static int solve(int i, int len, int[] price, int[][] memo1) {
        // base case: only piece of length 1 available
        if (i == 0) {
            return len * price[0];
        }

        if (memo[i][len] != -1) return memo[i][len];

        // don't cut rod at length (i+1)
        int notTake = solve(i - 1, len, price, memo1);

        // cut rod at length (i+1) if possible
        int take = 0;
        int rodLength = i + 1;
        if (rodLength <= len) {
            take = price[i] + solve(i, len - rodLength, price, memo1);
        }

        return memo[i][len] = Math.max(take, notTake);
    }


    public static int rodCutting(int[] price, int n) {
        int[][] dp = new int[n][n + 1];

        // base case: only rod of length 1 available
        for (int len = 0; len <= n; len++) {
            dp[0][len] = len * price[0];
        }

        for (int i = 1; i < n; i++) {
            int rodLength = i + 1;
            for (int len = 0; len <= n; len++) {
                // don't cut at length (i+1)
                int notTake = dp[i - 1][len];

                // cut at length (i+1)
                int take = 0;
                if (rodLength <= len) {
                    take = price[i] + dp[i][len - rodLength];
                }

                dp[i][len] = Math.max(take, notTake);
            }
        }

        return dp[n - 1][n];
    }


    public static int rodCuttingOptimised(int[] price, int n) {
        int[] dp = new int[n + 1];

        // base case: only rod length 1
        for (int len = 0; len <= n; len++) {
            dp[len] = len * price[0];
        }

        for (int i = 1; i < n; i++) {
            int rodLength = i + 1;
            // left to right — allows reuse (unbounded)
            for (int len = rodLength; len <= n; len++) {
                dp[len] = Math.max(dp[len], price[i] + dp[len - rodLength]);
            }
        }

        return dp[n];
    }
}




