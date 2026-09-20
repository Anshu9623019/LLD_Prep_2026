package DSA.DP;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DP_ON_GRID {

    public static void main(String[] args) {

    }

    //Ninja Training
    int ninjaTraining(int days, int task, int arr[][]){
        if(days==0){
            int max = 0;
            for(int i=0;i<3;i++){
                if(task!=i){
                    max = Math.max(max,arr[days][i]);
                }
            }
            return max;
        }
        int max = 0;
        for(int i=0;i<3;i++){
            if(task!=i){
                max = arr[days][task]+ Math.max(max,ninjaTraining(days-1,i,arr));
            }
        }
        return max;
    }


    int ninjaMain(int arr[][]){
        return ninjaTraining(arr.length-1,3,arr);
    }

    int ninjaTraining(int n, int[][] points){

        int dp[][] = new int[n][4];

        // base case
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0],
                Math.max(points[0][1], points[0][2]));

        for(int day = 1; day < n; day++){

            for(int last = 0; last < 4; last++){

                dp[day][last] = 0;

                for(int task = 0; task < 3; task++){

                    if(task != last){

                        int curr = points[day][task]
                                + dp[day-1][task];

                        dp[day][last] = Math.max(dp[day][last], curr);
                    }
                }
            }
        }

        return dp[n-1][3];
    }

    int ninjaTraining1(int n, int[][] points){

        int prev[] = new int[4];

        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0],
                Math.max(points[0][1], points[0][2]));

        for(int day = 1; day < n; day++){

            int temp[] = new int[4];

            for(int last = 0; last < 4; last++){

                temp[last] = 0;

                for(int task = 0; task < 3; task++){

                    if(task != last){

                        temp[last] = Math.max(
                                temp[last],
                                points[day][task] + prev[task]
                        );
                    }
                }
            }

            prev = temp;
        }

        return prev[3];
    }
    //Grid
    int countPaths(int i, int j){
        if(i == 0 && j == 0) return 1;
        if(i < 0 || j < 0) return 0;

        int up = countPaths(i-1, j);
        int left = countPaths(i, j-1);

        return up + left;
    }

    int uniquePaths(int m, int n){

        int dp[][] = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                if(i == 0 && j == 0){
                    dp[i][j] = 1;
                }
                else{
                    int up = 0, left = 0;

                    if(i > 0) up = dp[i-1][j];
                    if(j > 0) left = dp[i][j-1];

                    dp[i][j] = up + left;
                }
            }
        }

        return dp[m-1][n-1];
    }

    int uniquePaths1(int m, int n){

        int prev[] = new int[n];

        for(int i = 0; i < m; i++){

            int curr[] = new int[n];

            for(int j = 0; j < n; j++){

                if(i == 0 && j == 0){
                    curr[j] = 1;
                }
                else{
                    int up = 0, left = 0;

                    if(i > 0) up = prev[j];
                    if(j > 0) left = curr[j-1];

                    curr[j] = up + left;
                }
            }

            prev = curr;
        }

        return prev[n-1];
    }

    //Unique Path 2 with obstacle
    int solve(int i, int j, int[][] grid){

        if(i >= 0 && j >= 0 && grid[i][j] == 1)
            return 0;

        if(i == 0 && j == 0)
            return 1;

        if(i < 0 || j < 0)
            return 0;

        int up = solve(i-1, j, grid);
        int left = solve(i, j-1, grid);

        return up + left;
    }

    int uniquePathsWithObstacles(int[][] grid){

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                // obstacle
                if(grid[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }

                // start
                if(i == 0 && j == 0){
                    dp[i][j] = 1;
                    continue;
                }

                int up = 0, left = 0;

                if(i > 0) up = dp[i-1][j];
                if(j > 0) left = dp[i][j-1];

                dp[i][j] = up + left;
            }
        }

        return dp[m-1][n-1];
    }

    int uniquePathsWithObstacles1(int[][] grid){

        int m = grid.length;
        int n = grid[0].length;

        int[] prev = new int[n];

        for(int i = 0; i < m; i++){

            int[] curr = new int[n];

            for(int j = 0; j < n; j++){

                if(grid[i][j] == 1){
                    curr[j] = 0;
                    continue;
                }

                if(i == 0 && j == 0){
                    curr[j] = 1;
                    continue;
                }

                int up = 0, left = 0;

                if(i > 0) up = prev[j];
                if(j > 0) left = curr[j-1];

                curr[j] = up + left;
            }

            prev = curr;
        }

        return prev[n-1];
    }


    public int minPathSum(int[][] grid) {
        return solve(grid, grid.length - 1, grid[0].length - 1);
    }


    private int solve(int[][] grid, int i, int j) {
        // base case
        if (i == 0 && j == 0) return grid[0][0];

        // out of bounds
        if (i < 0 || j < 0) return Integer.MAX_VALUE;

        int fromTop  = solve(grid, i - 1, j);
        int fromLeft = solve(grid, i, j - 1);

        return grid[i][j] + Math.min(fromTop, fromLeft);
    }

    //Bottom-up
    public int minPathSum1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        // fill the dp table left to right, top to bottom
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];            // start cell
                } else if (i == 0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j]; // top row
                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j]; // left col
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m-1][n-1];
    }

    //Space optimization
    public int minPathSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] prev = new int[n]; // previous row

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n]; // current row
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    curr[j] = grid[i][j];
                } else if (i == 0) {
                    curr[j] = curr[j-1] + grid[i][j];  // top row — from left
                } else if (j == 0) {
                    curr[j] = prev[j] + grid[i][j];     // left col — from above
                } else {
                    curr[j] = grid[i][j] + Math.min(prev[j], curr[j-1]);
                }
            }
            prev = curr; // move current row to previous
        }
        return prev[n-1];
    }
