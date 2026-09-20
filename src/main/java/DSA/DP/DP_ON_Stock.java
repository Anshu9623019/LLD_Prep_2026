package DSA.DP;

public class DP_ON_Stock {
    public void main() {
    }

    int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }



//        ## Problem 2 — Buy & Sell Stock II (LC 122)
//
//### Problem
//> **Unlimited transactions**. Find max profit.


//Buy and sell on every profitable consecutive pair
//Sum all positive differences


    static int solve(int day, int canBuy, int[] prices) {
        // base case: no more days
        if (day == prices.length) return 0;

        if (canBuy == 1) {
            // choice 1: buy today
            int buy  = -prices[day] + solve(day + 1, 0, prices);
            // choice 2: skip today
            int skip = solve(day + 1, 1, prices);
            return Math.max(buy, skip);
        } else {
            // choice 1: sell today
            int sell = +prices[day] + solve(day + 1, 1, prices);
            // choice 2: skip today
            int skip = solve(day + 1, 0, prices);
            return Math.max(sell, skip);
        }
    }





//        **Recursion Tree for `[7,1,5,3,6,4]`:**
//        ```
//      solve(0, canBuy=1)
//  ├── BUY  → -7 + solve(1, 0)
//  │              ├── SELL → +1 + solve(2, 1)  ...
//  │              └── SKIP →      solve(2, 0)  ...
//  └── SKIP →      solve(1, 1)
//                   ├── BUY  → -1 + solve(2, 0)
//                   └── SKIP →      solve(2, 1)




    public static int maxProfit1(int[] prices) {
        int n = prices.length;

        // dp[day][canBuy]
        // canBuy=1 → free to buy
        // canBuy=0 → holding stock
        int[][] dp = new int[n + 1][2];

        // base case: day == n → profit = 0
        // already 0 by default

        // fill from right to left (bottom up)
        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    // buy or skip
                    int buy  = -prices[day] + dp[day + 1][0];
                    int skip = dp[day + 1][1];
                    dp[day][canBuy] = Math.max(buy, skip);
                } else {
                    // sell or skip
                    int sell = +prices[day] + dp[day + 1][1];
                    int skip = dp[day + 1][0];
                    dp[day][canBuy] = Math.max(sell, skip);
                }
            }
        }

        // start at day 0, free to buy
        return dp[0][1];
    }
//
//
//```
//
//        **DP Table (filled right to left):**
//        ```
//       canBuy=0  canBuy=1
//      day=6  [0,        0   ]   ← base case
//      day=5  [4,        4   ]   price=4
//      day=4  [6,        6   ]   price=6
//      day=3  [3,        3   ]   price=3
//      day=2  [5,        5   ]   price=5
//      day=1  [6,        6   ]   price=1  ← buy here
//      day=0  [7,        7   ]   price=7
//
//Answer: dp[0][1] = 7  ✅
//
//
public static int maxProfit2(int[] prices) {
    int n = prices.length;

    // only need next day's values
    int[] next = new int[2];
    int[] curr = new int[2];

    // base case: last day → 0 profit
    next[0] = next[1] = 0;

    for (int day = n - 1; day >= 0; day--) {
        for (int canBuy = 0; canBuy <= 1; canBuy++) {
            if (canBuy == 1) {
                int buy  = -prices[day] + next[0]; // buy today
                int skip = next[1];                 // skip today
                curr[canBuy] = Math.max(buy, skip);
            } else {
                int sell = +prices[day] + next[1];  // sell today
                int skip = next[0];                  // skip today
                curr[canBuy] = Math.max(sell, skip);
            }
        }
        next = curr.clone();
    }

    return next[1]; // day=0, canBuy=1
}


public int maxProfit4(int[] prices) {
    int n = prices.length;

    int ahead0 = 0;  // next day, holding stock
    int ahead1 = 0;  // next day, free to buy
    int curr0  = 0;  // today, holding stock
    int curr1  = 0;  // today, free to buy

    for (int day = n - 1; day >= 0; day--) {
        curr1 = Math.max(-prices[day] + ahead0, ahead1); // buy or skip
        curr0 = Math.max(+prices[day] + ahead1, ahead0); // sell or skip
        ahead0 = curr0;
        ahead1 = curr1;
    }

    return ahead1; // day=0, free to buy
}

