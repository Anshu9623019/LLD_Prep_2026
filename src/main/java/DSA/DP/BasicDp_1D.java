package DSA.DP;

public class BasicDp_1D {

    public static void main(String[] args) {

    }

    //Fibbonachi

    //Recursion
    int fib1(int n, int dp[]){
        if(n<=1){
            return n;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        return dp[n] = fib1(n-1,dp) + fib1(n-2,dp);
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

    //2. Climbing Stair  : 1D same as Fibbonacci
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
        int dp[] = new int[n+1];
        int prev2 = 1 ;
        int prev1 = 2;
        for(int i =3;i<=n;i++){
            int curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }


    //Fog Jump













}
