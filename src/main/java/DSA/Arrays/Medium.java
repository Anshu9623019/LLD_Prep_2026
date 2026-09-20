package DSA.Arrays;

import java.util.*;

public class Medium {
    public static void main(String[] args) {

    }

    // Variant 1: Check if two numbers sum to target using two-pointer approach
    public String twoSumExists(int[] arr, int target) {
        int n = arr.length;

        // Create an array of pairs [value, original_index]
        int[][] numsWithIndex = new int[n][2];

        // Store each element with its original index
        for (int i = 0; i < n; i++) {
            numsWithIndex[i][0] = arr[i]; // value
            numsWithIndex[i][1] = i;      // original index
        }

        // Sort the array based on the value, not index
        Arrays.sort(numsWithIndex, (a, b) -> Integer.compare(a[0], b[0]));

        // Initialize two pointers: one at start, one at end
        int left = 0, right = n - 1;

        // Run loop until pointers cross
        while (left < right) {
            // Calculate the sum of values at pointers
            int sum = numsWithIndex[left][0] + numsWithIndex[right][0];

            if (sum == target) {
                // Found the pair, return "YES"
                return "YES";
            } else if (sum < target) {
                // Sum is less than target, so move left pointer right to increase sum
                left++;
            } else {
                // Sum is greater than target, so move right pointer left to decrease sum
                right--;
            }
        }

        // If loop ends without returning, no pair found
        return "NO";
    }

    // Variant 2: Return indices of two numbers that sum to target
    public int[] twoSumIndices(int[] arr, int target) {
        int n = arr.length;
        int[][] numsWithIndex = new int[n][2];

        // Store element with original index
        for (int i = 0; i < n; i++) {
            numsWithIndex[i][0] = arr[i];
            numsWithIndex[i][1] = i;
        }

        // Sort by the value to apply two-pointer
        Arrays.sort(numsWithIndex, (a, b) -> Integer.compare(a[0], b[0]));

        int left = 0, right = n - 1;
        while (left < right) {
            int sum = numsWithIndex[left][0] + numsWithIndex[right][0];
            if (sum == target) {
                // Return original indices of the two numbers found
                return new int[] {numsWithIndex[left][1], numsWithIndex[right][1]};
            } else if (sum < target) {
                // Increase sum by moving left pointer forward
                left++;
            } else {
                // Decrease sum by moving right pointer backward
                right--;
            }
        }

        // No pair found
        return new int[] {-1, -1};
    }




    // sor 0,1,2
        public void sortColors(int[] nums) {

            int n = nums.length;

            // Boundary of 0s
            int start = 0;

            // Current unknown element
            int mid = 0;

            // Boundary of 2s
            int last = n - 1;

            while (mid <= last) {

                // Current element is 0
                if (nums[mid] == 0) {

                    int temp = nums[start];
                    nums[start] = nums[mid];
                    nums[mid] = temp;

                    start++;
                    mid++;

                    // Current element is 2
                } else if (nums[mid] == 2) {

                    int temp = nums[last];
                    nums[last] = nums[mid];
                    nums[mid] = temp;

                    last--;

                    // DON'T increment mid

                    // Current element is 1
                } else {

                    mid++;
                }
            }
        }

        // Mejority Element

        public int majorityElement(int[] nums) {

            int count = 0;
            int candidate = 0;

            for(int num : nums){
                if(count==0){
                    candidate = num;
                }
                if(num==candidate){
                    count++;
                }else{
                    count--;
                }
            }


            return candidate;

        }


        // Kadane Algo

    public int maxSubArray(int[] nums) {

        int n = nums.length;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){

            sum = sum + nums[i];

            if(sum>max){
                max = sum;
            }
            if(sum<0){
                sum = 0;
            }
        }

