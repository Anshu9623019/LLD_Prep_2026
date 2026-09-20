package DSA.Arrays;

import java.util.*;

public class Hard {


    public static void main(String[] args) {

    }
    // Pascal's Triangle I
//    This is the part that generates the remaining elements.
//
//    The formula being used is:
//
//    Next = Previous × (i - j) / j
//
//    Why does this work?
//
//    Because Pascal's Triangle is based on combinations:
//
//    C(n,r) = n! / (r!(n-r)!)
//
//    But instead of calculating factorials repeatedly, we calculate the next combination from the previous one:
//
//    C(n,r) = C(n,r-1) × (n-r+1) / r
//
//    Your code uses:
//
//    n = i - 1
//    r = j
//
//    so:
//
//    C(i-1,j)
//      =
//    C(i-1,j-1) × (i-j) / j
//
//    That's exactly:
//
//    res = res * (i-j);
//    res = res / j;

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> li = new ArrayList<>();

        int n = numRows;

        for(int i = 1; i <= n; i++) {

            List<Integer> temp = new ArrayList<>();

            temp.add(1);

            int res = 1;

            for(int j = 1; j < i; j++) {

                res = res * (i-j);
                res = res / j;

                temp.add(res);
            }

            li.add(temp);
        }

        return li;
    }



    // Majority Element II — > n/3
        public List<Integer> majorityElement(int[] nums) {


            int count1 = 0;
            int count2 = 0;
            int mejority1 = Integer.MIN_VALUE;
            int mejority2 = Integer.MIN_VALUE;;
            int n = nums.length;
            for(int num : nums){
                if(count1==0 && num!=mejority2){
                    mejority1 = num;
                    count1++;
                }else if(count2==0 && num!=mejority1){
                    mejority2 = num;
                    count2++;
                }else if(mejority1==num ){
                    count1++;
                }else if(mejority2==num){
                    count2++;
                }else{
                    count1--;
                    count2--;
                }

            }

            count1 = 0;
            count2 = 0;

            for(int num : nums){
                if(num == mejority1){
                    count1++;
                }
                if(num == mejority2){
                    count2++;
                }
            }

            List<Integer> arr = new ArrayList<>();


            if(count1>=n/3+1){
                arr.add(mejority1);
            }
            if(count2>=n/3+1){
                arr.add(mejority2);
            }

            Collections.sort(arr);

            return arr;

        }

        // 3 Sum
        public List<List<Integer>> threeSum(int[] nums) {

            Arrays.sort(nums);
            int n = nums.length;
            List<List<Integer>> arr = new ArrayList<>();


            for(int i=0;i<n;i++){
                if(i>0 && nums[i-1]==nums[i]){
                    continue;
                };
                int k = n-1;
                int j = i+1;
                while(j<k){
                    if(j>i+1 && nums[j-1]==nums[j]){j++; continue;};
                    if(k!=n-1 && j<k && nums[k+1]==nums[k]){ k--; continue;};
                    int ans = nums[i] + nums[j] + nums[k];
                    if(ans == 0){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        arr.add(temp);
                        j++;
                        k--;
                    }else if(ans<0){
                        j++;
                    }else{
                        k--;
                    }
                }
            }
            return arr;
        }


        // 4 Sum

        public List<List<Integer>> fourSum(int[] nums, int target) {

            int n = nums.length; // size of the array
            List<List<Integer>> ans = new ArrayList<>();

            // sort the given array:
            Arrays.sort(nums);

            // calculating the quadruplets:
            for (int i = 0; i < n; i++) {
                // avoid the duplicates while moving i:
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                for (int j = i + 1; j < n; j++) {
                    // avoid the duplicates while moving j:
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                    // 2 pointers:
                    int k = j + 1;
                    int l = n - 1;
                    while (k < l) {
                        long sum = nums[i];
                        sum += nums[j];
                        sum += nums[k];
                        sum += nums[l];
                        if (sum == target) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[k]);
                            temp.add(nums[l]);
                            ans.add(temp);
                            k++;
                            l--;

                            // skip the duplicates:
                            while (k < l && nums[k] == nums[k - 1]) k++;
                            while (k < l && nums[l] == nums[l + 1]) l--;
                        } else if (sum < target) k++;
                        else l--;
                    }
                }
            }

            return ans;
        }


        //Length of the longest subarray with zero Sum

    // compute length of the longest subarray with sum 0
    public int maxLen(int[] A, int n) {
        // map prefix sum -> first index seen
        Map<Integer, Integer> mpp = new HashMap<>();
        // best length so far
        int maxi = 0;
        // running prefix sum
        int sum = 0;

        // iterate over the array
        for (int i = 0; i < n; i++) {
            // update running sum
            sum += A[i];

            // if sum is zero, subarray [0..i] has zero sum
            if (sum == 0) {
                // update best length
                maxi = i + 1;
            }
            // otherwise check if this sum was seen before
            else {
                // when seen, zero-sum segment between previous index + 1 and i
                if (mpp.containsKey(sum)) {
                    // maximize length
                    maxi = Math.max(maxi, i - mpp.get(sum));
                }
                // first time seeing this sum
                else {
                    // record index
                    mpp.put(sum, i);
                }
            }
        }

        // return best length
        return maxi;
    }


   // Count the number of subarrays with given xor K

