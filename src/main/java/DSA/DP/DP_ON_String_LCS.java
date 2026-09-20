package DSA.DP;

import java.util.Arrays;

public class DP_ON_String_LCS {

    public int lcs(int i, int j, String s1, String s2) {
        // base case: either string exhausted
        if (i < 0 || j < 0) return 0;

        if (s1.charAt(i) == s2.charAt(j))
            return 1 + lcs(i - 1, j - 1, s1, s2);   // match → take both

        return Math.max(
                lcs(i - 1, j, s1, s2),    // skip s1[i]
                lcs(i, j - 1, s1, s2)     // skip s2[j]
        );
    }


    /// /Bottom Up
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length(), m = s2.length();

        // 1-indexed: dp[i][j] = LCS of s1[0..i-1] and s2[0..j-1]
        int[][] dp = new int[n + 1][m + 1];

        // base case: dp[0][j] = 0, dp[i][0] = 0 (empty string)
        // already 0 by default

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];   // match
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // skip
            }
        }

        return dp[n][m];
    }
//

    /// /Space Optimization
    public int longestCommonSubsequence1(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    curr[j] = 1 + prev[j - 1];
                else
                    curr[j] = Math.max(prev[j], curr[j - 1]);
            }
            prev = curr.clone();
        }

        return prev[m];
    }

//        **Time:** `O(n×m)` | **Space:** `O(m)` ✅
//        ### Detailed Dry Run
//
//`s1 = "ABCDE"`, `s2 = "ACE"` → `n=5, m=3`
//
//        **Fill row by row:**
//        ```
//        ""    A    C    E
//  ""  [ 0,   0,   0,   0 ]
//  A   [ 0,   1,   1,   1 ]
//  B   [ 0,   1,   1,   1 ]
//  C   [ 0,   1,   2,   2 ]
//  D   [ 0,   1,   2,   2 ]
//  E   [ 0,   1,   2,   3 ]

    /// /        **Cell-by-cell explanation:**


    public String printLCS(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

        // backtrack from dp[n][m]
        StringBuilder sb = new StringBuilder();
        int i = n, j = m;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));  // part of LCS
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;   // came from top
            } else {
                j--;   // came from left
            }
        }

        return sb.reverse().toString();  // "ACE"
    }

//        ### Complexity Summary
//
//|       Approach | Time | Space |

//        | Recursion | `O(2^(n+m))` | `O(n+m)` stack |
//        | Memoization | `O(n×m)` | `O(n×m)` |
//        | Tabulation | `O(n×m)` | `O(n×m)` |
//        | Space Optimised | `O(n×m)` | `O(m)` ✅ |

//        ### Key Takeaways

//        1. Match   → diagonal  (dp[i-1][j-1] + 1)
//        2. No match → max of top (dp[i-1][j]) , left (dp[i][j-1])
//        3. Base case → row 0 and col 0 are all 0
//        4. Answer  → dp[n][m]
//        5. Print   → backtrack from dp[n][m]


    /// / 2. Longest Common Substring

    int maxLen = 0;

    public int solve(int i, int j, int count, String s1, String s2) {
        if (i == 0 || j == 0) return count;

        if (s1.charAt(i - 1) == s2.charAt(j - 1))
            count = solve(i - 1, j - 1, count + 1, s1, s2);

        // always explore mismatches too
        maxLen = Math.max(maxLen, count);
        solve(i - 1, j, 0, s1, s2);
        solve(i, j - 1, 0, s1, s2);

        return maxLen;
    }

public int longestCommonSubstring(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];
    int maxLen = 0;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                dp[i][j] = 1 + dp[i-1][j-1];        // extend diagonal
                maxLen = Math.max(maxLen, dp[i][j]);  // track max
            } else {
                dp[i][j] = 0;                         // reset
            }
        }
    }

    return maxLen;
}

public int longestCommonSubstring1(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[] prev = new int[m + 1];
    int[] curr = new int[m + 1];
    int maxLen = 0;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                curr[j] = 1 + prev[j-1];
                maxLen = Math.max(maxLen, curr[j]);
            } else {
                curr[j] = 0;   // reset
            }
        }
        prev = curr.clone();
        curr = new int[m + 1];
    }

    return maxLen;
}
//        **Time:** `O(n×m)` | **Space:** `O(m)` ✅

