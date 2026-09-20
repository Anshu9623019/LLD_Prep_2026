package DSA.Heap.Operation;

import java.util.*;

public class HeapMediumProblem {

    //Kth largest/smallest element in an array

        // Function to get the Kth largest element
        public int kthLargestElement(int[] nums, int k) {
            // Return -1, if the Kth largest element does not exist
            if (k > nums.length) return -1;

            // Pointers to mark the part of working array
            int left = 0, right = nums.length - 1;

            // Until the Kth largest element is found
            while (true) {
                // Get the pivot index
                int pivotIndex = randomIndex(left, right);

                // Update the pivotIndex
                pivotIndex = partitionAndReturnIndex(nums, pivotIndex, left, right);

                // If Kth largest element is found, return
                if (pivotIndex == k - 1) return nums[pivotIndex];

                    // Else adjust the end pointers in array
                else if (pivotIndex > k - 1) right = pivotIndex - 1;
                else left = pivotIndex + 1;
            }
        }

        private Random rand = new Random();

        // Function to get a random index
        private int randomIndex(int left, int right) {
            // Length of the array
            int len = right - left + 1;

            // Return a random index from the array
            return rand.nextInt(len) + left;
        }

        // Function to perform the partition and return the updated index of pivot
        private int partitionAndReturnIndex(int[] nums, int pivotIndex, int left, int right) {
            int pivot = nums[pivotIndex]; // Get the pivot element

            // Swap the pivot with the left element
            int temp = nums[left];
            nums[left] = nums[pivotIndex];
            nums[pivotIndex] = temp;

            int ind = left + 1; // Index to mark the start of right portion

            // Traverse on the array
            for (int i = left + 1; i <= right; i++) {

                // If the current element is greater than the pivot
                if (nums[i] > pivot) {
                    // Place the current element in the left portion
                    temp = nums[ind];
                    nums[ind] = nums[i];
                    nums[i] = temp;

                    // Move the right portion index
                    ind++;
                }
            }

            // Place the pivot at the correct index
            temp = nums[left];
            nums[left] = nums[ind - 1];
            nums[ind - 1] = temp;

            return ind - 1; // Return the index of pivot now
        }



        // Function to sort a k-sorted array using a priority queue
        public List<Integer> sortNearlySortedArray(List<Integer> arr, int k) {
            // Create a min heap using PriorityQueue
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            // Store the final result
            List<Integer> result = new ArrayList<>();

            // Add first k+1 elements to the heap
            for (int i = 0; i <= k && i < arr.size(); i++) {
                minHeap.add(arr.get(i));
            }

            // Process the rest of the array
            for (int i = k + 1; i < arr.size(); i++) {
                result.add(minHeap.poll()); // remove smallest
                minHeap.add(arr.get(i));    // insert current
            }

            // Remove and add remaining elements in heap
            while (!minHeap.isEmpty()) {
                result.add(minHeap.poll());
            }

            return result;
        }



    // Sort ke sorted linkedList

    public class ListNode {
       int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode mergeKLists(ListNode[] lists) {
        // Create a priority queue (min-heap) to store nodes
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (a, b) -> a.val - b.val
        );

        // Push the head of each non-null list into the heap
        for (ListNode node : lists) {
            if (node != null) pq.add(node);
        }

        // Create a dummy node to build the result list
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // While the heap is not empty
        while (!pq.isEmpty()) {
            // Extract the node with the smallest value
            ListNode smallest = pq.poll();

            // Add it to the result list
            tail.next = smallest;
            tail = tail.next;

            // If there's a next node, push it into the heap
            if (smallest.next != null) {
                pq.add(smallest.next);
            }
        }

        // Return the head of the merged list
        return dummy.next;
     }


    // Function to replace elements by their rank in the array
    public int[] replaceWithRank(int[] arr) {
        // Create a copy of the original array
        int[] sortedArr = arr.clone();

        // Sort the copied array
        Arrays.sort(sortedArr);

        // Map to store rank of each unique number
        HashMap<Integer, Integer> rankMap = new HashMap<>();

        int rank = 1;

        // Assign rank to each unique number
        for (int num : sortedArr) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }

        // Replace elements in original array with their ranks
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }


//    Problem: Task Scheduler — arrange tasks with cooldown n while minimizing total intervals.
//
//            Pattern: Greedy / Frequency Counting.
//
//    Core intuition:
//
//    The most frequent task creates the skeleton. Count the gaps it forces, then use the remaining tasks to fill those gaps. Any gaps left over are idle intervals.
//
//    Key variables:
//
//    max       → highest task frequency
//    maxCount  → number of tasks having that frequency
//    partCount → max - 1
//    partLength → n - (maxCount - 1)
//    emptySlots → partCount × partLength
//    availableTasks → total - max × maxCount
//    idles → max(0, emptySlots - availableTasks)

    public int leastInterval(char[] tasks, int n) {
        int[] counter = new int[26];
        int max = 0;
        int maxCount = 0;
        for(char task : tasks) {
            counter[task - 'A']++;
            if(max == counter[task - 'A']) {
                maxCount++;
            }
            else if(max < counter[task - 'A']) {
                max = counter[task - 'A'];
                maxCount = 1;
            }
        }

        int partCount = max - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - max * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }


}
