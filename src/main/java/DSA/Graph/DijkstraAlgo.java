package DSA.Graph;

import java.util.*;

public class DijkstraAlgo {

    public static void main(String[] args) {

    }


    //Dijkstra's Algo : using Priority Queue
    class Solution1 {

        static class Pair {
            int node, dist;

            Pair(int node, int dist) {
                this.node = node;
                this.dist = dist;
            }
        }

        public int[] dijkstra(int V, List<List<Solution1.Pair>> adj, int src) {

            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);

            PriorityQueue<Solution1.Pair> pq =
                    new PriorityQueue<>((a, b) -> a.dist - b.dist);

            dist[src] = 0;
            pq.add(new Solution1.Pair(src, 0));

            while (!pq.isEmpty()) {

                Solution1.Pair curr = pq.poll();
                int node = curr.node;
                int d = curr.dist;

                if (d > dist[node]) continue; // Skip outdated entry

                for (Solution1.Pair neighbor : adj.get(node)) {

                    int newDist = d + neighbor.dist;

                    if (newDist < dist[neighbor.node]) {
                        dist[neighbor.node] = newDist;
                        pq.add(new Solution1.Pair(neighbor.node, newDist));
                    }
                }
            }

            return dist;
        }

    }


        // Dijkstra's Algo : Using Tree Set , remove duplicate entries
        class Solution2 {

            static class Pair {
                int node, dist;

                Pair(int node, int dist) {
                    this.node = node;
                    this.dist = dist;
                }
            }

            public int[] dijkstra(int V, List<List<Solution2.Pair>> adj, int src) {

                int[] dist = new int[V];
                Arrays.fill(dist, Integer.MAX_VALUE);

                TreeSet<Solution2.Pair> set = new TreeSet<>(
                        (a, b) -> {
                            if (a.dist == b.dist)
                                return a.node - b.node;
                            return a.dist - b.dist;
                        }
                );

                dist[src] = 0;
                set.add(new Solution2.Pair(src, 0));

                while (!set.isEmpty()) {

                    Solution2.Pair curr = set.pollFirst();
                    int node = curr.node;

                    for (Solution2.Pair neighbor : adj.get(node)) {

                        int newDist = dist[node] + neighbor.dist;

                        if (newDist < dist[neighbor.node]) {

                            // Remove old pair if exists
                            if (dist[neighbor.node] != Integer.MAX_VALUE) {
                                set.remove(new Solution2.Pair(neighbor.node,
                                        dist[neighbor.node]));
                            }

                            dist[neighbor.node] = newDist;
                            set.add(new Solution2.Pair(neighbor.node, newDist));
                        }
                    }
                }

                return dist;
            }

        }




// Print Shortest path using Dijkstra algo  : Source to destination
class Solution3 {

    static class Pair {
        int node, weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static List<Integer> shortestPath(int n, int m, int[][] edges) {

        // Adjacency List
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());

        for (int[] e : edges) {
            adj.get(e[0]).add(new Pair(e[1], e[2]));
            adj.get(e[1]).add(new Pair(e[0], e[2])); // undirected
        }

        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++)
            parent[i] = i;

        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> a.weight - b.weight);

        dist[1] = 0;
        pq.add(new Pair(1, 0));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();
            int node = curr.node;
            int d = curr.weight;

            if (d > dist[node])
                continue;

            for (Pair neighbor : adj.get(node)) {

                int newDist = d + neighbor.weight;

                if (newDist < dist[neighbor.node]) {

                    dist[neighbor.node] = newDist;
                    parent[neighbor.node] = node;

                    pq.add(new Pair(neighbor.node, newDist));
                }
            }
        }

        // If unreachable
        if (dist[n] == Integer.MAX_VALUE)
            return Arrays.asList(-1);

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        int node = n;

        while (parent[node] != node) {
            path.add(node);
            node = parent[node];
        }

        path.add(1);

        Collections.reverse(path);

        return path;
    }
}


// Shortest Distance in BinaryMaze

class Solution4 {

    static class Node {
        int row, col, dist;

        Node(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public static int shortestPath(int[][] grid,
                                   int[] source,
                                   int[] destination) {

        int n = grid.length;
        int m = grid[0].length;

        int sr = source[0];
        int sc = source[1];

        int dr = destination[0];
        int dc = destination[1];

        // Edge case
        if (grid[sr][sc] == 0 || grid[dr][dc] == 0)
            return -1;

        // If source == destination
        if (sr == dr && sc == dc)
            return 0;

        boolean[][] visited = new boolean[n][m];

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(sr, sc, 0));
        visited[sr][sc] = true;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {

                int newRow = current.row + dRow[i];
                int newCol = current.col + dCol[i];

                // Check valid cell
                if (newRow >= 0 && newRow < n &&
                        newCol >= 0 && newCol < m &&
                        grid[newRow][newCol] == 1 &&
                        !visited[newRow][newCol]) {

                    // If destination reached
                    if (newRow == dr && newCol == dc)
                        return current.dist + 1;

                    visited[newRow][newCol] = true;

                    queue.add(new Node(newRow,
                            newCol,
                            current.dist + 1));
                }
            }
        }

        return -1; // unreachable
    }
}