//        ### Detailed Dry Run

//`s1 = "ABCDE"`, `s2 = "ABCE"` → `n=5, m=4`

//        ""   A    B    C    E
//  ""  [ 0,   0,   0,   0,   0 ]
//  A   [ 0,   1,   0,   0,   0 ]
//  B   [ 0,   0,   2,   0,   0 ]
//  C   [ 0,   0,   0,   3,   0 ]
//  D   [ 0,   0,   0,   0,   0 ]
//  E   [ 0,   0,   0,   0,   1 ]

public String printLongestCommonSubstring(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];
    int maxLen = 0;
    int endIndex = 0;   // end index in s1

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                dp[i][j] = 1 + dp[i-1][j-1];
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    endIndex = i;   // track where it ends in s1
                }
            } else {
                dp[i][j] = 0;
            }
        }
    }
    // extract substring from s1
    return s1.substring(endIndex - maxLen, endIndex);
}




public int longestPalindromeSubseq(String s) {
    String rev = new StringBuilder(s).reverse().toString();
    return lcs(s, rev);   // reuse standard LCS
}

private int lcs(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1))
                dp[i][j] = 1 + dp[i-1][j-1];
            else
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
    }
    return dp[n][m];
}

//    ### Approach 2 — Direct DP on Single String
//
//#### State Definition
//```
//dp[i][j] = length of longest palindromic subsequence
//in s[i.j]
//        ```
//
//        #### Recurrence
//```
//        if s[i] == s[j]:
//dp[i][j] = 2 + dp[i+1][j-1]     // both ends match, add 2
//
//        if s[i] != s[j]:
//dp[i][j] = max(
//        dp[i+1][j],    // skip left char
//        dp[i][j-1]     // skip right char
//)
//```
//
//        #### Base Cases
//```
//dp[i][i] = 1       // single char is palindrome of length 1
//dp[i][i-1] = 0     // empty string
//
//

public int longestPalindromeSubseq1(String s) {
    int n = s.length();
    int[] prev = new int[n];
    int[] curr = new int[n];

    // base: single chars
    for (int i = 0; i < n; i++) prev[i] = 1;

    for (int i = n - 2; i >= 0; i--) {
        curr[i] = 1;   // dp[i][i] = 1
        for (int j = i + 1; j < n; j++) {
            if (s.charAt(i) == s.charAt(j))
                curr[j] = 2 + (i + 1 <= j - 1 ? prev[j-1] : 0);
            else
                curr[j] = Math.max(prev[j], curr[j-1]);
        }
        prev = curr.clone();
        curr = new int[n];
    }

    return prev[n - 1];
}

//
//        ### Related Problems Using LPS
//```
//   LPS length = lcs(s, reverse(s))
//
//  1. Min insertions to make palindrome
//   → n - LPS(s)
//
//  2. Min deletions to make palindrome
//   → n - LPS(s)
//
//  3. Check if string can be palindrome
//   → LPS(s) == n


public class MinInsertionsDeletions {

    // Step 1: compute LCS
    private int lcs(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[n][m];
    }

    // Step 2: compute min insertions and deletions
    public void minOperations(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int lcsLen = lcs(s1, s2);

        int deletions  = n - lcsLen;
        int insertions = m - lcsLen;

        System.out.println("LCS Length : " + lcsLen);
        System.out.println("Deletions  : " + deletions);
        System.out.println("Insertions : " + insertions);
        System.out.println("Total Ops  : " + (deletions + insertions));
    }
}


//        ### Detailed Dry Run — `s1="heap"`, `s2="pea"`
//
//        **LCS Table:**
//        ```
//        ""   p    e    a
//  "  [ 0,   0,   0,   0 ]
//  h  [ 0,   0,   0,   0 ]
//  e  [ 0,   0,   1,   1 ]
//  a  [ 0,   0,   1,   2 ]
//  p  [ 0,   1,   1,   2 ]
//
//LCS = dp[4][3] = 2  →  "ea"
//        ```
//
//        **Operations:**
//        ```
//s1 = "heap"  (n=4)
//s2 = "pea"   (m=3)
//lcs = 2      → "ea"
//
//Deletions  = 4 - 2 = 2  → delete 'h', 'p' from s1
//Insertions = 3 - 2 = 1  → insert 'p' into s1