//    Input: A = [4, 2, 2, 6, 4] , k = 6
//    Output: 4
//    Explanation: The subarrays having XOR of their elements as 6 are  [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], [6]


    public int countSubarrays(int[] A, int k) {
        // Store frequency of prefix XORs
        Map<Integer, Integer> freq = new HashMap<>();
        // Initialize with prefix XOR 0
        freq.put(0, 1);

        // Current prefix XOR
        int prefixXor = 0;
        // Answer count
        int count = 0;

        // Traverse array
        for (int num : A) {
            // Update prefix XOR
            prefixXor ^= num;

            // Compute required XOR
            int target = prefixXor ^ k;

            // If target exists in map, add its frequency
            if (freq.containsKey(target)) {
                count += freq.get(target);
            }

            // Store current prefix XOR in map
            freq.put(prefixXor, freq.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }




      // Merge Intervals

        public int[][] merge(int[][] in) {


            ArrayList<int[]> res = new ArrayList<>();


            if(in.length==0 || in==null){
                return res.toArray(new int[0][]);
            }

            Arrays.sort(in,(a,b)->a[0]-b[0]);

            int start = in[0][0];
            int end = in[0][1];

            for(int i[] : in){
                if(i[0]<=end){
                    end = Math.max(end,i[1]);
                }else{
                    res.add(new int[]{start,end});
                    start = i[0];
                    end = i[1];
                }
            }

            res.add(new int[]{start,end});

            return  res.toArray(new int[0][1]);
        }


        //Merge two sorted arrays without extra space


        public void swap(int []a1,int []a2,int m, int n) {
            if (a1[m] > a2[n]) {
                int temp = a1[m];
                a1[m] = a2[n];
                a2[n] = temp;
            }

        }
        public void merge(int[] nums1, int m, int[] nums2, int n) {

            int gap = (m+n)/2 + (m+n)%2;
            int length = m+n;
            while(gap>0){
                int left = 0;
                int right = left + gap;
                while(right<length){
                    if(left<m && right>=m){
                        swap(nums1,nums2,left,right-m);
                    }else if(left>=m){
                        swap(nums2,nums2,left-m,right-m);
                    }else{
                        swap(nums1,nums1,left,right);
                    }
                    left++;
                    right++;
                }
                if(gap==1){
                    break;
                }
                gap = gap/2 + gap%2;
            }

            for(int i=m;i<m+n;i++){
                nums1[i] = nums2[i-m];
            }
        }


        // Input:
    // nums = [1, 2, 3, 6, 7, 5, 7]
    //Output:
    // [7, 4]

//    Find the repeating and missing numbers


    public int[] findMissingRepeatingNumbers(int[] nums) {

        long n = nums.length;

        // Expected sum: 1 + 2 + ... + n
        long SN = (n * (n + 1)) / 2;

        // Expected square sum: 1² + 2² + ... + n²
        long S2N = (n * (n + 1) * (2 * n + 1)) / 6;

        long S = 0;
        long S2 = 0;

        for (int num : nums) {
            S += num;
            S2 += (long) num * num;
        }

        // X - Y
        long val1 = S - SN;

        // X² - Y²
        long val2 = S2 - S2N;

        // X + Y
        val2 = val2 / val1;

        // X = ((X-Y) + (X+Y)) / 2
        long x = (val1 + val2) / 2;

        // Y = X - (X-Y)
        long y = x - val1;

        return new int[]{(int) x, (int) y};
    }


    //  Count inversion
    class Solution {

        private void merge(int[] arr, int low, int mid, int high) {

            int[] temp = new int[high - low + 1];

            int left = low;
            int right = mid + 1;
            int k = 0;

            while (left <= mid && right <= high) {

                if (arr[left] <= arr[right]) {
                    temp[k++] = arr[left++];
                } else {
                    temp[k++] = arr[right++];
                }
            }

            while (left <= mid) {
                temp[k++] = arr[left++];
            }

            while (right <= high) {
                temp[k++] = arr[right++];
            }

            for (int i = low; i <= high; i++) {
                arr[i] = temp[i - low];
            }
        }

        private int countPairs(int[] arr, int low, int mid, int high) {

            int right = mid + 1;
            int count = 0;

            for (int i = low; i <= mid; i++) {

                while (right <= high &&
                        (long) arr[i] > 2L * arr[right]) {

                    right++;
                }

                count += right - (mid + 1);
            }

            return count;
        }

        private int mergeSort(int[] arr, int low, int high) {

            if (low >= high) {
                return 0;
            }

            int mid = low + (high - low) / 2;

            int count = 0;

            count += mergeSort(arr, low, mid);

            count += mergeSort(arr, mid + 1, high);

            // Count cross-half reverse pairs
            count += countPairs(arr, low, mid, high);

            // Normal merge
            merge(arr, low, mid, high);

            return count;
        }

        public int reversePairs(int[] nums) {
            return mergeSort(nums, 0, nums.length - 1);
        }
    }


        public int maxProduct(int[] nums) {

            int pre = 1;
            int suf = 1;
            int ans = Integer.MIN_VALUE;

            int n = nums.length;

            for (int i = 0; i < n; i++) {

                if (pre == 0) {
                    pre = 1;
                }

                if (suf == 0) {
                    suf = 1;
                }

                pre = pre * nums[i];
                suf = suf * nums[n - 1 - i];

                ans = Math.max(ans, Math.max(pre, suf));
            }

            return ans;
        }













}