//```
//        - Time: **O(m×n)**
//            - Space: **O(n)** — only 2 rows at a time
//
//---
//
//        **Full Comparison:**
//
//            | Approach | Time | Space | Stack | Notes |
//            |---|---|---|---|---|
//            | Recursion | O(2^(m+n)) | O(m+n) | Yes | Never use in interviews |
//            | Memoization | O(m×n) | O(m×n) | Yes | Good starting point |
//            | Tabulation | O(m×n) | O(m×n) | No | Clean, iterative |
//            | Space Optimized | O(m×n) | O(n) | No | Best overall |
//
//            ---
//
//            **The progression to explain in an interview:**
//            ```
//    Recursion → "has overlapping subproblems"
//            → Memoization → "cache results, top-down"
//            → Tabulation → "eliminate stack, bottom-up"
//            → Space Optimization → "only need prev row"



    //Print Path also
    public List<int[]> getPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // Step 1: build dp table (bottom-up)
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) dp[i][0] = dp[i-1][0] + grid[i][0];
        for (int j = 1; j < n; j++) dp[0][j] = dp[0][j-1] + grid[0][j];

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);

        // Step 2: trace back from bottom-right to top-left
        List<int[]> path = new LinkedList<>();
        int i = m - 1, j = n - 1;

        while (i > 0 || j > 0) {
            path.add(0, new int[]{i, j});
            if (i == 0) j--;                          // can only go left
            else if (j == 0) i--;                     // can only go up
            else if (dp[i-1][j] < dp[i][j-1]) i--;   // came from top
            else j--;                                  // came from left
        }
        path.add(0, new int[]{0, 0}); // add start cell
        return path;
    }


    //Triangle : sum
    public int minimumTotal2(List<List<Integer>> triangle) {
        return solve(triangle, 0, 0);
    }

    private int solve(List<List<Integer>> triangle, int i, int j) {
        // base case — last row
        if (i == triangle.size() - 1)
            return triangle.get(i).get(j);

        int down      = solve(triangle, i + 1, j);
        int downRight = solve(triangle, i + 1, j + 1);

        return triangle.get(i).get(j) + Math.min(down, downRight);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // initialize last row
        for (int j = 0; j < n; j++)
            dp[n-1][j] = triangle.get(n-1).get(j);

        // fill bottom to top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int down      = dp[i+1][j];
                int downRight = dp[i+1][j+1];
                dp[i][j] = triangle.get(i).get(j) + Math.min(down, downRight);
            }
        }
        return dp[0][0];
    }

