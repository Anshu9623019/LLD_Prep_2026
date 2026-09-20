package DSA.Graph;

import java.util.*;

public class TopologicalSorting {

    public static void main(String[] args) {

    }
    //DFS TopoSort :
    public static List<Integer> topoSortDFS(int V, List<List<Integer>> adj){

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < V; i++){
            if(!visited[i]){
                dfs(i, adj, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();

        while(!stack.isEmpty()){
            result.add(stack.pop());
        }

        return result;
    }

    private static void dfs(int node,
                            List<List<Integer>> adj,
                            boolean[] visited,
                            Stack<Integer> stack){

        visited[node] = true;

        for(int neighbor : adj.get(node)){
            if(!visited[neighbor]){
                dfs(neighbor, adj, visited, stack);
            }
        }

        stack.push(node);
    }

    //Kahn's Algo : Topo Sort
    public static List<Integer> topoSortBFS(int V, List<List<Integer>> adj) {

        int[] indegree = new int[V];

        // Step 1: Calculate indegree
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        // Step 2: Add all indegree 0 nodes
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        // Step 3: BFS
        while (!q.isEmpty()) {

            int node = q.poll();
            result.add(node);

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        return result;
    }

    //Detect Cycle in directed graph using kahn's algo
    public static boolean detectCycle(int V, List<List<Integer>> adj) {

        int[] indegree = new int[V];

        // Step 1: Calculate indegree
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        // Step 2: Add all nodes with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int count = 0;

        // Step 3: Kahn’s BFS
        while (!q.isEmpty()) {

            int node = q.poll();
            count++;

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        // Step 4: Check if all nodes processed
        return count != V;   // true → cycle exists
    }

    //Course Schedule

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Step 1: Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        // Step 2: Fill graph
        for(int[] pre : prerequisites){
            int course = pre[0];
            int prereq = pre[1];

            adj.get(prereq).add(course);  // prereq → course
            indegree[course]++;
        }

        // Step 3: Add indegree 0 nodes to queue
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        int count = 0;

        // Step 4: Kahn's BFS
        while(!q.isEmpty()){
            int node = q.poll();
            count++;

            for(int neighbor : adj.get(node)){
                indegree[neighbor]--;

                if(indegree[neighbor] == 0){
                    q.add(neighbor);
                }
            }
        }

        // If processed all courses → no cycle
        return count == numCourses;
    }

    //Course Schedule ii

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        // Build graph
        for(int[] pre : prerequisites){
            int course = pre[0];
            int prereq = pre[1];

            adj.get(prereq).add(course);
            indegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;

        while(!q.isEmpty()){

            int node = q.poll();
            result[index++] = node;

            for(int neighbor : adj.get(node)){
                indegree[neighbor]--;

                if(indegree[neighbor] == 0){
                    q.add(neighbor);
                }
            }
        }

        // If cycle exists → return empty array
        if(index != numCourses){
            return new int[0];
        }

        return result;
    }

    //Eventual Safe Node
    public List<Integer> eventualSafeNodes1(int[][] graph) {

        int n = graph.length;

        List<List<Integer>> reverse = new ArrayList<>();
        int[] indegree = new int[n];

        for(int i = 0; i < n; i++){
            reverse.add(new ArrayList<>());
        }

        // Build reverse graph
        for(int i = 0; i < n; i++){
            for(int neighbor : graph[i]){
                reverse.get(neighbor).add(i);
                indegree[i]++;   // count original outgoing edges
            }
        }

        Queue<Integer> q = new LinkedList<>();

        // Terminal nodes (no outgoing edges)
        for(int i = 0; i < n; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        boolean[] safe = new boolean[n];

        while(!q.isEmpty()){

            int node = q.poll();
            safe[node] = true;

            for(int prev : reverse.get(node)){
                indegree[prev]--;

                if(indegree[prev] == 0){
                    q.add(prev);
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(safe[i]){
                result.add(i);
            }
        }

        return result;
    }



    // Shortest path in directed graph
    class Solution3 {

        static class Pair {
            int node, weight;

            Pair(int n, int w) {
                node = n;
                weight = w;
            }
        }

        public int[] shortestPath(int N, int[][] edges, int src) {

            List<List<Solution3.Pair>> adj = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                adj.add(new ArrayList<>());
            }

            // Build graph
            for (int[] e : edges) {
                adj.get(e[0]).add(new Solution3.Pair(e[1], e[2]));
            }

            // Step 1: Topological sort
            boolean[] visited = new boolean[N];
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    topoDFS(i, adj, visited, stack);
                }
            }

            // Step 2: Initialize distance
            int[] dist = new int[N];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // Step 3: Relax in topo order
            while (!stack.isEmpty()) {
                int node = stack.pop();
                if (dist[node] != Integer.MAX_VALUE) {

                    for (Pair p : adj.get(node)) {
                        if (dist[node] + p.weight < dist[p.node]) {
                            dist[p.node] = dist[node] + p.weight;
                        }
                    }
                }
            }

            return dist;
        }


        private void topoDFS(int node, List<List<Solution3.Pair>> adj,
                             boolean[] visited, Stack<Integer> stack) {

            visited[node] = true;
            for (Solution3.Pair p : adj.get(node)) {
                if (!visited[p.node]) {
                    topoDFS(p.node, adj, visited, stack);
                }
            }
            stack.push(node);
        }
    }
        //Shortest Path in undirected graph Using BFS
        public int[] shortestPath1(int N, int[][] edges, int src) {

            List<List<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                adj.add(new ArrayList<>());
            }

            // Build undirected graph
            for (int[] e : edges) {
                adj.get(e[0]).add(e[1]);
                adj.get(e[1]).add(e[0]);
            }

            int[] dist = new int[N];
            Arrays.fill(dist, -1);  // -1 means not visited

            Queue<Integer> q = new LinkedList<>();

            q.add(src);
            dist[src] = 0;

            while (!q.isEmpty()) {

                int node = q.poll();

                for (int neighbor : adj.get(node)) {
                    if (dist[neighbor] == -1) {
                        dist[neighbor] = dist[node] + 1;
                        q.add(neighbor);
                    }
                }
            }

            return dist;
        }


    class Solution {

        public String alienOrder(String[] words) {

            // Graph
            Map<Character, Set<Character>> adj = new HashMap<>();

            // Indegree
            Map<Character, Integer> indegree = new HashMap<>();

            // Add every character
            for (String word : words) {
                for (char ch : word.toCharArray()) {
                    adj.putIfAbsent(ch, new HashSet<>());
                    indegree.putIfAbsent(ch, 0);
                }
            }

            // Build graph
            for (int i = 0; i < words.length - 1; i++) {

                String word1 = words[i];
                String word2 = words[i + 1];

                int minLength = Math.min(word1.length(), word2.length());

                boolean foundDifference = false;

                for (int j = 0; j < minLength; j++) {

                    char c1 = word1.charAt(j);
                    char c2 = word2.charAt(j);

                    if (c1 != c2) {

                        // c1 comes before c2
                        if (!adj.get(c1).contains(c2)) {

                            adj.get(c1).add(c2);
                            indegree.put(c2, indegree.get(c2) + 1);
                        }

                        foundDifference = true;
                        break;
                    }
                }

                // Invalid prefix case
                if (!foundDifference &&
                        word1.length() > word2.length()) {

                    return "";
                }
            }

            // Kahn's algorithm
            Queue<Character> queue = new LinkedList<>();

            for (char ch : indegree.keySet()) {

                if (indegree.get(ch) == 0) {
                    queue.offer(ch);
                }
            }

            StringBuilder result = new StringBuilder();

            while (!queue.isEmpty()) {

                char current = queue.poll();

                result.append(current);

                for (char next : adj.get(current)) {

                    indegree.put(
                            next,
                            indegree.get(next) - 1
                    );

                    if (indegree.get(next) == 0) {
                        queue.offer(next);
                    }
                }
            }

            // Cycle detection
            if (result.length() != indegree.size()) {
                return "";
            }

            return result.toString();
        }
    }


        //Word Ladder 1
        public int ladderLength(String beginWord,
                                String endWord,
                                List<String> wordList) {

            Set<String> set = new HashSet<>(wordList);

            if (!set.contains(endWord)) return 0;

            Queue<String> q = new LinkedList<>();
            q.add(beginWord);

            int level = 1;

            while (!q.isEmpty()) {

                int size = q.size();

                for (int i = 0; i < size; i++) {

                    String word = q.poll();

                    if (word.equals(endWord)) return level;

                    char[] arr = word.toCharArray();

                    for (int j = 0; j < arr.length; j++) {

                        char original = arr[j];

                        for (char c = 'a'; c <= 'z'; c++) {

                            arr[j] = c;
                            String newWord = new String(arr);

                            if (set.contains(newWord)) {
                                q.add(newWord);
                                set.remove(newWord); // mark visited
                            }
                        }

                        arr[j] = original;
                    }
                }

                level++;
            }

            return 0;
        }



        //Word ladder 2 : Check once again
        //Dijkstra's Algo : using Priority Queue
        class Solution1 {

            static class Pair {
                int node, dist;

                Pair(int node, int dist) {
                    this.node = node;
                    this.dist = dist;
                }
            }

            public int[] dijkstra(int V, List<List<Pair>> adj, int src) {

                int[] dist = new int[V];
                Arrays.fill(dist, Integer.MAX_VALUE);

                PriorityQueue<Pair> pq =
                        new PriorityQueue<>((a, b) -> a.dist - b.dist);

                dist[src] = 0;
                pq.add(new Pair(src, 0));

                while (!pq.isEmpty()) {

                    Pair curr = pq.poll();
                    int node = curr.node;
                    int d = curr.dist;

                    if (d > dist[node]) continue; // Skip outdated entry

                    for (Pair neighbor : adj.get(node)) {

                        int newDist = d + neighbor.dist;

                        if (newDist < dist[neighbor.node]) {
                            dist[neighbor.node] = newDist;
                            pq.add(new Pair(neighbor.node, newDist));
                        }
                    }
                }

                return dist;
            }
        }




}
