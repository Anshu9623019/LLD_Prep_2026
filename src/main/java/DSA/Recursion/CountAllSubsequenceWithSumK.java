package DSA.Recursion;

//🔹 Problem: Count Subsequences with Sum K
//Description:
//Given an array arr[] of size n and an integer K, count the number of subsequences whose sum is exactly K.
//A subsequence means we can pick or skip each element (order preserved).
//
//Not necessarily contiguous (unlike subarray).

//Input: arr = [1, 2, 1], K = 2
//Output: 2
//Explanation: Subsequences with sum 2 are:
//        - [1, 1]
//        - [2]

import org.example.OppsByKunalKushwaha.Access.A;

import java.util.ArrayList;

public class CountAllSubsequenceWithSumK {

    public static void solve(ArrayList<ArrayList<Integer>> li,int [] arr,int k,ArrayList<Integer> tempList,int i){
        if(k==0){
            li.add(new ArrayList<>(tempList));
            return;
        }
        if(i<0){
            return;
        }
        if(arr[i]<=k){
            tempList.add(arr[i]);
            solve(li,arr,k-arr[i],tempList,i-1);
            tempList.remove(tempList.size()-1);
            solve(li,arr,k,tempList,i-1);
        }else {
            solve(li,arr,k,tempList,i-1);
        }

    }

    public static void main(String[] args) {
        int k = 2;
        int arr[] = {1,2,1};
        int n = arr.length;
        ArrayList<ArrayList<Integer>> li = new ArrayList<>();
        solve(li,arr,k,new ArrayList<>(),n-1);
        System.out.println(li);
    }
}