//        - Time: **O(n²)**
//            - Space: **O(n²)**
//
//            ---
//
//            **Dry run on example:**
//            ```
//    Start (last row):
//    dp = [4, 1, 8, 3]
//
//    Row 2 (6 5 7):
//    dp[2] = 6+min(4,1)=7,  5+min(1,8)=6,  7+min(8,3)=10
//    dp = [7, 6, 10, 3]
//
//    Row 1 (3 4):
//    dp[1] = 3+min(7,6)=9,  4+min(6,10)=10
//    dp = [9, 10, 10, 3]
//
//    Row 0 (2):
//    dp[0] = 2+min(9,10)=11
//
//    Answer: 11 ✅


    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();

        // start with a copy of the last row
        int[] dp = new int[n];
        for (int j = 0; j < n; j++)
            dp[j] = triangle.get(n-1).get(j);

        // move bottom to top, overwrite dp in place
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }
        return dp[0];
    }
//```
//        - Time: **O(n²)**
//            - Space: **O(n)** — single row only
//
//---
//
//        **Full Comparison:**
//
//            | Approach | Time | Space | Direction | Notes |
//            |---|---|---|---|---|
//            | Recursion | O(2^n) | O(n) | Top → Bottom | Never in interviews |
//            | Memoization | O(n²) | O(n²) | Top → Bottom | Good to explain first |
//            | Tabulation | O(n²) | O(n²) | Bottom → Top | Cleaner, no stack |
//            | Space Optimized | O(n²) | O(n) | Bottom → Top | Best answer |
//
//            ---
//
//            **Key difference vs Grid DP:**
//
//            | | Min Path Sum (Grid) | Triangle DP |
//            |---|---|---|
//            | Movement | Right or Down | Down or Diagonal-right |
//            | DP direction | Top-left → Bottom-right | Bottom → Top (easier) |
//            | Space opt | O(n) — one row | O(n) — one row |
//
//            ---
//
//            **The interview progression to say out loud:**
//            ```
//            "I'll start top-down with recursion
//            → notice overlapping subproblems
// → cache with memo
// → convert to tabulation bottom-up
// → observe we only need one row → O(n) space"


    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int minSum = Integer.MAX_VALUE;

        // try every column in first row as starting point
        for (int j = 0; j < n; j++)
            minSum = Math.min(minSum, solve(matrix, 0, j, n));

        return minSum;
    }

    private int solve(int[][] matrix, int i, int j, int n) {
        // out of bounds
        if (j < 0 || j >= n) return Integer.MAX_VALUE;

        // base case — last row
        if (i == n - 1) return matrix[i][j];

        int down      = solve(matrix, i + 1, j,     n);
        int downLeft  = solve(matrix, i + 1, j - 1, n);
        int downRight = solve(matrix, i + 1, j + 1, n);

        return matrix[i][j] + Math.min(down, Math.min(downLeft, downRight));
    }


    public int[] fallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dpMin = new int[n][n];
        int[][] dpMax = new int[n][n];

        // initialize first row
        for (int j = 0; j < n; j++) {
            dpMin[0][j] = matrix[0][j];
            dpMax[0][j] = matrix[0][j];
        }

        // fill top to bottom
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int left  = (j > 0)     ? dpMin[i-1][j-1] : Integer.MAX_VALUE;
                int up    =               dpMin[i-1][j];
                int right = (j < n - 1) ? dpMin[i-1][j+1] : Integer.MAX_VALUE;
                dpMin[i][j] = matrix[i][j] + Math.min(up, Math.min(left, right));

                left  = (j > 0)     ? dpMax[i-1][j-1] : Integer.MIN_VALUE;
                up    =               dpMax[i-1][j];
                right = (j < n - 1) ? dpMax[i-1][j+1] : Integer.MIN_VALUE;
                dpMax[i][j] = matrix[i][j] + Math.max(up, Math.max(left, right));
            }
        }

        // answer is best value in last row
        int minSum = Integer.MAX_VALUE, maxSum = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            minSum = Math.min(minSum, dpMin[n-1][j]);
            maxSum = Math.max(maxSum, dpMax[n-1][j]);
        }
        return new int[]{minSum, maxSum};
    }