//Buy and  sell 3 : max 2 transaction


private int buySell3(int day, int canBuy, int cap, int[] prices) {
    // base cases
    if (day == prices.length) return 0;
    if (cap == 0) return 0;             // no transactions left

    if (canBuy == 1) {
        int buy  = -prices[day] + buySell3(day + 1, 0, cap, prices);
        int skip = buySell3(day + 1, 1, cap, prices);
        return Math.max(buy, skip);
    } else {
        int sell = +prices[day] + buySell3(day + 1, 1, cap - 1, prices);
        int skip = buySell3(day + 1, 0, cap, prices);
        return Math.max(sell, skip);
    }
}


public int maxProfit44(int[] prices) {
    int n = prices.length;

    // dp[day][canBuy][cap]
    int[][][] dp = new int[n + 1][2][3];

    // base cases: dp[n][*][*] = 0, dp[*][*][0] = 0
    // already 0 by default

    for (int day = n - 1; day >= 0; day--) {
        for (int canBuy = 0; canBuy <= 1; canBuy++) {
            for (int cap = 1; cap <= 2; cap++) {   // cap=0 → 0, skip
                if (canBuy == 1) {
                    int buy  = -prices[day] + dp[day + 1][0][cap];
                    int skip = dp[day + 1][1][cap];
                    dp[day][canBuy][cap] = Math.max(buy, skip);
                } else {
                    int sell = +prices[day] + dp[day + 1][1][cap - 1];
                    int skip = dp[day + 1][0][cap];
                    dp[day][canBuy][cap] = Math.max(sell, skip);
                }
            }
        }
    }

    return dp[0][1][2];   // day=0, free to buy, cap=2
}

public int maxProfit5(int[] prices) {
    int n = prices.length;

    // only need next day → 2D array [canBuy][cap]
    int[][] next = new int[2][3];
    int[][] curr = new int[2][3];

    for (int day = n - 1; day >= 0; day--) {
        for (int canBuy = 0; canBuy <= 1; canBuy++) {
            for (int cap = 1; cap <= 2; cap++) {
                if (canBuy == 1) {
                    int buy  = -prices[day] + next[0][cap];
                    int skip = next[1][cap];
                    curr[canBuy][cap] = Math.max(buy, skip);
                } else {
                    int sell = +prices[day] + next[1][cap - 1];
                    int skip = next[0][cap];
                    curr[canBuy][cap] = Math.max(sell, skip);
                }
            }
        }
        next = curr.clone();  // important: deep copy
    }

    return next[1][2];
}

    public int maxProfit6(int[] prices) {
        // 4 key states for 2 transactions:
        // buy1  → min price for 1st buy
        // sell1 → max profit after 1st sell
        // buy2  → min price for 2nd buy (after sell1)
        // sell2 → max profit after 2nd sell

        int buy1  = Integer.MIN_VALUE;  // max profit after 1st buy
        int sell1 = 0;                  // max profit after 1st sell
        int buy2  = Integer.MIN_VALUE;  // max profit after 2nd buy
        int sell2 = 0;                  // max profit after 2nd sell

        for (int price : prices) {
            buy1  = Math.max(buy1,  -price);          // best 1st buy
            sell1 = Math.max(sell1,  buy1  + price);  // best 1st sell
            buy2  = Math.max(buy2,   sell1 - price);  // best 2nd buy
            sell2 = Math.max(sell2,  buy2  + price);  // best 2nd sell
        }

        return sell2;
    }


