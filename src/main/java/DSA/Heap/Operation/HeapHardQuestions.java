package DSA.Heap.Operation;

import java.util.*;

public class HeapHardQuestions {
    public static void main(String[] args) {

    }

    public class Twitter {
        private static int timeStamp=0;

        // easy to find if user exist
        private Map<Integer, User> userMap;

        // Tweet link to next Tweet so that we can save a lot of time
        // when we execute getNewsFeed(userId)
        private class Tweet{
            public int id;
            public int time;
            public Tweet next;

            public Tweet(int id){
                this.id = id;
                time = timeStamp++;
                next=null;
            }
        }


        // OO design so User can follow, unfollow and post itself
        public class User{
            public int id;
            public Set<Integer> followed;
            public Tweet tweet_head;

            public User(int id){
                this.id=id;
                followed = new HashSet<>();
                follow(id); // first follow itself
                tweet_head = null;
            }

            public void follow(int id){
                followed.add(id);
            }

            public void unfollow(int id){
                followed.remove(id);
            }


            // everytime user post a new tweet, add it to the head of tweet list.
            public void post(int id){
                Tweet t = new Tweet(id);
                t.next=tweet_head;
                tweet_head=t;
            }
        }




        /** Initialize your data structure here. */
        public Twitter() {
            userMap = new HashMap<Integer, User>();
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            if(!userMap.containsKey(userId)){
                User u = new User(userId);
                userMap.put(userId, u);
            }
            userMap.get(userId).post(tweetId);

        }



        // Best part of this.
        // first get all tweets lists from one user including itself and all people it followed.
        // Second add all heads into a max heap. Every time we poll a tweet with
        // largest time stamp from the heap, then we add its next tweet into the heap.
        // So after adding all heads we only need to add 9 tweets at most into this
        // heap before we get the 10 most recent tweet.
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new LinkedList<>();

            if(!userMap.containsKey(userId))   return res;

            Set<Integer> users = userMap.get(userId).followed;
            PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));
            for(int user: users){
                Tweet t = userMap.get(user).tweet_head;
                // very imporant! If we add null to the head we are screwed.
                if(t!=null){
                    q.add(t);
                }
            }
            int n=0;
            while(!q.isEmpty() && n<10){
                Tweet t = q.poll();
                res.add(t.id);
                n++;
                if(t.next!=null)
                    q.add(t.next);
            }

            return res;

        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if(!userMap.containsKey(followerId)){
                User u = new User(followerId);
                userMap.put(followerId, u);
            }
            if(!userMap.containsKey(followeeId)){
                User u = new User(followeeId);
                userMap.put(followeeId, u);
            }
            userMap.get(followerId).follow(followeeId);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if(!userMap.containsKey(followerId) || followerId==followeeId)
                return;
            userMap.get(followerId).unfollow(followeeId);
        }
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */



    // Minimum Cost to Connect Sticks
        public int connectSticks(int[] sticks) {

            PriorityQueue<Integer> pq = new PriorityQueue<>();

            // Put all sticks into min heap
            for (int stick : sticks) {
                pq.offer(stick);
            }

            int totalCost = 0;

            // Keep connecting until only one stick remains
            while (pq.size() > 1) {

                int first = pq.poll();
                int second = pq.poll();

                int cost = first + second;

                totalCost += cost;

                // New combined stick
                pq.offer(cost);
            }

            return totalCost;
        }





    //Kth largest element in a stream of running integers

//    Create a min-heap.
//    Insert the first k elements from the initial stream into the heap.
//    For the remaining elements in the initial stream:
//    If the element is greater than the smallest in heap, insert it and remove the smallest.
//    When a new element is added via add()
//    Insert it into the heap.
//    If heap size exceeds k, remove the smallest.
//    Return the top of the heap (kth largest).
class KthLargest {