//        ### All Related String DP Formulas

//  lcs = LCS(s1, s2)
//  n   = len(s1)
//  m   = len(s2)
//
//┌─────────────────────────────────────────────────────┐
//        │  Problem                        │  Formula           │
//        ├─────────────────────────────────┼────────────────────│
//        │  Min Deletions from s1          │  n - lcs           │
//        │  Min Insertions into s1         │  m - lcs           │
//        │  Total Min Operations           │  n + m - 2*lcs     │
//        │  Shortest Common Supersequence  │  n + m - lcs       │
//        │  Longest Palindromic Subseq     │  lcs(s, reverse(s))│
//        │  Min Insert to make palindrome  │  n - lps           │
//        │  Min Delete to make palindrome  │  n - lps           │
//        └─────────────────────────────────┴────────────────────┘



public int shortestCommonSupersequence(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; j++)
            if (s1.charAt(i-1) == s2.charAt(j-1))
                dp[i][j] = 1 + dp[i-1][j-1];
            else
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);

    int lcs = dp[n][m];
    return n + m - lcs;   // SCS length
}


public String printShortestCommonSupersequence(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    // Step 1: build LCS table
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1))
                dp[i][j] = 1 + dp[i-1][j-1];
            else
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
    }

    // Step 2: backtrack to build SCS string
    StringBuilder sb = new StringBuilder();
    int i = n, j = m;

    while (i > 0 && j > 0) {
        if (s1.charAt(i-1) == s2.charAt(j-1)) {
            // char is in LCS → add ONCE, move diagonally
            sb.append(s1.charAt(i-1));
            i--; j--;
        } else if (dp[i-1][j] > dp[i][j-1]) {
            // came from top → s1 char not in LCS → add it
            sb.append(s1.charAt(i-1));
            i--;
        } else {
            // came from left → s2 char not in LCS → add it
            sb.append(s2.charAt(j-1));
            j--;
        }
    }

    // Step 3: append remaining characters
    while (i > 0) { sb.append(s1.charAt(i-1)); i--; }
    while (j > 0) { sb.append(s2.charAt(j-1)); j--; }

    // Step 4: reverse (built backwards)
    return sb.reverse().toString();
}




//        while i > 0 && j > 0:
//
//        ┌─────────────────────────────────────────────────┐
//        │ s1[i-1] == s2[j-1]  → LCS char               │
//        │   append char ONCE                              │
//        │   move diagonal: i--, j--                      │
//        ├─────────────────────────────────────────────────┤
//        │ dp[i-1][j] > dp[i][j-1]  → came from TOP      │
//        │   append s1[i-1]                               │
//        │   move up: i--                                 │
//        ├─────────────────────────────────────────────────┤
//        │ dp[i][j-1] >= dp[i-1][j]  → came from LEFT    │
//        │   append s2[j-1]                               │
//        │   move left: j--                               │
//        └─────────────────────────────────────────────────┘
//
//        after loop:
//        remaining s1 chars → append all
//        remaining s2 chars → append all
//        reverse final string

//        ### All String DP Formulas Together

//      lcs = LCS(s1, s2)
//      n   = len(s1),  m = len(s2)
//
//┌──────────────────────────────┬──────────────────────┐
//        │  Problem                     │  Formula             │
//        ├──────────────────────────────┼──────────────────────┤
//        │  LCS length                  │  lcs                 │
//        │  SCS length                  │  n + m - lcs         │
//        │  Min deletions               │  n - lcs             │
//        │  Min insertions              │  m - lcs             │
//        │  Total min ops               │  n + m - 2*lcs       │
//        │  Longest Palindromic Subseq  │  lcs(s, rev(s))      │
//        │  Min insert → palindrome     │  n - lps             │
//        └──────────────────────────────┴──────────────────────┘
//        ```





 // Distinct Subsequence