        return max;
    }



    // Function to find maximum sum of subarrays and print the subarray having maximum sum
    public int maxSubArray1(int[] nums) {

        // Maximum sum
        long maxi = Long.MIN_VALUE;

        // Current sum of subarray
        long sum = 0;

        // Starting index of current subarray
        int start = 0;

        // Indices of the maximum sum subarray
        int ansStart = -1, ansEnd = -1;

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {

            // Update starting index if sum is reset
            if (sum == 0) {
                start = i;
            }

            // Add current element to the sum
            sum += nums[i];

            // Update maxi and subarray indices if current sum is greater
            if (sum > maxi) {
                maxi = sum;
                ansStart = start;
                ansEnd = i;
            }

            // Reset sum to 0 if it becomes negative
            if (sum < 0) {
                sum = 0;
            }
        }

        // Printing the subarray
        System.out.print("The subarray is: [");
        for (int i = ansStart; i <= ansEnd; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println("]");

        // Return the maximum subarray sum found
        return (int) maxi;
    }

     // Stock buy and sell
    public int maxProfit(int[] prices) {

        int profit = 0;
        int buy = Integer.MAX_VALUE;
        for(int ele : prices){
            if(ele<buy){
                buy = ele;
            }
            profit = Math.max(profit,ele-buy);
        }

        return profit;

    }

    //2149. Rearrange Array Elements by Sign

//    Input: nums = [3,1,-2,-5,2,-4]
//    Output: [3,-2,1,-5,2,-4]

    public int[] rearrangeArray(int[] nums) {
        int neg = 1;
        int pos = 0;
        int n = nums.length;
        int ans[] = new int[n];

        for(int i =0;i<n;i++){
            if(nums[i]>0){
                ans[pos] = nums[i];
                pos = pos +2;
            }else{
                ans[neg] = nums[i];
                neg = neg +2;
            }
        }

        return ans;
    }

    // Next permutation

//    Traverse from the end and find the first index where the current digit is smaller than the next one (this is the "breaking point").
//    Then again traverse from the end to find the first digit greater than the breaking point digit and swap them.
//    Finally, reverse the part of the array to the right of the breaking point to get the smallest next permutation.
//    If no such breaking point exists (entire array is descending), just reverse the whole array.

    // Function to find next permutation
    public void nextPermutation(int[] nums) {
        // Set index to -1
        int index = -1;

        // Find the first decreasing element from end
        for (int i = nums.length - 2; i >= 0; i--) {
            // If smaller found
            if (nums[i] < nums[i + 1]) {
                // Store index
                index = i;
                break;
            }
        }

        // If no index found
        if (index == -1) {
            // Reverse the entire array
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // Find just larger element
        for (int i = nums.length - 1; i > index; i--) {
            // Swap them
            if (nums[i] > nums[index]) {
                swap(nums, i, index);
                break;
            }
        }

        // Reverse part after index
        reverse(nums, index + 1, nums.length - 1);
    }

    // Helper to reverse array
    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    // Helper to swap
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


//    Input:
//    arr = [4, 7, 1, 0]
//    Output:
//            7 1 0
//    Explanation:
//    The rightmost element (0) is always a leader.
//            7 and 1 are greater than the elements to their right, making them leaders as well.


//    Set a variable max to the last element of the array (nums[sizeOfArray - 1]), as the last element is always a leader.
//    Create an empty list ans to store the leader elements, and initially add the last element of the array to this list, as it is always a leader.
//    Start from the second last element (index = sizeOfArray - 2) and move towards the first element (index = 0).
//    For each element, compare it with the max variable. If the current element is greater than max, add this element to the ans list and update max to the current element.
//    After processing all elements, the ans list will contain all the leader elements in reverse order. Reverse the ans list and return it.

    // Function to find the leaders in an array.
    public ArrayList<Integer> leaders(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<>();

        if (nums.length == 0) {
            return ans;
        }

        // Last element of the array is always a leader
        int max = nums[nums.length - 1];
        ans.add(nums[nums.length - 1]);

        // Check elements from right to left
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > max) {
                ans.add(nums[i]);
                max = nums[i];
            }
        }

        /* Reverse the list to match
        the required output order */
        Collections.reverse(ans);

        // Return the leaders
        return ans;
    }

