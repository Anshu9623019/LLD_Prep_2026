package DSA.StackImp;

import java.util.*;

public class StackQuestions {


    // Valid parentheses

        public boolean isValid(String s) {

            Stack<Character> stack = new Stack<>();

            for (char ch : s.toCharArray()) {

                // Opening bracket
                if (ch == '(' || ch == '[' || ch == '{') {
                    stack.push(ch);
                }

                // Closing bracket
                else {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    char top = stack.pop();

                    if ((ch == ')' && top != '(') ||
                            (ch == ']' && top != '[') ||
                            (ch == '}' && top != '{')) {

                        return false;
                    }
                }
            }

            return stack.isEmpty();
        }

        // Minimum Stack
        // 1st method

    class MinStack {

        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {

            stack.push(val);

            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(
                        Math.min(val, minStack.peek())
                );
            }
        }

        public void pop() {

            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    // Method 2
    class MinStack2 {

        ArrayList<Long> stack = new ArrayList<>();
        long min;

        public void push(int val) {

            if (stack.isEmpty()) {
                stack.add(0L);
                min = val;
            } else {
                long diff = (long) val - min;
                stack.add(diff);

                if (val < min) {
                    min = val;
                }
            }
        }

        public void pop() {

            if (stack.isEmpty()) return;

            long diff = stack.remove(stack.size() - 1);

            if (diff < 0) {
                min = min - diff;
            }
        }

        public int top() {

            if (stack.isEmpty()) return -1;

            long diff = stack.get(stack.size() - 1);

            if (diff < 0) {
                return (int) min;
            }

            return (int) (min + diff);
        }

        public int getMin() {
            return (int) min;
        }
    }

    // Evaluate Reverse Polish Notation


        public int evalRPN(String[] tokens) {

            Deque<Integer> stack = new ArrayDeque<>();

            for (String token : tokens) {

                switch (token) {

                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;

                    case "-": {
                        int b = stack.pop();
                        int a = stack.pop();
                        stack.push(a - b);
                        break;
                    }

                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;

                    case "/": {
                        int b = stack.pop();
                        int a = stack.pop();
                        stack.push(a / b);
                        break;
                    }

                    default:
                        stack.push(Integer.parseInt(token));
                }
            }

            return stack.pop();
        }




    //Daily Temperatures


        public int[] dailyTemperatures(int[] temperatures) {

            int n = temperatures.length;
            int[] ans = new int[n];

            Deque<Integer> stack = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {

                while (!stack.isEmpty() &&
                        temperatures[i] > temperatures[stack.peek()]) {

                    int prev = stack.pop();

                    ans[prev] = i - prev;
                }

                stack.push(i);
            }

            return ans;
        }

        //Car fleet

            public int carFleet(int target, int[] position, int[] speed) {

                int n = position.length;

                // [position, speed]
                int[][] cars = new int[n][2];

                for (int i = 0; i < n; i++) {
                    cars[i][0] = position[i];
                    cars[i][1] = speed[i];
                }

                // Sort by position: closest to target first
                Arrays.sort(cars, (a, b) -> b[0] - a[0]);

                Stack<Double> stack = new Stack<>();

                for (int[] car : cars) {

                    double time =
                            (double) (target - car[0]) / car[1];

                    if (stack.isEmpty() || time > stack.peek()) {
                        stack.push(time);
                    }
                }

                return stack.size();
            }


            // Area of rectangle


        public int largestRectangleArea(int[] heights) {

              int n = heights.length;
             Stack <Integer> st = new Stack < > ();
             int leftSmall[] = new int[n];
             int rightSmall[] = new int[n];
             for (int i = 0; i < n; i++) {
                 while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                     st.pop();
                 }

                 if (st.isEmpty()) leftSmall[i] = 0;
                 else leftSmall[i] = st.peek() + 1;
                 st.push(i);
             }

             // clear the stack to be re-used
             while (!st.isEmpty()) st.pop();

             for (int i = n - 1; i >= 0; i--) {
                 while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                     st.pop();
                 }

                 if (st.isEmpty()) rightSmall[i] = n - 1;
                 else rightSmall[i] = st.peek() - 1;

                 st.push(i);
             }

             int maxA = 0;
             for (int i = 0; i < n; i++) {
                 maxA = Math.max(maxA, heights[i] * (rightSmall[i] - leftSmall[i] + 1));
             }


            int n1 = heights.length;
            Stack<Integer> st1 = new Stack<>();
            int maxA1 = 0;
            int i = 0;
            while(i<n){
                int num = heights[i];
                while(!st.isEmpty() && heights[st.peek()]>num){
                    int ele = heights[st.pop()];
                    int pse = st.isEmpty() ? -1 : st.peek();
                    int nse = i;
                    maxA1 = Math.max(maxA,ele*(nse - pse -1));
                }
                st.push(i);
                i++;
            }