public int solve(int i, int j, String s, String t) {
    // base cases
    if (j < 0) return 1;    // t exhausted → found one way
    if (i < 0) return 0;    // s exhausted → t still remaining

    if (s.charAt(i) == t.charAt(j))
        return solve(i-1, j-1, s, t)   // use s[i]
                + solve(i-1, j, s, t);    // skip s[i]

    return solve(i-1, j, s, t);        // skip s[i]
}

// call: solve(n-1, m-1, s, t)

public int numDistinct1(String s, String t) {
    int n = s.length(), m = t.length();
    int[][] dp = new int[n + 1][m + 1];

    // base case: empty t → 1 way for every prefix of s
    for (int i = 0; i <= n; i++) dp[i][0] = 1;
    // base case: empty s, non-empty t → 0 ways (default)

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s.charAt(i-1) == t.charAt(j-1))
                dp[i][j] = dp[i-1][j-1]   // use s[i]
                        + dp[i-1][j];    // skip s[i]
            else
                dp[i][j] = dp[i-1][j];    // skip s[i]
        }
    }

    return dp[n][m];
}

public int numDistinct2(String s, String t) {
    int n = s.length(), m = t.length();
    int[] prev = new int[m + 1];
    int[] curr = new int[m + 1];

    // base case
    prev[0] = 1;
    curr[0] = 1;

    for (int i = 1; i <= n; i++) {
        curr[0] = 1;  // empty t always 1 way
        for (int j = 1; j <= m; j++) {
            if (s.charAt(i-1) == t.charAt(j-1))
                curr[j] = prev[j-1] + prev[j];
            else
                curr[j] = prev[j];
        }
        prev = curr.clone();
    }

    return prev[m];
}
//```
//
//        **Time:** `O(n×m)` | **Space:** `O(m)` ✅
//
//        ---
//
//        ### Detailed Dry Run
//
//`s = "rabbbit"`, `t = "rabbit"` → Expected: `3`
//
//        **DP Table:**
//        ```
//        ""   r    a    b    b    i    t
//   ""  [  1,  0,   0,   0,   0,   0,   0 ]
//  r   [  1,  1,   0,   0,   0,   0,   0 ]
//  a   [  1,  1,   1,   0,   0,   0,   0 ]
//  b   [  1,  1,   1,   1,   0,   0,   0 ]
//  b   [  1,  1,   1,   2,   1,   0,   0 ]
//  b   [  1,  1,   1,   3,   3,   0,   0 ]
//  i   [  1,  1,   1,   3,   3,   3,   0 ]
//  t   [  1,  1,   1,   3,   3,   3,   3 ]
//
//Answer: dp[7][6] = 3  ✅

//        ### Visual — The 3 Ways
//```
//  s = "r a b b b i t"
//        ↑ ↑ ↑
//  t = "r a b b _ i t"   → skip 3rd b  ✅
//
//  s = "r a b b b i t"
//        ↑   ↑ ↑
//  t = "r a b _ b i t"   → skip 2nd b  ✅
//
//  s = "r a b b b i t"
//        ↑     ↑ ↑ (wait)
//  t = "r a _ b b i t"   → skip 1st b  ✅






//Longest Repeating Subsequence — Deep Dive : Problem Statement
//
//Given a string s, find the length of the longest subsequence that appears at least twice in the string,
// such that the two subsequences don't have the same character at the same position.
//
//Input:  s = "AABEBCDD"
//Output: 3
//Reason: "ABD" appears twice as subsequences
//at positions (0,2,6) and (1,4,7)



int[][] memo;

public int lrs(int i, int j, String s) {
    if (i == 0 || j == 0) return 0;
    if (memo[i][j] != -1) return memo[i][j];

    // same char AND different positions
    if (s.charAt(i-1) == s.charAt(j-1) && i != j)
        return memo[i][j] = 1 + lrs(i-1, j-1, s);

    return memo[i][j] = Math.max(
            lrs(i-1, j, s),
            lrs(i, j-1, s)
    );
}


public int longestRepeatingSubsequence(String s) {
        int n = s.length();
        memo = new int[n + 1][n + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
     return lrs(n, n, s);
}


public int longestRepeatingSubsequence1(String s) {
    int n = s.length();
    int[][] dp = new int[n + 1][n + 1];

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            // match only if same char AND different index
            if (s.charAt(i-1) == s.charAt(j-1) && i != j)
                dp[i][j] = 1 + dp[i-1][j-1];
            else
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
    }

    return dp[n][n];
}


