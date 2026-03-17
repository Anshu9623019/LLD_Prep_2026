package DSA.Graph;

import java.util.*;

public class DijkstraAlgo {

    public static void main(String[] args) {

    }

    //Word ladder 2 : Check once again
    //Dijkstra's Algo : using Priority Queue
    class Solution {

        static class Pair {
            int node, dist;

            Pair(int node, int dist) {
                this.node = node;
                this.dist = dist;
            }
        }

        public int[] dijkstra(int V, List<List<GraphReprasentation.Solution3.Solution.Pair>> adj, int src) {

            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);

            PriorityQueue<GraphReprasentation.Solution3.Solution.Pair> pq =
                    new PriorityQueue<>((a, b) -> a.dist - b.dist);

            dist[src] = 0;
            pq.add(new GraphReprasentation.Solution3.Solution.Pair(src, 0));

            while (!pq.isEmpty()) {

                GraphReprasentation.Solution3.Solution.Pair curr = pq.poll();
                int node = curr.node;
                int d = curr.dist;

                if (d > dist[node]) continue; // Skip outdated entry

                for (GraphReprasentation.Solution3.Solution.Pair neighbor : adj.get(node)) {

                    int newDist = d + neighbor.dist;

                    if (newDist < dist[neighbor.node]) {
                        dist[neighbor.node] = newDist;
                        pq.add(new GraphReprasentation.Solution3.Solution.Pair(neighbor.node, newDist));
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

            public int[] dijkstra(int V, List<List<GraphReprasentation.Solution3.Solution.Solution.Pair>> adj, int src) {

                int[] dist = new int[V];
                Arrays.fill(dist, Integer.MAX_VALUE);

                TreeSet<GraphReprasentation.Solution3.Solution.Solution.Pair> set = new TreeSet<>(
                        (a, b) -> {
                            if (a.dist == b.dist)
                                return a.node - b.node;
                            return a.dist - b.dist;
                        }
                );

                dist[src] = 0;
                set.add(new GraphReprasentation.Solution3.Solution.Solution.Pair(src, 0));

                while (!set.isEmpty()) {

                    GraphReprasentation.Solution3.Solution.Solution.Pair curr = set.pollFirst();
                    int node = curr.node;

                    for (GraphReprasentation.Solution3.Solution.Solution.Pair neighbor : adj.get(node)) {

                        int newDist = dist[node] + neighbor.dist;

                        if (newDist < dist[neighbor.node]) {

                            // Remove old pair if exists
                            if (dist[neighbor.node] != Integer.MAX_VALUE) {
                                set.remove(new GraphReprasentation.Solution3.Solution.Solution.Pair(neighbor.node,
                                        dist[neighbor.node]));
                            }

                            dist[neighbor.node] = newDist;
                            set.add(new GraphReprasentation.Solution3.Solution.Solution.Pair(neighbor.node, newDist));
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

        Queue<GraphReprasentation.Solution4.Node> queue = new LinkedList<>();

        queue.add(new GraphReprasentation.Solution4.Node(sr, sc, 0));
        visited[sr][sc] = true;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            GraphReprasentation.Solution4.Node current = queue.poll();

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

                    queue.add(new GraphReprasentation.Solution4.Node(newRow,
                            newCol,
                            current.dist + 1));
                }
            }
        }

        return -1; // unreachable
    }
}

class Solution5 {

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

        PriorityQueue<GraphReprasentation.Solution.Cell> pq =
                new PriorityQueue<>((a, b) -> a.effort - b.effort);

        pq.add(new GraphReprasentation.Solution.Cell(0, 0, 0));
        dist[0][0] = 0;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            GraphReprasentation.Solution.Cell current = pq.poll();

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
                        pq.add(new GraphReprasentation.Solution.Cell(newRow,
                                newCol,
                                newEffort));
                    }
                }
            }
        }

        return 0;
    }
}

    class Solution6 {

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

            PriorityQueue<GraphReprasentation.Solution.Solution.Cell> pq =
                    new PriorityQueue<>((a, b) -> a.effort - b.effort);

            pq.add(new GraphReprasentation.Solution.Solution.Cell(0, 0, 0));
            dist[0][0] = 0;

            int[] dRow = {-1, 1, 0, 0};
            int[] dCol = {0, 0, -1, 1};

            while (!pq.isEmpty()) {

                GraphReprasentation.Solution.Solution.Cell current = pq.poll();

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
                            pq.add(new GraphReprasentation.Solution.Solution.Cell(newRow,
                                    newCol,
                                    newEffort));
                        }
                    }
                }
            }

            return 0;
        }

    }

    // Number of ways to arrive at destination
    class Solution7 {

        static class Pair {
            int node;
            long dist;

            Pair(int node, long dist) {
                this.node = node;
                this.dist = dist;
            }
        }

        public int countPaths(int n, int[][] roads) {

            int MOD = 1000000007;

            List<List<GraphReprasentation.Solution.Solution.Solution3.Pair>> adj = new ArrayList<>();

            for (int i = 0; i < n; i++)
                adj.add(new ArrayList<>());

            for (int[] r : roads) {
                adj.get(r[0]).add(new GraphReprasentation.Solution.Solution.Solution3.Pair(r[1], r[2]));
                adj.get(r[1]).add(new GraphReprasentation.Solution.Solution.Solution3.Pair(r[0], r[2]));
            }

            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE);

            int[] ways = new int[n];

            PriorityQueue<GraphReprasentation.Solution.Solution.Solution3.Pair> pq =
                    new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

            dist[0] = 0;
            ways[0] = 1;

            pq.add(new GraphReprasentation.Solution.Solution.Solution3.Pair(0, 0));

            while (!pq.isEmpty()) {

                GraphReprasentation.Solution.Solution.Solution3.Pair curr = pq.poll();
                int node = curr.node;
                long d = curr.dist;

                for (GraphReprasentation.Solution.Solution.Solution3.Pair nei : adj.get(node)) {

                    int adjNode = nei.node;
                    long weight = nei.dist;

                    long newDist = d + weight;

                    if (newDist < dist[adjNode]) {

                        dist[adjNode] = newDist;
                        ways[adjNode] = ways[node];

                        pq.add(new GraphReprasentation.Solution.Solution.Solution3.Pair(adjNode, newDist));
                    } else if (newDist == dist[adjNode]) {

                        ways[adjNode] =
                                (ways[adjNode] + ways[node]) % MOD;
                    }
                }
            }

            return ways[n - 1];
        }
    }


}
        //End of Dijstra Pettern

