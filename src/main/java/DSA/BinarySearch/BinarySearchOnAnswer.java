package DSA.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BinarySearchOnAnswer {

    //Find Square root of a number
    // Function to find floor of square root using linear search
    public int floorSqrt(int n) {
        // Variable to store answer
        int ans = 0;

        // Run loop from 1 to n
        for (int i = 1; i <= n; i++) {
            // Check if i*i <= n
            if ((long)(i) * i <= n) {
                // Update answer
                ans = i;
            } else {
                // Break when i*i > n
                break;
            }
        }
        // Return final answer
        return ans;
    }


    // Function to find N-th root of M using binary search x^n
    public int nthRoot(int n, int m) {
        // Set low and high for binary search
        int low = 1, high = m;

        // Start binary search
        while (low <= high) {
            // Calculate mid
            int mid = (low + high) / 2;

            // Store result of mid^n
            long ans = 1;
            for (int i = 0; i < n; i++) {
                ans *= mid;
                if (ans > m) break;
            }

            // If mid^n equals m
            if (ans == m) return mid;

            // If mid^n is less than m
            if (ans < m) low = mid + 1;

                // If mid^n is more than m
            else high = mid - 1;
        }

        // Return -1 if not found
        return -1;
    }

    // Koko eating bananas
    // Function to calculate total hours at given speed
    private int calculateTotalHours(int[] piles, int speed) {
        int totalH = 0;
        for (int bananas : piles) {
            totalH += (int)Math.ceil((double)bananas / speed);
        }
        return totalH;
    }

    // Function to find minimum eating speed
    public int minEatingSpeed(int[] piles, int h) {
        // Find maximum element
        int maxPile = Arrays.stream(piles).max().getAsInt();

        // Initialize low and high pointers
        int low = 1, high = maxPile;
        int ans = maxPile;

        // Binary search on answer space
        while (low <= high) {
            int mid = (low + high) / 2;
            int totalH = calculateTotalHours(piles, mid);

            // If possible, try smaller speed
            if (totalH <= h) {
                ans = mid;
                high = mid - 1;
            }
            // Otherwise, try larger speed
            else {
                low = mid + 1;
            }
        }
        return ans;
    }


    // Function to check if it's possible to make m bouquets on or before 'day'
    public static boolean isPossible(int[] bloomDays, int day, int m, int k) {
        int count = 0; // counts consecutive flowers that bloomed on or before 'day'
        int bouquets = 0; // number of bouquets made

        for (int bloom : bloomDays) {
            if (bloom <= day) {
                count++; // flower is ready
                if (count == k) {
                    bouquets++; // form one bouquet
                    count = 0; // reset count for next bouquet
                }
            } else {
                count = 0; // break in consecutive flowers
            }
        }

        return bouquets >= m; // check if required bouquets can be made
    }

    // Main function to find minimum day to make m bouquets
    public static int roseGarden(int[] bloomDays, int k, int m) {
        long required = (long) m * k;
        if (required > bloomDays.length) return -1; // not enough flowers

        int minDay = Integer.MAX_VALUE;
        int maxDay = Integer.MIN_VALUE;

        // Find the minimum and maximum bloom day
        for (int bloom : bloomDays) {
            minDay = Math.min(minDay, bloom);
            maxDay = Math.max(maxDay, bloom);
        }

        // Binary search between minDay and maxDay
        int low = minDay, high = maxDay, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible(bloomDays, mid, m, k)) {
                result = mid; // possible to form bouquets, try earlier
                high = mid - 1;
            } else {
                low = mid + 1; // need more days
            }
        }

        return result;
    }

    // // Helper method to calculate sum by divisor
        private int sumByD(int[] arr, int div) {
            int sum = 0;
            for (int num : arr) {
                sum += Math.ceil((double) num / div);
            }
            return sum;
        }

        // Method to find the smallest divisor using binary search
        public int smallestDivisor(int[] arr, int limit) {
            if (arr.length > limit) return -1;

            int low = 1;
            int high = Arrays.stream(arr).max().getAsInt();

            while (low <= high) {
                int mid = (low + high) / 2;
                if (sumByD(arr, mid) <= limit) {
                    high = mid - 1; // Try smaller divisor
                } else {
                    low = mid + 1;  // Try larger divisor
                }
            }

            return low;
        }



    // Function to calculate how many days are needed to ship
    // all packages with the given ship capacity
    int daysNeeded(int[] weights, int capacity) {
        // Initialize count of days to 1
        int days = 1;
        // Initialize current load on ship to 0
        int currentLoad = 0;

        // Iterate over all package weights
        for (int w : weights) {
            // Check if adding current package exceeds capacity
            if (currentLoad + w > capacity) {
                // If yes, increase days count since we start a new day
                days++;
                // Reset current load to current package weight
                currentLoad = w;
            } else {
                // Else, add current package weight to current load
                currentLoad += w;
            }
        }
        // Return total days required
        return days;
    }

    // Function to find minimum ship capacity to ship all packages within d days
    int shipWithinDays(int[] weights, int d) {
        // Calculate minimum capacity as max weight in packages
        int left = Arrays.stream(weights).max().getAsInt();
        // Calculate maximum capacity as sum of all weights
        int right = Arrays.stream(weights).sum();

        // Binary search between left and right capacity values
        while (left < right) {
            // Calculate mid value to test
            int mid = left + (right - left) / 2;
            // Calculate how many days needed for capacity mid
            int needed = daysNeeded(weights, mid);

            // If days needed is less or equal to allowed days,
            // try to find smaller capacity on left side
            if (needed <= d) {
                right = mid;
            } else {
                // Else, need more capacity, search on right side
                left = mid + 1;
            }
        }
        // Return minimum capacity found
        return left;
    }


    // Find Missing number
    int findMissing(int arr[],int k){
        int cnt = 0;
        for(int ele : arr){
            if(ele<=k){
                cnt++;
            }
        }
        return k - cnt;
    }
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        int max  = 1;
        for(int ele : arr){
            max = Math.max(ele,max);
        }
        int l = 1;
        int  h = Integer.MAX_VALUE;
        int ans = -1;
        while(l<=h){
            int mid = l + (h-l)/2;
            if(findMissing(arr,mid)>=k){
                ans = mid;
                h = mid-1;
            }else{
                l = mid+1;
            }
        }
        return ans;
    }


    // Function to check if cows can be placed with distance d
    public boolean canPlace(int[] stalls, int cows, int d) {
        // Place first cow at first stall
        int count = 1;
        int lastPos = stalls[0];

        // Loop through stalls
        for (int i = 1; i < stalls.length; i++) {
            // If stall is at least d away from last placed cow
            if (stalls[i] - lastPos >= d) {
                // Place cow here
                count++;
                // Update last position
                lastPos = stalls[i];
            }
            // If all cows are placed successfully
            if (count >= cows) return true;
        }
        // Could not place all cows
        return false;
    }

    // Function to maximize minimum distance
    public int aggressiveCows(int[] stalls, int cows) {
        // Sort stalls
        Arrays.sort(stalls);

        // Define search space
        int low = 1;
        int high = stalls[stalls.length - 1] - stalls[0];
        int ans = 0;

        // Binary search
        while (low <= high) {
            // Find mid distance
            int mid = low + (high - low) / 2;

            // If placement possible
            if (canPlace(stalls, cows, mid)) {
                // Store answer
                ans = mid;
                // Try bigger distance
                low = mid + 1;
            }
            else {
                // Try smaller distance
                high = mid - 1;
            }
        }
        // Return result
        return ans;
    }


    // Allocate pages
    public static int countStudents(ArrayList<Integer> arr, int pages) {
        int n = arr.size(); // size of array
        int students = 1; //Students are initially 1
        long pagesStudent = 0;
        for (int i = 0; i < n; i++) {
            if (pagesStudent + arr.get(i) <= pages) {
                // add pages to current student
                pagesStudent += arr.get(i);
            } else {
                // add pages to next student
                students++;
                pagesStudent = arr.get(i);
            }
        }
        return students;
    }

    public static int findPages(ArrayList<Integer> arr, int n, int m) {
        // book allocation impossible
        if (m > n)
            return -1;

        int low = Collections.max(arr);
        int high = arr.stream().mapToInt(Integer::intValue).sum();
        while (low <= high) {
            int mid = (low + high) / 2;
            int students = countStudents(arr, mid);
            if (students > m) {
                low = mid + 1;  //Trim down the left part of the arry
            } else {
                high = mid - 1; //Trim down the right part of the array
            }
        }
        return low;
    }

    // Split array

        public int splitArray(int[] nums, int k) {

            int low = 0;
            int high = 0;

            for (int num : nums) {
                low = Math.max(low, num);
                high += num;
            }

            while (low < high) {

                int mid = low + (high - low) / 2;

                if (canSplit(nums, k, mid)) {
                    // mid is possible
                    // try smaller
                    high = mid;
                } else {
                    // mid is impossible
                    // need larger limit
                    low = mid + 1;
                }
            }

            return low;
        }

        private boolean canSplit(int[] nums, int k, int maxSum) {

            int subarrays = 1;
            int currentSum = 0;

            for (int num : nums) {

                if (currentSum + num <= maxSum) {

                    currentSum += num;

                } else {

                    subarrays++;
                    currentSum = num;

                    if (subarrays > k) {
                        return false;
                    }
                }
            }

            return true;
        }


        // Painter partition

        public int painterPartition(int[] boards, int k) {

            int low = 0;
            int high = 0;

            for (int board : boards) {
                low = Math.max(low, board);
                high += board;
            }

            while (low < high) {

                int mid = low + (high - low) / 2;

                if (canPaint(boards, k, mid)) {

                    // Possible → try smaller maximum
                    high = mid;

                } else {

                    // Impossible → need larger maximum
                    low = mid + 1;
                }
            }

            return low;
        }

        private boolean canPaint(
                int[] boards,
                int k,
                int limit) {

            int painters = 1;
            int currentWork = 0;

            for (int board : boards) {

                if (currentWork + board <= limit) {

                    currentWork += board;

                } else {

                    painters++;
                    currentWork = board;

                    if (painters > k) {
                        return false;
                    }
                }
            }

            return true;
        }

        // Minimize Max Distance to Gas Station — LC 774

            public double minmaxGasDist(int[] stations, int k) {

                double low = 0;
                double high = 0;

                // Maximum existing gap
                for (int i = 1; i < stations.length; i++) {
                    high = Math.max(
                            high,
                            stations[i] - stations[i - 1]
                    );
                }

                // Precision: 1e-6
                while (high - low > 1e-6) {

                    double mid = low + (high - low) / 2.0;

                    if (canPlace(stations, k, mid)) {

                        // mid is possible
                        // try smaller distance
                        high = mid;

                    } else {

                        // mid is too small
                        low = mid;
                    }
                }

                return high;
            }

            private boolean canPlace(
                    int[] stations,
                    int k,
                    double dist) {

                int required = 0;

                for (int i = 1; i < stations.length; i++) {

                    double gap =
                            stations[i] - stations[i - 1];

                    int needed =
                            (int) Math.ceil(gap / dist) - 1;

                    required += needed;

                    if (required > k) {
                        return false;
                    }
                }

                return true;
            }


            // Median of sorted Array
        public double findMedianSortedArrays(
                int[] nums1,
                int[] nums2) {

            // Always binary search smaller array
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int n = nums1.length;
            int m = nums2.length;

            int low = 0;
            int high = n;

            int totalLeft = (n + m + 1) / 2;

            while (low <= high) {

                int cut1 = low + (high - low) / 2;

                int cut2 = totalLeft - cut1;

                // Boundary values
                int left1 = cut1 == 0
                        ? Integer.MIN_VALUE
                        : nums1[cut1 - 1];

                int right1 = cut1 == n
                        ? Integer.MAX_VALUE
                        : nums1[cut1];

                int left2 = cut2 == 0
                        ? Integer.MIN_VALUE
                        : nums2[cut2 - 1];

                int right2 = cut2 == m
                        ? Integer.MAX_VALUE
                        : nums2[cut2];

                // Correct partition
                if (left1 <= right2 &&
                        left2 <= right1) {

                    // Odd
                    if ((n + m) % 2 == 1) {
                        return Math.max(left1, left2);
                    }

                    // Even
                    int leftMax =
                            Math.max(left1, left2);

                    int rightMin =
                            Math.min(right1, right2);

                    return (leftMax + rightMin) / 2.0;
                }

                // cut1 is too far right
                else if (left1 > right2) {
                    high = cut1 - 1;
                }

                // cut1 is too far left
                else {
                    low = cut1 + 1;
                }
            }

            return 0.0;
        }

        // Kth element of two sorted array

            public int kthElement(int[] a, int[] b, int k) {

                // Always binary search on smaller array
                if (a.length > b.length) {
                    return kthElement(b, a, k);
                }

                int n = a.length;
                int m = b.length;

                int low = Math.max(0, k - m);
                int high = Math.min(k, n);

                while (low <= high) {

                    int cut1 = low + (high - low) / 2;

                    int cut2 = k - cut1;

                    // Left values
                    int leftA = cut1 == 0
                            ? Integer.MIN_VALUE
                            : a[cut1 - 1];

                    int leftB = cut2 == 0
                            ? Integer.MIN_VALUE
                            : b[cut2 - 1];

                    // Right values
                    int rightA = cut1 == n
                            ? Integer.MAX_VALUE
                            : a[cut1];

                    int rightB = cut2 == m
                            ? Integer.MAX_VALUE
                            : b[cut2];

                    // Correct partition
                    if (leftA <= rightB &&
                            leftB <= rightA) {

                        return Math.max(leftA, leftB);
                    }

                    // Took too many elements from A
                    if (leftA > rightB) {

                        high = cut1 - 1;
                    }

                    // Took too few elements from A
                    else {

                        low = cut1 + 1;
                    }
                }

                return -1;
            }








}