            while(!st.isEmpty()){
                int ele = heights[st.pop()];
                int nse = n;
                int pse = st.isEmpty() ? -1 : st.peek();
                maxA1 = Math.max(maxA,ele*(nse-pse-1));
            }
            return maxA1;

        }



        // TUF Question

        // Next Greater element 1

            public int[] nextGreaterElement(int[] nums1, int[] nums2) {

                Map<Integer, Integer> map = new HashMap<>();
                Deque<Integer> stack = new ArrayDeque<>();

                // Build next greater for every element in nums2
                for (int num : nums2) {

                    while (!stack.isEmpty() && num > stack.peek()) {
                        map.put(stack.pop(), num);
                    }

                    stack.push(num);
                }

                // Remaining elements have no greater element
                while (!stack.isEmpty()) {
                    map.put(stack.pop(), -1);
                }

                // Answer nums1
                int[] ans = new int[nums1.length];

                for (int i = 0; i < nums1.length; i++) {
                    ans[i] = map.get(nums1[i]);
                }

                return ans;
            }

        // Next Greater element II(Circular array)
        public int[] nextGreaterElements(int[] nums) {

            int n = nums.length;
            int[] ans = new int[n];

            Arrays.fill(ans, -1);

            Deque<Integer> stack = new ArrayDeque<>();

            // Traverse array twice
            for (int i = 0; i < 2 * n; i++) {

                int idx = i % n;

                while (!stack.isEmpty() &&
                        nums[idx] > nums[stack.peek()]) {

                    ans[stack.pop()] = nums[idx];
                }

                // Push only during first traversal
                if (i < n) {
                    stack.push(idx);
                }
            }

            return ans;
        }

        // Next Smaller Element
            public int[] nextSmallerElement(int[] arr) {

                int n = arr.length;
                int[] ans = new int[n];

                Arrays.fill(ans, -1);

                Deque<Integer> stack = new ArrayDeque<>();

                for (int i = 0; i < n; i++) {

                    while (!stack.isEmpty()
                            && arr[i] < arr[stack.peek()]) {

                        int index = stack.pop();
                        ans[index] = arr[i];
                    }

                    stack.push(i);
                }

                return ans;
            }

            // Number of  next greater element
            class Solution {
                public int[] countGreater(int[] arr) {

                    int n = arr.length;
                    int[] ans = new int[n];

                    for (int i = 0; i < n; i++) {

                        for (int j = i + 1; j < n; j++) {

                            if (arr[j] > arr[i]) {
                                ans[i]++;
                            }
                        }
                    }

                    return ans;
                }
            }


            // Trapping rain water problem, Two pointer approach
                public int trap(int[] height) {

                    int total =0,leftmax=0, rightmax=0;
                    int i = 0, j = height.length-1;
                    while(i<j){
                        if(height[i]<=height[j]){
                            if(leftmax>=height[i]){
                                total += leftmax - height[i];
                            }else{
                                leftmax = height[i];
                            }
                            i++;
                        }else{
                            if(rightmax>=height[j]){
                                total += rightmax - height[j];
                            }else{
                                rightmax = height[j];
                            }
                            j--;
                        }
                    }

                    return total;
                }

            // Sum of subArray Minimum

        public int[] prevSE(int arr[]) {
            int n = arr.length;
            Stack<Integer> st = new Stack<>();
            int ans[] = new int[n];
            for (int i = 0; i < n; i++) {
                while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                    st.pop();
                }
                ans[i] = st.isEmpty() ? -1 : st.peek();
                st.push(i);
            }
            return ans;
        }

        public int[] nextSE(int arr[]) {
            int n = arr.length;
            Stack<Integer> st = new Stack<>();
            int ans[] = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                    st.pop();
                }
                ans[i] = st.isEmpty() ? n : st.peek(); // next smaller => n if none
                st.push(i);
            }
            return ans;
        }

        public int sumSubarrayMins(int[] arr) {
            int[] prev = prevSE(arr);
            int[] next = nextSE(arr);
            long mod = 1_000_000_007;
            long total = 0;

            for (int i = 0; i < arr.length; i++) {
                long left = i - prev[i];
                long right = next[i] - i;
                total = (total + ((left * right) % mod * arr[i]) % mod) % mod;
            }

            return (int) total;
        }

        //  Remove K digit
        public String removeKdigits(String num, int k) {

            if (k == num.length()) {
                return "0";
            }

            Deque<Character> stack = new ArrayDeque<>();

            for (char digit : num.toCharArray()) {

                while (!stack.isEmpty()
                        && k > 0
                        && stack.peek() > digit) {

                    stack.pop();
                    k--;
                }

                stack.push(digit);
            }

            // If digits are already increasing
            while (k > 0) {
                stack.pop();
                k--;
            }

            // Build result
            StringBuilder ans = new StringBuilder();

            while (!stack.isEmpty()) {
                ans.append(stack.removeLast());
            }

            // Remove leading zeros
            int i = 0;

            while (i < ans.length() && ans.charAt(i) == '0') {
                i++;
            }

            String result = ans.substring(i);

            return result.isEmpty() ? "0" : result;
        }


        // Largest Rectangle in histogram

    public int largestRectangleArea1(int[] heights) {

        //  int n = heights.length;
        // Stack <Integer> st = new Stack < > ();
        // int leftSmall[] = new int[n];
        // int rightSmall[] = new int[n];
        // for (int i = 0; i < n; i++) {
        //     while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
        //         st.pop();
        //     }

        //     if (st.isEmpty()) leftSmall[i] = 0;
        //     else leftSmall[i] = st.peek() + 1;
        //     st.push(i);
        // }

        // // clear the stack to be re-used
        // while (!st.isEmpty()) st.pop();

        // for (int i = n - 1; i >= 0; i--) {
        //     while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
        //         st.pop();
        //     }

        //     if (st.isEmpty()) rightSmall[i] = n - 1;
        //     else rightSmall[i] = st.peek() - 1;

        //     st.push(i);
        // }

        // int maxA = 0;
        // for (int i = 0; i < n; i++) {
        //     maxA = Math.max(maxA, heights[i] * (rightSmall[i] - leftSmall[i] + 1));
        // }
        // return maxA;

        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int maxA = 0;
        int i = 0;
        while (i < n) {
            int num = heights[i];
            while (!st.isEmpty() && heights[st.peek()] > num) {
                int ele = heights[st.pop()];
                int pse = st.isEmpty() ? -1 : st.peek();
                int nse = i;
                maxA = Math.max(maxA, ele * (nse - pse - 1));
            }
            st.push(i);
            i++;
        }

        while (!st.isEmpty()) {
            int ele = heights[st.pop()];
            int nse = n;
            int pse = st.isEmpty() ? -1 : st.peek();
            maxA = Math.max(maxA, ele * (nse - pse - 1));
        }
        return maxA;
    }

    // Maximum Rectangles
    // Function to find Largest Rectangle Area in Histogram
    public int solve(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int maxA = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            // Process until we find a smaller height
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                int height = arr[st.pop()];
                int pse = st.isEmpty() ? -1 : st.peek(); // Previous Smaller Element index
                int nse = i; // Next Smaller Element index
                maxA = Math.max(maxA, height * (nse - pse - 1));
            }
            st.push(i);
        }

        // Clear remaining bars
        while (!st.isEmpty()) {
            int height = arr[st.pop()];
            int pse = st.isEmpty() ? -1 : st.peek();
            int nse = n;
            maxA = Math.max(maxA, height * (nse - pse - 1));
        }

        return maxA;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] heights = new int[n][m]; // Heights for histogram

        // Convert matrix to heights for histogram
        for (int i = 0; i < m; i++) {
            heights[0][i] = matrix[0][i] - '0';
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    heights[i][j] = heights[i - 1][j] + 1;
                } else {
                    heights[i][j] = 0;
                }
            }
        }

        int maxA = 0;
        // Apply largest rectangle in histogram logic for each row
        for (int i = 0; i < n; i++) {
            maxA = Math.max(maxA, solve(heights[i]));
        }

        return maxA;
    }

    //Sliding window max
        public int[] maxSlidingWindow(int[] nums, int k) {

            Deque<Integer> dq  = new ArrayDeque<>();
            int n = nums.length;
            int ans[] = new int[n-k+1];
            int c = 0;
            for(int i=0;i<n;i++){
                if(!dq.isEmpty() && dq.peek()==i-k){
                    dq.poll();
                }
                while(!dq.isEmpty() && nums[dq.peekLast()]<nums[i]){
                    dq.pollLast();
                }
                dq.offer(i);
                if(i>=k-1){
                    ans[c++] = nums[dq.peek()];
                }
            }

            return ans;

        }
    }

    // Online Stock span
    class Pair{
        int val;
        int ind;
        Pair(int val,int ind){
            this.ind = ind;
            this.val = val;
        }
    }

