package DSA.Greedy;

import java.util.*;

public class Medium {

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


    // Train arrival and depart

    public int findPlatform(int[] arr, int[] dep) {

        Arrays.sort(arr);
        Arrays.sort(dep);

        int n = arr.length;

        int i = 0; // arrival pointer
        int j = 0; // departure pointer

        int platforms = 0;
        int maxPlatforms = 0;

        while (i < n && j < n) {

            // New train arrives before/equal to departure
            if (arr[i] <= dep[j]) {

                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);

                i++;

            } else {

                // Train departs → platform becomes free
                platforms--;
                j++;
            }
        }

        return maxPlatforms;
    }


    // Job Scheduling
    public int[] JobScheduling(int[][] jobs) {

        // Sort by profit descending
        Arrays.sort(jobs, (a, b) -> b[2] - a[2]);

        int maxDeadline = 0;

        for (int[] job : jobs) {
            maxDeadline = Math.max(maxDeadline, job[1]);
        }

        // slot[i] = job scheduled at time i
        int[] slot = new int[maxDeadline + 1];

        Arrays.fill(slot, -1);

        int count = 0;
        int totalProfit = 0;

        for (int[] job : jobs) {

            int deadline = job[1];
            int profit = job[2];

            // Try latest possible slot
            for (int t = deadline; t >= 1; t--) {

                if (slot[t] == -1) {

                    slot[t] = job[0];

                    count++;
                    totalProfit += profit;

                    break;
                }
            }
        }

        return new int[]{count, totalProfit};
    }

    // Candy
    class Solution {

        class CandyNode {
            int rat;
            int ind;
            CandyNode(int rat, int ind) {
                this.rat = rat;
                this.ind = ind;
            }
        }

        public int candy(int[] ratings) {
            int n = ratings.length;
            ArrayList<CandyNode> li = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                li.add(new CandyNode(ratings[i], i));
            }

            // Sort by rating ascending, so lower rating children are assigned first
            li.sort((a, b) -> a.rat - b.rat);

            int[] candy = new int[n];
            Arrays.fill(candy, 1); // at least 1 candy

            for (CandyNode node : li) {
                int idx = node.ind;

                // If left neighbor exists and has lower rating, adjust
                if (idx > 0 && ratings[idx] > ratings[idx - 1]) {
                    candy[idx] = Math.max(candy[idx], candy[idx - 1] + 1);
                }
                // If right neighbor exists and has lower rating, adjust
                if (idx < n - 1 && ratings[idx] > ratings[idx + 1]) {
                    candy[idx] = Math.max(candy[idx], candy[idx + 1] + 1);
                }
            }

            int ans = 0;
            for (int c : candy) ans += c;
            return ans;
        }
    }


    // Schedule Job
    public int solve(int[] bt) {

        Arrays.sort(bt);

        int waiting = 0;
        int totalWaiting = 0;

        for (int time : bt) {

            totalWaiting += waiting;
            waiting += time;
        }

        return totalWaiting / bt.length;
    }


    // Program for Least Recently Used (LRU) Page Replacement Algorithm
    public int lruPageFaults(int[] pages, int capacity) {

        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        int faults = 0;

        for (int page : pages) {

            // Page already exists → HIT
            if (set.contains(page)) {

                // Move page to MRU
                set.remove(page);
                set.add(page);

            } else {

                // Page Fault
                faults++;

                // Remove LRU
                if (set.size() == capacity) {
                    int lru = set.iterator().next();
                    set.remove(lru);
                }

                // Add as MRU
                set.add(page);
            }
        }

        return faults;
    }

    // Interval

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> li = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 1. Add intervals before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            li.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }

        // 2. Merge overlapping intervals
        int minInt = newInterval[0];
        int maxInt = newInterval[1];
        while (i < n && intervals[i][0] <= newInterval[1]) {
            minInt = Math.min(minInt, intervals[i][0]);
            maxInt = Math.max(maxInt, intervals[i][1]);
            i++;
        }
        li.add(new int[]{minInt, maxInt});

        // 3. Add remaining intervals
        while (i < n) {
            li.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }

        // Directly convert to int[][]
        return li.toArray(new int[li.size()][]);
    }


    //  Overlapping

    public int[][] merge(int[][] a) {
        Arrays.sort(a,(c,b)-> c[0]-b[0]);
        List<List<Integer>> arr = new ArrayList<>();

        int start = a[0][0];
        int end = a[0][1];

        for(int i =1;i<a.length;i++){
            if(end>=a[i][0]){
                end = Math.max(end,a[i][1]);
            }else{
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(start);
                temp.add(end);
                arr.add(temp);
                start = a[i][0];
                end = a[i][1];
            }
        }

        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(start);
        temp.add(end);
        arr.add(temp);

        return arr.toArray(new ArrayList<Integer>());

    }


    public int eraseOverlapIntervals(int[][] intervals) {
        int res = 0;

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int prev_end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (prev_end > intervals[i][0]) {
                res++;
            } else {
                prev_end = intervals[i][1];
            }
        }

        return res;
    }




    // Hand of staight

        public boolean isNStraightHand(int[] hand, int groupSize) {

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (int i : hand) {
                minHeap.add(i);
            }

            while (minHeap.size() != 0) {

                int start = minHeap.poll();

                for (int j = 1; j < groupSize; j++) {

                    if (minHeap.remove(start + j)) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }

            return true;
        }


        // Form triplet


            public boolean mergeTriplets(int[][] triplets, int[] target) {

                boolean first = false;
                boolean second = false;
                boolean third = false;

                for (int[] t : triplets) {

                    // Cannot use this triplet
                    if (t[0] > target[0] ||
                            t[1] > target[1] ||
                            t[2] > target[2]) {
                        continue;
                    }

                    // This triplet is valid
                    if (t[0] == target[0]) {
                        first = true;
                    }

                    if (t[1] == target[1]) {
                        second = true;
                    }

                    if (t[2] == target[2]) {
                        third = true;
                    }
                }

                return first && second && third;
            }


        //763. Partition Labels

        public List<Integer> partitionLabels(String s) {

            int[] last = new int[26];

            // Store last occurrence of every character
            for (int i = 0; i < s.length(); i++) {
                last[s.charAt(i) - 'a'] = i;
            }

            List<Integer> result = new ArrayList<>();

            int start = 0;
            int end = 0;

            for (int i = 0; i < s.length(); i++) {

                // Current character must be completely
                // contained inside this partition
                end = Math.max(
                        end,
                        last[s.charAt(i) - 'a']
                );

                // All characters seen so far end here
                if (i == end) {

                    result.add(end - start + 1);

                    start = i + 1;
                }
            }
            return result;
        }

}
