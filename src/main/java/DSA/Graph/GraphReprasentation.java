package DSA.Graph;

import org.example.OppsByKunalKushwaha.Access.A;

import java.util.*;

public class GraphReprasentation {

    static class Pair {
        int val;
        int weight;

        Pair(int val, int weight) {
            this.val = val;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        //We can represent Graph in 2 ways
        // 1. Matrix;
        int n = 5;
        int m = 5;
        int graph[][] = new int[n + 1][m + 1];


        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                graph[i][j] = 1; // I is connected to j.
                graph[j][i] = 1; // I is connected to i;
                graph[i][j] = 10; // for weighted graph edge with vertex i and j and weight 10.
            }
        }

        //2. List
        ArrayList<ArrayList<Integer>> graph1 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph1.add(new ArrayList<>());
        }
        graph1.get(1).add(2); // 1 is connected to 2.

        //Representation for weighted graph
        ArrayList<ArrayList<Pair>> graph2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph2.add(new ArrayList<>());
        }
        graph2.get(1).add(new Pair(2, 10));


        GraphList gp = new GraphList(5);
        gp.addEdge(1, 2);
        gp.addEdge(1, 5);
        gp.addEdge(2, 3);
        gp.addEdge(5, 3);
        gp.addEdge(5, 4);
        gp.addEdge(3, 4);

        gp.printGraph();


        GraphMatrix gpMt = new GraphMatrix(8);
        gpMt.addEdge(1, 2);
        gpMt.addEdge(2, 3);
        gpMt.addEdge(4, 5);
        gpMt.addEdge(5, 6);
        gpMt.addEdge(7, 8);
        int[][] grid = {
                {1, 1, 0},
                {0, 1, 0},
                {1, 0, 1}
        };

    }


    // matrix
    static class GraphMatrix {
        int[][] matrix;
        int vertices;

        GraphMatrix(int v) {
            vertices = v;
            matrix = new int[v + 1][v + 1];
        }

        void addEdge(int u, int v) {
            matrix[u][v] = 1;
            matrix[v][u] = 1; // remove this line for directed graph
        }

        void printGraph() {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    //List
    static class GraphList {
        int vertices;
        LinkedList<Integer>[] adjList;

        GraphList(int v) {
            vertices = v;
            adjList = new LinkedList[v + 1];

            for (int i = 0; i <= v; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        void addEdge(int u, int v) {
            adjList[u].add(v);
            adjList[v].add(u); // remove for directed graph
        }

        void printGraph() {
            for (int i = 0; i <= vertices; i++) {
                System.out.print(i + " -> ");
                for (int neighbor : adjList[i]) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }
    }


    // Weighted Graph

    class Edge {
        int dest;
        int weight;

        Edge(int d, int w) {
            dest = d;
            weight = w;
        }
    }

    class WeightedDirectedGraph {
        int vertices;
        ArrayList<Edge>[] adjList;

        WeightedDirectedGraph(int v) {
            vertices = v;
            adjList = new ArrayList[v];

            for (int i = 0; i < v; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        void addEdge(int src, int dest, int weight) {
            adjList[src].add(new Edge(dest, weight));
        }

        void printGraph() {
            for (int i = 0; i < vertices; i++) {
                System.out.print(i + " -> ");
                for (Edge edge : adjList[i]) {
                    System.out.print("(" + edge.dest + ", " + edge.weight + ") ");
                }
                System.out.println();
            }
        }
    }

}