class StockSpanner {
    Stack<Pair> sc;
    int index;
    public StockSpanner() {
        sc = new Stack<>();
        index = -1;
    }

    public int next(int price) {
        index = index+1;
        while(!sc.isEmpty() && price>=sc.peek().val){
            sc.pop();
        }
        int ans = index -  (sc.isEmpty() ? -1 : sc.peek().ind);
        sc.add(new Pair(price,index));
        return ans;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */

// Celebrity Problem
class Solution {
    public int celebrity(int[][] matrix) {

        int n = matrix.length;

        int candidate = 0;

        // Step 1: Find possible celebrity
        for (int i = 1; i < n; i++) {

            if (matrix[candidate][i] == 1) {
                // candidate knows i
                // candidate cannot be celebrity
                candidate = i;
            }
        }

        // Step 2: Verify candidate
        for (int i = 0; i < n; i++) {

            if (i == candidate) {
                continue;
            }

            // Celebrity knows nobody
            // Everyone knows celebrity
            if (matrix[candidate][i] == 1 ||
                    matrix[i][candidate] == 0) {

                return -1;
            }
        }

        return candidate;
    }

    //LRU
    class LRUCache {

        class Node {
            int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;

        private final Map<Integer, Node> map;

        private final Node head;
        private final Node tail;

        public LRUCache(int capacity) {

            this.capacity = capacity;

            map = new HashMap<>();

            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {

            if (!map.containsKey(key)) {
                return -1;
            }

            Node node = map.get(key);

            // Recently used → move to end
            remove(node);
            addToEnd(node);

            return node.value;
        }

        public void put(int key, int value) {

            // Key already exists
            if (map.containsKey(key)) {

                Node node = map.get(key);

                node.value = value;

                remove(node);
                addToEnd(node);

                return;
            }

            // New node
            Node node = new Node(key, value);

            map.put(key, node);
            addToEnd(node);

            // Capacity exceeded
            if (map.size() > capacity) {

                Node lru = head.next;

                remove(lru);

                map.remove(lru.key);
            }
        }

        // Remove node from DLL
        private void remove(Node node) {

            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // Add node just before tail
        private void addToEnd(Node node) {

            Node prevNode = tail.prev;

            prevNode.next = node;
            node.prev = prevNode;

            node.next = tail;
            tail.prev = node;
        }
    }


    class LFUCache {

        class Node {
            int key;
            int value;
            int freq;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.freq = 1;
            }
        }

        class DLL {

            Node head;
            Node tail;
            int size;

            DLL() {
                head = new Node(0, 0);
                tail = new Node(0, 0);

                head.next = tail;
                tail.prev = head;
            }

            void add(Node node) {

                Node next = head.next;

                head.next = node;
                node.prev = head;

                node.next = next;
                next.prev = node;

                size++;
            }

            void remove(Node node) {

                node.prev.next = node.next;
                node.next.prev = node.prev;

                size--;
            }

            Node removeLRU() {

                if (size == 0) {
                    return null;
                }

                Node node = tail.prev;

                remove(node);

                return node;
            }
        }

        private final int capacity;
        private int minFreq;

        // key -> Node
        private Map<Integer, Node> keyMap;

        // freq -> DLL
        private Map<Integer, DLL> freqMap;

        public LFUCache(int capacity) {

            this.capacity = capacity;

            keyMap = new HashMap<>();
            freqMap = new HashMap<>();

            minFreq = 0;
        }

        public int get(int key) {

            if (!keyMap.containsKey(key)) {
                return -1;
            }

            Node node = keyMap.get(key);

            increaseFrequency(node);

            return node.value;
        }

        public void put(int key, int value) {

            if (capacity == 0) {
                return;
            }

            // Existing key
            if (keyMap.containsKey(key)) {

                Node node = keyMap.get(key);

                node.value = value;

                increaseFrequency(node);

                return;
            }

            // Cache full
            if (keyMap.size() == capacity) {

                DLL list = freqMap.get(minFreq);

                Node lru = list.removeLRU();

                keyMap.remove(lru.key);
            }

            Node node = new Node(key, value);

            keyMap.put(key, node);

            freqMap
                    .computeIfAbsent(1, x -> new DLL())
                    .add(node);

            minFreq = 1;
        }

        private void increaseFrequency(Node node) {

            int oldFreq = node.freq;

            DLL oldList = freqMap.get(oldFreq);

            oldList.remove(node);

            // If this was the last node
            // in minimum frequency
            if (oldFreq == minFreq &&
                    oldList.size == 0) {

                minFreq++;
            }

            node.freq++;

            freqMap
                    .computeIfAbsent(node.freq, x -> new DLL())
                    .add(node);
        }
    }
}










}
