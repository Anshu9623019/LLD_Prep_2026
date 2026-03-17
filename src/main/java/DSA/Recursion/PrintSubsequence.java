package DSA.Recursion;

import java.util.ArrayList;

public class PrintSubsequence {

    public static void solve(int arr[], ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> temp, int i) {
        if (i >= arr.length) {
            ans.add(new ArrayList<>(temp)); // copy the list
            return;
        }

        // Include arr[i]
        temp.add(arr[i]);
        solve(arr, ans, temp, i + 1);

        // Exclude arr[i]
        temp.remove(temp.size() - 1);
        solve(arr, ans, temp, i + 1);

//        // "Take" branch → create a new list with the current choice
//        ArrayList<Integer> takeList = new ArrayList<>(take);
//        takeList.add(arr[i]);
//        solve(arr, ans, takeList, i + 1);
//
//        // "Not-take" branch → use a copy of the current list without adding
//        ArrayList<Integer> notTakeList = new ArrayList<>(take);
//        solve(arr, ans, notTakeList, i + 1);
    }


    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        ArrayList<ArrayList<Integer>> li = new ArrayList<>();
        solve(arr,li,new ArrayList<>(),0);
        System.out.println(li);
    }
}