public int longestRepeatingSubsequence2(String s) {
    int n = s.length();
    int[] prev = new int[n + 1];
    int[] curr = new int[n + 1];

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (s.charAt(i-1) == s.charAt(j-1) && i != j)
                curr[j] = 1 + prev[j-1];
            else
                curr[j] = Math.max(prev[j], curr[j-1]);
        }
        prev = curr.clone();
        curr = new int[n + 1];
    }

    return prev[n];
}



//        **Time:** `O(n²)` | **Space:** `O(n)` ✅
//        ### Detailed Dry Run
//
//`s = "AABEBCDD"` → `n = 8`
//
//Run `LCS(s, s)` with `i != j` condition:
//        ```
//        ""  A   A   B   E   B   C   D   D
// ""  [ 0,  0,  0,  0,  0,  0,  0,  0,  0 ]
//A   [ 0,  0,  1,  1,  1,  1,  1,  1,   ]
//A   [ 0,  1,  1,  1,  1,  1,  1,  1,   ]
//B   [ 0,  1,  1,  1,  1,  2,  2,  2,   ]
//E   [ 0,  1,  1,  1,  1,  2,  2,  2,   ]
//B   [ 0,  1,  1,  2,  2,  2,  2,  2,   ]
//C   [ 0,  1,  1,  2,  2,  2,  2,  2,   ]
//D   [ 0,  1,  1,  2,  2,  2,  2,  2,   ]
//D   [ 0,  1,  1,  2,  2,  2,  2,  3,  3 ]
//
//Answer: dp[8][8] = 3  ✅







//Edit Distance



public int solve1(int i, int j, String s1, String s2) {
    // base cases
    if (i == 0) return j;   // insert all remaining s2 chars
    if (j == 0) return i;   // delete all remaining s1 chars

    if (s1.charAt(i-1) == s2.charAt(j-1))
        return solve1(i-1, j-1, s1, s2);  // no op needed

    return 1 + Math.min(
            solve1(i, j-1, s1, s2),    // insert
            Math.min(
                    solve1(i-1, j, s1, s2),    // delete
                    solve1(i-1, j-1, s1, s2))  // replace
    );
}



// call: solve(n, m, s1, s2)
//
//
//
//Tabulation
public int minDistance(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    // base cases
    for (int i = 0; i <= n; i++) dp[i][0] = i;  // delete all
    for (int j = 0; j <= m; j++) dp[0][j] = j;  // insert all

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1))
                dp[i][j] = dp[i-1][j-1];          // no op
            else
                dp[i][j] = 1 + Math.min(
                        dp[i][j-1],                    // insert
                        Math.min(
                                dp[i-1][j],                    // delete
                                dp[i-1][j-1])                  // replace
                );
        }
    }

    return dp[n][m];
}



//Space Optimization

public int minDistance1(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[] prev = new int[m + 1];
    int[] curr = new int[m + 1];

    // base case: first row
    for (int j = 0; j <= m; j++) prev[j] = j;

    for (int i = 1; i <= n; i++) {
        curr[0] = i;   // base case: first col
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1))
                curr[j] = prev[j-1];              // no op
            else
                curr[j] = 1 + Math.min(
                        curr[j-1],                    // insert
                        Math.min(
                                prev[j],                      // delete
                                prev[j-1])                    // replace
                );
        }
        prev = curr.clone();
    }

    return prev[m];
}
////```
////
////        **Time:** `O(n×m)` | **Space:** `O(m)` ✅
////
////        ---
////
////        ### Detailed Dry Run
////
////`s = "horse"`, `s2 = "ros"` → Expected: `3`
////
////        **Base cases:**
////        ```
////dp[i][0] = i  →  0,1,2,3,4,5
////dp[0][j] = j  →  0,1,2,3
////        ```
////
////        **Full DP Table:**
////        ```
////        ""   r    o    s////  ""  [  0,  1,   2,    ]
////    h   [  1,  1,   2,    ]
////    o   [  2,  2,   1,    ]
////    r   [  3,  2,   2,    ]
////    s   [  4,  3,   3,    ]
////    e   [  5,  4,   4,   3  ]
////
////    Answer: dp[5][3] = 3  ✅

