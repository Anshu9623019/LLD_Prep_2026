package DSA.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Easy {

    // Assign Cookies
    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        int j = 0;

        while (i < g.length && j < s.length) {

            if (s[j] >= g[i]) {
                // Cookie satisfies this child
                i++;
            }

            // Cookie is used or too small
            j++;
        }

        return i;
    }
    // Fraction Knapsack
    class Item {
        int value;
        int weight;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public double fractionalKnapsack(int W, int[] values, int[] weights) {

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            items.add(new Item(values[i], weights[i]));
        }

        // Highest value/weight first
        items.sort((a, b) ->
                Double.compare(
                        (double) b.value / b.weight,
                        (double) a.value / a.weight
                )
        );

        double totalValue = 0;

        for (Item item : items) {

            if (W >= item.weight) {

                // Take complete item
                W -= item.weight;
                totalValue += item.value;

            } else {

                // Take fraction
                totalValue +=
                        ((double) item.value / item.weight) * W;

                break;
            }
        }

        return totalValue;
    }

    // 860. Lemonade Change

    public boolean lemonadeChange(int[] bills) {

        int five = 0;
        int ten = 0;

        for (int bill : bills) {

            if (bill == 5) {
                five++;
            }

            else if (bill == 10) {

                if (five == 0) {
                    return false;
                }

                five--;
                ten++;
            }

            else { // bill == 20

                // Prefer 10 + 5
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                }

                // Otherwise use 5 + 5 + 5
                else if (five >= 3) {
                    five -= 3;
                }

                else {
                    return false;
                }
            }
        }

        return true;
    }

    // 678. Valid Parenthesis String

    public boolean checkValidString(String s) {

        int low = 0;
        int high = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                low++;
                high++;
            }

            else if (c == ')') {
                low--;
                high--;
            }

            else { // '*'

                // '*' can act as ')'
                low--;

                // '*' can act as '('
                high++;
            }

            // Minimum cannot be negative
            low = Math.max(low, 0);

            // Even maximum cannot balance
            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }


   //
    public int maxMeetings(int[] start, int[] end) {

        int n = start.length;

        int[][] meetings = new int[n][2];

        for (int i = 0; i < n; i++) {
            meetings[i][0] = start[i];
            meetings[i][1] = end[i];
        }

        // Sort by ending time
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        int count = 0;
        int lastEnd = -1;

        for (int[] meeting : meetings) {

            int startTime = meeting[0];
            int endTime = meeting[1];

            if (startTime > lastEnd) {

                count++;
                lastEnd = endTime;
            }
        }

        return count;
    }

    // Jump 1
    public boolean canJump(int[] nums) {

        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {

            // Current index is unreachable
            if (i > maxReach) {
                return false;
            }

            // Extend our reachable range
            maxReach = Math.max(maxReach, i + nums[i]);

            // Already reached the end
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }

    // Jump 2
    public int jump(int[] nums) {

        int n = nums.length;

        int l = 0, r = 0, jumps = 0;

        while (r < n - 1) {

            int max = 0;

            while (l <= r) {
                max = Math.max(nums[l] + l, max);
                l++;
            }

            l = r + 1;
            r = max;
            jumps++;
        }

        return jumps;
    }











}
