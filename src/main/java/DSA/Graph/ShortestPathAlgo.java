package DSA.Graph;

import java.util.Arrays;

public class ShortestPathAlgo {

    public static void main(String[] args) {

    }

    //Bellman ford Lado
    //find Shortest path and detect cycle in negative graph.
    public int[] bellmanFord(int V, int[][] edges, int src) {

        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e8);

        dist[src] = 0;

        // Relax edges V-1 times
        for (int i = 0; i < V - 1; i++) {

            for (int[] e : edges) {

                int u = e[0];
                int v = e[1];
                int wt = e[2];

                if (dist[u] != 1e8 &&
                        dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Detect negative cycle
        for (int[] e : edges) {

            int u = e[0];
            int v = e[1];
            int wt = e[2];

            if (dist[u] != 1e8 &&
                    dist[u] + wt < dist[v]) {

                System.out.println("Negative cycle detected");
            }
        }

        return dist;
    }


    // floydWarshall algo
    public void floydWarshall(int[][] dist) {

        int V = dist.length;

        for (int k = 0; k < V; k++) {

            for (int i = 0; i < V; i++) {

                for (int j = 0; j < V; j++) {

                    if (dist[i][k] == Integer.MAX_VALUE ||
                            dist[k][j] == Integer.MAX_VALUE)
                        continue;

                    dist[i][j] = Math.min(
                            dist[i][j],
                            dist[i][k] + dist[k][j]
                    );
                }
            }
        }
    }


public int findTheCity(int n,
                       int[][] edges,
                       int distanceThreshold) {

    int INF = (int)1e9;

    int[][] dist = new int[n][n];

    for (int i = 0; i < n; i++) {
        Arrays.fill(dist[i], INF);
        dist[i][i] = 0;
    }

    for (int[] e : edges) {
        dist[e[0]][e[1]] = e[2];
        dist[e[1]][e[0]] = e[2];
    }

    // Floyd–Warshall
    for (int k = 0; k < n; k++) {

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                dist[i][j] = Math.min(
                        dist[i][j],
                        dist[i][k] + dist[k][j]
                );
            }
        }
    }

    int minCity = n;
    int result = -1;

    for (int i = 0; i < n; i++) {

        int count = 0;

        for (int j = 0; j < n; j++) {

            if (dist[i][j] <= distanceThreshold)
                count++;
        }

        if (count <= minCity) { // return height index if twi city have same number of min neigh

            minCity = count;
            result = i;
        }
    }

    return result;
}

}