//    Input:
//    nums = [100, 4, 200, 1, 3, 2]
//    Output:
//            4

    public int longestConsecutive(int[] nums) {
        // Get the length of the array
        int n = nums.length;

        // If the array is empty, no sequence exists
        if (n == 0) return 0;

        // Variable to store the longest sequence length found
        int longest = 1;

        // HashSet to store unique elements for O(1) lookup
        Set<Integer> st = new HashSet<>();

        // Add all elements to the set to remove duplicates
        for (int i = 0; i < n; i++) {
            st.add(nums[i]);
        }

        /* Loop through each element in the set to find
           the starting point of consecutive sequences */
        for (int it : st) {
            // If there is no number before 'it', it’s the start of a sequence
            if (!st.contains(it - 1)) {
                // Start the count for this sequence
                int cnt = 1;
                // Store the current number
                int x = it;

                // Keep checking for the next consecutive number
                while (st.contains(x + 1)) {
                    // Move to the next number in sequence
                    x = x + 1;
                    // Increment the length of current sequence
                    cnt = cnt + 1;
                }

                // Update the longest sequence length if needed
                longest = Math.max(longest, cnt);
            }
        }

        // Return the length of the longest sequence
        return longest;
    }



        public void setZeroes(int[][] matrix) {

            int m = matrix.length;
            int n = matrix[0].length;

            boolean firstRowZero = false;
            boolean firstColZero = false;

            // 1. Check whether first row originally contains 0
            for (int j = 0; j < n; j++) {
                if (matrix[0][j] == 0) {
                    firstRowZero = true;
                    break;
                }
            }

            // 2. Check whether first column originally contains 0
            for (int i = 0; i < m; i++) {
                if (matrix[i][0] == 0) {
                    firstColZero = true;
                    break;
                }
            }

            // 3. Use first row and first column as markers
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {

                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }

            // 4. Zero inner matrix using markers
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {

                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            // 5. Zero first row if originally required
            if (firstRowZero) {
                for (int j = 0; j < n; j++) {
                    matrix[0][j] = 0;
                }
            }

            // 6. Zero first column if originally required
            if (firstColZero) {
                for (int i = 0; i < m; i++) {
                    matrix[i][0] = 0;
                }
            }
        }



        // Rotate matrix by 90 degrees
        public void rotate(int[][] matrix) {

            int n = matrix.length;

            // Step 1: Transpose
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {

                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }

            // Step 2: Reverse every row
            for (int i = 0; i < n; i++) {

                int left = 0;
                int right = n - 1;

                while (left < right) {

                    int temp = matrix[i][left];
                    matrix[i][left] = matrix[i][right];
                    matrix[i][right] = temp;

                    left++;
                    right--;
                }
            }
        }



    public List<Integer> spiralOrder(int[][] mat) {
        // Define ans list to store the result.
        List<Integer> ans = new ArrayList<>();

        int n = mat.length; // no. of rows
        int m = mat[0].length; // no. of columns

        // Initialize the pointers required for traversal.
        int top = 0, left = 0, bottom = n - 1, right = m - 1;

        // Loop until all elements are not traversed.
        while (top <= bottom && left <= right) {

            // For moving left to right
            for (int i = left; i <= right; i++)
                ans.add(mat[top][i]);

            top++;

            // For moving top to bottom.
            for (int i = top; i <= bottom; i++)
                ans.add(mat[i][right]);

            right--;

            // For moving right to left.
            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    ans.add(mat[bottom][i]);

                bottom--;
            }

            // For moving bottom to top.
            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    ans.add(mat[i][left]);

                left++;
            }
        }
        return ans;
    }




//    560. Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {

        // int n = nums.length;
        // int sum = 0;
        // int cnt = 0;
        // for(int i=0;i<n;i++){
        //     sum = 0;
        //     for(int j =i;j<n;j++){
        //         sum += nums[j];
        //         if(sum==k){
        //             cnt++;
        //         }
        //     }
        // }

        Map<Integer,Integer> mp = new HashMap<>();

        int n = nums.length;
        int sum = 0;
        int cnt = 0;
        mp.put(0,1);
        for(int i=0;i<n;i++){
            sum+= nums[i];
            int comp = sum - k;
            if(mp.containsKey(comp)){
                cnt += mp.get(comp);
            }
            mp.put(sum,mp.getOrDefault(sum,0)+1);
        }
        return cnt;
    }

}








}
