package DSA.DP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicDp_1D {

    public static void main(String[] args) {

    }

    //Fibbonachi
    //Recursion : EASY
    int fib1(int n, int dp[]){
        if(n<=1){
            return n;
        }
        return fib1(n-1,dp) + fib1(n-2,dp);
    }

    //Memoization : topDown
    int fib2(int n, int dp[]){
        if(n<=1){
            return n;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        dp[n] = fib2(n-1,dp) + fib2(n-2,dp);
        return dp[n];
    }

    //Memoization : botton-up
    int fib3(int n){
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    //Space Optimize
    //Memoization : botton-up
    int fib4(int n){
        int prev2 = 0;
        int prev1 = 1;
        for(int i=2;i<=n;i++){
            int curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    //2. Climbing Stair  : 1D same as Fibbonacci : Easy
    int climbingStair(int n){
        int dp[] = new int[n+1];
        dp[1] = 1 ;
        dp[2] = 2;
        for(int i =3;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }


    //Space optimize
    int climbingStair1(int n){
        int prev2 = 1 ;
        int prev1 = 2;
        for(int i =3;i<=n;i++){
            int curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }


    // 3. Fog Jump (i+1,i+2)
    //1. Recursion
    int frogJump1(int height[], int i){
        if(i==0){
            return 0;
        }
        int left = Math.abs(height[i-1]-height[i]) + frogJump1(height,i-1);
        int right = Integer.MAX_VALUE;
        if(i>1){
             right = Math.abs(height[i-2]-height[i]) + frogJump1(height,i-2);
        }
        return Math.min(left,right);
    }

    //2. Memoization : TopDown
    int frogJump2(int height[], int i,int dp[]){
        if(i==0){
            return 0;
        }

        if (dp[i]!=-1){
            return dp[i];
        }
        int left = Math.abs(height[i-1]-height[i]) + frogJump2(height,i-1,dp);
        int right = Integer.MAX_VALUE;
        if(i>1){
            right = Math.abs(height[i-2]=height[i]) + frogJump2(height,i-2,dp);
        }
        return dp[i] = Math.min(left,right);
    }


    //3. Tabulation, Bottom-up
    int frogJump3(int height[],int dp[]){
        dp[0] = 0;
        for(int i=1;i<=height.length-1;i++){
            int left = Math.abs(height[i-1]-height[i]) + dp[i-1];
            int right = Integer.MAX_VALUE;
            if(i>1){
                right = Math.abs(height[i-2]=height[i]) + dp[i-2];
            }
            dp[i] = Math.min(left,right);
        }
        return dp[height.length-1];
    }

    //4. Space Optimization
    int frogJump3(int height[]){
        int prev1 = 0;
        int prev2 = 0;
        for(int i=1;i<=height.length-1;i++){
            int left = Math.abs(height[i-1]-height[i]) + prev1;
            int right = Integer.MAX_VALUE;
            if(i>1){
                right = Math.abs(height[i-2]=height[i]) + prev2;
            }
            int curr = Math.min(left,right);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    //4. Frog Jump with K steps
    public int frogJumpRec(int i, int[] h, int k) {
        if (i == 0) return 0;

        int minCost = Integer.MAX_VALUE;

        for (int j = 1; j <= k; j++) {
            if (i - j >= 0) {
                int cost = frogJumpRec(i - j, h, k)
                        + Math.abs(h[i] - h[i - j]);

                minCost = Math.min(minCost, cost);
            }
        }
        return minCost;
    }

    public int frogJumpMemo(int i, int[] h, int k, int[] dp) {
        if (i == 0) return 0;

        if (dp[i] != -1) return dp[i];

        int minCost = Integer.MAX_VALUE;

        for (int j = 1; j <= k; j++) {
            if (i - j >= 0) {
                int cost = frogJumpMemo(i - j, h, k, dp)
                        + Math.abs(h[i] - h[i - j]);

                minCost = Math.min(minCost, cost);
            }
        }

        return dp[i] = minCost;
    }


    public int frogJumpTab(int[] h, int k) {

        int n = h.length;
        int[] dp = new int[n];

        dp[0] = 0;

        for (int i = 1; i < n; i++) {

            int minCost = Integer.MAX_VALUE;

            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {

                    int cost = dp[i - j]
                            + Math.abs(h[i] - h[i - j]);

                    minCost = Math.min(minCost, cost);
                }
            }

            dp[i] = minCost;
        }

        return dp[n - 1];
    }


    public void frogJumpPath(int[] h, int k) {

        int n = h.length;
        int[] dp = new int[n];
        int[] parent = new int[n];

        dp[0] = 0;
        parent[0] = -1;

        for (int i = 1; i < n; i++) {

            int minCost = Integer.MAX_VALUE;

            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {

                    int cost = dp[i - j]
                            + Math.abs(h[i] - h[i - j]);

                    if (cost < minCost) {
                        minCost = cost;
                        parent[i] = i - j;
                    }
                }
            }

            dp[i] = minCost;
        }

        // reconstruct path
        List<Integer> path = new ArrayList<>();
        int curr = n - 1;

        while (curr != -1) {
            path.add(curr);
            curr = parent[curr];
        }

        Collections.reverse(path);

        System.out.println(path);
    }




  // Maximum Sum of non adj

    int solve(int i, int arr[]) {
        if(i == 0) return arr[0];
        if(i < 0) return 0;

        int pick = arr[i] + solve(i-2, arr);
        int notPick = solve(i-1, arr);

        return Math.max(pick, notPick);
    }


    //DP : Bottom up
    int maxSum(int arr[]) {

        int n = arr.length;
        int dp[] = new int[n];

        dp[0] = arr[0];

        for(int i = 1; i < n; i++){

            int pick = arr[i];
            if(i > 1) pick += dp[i-2];

            int notPick = dp[i-1];

            dp[i] = Math.max(pick, notPick);
        }

        return dp[n-1];
    }

    //Space Optimization
    int maxSum1(int arr[]) {

        int prev1 = arr[0];
        int prev2 = 0;

        for(int i = 1; i < arr.length; i++){

            int pick = arr[i];
            if(i > 1) pick += prev2;

            int notPick = prev1;

            int curr = Math.max(pick, notPick);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }


    //Robber 2
    // The difference is that houses are arranged in a circle.
    public int rob(int[] nums) {

        int n = nums.length;

        if(n == 1) return nums[0];

        return Math.max(
                robLinear(nums, 0, n-2),
                robLinear(nums, 1, n-1)
        );
    }

    public int robLinear(int[] nums, int start, int end){

        int prev = nums[start];
        int prev2 = 0;

        for(int i = start + 1; i <= end; i++){

            int pick = nums[i];
            if(i > start + 1) pick += prev2;

            int notPick = prev;

            int curr = Math.max(pick, notPick);

            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
}


//1. Fibonacchi
//2. climbingStair1
//3. frog Jump, i-1,i-2;
//4. from jump, i-k
//5. Robber 1, pick, NotPick
//6. Robber 2, Pick, NotPick,start,end;