    PriorityQueue<Integer> pq ;
    int k ;

    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>((a,b)->a-b);
        this.k = k;
        for(int ele : nums){
            pq.offer(ele);
            if(pq.size()>k){
                pq.poll();
            }
        }
    }

    public int add(int val) {
        pq.offer(val);
        if(pq.size()>k){
            pq.poll();
        }
        return pq.peek();
    }
}



    // Max Sum combination
//    Input : nums1 = [7, 3], nums2 = [1, 6], k = 2
//    Output : [13, 9]
//    Explanation : The 2 maximum combinations are made by: nums1[0] + nums2[1] = 13 nums1[1] + nums2[1] = 9


//    Sort both input arrays in descending order.
//    Use a max heap to store tuples of (sum, index1, index2).
//    Push the initial max sum formed by the first elements of both arrays into the heap.
//    Maintain a set to track visited index pairs.
//    Repeat the following k times:
//    Pop the current largest sum from the heap.
//    Add it to the result.
//    Push the next two possible combinations (moving one index forward in either array) into the heap if not visited.
//    Return the result list after collecting k sums.



    public List<Integer> maxCombinations(int[] nums1, int[] nums2, int k) {
        // Sort both arrays in descending order
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;

        // Max heap to store sums and their indices
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Set to keep track of visited index pairs
        Set<String> visited = new HashSet<>();

        // Push initial max sum combination
        maxHeap.offer(new int[]{nums1[n - 1] + nums2[n - 1], n - 1, n - 1});
        visited.add((n - 1) + "," + (n - 1));

        // Result list
        List<Integer> result = new ArrayList<>();

        // Extract top k combinations
        while (k-- > 0 && !maxHeap.isEmpty()) {
            int[] current = maxHeap.poll();
            int sum = current[0], i = current[1], j = current[2];

            result.add(sum);

            // Check for new combination (i - 1, j)
            if (i - 1 >= 0) {
                String key1 = (i - 1) + "," + j;
                if (!visited.contains(key1)) {
                    maxHeap.offer(new int[]{nums1[i - 1] + nums2[j], i - 1, j});
                    visited.add(key1);
                }
            }

            // Check for new combination (i, j - 1)
            if (j - 1 >= 0) {
                String key2 = i + "," + (j - 1);
                if (!visited.contains(key2)) {
                    maxHeap.offer(new int[]{nums1[i] + nums2[j - 1], i, j - 1});
                    visited.add(key2);
                }
            }
        }

        return result;
    }



        public int[] topKFrequent(int[] nums, int k) {

            // 1. Count frequency
            HashMap<Integer, Integer> freq = new HashMap<>();

            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }

            // 2. Bucket: index = frequency
            List<Integer>[] bucket = new ArrayList[nums.length + 1];

            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                int num = entry.getKey();
                int frequency = entry.getValue();

                if (bucket[frequency] == null) {
                    bucket[frequency] = new ArrayList<>();
                }

                bucket[frequency].add(num);
            }

            // 3. Traverse from highest frequency
            int[] result = new int[k];
            int index = 0;

            for (int frequency = nums.length; frequency >= 1; frequency--) {

                if (bucket[frequency] == null) {
                    continue;
                }

                for (int num : bucket[frequency]) {
                    result[index++] = num;

                    if (index == k) {
                        return result;
                    }
                }
            }

            return result;
        }



//    Max Heap              Min Heap
//   ↓                     ↓
//    smaller half          larger half
//
//    MaxHeap → 3 2 1
//    MinHeap → 4 5 6
//
//    For an odd number of elements:
//
//    left = 4 elements
//    right = 3 elements
//
//    median = left.peek()
//
//    For an even number:
//
//    left = 3
//    right = 3
//
//    median = (left.peek() + right.peek()) / 2


    class MedianFinder {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        public MedianFinder() {
        }
        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() > maxHeap.size())
                maxHeap.offer(minHeap.poll());
        }
        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) return maxHeap.peek();
            return (minHeap.peek() + maxHeap.peek()) / 2.0d;
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */


}
