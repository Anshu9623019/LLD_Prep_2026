package DSA.Recursion;

//🔹 Problem: Combination Sum
//Input:
//
//An array of distinct integers candidates[]
//
//A target integer target
//
//Output:
//
//All unique combinations where numbers sum to target
//
//Each number in candidates can be used unlimited times
//
//Input: candidates = [2,3,6,7], target = 7
//Output: [[2,2,3],[7]]
//
//Explanation:
//        - 2+2+3 = 7
//        - 7 = 7

//Input: candidates = [2,3,5], target = 8
//Output: [[2,2,2,2],[2,3,3],[3,5]]
//makefile
//        Copy
//Edit
//Input: candidates = [2], target = 1
//Output: []


import org.example.OppsByKunalKushwaha.Access.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CombinationSum {


    public static void solve(int arr[],ArrayList<ArrayList<Integer>> li,ArrayList<Integer> tempList,int k,int index){
        if(k<0){
            return;
        }
        if(k==0){
            li.add(new ArrayList<>(tempList));
            return;
        }
        for(int i=index;i<arr.length;i++){
                tempList.add(arr[i]);
                solve(arr,li,tempList,k-arr[i],i);
                tempList.remove(tempList.size()-1);
        }
    }
    public static void main(String[] args) {
        int arr[] = {2,3,5};
        int k = 8;
        int n = arr.length;
        ArrayList<ArrayList<Integer>> li = new ArrayList<>();
        solve(arr,li,new ArrayList<>(),k,0);
        System.out.println(li);
    }
}
