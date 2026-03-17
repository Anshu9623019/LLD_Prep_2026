package DSA.Recursion;

//Description
//Given an integer n, generate all binary strings of length n.
//
//A binary string is made up of only '0' and '1'.
//
//Return all possible strings.
//
//Example 1

//Input: n = 2
//Output: ["00", "01", "10", "11"]
//Example 2
//Input: n = 3
//Output: ["000", "001", "010", "011", "100", "101", "110", "111"]


import java.util.ArrayList;

public class GenarateAllBinaryString {

    public  static void solve(ArrayList<String> ans, String out,int n){
        if(n==0){
            ans.add(out);
            return;
        }
        String one = out + "1";
        String zero = out + "0";
        solve(ans,one,n-1);
        solve(ans,zero,n-1);
    }

    public static void main(String[] args) {

        ArrayList<String> ans = new ArrayList<>();
        solve(ans,"",3);
        System.out.println(ans);
    }
}
