package DSA.Recursion;

import java.util.ArrayList;

public class GenerateParenthesis {

    public static void solve(ArrayList<String> ans,String sublist,int left,int right){
        if(left>right){
            return;
        }
        if(left==0 && right==0){
            ans.add(sublist);
            return;
        }
        if(left>0){
            solve(ans,sublist+"(",left-1,right);
        }
        if(right>0){
            solve(ans,sublist+")",left,right-1);
        }
    }
    public static void main(String[] args) {
        int n = 3;
        ArrayList<String> li = new ArrayList<>();
        solve(li,"",n,n);
        System.out.println(li);
    }
}