//////
////        ### Step-by-step Transformation
//```
//s1 = "horse"  →  s2 = "ros"
//
//Step 1: horse → rorse   (replace 'h' with 'r')
//Step 2: rorse → rose    (delete 'r' at index 2)
//Step 3: rose  → ros     (delete 'e')

//Total: 3 operations ✅








//      Wildcard Pattern

public boolean solve2(int i, int j, String s, String p) {
    // both exhausted → match
    if (i == 0 && j == 0) return true;

    // pattern exhausted, string remaining → no match
    if (j == 0) return false;

    // string exhausted, pattern remaining
    // only if all remaining pattern chars are '*'
    if (i == 0) {
        for (int k = 1; k <= j; k++)
            if (p.charAt(k-1) != '*') return false;
        return true;
    }

    if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?')
        return solve2(i-1, j-1, s, p);

    if (p.charAt(j-1) == '*')
        return solve2(i, j-1, s, p)     // '*' = empty
                || solve2(i-1, j, s, p);    // '*' = one+ chars

    return false;
}



//// call: solve(n, m, s, p)


public boolean isMatch(String s, String p) {
    int n = s.length(), m = p.length();
    boolean[][] dp = new boolean[n + 1][m + 1];

    // base cases
    dp[0][0] = true;

    // empty string vs pattern starting with '*'
    for (int j = 1; j <= m; j++) {
        if (p.charAt(j-1) == '*')
            dp[0][j] = dp[0][j-1];   // '*' matches empty
        else
            break;                    // once non-'*' found, stop
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?')
                dp[i][j] = dp[i-1][j-1];             // char match or '?'
            else if (p.charAt(j-1) == '*')
                dp[i][j] = dp[i][j-1]                // '*' = empty
                        || dp[i-1][j];              // '*' = one+ chars
            else
                dp[i][j] = false;                    // no match
        }
    }

    return dp[n][m];
}

////  Space optimized

public boolean isMatch1(String s, String p) {
    int n = s.length(), m = p.length();
    boolean[] prev = new boolean[m + 1];
    boolean[] curr = new boolean[m + 1];

    // base case: empty string
    prev[0] = true;
    for (int j = 1; j <= m; j++) {
        if (p.charAt(j-1) == '*') prev[j] = prev[j-1];
        else break;
    }

    for (int i = 1; i <= n; i++) {
        curr[0] = false;   // non-empty string, empty pattern
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?')
                curr[j] = prev[j-1];
            else if (p.charAt(j-1) == '*')
                curr[j] = curr[j-1] || prev[j];
            else
                curr[j] = false;
        }
        prev = curr.clone();
        curr = new boolean[m + 1];
    }

    return prev[m];
}


//        ### Detailed Dry Run 1
//
//        `s = "adceb"`, `p = "a*b"` → Expected: `true`
//
//        **Base cases:*
//        **Full DP Table:**
//        ```
//        ""     a      *     b
//  ""  [ T,     F,     F,    F  ]
//  a   [ F,     T,     T,    F  ]
//  d   [ F,     F,     T,    F  ]
//  c   [ F,     F,     T,    F  ]
//  e   [ F,     F,     T,    F  ]
//  b   [ F,     F,     T,     T  ]
//
////Answer: dp[5][3] = true  ✅

//        ### Complexity Summary
//
//| Approach | Time | Space |
//        |---|---|---|
//        | Recursion | `O(2^(n+m))` | `O(n+m)` stack |
//        | Memoization | `O(n×m)` | `O(n×m) + stack` |
//        | Tabulation | `O(n×m)` | `O(n×m)` |
//        | Space Optimised | `O(n×m)` | `O(m)` ✅ |



//        ### Key Takeaways
//        1. '?' → same as char match → dp[i-1][j-1]
//        2. '*' → two choices:
//          empty      → dp[i][j-1]
//          one+ chars → dp[i-1][j]
//        3. Base → dp[0][0] = true
//dp[0][j] = true only if all p[1..j] are '*'
//dp[i][0] = false
//        4. Answer → dp[n][m]

}