//        ### All Solutions Comparison
//
//| Solution | Time | Space | Notes |
//        |---|---|---|---|
//        | Recursion | `O(2^n)` | `O(n)` | TLE |
//        | Memoization | `O(n×2×3)` | `O(n×2×3)` | Top-down |
//        | Tabulation | `O(n×2×3)` | `O(n×2×3)` | Bottom-up |
//        | Space Optimised | `O(n×2×3)` | `O(1)` | 2D next/curr |
//        | **4 Variables** | **`O(n)`** | **`O(1)`** | ✅ Best |
//
//        ---
//
//        ### Key Takeaways
//```
//          1. Extra state cap tracks transactions left
//          2. cap-- on every SELL (not buy)
//          3. Base: cap==0 → return 0
//          4. 4 variable approach:
//              buy1  → best profit after 1st buy
//              sell1 → best profit after 1st sell
//              buy2  → best profit after 2nd buy  (uses sell1)
//              sell2 → best profit after 2nd sell (final answer)
//          5. Each variable feeds into next:
//             buy1 → sell1 → buy2 → sell2



////At max K transaction


class Solution {
    private int solve(int day, int canBuy, int cap, int[] prices) {
        // base cases
        if (day == prices.length) return 0;
        if (cap == 0) return 0;

        if (canBuy == 1) {
            int buy  = -prices[day] + solve(day + 1, 0, cap, prices);
            int skip = solve(day + 1, 1, cap, prices);
            return Math.max(buy, skip);
        } else {
            int sell = +prices[day] + solve(day + 1, 1, cap - 1, prices);
            int skip = solve(day + 1, 0, cap, prices);
            return Math.max(sell, skip);
        }
    }

    public int maxProfit(int k, int[] prices) {
        return solve(0, 1, k, prices);
    }
}



public int maxProfit(int k, int[] prices) {
    int n = prices.length;

    // dp[day][canBuy][cap]
    int[][][] dp = new int[n + 1][2][k + 1];

    // base cases: dp[n][*][*]=0, dp[*][*][0]=0
    // already 0 by default

    for (int day = n - 1; day >= 0; day--) {
        for (int canBuy = 0; canBuy <= 1; canBuy++) {
            for (int cap = 1; cap <= k; cap++) {
                if (canBuy == 1) {
                    int buy  = -prices[day] + dp[day + 1][0][cap];
                    int skip = dp[day + 1][1][cap];
                    dp[day][canBuy][cap] = Math.max(buy, skip);
                } else {
                    int sell = +prices[day] + dp[day + 1][1][cap - 1];
                    int skip = dp[day + 1][0][cap];
                    dp[day][canBuy][cap] = Math.max(sell, skip);
                }
            }
        }
    }

    return dp[0][1][k];
}



    public int maxProfit9(int k, int[] prices) {
        int n = prices.length;

        int[][] next = new int[2][k + 1];
        int[][] curr = new int[2][k + 1];

        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (canBuy == 1) {
                        int buy  = -prices[day] + next[0][cap];
                        int skip = next[1][cap];
                        curr[canBuy][cap] = Math.max(buy, skip);
                    } else {
                        int sell = +prices[day] + next[1][cap - 1];
                        int skip = next[0][cap];
                        curr[canBuy][cap] = Math.max(sell, skip);
                    }
                }
            }
            // deep copy next = curr
            for (int i = 0; i < 2; i++)
                next[i] = curr[i].clone();
        }

        return next[1][k];
    }





//        ### Stock III vs Stock IV

//      Stock III        Stock IV
//      ────────────────────────────────────────────
//      cap             fixed = 2        variable = k
//      memo size       [n][2][3]        [n][2][k+1]
//      tabulation      cap: 1 to 2      cap: 1 to k
//      answer          dp[0][1][2]      dp[0][1][k]
//      code change     NONE             just replace 2 with k



//        > Stock IV is the **generalisation** of Stock III. ✅
//
//        ---
//
//        ### All Solutions Comparison
//
//        | Solution | Time | Space |
//        |---|---|---|
//        | Recursion | `O(2^n)` | `O(n)` |
//        | Memoization | `O(n×2×k)` | `O(n×2×k)` |
//        | Tabulation | `O(n×2×k)` | `O(n×2×k)` |
//        | **Space Optimised** | **`O(n×2×k)`** | **`O(k)`** ✅ |




