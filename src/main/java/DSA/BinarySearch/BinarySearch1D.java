package DSA.BinarySearch;

import java.util.List;

public class BinarySearch1D {

    //Search In sorted Array

        public int search23(int[] nums, int target) {

            int n = nums.length;
            int s = 0;
            int e = n-1;

            while(s<=e){
                int mid = s + (e-s)/2;
                if(nums[mid]==target){
                    return mid;
                }else if(nums[mid]>target){
                    e = mid-1;
                }else{
                    s = mid+1;
                }
            }

            return -1;

        }


// Function to find the lower bound index using binary search
public int lowerBound(int[] arr, int x) {
    int low = 0;                  // Start index
    int high = arr.length - 1;    // End index
    int ans = arr.length;         // Default value if not found

    while (low <= high) {
        int mid = (low + high) / 2;  // Find mid index

        if (arr[mid] >= x) {
            ans = mid;            // Store possible answer
            high = mid - 1;       // Move left
        } else {
            low = mid + 1;        // Move right
        }
    }
    return ans;  // Return the lower bound index
}

//Upper bound
// Binary search to find upper bound
public int upperBound(int[] arr, int x) {
    int low = 0, high = arr.length - 1;
    int ans = arr.length;  // Default to length if not found

    while (low <= high) {
        int mid = (low + high) / 2;

        if (arr[mid] > x) {
            ans = mid;        // Store current index as potential answer
            high = mid - 1;   // Move left
        } else {
            low = mid + 1;    // Move right
        }
    }
    return ans;  // Return final answer
}

// Search Insert Position
// Function to find the insert position of x in sorted array
public int searchInsert(int[] arr, int x) {
    int n = arr.length;
    int low = 0, high = n - 1;
    int ans = n; // Default to end if x is greater than all elements

    while (low <= high) {
        int mid = (low + high) / 2;

        if (arr[mid] >= x) {
            // Potential answer found, try to go left
            ans = mid;
            high = mid - 1;
        } else {
            // Go right
            low = mid + 1;
        }
    }
    return ans;
}

    // Function to find floor
    public int findFloor(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= x) {
                ans = arr[mid];     // Potential floor
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Function to find ceiling
    public int findCeil(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                ans = arr[mid];     // Potential ceil
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }


    // find last index of key using binary search
    public int solve(int n, int key, List<Integer> v) {
        // initialize search bounds and result
        int start = 0;
        int end = n - 1;
        int res = -1;

        // binary search loop
        while (start <= end) {
            // compute mid safely
            int mid = start + (end - start) / 2;
            // when match found, store index and move right
            if (v.get(mid) == key) {
                res = mid;
                start = mid + 1;
            }
            // when key is smaller, move left
            else if (key < v.get(mid)) {
                end = mid - 1;
            }
            // otherwise move right
            else {
                start = mid + 1;
            }
        }
        // return last occurrence or -1
        return res;
    }

    //Search in rotated sorted array-I
    // Function to search target in rotated sorted array using binary search
    public int search13(int[] nums, int target) {

        // Initialize search space
        int low = 0;
        int high = nums.length - 1;

        // Continue while there is still a valid search range
        while (low <= high) {

            // Calculate middle index
            int mid = (low + high) / 2;

            // If target found, return index
            if (nums[mid] == target)
                return mid;

            // If left part is sorted
            if (nums[low] <= nums[mid]) {

                // If target lies within sorted left part
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                }
                // Else, search in right half
                else {
                    low = mid + 1;
                }
            }

            // Else, right part is sorted
            else {

                // If target lies within sorted right part
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                }
                // Else, search in left half
                else {
                    high = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }


    //Search in rotated sorted array-II
    public boolean searchInRotatedSortedArrayII(int[] arr, int k) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // If mid element is the target
            if (arr[mid] == k) return true;

            // Handle duplicates: cannot determine sorted side
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low++;
                high--;
                continue;
            }

            // Left half is sorted
            if (arr[low] <= arr[mid]) {
                if (arr[low] <= k && k <= arr[mid]) {
                    high = mid - 1; // Search left
                } else {
                    low = mid + 1;  // Search right
                }
            }
            // Right half is sorted
            else {
                if (arr[mid] <= k && k <= arr[high]) {
                    low = mid + 1;  // Search right
                } else {
                    high = mid - 1; // Search left
                }
            }
        }

        return false; // Not found
    }


    // Function to find the minimum element using binary search
    public int findMin(int[] nums) {

        // Initialize low and high pointers
        int low = 0, high = nums.length - 1;

        // Binary search loop
        while (low < high) {

            // Calculate mid index
            int mid = low + (high - low) / 2;

            // Check which half to discard
            if (nums[mid] > nums[high]) {

                // Minimum lies in right half
                low = mid + 1;

            } else {

                // Minimum lies in left half (including mid)
                high = mid;
            }
        }

        // Return the minimum element
        return nums[low];
    }

    // Function to find rotation count using binary search
    public int findRotations(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        // Loop until low meets high
        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid element is greater than element at high,
            // smallest element lies to the right of mid
            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else {
                // Else smallest element is at mid or to the left
                high = mid;
            }
        }

        // When low == high, we found the smallest element
        return low;
    }


    // Function to find the single non-duplicate element using binary search
    public int singleNonDuplicate(int[] arr) {
        // Get the size of the array
        int n = arr.length;

        // Edge case: only one element in the array
        if (n == 1) return arr[0];

        // Edge case: first element is the unique one
        if (arr[0] != arr[1]) return arr[0];

        // Edge case: last element is the unique one
        if (arr[n - 1] != arr[n - 2]) return arr[n - 1];

        // Initialize binary search bounds (exclude first and last index)
        int low = 1, high = n - 2;

        // Perform binary search
        while (low <= high) {
            // Calculate middle index
            int mid = (low + high) / 2;

            // Check if middle element is the unique one
            if (arr[mid] != arr[mid + 1] && arr[mid] != arr[mid - 1]) {
                return arr[mid];
            }

            // If mid is in the left half (pairing is valid)
            if ((mid % 2 == 1 && arr[mid] == arr[mid - 1]) ||
                    (mid % 2 == 0 && arr[mid] == arr[mid + 1])) {
                // Move to the right half
                low = mid + 1;
            }
            // If mid is in the right half (pairing broken earlier)
            else {
                // Move to the left half
                high = mid - 1;
            }
        }

        // Dummy return (not reachable if input is valid)
        return -1;
    }


    // Function to find a peak element using binary search
    public int findPeakElement(int[] nums) {
        // Set left and right bounds
        int low = 0, high = nums.length - 1;

        // Binary search loop
        while (low < high) {
            // Find mid point
            int mid = (low + high) / 2;

            // If mid element is greater than next
            if (nums[mid] > nums[mid + 1]) {
                // Move to left half
                high = mid;
            } else {
                // Move to right half
                low = mid + 1;
            }
        }

        // Return peak index
        return low;
    }





}