//```
//        - Time: **O(n²)**
//            - Space: **O(n²)**
//
//            ---
//
//            **Dry run on example:**
//            ```
//    Matrix:
//            2  1  3
//            6  5  4
//            7  8  9
//
//    dpMin after row 0:  [2, 1, 3]
//
//    dpMin after row 1:
//    j=0: 6 + min(INF, 2, 1)  = 6+1 = 7
//    j=1: 5 + min(2,  1, 3)   = 5+1 = 6
//    j=2: 4 + min(1,  3, INF) = 4+1 = 5
//    dpMin = [7, 6, 5]
//
//    dpMin after row 2:
//    j=0: 7 + min(INF, 7, 6)  = 7+6 = 13
//    j=1: 8 + min(7,   6, 5)  = 8+5 = 13
//    j=2: 9 + min(6,   5, INF)= 9+5 = 14
//    dpMin = [13, 13, 14]
//
//    Min = 13 ✅
//
//    dpMax last row = [20, 19, 17]
//    Max = 20  (2→6→7... wait)


    public int[] fallingPathSum1(int[][] matrix) {
        int n = matrix.length;
        int[] prevMin = new int[n];
        int[] prevMax = new int[n];

        // initialize with first row
        for (int j = 0; j < n; j++) {
            prevMin[j] = matrix[0][j];
            prevMax[j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            int[] currMin = new int[n];
            int[] currMax = new int[n];

            for (int j = 0; j < n; j++) {
                int leftMin  = (j > 0)     ? prevMin[j-1] : Integer.MAX_VALUE;
                int upMin    =               prevMin[j];
                int rightMin = (j < n - 1) ? prevMin[j+1] : Integer.MAX_VALUE;
                currMin[j] = matrix[i][j] + Math.min(upMin, Math.min(leftMin, rightMin));

                int leftMax  = (j > 0)     ? prevMax[j-1] : Integer.MIN_VALUE;
                int upMax    =               prevMax[j];
                int rightMax = (j < n - 1) ? prevMax[j+1] : Integer.MIN_VALUE;
                currMax[j] = matrix[i][j] + Math.max(upMax, Math.max(leftMax, rightMax));
            }
            prevMin = currMin;
            prevMax = currMax;
        }

        int minSum = Integer.MAX_VALUE, maxSum = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            minSum = Math.min(minSum, prevMin[j]);
            maxSum = Math.max(maxSum, prevMax[j]);
        }
        return new int[]{minSum, maxSum};
    }


    //Chocolate PickUp
    public int cherryPickup(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        return solve(grid, 0, 0, c - 1, r, c);
    }

    private int solve(int[][] grid, int i, int j1, int j2, int r, int c) {
        // out of bounds
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) return Integer.MIN_VALUE;

        // collect chocolates — count once if same cell
        int chocolates = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];

        // base case — last row
        if (i == r - 1) return chocolates;

        // try all 9 combinations (3 moves × 3 moves)
        int maxNext = Integer.MIN_VALUE;
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int next = solve(grid, i + 1, j1 + dj1, j2 + dj2, r, c);
                maxNext = Math.max(maxNext, next);
            }
        }

        return chocolates + maxNext;
    }

    public int cherryPickup0(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[][][] memo = new int[r][c][c];
        for (int[][] layer : memo)
            for (int[] row : layer)
                Arrays.fill(row, -1);

        return solve(grid, 0, 0, c - 1, r, c, memo);
    }

    private int solve(int[][] grid, int i, int j1, int j2,
                      int r, int c, int[][][] memo) {
        // out of bounds
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c)
            return Integer.MIN_VALUE;

        // base case
        int chocolates = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
        if (i == r - 1) return chocolates;

        // check cache
        if (memo[i][j1][j2] != -1) return memo[i][j1][j2];

        // try all 9 combinations
        int maxNext = Integer.MIN_VALUE;
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int next = solve(grid, i + 1, j1 + dj1, j2 + dj2, r, c, memo);
                if (next != Integer.MIN_VALUE) // skip out-of-bound paths
                    maxNext = Math.max(maxNext, next);
            }
        }

        memo[i][j1][j2] = chocolates + maxNext;
        return memo[i][j1][j2];
    }

    //Tabulation
    public int cherryPickup1(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[][][] dp = new int[r][c][c];

        // initialize last row
        for (int j1 = 0; j1 < c; j1++)
            for (int j2 = 0; j2 < c; j2++)
                dp[r-1][j1][j2] = (j1 == j2)
                        ? grid[r-1][j1]
                        : grid[r-1][j1] + grid[r-1][j2];

        // fill bottom to top
        for (int i = r - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {

                    int chocolates = (j1 == j2)
                            ? grid[i][j1]
                            : grid[i][j1] + grid[i][j2];

                    int maxNext = Integer.MIN_VALUE;

                    // try all 9 move combinations
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int nj1 = j1 + dj1;
                            int nj2 = j2 + dj2;
                            if (nj1 >= 0 && nj1 < c && nj2 >= 0 && nj2 < c)
                                maxNext = Math.max(maxNext, dp[i+1][nj1][nj2]);
                        }
                    }

                    dp[i][j1][j2] = chocolates + maxNext;
                }
            }
        }

        // answer — both start at row 0, j1=0, j2=c-1
        return dp[0][0][c-1];
    }


    public int cherryPickup2(int[][] grid) {
        int r = grid.length, c = grid[0].length;

        // next = dp[i+1], curr = dp[i]
        int[][] next = new int[c][c];

        // initialize last row
        for (int j1 = 0; j1 < c; j1++)
            for (int j2 = 0; j2 < c; j2++)
                next[j1][j2] = (j1 == j2)
                        ? grid[r-1][j1]
                        : grid[r-1][j1] + grid[r-1][j2];

        // fill bottom to top
        for (int i = r - 2; i >= 0; i--) {
            int[][] curr = new int[c][c];

            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {

                    int chocolates = (j1 == j2)
                            ? grid[i][j1]
                            : grid[i][j1] + grid[i][j2];

                    int maxNext = Integer.MIN_VALUE;

                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int nj1 = j1 + dj1;
                            int nj2 = j2 + dj2;
                            if (nj1 >= 0 && nj1 < c && nj2 >= 0 && nj2 < c)
                                maxNext = Math.max(maxNext, next[nj1][nj2]);
                        }
                    }

                    curr[j1][j2] = chocolates + maxNext;
                }
            }
            next = curr; // slide the window up
        }

        return next[0][c-1];
    }
//```
//        - Time: **O(r × c²)**
//            - Space: **O(c²)** — two 2D arrays only
//
//---
//
//        **Full Comparison:**
//
//            | Approach | Time | Space | Notes |
//            |---|---|---|---|
//            | Recursion | O(9^r) | O(r) | Exponential, never use |
//            | Memoization | O(r×c²) | O(r×c²) | 3D memo table |
//            | Tabulation | O(r×c²) | O(r×c²) | Fill bottom-up |
//            | Space Optimized | O(r×c²) | O(c²) | Two 2D arrays |
//
//            ---
//
//            **The 3 tricks that make this problem hard:**
//            ```
//            1. SAME CELL → count once
//   if (j1 == j2) chocolates = grid[i][j1]
//            else          chocolates = grid[i][j1] + grid[i][j2]
//
//            2. 9 COMBINATIONS → nested loop dj1, dj2 ∈ {-1, 0, 1}
//    Don't manually write 9 cases — use the double for loop
//
//            3. OUT OF BOUNDS → return INT_MIN (not 0!)
//    Returning 0 would incorrectly treat invalid paths as valid

}