//        ### Key Takeaways
//```
//        1. Exact same as Stock III — just replace 2 with k
//        2. State: (day, canBuy, cap)
//        3. cap-- only on SELL
//        4. Base: cap==0 → 0, day==n → 0
//        5. Answer: dp[0][1][k]



//Stock problem progression:
//I   → 1 transaction   → simple min/max
//II  → unlimited       → greedy / canBuy state
//III → 2 transactions  → add cap(0-2)
//IV  → k transactions  → generalise cap(0-k)
//V   → cooldown        → day+2 on sell
//VI  → fee             → subtract fee on sell



////V   → cooldown        → day+2 on sell

    private int solve3(int day, int canBuy, int[] prices) {
        // base case
        if (day >= prices.length) return 0;  // >= handles day+2 overshot

        if (canBuy == 1) {
            int buy  = -prices[day] + solve(day + 1, 0, prices);
            int skip = solve(day + 1, 1, prices);
            return Math.max(buy, skip);
        } else {
            int sell = +prices[day] + solve(day + 2, 1, prices); // day+2
            int skip = solve(day + 1, 0, prices);
            return Math.max(sell, skip);
        }
    }

    public int maxProfit7(int[] prices) {
        return solve3(0, 1, prices);
    }


    public int maxProfit8(int[] prices) {
        int n = prices.length;

        // dp[day][canBuy]
        // extra size n+2 to handle day+2 safely
        int[][] dp = new int[n + 2][2];

        // base cases: dp[n][*] = dp[n+1][*] = 0
        // already 0 by default

        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    int buy  = -prices[day] + dp[day + 1][0];
                    int skip = dp[day + 1][1];
                    dp[day][canBuy] = Math.max(buy, skip);
                } else {
                    int sell = +prices[day] + dp[day + 2][1]; // day+2
                    int skip = dp[day + 1][0];
                    dp[day][canBuy] = Math.max(sell, skip);
                }
            }
        }

        return dp[0][1];
    }





//Stock II  → sell = +prices[day] + solve(day+1, 1)
//Stock VI  → sell = +prices[day] - fee + solve(day+1, 1)
//                               ↑
//only change!
//
//

    private int solve(int day, int canBuy, int[] prices, int fee) {
        // base case
        if (day == prices.length) return 0;

        if (canBuy == 1) {
            int buy  = -prices[day] + solve(day + 1, 0, prices, fee);
            int skip = solve(day + 1, 1, prices, fee);
            return Math.max(buy, skip);
        } else {
            int sell = +prices[day] - fee + solve(day + 1, 1, prices, fee); // fee
            int skip = solve(day + 1, 0, prices, fee);
            return Math.max(sell, skip);
        }
    }

    public int maxProfit(int[] prices, int fee) {
        return solve(0, 1, prices, fee);
    }


    public int maxProfit8(int[] prices, int fee) {
        int n = prices.length;

        // dp[day][canBuy]
        int[][] dp = new int[n + 1][2];

        // base case: dp[n][*] = 0 (already default)

        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    int buy  = -prices[day] + dp[day + 1][0];
                    int skip = dp[day + 1][1];
                    dp[day][canBuy] = Math.max(buy, skip);
                } else {
                    int sell = +prices[day] - fee + dp[day + 1][1]; // fee
                    int skip = dp[day + 1][0];
                    dp[day][canBuy] = Math.max(sell, skip);
                }
            }
        }

        return dp[0][1];
    }


//All 6 problems share same skeleton:
//Change 1 thing → get next problem
//II  → add cap         → III / IV
//II  → add day+2       → V
//II  → add -fee        → VI
//```
//
//        ---
//
//        ### Complete DP on Stocks — Done! 🎉
//        ```
//I   → O(n), O(1) — simple min tracking
//II  → O(n), O(1) — greedy or canBuy state
//III → O(n), O(1) — cap=2, 4 variables
//IV  → O(nk),O(k) — cap=k, generalised
//V   → O(n), O(1) — day+2 on sell
//VI  → O(n), O(1) — fee on sell

}