package DSA.SlidingWindowTwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointer {

        public boolean isPalindrome(String s) {

            int left = 0;
            int right = s.length() - 1;

            while (left < right) {

                while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                    left++;
                }

                while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                    right--;
                }

                if (Character.toLowerCase(s.charAt(left)) !=
                        Character.toLowerCase(s.charAt(right))) {
                    return false;
                }

                left++;
                right--;
            }

            return true;
        }


        // 3 Sum
        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> ans = new ArrayList<>();

            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 2; i++) {

                // Skip duplicate first elements
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = nums.length - 1;

                while (left < right) {

                    int sum = nums[i] + nums[left] + nums[right];

                    if (sum < 0) {
                        left++;
                    }
                    else if (sum > 0) {
                        right--;
                    }
                    else {
                        ans.add(Arrays.asList(
                                nums[i],
                                nums[left],
                                nums[right]
                        ));

                        // Skip duplicates
                        while (left < right &&
                                nums[left] == nums[left + 1]) {
                            left++;
                        }

                        while (left < right &&
                                nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    }
                }
            }

            return ans;
        }


    // Container with Most Water
        public int maxArea(int[] height) {

            int left = 0;
            int right = height.length - 1;
            int maxArea = 0;

            while (left < right) {

                int area = Math.min(height[left], height[right])
                        * (right - left);

                maxArea = Math.max(maxArea, area);

                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

            return maxArea;
        }

    //Trapping Rain water

        public int trap(int[] height) {

            int left = 0;
            int right = height.length - 1;

            int maxLeft = 0;
            int maxRight = 0;

            int water = 0;

            while (left < right) {

                if (height[left] <= height[right]) {

                    if (height[left] >= maxLeft) {
                        maxLeft = height[left];
                    } else {
                        water += maxLeft - height[left];
                    }

                    left++;

                } else {

                    if (height[right] >= maxRight) {
                        maxRight = height[right];
                    } else {
                        water += maxRight - height[right];
                    }

                    right--;
                }
            }

            return water;
        }




}