// Path with minimum effort(good question)


    static class Cell {
        int row, col, effort;

        Cell(int row, int col, int effort) {
            this.row = row;
            this.col = col;
            this.effort = effort;
        }
    }

    public int minimumEffortPath(int[][] heights) {

        int n = heights.length;
        int m = heights[0].length;

        int[][] dist = new int[n][m];

        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<Cell> pq =
                new PriorityQueue<>((a, b) -> a.effort - b.effort);

        pq.add(new Cell(0, 0, 0));
        dist[0][0] = 0;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            Cell current = pq.poll();

            int r = current.row;
            int c = current.col;
            int effort = current.effort;

            // If reached destination
            if (r == n - 1 && c == m - 1)
                return effort;

            for (int i = 0; i < 4; i++) {

                int newRow = r + dRow[i];
                int newCol = c + dCol[i];

                if (newRow >= 0 && newRow < n &&
                        newCol >= 0 && newCol < m) {

                    int edgeWeight = Math.abs(
                            heights[r][c] - heights[newRow][newCol]);

                    int newEffort = Math.max(effort, edgeWeight);

                    if (newEffort < dist[newRow][newCol]) {

                        dist[newRow][newCol] = newEffort;
                        pq.add(new Cell(newRow,
                                newCol,
                                newEffort));
                    }
                }
            }
        }

        return 0;
    }

    //Cheapest flight with K stop

    class Solution {
        class Truple{
            int tw,node,k;
            Truple(int tw,int node,int k){
                this.tw = tw;
                this.node = node;
                this.k = k;
            }
        }
        class Pair{
            int node, w;
            Pair(int node,int w){
                this.node = node;
                this. w = w;
            }
        }
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int dist[] = new int[n];
            List<List<Pair>> adj = new ArrayList<>();
            for(int i=0;i<n;i++){
                adj.add(new ArrayList<>());
            }
            int m = flights.length;
            for(int i=0;i<m;i++){
                adj.get(flights[i][0]).add(new Pair(flights[i][1],flights[i][2]));
            }
            Queue<Truple> q = new LinkedList<>();
            q.add(new Truple(0,src,0));
            for(int i=0;i<n;i++){
                dist[i] = Integer.MAX_VALUE;
            }
            dist[src] = 0;
            while(!q.isEmpty()){
                Truple peek = q.poll();
                int node = peek.node;
                int tk  = peek.k;
                int tw = peek.tw;
                if(tk>k){
                    continue;
                }
                for(Pair ele : adj.get(node)){
                    if(tw + ele.w<dist[ele.node] && tk<=k){
                        dist[ele.node] = tw + ele.w;
                        q.add(new Truple(tw+ele.w,ele.node,tk+1));
                    }
                }
            }
            return dist[dst]==Integer.MAX_VALUE ? -1 : dist[dst];
        }
    }



    // Minimum multiplication to reach end

    class Solution6 {

        class Pair {
            int value;
            int count;

            Pair(int value, int count) {
                this.value = value;
                this.count = count;
            }
        }

        public int minimumMultiplications(
                int[] arr,
                int start,
                int end) {

            int MOD = 100000;

            boolean[] visited = new boolean[MOD];

            Queue<Pair> queue = new LinkedList<>();

            queue.offer(new Pair(start, 0));
            visited[start] = true;

            while (!queue.isEmpty()) {

                Pair current = queue.poll();

                int value = current.value;
                int count = current.count;

                if (value == end) {
                    return count;
                }

                for (int multiplier : arr) {

                    int next =
                            (value * multiplier) % MOD;

                    if (!visited[next]) {

                        visited[next] = true;

                        queue.offer(
                                new Pair(
                                        next,
                                        count + 1
                                )
                        );
                    }
                }
            }

            return -1;
        }
    }



    // Number of ways to arrive destination

    class Solution7 {

        class Pair {
            int node;
            long distance;

            Pair(int node, long distance) {
                this.node = node;
                this.distance = distance;
            }
        }

        public int countPaths(int n, int[][] roads) {

            final long MOD = 1_000_000_007;

            List<List<Pair>> adj = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            // Undirected graph
            for (int[] road : roads) {

                int u = road[0];
                int v = road[1];
                int time = road[2];

                adj.get(u).add(
                        new Pair(v, time)
                );

                adj.get(v).add(
                        new Pair(u, time)
                );
            }

            long[] dist = new long[n];

            Arrays.fill(
                    dist,
                    Long.MAX_VALUE
            );

            long[] ways = new long[n];

            dist[0] = 0;
            ways[0] = 1;

            PriorityQueue<Pair> pq =
                    new PriorityQueue<>(
                            (a, b) ->
                                    Long.compare(
                                            a.distance,
                                            b.distance
                                    )
                    );

            pq.offer(new Pair(0, 0));

            while (!pq.isEmpty()) {

                Pair current = pq.poll();

                int node = current.node;
                long distance = current.distance;

                // Ignore outdated entry
                if (distance > dist[node]) {
                    continue;
                }

                for (Pair edge : adj.get(node)) {

                    int next = edge.node;
                    long newDist =
                            distance + edge.distance;

                    // Found a shorter path
                    if (newDist < dist[next]) {

                        dist[next] = newDist;

                        ways[next] = ways[node];

                        pq.offer(
                                new Pair(
                                        next,
                                        newDist
                                )
                        );
                    }

                    // Found another shortest path
                    else if (newDist == dist[next]) {

                        ways[next] =
                                (ways[next] + ways[node])
                                        % MOD;
                    }
                }
            }

            return (int) ways[n - 1];
        }
    }

}
        //End of Dijkstra Pattern

